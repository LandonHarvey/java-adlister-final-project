
function updateComment(num) {
    console.log(num);
    document.getElementById("parentComment").value = num;
    console.log(document.getElementById("parentComment").value);
    document.getElementById("commentBox").style.display = "block";
    document.location.href = "#comment";
}

function updateReport(num) {
    console.log(num);
    document.getElementById("changeVariable").value = num;
    console.log(document.getElementById("changeVariable").value);
    document.getElementById("reportBox").style.display = "block";
    document.location.href = "#report";
}