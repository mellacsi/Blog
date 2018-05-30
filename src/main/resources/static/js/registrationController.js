document.addEventListener("load", validateForm);
document.addEventListener("change", showButton);

var __ret = validateForm();
var flags = [false, false, false, false, false];
var showButtFlag = false;

function validateName(event){
    //reset view feedback
    event.target.style.backgroundColor = "transparent";
    var oldDiv = document.getElementById("newNameDiv");
    if(oldDiv != null){
        oldDiv.parentNode.removeChild(oldDiv);
    }

    event.target.style.backgroundColor = "transparent";
    var capitalLett = 0;
    var letter = 0;
    var name = event.target.value;
    for(var i=0;i<name.length;i++)
    {
        if('A' <= name.charAt(i) && name.charAt(i) <= 'Z') // check if you have an uppercase
            capitalLett++;
        if('a' <= name.charAt(i) && name.charAt(i) <= 'z') // check if you have a lowercase
            letter++;
    }

    if(capitalLett + letter != name.length || name.length == 0){
        var newDiv = document.createElement("div");
        newDiv.setAttribute("id", "newNameDiv");
        newDiv.innerHTML = "Field must be filled out with letters";
        var element = document.getElementById("nameDiv");
        event.target.style.backgroundColor = "#ff4c4f";
        element.appendChild(newDiv);
        flags[0] = false;
        return false;
    }else {
        flags[0] = true;
        return true;
    }
}
function validateSurname(event){
    //reset view feedback
    event.target.style.backgroundColor = "transparent";
    var oldDiv = document.getElementById("newSurnameDiv");
    if(oldDiv != null){
        oldDiv.parentNode.removeChild(oldDiv);
    }

    event.target.style.backgroundColor = "transparent";
    var capitalLett = 0;
    var letter = 0;
    var name = event.target.value;
    for(var i=0;i<name.length;i++)
    {
        if('A' <= name.charAt(i) && name.charAt(i) <= 'Z') // check if you have an uppercase
            capitalLett++;
        if('a' <= name.charAt(i) && name.charAt(i) <= 'z') // check if you have a lowercase
            letter++;
    }

    if(capitalLett + letter != name.length || name.length == 0){
        var newDiv = document.createElement("div");
        newDiv.setAttribute("id", "newSurnameDiv");
        newDiv.innerHTML = "Field must be filled out with letters";
        var element = document.getElementById("surnameDiv");
        event.target.style.backgroundColor = "#ff4c4f";
        element.appendChild(newDiv);
        flags[1] = false;
        return false;
    }else {
        flags[1] = true;
        return true;
    }
}

function validateUsername(event){
    //reset view feedback
    event.target.style.backgroundColor = "transparent";
    var oldDiv = document.getElementById("newUsernameDiv");
    if(oldDiv != null){
        oldDiv.parentNode.removeChild(oldDiv);
    }

    var capitalLett = 0;
    var letter = 0;
    var underSc = 0;
    var numbers = 0;

    var name = event.target.value;
    for(var i=0;i<name.length;i++)
    {
        if('A' <= name.charAt(i) && name.charAt(i) <= 'Z') // check if you have an uppercase
            capitalLett++;
        if('a' <= name.charAt(i) && name.charAt(i) <= 'z') // check if you have a lowercase
            letter++;
        if(name.charAt(i) == '_')
            underSc++;
        if('0' <= name.charAt(i) && name.charAt(i) <= '9') // check if you have a numeric
            numbers++;
    }

    if(capitalLett + letter + numbers + underSc != name.length || name.length == 0){
        var newDiv = document.createElement("div");
        newDiv.setAttribute("id", "newUsernameDiv");
        newDiv.innerHTML = "Field 'Username' must be filled out with letters, numbers or '_'";
        var element = document.getElementById("usernameDiv");
        event.target.style.backgroundColor = "#ff4c4f";
        element.appendChild(newDiv);
        flags[2] = false;
        return false;
    } else {
        flags[2] = true;
        return true;
    }
}

function validatePass(event) {
    //reset view feedback
    var oldDiv = document.getElementById("newPassDiv");
    if (oldDiv != null) {
        oldDiv.parentNode.removeChild(oldDiv);
    }

    var oldDiv2 = document.getElementById("newConfPassDiv");
    if(oldDiv2 != null){
        oldDiv2.parentNode.removeChild(oldDiv2);
    }

    var password = event.target.value;
    var secondPassword = document.getElementById("myconfpassword");

    if (password.length < 8 || password.length > 15) {
        var newDiv = document.createElement("div");
        newDiv.setAttribute("id", "newPassDiv");
        newDiv.innerHTML = "Length must be between 8 and 15";
        var element = document.getElementById("passDiv");
        event.target.style.backgroundColor = "#ff4c4f";
        element.appendChild(newDiv);

        flags[3] = false;
        return false;
    } else if(password != secondPassword.value){
        event.target.style.backgroundColor = "transparent";
        var newDiv = document.createElement("div");
        newDiv.setAttribute("id", "newConfPassDiv");
        newDiv.innerHTML = "Not same passwords";
        var element = document.getElementById("confPassDiv");
        secondPassword.style.backgroundColor = "#ff4c4f";
        element.appendChild(newDiv);

        flags[3] = true;
        flags[4] = false;
        return true;
    } else {
        secondPassword.style.backgroundColor = "transparent";
        event.target.style.backgroundColor = "transparent";
        flags[3] = true;
        flags[4] = true;
        return true;
    }
}

function validateSecondPass(event){
    //reset view feedback
    var oldDiv = document.getElementById("newConfPassDiv");
    if(oldDiv != null){
        oldDiv.parentNode.removeChild(oldDiv);
    }

    var secondPassword = event.target;
    var password = document.getElementById("mypassword").value;
    if(password != secondPassword.value) {
        var newDiv = document.createElement("div");
        newDiv.setAttribute("id", "newConfPassDiv");
        newDiv.innerHTML = "Not same passwords";
        var element = document.getElementById("confPassDiv");
        event.target.style.backgroundColor = "#ff4c4f";
        element.appendChild(newDiv);

        flags[4] = false;
        return false;
    } else if(password == secondPassword.value && flags[3] == true){
        secondPassword.style.backgroundColor = "transparent";
        flags[4] = true;
        return true;
    }else{
        var newDiv = document.createElement("div");
        newDiv.setAttribute("id", "newConfPassDiv");
        newDiv.innerHTML = "Not same passwords";
        var element = document.getElementById("confPassDiv");
        event.target.style.backgroundColor = "#ff4c4f";
        element.appendChild(newDiv);

        flags[4] = false;
        return false;
    }
}

function validateForm() {
    var name = document.getElementById("myname");
    var surname = document.getElementById("mysurname");
    var username = document.getElementById("username");
    var password = document.getElementById("mypassword");
    var secondPassword = document.getElementById("myconfpassword");

    name.addEventListener("change", validateName);
    surname.addEventListener("change", validateSurname);
    username.addEventListener("change", validateUsername);
    password.addEventListener("change", validatePass);
    secondPassword.addEventListener("change", validateSecondPass);

    return {name: name, surname: surname, username: username, password: password, secondPassword: secondPassword};
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

