// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

// global variables so games array only needs to load once
// temp solution, a queue would be great for this
var lastPicked = -1;
var secondLastPicked = -1;
var thirdLastPicked = -1;

var array;

// load games array into global variable
window.onload = async function(){
    const responseFromServer = await fetch('/games');
    array = await responseFromServer.json();
};

// retrieve a random game from the json array. 
// lastPicked and secondLastpicked, etc are variables used to keep track of games to avoid duplication when pressing the button
// (makes it more random!)
async function getGame() {
  var randomNumber = Math.floor(Math.random() * array.length);

  while(randomNumber == lastPicked || randomNumber == secondLastPicked || randomNumber == thirdLastPicked) {
    randomNumber = Math.floor(Math.random() * array.length);
  }

  thirdLastPicked = secondLastPicked;
  secondLastPicked = lastPicked;
  lastPicked = randomNumber;
  
  myGame = array[randomNumber];

  document.getElementById("game_name").innerHTML = myGame.name;
  document.getElementById("game_description").innerHTML = myGame.message;
  document.getElementById("game_years").innerHTML = "Years Played: <b>" + myGame.yearsPlayed + "</b>";
}

function showForm() {
  document.getElementById("form-button").style.display = "none";
  document.getElementById("form").style.display = "block";
}