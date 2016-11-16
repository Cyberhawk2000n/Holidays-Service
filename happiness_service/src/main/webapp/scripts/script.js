/**
 * Created by Станислав on 05.11.2016.
 */

$(document).ready(function() {

    //Stops the submit request
    $("#myAjaxRequestForm").submit(function (e) {
        e.preventDefault();
        SendRequest();
    });

    //datepicker
    $(function () {
        $("#datepicker").datepicker();
    });
})

function SendRequest(){
    var user_email = document.getElementById("email").value;
    var user_pw = document.getElementById("pw").value;
    var user_bitrhdate = document.getElementById("datepicker").value;
    $ajax({
        type: "POST",
        url: "/",
        data:
        {
            "user":
            {
                "email":user_email,
                "pw":user_pw,
                "birthdate":user_bitrhdate
            }
        },
        dataType: "json",
        success: function () {
        },
        error: function () {

        },
        completed: function () {

        }
    });
}