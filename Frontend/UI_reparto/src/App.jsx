import { Routes, Route } from "react-router-dom";
import Menu from "./modulos/Menu/Menu";
import InsertarConductorPage from "./modulos/Conductor/InsertarConductor";
import ListarConductoresPage from "./modulos/Conductor/listarConductores";
import InsertarPaquetePage from "./modulos/Paquete/InsertarPaquete";
import ListarPaquetesPage from "./modulos/Paquete/listarPaquetes";
import InsertarVehiculoPage from "./modulos/Vehiculo/insertarVehiculo";
import ListarVehiculosPage from "./modulos/Vehiculo/listarVehiculo";
import InsertarMunicipioPage from "./modulos/Municipio/insertarMunicipio";
import ListarMunicipiosPage from "./modulos/Municipio/listarMunicipios";

function App() {
  return (
    <>
      <Menu />

      <Routes>
        <Route path="/insertarConductor" element={<InsertarConductorPage />} />
        <Route path="/listarConductor" element={<ListarConductoresPage />} />
        <Route path="/insertarVehiculo" element={<InsertarVehiculoPage />} />
        <Route path="/listarVehiculo" element={<ListarVehiculosPage />} />
        <Route path="/insertarPaquete" element={<InsertarPaquetePage />} />
        <Route path="/listarPaquete" element={<ListarPaquetesPage />} />
        <Route path="/insertarMunicipio" element={<InsertarMunicipioPage />} />
        <Route path="/listarMunicipio" element={<ListarMunicipiosPage />} />
      </Routes>
    </>
  );
}

export default App;
