materialsTable = document.getElementById('materials-table');

materialsUrl = "http://localhost:8080/materials/big-amount"
fetch(materialsUrl)
    .then((response) => {
        if (!response.ok)
            alert("Unable to connect to server | HTTP Error: ${response.status}");

        return response.json();
    })
    .then((json) => {
        const materials = json;
        for (const material of materials) {
            const tdName = document.createElement('td')
            const tdStorageAmount = document.createElement('td')

            tdName.textContent = material.name;
            tdStorageAmount.textContent = material.storageAmount;

            const tr = document.createElement('tr');
            tr.appendChild(tdName);
            tr.appendChild(tdStorageAmount);

            materialsTable.appendChild(tr);
        }
    });