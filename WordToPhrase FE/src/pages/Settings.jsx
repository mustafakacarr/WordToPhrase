import React, { useEffect, useState } from "react";
import BaseLayout from "../layout/BaseLayout";
import { postWithoutAuth } from "../api/apiCall";
import axios from "axios";

const Settings = () => {
  const user = JSON.parse(localStorage.getItem("user"));

  const [frequency, setFrequency] = useState(user.newWordFrequency);

  const handleSave = async () => {
    try {
      const params = { newWordFrequency: frequency };
      const response = await axios.post(
        `/api/auth/${user.id}/newWordFrequency`,
       null, {params}
      );
        localStorage.setItem("user", JSON.stringify(response.data));
      window.location.reload();
    } catch (error) {
      console.error("Error saving settings:", error);
    }
  };
  return (
    <BaseLayout>
      <h1 className="fs-3">Settings</h1>
      <div className="col-12">
        <div className="card">
          <div className="card-body">
            <div className="row mb-3">
              <label className="col-sm-3 col-form-label">
                Frequency of new questions
              </label>
              <div className="col-sm-9">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Enter number of questions"
                  value={frequency}
                  onChange={(e) => setFrequency(e.target.value)}
                />
              </div>
            </div>
            <div className="btn btn-success" onClick={handleSave}>
              Save Preferences
            </div>
          </div>
        </div>
      </div>
    </BaseLayout>
  );
};

export default Settings;
