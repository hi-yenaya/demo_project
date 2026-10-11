import React from 'react';
import { NavLink } from 'react-router-dom';

export default function Sidebar() {
  return (
    <aside className="sidebar">
      <nav className="nav-menu">
        <NavLink 
          to="/" 
          end 
          className={({ isActive }) => (isActive ? 'nav-item active' : 'nav-item')}
        >
          🏠 홈 (Home)
        </NavLink>
        <NavLink 
          to="/api-test" 
          className={({ isActive }) => (isActive ? 'nav-item active' : 'nav-item')}
        >
          🔗 API 연결 테스트
        </NavLink>
      </nav>
    </aside>
  );
}
