# 구미 5반 알고리즘 스터디

매일 SWEA 문제를 상·중·하 한 문제씩 풀고, 각자의 Fork에서 풀이한 뒤 이 저장소로 Pull Request를 보내는 스터디입니다.

## 매일 진행 방식

1. 평일 오전 9시 7분(KST)에 문제 은행에서 상·중·하 문제가 하나씩 선택됩니다.
2. 관리자가 `data/algorithm-schedule.json`에 특정 날짜 문제를 등록한 경우 해당 문제가 우선됩니다.
3. 상·중·하 Issue가 각각 자동 생성됩니다.
4. Discord `#algorithm_study` 채널에 문제별 커스텀 알림 3개가 전송됩니다.
5. 학생은 자신의 Fork를 최신 상태로 동기화하고 풀이 브랜치를 만듭니다.
6. 풀이를 Commit·Push하고 공용 저장소의 `main` 브랜치로 Pull Request를 만듭니다.
7. PR 생성·수정·리뷰·병합 상태는 Discord의 GitHub 웹훅으로 공유됩니다.

마감은 매일 23:59입니다.

## 난이도 기준

| 구분 | SWEA 난이도 | 권장 대상 |
|---|---|---|
| 상 | D5, D6 | 심화 문제 |
| 중 | D3, D4 | 기본기를 응용하는 문제 |
| 하 | D1, D2 | 개념과 구현을 익히는 문제 |

SWEA 페이지를 무작위로 스크래핑하지 않습니다. 관리자가 검토해 둔 `data/algorithm-problems.json` 문제 은행을 난이도별로 순환하므로 안정적으로 같은 기준을 유지합니다.

## 문제 은행과 날짜별 지정

기본 문제는 `data/algorithm-problems.json`에서 관리합니다. 각 난이도의 문제를 모두 사용하면 처음 문제부터 다시 순환합니다.

특정 날짜에 다른 문제를 내고 싶으면 `data/algorithm-schedule.json`의 `_example`을 복사하고 날짜를 `YYYY-MM-DD`로 변경합니다.

```json
{
  "_example": {
    "high": {
      "number": "0000",
      "title": "관리자가 선택한 심화 문제",
      "difficulty": "D5",
      "url": "https://swexpertacademy.com/..."
    },
    "medium": {
      "number": "5215",
      "title": "햄버거 다이어트",
      "difficulty": "D3",
      "url": "https://swexpertacademy.com/..."
    },
    "low": {
      "number": "0000",
      "title": "관리자가 선택한 입문 문제",
      "difficulty": "D2",
      "url": "https://swexpertacademy.com/..."
    }
  },
  "2026-07-28": {
    "high": {
      "number": "문제번호",
      "title": "문제명",
      "difficulty": "D5",
      "url": "실제 SWEA 문제 링크"
    },
    "medium": {
      "number": "문제번호",
      "title": "문제명",
      "difficulty": "D4",
      "url": "실제 SWEA 문제 링크"
    },
    "low": {
      "number": "문제번호",
      "title": "문제명",
      "difficulty": "D2",
      "url": "실제 SWEA 문제 링크"
    }
  }
}
```

실제 저장 시에는 설명용 날짜와 가짜 링크를 사용하지 말고 SWEA에서 복사한 실제 문제 링크를 입력합니다. PR의 `알고리즘 자동화 검증` 체크가 문제 은행과 날짜별 일정의 필수 필드, 난이도 구간, URL을 검사합니다.

## Fork에서 제출하는 방법

최초 한 번:

```bash
git clone https://github.com/<내-github-id>/ALGO_GUMI_5_16th.git
cd ALGO_GUMI_5_16th
git remote add upstream https://github.com/YEOUL0520/ALGO_GUMI_5_16th.git
```

매일 제출할 때:

```bash
git fetch upstream
git switch main
git merge --ff-only upstream/main
git push origin main

git switch -c solution/<내-github-id>/2026-07-28
mkdir -p solutions/<내-github-id>/2026-07-28
```

풀이 파일은 다음 형식을 권장합니다.

```text
solutions/<github-id>/<YYYY-MM-DD>/<high|medium|low>/<문제번호>.<확장자>
```

예:

```text
solutions/octocat/2026-07-28/medium/5215.java
```

Commit과 Push:

```bash
git add solutions/<내-github-id>/2026-07-28
git commit -m "solve: 2026-07-28 SWEA"
git push -u origin solution/<내-github-id>/2026-07-28
```

GitHub에서 `base repository`는 `YEOUL0520/ALGO_GUMI_5_16th`, `base`는 `main`으로 선택합니다. PR 제목은 `[ALGO][2026-07-28] 이름` 형식을 권장합니다.

## Discord와 GitHub 설정

### 1. 매일 문제 커스텀 메시지

저장소 `Settings → Secrets and variables → Actions`에 Repository secret을 만듭니다.

- 이름: `DISCORD_WEBHOOK_URL`
- 값: `#algorithm_study` 채널의 원본 Discord 웹훅 URL
- 주의: Actions secret 값에는 `/github`를 붙이지 않습니다.

생성되는 메시지 예:

```text
📚 오늘의 알고리즘 문제가 등록되었습니다!
난이도: 중 (D3)
문제: SWEA 5215 햄버거 다이어트
마감: 오늘 23:59
제출 방법: Fork 저장소에서 풀이 후 PR 생성
```

### 2. PR·리뷰 실시간 알림

저장소 `Settings → Webhooks`의 Discord GitHub 연동은 다음처럼 설정합니다.

- Payload URL: Discord 웹훅 URL 뒤에 `/github` 추가
- Content type: `application/json`
- Secret: 비워 두기
- Events: Pull requests, Issue comments, Pull request reviews, Pull request review comments
- Active: 체크

`Issues` 이벤트는 커스텀 문제 알림과 중복되므로 선택 해제를 권장합니다. GitHub Webhook의 `Secret` 입력란에는 Discord 웹훅 주소를 넣지 않습니다.

## 관리자 운영

- 자동 출제: 월요일~금요일 09:07 KST
- 워크플로에 `Asia/Seoul` 시간대를 명시해 UTC 변환 오류를 방지합니다.
- GitHub Actions 예약 실행은 서비스 혼잡 시 지연될 수 있습니다.
- 일정 입력 권장 시각: 전날 23:59 이전
- 수동 실행: `Actions → 오늘의 알고리즘 문제 → Run workflow`
- 수동 실행 시 `study_date`에 `YYYY-MM-DD`를 넣어 특정 날짜를 테스트할 수 있습니다.
- 웹훅 URL은 README, Issue, 코드에 직접 적지 않습니다.
