import React, { useEffect, useState } from "react";
import { getWithoutAuth } from "../api/apiCall";

const WordList = () => {
  const tempUserId = 1;
  const [words, setWords] = useState([]);

  const fetchWords = async () => {
    try {
      const response = await getWithoutAuth("/api/words", {
        params: { userId: tempUserId },
      });
      setWords(response.data);
      console.log("🚀 ~ fetchWords ~ response:", response);
    } catch (error) {
      console.error("Error fetching words:", error);
    }
  };

  useEffect(() => {
    fetchWords();
  }, []);

  return (
    <div className="container mt-3">
      <div className="row row-cols-1 row-cols-md-3 g-4">
        {words.map((wordItem, index) => (
          <div className="col" key={index}>
            <div className="card h-100">
              <div className="card-body d-flex justify-content-between align-items-center">
                <div>
                  <h5 className="card-title">{wordItem.word}</h5>
                  <p className="card-text">{wordItem.meaning}</p>
                </div>
                {wordItem.image && (
                  <img
                    src={`data:${wordItem.image.type};base64,${wordItem.image.file}`}
                    alt={wordItem.word}
                    className="img-thumbnail"
                    style={{
                      width: "60px",
                      height: "60px",
                      objectFit: "cover",
                    }}
                  />
                )}
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default WordList;
