materialsStoragesTable = document.getElementById('materials-storages-table');

materialsStoragesUrl = "http://localhost:8080/storages-materials/small-amount";
fetch(materialsStoragesUrl)
    .then((response) => {
        if (!response.ok)
            alert("Unable to connect to server | HTTP Error: ${response.status}");

        return response.json();
    })
    .then((json) => {
        const materialsStorages = json;
        for (const ms of materialsStorages) {
            const tdMaterialName = document.createElement('td');
            const tdStorageId = document.createElement('td');
            const tdAmount = document.createElement('td');

            tdMaterialName.textContent = ms.materialName;
            tdStorageId.textContent = ms.storageId;
            tdAmount.textContent = ms.amount;
            
            const tr = document.createElement('tr');
            tr.appendChild(tdMaterialName);
            tr.appendChild(tdStorageId);
            tr.appendChild(tdAmount);

            materialsStoragesTable.appendChild(tr);
        }
    });