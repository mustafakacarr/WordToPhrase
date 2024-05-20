import React, { useState } from "react";
import BaseLayout from "../layout/BaseLayout";

import { postWithoutAuth } from "../api/apiCall";
import axios from "axios";
import ExamContent from "../components/ExamContent";

const Exam = () => {
  const [exam, setExam] = useState({}); // [1
  const [examStarted, setExamStarted] = useState(false);
  const tempUserId = 1;
  const handleCreateNewExam = async () => {
    try {
      const response = await postWithoutAuth("/api/exams/users/" + tempUserId);
      if (response.data.questions.length > 0)
        console.log("Exam created successfully!");
      setExamStarted(true);
      setExam(response.data);
    } catch (error) {
      console.error("Error creating new exam:", error);
    }
  };

  const questionList = [
    {
      image: "https://via.placeholder.com/150",
      word: "What is the capital of France?",
      options: ["New York", "London", "Paris", "Dublin"],
    },
    {
      image: "https://via.placeholder.com/150",
      word: "What is the capital of Ireland?",
      options: ["New York", "London", "Paris", "Dublin"],
    },
    {
      image: "https://via.placeholder.com/150",
      word: "What is the capital of England?",
      options: ["New York", "London", "Paris", "Dublin"],
    },
  ];
  return (
    <BaseLayout>
      {!examStarted && (
        <div className="text-center">
          <button className="btn btn-primary" onClick={handleCreateNewExam}>
            Start Exam
          </button>
        </div>
      )}
      {examStarted && <ExamContent exam={exam} />}
    </BaseLayout>
  );
};

export default Exam;
