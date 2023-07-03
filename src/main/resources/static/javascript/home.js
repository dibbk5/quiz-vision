const headers = {
  "Content-Type": "application/json",
};
const baseUrl = "http://localhost:8080/api/v1/quizzes/";

// Getting User Id
const userId = localStorage.getItem("user");
localStorage.setItem("user", userId);

const createQuizBtn = document.getElementById("create-new-quiz-btn");
const quizName = document.getElementById("new-quiz-name");
const quizContainer = document.getElementById("quiz-container");

// Adds a quiz using the quiz name entered
async function addQuiz(obj) {
  const response = await fetch(`${baseUrl}quiz`, {
    method: "POST",
    body: JSON.stringify(obj),
    headers: headers,
  }).catch((err) => console.error(err.message));

  if (response.status === 200) {
    return getQuizzes();
  }
}

// Gets quizzes and sends them to the createQuizCard function to create the html
async function getQuizzes() {
  await fetch(`${baseUrl}quiz`, {
    method: "GET",
    headers: headers,
  })
    .then((response) => response.json())
    .then((data) => createQuizCard(data))
    .catch((err) => console.error(err));
}

// Creates the Quiz Cards for all of the Quizzes in the Database
function createQuizCard(array) {
  quizContainer.innerHTML = "";
  array.forEach((obj) => {
    let quizCard = document.createElement("div");
    quizCard.classList.add("m-2");
    quizCard.innerHTML = `
    <div class="card d-flex">
                <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                    <p class="card-text">${obj.quizName}</p>
                    <p id="quiz-id" class="card-text">ID: ${obj.id}</p>
                </div>
                <a href="./attempt.html?quiz-id=${obj.id}&quiz-name=${obj.quizName}" class="btn btn-primary">Take Quiz</a>
                <a href="./quiz.html?quiz-id=${obj.id}&quiz-name=${obj.quizName}" class="btn btn-primary">Edit Quiz</a>
            </div>
    `;
    quizContainer.append(quizCard);
  });
}

// Event listener for when someone is creating a new quiz
createQuizBtn.addEventListener("click", (e) => {
  let bodyObj = {
    quizName: quizName.value,
  };

  addQuiz(bodyObj);
});

getQuizzes();
