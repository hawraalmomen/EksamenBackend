async function getInventory() {

    const response = await fetch("http://localhost:8080/inventory");

    const data = await response.json();

    const container = document.getElementById("inventoryList");

    container.innerHTML = "";

    // loop gennem object/map
    for (const component in data) {

        const quantity = data[component];

        container.innerHTML += `
            <div style="border:1px solid #ccc; padding:10px; margin:5px;">
                <p><strong>${component}</strong></p>
                <p>Quantity: ${quantity}</p>
            </div>
        `;
    }
}