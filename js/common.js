
let request = new XMLHttpRequest();
request.open("GET", "http://localhost:8080/getCommonDetails");
request.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem("token"));
request.setRequestHeader('Access-Control-Allow-Origin', "*");
request.send();
request.onload = () => {
    console.log(request);
    if (request.status == 200) {
        const jsonData = JSON.parse(request.response);
        console.log(jsonData);
        const userData = jsonData.users;
        const designationData = jsonData.designations;
        document.getElementById("requester").textContent = userData.display_name;
        var text1 = "<option value='' selected>-- Select --</option>";
        for (let i = 0; i < designationData.length; i++) {
            text1 += "<option value='" + designationData[i].designation_name + "'>" + designationData[i].designation_name + "</option>";
        }
        // console.log("text1 :"+text1)
        document.getElementById("designation_select").innerHTML = text1;

        const divisionData = jsonData.divisions;
        var text2 = "<option value='' selected>-- Select --</option>";
        for (let i = 0; i < divisionData.length; i++) {
            text2 += "<option value='" + divisionData[i].division_name + "'>" + divisionData[i].division_name + "</option>";
        }
        // console.log("text2 :"+text2)
        document.getElementById("division_select").innerHTML = text2;
        document.getElementById("division_select1").innerHTML = text2;
        document.getElementById("division_select").value=userData.division_name;
    }
    else {
        window.location = "../index.html"
    }
}