// document.getElementById("navigation1").innerHTML = 
// // '<div class="nav-container" style="display: -webkit-box;"><div class="brand"><a href="#!">'
// //     +'Transport Requisition System</a>'
// //     +'</div>
//     '<div style="color: white;">Transport Requisition System</div><div class="navbar1" id="nav1"><div><a href="javascript:void(0);" style="font-size:15px;" class="icon" onclick="myFunction()">&#9776;</a></div>'
//     +'<a href="#" class="active1">Home</a><a href="#">course</a><a href="#">More</a><div class="dropdown1"><button class="btn">Dropdown'
//     +'<i class="fa fa-caret-down"></i></button><div class="dropdown1-menu"><a href="#">Item 1</a><a href="#">Item 2</a><a href="#">Item 3</a></div></div></div>'


let xml = new XMLHttpRequest();
    xml.open("GET", "http://localhost:8080/getNavbarMenu", true);
    xml.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem("token"));
    xml.setRequestHeader('Access-Control-Allow-Origin', "*");
    var data = {};
    xml.send();
    xml.onload = () => {
      console.log("Request : " + xml.status);
      if (xml.status == 200) {
        const jsonData = JSON.parse(xml.response);
        console.log("jsonData : " + jsonData.navbarmenu[0].navbar_name);

        var text = "<div style='color: white; margin-left:20px;'>Transport Requisition System</div><div class='navbar1' id='nav1'><div><a href='javascript:void(0);' style='font-size:15px;' class='icon' onclick='myFunction()'>&#9776;</a></div>";
        for (let i = 0; i < jsonData.navbarmenu.length; i++) {
          text += "<a href='"+jsonData.navbarmenu[i].navbar_url+"' class='active1'>"+jsonData.navbarmenu[i].navbar_name+"</a>";
        }
        text += "<div class='dropdown1' style='margin-right: 30px;'><button class='btn'>"+jsonData.username.toUpperCase()+"  <i class='fa fa-caret-down'></i></button><div class='dropdown1-menu'>"
          +"<a href='#' data-toggle='modal' data-target='#exampleModalLong'>Change Password</a><a onclick='logout()'>Logout</a></div></div></div>"
          +"<div class='modal fade' id='exampleModalLong' tabindex='-1' role='dialog' aria-labelledby='exampleModalLongTitle' aria-hidden='true'>"
          +"<div class='modal-dialog' role='document'>"
              +"<div class='modal-content'>"
                  +"<div class='modal-header'>"
                      +"<h3 class='modal-title' id='exampleModalLongTitle'>Change Password</h3>"
                      +"<button type='button' class='close' data-dismiss='modal' aria-label='Close'>"
                          +"<span aria-hidden='true'>&times;</span>"
                      +"</button>"
                  +"</div>"
                  
                  +"<div class='modal-body'>"
                      +"<div class='container' style='border: 1px solid #ccc;border-radius: 3px;padding: 30px;'>"
                      +"<div><span id='messageReponse'></span></div>"
                          +"<form>"
  
                              +"<div class='form-group'>"
                                  +"<label for='inputEmail4'>Old Password</label>"
                                  +"<input type='password' class='form-control' id='currentpswd' placeholder='Enter Old Password'>"
                              +"</div>"
  
                              +"<div class='form-group'>"
                                  +"<label for='inputPassword4'>New Password</label>"
                                  +"<input type='password' class='form-control' id='newpswd'"
                                      +"placeholder='Enter New Password'>"
                              +"</div>"
  
                              +"<div class='form-group'>"
                                  +"<label for='inputPassword4'>Confirm New Password</label>"
                                  +"<input type='password' class='form-control' id='confirmpswd'"
                                    +"placeholder='Enter Confirm New Password'>"
                              +"</div>"
  
                              +"<div class='modal-footer'>"
                                  +"<button type='button' class='btn btn-primary' onclick='Submit()' data-toggle='modal'"
                                      +"data-target='#exampleModalLong'>"
                                     +"Save"
                                  +"</button>"
                                  +"<button type='button' class='btn btn-secondary' data-dismiss='modal'>Close</button>"
                              +"</div>"
                          +"</form>"
                      +"</div>"
                  +"</div>"
              +"</div>"
          +"</div>"
      +"</div>";
        console.log(text)
        document.getElementById("navigation1").innerHTML = text;
      }
      else {
        window.location.href = "../index.html";
      }
    }

    function logout()
    {
        console.log("--------------------");
        sessionStorage.clear();
        window.location = "../index.html"
    } 

    function myFunction() {
      var x = document.getElementById("nav1");
      if (x.className === "navbar1") {
        x.className += " responsive";
        document.getElementById("screen1").style.marginTop = "20vh";
      } else {
        x.className = "navbar1";
        document.getElementById("screen1").style.marginTop = "0vh";
      }
    }

    async function Submit()
    {
        console.log("************")
        event.preventDefault()
        const currentpswd = document.getElementById("currentpswd").value;
        const newpswd = document.getElementById("newpswd").value;
        const confirmpswd = document.getElementById("confirmpswd").value;
        const messageReponse = document.getElementById("messageReponse");
    
        if(newpswd == confirmpswd)
        {    
            var xhr = new XMLHttpRequest();
            xhr.open('PUT', 'http://localhost:8080/changePassword', true);
            xhr.setRequestHeader('Content-Type', 'application/json');
            xhr.setRequestHeader('Authorization', 'Bearer '+sessionStorage.getItem("token"));
            xhr.onreadystatechange = function() {
                if(xhr.readyState === XMLHttpRequest.DONE) {
                    if(xhr.status === 200) 
                    {
                        let reponse = xhr.responseText;
                        
                        if(reponse == "200")
                        {
                        messageReponse.textContent = "Successfully Password Updated!";
                        messageReponse.style.color = "green"; 
                        sessionStorage.clear();
                        window.location = "../index.html";
                        }
                        else
                        {
                        messageReponse.textContent = '* Incorrect Current Passoword';
                        messageReponse.style.color = "red"; 
                        }
                    }
                    else 
                    {
                        messageReponse.textContent = "** Something went wrong, please try after sometime";
                        messageReponse.style.color = "red"; 
                        console.error(xhr.responseText);
                    }
                }
            };
    
        var data = {
            currentPassword: currentpswd,
            newPassword: newpswd
        };
    
        xhr.send(JSON.stringify(data))
    
      }
      else
      {
        messageReponse.textContent = "New Password and Confirm Password is not Matched!"
        messageReponse.style.color = "red"; 
      }
    }