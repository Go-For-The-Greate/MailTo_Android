# MailTo_Android
프로젝트 스터디


## 기능 최소 요구사항

### 1) 메일 작성

- 이모티콘 허용
- 사용자 이메일 기입 → 이메일 양식이 아니면 따로 UI 표시하거나 예외처리 필요
- 최대 글자수는 제한 없음
- 제목 칸과 본문 칸 따로 UI 두기 → 어느 정도 선까지 관용 o

### 2) 메일 전송

- 메일 전송중임을 사용자에게 알려주기 위한 UI 넣기
- 메일 전송 완료 UI(다이알로그, 스낵바, 화면 등등) 필요

+) 옵션

- ~~메일 전송~~액션하고 사용자가 기입한 이메일로 전송완료됐다는 확인 메일 보내기 (option, 되면 하기)

### 3) 메일 보낸 흔적

- 사용자가 몇 번 메일을 보냈는지 횟수와 UI 필요 (로컬 DB에 저장하기)
    - ex) 메일 보낼때마다 나무에 사과가 열린다던지 등등 창의적 아이디어 필요, 리스트나 횟수만 있어도 상관 x


## 사용된 기술 혹은 라이브러리 목록
- Compose
- Screen API
