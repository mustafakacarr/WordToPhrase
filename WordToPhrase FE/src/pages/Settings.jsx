import React, { useState } from "react";
import BaseLayout from "../layout/BaseLayout";

const Settings = () => {
  const [frequency, setFrequency] = useState(10);

  
  return (
    <BaseLayout>
      <h1 className="fs-3">Settings</h1>
      <div className="col-12">
        <div className="card">
          <div className="card-body">
            <div className="row mb-3">
              <label  className="col-sm-3 col-form-label">
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
            <div className="btn btn-success">Save Preferences</div>
          </div>
        </div>
      </div>
    </BaseLayout>
  );
};

export default Settings;
