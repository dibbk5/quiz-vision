const headers = {
  "Content-Type": "application/json",
};
const baseUrl = "http://localhost:8080/api/v1/questions/";

// Getting User Id
const userId = localStorage.getItem("user");
console.log(userId);

//Get values from URL
const urlParams = new URLSearchParams(window.location.search);
const idValue = urlParams.get("quiz-id");
const nameValue = urlParams.get("quiz-name");

// Find Elements
const quizName = document.getElementById("quiz-name");
const questionContainer = document.getElementById("question-container");

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

// Gets answers for a specific questionId
async function getAnswers(questionId) {
  await fetch(`http://localhost:8080/api/v1/answers/answer/${questionId}`, {
    method: "GET",
    headers: headers,
  })
    .then((response) => response.json())
    .then((data) => createAnswerSection(data, questionId))
    .catch((err) => console.error(err));
}

//Create cards for each question of the quiz
function createQuestionCard(array) {
  questionContainer.innerHTML = "";
  array.forEach((obj, i) => {
    let questionCard = document.createElement("div");
    questionCard.classList.add("m-2");
    questionCard.innerHTML = `
          <div class="card d-flex" style="width: 75%; height: 10%; margin: 50px;">
              <div class="card-header">Question ${i + 1}</div>
                  <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                      <p class="card-text">${obj.description}</p>
                      <ul class="list-group list-group-flush">
                          <div id="${obj.id}"></div>
                      </ul>
                  </div>
              </div>
              `;
    getAnswers(obj.id);
    questionContainer.append(questionCard);
  });
}

//Creates the HTML for the answers of each question
function createAnswerSection(array, questionId) {
  let answerContainer = document.getElementById(`${questionId}`);
  answerContainer.innerHTML = "";
  array.forEach((obj) => {
    let answerSection = document.createElement("div");
    answerSection.innerHTML = `
        <div class="form-check">
            <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
            <label class="form-check-label" for="defaultCheck1">
            ${obj.answer}
            </label>
        </div>`;

    answerContainer.append(answerSection);
  });
}

function setQuizName() {
  quizName.innerHTML = `
        <h1>${nameValue}</h1>`;
}

setQuizName();
getQuestions();
