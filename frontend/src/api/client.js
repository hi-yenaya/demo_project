import axios from 'axios';

/**
 * Axios 공통 인스턴스
 * - baseURL: '/api' (Vite 개발 프록시 및 운영 IIS 리버스 프록시 연동)
 * - timeout: 5000ms
 */
const apiClient = axios.create({
  baseURL: '/api',
  timeout: 5000,
  headers: {
    'Content-Type': 'application/json',
  },
});

// 응답 인터셉터: 공통 에러 핸들링
apiClient.interceptors.response.use(
  (response) => response,
  (error) => {
    console.error('[API Error]:', error.response || error.message);
    return Promise.reject(error);
  }
);

export default apiClient;
