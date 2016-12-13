$(document).ready(function(){
		//stops standart form submit request
        //$("#EventForm").submit(function (e) {
        //e.preventDefault();
		//});


	if(!checkRole()){
		window.location.replace("/happiness_service-1.0-SNAPSHOT/registration.html");
	}
	else{
		if($.cookie("role")=="user") {
			alert("You have to be a moderator or an admin to do this \n You can send request for moderator rights \n in your profile");
			window.location.replace("/happiness_service-1.0-SNAPSHOT/registration.html");
		}
	}
	    //Datepicker 
	   	$( function() {
		$( "#datepicker" ).datepicker();
   		});

		$.ajax({
			type : "POST",
			url : "/happiness_service-1.0-SNAPSHOT/EventsServlet",
			data :
			{
				"message" : "users"
			},
			dataType: "json",

			success : function(responseText) {
				alert("Everything's fine");
				$.each(responseText, function() {
					$('#MembersList').append(
					'<option value=\"' +this.Name +'\">'+this.Name+'</option>'
					);
				});
			},

			error:function(data,status,er) {
				alert("MISTAKES WERE MADE \n\nerror: "+data+" \nstatus: "+status+" \ner:"+er);
			}
		});

		//Check is there any event of selected type and user name
		$('#MembersList').on('change', function() {
		var type_event_array = document.getElementsByName('type_radio');
		var type_event;
		var type_event_flag=0;
		for(var i=0;i<type_event_array.length;i++)
			if(type_event_array[i].checked) {
				type_event = type_event_array[i].value;
				type_event_flag=1;
			}
		if(type_event_flag==0)
			return;
		alert ("Data ot from radio:"+type_event+" \n name:"+this.value);

		var new_name = document.getElementById('name');
		$.ajax({
			type : "POST",
			url : "/happiness_service-1.0-SNAPSHOT/EventsServlet",
			data :
			{
				"message" : "user",
				"type" : type_event,
				"name" : "test"
			},
			dataType: "json",

			success : function(responseText) {
				alert("Everything's fine");
				if(responseText.message == "1") {
					if (!$("#EvetExistError").is(":visible"))
						$("#EventExistError").removeClass("hidden");
					$('#myModal1_info').append().text(responseText.type + "\n"+responseText.Date +"\n"+responseText.User);

				}
				else if(responseText.message == "0"){
					if ($("#EvetExistError").is(":visible"))
						$("#EventExistError").addClass("hidden");
				}
			},

			error:function(data,status,er) {
				alert("MISTAKES WERE MADE \n\nerror: "+data+" \nstatus: "+status+" \ner:"+er);
			}
		});
	})


})

//Checking for enabling the template editing field
function check(){
	var freq = document.getElementsByName('freq_radio');
	var type = document.getElementsByName('type_radio');
	var date = document.getElementById('datepicker');
	var select = document.getElementById('MembersList');
	var Email_text = document.getElementById('comment');

	if(Email_text.disabled == false)
		return;
	var flag_freq=0;
	var flag_type=0;
	var flag_date=0;
	var flag_select=0;
	for(var i=0;i<freq.length;i++)
	{
	    if(freq[i].checked)
	     flag_freq=1;
	}
	for(var i=0;i<type.length;i++)
	{
	    if(type[i].checked)
	     flag_type=1;
	} 
	if(date.value != '' && date.value !=date.defaultValue)
		flag_date=1;
	if(select != '')
		flag_select=1; 
	if(flag_freq==1 && flag_date==1 && flag_select==1 && flag_type==1){
		document.getElementById('comment').disabled=false;
		document.getElementById('Refresh_email').disabled=false;
		alert("Здесь я запрашиваю шаблон у сервера");
	}


}

//Refresh the template editing field
function refresh_email(){
	alert("Здесь я запрашиваю шаблон у сервера");
}

//Ajax to merge new User
function add_member(){
	var new_email = document.getElementById('email');
	var new_name = document.getElementById('name');
	alert("Здесь создается новый пользователь");
	alert("Name: "+new_name.value + " \nEmail:" +new_email.value);
	$.ajax({
		type : "POST",
		url : "/happiness_service-1.0-SNAPSHOT/EventsServlet",
		data :
		{
			"message" : "add",
			"Name" : new_name.value,
			"Email" : new_email.value
		},
		dataType: "json",

		success : function(responseText) {
			alert("Everything's fine \nResponse:" + responseText.message);
			$('#MembersList').append(
				'<option value=\"' +new_name +'\">'+new_name+'</option>'
			);
		},

		error:function(data,status,er) {
			alert("MISTAKES WERE MADE \n\nerror: "+data+" \nstatus: "+status+" \ner:"+er);
		}
	});
}

function submitForm(){
		var name = document.getElementById("name");
		var date = document.getElementById("datepicker");
		var comment = document.getElementById("comment");
		$.ajax({
			type : "POST",
			url : "/happiness_service-1.0-SNAPSHOT/EventsServlet",
			data :
			{
				"message" : "event",
				"name": name.value,
				"date": date.value,
				"content":comment.value
			},
			dataType: "json",

			success : function(responseText) {
				alert("Everything's fine");
				$.each(responseText, function() {
					$('#MembersList').append(
						$("<option></option>").text(this.Name)
					);
				});
			},

			error:function(data,status,er) {
				alert("MISTAKES WERE MADE \n\nerror: "+data+" \nstatus: "+status+" \ner:"+er);
			}
		});
}


function checkRole(){
	if($.cookie("role")!=null){
		return true;
	}
	else
		return false;
}
