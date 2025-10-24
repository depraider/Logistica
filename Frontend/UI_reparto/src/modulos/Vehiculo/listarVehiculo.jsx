import React, { useEffect, useState } from "react";
import "../Conductor/listarConductores.css";

export default function ListarPaquetesPage() {
  const [paquetes, setPaquetes] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [editando, setEditando] = useState(null);
  const [formData, setFormData] = useState({
    descripcion: "",
    destinatario: "",
    direccionEntrega: "",
    municipio: "",
  });

  useEffect(() => {
    obtenerPaquetes();
  }, []);

  const obtenerPaquetes = async () => {
    setLoading(true);
    setError(null);
    try {
      const response = await fetch("http://localhost:8080/listarPaquetes", {
        headers: { "Content-Type": "application/json" },
      });
      if (!response.ok) throw new Error("Error al obtener paquetes");
      const data = await response.json();
      setPaquetes(data);
    } catch (err) {
      setError(
        "No se pudieron cargar los paquetes. ¿Está corriendo el backend?"
      );
    } finally {
      setLoading(false);
    }
  };

  const eliminarPaquete = async (codigo) => {
    if (!window.confirm("¿Seguro que deseas eliminar este paquete?")) return;
    try {
      const response = await fetch(
        `http://localhost:8080/eliminarPaquete/${codigo}`,
        {
          method: "DELETE",
        }
      );
      if (response.ok) {
        alert("Paquete eliminado correctamente.");
        obtenerPaquetes();
      } else {
        alert("Error al eliminar el paquete.");
      }
    } catch (error) {
      alert("Error de conexión al eliminar.");
    }
  };

  const comenzarEdicion = (paquete) => {
    setEditando(paquete.codigoPaquete);
    setFormData({ ...paquete });
  };

  const cancelarEdicion = () => {
    setEditando(null);
    setFormData({
      descripcion: "",
      destinatario: "",
      direccionEntrega: "",
      municipio: "",
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
        `http://localhost:8080/actualizarPaquete/${editando}`,
        {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(formData),
        }
      );

      if (response.ok) {
        alert("Paquete actualizado correctamente.");
        setEditando(null);
        obtenerPaquetes();
      } else {
        alert("Error al actualizar el paquete.");
      }
    } catch (error) {
      alert("Error de conexión al actualizar.");
    }
  };

  return (
    <div className="listar-conductores-container">
      <h2>Lista de Paquetes</h2>

      {loading && <p>Cargando paquetes...</p>}
      {error && <p className="error">{error}</p>}
      {!loading && !error && paquetes.length === 0 && (
        <p>No hay paquetes registrados.</p>
      )}

      <div className="conductores-list">
        {paquetes.map((p) => (
          <div key={p.codigoPaquete} className="conductor-card">
            <p>
              <strong>Código:</strong> {p.codigoPaquete}
            </p>
            <p>
              <strong>Descripción:</strong> {p.descripcion}
            </p>
            <p>
              <strong>Destinatario:</strong> {p.destinatario}
            </p>
            <p>
              <strong>Dirección:</strong> {p.direccionEntrega}
            </p>
            <p>
              <strong>Municipio:</strong> {p.municipio}
            </p>

            <div className="acciones">
              <button onClick={() => comenzarEdicion(p)}>Modificar</button>
              <button
                className="eliminar"
                onClick={() => eliminarPaquete(p.codigoPaquete)}
              >
                Eliminar
              </button>
            </div>

            {editando === p.codigoPaquete && (
              <div className="editar-form">
                <h4>Editar Paquete</h4>
                <input
                  type="text"
                  name="descripcion"
                  value={formData.descripcion}
                  onChange={handleChange}
                  placeholder="Descripción"
                />
                <input
                  type="text"
                  name="destinatario"
                  value={formData.destinatario}
                  onChange={handleChange}
                  placeholder="Destinatario"
                />
                <input
                  type="text"
                  name="direccionEntrega"
                  value={formData.direccionEntrega}
                  onChange={handleChange}
                  placeholder="Dirección"
                />
                <input
                  type="text"
                  name="municipio"
                  value={formData.municipio}
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
