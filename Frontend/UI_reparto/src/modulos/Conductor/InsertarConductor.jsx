import React, { useState } from "react";
import "../insertarForm.css";

export default function InsertarConductorPage() {
  const [conductor, setConductor] = useState({
    dni: "",
    nombre: "",
    telefono: "",
    direccion: "",
    salario: "",
    municipioResidencia: "",
  });

  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState(null); 

  const handleChange = (e) => {
    setConductor({
      ...conductor,
      [e.target.name]: e.target.value,
    });
    setMessage(null);
  };

  const validate = (obj) => {
    if (!obj.dni?.trim()) return "El DNI es obligatorio";
    if (!obj.nombre?.trim()) return "El nombre es obligatorio";
    if (!obj.telefono?.trim()) return "El teléfono es obligatorio";
    if (!obj.direccion?.trim()) return "La dirección es obligatoria";
    if (obj.salario === "" || obj.salario === null) return "El salario es obligatorio";
    if (isNaN(Number(obj.salario))) return "El salario debe ser un número";
    if (!obj.municipioResidencia?.trim()) return "El municipio es obligatorio";
    return null;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage(null);

    const err = validate(conductor);
    if (err) {
      setMessage({ type: "error", text: err });
      return;
    }

    const conductorEnviar = {
      dni: conductor.dni.trim(),
      nombre: conductor.nombre.trim(),
      telefono: conductor.telefono.trim(),
      direccion: conductor.direccion.trim(),
      salario: Number(conductor.salario),
      municipioResidencia: conductor.municipioResidencia.trim(),
    };

    setLoading(true);
    try {
      const response = await fetch("http://localhost:8080/insertarConductor", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(conductorEnviar),
      });

      if (response.ok) {
        setMessage({ type: "ok", text: "✅ Conductor insertado correctamente!" });
        setConductor({
          dni: "",
          nombre: "",
          telefono: "",
          direccion: "",
          salario: "",
          municipioResidencia: "",
        });
      } else {
        let serverMsg = "";
        try {
          serverMsg = await response.text();
        } catch (_) {}
        setMessage({
          type: "error",
          text: `Error al insertar conductor (status ${response.status}) ${serverMsg}`,
        });
      }
    } catch (error) {
      console.error("Fetch error:", error);
      setMessage({
        type: "error",
        text: "Error de conexión al servidor. ¿Está corriendo el backend y permitido CORS?",
      });
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="insertar-container">
      <h2>Insertar Conductor</h2>

      {message && (
        <div className={`message ${message.type}`}>
          {message.text}
        </div>
      )}

      <form onSubmit={handleSubmit} className="form">
        <input type="text" name="dni" placeholder="DNI" value={conductor.dni} onChange={handleChange} required />
        <input type="text" name="nombre" placeholder="Nombre" value={conductor.nombre} onChange={handleChange} required />
        <input type="text" name="telefono" placeholder="Teléfono" value={conductor.telefono} onChange={handleChange} required />
        <input type="text" name="direccion" placeholder="Dirección" value={conductor.direccion} onChange={handleChange} required />
        <input type="number" name="salario" placeholder="Salario" value={conductor.salario} onChange={handleChange} required step="0.01" />
        <input type="text" name="municipioResidencia" placeholder="Municipio" value={conductor.municipioResidencia} onChange={handleChange} required />

        <button type="submit" disabled={loading}>
          {loading ? "Enviando..." : "Insertar"}
        </button>
      </form>
    </div>
  );
}
