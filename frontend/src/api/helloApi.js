import apiClient from './client';

/**
 * Spring Boot 헬스체크 및 연동 테스트 API 호출
 * GET /api/hello
 */
export const getHelloMessage = async () => {
  const response = await apiClient.get('/hello');
  return response.data; // { message: "Spring Boot API 연결 성공" }
};
