import React, { useState, useContext } from "react";
import { GlobalStateContext } from '../../GlobalStateContext'; // Import global state context
import axios from "axios";
import CodeMirror from '@uiw/react-codemirror';
import { javascript } from '@codemirror/lang-javascript'; // Import the language extension
import './CodingEditorComponent.css'; // Import custom CSS for styling
import DashboardServices from "../../services/DashboardServices";
const CodingEditorComponent = () => {
  const [state] = useContext(GlobalStateContext); // Access global state
  const problem = state.problemData; // Get the selected problem data
  const [code, setCode] = useState('// Write your code here');
  const [output, setOutput] = useState('');

  // Fetch problems by category filter
  const handleRunCode = () => {

    const requestBody = {
      code: code,
      testCases:problem?.testCases,
      language: "java",
      versionIndex: 4
  };
      // Fetch all problems
      DashboardServices.executeCode(requestBody, problem?.id).then(response => {
        setOutput(response.data)
      }).catch(error => {
        console.log(error);
      });
  };

  return (
    <div className="coding-editor-container">
      {/* Left Section: Problem Details */}
      <div className="problem-details">
        {problem ? (
          <>
            <h1 className="problem-title">{problem.title}</h1>
            <h3 className="problem-difficulty">Difficulty: {problem.difficulty}</h3>
            <div className="problem-description">
              <p>{problem.description}</p>
            </div>
            <div className="input-format">
              <h4>Input Format:</h4>
              <p>{problem.inputFormat}</p>
            </div>
            <div className="output-format">
              <h4>Output Format:</h4>
              <p>{problem.outputFormat}</p>
            </div>
            <h4>Example Test Cases:</h4>
<pre className="test-cases">
  {JSON.stringify(problem.testCases, null, 2)}
</pre>

          </>
        ) : (
          <p>Loading problem...</p>
        )}
      </div>

      {/* Right Section: Code Editor */}
      <div className="editor-section">
        <h2>Code Editor</h2>
        <CodeMirror
          value={code}
          height="500px"
          extensions={[javascript({ jsx: true })]}  // Using the javascript extension
          onChange={(value) => setCode(value)}
        />
        <button className="run-code-button" onClick={handleRunCode}>
          Run Code
        </button>
        <div className="output-section">
          <h3 style={{color: "black", fontWeight:"bolder"}}>Output:</h3>
          <pre style={{color: "black", fontWeight:"bolder"}}>{output}</pre>
        </div>
      </div>
    </div>
  );
};

export default CodingEditorComponent;
