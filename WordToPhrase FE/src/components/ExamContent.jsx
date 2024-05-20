import React, { useState } from "react";
import Question from "./Question";
import { postWithoutAuth } from "../api/apiCall";

const ExamContent = (props) => {
  const [userAnswers, setUserAnswers] = useState([]);
  const { questions, examId } = props.exam;
const history=useNavigate();
  const handleFinishExam = async () => {
    try {
      const response = await postWithoutAuth(
        `/api/exams/${examId}/finish`,
        formatAnswers(userAnswers)
      );
      console.log("Exam finished!");
        console.log("User answers:", userAnswers);
        history(`/results/${response.data.resultId}`)
    } catch (error) {
      console.error("Error finishing exam:", error);
    }
  };
  const formatAnswers = (answers) => {
    const formatted = { answers: {} };
    answers.forEach((answer) => {
      formatted.answers[answer.wordId] = answer.answer;
    });
    return formatted;
  };
  const handleAnswer = (wordId, answer) => {
    const newAnswers = [...userAnswers];
    const existingAnswerIndex = newAnswers.findIndex(
      (item) => item.wordId === wordId
    );
    if (existingAnswerIndex !== -1) {
      newAnswers[existingAnswerIndex] = { wordId, answer };
    } else {
      newAnswers.push({ wordId, answer });
    }
    setUserAnswers(newAnswers);
  };

  return (
    <div>
      {questions.map((question, index) => (
        <Question
          key={index}
          order={index}
          data={question}
          onAnswer={handleAnswer}
        />
      ))}{" "}
      <div
        className="btn btn-primary row justify-content-center m-auto"
        onClick={handleFinishExam}
      >
        Finish Exam
      </div>
    </div>
  );
};

export default ExamContent;
