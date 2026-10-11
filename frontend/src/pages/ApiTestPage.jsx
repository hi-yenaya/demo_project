import React, { useState, useEffect } from 'react';
import { getHelloMessage } from '../api/helloApi';

export default function ApiTestPage() {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  // 백엔드 API 호출 함수
  const fetchApi = async () => {
    setLoading(true);
    setError(null);
    setData(null);

    try {
      const result = await getHelloMessage();
      setData(result);
    } catch (err) {
      setError(err.message || 'API 호출에 실패했습니다.');
    } finally {
      setLoading(false);
    }
  };

  // 컴포넌트 마운트 시 1회 자동 호출
  useEffect(() => {
    fetchApi();
  }, []);

  return (
    <div className="page-container">
      <div className="page-header">
        <h1>Spring Boot API 연결 테스트</h1>
        <p className="description">
          Vite Proxy를 통해 Spring Boot 백엔드(<code>GET /api/hello</code>)를 호출하고 응답 상태를 확인합니다.
        </p>
      </div>

      <div className="test-card">
        <div className="test-actions">
          <button className="btn primary" onClick={fetchApi} disabled={loading}>
            {loading ? '연결 확인 중...' : '🔄 다시 테스트'}
          </button>
        </div>

        <div className="status-area">
          {/* 1. 로딩 상태 */}
          {loading && (
            <div className="status-box loading">
              <span className="spinner">⏳</span>
              <p>Spring Boot 서버(<code>http://localhost:8080/api/hello</code>)와 통신 중입니다...</p>
            </div>
          )}

          {/* 2. 에러 상태 */}
          {error && !loading && (
            <div className="status-box error">
              <h3>❌ 백엔드 연결 실패</h3>
              <p className="error-text"><strong>오류 내용:</strong> {error}</p>
              <div className="error-guide">
                <p><strong>점검 사항:</strong></p>
                <ul>
                  <li>Spring Boot 서버가 구동 중인지 확인해 주세요. (포트: 8080)</li>
                  <li>백엔드 실행 명령어: <code>.\gradlew.bat :app:web:bootRun</code></li>
                </ul>
              </div>
            </div>
          )}

          {/* 3. 성공 상태 */}
          {data && !loading && (
            <div className="status-box success">
              <h3>✅ Spring Boot 백엔드 연결 성공!</h3>
              <p className="success-message">
                응답 메시지: <strong>{data.message}</strong>
              </p>
              <div className="json-viewer">
                <h4>원본 JSON 응답:</h4>
                <pre>{JSON.stringify(data, null, 2)}</pre>
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}
