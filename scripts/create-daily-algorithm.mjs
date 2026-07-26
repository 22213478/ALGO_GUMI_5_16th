import { readFile } from "node:fs/promises";
import { fileURLToPath } from "node:url";
import path from "node:path";
import { createGitHubClient } from "./github-client.mjs";
import {
  buildDiscordMessage,
  buildIssueBody,
  dateInfoInTimezone,
  levelDefinitions,
  validateProblemBank,
  validateSchedule,
} from "./study-utils.mjs";

const root = path.resolve(path.dirname(fileURLToPath(import.meta.url)), "..");
const timezone = "Asia/Seoul";
const webhookUrl = process.env.DISCORD_WEBHOOK_URL;

if (!webhookUrl) {
  throw new Error("Actions secret DISCORD_WEBHOOK_URL이 필요합니다.");
}
if (/\/github\/?(?:\?.*)?$/.test(webhookUrl)) {
  throw new Error("DISCORD_WEBHOOK_URL에는 /github를 붙이지 않은 원본 Discord 웹훅 URL을 사용하세요.");
}

const schedule = JSON.parse(
  await readFile(path.join(root, "data", "algorithm-schedule.json"), "utf8"),
);
const problemBank = JSON.parse(
  await readFile(path.join(root, "data", "algorithm-problems.json"), "utf8"),
);
validateSchedule(schedule);
validateProblemBank(problemBank);

const automaticDate = dateInfoInTimezone(timezone);
const requestedDate = process.env.STUDY_DATE?.trim();
if (requestedDate && !/^\d{4}-\d{2}-\d{2}$/.test(requestedDate)) {
  throw new Error("STUDY_DATE는 YYYY-MM-DD 형식이어야 합니다.");
}
const dateInfo = requestedDate
  ? { key: requestedDate, display: requestedDate }
  : automaticDate;
const scheduledProblems = schedule[dateInfo.key];

const { api, paginate, ensureLabel, repository } = createGitHubClient();
await ensureLabel("daily-algorithm", "1D76DB", "매일 자동으로 출제되는 알고리즘 문제");
await ensureLabel("discord-notified", "0E8A16", "Discord 커스텀 알림 전송 완료");

const issues = await paginate(
  `/repos/${repository}/issues?state=all&labels=daily-algorithm`,
);

for (const [level, definition] of Object.entries(levelDefinitions)) {
  const dateMarker = `<!-- daily-algorithm-date: ${dateInfo.key} -->`;
  const levelMarker = `<!-- algorithm-level: ${level} -->`;
  let issue = issues.find(
    (candidate) =>
      candidate.body?.includes(dateMarker) && candidate.body?.includes(levelMarker),
  );
  const previousIssueCount = issues.filter(
    (candidate) =>
      candidate.body?.includes(levelMarker) && !candidate.body?.includes(dateMarker),
  ).length;
  const problem =
    scheduledProblems?.[level] ??
    problemBank[level][previousIssueCount % problemBank[level].length];

  if (!issue) {
    const levelLabel = `난이도:${definition.korean}`;
    const sweaLabel = `SWEA:${problem.difficulty}`;
    await ensureLabel(levelLabel, definition.color, `${definition.korean} 난이도 문제`);
    await ensureLabel(sweaLabel, "C5DEF5", "SWEA 난이도");

    issue = await api(`/repos/${repository}/issues`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        title: `[오늘의 알고리즘][${definition.korean}] SWEA ${problem.number} ${problem.title}`,
        body: buildIssueBody(level, problem, dateInfo),
        labels: ["daily-algorithm", levelLabel, sweaLabel],
      }),
    });
  }

  const labels = issue.labels.map((label) => label.name ?? label);
  if (labels.includes("discord-notified")) {
    console.log(`${definition.korean} 문제는 이미 Issue와 Discord에 등록됐습니다.`);
    continue;
  }

  const discordResponse = await fetch(
    `${webhookUrl}${webhookUrl.includes("?") ? "&" : "?"}wait=true`,
    {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        username: "구미 5반 알고리즘 스터디",
        content: buildDiscordMessage(level, problem, issue.html_url),
        allowed_mentions: { parse: [] },
      }),
    },
  );

  if (!discordResponse.ok) {
    throw new Error(
      `Discord 웹훅 ${discordResponse.status}: ${await discordResponse.text()}`,
    );
  }

  await api(`/repos/${repository}/issues/${issue.number}`, {
    method: "PATCH",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ labels: [...labels, "discord-notified"] }),
  });
  console.log(`${definition.korean} 문제와 Discord 알림을 생성했습니다: ${issue.html_url}`);
}
