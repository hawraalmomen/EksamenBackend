
const params = new URLSearchParams(window.location.search);
const orderId = params.get("id");

console.log("ORDER ID:", orderId);

// STOP hvis ingen id
if (!orderId) {
    alert("No order id in URL!");
    throw new Error("Missing orderId in URL");
}


async function loadOrderLines() {

    try {
        const response = await fetch(`http://localhost:8080/orderlines/${orderId}/lines`);

        if (!response.ok) {
            const msg = await response.text();
            throw new Error(msg);
        }

        const lines = await response.json();

        const orderLinesDiv = document.getElementById("orderLines");
        orderLinesDiv.innerHTML = "";

        lines.forEach(line => {

            const div = document.createElement("div");
            div.style.border = "1px solid #ccc";
            div.style.padding = "10px";
            div.style.margin = "5px";

            div.innerHTML = `
                <p><strong>Component:</strong> ${line.component.externalNumber}</p>
                <p><strong>Quantity:</strong> ${line.quantity}</p>
            `;

            orderLinesDiv.appendChild(div);
        });

    } catch (error) {
        console.error("Error loading order lines:", error);
    }
}


async function addToOrder() {

    const componentId = document.getElementById("componentId").value;
    const quantity = document.getElementById("quantity").value;

    const request = {
        componentId: parseInt(componentId),
        quantity: parseInt(quantity)
    };

    try {
        const response = await fetch(`http://localhost:8080/orderlines/${orderId}/lines`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(request)
        });

        if (response.ok) {
            alert("Added to order!");

            document.getElementById("componentId").value = "";
            document.getElementById("quantity").value = "";

            loadOrderLines();
        } else {
            const msg = await response.text();
            alert("Error: " + msg);
        }

    } catch (error) {
        console.error("Error adding to order:", error);
    }
}



loadOrderLines();