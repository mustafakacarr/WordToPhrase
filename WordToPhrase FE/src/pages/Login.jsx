import React, { useState } from "react";
import { postWithoutAuth } from "../api/apiCall";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [successMessage, setSuccessMessage] = useState("");
  const history = useNavigate();
  const handleLoginRequest = async () => {
    try {
      const response = await postWithoutAuth("/api/auth/login", {
        username,
        password,
      });
      if (response.data != null)
        localStorage.setItem(
          "user",
          JSON.stringify(response.data ? response.data : null)
        );
      setSuccessMessage("Login successful! Redirecting to home...");
      setErrorMessage("");
      setTimeout(() => {
        history("/home");
      }, 1000);

      console.log("Login response:", response.data);
    } catch (error) {
      setErrorMessage("Error logging in. Please try again later.");
      setSuccessMessage("");
      console.error("Error logging in:", error);
    }
  };

  return (
    <div className="d-flex justify-content-center align-items-center vh-100">
      <div className="card p-4 shadow-sm" style={{ width: "20rem" }}>
        <h3 className="text-center mb-4">Login</h3>
        {successMessage && (<div className="alert alert-success text-center" role="alert">{successMessage}</div>)}
      {errorMessage && (  <div className="alert alert-danger text-center" role="alert">{errorMessage}</div>)}
        <div className="mb-3">
          <label htmlFor="email" className="form-label">
            Username
          </label>
          <input
            type="text"
            className="form-control"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="password" className="form-label">
            Password
          </label>
          <input
            type="password"
            className="form-control"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button className="btn btn-primary w-100" onClick={handleLoginRequest}>
          Login
        </button>
        <div className="text-center mt-3">
          <a href="/forgot-password" className="text-decoration-none">
            Forgot password?
          </a>
        </div>
        <div className="text-center mt-3">
          <a href="/signup" className="text-decoration-none">
            Register
          </a>
        </div>
      </div>
    </div>
  );
};

export default Login;
