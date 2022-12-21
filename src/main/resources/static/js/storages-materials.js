materialsStoragesTable = document.getElementById('materials-storages-table');

materialsStoragesUrl = "http://localhost:8080/storages-materials"
fetch(materialsStoragesUrl)
    .then((response) => {
        if (!response.ok)
            alert("Unable to connect to server | HTTP Error: ${response.status}");

        return response.json();
    })
    .then((json) => {
        const materialsStorages = json;
        for (const ms of materialsStorages) {
            const tdStorageId = document.createElement('td');
            const tdMaterialId = document.createElement('td');
            const tdMeasureUnit = document.createElement('td');
            const tdAmount = document.createElement('td');
            const tdLastOperationDate = document.createElement('td');
            const tdDeleteButton = document.createElement('td');

            const deleteButton = document.createElement('button');
            deleteButton.textContent = '🗑';
            deleteButton.onclick = () => {
                fetch('http://localhost:8080/storages/' + ms.storageId + '/materials/' + ms.materialId + '/storages-materials', {
                    method: 'DELETE',
                })
                .then(() => {
                    window.location.reload();
                });
            };

            tdStorageId.textContent = ms.storageId;
            tdMaterialId.textContent = ms.materialId;
            tdMeasureUnit.textContent = ms.measureUnit;
            tdAmount.textContent = ms.amount;
            tdLastOperationDate.textContent = ms.lastOperationDate;
            tdDeleteButton.appendChild(deleteButton);

            const tr = document.createElement('tr');
            tr.appendChild(tdStorageId);
            tr.appendChild(tdMaterialId);
            tr.appendChild(tdMeasureUnit);
            tr.appendChild(tdAmount);
            tr.appendChild(tdLastOperationDate);
            tr.appendChild(tdDeleteButton);

            materialsStoragesTable.appendChild(tr);
        }
    });