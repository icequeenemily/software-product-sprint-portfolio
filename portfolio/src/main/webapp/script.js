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

/**
 * Fetches a random quote from the server and adds it to the DOM.
 */
function getRandomQuote() {
  console.log('Fetching a saying.');

  // The fetch() function returns a Promise because the request is asynchronous.
  const responsePromise = fetch('/data');

  // When the request is complete, pass the response into handleResponse().
  responsePromise.then(handleResponse);
}

/**
 * Handles response by converting it to text and passing the result to
 * addQuoteToDom().
 */
function handleResponse(response) {
  console.log('Handling the response.');

  // response.text() returns a Promise, because the response is a stream of
  // content and not a simple variable.
  const textPromise = response.text();

  // When the response is converted to text, pass the result into the
  // addQuoteToDom() function.
  textPromise.then(addQuoteToDom);
}

/** Adds a random quote to the DOM. */
function addQuoteToDom(saying) {
    saying= "The Early Bird gets the Worm";
  console.log('Adding quote to dom: ' + "The Early Bird Gets the Worm!");
  const quoteContainer = document.getElementById('saying-container');
  quoteContainer.innerText = saying;
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
//For JSON
function getServerStats() {
  fetch('/data').then(response => response.json()).then((quotes) => {
    // stats is an object, not a string, so we have to
    // reference its fields to create HTML content

    const statsListElement = document.getElementById('data-container');
    statsListElement.innerHTML = '';
    statsListElement.appendChild(
        createListElement('Message 1: ' + quotes[1]));
    statsListElement.appendChild(
        createListElement('Message 2: ' + quotes[2]));
    statsListElement.appendChild(
        createListElement('Message 3: ' + quotes[3]));
  });
}

/** Creates an <li> element containing text. */
function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}
