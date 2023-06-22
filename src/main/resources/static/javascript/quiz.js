const urlParams = new URLSearchParams(window.location.search);
const idValue = urlParams.get("quiz-id");
const nameValue = urlParams.get("quiz-name");

console.log(idValue);
console.log(nameValue);
