import React, { useState } from "react";
import "../insertarForm.css";

export default function InsertarPaquetePage() {
  const [paquete, setPaquete] = useState({
    codigoPaquete: "",
    descripcion: "",
    destinatario: "",
    direccionEntrega: "",
    municipio: "",
  });

  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState(null);

  const handleChange = (e) => {
    setPaquete({
      ...paquete,
      [e.target.name]: e.target.value,
    });
    setMessage(null);
  };

  const validate = (obj) => {
    if (!obj.codigoPaquete?.trim())
      return "El código del paquete es obligatorio";
    if (!obj.descripcion?.trim()) return "La descripción es obligatoria";
    if (!obj.destinatario?.trim()) return "El destinatario es obligatorio";
    if (!obj.direccionEntrega?.trim())
      return "La dirección de entrega es obligatoria";
    if (!obj.municipio?.trim()) return "El municipio es obligatorio";
    return null;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage(null);

    const err = validate(paquete);
    if (err) {
      setMessage({ type: "error", text: err });
      return;
    }

    const paqueteEnviar = {
      codigoPaquete: paquete.codigoPaquete.trim(),
      descripcion: paquete.descripcion.trim(),
      destinatario: paquete.destinatario.trim(),
      direccionEntrega: paquete.direccionEntrega.trim(),
      municipio: paquete.municipio.trim(),
    };

    setLoading(true);
    try {
      const response = await fetch("http://localhost:8080/insertarPaquete", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(paqueteEnviar),
      });

      if (response.ok) {
        setMessage({ type: "ok", text: "Paquete insertado correctamente!" });
        setPaquete({
          codigoPaquete: "",
          descripcion: "",
          destinatario: "",
          direccionEntrega: "",
          municipio: "",
        });
      } else {
        let serverMsg = "";
        try {
          serverMsg = await response.text();
        } catch (_) {
          serverMsg = "";
        }
        setMessage({
          type: "error",
          text: `Error al insertar paquete (status ${response.status}) ${serverMsg}`,
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
      <h2>Insertar Paquete</h2>

      {message && (
        <div className={`message ${message.type}`}>{message.text}</div>
      )}

      <form onSubmit={handleSubmit} className="form">
        <input
          type="text"
          name="codigoPaquete"
          placeholder="Código del paquete"
          value={paquete.codigoPaquete}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="descripcion"
          placeholder="Descripción"
          value={paquete.descripcion}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="destinatario"
          placeholder="Destinatario"
          value={paquete.destinatario}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="direccionEntrega"
          placeholder="Dirección de entrega"
          value={paquete.direccionEntrega}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="municipio"
          placeholder="Municipio (código postal o nombre)"
          value={paquete.municipio}
          onChange={handleChange}
          required
        />

        <button type="submit" disabled={loading}>
          {loading ? "Enviando..." : "Insertar"}
        </button>
      </form>
    </div>
  );
}
