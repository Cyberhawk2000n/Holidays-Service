<%--
  Created by IntelliJ IDEA.
  User: Станислав
  Date: 07.12.2016
  Time: 5:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="klosterteam.happiness_service.Servlets.VotingServletAPI" %>

<!DOCTYPE html>
<html>
<head>
    <title>Contact form using Bootstrap 3.3.4</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta charset="utf-8">
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery-3.1.1.min.js"></script>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
Welcome to vote #<b><%=request.getParameter("id")%></b>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="main.html">HappyService</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="main.html">Main</a></li>
            <li><a href="events-create.html">Events</a></li>
            <li><a href="about.htlm">About me</a></li>
        </ul>
        <form class="navbar-form navbar-left">
            <div class="form-group">
                <label class="btn btn-default btn-file">
                    Update members <input type="file" style="display: none;">
                </label>
                <button type="submit" class="btn btn-success">Become an organizaer</button>
            </div>
        </form>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="registration.html"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        </ul>
    </div>
</nav>

<div class="row">
    <div class="col-md-3 col-sm-offset-3" >
        <h1>Vote for a gift!</h1>
        <form role="form" id="EventForm" onclick="check()" \>
            <label for="Options" class="h4">Choose an option:</label>

            <div id="Options">
                <% String[] options = VotingServletAPI.getVotingOptions(Integer.parseInt(request.getParameter("id")));
                    for (int i=0; i<options.length;i++ ){%>
                <label class="radio-inline">
                    <input type="radio" name="freq_radio"><%=options[i]%></label> <br>
                <% }%>
            </div>
            <div id="Progress">
                <% int[] progress = VotingServletAPI.getVotingProgress(Integer.parseInt(request.getParameter("id")));
                    for (int i=0; i<options.length;i++ ){%>
                    <%=i%>  <%=options[i]%> -- <%=progress[i]%>
                <% }%>
            </div>
        </form>
    </div>
    <div class="col-md-6 ">
        <h1>Information:</h1>
        <div id="Preferences">
            <label class="h4">Preferences:</label>
            <%=VotingServletAPI.getPreference(Integer.parseInt(request.getParameter("id")))%>
        </div>
        <div id="History">
            <label class="h4">History:</label>
            <% String[] history = VotingServletAPI.getVotingOptions(Integer.parseInt(request.getParameter("id")));
                for (int i=0; i<history.length;i++ ){%>
            <label class="radio-inline">
                <input type="radio" name="freq_radio"><%=history[i]%></label> <br>
            <% }%>
        </div>
    </div>
</div>

<br>
<div class="row">
    <div class="col-md-6 col-sm-offset-3 ">
        <div class="btn-toolbar">
            <button type="submit" id="gift-modal" class="btn btn-info btn-md pull-left" data-toggle="modal" data-target="#myModal">Propose a gift
            </button>

            <div id="myModal" class="modal fade" role="dialog">
                <div class="modal-dialog">
                    <!--Modal body-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Propose a gift:</h4>
                        </div>
                        <div class="modal-body">
                            <form>
                                <label class="h4">Choose a category:</label>
                                <br>
                                <select class="selectpicker" name="category" id="CategoryList" data-live-search="true">
                                    <option>Tourism</option>
                                    <option>Other</option>
                                </select>
                                <br>
                                <label class="h4" >Enter name of gift:</label>
                                <input type='text' name="gift_name" id="gift_name" class="form-control"/>
                                <label class="h4">List of gifts in this category:</label> <br>
                                <input type="radio" name="type_radio"> test <br>
                                <input type="radio" name="type_radio"> test2 <br>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-success" data-dismiss="modal" onclick="add_member();">Add</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Abort</button>
                        </div>
                    </div>
                </div>
            </div>

            <button type="submit" id="form-submit" class="btn btn-success btn-md pull-left">Confirm
            </button>
        </div>
    </div>
</div>
</body>

</html>

