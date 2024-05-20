import React from "react";

const Answer = ({ data,handleClick,isActive }) => {


  return (
    <div
      className={`card h-100 col-12 mt-3 answerCard ${
        isActive ? "active" : ""
      }`}
      style={{ cursor: "pointer" }}
      onClick={handleClick}
    >
      <div className="card-body d-flex justify-content-between align-items-center">
        <div>
          <h5 className="card-title">{data}</h5>
        </div>
      </div>
    </div>
  );
};

export default Answer;
