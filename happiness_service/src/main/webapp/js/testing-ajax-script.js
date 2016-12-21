function TestConnection() {
    $.ajax({
        type : "POST",
        url : "/happiness_service-1.0-SNAPSHOT/testingAjaxServlet",
        data : 
        {
            "message" : "test connection processing!"
        },
        dataType: "json",

        success : function(responseText) {
            /*alert("Everything's fine");*/
            //var json = JSON.parse(responseText);
            $('#ajaxGetUserServletResponse').text(responseText.message);
        },

        error:function(data,status,er) {
            alert("error: "+data+" \n status: "+status+" \ner:"+er);
        }
    });
}

