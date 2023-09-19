import './styles.scss';
import '@popperjs/core';
import 'bootstrap/js/src/collapse';
import { NavLink, Link } from 'react-router-dom';

export const Navbar = () => {
  return (
    <nav className="navbar navbar-expand-lg bg-primary">
      <div className="container-fluid">
        <Link
          to="/"
          className="navbar-brand fw-bolder d-lg-none d-flex align-items-center ms-3"
        >
          <h4>Locadora</h4>
        </Link>

        <button
          className="navbar-toggler ms-auto"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarCollapse"
          aria-controls="navbarCollapse"
          aria-expanded="false"
          aria-label="Expandir menu"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        <div className="collapse navbar-collapse d-lg-flex" id="navbarCollapse">
          <Link to="/" className="navbar-brand d-none d-lg-flex fw-bolder me-0">
            <h4>Locadora</h4>
          </Link>
          <ul className="navbar-nav mx-lg-auto">
            <li className="nav-item">
              <NavLink
                to="/"
                className={({ isActive }) => `nav-link text-uppercase fw-bolder fs-6 ${isActive ? 'active' : '' }`}
              >
                Home
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink
                to="/filmes"
                className="nav-link text-uppercase fw-bolder fs-6"
              >
                Catálogo
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink
                to="/admin"
                className="nav-link text-uppercase fw-bolder fs-6"
              >
                Admin
              </NavLink>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};
