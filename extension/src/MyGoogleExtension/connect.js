/* global chrome */

// Функция для отправки POST-запроса к серверу
async function connectWithPeers(addresses) {
    try {
        const url = 'http://localhost:8080/peer/connect';
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(Array.from(addresses)) // Преобразование Set в Array
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const knownPeers = await response.json();
        console.log('Known peers:', knownPeers);

        // Отображение списка известных пиров в расширении
        displayKnownPeers(knownPeers);
        return knownPeers;
    } catch (error) {
        console.error('Error connecting to peers:', error);
        return null;
    }
}

function displayKnownPeers(knownPeers) {
    const peersList = document.getElementById('peersList');
    peersList.innerHTML = ''; // Очищение существующего списка

    knownPeers.forEach(peer => {
        const listItem = document.createElement('li');
        listItem.textContent = peer;
        peersList.appendChild(listItem);
    });
}

// Пример использования
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

// Функция для отправки GET-запроса к серверу для загрузки файла
async function downloadFile(filePath, chunkSize, delay) {
    try {
        const url = `http://localhost:8080/peer/files?filePath=${encodeURIComponent(filePath)}&chunkSize=${chunkSize}&delay=${delay}`;
        const response = await fetch(url);

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const blob = await response.blob();
        const downloadUrl = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = downloadUrl;
        a.download = filePath.split('D:\\test\\text.txt').pop(); // Имя файла для сохранения
        document.body.appendChild(a);
        a.click();
        window.URL.revokeObjectURL(downloadUrl);
    } catch (error) {
        console.error('Error uploading file:', error);
    }
}

// Пример использования
document.addEventListener('DOMContentLoaded', function() {
    const downloadButton = document.getElementById('downloadButton');
    if (downloadButton) {
        downloadButton.addEventListener('click', function() {
            const filePath = document.getElementById('filePathInput').value;
            const chunkSize = parseInt(document.getElementById('chunkSizeInput').value, 10);
            const delay = parseInt(document.getElementById('delayInput').value, 10);

            downloadFile(filePath, chunkSize, delay);
        });
    }
});

// Функция для загрузки части файла
async function downloadFilePart(filePath, start, end) {
    try {
        const url = `http://localhost:8080/files/partial?filePath=${encodeURIComponent(filePath)}&start=${start}&end=${end}`;
        const response = await fetch(url);

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        return await response.blob();
    } catch (error) {
        console.error('Error downloading file part:', error);
        throw error;
    }
}

// Функция для получения общего размера файла
async function getFileSize(filePath) {
    const sizeResponse = await fetch(`http://localhost:8080/fileSize?filePath=${encodeURIComponent(filePath)}`);
    if (!sizeResponse.ok) {
        throw new Error(`HTTP error! status: ${sizeResponse.status}`);
    }
    return await sizeResponse.json();
}

// Функция для загрузки файла по частям
async function downloadFileByParts(filePath, chunkSize) {
    try {
        const totalSize = await getFileSize(filePath);
        let start = 0;
        let end = chunkSize - 1;

        while (start < totalSize) {
            const blob = await downloadFilePart(filePath, start, end);
            saveFile(blob, filePath);

            start = end + 1;
            end = Math.min(start + chunkSize - 1, totalSize - 1);
        }
    } catch (error) {
        console.error('Error during file download by parts:', error);
    }
}

// Функция для сохранения файла на клиентской стороне
function saveFile(blob, filePath) {
    const downloadUrl = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = downloadUrl;
    a.download = filePath.split('/').pop();
    document.body.appendChild(a);
    a.click();
    window.URL.revokeObjectURL(downloadUrl);
}

document.getElementById('downloadButton1').addEventListener('click', function() {
    const filePath = document.getElementById('filePath').value;
    downloadFileByParts(filePath, 3); // Вы можете изменить размер чанка здесь
});


