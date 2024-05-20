import React, { useEffect, useState } from "react";
import BaseLayout from "../layout/BaseLayout";
import { useParams } from "react-router-dom";
import { getWithoutAuth } from "../api/apiCall";

const ResultSingle = () => {
  let { resultId } = useParams();
  const [result, setResult] = useState({});

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
      Exam Results For {result.resultDatetime}
      <br />
      Exam For {result.examDatetime} <br />
      Correct Count {result.correctCount} <br />
      Wrong Answer Count {result.wrongCount}
          <br /> Total Question {result.wrongCount + result.correctCount}
          <br />
          Success Ratio {result.correctCount / (result.wrongCount + result.correctCount) * 100}%
    </BaseLayout>
  );
};

export default ResultSingle;
