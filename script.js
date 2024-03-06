const input = document.getElementById('input');
const regexInput = document.getElementById('regex');
const caseSensitiveCheckbox = document.getElementById('caseSensitive');
const resultDiv = document.getElementById('result');

function handleInput() {
  const text = input.value;
  const regex = regexInput.value;
  const caseSensitive = caseSensitiveCheckbox.checked;

  try {
    const flags = caseSensitive ? 'g' : 'gi';
    const regexObj = new RegExp(regex, flags);
    const matches = text.match(regexObj);

    if (matches) {
      resultDiv.innerHTML = `Matches found: <ul>${matches.map(match => `<li>${match}</li>`).join('')}</ul>`;
    } else {
      resultDiv.textContent = 'No matches found';
    }
    resultDiv.classList.remove('error');
  } catch (error) {
    resultDiv.textContent = `Invalid regular expression: ${error.message}`;
    resultDiv.classList.add('error');
  }
}

input.addEventListener('input', handleInput);
regexInput.addEventListener('input', handleInput);
caseSensitiveCheckbox.addEventListener('change', handleInput);

window.onload = () => {
  input.value = 'Sample email addresses: john@example.com, alice@company.co.uk';
  regexInput.value = '\\b\\w+@\\w+\\.\\w+\\b';
  handleInput();
};

body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  background-color: #f5f5f5;
}

.container {
  max-width: 600px;
  margin: 50px auto;
  padding: 20px;
  border-radius: 10px;
  background-color: #fff;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
}

h1 {
  text-align: center;
  color: #333;
}

.input-container {
  margin-bottom: 20px;
}

.input-container label {
  display: block;
  margin-bottom: 5px;
  color: #666;
}

.input-container input[type="text"] {
  width: 100%;
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 5px;
  transition: border-color 0.3s ease;
}

.input-container input[type="text"]:focus {
  border-color: #ff7f50;
}

.result {
  border: 1px solid #ccc;
  padding: 10px;
  min-height: 100px;
  background-color: #f9f9f9;
  border-radius: 5px;
}

.result ul {
  list-style-type: none;
  padding: 0;
}

.result li {
  padding: 10px;
  border-bottom: 1px solid #ddd;
  color: #333;
  transition: background-color 0.3s ease, transform 0.3s ease;
}

.result li:last-child {
  border-bottom: none;
}

.result li:hover {
  background-color: #ff7f50;
  color: #fff;
  cursor: pointer;
}

.error {
  color: red;
  font-weight: bold;
  animation: shake 0.5s ease-in-out;
}

@keyframes shake {
  0%, 100% {
    transform: translateX(0);
  }
  25%, 75% {
    transform: translateX(-10px);
  }
  50% {
    transform: translateX(10px);
  }
}

script.js
