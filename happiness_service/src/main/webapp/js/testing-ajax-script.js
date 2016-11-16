function TestConnection() {
    $.ajax({
        type : "POST",
        url : "/",
        data : 
        {
            "test":
            {
            	"message" : "test connection processing!"
            }
        },
        dataType: "json",

        success : function(responseText) {
            $('#ajaxGetUserServletResponse').text(responseText);
        }
    });
}