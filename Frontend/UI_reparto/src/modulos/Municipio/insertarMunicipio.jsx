import React, { useState } from "react";
import "../insertarForm.css";

export default function InsertarMunicipioPage() {
  const [municipio, setMunicipio] = useState({
    codigoMunicipio: "",
    nombre: "",
    provincia: "",
  });

  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState(null);

  const handleChange = (e) => {
    setMunicipio({
      ...municipio,
      [e.target.name]: e.target.value,
    });
    setMessage(null);
  };

  const validate = (obj) => {
    if (!obj.codigoMunicipio?.trim())
      return "El código del municipio es obligatorio";
    if (!obj.nombre?.trim()) return "El nombre del municipio es obligatorio";
    if (!obj.provincia?.trim()) return "La provincia es obligatoria";
    return null;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage(null);

    const err = validate(municipio);
    if (err) {
      setMessage({ type: "error", text: err });
      return;
    }

    const municipioEnviar = {
      codigoMunicipio: municipio.codigoMunicipio.trim(),
      nombre: municipio.nombre.trim(),
      provincia: municipio.provincia.trim(),
    };

    setLoading(true);
    try {
      const response = await fetch("http://localhost:8080/insertarMunicipio", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(municipioEnviar),
      });

      if (response.ok) {
        setMessage({
          type: "ok",
          text: "Municipio insertado correctamente!",
        });
        setMunicipio({ codigoMunicipio: "", nombre: "", provincia: "" });
      } else {
        let serverMsg = "";
        try {
          serverMsg = await response.text();
        } catch (_) {
          serverMsg = "";
        }
        setMessage({
          type: "error",
          text: `Error al insertar municipio (status ${response.status}) ${serverMsg}`,
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
      <h2>Insertar Municipio</h2>

      {message && (
        <div className={`message ${message.type}`}>{message.text}</div>
      )}

      <form onSubmit={handleSubmit} className="form">
        <input
          type="text"
          name="codigoMunicipio"
          placeholder="Código del municipio"
          value={municipio.codigoMunicipio}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="nombre"
          placeholder="Nombre del municipio"
          value={municipio.nombre}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="provincia"
          placeholder="Provincia"
          value={municipio.provincia}
          onChange={handleChange}
          required
        />

        <button
          type="submit"
          disabled={loading}
          style={{
            padding: "10px 16px",
            cursor: loading ? "not-allowed" : "pointer",
          }}
        >
          {loading ? "Enviando..." : "Insertar"}
        </button>
      </form>
    </div>
  );
}
