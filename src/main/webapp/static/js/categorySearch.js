"use strict"

function chkcontrol(j) {
    var total=0;
    for(var i=0; i < document.catCheck.categoryname.length; i++){
        if(document.catCheck.categoryname[i].checked){
            total =total +1;}
        if(total > 3){
            alert("Please Select only three")
            document.catCheck.categoryname[i].checked = false;
            return false;
        }
    }
}
