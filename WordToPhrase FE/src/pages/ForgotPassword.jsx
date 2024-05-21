import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";

const ForgotPassword = () => {
  const [email, setEmail] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    // Şifre sıfırlama işlemi burada yapılacak
    console.log("Email:", email);
  };

  return (
    <div className="d-flex justify-content-center align-items-center vh-100">
      <div className="card p-4 shadow-sm" style={{ width: "20rem" }}>
        <h3 className="text-center mb-4">Forgot Password</h3>
        <form onSubmit={handleSubmit}>
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
          <button type="submit" className="btn btn-primary w-100">
            Reset Password
          </button>
          <div className="text-center mt-3">
            <a href="/login" className="text-decoration-none">
              Back to Login
            </a>
          </div>
        </form>
      </div>
    </div>
  );
};

export default ForgotPassword;
