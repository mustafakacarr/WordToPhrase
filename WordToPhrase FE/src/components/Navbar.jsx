import { faUser } from "@fortawesome/free-regular-svg-icons";
import { faDoorOpen } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React, { useState } from "react";

const Navbar = () => {
  const user = JSON.parse(localStorage.getItem("user"));
  const [activeTab, setActiveTab] = useState("Home");

  const handleTabClick = (tab) => {
    setActiveTab(tab);
  };

  const handleLogout = () => {
    localStorage.removeItem("user");
    window.location.href = "/login";
  };

  return (
    <div className="mt-3 mx-1 mx-md-3 mx-lg-5">
      <div className="border rounded mx-auto py-1 px-3 position-relative">
        <ul className="nav d-flex justify-content-center row gx-2 mt-2">
          <li className="col-12 col-md-6 col-lg-2 py-1">
            <a
              className={`btn ${
                activeTab === "Home" ? "btn-teal" : "btn-dark"
              } rounded px-3 w-100 h-100`}
              onClick={() => handleTabClick("Home")}
              href="/home"
            >
              Home
            </a>
          </li>
          <li className="col-12 col-md-6 col-lg-2 py-1">
            <a
              className={`btn ${
                activeTab === "Exam" ? "btn-teal" : "btn-dark"
              } rounded px-3 w-100 h-100`}
              onClick={() => handleTabClick("Exam")}
              href="/exam"
            >
              Exam
            </a>
          </li>
          <li className="col-12 col-md-6 col-lg-2 py-1">
            <a
              className={`btn ${
                activeTab === "Words" ? "btn-teal" : "btn-dark"
              } rounded px-3 w-100 h-100`}
              onClick={() => handleTabClick("Words")}
              href="/words"
            >
              Words
            </a>
          </li>
          <li className="col-12 col-md-6 col-lg-2 py-1">
            <a
              className={`btn ${
                activeTab === "Results" ? "btn-teal" : "btn-dark"
              } rounded px-3 w-100 h-100`}
              onClick={() => handleTabClick("Results")}
              href="/results"
            >
              Results
            </a>
          </li>
          <li className="col-12 col-md-6 col-lg-2 py-1">
            <a
              className={`btn ${
                activeTab === "Settings" ? "btn-teal" : "btn-dark"
              } rounded px-3 w-100 h-100`}
              onClick={() => handleTabClick("Settings")}
              href="/settings"
            >
              Settings
            </a>
          </li>
          <li className="col-12 col-md-6 col-lg-2 py-1">
            <div className="dropdown">
              <button
                className="btn btn-secondary dropdown-toggle"
                type="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                <FontAwesomeIcon icon={faUser} />{user.username}
              </button>
              <ul className="dropdown-menu">
                <li
                  className="dropdown-item"
                  style={{ userSelect: "none" }}
                  onClick={handleLogout}
                >
                  <FontAwesomeIcon icon={faDoorOpen} size="lg" /> Logout
                </li>
              </ul>
            </div>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default Navbar;
