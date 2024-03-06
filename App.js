import React, { useState } from 'react';
import './App.css';

function App() {
  const [inputText, setInputText] = useState('');
  const [regexPattern, setRegexPattern] = useState('');
  const [caseSensitive, setCaseSensitive] = useState(false);
  const [matches, setMatches] = useState([]);
  const [error, setError] = useState('');

  const handleInputChange = (e) => {
    setInputText(e.target.value);
    handleRegex();
  };

  const handleRegexChange = (e) => {
    setRegexPattern(e.target.value);
    handleRegex();
  };

  const handleCaseSensitiveChange = () => {
    setCaseSensitive(!caseSensitive);
    handleRegex();
  };

  const handleRegex = () => {
    try {
      const flags = caseSensitive ? 'g' : 'gi';
      const regexObj = new RegExp(regexPattern, flags);
      const matches = inputText.match(regexObj);
      if (matches) {
        setMatches(matches);
      } else {
        setMatches([]);
      }
      setError('');
    } catch (error) {
      setError(error.message);
    }
  };

  return (
    <div className="App">
      <h1>Real-Time Regular Expression Checker</h1>
      <div className="input-container">
        <label htmlFor="input">Text Input:</label>
        <input
          type="text"
          id="input"
          value={inputText}
          onChange={handleInputChange}
          placeholder="Type some text..."
        />
      </div>
      <div className="input-container">
        <label htmlFor="regex">Regular Expression:</label>
        <input
          type="text"
          id="regex"
          value={regexPattern}
          onChange={handleRegexChange}
          placeholder="Enter a regular expression..."
        />
      </div>
      <div className="input-container">
        <input
          type="checkbox"
          id="caseSensitive"
          checked={caseSensitive}
          onChange={handleCaseSensitiveChange}
        />
        <label htmlFor="caseSensitive">Case Sensitive</label>
      </div>
      <div className="result">
        {error && <div className="error">{error}</div>}
        {!error && matches.length > 0 && (
          <ul>
            {matches.map((match, index) => (
              <li key={index}>{match}</li>
            ))}
          </ul>
        )}
        {!error && matches.length === 0 && <div>No matches found</div>}
      </div>
    </div>
  );
}

export default App;
```
