import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Question from "./Question";
import { postWithoutAuth } from "../api/apiCall";

const ExamContent = (props) => {
  const [userAnswers, setUserAnswers] = useState([]);
  const { questions, examId } = props.exam;
  const navigate = useNavigate();
  const [message, setMessage] = useState("");

  const handleFinishExam = async () => {
    try {
      const response = await postWithoutAuth(
        `/api/exams/${examId}/finish`,
        formatAnswers(userAnswers)
      );
      console.log("Exam finished!");
      console.log("User answers:", userAnswers);
      setMessage("Exam finished! Redirecting to results...");
      setTimeout(() => {
        navigate(`/results/${response.data.resultId}`);
      }, 2000);
    } catch (error) {
      setMessage("Error finishing exam. Please try again later.");
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
      {!message ? (
        questions.length > 0 ? (
          <>
            {questions.map((question, index) => (
              <Question
                key={index}
                order={index}
                data={question}
                onAnswer={handleAnswer}
              />
            ))}
            <div
              className="btn btn-primary row justify-content-center m-auto"
              onClick={handleFinishExam}
            >
              Finish Exam
            </div>
          </>
        ) : (
          "There is no word to ask you. You could add new words to practice"
        )
      ) : (
        <div>{message}</div>
      )}
    </div>
  );
}
export default ExamContent;
