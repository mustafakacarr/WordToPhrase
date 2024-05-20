
import NewWordForm from "../components/NewWordForm";
import WordList from "../components/WordList";
import BaseLayout from "../layout/BaseLayout";
import React from "react";
const Words = () => {
 
  return (
    <BaseLayout>
      <h1 className="fs-3 text-center">Add New Word</h1>
      <NewWordForm />
      <h1 className="fs-3 text-center">Word List</h1>
      <WordList />
    </BaseLayout>
  );
};

export default Words;
