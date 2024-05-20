import React, { useState } from "react";
import Navbar from "../components/Navbar";

const BaseLayout = ({ children }) => {
 
  return (
    <div>
      {" "}
      <Navbar></Navbar>
      <div className="container content mt-3">
        {children}
      </div>
    </div>
  );
};

export default BaseLayout;
