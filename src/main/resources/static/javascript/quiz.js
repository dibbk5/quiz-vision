const headers = {
  "Content-Type": "application/json",
};
const baseUrl = "http://localhost:8080/api/v1/questions/";

// Find Elements
const addQuestionBtn = document.getElementById("add-question-btn");
const quizName = document.getElementById("quiz-name");
const questionDescription = document.getElementById("question-description");
const questionContainer = document.getElementById("question-container");
const answerOne = document.getElementById("answer-one");
const answerTwo = document.getElementById("answer-two");
const answerThree = document.getElementById("answer-three");
const answerFour = document.getElementById("answer-four");
const correctOne = document.getElementById("correct-one");
const correctTwo = document.getElementById("correct-two");
const correctThree = document.getElementById("correct-three");
const correctFour = document.getElementById("correct-four");

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
  })
    .then((response) => response.json())
    .then((data) => startAnswerAdd(data))
    .then(getQuestions)
    .catch((err) => console.error(err.message));
}

async function addAnswers(obj, questionId) {
  const response = await fetch(
    `http://localhost:8080/api/v1/answers/answer/${questionId}`,
    {
      method: "POST",
      body: JSON.stringify(obj),
      headers: headers,
    }
  ).catch((err) => console.error(err.message));
  console.log(response);

  if (response.status === 200) {
    return getQuestions();
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

//Handle deleting question
async function deleteQuestion(questionId) {
  await fetch(baseUrl + questionId, {
    method: "DELETE",
    headers: headers,
  }).catch((err) => console.error(err));
  return getQuestions();
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
                <button class="btn btn-danger" onclick="deleteQuestion(${
                  obj.id
                })">Delete</button>
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
    if (obj.correctAnswer === true) {
      answerSection.innerHTML = `
            <li class="list-group-item" style="background-color: red;">${obj.answer}</li>`;
    } else {
      answerSection.innerHTML = `
            <li class="list-group-item">${obj.answer}</li>`;
    }
    answerContainer.append(answerSection);
  });
}

//Runs a for each of adding answers to the database
function startAnswerAdd(questionId) {
  let answerObj = [
    {
      answer: answerOne.value,
      correctAnswer: correctOne.checked,
    },
    {
      answer: answerTwo.value,
      correctAnswer: correctTwo.checked,
    },
    {
      answer: answerThree.value,
      correctAnswer: correctThree.checked,
    },
    {
      answer: answerFour.value,
      correctAnswer: correctFour.checked,
    },
  ];
  answerObj.forEach((obj) => {
    addAnswers(obj, questionId);
  });
}

//Listener for when someone saves the add question modal
//Then add questions and answers to database
addQuestionBtn.addEventListener("click", (e) => {
  let bodyObj = {
    description: questionDescription.value,
  };
  addQuestion(bodyObj);
});

getQuestions();
