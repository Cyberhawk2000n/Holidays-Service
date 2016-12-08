$(document).ready(function() {
	//Datepicker
	$(function () {
		$("#datepicker").datepicker();
	});
	$(function () {
		$("#datepicker1").datepicker();
	});

	if(!checkRole()){
		window.location.replace("/happiness_service-1.0-SNAPSHOT/registration.html");
	}
	else{
		if($.cookie("role")=="user") {
			$.ajax({
				type : "POST",
				url : "/happiness_service-1.0-SNAPSHOT/MainServlet",
				data :
				{
					"message" : "info",
				},
				dataType: "json",

				success : function(responseText) {
					alert("Everything's fine\n"+responseText.message);
					$.each(responseText,function(){
						$('#list-group-events').append(
							'<li class="list-group-item">'+this.date+' '+this.name+'</li>'
						);
					});
				},

				error:function(data,status,er) {
					alert("MISTAKES WERE MADE \n\nerror: "+data+" \nstatus: "+status+" \ner:"+er);
				}
			});
		}
		else{
			$.ajax({
				type : "POST",
				url : "/happiness_service-1.0-SNAPSHOT/MainServlet",
				data :
				{
					"message" : "info",
				},
				dataType: "json",

				success : function(responseText) {
					alert("Everything's fine\n"+responseText.message);
					$.each(responseText,function(){
						$('#list-group-events').append(
							'<li class="list-group-item">'+this.date+' '+this.name+
							'<button onclick="fillUpdate('+this.id+')" type="button" class="btn btn-default btn-sm pull-right"  data-toggle="modal" data-target="#UpdateEventModule"id="e'+this.id+'">'+
							'<span class="glyphicon glyphicon-cog"></span> Edit Event'+
							'</button>'+
							'<button type="button" class="btn btn-default btn-sm pull-right"  data-toggle="modal" data-target="#delayModule"id="d'+this.id+'">'+
							'<span class="glyphicon glyphicon-cog"></span> Delay Event'+
							'</button>'+
							'</li>'
						);
					});
				},

				error:function(data,status,er) {
					alert("MISTAKES WERE MADE \n\nerror: "+data+" \nstatus: "+status+" \ner:"+er);
				}
			});
		}
	}

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
		alert ("Data ot from radio:"+type_event);

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

function fillUpdate(_id){
	fillMemberList();
	$.ajax({
		ype : "POST",
		url : "/happiness_service-1.0-SNAPSHOT/MainServlet",
		data :
		{
			"message" : "event",
			"id":_id
		},
		dataType: "json",

		success : function(responseText) {
			alert(responseText.message);

		},
		error:function(data,status,er) {
			alert("MISTAKES WERE MADE \n\nerror: "+data+" \nstatus: "+status+" \ner:"+er);
		}
	});
}

function fillMemberList() {
	$.ajax({
		type: "POST",
		url: "/happiness_service-1.0-SNAPSHOT/EventsServlet",
		data: {
			"message": "users"
		},
		dataType: "json",

		success: function (responseText) {
			alert("Everything's fine");
			$.each(responseText, function () {
				$('#MembersList').append(
					'<option value=\"' + this.Name + '\">' + this.Name + '</option>'
				);
			});
		},

		error: function (data, status, er) {
			alert("MISTAKES WERE MADE \n\nerror: " + data + " \nstatus: " + status + " \ner:" + er);
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

