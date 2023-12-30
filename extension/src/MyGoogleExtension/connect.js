/* global chrome */

/**
 * Asynchronously sends a POST request to the server to connect with peers.
 *
 * @param {Set<string>} addresses - A set of peer addresses to connect to.
 * @returns {Promise<Array<string>|null>} A promise that resolves to an array of known peers
 *         if the connection is successful, or null if an error occurs.
 */
async function connectWithPeers(addresses) {
    try {
        const url = 'http://localhost:8080/peer/connect';
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(Array.from(addresses)) // Converts Set to Array
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const knownPeers = await response.json();
        console.log('Known peers:', knownPeers);

        // Display the list of known peers in the extension
        displayKnownPeers(knownPeers);
        return knownPeers;
    } catch (error) {
        console.error('Error connecting to peers:', error);
        return null;
    }
}

/**
 * Displays a list of known peers in the UI.
 *
 * @param {Array<string>} knownPeers - An array of known peer addresses.
 */
function displayKnownPeers(knownPeers) {
    const peersList = document.getElementById('peersList');
    peersList.innerHTML = ''; // Clearing the existing list

    knownPeers.forEach(peer => {
        const listItem = document.createElement('li');
        listItem.textContent = peer;
        peersList.appendChild(listItem);
    });
}

// Example usage
document.addEventListener('DOMContentLoaded', function() {
    const connectButton = document.getElementById('connectButton');
    if (connectButton) {
        connectButton.addEventListener('click', function() {
            const addressesInput = document.getElementById('addressesInput').value;
            const addresses = new Set(addressesInput.split(',').map(addr => addr.trim()));

            connectWithPeers(addresses);
        });
    }
});
