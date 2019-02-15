
const client = filestack.init('A3wyoxEWPREm8wSx1lWw5z');

let handler = document.getElementById("handlez").value;

console.log(handler);

if (handler === ""){
    const imgPlaceholder = document.createElement('img');
    imgPlaceholder.width = 360;
    imgPlaceholder.height = 250;
    imgPlaceholder.src = "/static/img/placeholder-profile-sq.jpg";
    document.getElementById('content').appendChild(imgPlaceholder)
}

client.retrieve(handler).then((blob) => {
    const urlCreator = window.URL || window.webkitURL;
    const img = document.createElement('img');
    img.width = 360;
    img.height = 250;
    img.src = urlCreator.createObjectURL(blob);

    document.getElementById('content').appendChild(img);
}).catch((error) => {
    console.error(error);
});