import React, { useState } from "react";
import Answer from "./Answer";

const Question = (props) => {
  const { data, order, onAnswer } = props;
  const [activeAnswer, setActiveAnswer] = useState(null); 
  const wordItem = {
    word: data.word,
    img: data.image,
  };
  const answerList = data.options;
  const handleClick = (answerItem) => () => {
    console.log("🚀 ~ handleClick ~ answerItem:", answerItem)
    
    setActiveAnswer(answerItem)
    onAnswer(data.wordId, answerItem);
  };

  return (
    <div className="row justify-content-center m-auto">
      <div className="col-8">
        <h3 className="text-center">Question No: {order}</h3>
        <div className="mt-2 mb-3 ">
          <div className="card h-100">
            <div className="card-body d-flex justify-content-between align-items-center">
              <div>
                <h5 className="card-title">{wordItem.word}</h5>
              </div>
              <img
                src={wordItem.img}
                alt={wordItem.word}
                className="img-thumbnail"
                style={{ width: "80px", height: "80px", objectFit: "cover" }}
              />
            </div>
          </div>
        </div>{" "}
        <div className="mt-2 mb-5">
          {answerList.map((answerItem) => (
            <Answer
         
              data={answerItem}
              handleClick={handleClick(answerItem)}
              isActive={answerItem == activeAnswer}
            />
          ))}
        </div>
      </div>
    </div>
  );
};

export default Question;
