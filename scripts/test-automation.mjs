import assert from "node:assert/strict";
import { readFile } from "node:fs/promises";
import {
  buildDiscordMessage,
  buildIssueBody,
  dateInfoInTimezone,
  validateSchedule,
} from "./study-utils.mjs";

const schedule = JSON.parse(
  await readFile(
    new URL("../data/algorithm-schedule.json", import.meta.url),
    "utf8",
  ),
);
assert.doesNotThrow(() => validateSchedule(schedule));

const dateInfo = dateInfoInTimezone(
  "Asia/Seoul",
  new Date("2026-07-26T22:00:00.000Z"),
);
assert.deepEqual(dateInfo, {
  key: "2026-07-27",
  display: "2026-07-27 (월)",
});

const example = schedule._example;
for (const level of ["high", "medium", "low"]) {
  const body = buildIssueBody(level, example[level], dateInfo);
  assert.match(body, new RegExp(`algorithm-level: ${level}`));
  assert.match(body, /마감: \*\*오늘 23:59\*\*/);

  const message = buildDiscordMessage(
    level,
    example[level],
    `https://github.com/example/issues/${level}`,
  );
  assert.match(message, /📚 오늘의 알고리즘 문제/);
  assert.match(message, /제출 방법: Fork 저장소에서 풀이 후 PR 생성/);
}

assert.throws(
  () =>
    validateSchedule({
      "2026-07-27": {
        ...example,
        high: { ...example.high, difficulty: "D2" },
      },
    }),
  /D5, D6/,
);

console.log("알고리즘 자동화 테스트 통과: 상·중·하 일정과 Discord 메시지");
