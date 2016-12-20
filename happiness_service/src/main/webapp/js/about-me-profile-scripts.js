$(document).ready(function() {

    if(!checkRole()){
        window.location.replace("/registration.html");
    }
    else{
        if($.cookie("role")=="user") {
            $("#lbl-browse-btn").addClass("hidden");
        }
        else if($.cookie("role")=="mod")
            $("#browse-btn").addClass("hidden");
    }

    //Datepicker
    $( function() {
        $( "#datepicker" ).datepicker();
    });



    $.ajax({
        type : "POST",
        url : "/AboutMeProfileServlet",
        data :
        {
            "message" : "info",
        },
        dataType: "json",

        success : function(responseText) {
            alert("Everything's fine\n"+responseText.message);
            //код заполения input-ов
            $("#email").val(responseText.email);
            $("#pw").val(responseText.pw);
            $("#datepicker").val(responseText.date);
            $("#comment").val(responseText.comment);
            if(responseText.marked == "true")
                $("#No_Congrat").hide();
            else
                $("#Congrat").hide();

        },

        error:function(data,status,er) {
            alert("MISTAKES WERE MADE \n\nerror: "+data+" \nstatus: "+status+" \ner:"+er);
        }
    });
})

function markUser(){
    $.ajax({
        type : "POST",
        url : "/AboutMeProfileServlet",
        data :
        {
            "message" : "mark",
        },
        dataType: "json",

        success : function(responseText) {
            alert("Everything's fine\n"+responseText.message);
            $("#No_Congrat").hide();
            $("#Congrat").show();
        },

        error:function(data,status,er) {
            alert("MISTAKES WERE MADE \n\nerror: "+data+" \nstatus: "+status+" \ner:"+er);
        }
    });
}
function unmarkUser(){
    $.ajax({
        type : "POST",
        url : "/AboutMeProfileServlet",
        data :
        {
            "message" : "unmark",
        },
        dataType: "json",

        success : function(responseText) {
            alert("Everything's fine\n"+responseText.message);
            $("#Congrat").hide();
            $("#No_Congrat").show();
        },

        error:function(data,status,er) {
            alert("MISTAKES WERE MADE \n\nerror: "+data+" \nstatus: "+status+" \ner:"+er);
        }
    });
}


function becomeAnOrganizer(){
    $.ajax({
        type : "POST",
        url : "/AboutMeProfileServlet",
        data :
        {
            "message" : "organize",
        },
        dataType: "json",

        success : function(responseText) {
            alert("Everything's fine\n"+responseText.message);
            $('#Organize-btn').hide();
        },

        error:function(data,status,er) {
            alert("MISTAKES WERE MADE \n\nerror: "+data+" \nstatus: "+status+" \ner:"+er);
        }
    });
}


function formSubmit(){
    var _email = document.getElementById("email");
    var _pw =document.getElementById("pw");
    var _datepicker = document.getElementById("datepicker");
    var _comment = document.getElementById("comment");
    alert("Sending ajax\nemail:"+_email);
    $.ajax({
        type : "POST",
        url : "/AboutMeProfileServlet",
        data :
        {
            "message" : "update",
            "email": _email.value,
            "pw": _pw.value,
            "date": _datepicker.value,
            "comment": _comment.value
        },
        dataType: "json",

        success : function(responseText) {
            alert("Everything's fine\n"+responseText.message);
        },

        error:function(data,status,er) {
            alert("MISTAKES WERE MADE \n\nerror: "+data+" \nstatus: "+status+" \ner:"+er);
        }
    })
}

function checkRole(){
    if($.cookie("role")!=null){
        return true;
    }
    else
        return false;
}
