const storagesMaterialsUrl = 'http://localhost:8080/storages-materials'
function sendData(smJson) {
    fetch(storagesMaterialsUrl, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(smJson),
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
    const storageId = document.getElementById('storage-id').value;
    const materialId = document.getElementById('material-id').value;
    const measureUnit = document.getElementById('measure-unit').value;
    const amount = document.getElementById('amount').value;
    const lastOperationDate = document.getElementById('last-operation-date').value;
    const smJson = {
        "storageId": storageId,
        "materialId": materialId,
        "measureUnit": measureUnit,
        "amount": amount,
        "lastOperationDate": lastOperationDate,
    };
    sendData(smJson);
};
