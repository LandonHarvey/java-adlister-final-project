
const client = filestack.init('A3wyoxEWPREm8wSx1lWw5z');

let handler = document.getElementById("handlez").value;

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