const headers = {
  "Content-Type": "application/json",
};

const baseUrl = "http://localhost:8080/api/v1/scores/";

// Getting User Id
const userId = localStorage.getItem("user");
localStorage.setItem("user", userId);

// Find Elements
const scoreContainer = document.getElementById("score-container");

//Gets Scores for User using userID
async function getScores() {
  await fetch(`${baseUrl}score/${userId}`, {
    method: "GET",
    headers: headers,
  })
    .then((response) => response.json())
    .then((data) => createScoreCard(data))
    .catch((err) => console.log(err));
}

function createScoreCard(array) {
  scoreContainer.innerHTML = "";
  array.forEach((obj) => {
    let scoreCard = document.createElement("div");
    let percentScore = (obj.score / obj.denominator) * 100;
    scoreCard.innerHTML = `
    <div class="card d-flex">
    <div class="card-header">Quiz Name: ${obj.quizName}</div>
        <div class="card-body d-flex flex-column  justify-content-between">
            <p class="card-text">Score: ${obj.score}/${obj.denominator}</p>
            <p class="card-text">Percent: ${percentScore}%</p>
        </div>
    </div>
    </div>
        `;
    scoreContainer.append(scoreCard);
  });
}

getScores();
