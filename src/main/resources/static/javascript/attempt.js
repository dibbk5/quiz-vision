const headers = {
  "Content-Type": "application/json",
};
const baseUrl = "http://localhost:8080/api/v1/questions/";

// Getting User Id
const userId = localStorage.getItem("user");

//Get values from URL
const urlParams = new URLSearchParams(window.location.search);
const idValue = urlParams.get("quiz-id");
const nameValue = urlParams.get("quiz-name");

// Find Elements
const quizName = document.getElementById("quiz-name");
const questionContainer = document.getElementById("question-container");
const scoreContainer = document.getElementById("score-container");
const doneContainer = document.getElementById("when-done");

//Set array for correct answers
let answersArray = [];

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
          <div class="card d-flex">
              <div class="card-header" id="question${
                obj.id
              }" name="question">Question ${i + 1}
              </div>
              <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
              <div class="card-title" id="question-mark${obj.id}"></div>
                      <p class="card-text">${obj.description}</p>
                          <div id="${obj.id}"></div> 
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
        <label class="form-check-label">
            <input class="form-check-input answers" type="radio" id="option${obj.id}" role="${questionId}">
            ${obj.answer}
          </label>
        </div>`;
    answerContainer.append(answerSection);
    if (obj.correctAnswer === true) {
      answersArray.push({
        id: obj.id,
        question: questionId,
      });
    }
  });
}

//Submits quiz and shows you the score and correct answers
function submitQuiz() {
  let userScore = 0;
  let userAnswers = [];
  const questionSelector = document.getElementsByName("question");
  let answerSelector = document.querySelectorAll(".answers");
  const numberQuestions = questionSelector.length;
  answersArray.sort((a, b) => {
    return a.question - b.question;
  });
  for (i = 0; i < answerSelector.length; i++) {
    if (answerSelector[i].checked) {
      let idString = answerSelector[i].id;
      let answerId = idString.match(/\d+/g);
      let obj = {
        id: parseInt(answerId),
        question: parseInt(answerSelector[i].role),
      };
      userAnswers.push(obj);
    }
  }
  userAnswers.sort((a, b) => {
    return a.question - b.question;
  });
  for (i = 0; i < answersArray.length; i++) {
    if (userAnswers[i].id === answersArray[i].id) {
      userScore++;
      let headerText = document.getElementById(
        `question${answersArray[i].question}`
      );
      let markText = document.getElementById(
        `question-mark${answersArray[i].question}`
      );

      headerText.classList.add("correct");
      markText.innerText = "CORRECT";
      markText.style.color = "green";
    } else {
      let headerText = document.getElementById(
        `question${answersArray[i].question}`
      );
      let markText = document.getElementById(
        `question-mark${answersArray[i].question}`
      );

      headerText.classList.add("cross");
      markText.innerText = "INCORRECT";
      markText.style.color = "red";
    }
  }

  const finalPercentage = (userScore / numberQuestions) * 100;

  scoreContainer.innerHTML = `
    <div class="container">SCORE: ${finalPercentage} %</div>
    <div class="container">You got ${userScore} out of ${numberQuestions} quesitons correct.</div>`;

  doneContainer.innerHTML = ``;
}

function setQuizName() {
  quizName.innerHTML = `
        <h2>Quiz Name: ${nameValue}</h2>`;
}

setQuizName();
getQuestions();
