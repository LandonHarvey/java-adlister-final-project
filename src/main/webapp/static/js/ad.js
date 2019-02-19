function updateComment() {
    document.getElementById("parentComment").value = event.target.value;
    console.log(event.target.value);
    console.log(document.getElementById("parentComment").value);
    document.getElementById("commentBox").style.display = "block";
    document.location.href = "#comment";
}
