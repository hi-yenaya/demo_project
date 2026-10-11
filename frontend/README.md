# Frontend Module (React 19 + Vite)

본 문서는 React에 익숙하지 않은 개발자분들도 프로젝트의 구조, 동작 원리, 개발 및 배포 방법을 쉽게 이해하실 수 있도록 상세히 설명한 가이드입니다.

---

## 1. React & Vite 입문자를 위한 기초 개념

### 1.1 React란 무엇인가요?
* **컴포넌트(Component) 기반**: 웹 화면을 레고 블록처럼 작은 UI 조각(컴포넌트)으로 쪼개서 만들고 조합하는 UI 라이브러리입니다.
* **JSX 문법**: 자바스크립트 파일(`*.jsx`) 안에서 HTML 태그를 직접 작성할 수 있는 직관적인 문법입니다.
* **SPA (Single Page Application)**: 페이지를 이동할 때마다 서버에서 새로운 HTML을 받아오지 않고, 최초에 `index.html` 딱 1장만 로드한 후 JavaScript가 필요한 화면 부분만 실시간으로 갈아 끼웁니다. 덕분에 브라우저 깜빡임 없이 부드러운 앱 경험을 제공합니다.

### 1.2 주요 React 핵심 요소
1. **컴포넌트 (Component)**:
   * 화면의 한 조각을 나타내는 함수입니다. (예: `HomePage.jsx`, `Button.jsx`)
2. **State (상태 - `useState`)**:
   * 화면에 표시되는 **"동적으로 변하는 데이터"**입니다. 이 상태 값이 바뀌면 React가 화면을 자동으로 감지하여 다시 그립니다(Re-rendering).
3. **Effect (부수효과 - `useEffect`)**:
   * 화면이 처음 브라우저에 나타날 때(마운트될 때) 백엔드 API를 호출하여 데이터를 받아오는 등의 작업을 수행합니다.
4. **Router (라우터 - `react-router-dom`)**:
   * 브라우저 URL 주소(`/`, `/api-test` 등)에 따라 어떤 페이지 컴포넌트를 보여줄지 결정해 주는 네비게이터 역할을 합니다.

### 1.3 Vite(비트)란?
* 기존의 무거운 번들러(Webpack)를 대체하는 초고속 프론트엔드 빌드 툴입니다.
* 코드 수정 시 브라우저 새로고침 없이 변경된 부분만 밀리초(ms) 단위로 즉시 반영(HMR)해 줍니다.

---

## 2. 프론트엔드 디렉터리 구조 및 파일별 역할

```text
frontend/
├── index.html            # [중요] 브라우저가 최초로 읽는 단 하나의 HTML 파일 (<div id="root">)
├── vite.config.js        # Vite 설정 파일 (포트: 5173, /api 백엔드 프록시 설정 포함)
├── package.json          # 프로젝트 의존성 라이브러리 목록 및 실행 스크립트 정의
└── src/
    ├── main.jsx          # React의 진입점(Entry Point). index.html의 root에 App을 주입
    ├── App.jsx           # 전체 라우팅(경로별 페이지 매핑)을 정의하는 최상위 컴포넌트
    ├── index.css         # 전역 스타일시트
    │
    ├── api/              # 백엔드 Spring Boot와의 통신을 담당하는 계층
    │   ├── client.js     # Axios 공통 설정 (baseURL: '/api', 에러 인터셉터 등)
    │   └── helloApi.js   # 개별 API 호출 함수 모음 (GET /api/hello 등)
    │
    ├── layouts/          # 페이지 공통 레이아웃 (헤더, 네비게이션 바, 메인 컨테이너)
    │   └── MainLayout.jsx
    │
    ├── pages/            # 각 URL 경로에 해당하는 실제 화면 컴포넌트
    │   ├── HomePage.jsx      # 메인 대시보드 화면 ('/')
    │   ├── ApiTestPage.jsx   # 백엔드 API 연동 테스트 화면 ('/api-test')
    │   └── NotFoundPage.jsx  # 404 페이지 ('/*')
    │
    ├── components/       # 재사용 가능한 UI 컴포넌트 (버튼, 모달, 카드 등)
    ├── hooks/            # 커스텀 React 훅
    └── utils/            # 날짜 포맷, 공통 유틸 함수
```

---

## 3. 백엔드 통신 및 프록시(Proxy) 동작 원리

개발 환경에서는 브라우저 보안 정책인 **CORS(Cross-Origin Resource Sharing)** 에러를 방지하기 위해 Vite 프록시를 사용합니다.

* [vite.config.js](file:///d:/Workspace/project/frontend/vite.config.js) 설정:
  ```javascript
  server: {
    port: 5173,
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // 백엔드 주소
        changeOrigin: true,
      },
    },
  }
  ```
* 프론트엔드 코드에서 `axios.get('/api/hello')`를 호출하면:
  1. 브라우저는 `http://localhost:5173/api/hello`로 요청합니다 (같은 도메인이므로 CORS 발생 안 함).
  2. Vite 개발 서버가 이를 가로채서 `http://localhost:8080/api/hello`로 백엔드에 대신 전달합니다.
  3. 백엔드 응답을 받아 브라우저에 반환합니다.

---

## 4. 로컬 개발 환경 실행 방법

> Node.js 18 이상이 설치되어 있어야 합니다.

1. **터미널에서 frontend 디렉터리로 이동:**
   ```bash
   cd frontend
   ```
2. **패키지(라이브러리) 설치 (최초 1회 또는 package.json 변경 시):**
   ```bash
   npm install
   ```
3. **로컬 개발 서버 실행:**
   ```bash
   npm run dev
   ```
4. **브라우저 접속:**
   * `http://localhost:5173` 접속
   * 백엔드가 실행 중인 상태에서 상단 메뉴의 **"API 연동 테스트"** 탭을 클릭하여 통신 상태 확인

---

## 5. 프로덕션 빌드 (Build)의 이해

React 코드는 개발할 때는 JSX와 최신 문법으로 작성되지만, 배포할 때는 모든 브라우저가 인식할 수 있는 순수한 HTML, CSS, JavaScript 파일로 변환(압축 및 난독화)되어야 합니다.

* **빌드 명령어 실행:**
  ```bash
  npm run build
  ```
* **빌드 결과물:**
  * 실행 완료 시 `frontend/dist/` 폴더가 생성됩니다.
  * `dist/` 내부에는 `index.html`과 최적화된 정적 자원(`assets/*.js`, `assets/*.css`)만 남습니다.
  * **중요:** 실제 배포 시에는 소스코드가 아니라 오직 이 **`dist/` 폴더 내용물**만 웹 서버에 올리면 됩니다.

---

## 6. 운영 환경 배포 가이드 (Linux & Windows)

React SPA는 백엔드 서버(Node.js) 없이 **정적 웹 서버(Nginx, Apache, IIS)**를 통해 서빙하는 것이 표준입니다.

> [!WARNING] **SPA 배포 시 필수 주의점 (새로고침 404 문제)**  
> 사용자가 `http://mysite.com/api-test` 화면에서 F5(새로고침)를 누르면, 웹 서버는 서버 하드디스크에서 `/api-test`라는 실제 파일이나 폴더를 찾으려다 없어서 **404 Not Found**를 띄웁니다.  
> 따라서 웹 서버에 **"경로를 찾을 수 없으면 무조건 `index.html`을 반환하라(Fallback)"**는 Rewrite 규칙을 반드시 적용해야 합니다.

---

### 6.1 Linux 환경 배포 (Nginx 사용)

1. **`dist` 폴더 복사:**
   * 로컬에서 빌드한 `dist/` 안의 파일들을 서버의 `/var/www/demo-frontend` 경로로 업로드합니다.

2. **Nginx 가상 호스트 설정 (`/etc/nginx/conf.d/demo.conf`):**
   ```nginx
   server {
       listen 80;
       server_name your-domain.com; # 도메인 또는 서버 IP

       # 1. 프론트엔드 정적 파일 서빙
       location / {
           root /var/www/demo-frontend;
           index index.html index.htm;
           # [핵심] SPA 새로고침 404 방지 설정
           try_files $uri $uri/ /index.html;
       }

       # 2. 백엔드 Spring Boot API 리버스 프록시 연동
       location /api/ {
           proxy_pass http://127.0.0.1:8080;
           proxy_set_header Host $host;
           proxy_set_header X-Real-IP $remote_addr;
           proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
       }
   }
   ```

3. **Nginx 설정 검사 및 재시작:**
   ```bash
   sudo nginx -t
   sudo systemctl restart nginx
   ```

---

### 6.2 Windows 환경 배포 (IIS 사용)

1. **사전 준비:**
   * IIS에 **URL Rewrite (URL 재작성)** 확장 모듈 및 **Application Request Routing (ARR)** 모듈이 설치되어 있어야 합니다.

2. **파일 배치:**
   * `dist/` 내부의 모든 파일과 폴더를 IIS 웹사이트 홈 디렉터리(예: `C:\inetpub\wwwroot\demo`)로 복사합니다.

3. **`web.config` 파일 추가:**
   `C:\inetpub\wwwroot\demo\web.config` 파일 생성:
   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <configuration>
     <system.webServer>
       <rewrite>
         <rules>
           <!-- 1. 백엔드 API 리버스 프록시 규칙 -->
           <rule name="ReverseProxyToBackend" stopProcessing="true">
             <match url="^api/(.*)" />
             <action type="Rewrite" url="http://localhost:8080/api/{R:1}" />
           </rule>

           <!-- 2. React SPA 새로고침 404 방지 규칙 -->
           <rule name="React SPA Fallback" stopProcessing="true">
             <match url=".*" />
             <conditions logicalGrouping="MatchAll">
               <add input="{REQUEST_FILENAME}" matchType="IsFile" negate="true" />
               <add input="{REQUEST_FILENAME}" matchType="IsDirectory" negate="true" />
             </conditions>
             <action type="Rewrite" url="/" />
           </rule>
         </rules>
       </rewrite>
     </system.webServer>
   </configuration>
   ```

---

## 7. 자주 묻는 질문 및 트러블슈팅

* **Q. API 호출 시 404가 발생합니다.**
  * 백엔드 서버(`http://localhost:8080`)가 켜져 있는지 확인하세요.
  * 요청 URL이 `/api/...`로 시작하는지 확인하세요 ([src/api/client.js](file:///d:/Workspace/project/frontend/src/api/client.js)에 `baseURL: '/api'`가 설정되어 있습니다).
* **Q. `npm install` 시 에러가 납니다.**
  * Node.js 버전이 18 이상인지 `node -v`로 확인해 보세요.
  * `node_modules` 폴더와 `package-lock.json`을 삭제한 후 다시 `npm install`을 실행해 보세요.
