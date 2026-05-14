
async function getComponents() {

    try {
        const response = await fetch("http://localhost:8080/components");
        const components = await response.json();

        const componentList = document.getElementById("componentList");
        componentList.innerHTML = "";

        components.forEach(component => {

            const div = document.createElement("div");
            div.style.border = "1px solid #ccc";
            div.style.padding = "10px";
            div.style.margin = "5px";

            div.innerHTML = `
                <p><strong>${component.externalNumber}</strong></p>
                <p>ID: ${component.internalNumber}</p>
                <p>Supplier: ${component.supplier?.name ?? "No supplier"}</p>
                <p>Status: ${component.discontinued ? "❌ Discontinued" : "✔ Active"}</p>
            `;

            const btn = document.createElement("button");
            btn.innerText = "Mark discontinued";


            btn.onclick = () => discontinueComponent(component.id);

            div.appendChild(btn);
            componentList.appendChild(div);
        });

    } catch (error) {
        console.error("Error fetching components:", error);
    }
}



async function createComponent() {

    const component = {
        externalNumber: document.getElementById("externalNumber").value,
        internalNumber: document.getElementById("internalNumber").value,
        discontinued: false,
        orderable: true,
        supplier: {
            id: 1
        }
    };


    const response = await fetch("http://localhost:8080/components", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(component)
    });

    if (response.ok) {
        alert("Component created!");

        document.getElementById("externalNumber").value = "";
        document.getElementById("internalNumber").value = "";

        getComponents();
    } else {
        const msg = await response.text();
        alert("Error creating component: " + msg);
    }
}


async function discontinueComponent(id) {

    const confirmDelete = confirm("Are you sure you want to mark this component as discontinued?");
    if (!confirmDelete) return;


    const response = await fetch(`http://localhost:8080/components/${id}`, {
        method: "PUT"
    });

    if (response.ok) {
        alert("Component marked as discontinued");
        getComponents();
    } else {
        const msg = await response.text();
        alert("Error updating component: " + msg);
    }
}