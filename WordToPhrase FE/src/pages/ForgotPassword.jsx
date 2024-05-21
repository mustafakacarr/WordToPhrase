import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { postWithoutAuth } from "../api/apiCall";
import axios from "axios";

const ForgotPassword = () => {
  const [email, setEmail] = useState("");
  const [mailSent, setMailSent] = useState(false);
  const handleSubmit = async () => {
    try {
      const params = { email };

      console.log("🚀 ~ handleSubmit ~ params:", params)
      const response = await axios.post(
        "/api/auth/forgot-password",
null,
        {
          params
        }
      );

      setMailSent(true);
      setTimeout(() => {
        setMailSent(false);
      }, 5000);
      console.log("Forgot password response:", response.data);
    } catch (error) {
      console.error("Error resetting password:", error);
    }
  };

  return (
    <div className="d-flex justify-content-center align-items-center vh-100">
      <div className="card p-4 shadow-sm" style={{ width: "20rem" }}>
        <h3 className="text-center mb-4">Forgot Password</h3>

        {!mailSent ? (
          <>
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
            <button className="btn btn-primary w-100" onClick={handleSubmit}>
              Reset Password
            </button>
          </>
        ) : (
          <div className="alert alert-success text-center" role="alert">
            Mail sent successfully, dont forget to check your inbox and junk!{" "}
          </div>
        )}
        <div className="text-center mt-3">
          <a href="/login" className="text-decoration-none">
            Back to Login
          </a>
        </div>
      </div>
    </div>
  );
};

export default ForgotPassword;
