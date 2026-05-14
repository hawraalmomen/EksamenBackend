
async function getOrders() {

    try {
        const response = await fetch("http://localhost:8080/orders");
        const orders = await response.json();

        const orderList = document.getElementById("orderList");
        orderList.innerHTML = "";

        orders.forEach(order => {

            const isReceived = order.receivedDate !== null;

            const div = document.createElement("div");
            div.style.border = "1px solid #ccc";
            div.style.padding = "10px";
            div.style.margin = "5px";

            div.innerHTML = `
                <p><strong>Order ID:</strong> ${order.id}</p>
                <p><strong>Tracking:</strong> ${order.trackingCode}</p>
                <p><strong>Ordered:</strong> ${order.orderedDate}</p>
                <p><strong>Expected:</strong> ${order.expectedDelivery}</p>
                <p><strong>Status:</strong> ${isReceived ? "✔ Received" : "⏳ Pending"}</p>
            `;

            // kun hvis ordren IKKE er modtaget
            if (!isReceived) {
                const btn = document.createElement("button");
                btn.innerText = "Open Order";

                btn.onclick = () => openOrder(order.id);

                div.appendChild(btn);
            }

            orderList.appendChild(div);
        });

    } catch (error) {
        console.error("Error fetching orders:", error);
    }
}


function openOrder(id) {
    window.location.href = `order.html?id=${id}`;
}