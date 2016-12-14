//todo make this structure displaying correctly. Separete jQuerries in separate functions
var arr =[];

$(document).ready(function() {

    if(!checkRole()){
        window.location.replace("/happiness_service-1.0-SNAPSHOT/registration.html");
    }

    $.ajax({
        type : "POST",
        url : "/happiness_service-1.0-SNAPSHOT/AboutMePreferencesServlet",
        data :
        {
            "message" : "info",
        },
        dataType: "json",

        success : function(responseText) {
            alert("Everything's fine\n" + responseText.message);
            $.each(responseText, function() {
                arr.push(this.Name);
                var _name = this.Name;
                $('#ListOfCategories').append(
                    '<label class="control-label">'+_name+'</label>' +
                    '<select class="form-control" id="'+_name+'" name="'+_name+'" multiple>'+_name+'</select>'
                );
                $.each(this.Subname,function(){
                    var _subname = this.toString();
                    var _id_for_append = '#'+_name;
                    $(_id_for_append).append(
                        '<option value="'+_subname+'">'+_subname+'</option>'
                    )
                })
            });
        },

        error:function(data,status,er) {
            alert("MISTAKES WERE MADE \n\nerror: "+data+" \nstatus: "+status+" \ner:"+er);
        }
    });
})

function submitForm(){
    var selected_options =[];
    var selected_sub_options =[];
    var count_selected_sub =[];
    for(var i=0;i<arr.length;i++){
        selected_options.push(arr[i]);
        var count =0;
        var options = document.getElementsByName(arr[i]);
        for(var j=0;j<options[0].length;j++){
            if(options[0][j].selected == true){
                selected_sub_options.push(options[0][j].value);
                count++;
            }
        }
        count_selected_sub.push(count);
    }
    $.ajax({
        type : "POST",
        url : "/happiness_service-1.0-SNAPSHOT/AboutMePreferencesServlet",
        data :
        {
            "message" : "update",
            "categories" : selected_options,
            "sub_categories" : selected_sub_options,
            "sub_count" : count_selected_sub
        },
        dataType: "json",

        success : function(responseText) {
            alert("Everything's fine\n"+responseText.message);
            //код заполения input-ов
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
