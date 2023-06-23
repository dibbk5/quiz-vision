const headers = {
  "Content-Type": "application/json",
};
const baseUrl = "http://localhost:8080/api/v1/questions/";

// Find Elements
const addQuestionBtn = document.getElementById("add-question-btn");
const quizName = document.getElementById("quiz-name");
const questionDescription = document.getElementById("question-description");
const questionContainer = document.getElementById("question-container");

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
  array.forEach((obj) => {
    let questionCard = document.createElement("div");
    questionCard.classList.add("m-2");
    questionCard.innerHTML = `
        <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                    <p class="card-text">${obj.description}</p>
                </div>
                <button class="btn btn-danger" onclick="deleteQuestion(${obj.id})">Delete</button>
            </div>
            `;
    questionContainer.append(questionCard);
  });
}

//Listener for when someone saves the add question modal
addQuestionBtn.addEventListener("click", (e) => {
  let bodyObj = {
    description: questionDescription.value,
  };
  addQuestion(bodyObj);
});

getQuestions();
