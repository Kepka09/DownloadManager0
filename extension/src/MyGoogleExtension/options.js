// options.js

document.addEventListener("DOMContentLoaded", function() {
    const saveButton = document.getElementById("saveBtn");

    saveButton.addEventListener("click", function() {
        // Get the values entered by the user
        const stunServer = document.getElementById("stunServer").value;
        const turnServer = document.getElementById("turnServer").value;

        // Save the settings using chrome.storage
        chrome.storage.local.set({ stunServer, turnServer }, () => {
            console.log("Settings saved.");
        });
    });

    // Load and display previously saved settings
    chrome.storage.local.get(["stunServer", "turnServer"], (result) => {
        const stunServerInput = document.getElementById("stunServer");
        const turnServerInput = document.getElementById("turnServer");

        if (result.stunServer) {
            stunServerInput.value = result.stunServer;
        }

        if (result.turnServer) {
            turnServerInput.value = result.turnServer;
        }
    });
});
