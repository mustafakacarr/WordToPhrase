import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { postWithoutAuth } from "../api/apiCall";
import { useNavigate } from "react-router-dom";

const Register = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [username, setUsername] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const history = useNavigate();
  const handleSubmit = async () => {
    try {
      const response = await postWithoutAuth("/api/auth/signup", {
        email,
        password,
        username,
      });
      console.log("Register response:", response.data);
      if (response.data != null)
        localStorage.setItem(
          "user",
          JSON.stringify(response.data ? response.data : null)
        );
      setTimeout(() => {
        history("/home");
      }, 1000);
    } catch (error) {
      console.error("Error registering:", error);
    }
  };

  return (
    <div className="d-flex justify-content-center align-items-center vh-100">
      <div className="card p-4 shadow-sm" style={{ width: "20rem" }}>
        <h3 className="text-center mb-4">Register</h3>

        <div className="mb-3">
          <label htmlFor="email" className="form-label">
            Email address
          </label>
          <input
            type="email"
            className="form-control"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="username" className="form-label">
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
        <div className="mb-3">
          <label htmlFor="confirmPassword" className="form-label">
            Confirm Password
          </label>
          <input
            type="password"
            className="form-control"
            id="confirmPassword"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            required
          />
        </div>
        <div onClick={handleSubmit} className="btn btn-primary w-100">
          Register
        </div>
        <div className="text-center mt-3">
          <a href="/login" className="text-decoration-none">
            Already have an account? Login
          </a>
        </div>
      </div>
    </div>
  );
};

export default Register;
