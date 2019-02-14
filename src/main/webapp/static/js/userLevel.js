
window.onload = function userLevel(){
    let likes = document.getElementById("likes").innerText;
    let level = document.getElementById("level");
    let levels = [1,2,3,4,5,6,7,8,9,10];
    let levelCheck = likes/10;
    let distance = Math.abs(levels[0] - levelCheck);
    let idx = 0;
    for(let c = 1; c < levels.length; c++) {
        let cdistance = Math.abs(levels[c] - levelCheck);
        if (cdistance < distance) {
            idx = c;
            distance = cdistance;
        }
    }
    level.innerText = levels[idx];
    outOf();
};

function outOf(){
    let levelz = document.getElementById("level").innerText;
    let outOf = document.getElementById("outof");
    if (levelz === 10){
        outOf.innerText = "/" + levelz + "00";
    } else {
        outOf.innerText = "/" + levelz + 0;
    }
}