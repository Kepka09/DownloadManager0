// content.js

// Function for sending messages to the background script
function sendMessageToBackground(message) {
    chrome.runtime.sendMessage(message, function(response) {
        console.log("Response from background script:", response);
    });
}

// Example of sending a request to initiate a connection
sendMessageToBackground({ type: "initiateConnection", data: "Some Data" });

// You can add additional interactions with the DOM of the web page here
