import './styles.scss';
import '@popperjs/core';
import 'bootstrap/js/src/collapse';

export const Navbar = () => {
  return (
    <nav className="navbar navbar-expand-lg bg-primary">
      <div className="container-fluid">
        <a className="navbar-brand fw-bolder d-lg-none d-flex align-items-center ms-3">
          <h4>Locadora</h4>
        </a>

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
          <a className="navbar-brand d-none d-lg-flex fw-bolder me-0">
            <h4>Locadora</h4>
          </a>
          <ul className="navbar-nav mx-lg-auto">
            <li className="nav-item">
              <a className="nav-link text-uppercase fw-bolder fs-6 active">
                Home
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link text-uppercase fw-bolder fs-6" href="#">
                Catálogo
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link text-uppercase fw-bolder fs-6" href="#">
                Admin
              </a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};
