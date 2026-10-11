# Backend Module (Spring Boot 3)

Spring Boot 3.3.4 및 Java 17 기반의 멀티모듈 백엔드 프로젝트입니다.

---

## 1. 모듈 구성 요약

* [app:web](file:///d:/Workspace/project/backend/app/web): 사용자 요청을 처리하는 웹 REST API 서버 (포트 `8080`)
* [app:batch](file:///d:/Workspace/project/backend/app/batch): 비동기 대용량 데이터 처리 배치 애플리케이션
* [module:domain](file:///d:/Workspace/project/backend/module/domain): JPA Entity, Repository, QueryDSL 및 비즈니스 도메인 계층
* [module:common](file:///d:/Workspace/project/backend/module/common): 공통 응답 규격(DTO), 전역 예외 처리, 공통 유틸리티

---

## 2. 로컬 빌드 및 실행 방법

> 실행 전 `backend` 디렉터리로 이동해야 합니다.

### 2.1 로컬 실행 (bootRun)

* **Windows (PowerShell)**:
  ```powershell
  .\gradlew :app:web:bootRun --console=plain
  ```
* **Linux / macOS**:
  ```bash
  chmod +x ./gradlew
  ./gradlew :app:web:bootRun --console=plain
  ```

> [!NOTE]
> DB 연결 정보는 [app/web/src/main/resources/application.yml](file:///d:/Workspace/project/backend/app/web/src/main/resources/application.yml)에 정의되어 있습니다. 환경 변수(`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`)가 없으면 기본값(`localhost:3306/ori_med`, `root`/`root`)이 적용됩니다.

---

## 3. 배포용 빌드 및 실행 (Linux & Windows)

### 3.1 배포용 JAR 빌드

* **Windows**:
  ```powershell
  .\gradlew :app:web:bootJar
  ```
* **Linux / macOS**:
  ```bash
  ./gradlew :app:web:bootJar
  ```
* 빌드 결과물 경로: `backend/app/web/build/libs/web-0.0.1-SNAPSHOT.jar`

---

### 3.2 Windows 환경 실행 및 배포

1. **콘솔 직접 실행**:
   ```powershell
   java -jar .\app\web\build\libs\web-0.0.1-SNAPSHOT.jar
   ```

2. **백그라운드 실행 (PowerShell)**:
   ```powershell
   Start-Process javaw -ArgumentList "-jar", ".\app\web\build\libs\web-0.0.1-SNAPSHOT.jar"
   ```

3. **환경 변수 지정 실행**:
   ```powershell
   $env:DB_URL="jdbc:mysql://서버IP:3306/ori_med"
   $env:DB_USERNAME="사용자명"
   $env:DB_PASSWORD="비밀번호"
   java -jar .\app\web\build\libs\web-0.0.1-SNAPSHOT.jar
   ```

---

### 3.3 Linux 환경 실행 및 배포

1. **포그라운드 실행**:
   ```bash
   java -jar ./app/web/build/libs/web-0.0.1-SNAPSHOT.jar
   ```

2. **백그라운드 실행 (nohup)**:
   ```bash
   nohup java -jar ./app/web/build/libs/web-0.0.1-SNAPSHOT.jar > ./app.log 2>&1 &
   
   # 실행 확인 및 로그 모니터링
   ps -ef | grep web-0.0.1-SNAPSHOT.jar
   tail -f ./app.log
   
   # 프로세스 종료
   kill -15 <PID>
   ```

3. **systemd 서비스 등록 배포 (권장)**:
   `/etc/systemd/system/demo-web.service` 파일 작성:
   ```ini
   [Unit]
   Description=Demo Web Spring Boot Application
   After=syslog.target network.target

   [Service]
   User=appuser
   ExecStart=/usr/bin/java -jar /opt/demo/web-0.0.1-SNAPSHOT.jar
   SuccessExitStatus=143
   Restart=always
   RestartSec=10

   [Install]
   WantedBy=multi-user.target
   ```
   서비스 구동 명령:
   ```bash
   sudo systemctl daemon-reload
   sudo systemctl enable demo-web
   sudo systemctl start demo-web
   sudo systemctl status demo-web
   ```
