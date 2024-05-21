import React, { useEffect, useState } from "react";
import { getWithoutAuth, postWithoutAuth, postWithoutAuthAsMultipart } from "../api/apiCall";
import axios from "axios";
const NewWordForm = () => {
  const tempUserId = 1;
  const [word, setWord] = useState("");
  const [meaning, setMeaning] = useState("");
  const [sentences, setSentences] = useState([""]);
  const [selectedImage, setSelectedImage] = useState("");
  const [isSuccess, setIsSuccess] = useState(false);
  const handleSentenceChange = (index, value) => {
    const newSentences = [...sentences];
    newSentences[index] = value;
    setSentences(newSentences);
  };

  const addSentence = () => {
    setSentences([...sentences, ""]);
  };

  const addWord = async () => {
    const emptyIndex = sentences.findIndex((sentence) => sentence === "");
    if (emptyIndex !== -1) {
      sentences.splice(emptyIndex, 1);
    }
    if (!word || !meaning || sentences.length === 0) {
      throw new Error("Word, meaning and sentences are required!");
    }
    try {
      const data = new FormData();
      data.append("word", JSON.stringify({
        word,
        meaning,
        phrases: sentences,
        ownerId: tempUserId,
      }));
      data.append("image", selectedImage);

   const response = await axios.post("/api/words", data, {
     headers: {
       "Content-Type": "multipart/form-data",
     },
   });
      setSentences([""]);
      setWord("");
      setMeaning("");
      

      setIsSuccess(true);
      setTimeout(() => {
        setIsSuccess(false);
      }, 3000);
    } catch (error) {
      console.error("Error adding word:", error);
    }
  };
  const handleFileChange = (e) => {
    if (e.target.files && e.target.files[0])
      setSelectedImage(e.target.files[0]);
  };

  return (
    <div className="mt-4">
      <div className="text-center text-success">
        {isSuccess && "The word added successfully!"}
      </div>
      <div className="row mb-3">
        <label htmlFor="imageUpload" className="col-sm-2 col-form-label">
          Image
        </label>
        <div className="col-sm-10">
          <input
            type="file"
            className="form-control"
            id="imageUpload"
            onChange={(e) => handleFileChange(e)}
          />
        </div>
      </div>
      <div className="row mb-3">
        <label htmlFor="wordInput" className="col-sm-2 col-form-label">
          Word
        </label>
        <div className="col-sm-10">
          <input
            type="text"
            className="form-control"
            id="wordInput"
            placeholder="Enter word"
            onChange={(e) => setWord(e.target.value)}
          />
        </div>
      </div>
      <div className="row mb-3">
        <label htmlFor="meaningInput" className="col-sm-2 col-form-label">
          Meaning
        </label>
        <div className="col-sm-10">
          <input
            type="text"
            className="form-control"
            id="meaningInput"
            placeholder="Enter meaning"
            onChange={(e) => setMeaning(e.target.value)}
          />
        </div>
      </div>
      <div className="row mb-3">
        <label className="col-sm-2 col-form-label">Sentences</label>
        <div className="col-sm-10">
          {sentences.map((sentence, index) => (
            <div key={index} className="mb-2">
              <input
                type="text"
                className="form-control"
                value={sentence}
                onChange={(e) => handleSentenceChange(index, e.target.value)}
                placeholder={`Sentence ${index + 1}`}
              />
            </div>
          ))}
          <button
            type="button"
            className="btn btn-primary"
            onClick={addSentence}
          >
            Add Sentence
          </button>
        </div>
      </div>
      <div className="row">
        <div className="col-sm-10 offset-sm-2">
          <button type="submit" className="btn btn-success" onClick={addWord}>
            Save It
          </button>
        </div>
      </div>
    </div>
  );
};

export default NewWordForm;
