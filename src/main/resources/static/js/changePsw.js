document.addEventListener("load", validateForm);
document.addEventListener("change", showButton);

var __ret = validateForm();
var flags = [false, false, false];
var showButtFlag = false;


function validateForm() {
    var username = document.getElementById("username");
    var oldPassword = document.getElementById("oldPassword");
    var newPassword = document.getElementById("newPassword");

    username.addEventListener("change", validateUsername);
    oldPassword.addEventListener("change", validateOldPass);
    newPassword.addEventListener("change", validateNewPass);

    return {username: username, oldPassword: oldPassword, newPassword: newPassword};
}

function validateUsername(event){
    if(event.target.value.length != 0)
        flags[0] = true;
    else
        flags[0] = false;
}

function validateOldPass(event) {
    if(event.target.value.length != 0)
        flags[1] = true;
    else
        flags[1] = false;
}

function validateNewPass(event) {
    //reset view feedback
    var oldDiv = document.getElementById("passDiv");
    if (oldDiv != null) {
        oldDiv.parentNode.removeChild(oldDiv);
    }

    var password = event.target.value;

    if (password.length < 8 || password.length > 15) {
        var newDiv = document.createElement("div");
        newDiv.setAttribute("id", "passDiv");
        newDiv.innerHTML = "Length must be between 8 and 15";
        var element = document.getElementById("newPassDiv");
        event.target.style.backgroundColor = "#ff4c4f";
        element.appendChild(newDiv);

        flags[2] = false;
        return false;
    } else {
        event.target.style.backgroundColor = "transparent";
        flags[2] = true;
        return true;
    }
}

function showButton(){
    var counter = 0;
    var oldButton = document.getElementById("newButton");
    var newButton = document.createElement("button");
    newButton.setAttribute("id", "newButton");
    newButton.setAttribute("tabindex", "11");
    newButton.className += "btn btn-primary";
    newButton.innerHTML = "Submit";
    var element = document.getElementById("submitButton");

    for(var i=0;i<flags.length;i++) {
        if(flags[i] == true){
            counter++;
            continue;
        }else{
            if(oldButton != null){
                oldButton.parentNode.removeChild(oldButton);
                showButtFlag = false;
            }
            break;
        }
    }

    if(counter == flags.length && showButtFlag == false){
        element.appendChild(newButton);
        newButton.addEventListener("click", function () {
            document.forms;
        });
        showButtFlag = true;
    }
}