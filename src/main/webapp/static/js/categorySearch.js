"use strict"

function chkcontrol(j) {
    var total=0;
    for(var i=0; i < document.catCheck[0].categoryname.length; i++){
        if(document.catCheck[0].categoryname[i].checked){
            total =total +1;}
        if(total > 3){
            alert("Please Select only three")
            document.catCheck[0].categoryname[i].checked = false;
            return false;
        }
    }
}
