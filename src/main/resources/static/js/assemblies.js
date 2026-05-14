async function getAssemblies() {

    const response = await fetch("http://localhost:8080/assemblies");

    const assemblies = await response.json();

    const container = document.getElementById("assemblyList");

    container.innerHTML = "";

    assemblies.forEach(assembly => {

        container.innerHTML += `
            <div style="border:1px solid #ccc; padding:10px; margin:5px;">
                <p><strong>${assembly.name}</strong></p>
                <p>ID: ${assembly.id}</p>
            </div>
        `;
    });
}