import React, { useEffect, useState } from "react";
import BaseLayout from "../layout/BaseLayout";
import ResultCard from "../components/ResultCard";
import { getWithoutAuth } from "../api/apiCall";

const Results = () => {
  const [resultList, setResultList] = useState([]);
const tempUserId = 1;
  const fetchData = async () => {
    try {
      const response = await getWithoutAuth(`/api/results/users/${tempUserId}`);
      setResultList(response.data);
    } catch (error) {
      console.error("Error fetching results:", error);
    }
  };
  useEffect(() => {
    fetchData();
  }, []);
  return (
    <BaseLayout>
      {" "}
      <div className="fs-3 text-center">All Results</div>
      <br />
      {resultList.map((result, index) => (
        <ResultCard key={index} result={result} />
      ))}
    </BaseLayout>
  );
};

export default Results;
