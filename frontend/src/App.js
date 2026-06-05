import { useState } from "react";
import axios from "axios";
import "./App.css";

function App() {
  const [resume, setResume] = useState(null);
  const [jd, setJd] = useState("");
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);

  const analyzeResume = async () => {
    if (!resume || !jd.trim()) {
      alert("Please upload a resume and enter a Job Description.");
      return;
    }

    const formData = new FormData();
    formData.append("file", resume);
    formData.append("jobDescription", jd);

    try {
      setLoading(true);

      const response = await axios.post(
        "http://localhost:8080/api/resume/analyze",
        formData
      );

      setResult(response.data);
    } catch (error) {
      console.error(error);
      alert("Analysis failed");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container">
      <div className="hero">
        <h1>AI Resume Analyzer</h1>
        <p>
          Upload your resume and compare it against a job description to
          discover strengths, missing skills, and improvement suggestions.
        </p>
      </div>

      <div className="input-card">
        <label className="label">Upload Resume (PDF)</label>

        <input 
          type="file"
          accept=".pdf"
          onChange={(e) => setResume(e.target.files[0])}
        />

        {resume && (
          <p className="file-name">
            Selected File: <strong>{resume.name}</strong>
          </p>
        )}

        <label className="label">Job Description</label>

        <textarea
          rows="10"
          placeholder="Paste the job description here..."
          value={jd}
          onChange={(e) => setJd(e.target.value)}
        />

        <button
          className="analyze-btn"
          onClick={analyzeResume}
          disabled={loading}
        >
          {loading ? "Analyzing..." : "Analyze Resume"}
        </button>
      </div>

      {result && (
        <div className="results">
          <div className="score-card">
            <h3>Match Score</h3>
            <div className="score-value">{result.matchScore}%</div>
          </div>

          <div className="card">
            <h2>Skills Found</h2>

            <div className="tags">
              {result.skillsFound?.length > 0 ? (
                result.skillsFound.map((skill, index) => (
                  <span key={index} className="skill-tag">
                    {skill}
                  </span>
                ))
              ) : (
                <p>No skills detected.</p>
              )}
            </div>
          </div>

          <div className="card">
            <h2>Missing Skills</h2>

            <div className="tags">
              {result.missingSkills?.length > 0 ? (
                result.missingSkills.map((skill, index) => (
                  <span key={index} className="missing-tag">
                    {skill}
                  </span>
                ))
              ) : (
                <p>✅ No major skills missing.</p>
              )}
            </div>
          </div>

          <div className="card">
            <h2>Suggestions</h2>

            <ul className="suggestions-list">
              {result.suggestions?.map((suggestion, index) => (
                <li key={index}>{suggestion}</li>
              ))}
            </ul>
          </div>
        </div>
      )}
    </div>
  );
}

export default App;