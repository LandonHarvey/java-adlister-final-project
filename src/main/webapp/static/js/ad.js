
function updateComment(num) {
    console.log(num);
    document.getElementById("parentComment").value = num;
    console.log(document.getElementById("parentComment").value);
    document.getElementById("commentBox").style.display = "block";
    document.location.href = "#comment";
}
