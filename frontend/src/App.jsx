import React from 'react';
import { Routes, Route } from 'react-router-dom';
import MainLayout from './layouts/MainLayout';
import HomePage from './pages/HomePage';
import ApiTestPage from './pages/ApiTestPage';
import NotFoundPage from './pages/NotFoundPage';

export default function App() {
  return (
    <Routes>
      <Route path="/" element={<MainLayout />}>
        {/* 기본 홈 화면 */}
        <Route index element={<HomePage />} />
        {/* API 연결 테스트 화면 */}
        <Route path="api-test" element={<ApiTestPage />} />
        {/* 404 Not Found 화면 */}
        <Route path="*" element={<NotFoundPage />} />
      </Route>
    </Routes>
  );
}
