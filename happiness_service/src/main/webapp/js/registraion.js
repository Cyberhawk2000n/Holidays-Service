function submitForm() {
    var email = document.getElementById("email");
    var pw = document.getElementById("pw");
    $.ajax({
        type : "POST",
        url : "/happiness_service-1.0-SNAPSHOT/RegistrationServlet",
        data :
        {
            "message" : "init",
            "email" : email.value,
            "pw" : pw.value
        },
        dataType: "json",

        success : function(responseText) {
            /*alert("Everything's fine\n"+responseText.message);*/
            if(responseText.message == "success")
                window.location.replace("/happiness_service-1.0-SNAPSHOT/main.html");
            else
                $('#msgSubmit').show();
        },

        error:function(data,status,er) {
            alert("MISTAKES WERE MADE \n\nerror: "+data+" \nstatus: "+status+" \ner:"+er);
        }
    })
}