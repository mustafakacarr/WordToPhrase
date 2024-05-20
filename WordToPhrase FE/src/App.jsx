import "bootstrap/dist/js/bootstrap.bundle.min.js";
import "./App.scss";
import Home from "./pages/Home";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Words from "./pages/Words";
import Register from "./pages/Register";
import Login from "./pages/Login";
import Exam from "./pages/Exam";
import Results from "./pages/Results";
import Settings from "./pages/Settings";
import React from "react";
import ResultSingle from "./pages/ResultSingle";
function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/words" element={<Words />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Register />} />
          <Route path="/exam" element={<Exam />} />
          <Route path="/results" element={<Results />} />
          <Route path="/results/:resultId" element={<ResultSingle />} />
          <Route path="/settings" element={<Settings />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
