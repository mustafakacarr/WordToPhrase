import React, { useEffect, useState } from "react";
import BaseLayout from "../layout/BaseLayout";
import { useNavigate, useParams } from "react-router-dom";
import { getWithoutAuth } from "../api/apiCall";
import ResultCard from "../components/ResultCard";

const ResultSingle = () => {
  let { resultId } = useParams();
  const [result, setResult] = useState({});
const history = useNavigate();
  const fetchData = async () => {
    try {
      const response = await getWithoutAuth(`/api/results/${resultId}`);
      setResult(response.data);
    } catch (error) {
      console.error("Error fetching results:", error);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <BaseLayout>
      <ResultCard result={result} />
      <center>
        <a className="fs-4 text-success" style={{cursor:"pointer",textDecoration:"none"}} onClick={() => history("/results")}>
          {" "}
          Go to All Results
        </a>
      </center>
    </BaseLayout>
  );
};

export default ResultSingle;
