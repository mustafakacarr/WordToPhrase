import React, { useRef } from "react";
import "bootstrap/dist/css/bootstrap.min.css";

const ResultCard = ({ result }) => {
  const resultCardRef = useRef();

  const formatDate = (timestamp) => {
    const date = new Date(timestamp);
    const options = {
      year: "numeric",
      month: "long",
      day: "numeric",
    };
    return date.toLocaleDateString("en-EN", options);
  };

  const successRatio = (
    (result.correctCount / (result.wrongCount + result.correctCount)) *
    100
  ).toFixed(2);

  const handlePrint = () => {
    const printContents = resultCardRef.current.innerHTML;
    const originalContents = document.body.innerHTML;
    document.body.innerHTML = printContents;
    window.print();
    document.body.innerHTML = originalContents;
    window.location.reload(); // Sayfayı yenileyerek orijinal içeriği geri yükleyin
  };

  return (
    <div className="card my-3 shadow-sm" ref={resultCardRef}>
      <div className="card-body">
        <h5 className="card-title mb-4 text-center">
          {formatDate(result.resultDatetime)} Results
        </h5>
        <ul className="list-group list-group-flush">
          <li className="list-group-item d-flex justify-content-between align-items-center">
            <span>Exam Results For:</span>
            <span className="fw-bold">{formatDate(result.resultDatetime)}</span>
          </li>
          <li className="list-group-item d-flex justify-content-between align-items-center">
            <span>Exam For:</span>
            <span className="fw-bold">{formatDate(result.examDatetime)}</span>
          </li>
          <li className="list-group-item d-flex justify-content-between align-items-center">
            <span>Correct Count:</span>
            <span className="fw-bold">{result.correctCount}</span>
          </li>
          <li className="list-group-item d-flex justify-content-between align-items-center">
            <span>Wrong Answer Count:</span>
            <span className="fw-bold">{result.wrongCount}</span>
          </li>
          <li className="list-group-item d-flex justify-content-between align-items-center">
            <span>Total Questions:</span>
            <span className="fw-bold">
              {result.wrongCount + result.correctCount}
            </span>
          </li>
          <li className="list-group-item d-flex justify-content-between align-items-center">
            <span>Success Ratio:</span>
            <span className="fw-bold">{successRatio}%</span>
          </li>
        </ul>
        <div className="text-center mt-3">
          <button className="btn btn-primary" onClick={handlePrint}>
            Print Results
          </button>
        </div>
      </div>
    </div>
  );
};

export default ResultCard;
