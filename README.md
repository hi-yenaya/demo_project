# Full-Stack Web Application (Spring Boot + React)

본 프로젝트는 **Spring Boot 3 (Java 17)** 기반의 멀티모듈 백엔드와 **React 19 + Vite** 기반의 SPA 프론트엔드로 구성된 풀스택 웹 애플리케이션입니다.

---

## 1. 프로젝트 구조

```text
project/
├── backend/                  # Spring Boot 3 멀티모듈 백엔드
│   ├── app/
│   │   ├── web/              # 웹 API 서버 (포트 8080)
│   │   └── batch/            # 배치 애플리케이션
│   └── module/
│       ├── domain/           # JPA 엔티티 및 Repository 계층
│       └── common/           # 공통 유틸 및 예외/응답 규격
├── frontend/                 # React + Vite 프론트엔드 (포트 5173)
│   ├── src/
│   │   ├── api/              # Axios API 통신 모듈
│   │   ├── components/       # 공통 UI 컴포넌트
│   │   ├── layouts/          # 페이지 레이아웃
│   │   └── pages/            # 라우팅 페이지 컴포넌트
│   └── vite.config.js        # Vite 설정 및 프록시 구성
└── README.md
```

---

## 2. 개발 환경 요구사항 (Prerequisites)

| 구분 | 권장 버전 | 비고 |
| :--- | :--- | :--- |
| **Java** | OpenJDK 17 이상 | 백엔드 빌드 및 런타임 |
| **Node.js** | Node.js 18.x 또는 20.x 이상 | 프론트엔드 패키지 매니저 및 빌드 |
| **npm** | 9.x 이상 (Node.js 포함) | 프론트엔드 의존성 관리 |
| **Database** | MySQL 8.0 이상 | 기본 데이터베이스 (포트 3306) |

---

## 3. 프론트엔드와 백엔드 통신 구조

### 3.1 통신 아키텍처 다이어그램

```text
[ 브라우저 (클라이언트) ]
         │
         │  (1) 개발 환경 (Local Dev)
         ▼
[ Vite Dev Server : 5173 ] ──── /api/* 프록시 전달 ────▶ [ Spring Boot : 8080 ]
                                                            │
                                                            │  (DB 접근)
                                                            ▼
                                                     [ MySQL DB : 3306 ]

-----------------------------------------------------------------------------

         │  (2) 운영 환경 (Production)
         ▼
[ Web Server (Nginx / IIS) : 80 or 443 ]
   ├── 정적 자원 (HTML, JS, CSS) ───────▶ 빌드 결과물 (frontend/dist)
   └── /api/* 요청 리버스 프록시 ───────▶ [ Spring Boot JAR : 8080 ]
```

### 3.2 개발 환경 통신 (Vite Reverse Proxy)
* **프론트엔드 주소**: `http://localhost:5173`
* **백엔드 API 주소**: `http://localhost:8080`
* 브라우저 보안 정책(CORS) 문제를 방지하기 위해 프론트엔드의 `vite.config.js`에서 `/api`로 들어오는 모든 요청을 백엔드(`http://localhost:8080`)로 투명하게 중계(Proxy)합니다.
* 예: 프론트엔드에서 `axios.get('/api/hello')` 호출 시 ➔ Vite가 `http://localhost:8080/api/hello`로 포워딩하여 CORS 에러 없이 통신됩니다.

### 3.3 운영 환경 통신 (Web Server Reverse Proxy)
* 프론트엔드 빌드 결과물(`frontend/dist` 디렉터리의 정적 파일)을 웹 서버(Nginx, IIS 등)가 서빙합니다.
* 웹 서버 설정에서 `/api` 경로의 요청만 백엔드 WAS(`http://127.0.0.1:8080`)로 리버스 프록시(Reverse Proxy)하도록 설정합니다.
* 따라서 클라이언트는 동일한 도메인/포트로 접속하므로 CORS 설정 없이 안전하게 동작합니다.

---

## 4. 로컬 개발 환경 퀵스타트 (Quick Start)

### 1단계: 백엔드 실행
1. 터미널을 열고 `backend` 폴더로 이동합니다.
   ```bash
   cd backend
   ```
2. 웹 모듈 실행:
   * **Windows**:
     ```powershell
     .\gradlew :app:web:bootRun --console=plain
     ```
   * **Linux / macOS**:
     ```bash
     ./gradlew :app:web:bootRun --console=plain
     ```
   * 서버가 정상 구동되면 `http://localhost:8080/hello` 접속 시 `"Hello, Spring Boot!"` 확인 가능.

### 2단계: 프론트엔드 실행
1. 새 터미널 창을 열고 `frontend` 폴더로 이동합니다.
   ```bash
   cd frontend
   ```
2. 패키지 설치 및 개발 서버 실행:
   ```bash
   npm install
   npm run dev
   ```
3. 브라우저에서 `http://localhost:5173` 접속 후 웹 화면 및 API 연동 테스트(`/api-test`) 확인.

---

## 5. 상세 안내 문서

* 백엔드 상세 빌드/배포 가이드: [backend/README.md](file:///d:/Workspace/project/backend/README.md)
* 프론트엔드 React 기초 & 빌드/배포 가이드: [frontend/README.md](file:///d:/Workspace/project/frontend/README.md)
