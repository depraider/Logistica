import { Link } from "react-router-dom";
import { useState } from "react";
import "./Menu.css";

function Menu() {
  const [activeMenu, setActiveMenu] = useState(null);

  const toggleMenu = (menu) => {
    setActiveMenu(activeMenu === menu ? null : menu);
  };

  return (
    <header className="header">
      <nav className="navbar">
        <ul className="nav-links">
          <li
            className={`dropdown ${
              activeMenu === "conductores" ? "active" : ""
            }`}
            onClick={() => toggleMenu("conductores")}
          >
            <span>Conductores</span>
            {activeMenu === "conductores" && (
              <ul className="dropdown-content">
                <li>
                  <Link to="/insertarConductor">Insertar</Link>
                </li>
                <li>
                  <Link to="/listarConductor">Listar</Link>
                </li>
              </ul>
            )}
          </li>

          <li
            className={`dropdown ${activeMenu === "vehiculos" ? "active" : ""}`}
            onClick={() => toggleMenu("vehiculos")}
          >
            <span>Vehículos</span>
            {activeMenu === "vehiculos" && (
              <ul className="dropdown-content">
                <li>
                  <Link to="/insertarVehiculo">Insertar</Link>
                </li>
                <li>
                  <Link to="/listarVehiculo">Listar</Link>
                </li>
              </ul>
            )}
          </li>

          <li
            className={`dropdown ${activeMenu === "paquetes" ? "active" : ""}`}
            onClick={() => toggleMenu("paquetes")}
          >
            <span>Paquetes</span>
            {activeMenu === "paquetes" && (
              <ul className="dropdown-content">
                <li>
                  <Link to="/insertarPaquete">Insertar</Link>
                </li>
                <li>
                  <Link to="/listarPaquete">Listar</Link>
                </li>
              </ul>
            )}
          </li>

          <li
            className={`dropdown ${
              activeMenu === "municipios" ? "active" : ""
            }`}
            onClick={() => toggleMenu("municipios")}
          >
            <span>Municipios</span>
            {activeMenu === "municipios" && (
              <ul className="dropdown-content">
                <li>
                  <Link to="/insertarMunicipio">Insertar</Link>
                </li>
                <li>
                  <Link to="/listarMunicipio">Listar</Link>
                </li>
              </ul>
            )}
          </li>
        </ul>
      </nav>
    </header>
  );
}

export default Menu;
