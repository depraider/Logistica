import React, { useEffect, useState } from "react";
import "./ListarConductores.css";

export default function ListarConductoresPage() {
  const [conductores, setConductores] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [editando, setEditando] = useState(null); // dni del conductor en edición
  const [formData, setFormData] = useState({
    nombre: "",
    telefono: "",
    direccion: "",
    salario: "",
    municipioResidencia: "",
  });

  useEffect(() => {
    obtenerConductores();
  }, []);

  const obtenerConductores = async () => {
    setLoading(true);
    setError(null);

    try {
      const response = await fetch("http://localhost:8080/listarConductores", {
        headers: { "Content-Type": "application/json" },
      });

      if (!response.ok) throw new Error(`Error al obtener conductores (status ${response.status})`);

      const data = await response.json();
      setConductores(data);
    } catch (err) {
      console.error("Error al cargar conductores:", err);
      setError("❌ No se pudieron cargar los conductores. ¿Está corriendo el backend?");
    } finally {
      setLoading(false);
    }
  };

  const eliminarConductor = async (dni) => {
    if (!window.confirm("¿Seguro que deseas eliminar este conductor?")) return;

    try {
      const response = await fetch(`http://localhost:8080/eliminarConductor/${dni}`, {
        method: "DELETE",
      });

      if (response.ok) {
        alert("✅ Conductor eliminado correctamente.");
        obtenerConductores();
      } else {
        alert("❌ Error al eliminar el conductor.");
      }
    } catch (error) {
      alert("Error de conexión al eliminar.");
    }
  };

  const comenzarEdicion = (conductor) => {
    setEditando(conductor.dni);
    setFormData({ ...conductor });
  };

  const cancelarEdicion = () => {
    setEditando(null);
    setFormData({
      nombre: "",
      telefono: "",
      direccion: "",
      salario: "",
      municipioResidencia: "",
    });
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
        `http://localhost:8080/actualizarConductor/${editando}`,
        {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(formData),
        }
      );

      if (response.ok) {
        alert("✅ Conductor actualizado correctamente.");
        setEditando(null);
        obtenerConductores();
      } else {
        alert("❌ Error al actualizar el conductor.");
      }
    } catch (error) {
      alert("Error de conexión al actualizar.");
    }
  };

  return (
    <div className="listar-conductores-container">
      <h2>Lista de Conductores</h2>

      {loading && <p>Cargando conductores...</p>}
      {error && <p className="error">{error}</p>}
      {!loading && !error && conductores.length === 0 && (
        <p>No hay conductores registrados.</p>
      )}

      <div className="conductores-list">
        {conductores.map((c) => (
          <div key={c.dni} className="conductor-card">
            <p><strong>DNI:</strong> {c.dni}</p>
            <p><strong>Nombre:</strong> {c.nombre}</p>
            <p><strong>Teléfono:</strong> {c.telefono}</p>
            <p><strong>Dirección:</strong> {c.direccion}</p>
            <p><strong>Salario:</strong> {c.salario.toFixed(2)} €</p>
            <p><strong>Municipio:</strong> {c.municipioResidencia}</p>

            <div className="acciones">
              <button onClick={() => comenzarEdicion(c)}>Modificar</button>
              <button className="eliminar" onClick={() => eliminarConductor(c.dni)}>
                Eliminar
              </button>
            </div>

            {editando === c.dni && (
              <div className="editar-form">
                <h4>Editar Conductor</h4>
                <input
                  type="text"
                  name="nombre"
                  value={formData.nombre}
                  onChange={handleChange}
                  placeholder="Nombre"
                />
                <input
                  type="text"
                  name="telefono"
                  value={formData.telefono}
                  onChange={handleChange}
                  placeholder="Teléfono"
                />
                <input
                  type="text"
                  name="direccion"
                  value={formData.direccion}
                  onChange={handleChange}
                  placeholder="Dirección"
                />
                <input
                  type="number"
                  name="salario"
                  value={formData.salario}
                  onChange={handleChange}
                  placeholder="Salario"
                />
                <input
                  type="text"
                  name="municipioResidencia"
                  value={formData.municipioResidencia}
                  onChange={handleChange}
                  placeholder="Municipio"
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
