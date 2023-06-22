const headers = {
  "Content-Type": "application/json",
};
const baseUrl = "http://localhost:8080/api/v1/quizzes/";

const createQuizBtn = document.getElementById("create-new-quiz-btn");
const quizName = document.getElementById("new-quiz-name");

async function addQuiz(obj) {
  const response = await fetch(`${baseUrl}quiz`, {
    method: "POST",
    body: JSON.stringify(obj),
    headers: headers,
  }).catch((err) => console.error(err.message));
  const responseArr = await response.json();

  if (response.status === 200) {
    window.location.replace(responseArr[0]);
  }
}

createQuizBtn.addEventListener("click", (e) => {
  let bodyObj = {
    quizName: quizName.value,
  };

  addQuiz(bodyObj);
});
