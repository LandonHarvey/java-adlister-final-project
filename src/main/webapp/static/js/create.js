
const inputUpdateHandler = event => {
    const t = event.target;
    
    if (t.id === "title") {
        const counter = document.getElementById("titleLength");
        counter.innerText = 50 - t.value.trim().length;
    }
    if (t.id === "description") {
        const counter = document.getElementById("desLength");
        counter.innerText = 5000 - t.value.trim().length;
    }
};

document.addEventListener("input", inputUpdateHandler);