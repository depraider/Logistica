import React, { useState } from "react";
import "../insertarForm.css";

export default function InsertarVehiculoPage() {
  const [vehiculo, setVehiculo] = useState({
    matricula: "",
    modelo: "",
    potencia: "",
    tipo: "",
  });

  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState(null);

  const handleChange = (e) => {
    setVehiculo({
      ...vehiculo,
      [e.target.name]: e.target.value,
    });
    setMessage(null);
  };

  const validate = (obj) => {
    if (!obj.matricula?.trim()) return "La matrícula es obligatoria";
    if (!obj.modelo?.trim()) return "El modelo es obligatorio";
    if (obj.potencia && isNaN(Number(obj.potencia)))
      return "La potencia debe ser un número";
    return null;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage(null);

    const err = validate(vehiculo);
    if (err) {
      setMessage({ type: "error", text: err });
      return;
    }

    const vehiculoEnviar = {
      matricula: vehiculo.matricula.trim(),
      modelo: vehiculo.modelo.trim(),
      potencia: vehiculo.potencia ? Number(vehiculo.potencia) : null,
      tipo: vehiculo.tipo.trim() || null,
    };

    setLoading(true);
    try {
      const response = await fetch("http://localhost:8080/insertarVehiculo", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(vehiculoEnviar),
      });

      if (response.ok) {
        setMessage({ type: "ok", text: "Vehículo insertado correctamente!" });
        setVehiculo({
          matricula: "",
          modelo: "",
          potencia: "",
          tipo: "",
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
          text: `Error al insertar vehículo (status ${response.status}) ${serverMsg}`,
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
      <h2>Insertar Vehículo</h2>

      {message && (
        <div className={`message ${message.type}`}>{message.text}</div>
      )}

      <form onSubmit={handleSubmit} className="form">
        <input
          type="text"
          name="matricula"
          placeholder="Matrícula"
          value={vehiculo.matricula}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="modelo"
          placeholder="Modelo"
          value={vehiculo.modelo}
          onChange={handleChange}
          required
        />
        <input
          type="number"
          name="potencia"
          placeholder="Potencia (opcional)"
          value={vehiculo.potencia}
          onChange={handleChange}
        />
        <input
          type="text"
          name="tipo"
          placeholder="Tipo (opcional)"
          value={vehiculo.tipo}
          onChange={handleChange}
        />

        <button type="submit" disabled={loading}>
          {loading ? "Enviando..." : "Insertar"}
        </button>
      </form>
    </div>
  );
}
