storagesTable = document.getElementById('storages-table');

storagesUrl = "http://localhost:8080/storages"
fetch(storagesUrl)
    .then((response) => {
        if (!response.ok)
            alert("Unable to connect to server | HTTP Error: ${response.status}");

        return response.json();
    })
    .then((json) => {
        const storages = json;
        for (const storage of storages) {
            const tdId = document.createElement('td');
            const tdOwnerSurname = document.createElement('td');
            const tdDeleteButton = document.createElement('td');

            const deleteButton = document.createElement('button');
            deleteButton.textContent = '🗑';
            deleteButton.onclick = () => {
                fetch(storagesUrl + '/' + storage.id, {
                    method: 'DELETE',
                })
                    .then(() => {
                        window.location.reload();
                    });
            };

            tdId.textContent = storage.id;
            tdOwnerSurname.textContent = storage.ownerSurname;
            tdDeleteButton.appendChild(deleteButton);

            const tr = document.createElement('tr');
            tr.appendChild(tdId);
            tr.appendChild(tdOwnerSurname);
            tr.appendChild(tdDeleteButton);

            storagesTable.appendChild(tr);
        }
    });