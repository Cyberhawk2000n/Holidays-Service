$(document).ready(function() {

    if(!checkRole()){
        window.location.replace("/RegistrationServlet");
    }
    else{
        if($.cookie("role")=="user")
            $('#moderator_btn').hide;
    }
})

function checkRole(){
    if($.cookie("role")!=null){
        return true;
    }
    else
        return false;
}