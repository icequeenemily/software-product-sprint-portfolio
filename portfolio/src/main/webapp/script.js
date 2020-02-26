// Copyright 2019 Google LLC
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

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['"Hello, you."-Joe Goldberg', '"Three words, eight letters. Say it and I am yours." -Blair Waldorf', '"And so the lion fell in love with the lamb..." -Edward Cullen', '"How you doinnn?" -Joey'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

function getRandomQuoteUsingArrowFunctions() {
  fetch('/data').then(response => response.text()).then((request) => {
    document.getElementById('saying-container').innerText = "The early bird gets the worm!";
  });
}

async function getRandomQuoteUsingAsyncAwait() {
  const response = await fetch('/data');
  const quote = await response.text();
  document.getElementById('saying-container').innerText = "The early bird gets the worm!";
}
