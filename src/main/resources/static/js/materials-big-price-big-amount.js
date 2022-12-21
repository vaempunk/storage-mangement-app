materialsTable = document.getElementById('materials-table');

materialsUrl = "http://localhost:8080/materials/big-price-big-amount"
fetch(materialsUrl)
    .then((response) => {
        if (!response.ok)
            alert("Unable to connect to server | HTTP Error: ${response.status}");

        return response.json();
    })
    .then((json) => {
        const materials = json;
        for (const material of materials) {
            const tdId = document.createElement('td');
            const tdName = document.createElement('td')
            const tdMeasureUnit = document.createElement('td')
            const tdUnitPrice = document.createElement('td')

            tdId.textContent = material.id;
            tdName.textContent = material.name;
            tdMeasureUnit.textContent = material.measureUnit;
            tdUnitPrice.textContent = material.unitPrice;

            const tr = document.createElement('tr');
            tr.appendChild(tdId);
            tr.appendChild(tdName);
            tr.appendChild(tdMeasureUnit);
            tr.appendChild(tdUnitPrice);

            materialsTable.appendChild(tr);
        }
    });