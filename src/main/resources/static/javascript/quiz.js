const headers = {
  "Content-Type": "application/json",
};
const baseUrl = "http://localhost:8080/api/v1/questions/";

// Find Elements
const addQuestionBtn = document.getElementById("add-question-btn");
const quizName = document.getElementById("quiz-name");

// get information from URL
const urlParams = new URLSearchParams(window.location.search);
const idValue = urlParams.get("quiz-id");
const nameValue = urlParams.get("quiz-name");

// Add Question Button
async function addQuestion(obj) {
  const response = await fetch(`${baseUrl}question/${idValue}`, {
    method: "POST",
    body: JSON.stringify(obj),
    headers: headers,
  }).catch((err) => console.error(err.message));
  const responseArr = await response.json();

  if (response.status === 200) {
    window.location.replace(responseArr[0]);
  }
}

//Get Questions for quiz
async function getQuestions() {
  await fetch(`${baseUrl}question/${idValue}`, {
    method: "GET",
    headers: headers,
  })
    .then((response) => response.json())
    .then((data) => createQuestionCard(data))
    .catch((err) => console.error(err));
}

//Create cards for each question of the quiz
function createQuestionCard() {}

//Listener for when someone saves the add question modal
addQuestionBtn.addEventListener("click", (e) => {
  let bodyObj = {
    quizName: quizName.value,
  };

  addQuiz(bodyObj);
});
