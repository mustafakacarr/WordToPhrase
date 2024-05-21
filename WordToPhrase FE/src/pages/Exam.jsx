import React, { useState } from "react";
import BaseLayout from "../layout/BaseLayout";

import { postWithoutAuth } from "../api/apiCall";
import axios from "axios";
import ExamContent from "../components/ExamContent";

const Exam = () => {
  const [exam, setExam] = useState({}); 
  const [examStarted, setExamStarted] = useState(false);
  const user = JSON.parse(localStorage.getItem("user"))
  const handleCreateNewExam = async () => {
    try {
      const response = await postWithoutAuth("/api/exams/users/" + user.id);
      if (response.data.questions.length > 0)
        console.log("Exam created successfully!");
        setExamStarted(true);
        setExam(response.data);
    } catch (error) {
      console.error("Error creating new exam:", error);
    }
  };

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
