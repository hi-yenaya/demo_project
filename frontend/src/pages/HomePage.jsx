import React from 'react';

export default function HomePage() {
  return (
    <div className="page-container">
      <div className="page-header">
        <h1>대시보드 홈</h1>
        <p className="description">
          Spring Boot Gradle 멀티모듈 백엔드와 Vite + React 프론트엔드가 연동된 관리자 페이지입니다.
        </p>
      </div>

      <div className="card-grid">
        <div className="card">
          <div className="card-icon">☕</div>
          <h3>Spring Boot Backend</h3>
          <p><strong>포트:</strong> 8080</p>
          <p><strong>구조:</strong> Gradle 멀티모듈 (Web, Batch, Domain, Common)</p>
          <p><strong>DB:</strong> MySQL (ori_med) + JPA + QueryDSL</p>
        </div>

        <div className="card">
          <div className="card-icon">⚡</div>
          <h3>React Frontend</h3>
          <p><strong>포트:</strong> 5173</p>
          <p><strong>번들러:</strong> Vite (Fast Refresh)</p>
          <p><strong>통신/라우팅:</strong> Axios, React Router</p>
        </div>

        <div className="card">
          <div className="card-icon">🏢</div>
          <h3>운영 배포 아키텍처</h3>
          <p><strong>프론트엔드:</strong> Windows Server IIS (dist 정적 파일)</p>
          <p><strong>백엔드:</strong> Java Jar (내장 Tomcat)</p>
          <p><strong>연동:</strong> IIS Reverse Proxy (/api/**)</p>
        </div>
      </div>
    </div>
  );
}
