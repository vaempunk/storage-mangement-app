const materialsUrl = 'http://localhost:8080/materials'
function sendData(id, materialJson) {
    let requestUrl = materialsUrl;
    if (id != 0) {
        requestUrl += '/' + id;
    }
    fetch(requestUrl, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(materialJson),
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
    const name = document.getElementById('name').value;
    const measureUnit = document.getElementById('measure-unit').value;
    const unitPrice = document.getElementById('unit-price').value;
    const materialJson = {
        "name": name,
        "measureUnit": measureUnit,
        "unitPrice": unitPrice,
    };
    const id = document.getElementById('id').value;
    sendData(id, materialJson);
};
