$(document).ready(function(){
		//stops standart form submit request
	    $("#EventForm").submit(function (e) {
        e.preventDefault();
        SendRequest();
		});

	    //Datepicker 
	   	$( function() {
		$( "#datepicker" ).datepicker();
   		});

	   	//test JSON for members
   		var jsonResult = [{
		    "Id": 1,
		    "Name": "Иванов Иван Иванович"},
		{
		    "Id": 2,
		    "Name": "Алексеев Алексей Алексеевич"},
		{
		    "Id": 4,
		    "Name": "Петров Петр Петрович"}
		]

		$.each(jsonResult, function() {
		   $('#MembersList').append(
		        $("<option></option>").text(this.Name)
		   );
		});

	})	

function check()
{
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

	function refresh_email(){
		alert("Здесь я запрашиваю шаблон у сервера");
	}
	

	function add_member(){
		var new_email = document.getElementById('email');
		var new_name = document.getElementById('name');
		alert("Здесь создается новый пользователь");
		alert(new_name.value,new_email.value);
	}

	$('#MembersList').on('change', function() {
  		if(!$("#EvetExistError").is(":visible")){
  			// remove hidden class
  			$("#EventExistError").removeClass("hidden");
		}
  		alert( this.value );	 
	})
}
