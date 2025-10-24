import React, { useEffect, useState } from "react";
import "../Conductor/listarConductores.css";

export default function ListarMunicipiosPage() {
  const [municipios, setMunicipios] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [editando, setEditando] = useState(null);
  const [formData, setFormData] = useState({
    nombre: "",
    provincia: "",
  });

  useEffect(() => {
    obtenerMunicipios();
  }, []);

  const obtenerMunicipios = async () => {
    setLoading(true);
    setError(null);
    try {
      const response = await fetch("http://localhost:8080/listarMunicipios", {
        headers: { "Content-Type": "application/json" },
      });
      if (!response.ok) throw new Error("Error al obtener municipios");
      const data = await response.json();
      setMunicipios(data);
    } catch (err) {
      setError("No se pudieron cargar los municipios.");
    } finally {
      setLoading(false);
    }
  };

  const eliminarMunicipio = async (codigo) => {
    if (!window.confirm("¿Seguro que deseas eliminar este municipio?")) return;
    try {
      const response = await fetch(
        `http://localhost:8080/eliminarMunicipio/${codigo}`,
        { method: "DELETE" }
      );
      if (response.ok) {
        alert("Municipio eliminado correctamente.");
        obtenerMunicipios();
      } else {
        alert("Error al eliminar el municipio.");
      }
    } catch (error) {
      alert("Error de conexión al eliminar.");
    }
  };

  const comenzarEdicion = (municipio) => {
    setEditando(municipio.codigoMunicipio);
    setFormData({ nombre: municipio.nombre, provincia: municipio.provincia });
  };

  const cancelarEdicion = () => {
    setEditando(null);
    setFormData({ nombre: "", provincia: "" });
  };

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const guardarCambios = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/actualizarMunicipio/${editando}`,
        {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(formData),
        }
      );
      if (response.ok) {
        alert("Municipio actualizado correctamente.");
        setEditando(null);
        obtenerMunicipios();
      } else {
        alert("Error al actualizar el municipio.");
      }
    } catch (error) {
      alert("Error de conexión al actualizar.");
    }
  };

  return (
    <div className="listar-conductores-container">
      <h2>Lista de Municipios</h2>

      {loading && <p>Cargando municipios...</p>}
      {error && <p className="error">{error}</p>}
      {!loading && !error && municipios.length === 0 && (
        <p>No hay municipios registrados.</p>
      )}

      <div className="conductores-list">
        {municipios.map((m) => (
          <div key={m.codigoMunicipio} className="conductor-card">
            <p>
              <strong>Código:</strong> {m.codigoMunicipio}
            </p>
            <p>
              <strong>Nombre:</strong> {m.nombre}
            </p>
            <p>
              <strong>Provincia:</strong> {m.provincia}
            </p>

            <div className="acciones">
              <button onClick={() => comenzarEdicion(m)}>Modificar</button>
              <button
                className="eliminar"
                onClick={() => eliminarMunicipio(m.codigoMunicipio)}
              >
                Eliminar
              </button>
            </div>

            {editando === m.codigoMunicipio && (
              <div className="editar-form">
                <h4>Editar Municipio</h4>
                <input
                  type="text"
                  name="nombre"
                  value={formData.nombre}
                  onChange={handleChange}
                  placeholder="Nombre"
                />
                <input
                  type="text"
                  name="provincia"
                  value={formData.provincia}
                  onChange={handleChange}
                  placeholder="Provincia"
                />

                <div className="acciones">
                  <button onClick={guardarCambios}>Guardar cambios</button>
                  <button className="cancelar" onClick={cancelarEdicion}>
                    Cancelar
                  </button>
                </div>
              </div>
            )}
          </div>
        ))}
      </div>
    </div>
  );
}
