export const levelDefinitions = {
  high: {
    korean: "상",
    allowedDifficulties: ["D5", "D6", "D8", "A형"],
    color: "B60205",
  },
  medium: {
    korean: "중",
    allowedDifficulties: ["D3", "D4"],
    color: "FBCA04",
  },
  low: {
    korean: "하",
    allowedDifficulties: ["D1", "D2"],
    color: "0E8A16",
  },
};

const koreanWeekdays = {
  Sun: "일",
  Mon: "월",
  Tue: "화",
  Wed: "수",
  Thu: "목",
  Fri: "금",
  Sat: "토",
};

export function dateInfoInTimezone(timezone, date = new Date()) {
  const parts = Object.fromEntries(
    new Intl.DateTimeFormat("en-US", {
      timeZone: timezone,
      year: "numeric",
      month: "2-digit",
      day: "2-digit",
      weekday: "short",
    })
      .formatToParts(date)
      .filter((part) => part.type !== "literal")
      .map((part) => [part.type, part.value]),
  );

  return {
    key: `${parts.year}-${parts.month}-${parts.day}`,
    display: `${parts.year}-${parts.month}-${parts.day} (${koreanWeekdays[parts.weekday]})`,
  };
}

function validateProblem(level, problem, location) {
  const definition = levelDefinitions[level];
  if (!problem || typeof problem !== "object") {
    throw new Error(`${location}.${level} 문제가 없습니다.`);
  }

  for (const field of ["number", "title", "difficulty", "url"]) {
    if (typeof problem[field] !== "string" || problem[field].trim() === "") {
      throw new Error(`${location}.${level}.${field} 값이 필요합니다.`);
    }
  }

  if (!definition.allowedDifficulties.includes(problem.difficulty)) {
    throw new Error(
      `${location}.${level} 난이도는 ${definition.allowedDifficulties.join(", ")} 중 하나여야 합니다.`,
    );
  }

  try {
    const url = new URL(problem.url);
    if (url.protocol !== "https:" || url.hostname !== "swexpertacademy.com") {
      throw new Error();
    }
  } catch {
    throw new Error(`${location}.${level}.url은 SWEA의 https 문제 링크여야 합니다.`);
  }
}

export function validateProblemBank(bank) {
  if (!bank || typeof bank !== "object" || Array.isArray(bank)) {
    throw new Error("algorithm-problems.json은 JSON 객체여야 합니다.");
  }

  const problemNumbers = new Set();
  for (const level of Object.keys(levelDefinitions)) {
    if (!Array.isArray(bank[level]) || bank[level].length === 0) {
      throw new Error(`문제 은행의 ${level} 배열이 비어 있습니다.`);
    }
    for (const [index, problem] of bank[level].entries()) {
      validateProblem(level, problem, `algorithm-problems.${level}[${index}]`);
      if (problemNumbers.has(problem.number)) {
        throw new Error(`문제 은행에 중복된 문제 번호가 있습니다: ${problem.number}`);
      }
      problemNumbers.add(problem.number);
    }
  }
}

export function validateSchedule(schedule) {
  if (!schedule || typeof schedule !== "object" || Array.isArray(schedule)) {
    throw new Error("algorithm-schedule.json은 JSON 객체여야 합니다.");
  }

  const entries = Object.entries(schedule);
  if (entries.length === 0) {
    throw new Error("알고리즘 일정 파일이 비어 있습니다.");
  }

  for (const [date, problems] of entries) {
    if (date !== "_example" && !/^\d{4}-\d{2}-\d{2}$/.test(date)) {
      throw new Error(`날짜 키는 YYYY-MM-DD 형식이어야 합니다: ${date}`);
    }
    for (const level of Object.keys(levelDefinitions)) {
      validateProblem(level, problems[level], date);
    }
  }
}

export function buildIssueBody(level, problem, dateInfo) {
  const definition = levelDefinitions[level];
  return `<!-- daily-algorithm-date: ${dateInfo.key} -->
<!-- algorithm-level: ${level} -->
<!-- swea-problem-number: ${problem.number} -->
# SWEA ${problem.number} ${problem.title}

${dateInfo.display}의 알고리즘 문제입니다.

- 난이도: **${definition.korean} (${problem.difficulty})**
- 문제 링크: [SWEA ${problem.number} ${problem.title}](${problem.url})
- 마감: **오늘 23:59**

## 제출 방법

1. 자신의 Fork를 최신 상태로 동기화합니다.
2. 문제를 풀고 아래 경로에 저장합니다.
3. 공용 저장소의 \`main\` 브랜치로 Pull Request를 만듭니다.

\`\`\`text
solutions/<github-id>/${dateInfo.key}/${level}/${problem.number}.<확장자>
\`\`\`

코드 상단이나 별도 Markdown에 시간 복잡도와 풀이 아이디어를 함께 남기는 것을 권장합니다.
`;
}

export function buildDiscordMessage(level, problem, issueUrl) {
  const definition = levelDefinitions[level];
  return `📚 오늘의 알고리즘 문제가 등록되었습니다!
난이도: ${definition.korean} (${problem.difficulty})
문제: SWEA ${problem.number} ${problem.title}
마감: 오늘 23:59
제출 방법: Fork 저장소에서 풀이 후 PR 생성
문제 링크: ${problem.url}
Issue: ${issueUrl}`;
}
