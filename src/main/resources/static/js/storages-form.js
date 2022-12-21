const storagesUrl = 'http://localhost:8080/storages'
function sendData(id, storageJson) {
    let requestUrl = storagesUrl;
    if (id != 0)
        requestUrl += '/' + id;
    fetch(requestUrl, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(storageJson),
    })
        .then(() => {
            window.location.reload();
        })
        .catch((error) => {
            alert(error);
        });
}

const submitButton = document.getElementById('submit-button');
submitButton.onclick = () => {
    const ownerSurname = document.getElementById('owner-surname').value;
    const storageJson = {
        "ownerSurname": ownerSurname,
    };
    const id = document.getElementById('id').value;
    sendData(id, storageJson);
};
