package klosterteam.happiness_service.Servlets;

import java.io.*;
import java.util.Calendar;
import java.util.List;
import javax.json.JsonArray;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.Cookie;
import klosterteam.happiness_service.HappyHibernate;
import klosterteam.hibernate.Event_types;
import klosterteam.hibernate.Events;
import klosterteam.hibernate.Roles;
import klosterteam.hibernate.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Created by Stanislav on 22.11.2016.
 * stas33553377@yandex.ru
 */
public class EventsServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logger log = LogManager.getLogger(EventsServlet.class);
        log.debug("EventsServlet ---> processRequest() ---> request ="+request.getParameter("message"));
        if("users".equals(request.getParameter("message"))){
            log.debug("EventsServlet ---> processRequest() ---> forming JsonArray of Users");
            //read list of users of this department. Get the department from moderator email (cookies.email)
            //json respond:
            //jsonObject [ {Name: "FirstName LastName Patronymmic"} , {Name: "FirstName LastName Patronymmic"} ]

            JsonArray json = this.getUsersFromDepartment(request);
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
            return;
        }
        else if("user".equals(request.getParameter("message"))){
            log.debug("EventsServlet ---> processRequest() ---> forming an event Json based on type: "+request.getParameter("type")+" and user name: "+request.getParameter("name"));
            //check event existance by type = request.getParameter("e_type") and full name  of user = request.getParameter("e_name")
            //example of structure :
            //jsonObject {
            //  message : 1  // '0' if there is no Event on this user and '1' if there is one
            //  Event_name : 'Birthday on 16.09'
            //}
            JsonObject json = this.getEventExists(request);
            if (json != null)
            {
                response.setContentType("application/json");
                response.getWriter().write(json.toString());
            }
            return;
        }
        else if("add".equals(request.getParameter("message"))) {
            log.debug("EventsServlet ---> processRequest() ---> adding user to Database with name: " + request.getParameter("Name") + " and email: " + request.getParameter("Email"));
            //merge new User to DB. request fields: "Name" , "Emai;"
            JsonObject json = Json.createObjectBuilder()
                    .add("message", "Success on adding user =) kinda lel")
                    .build();
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
            return;
        }
        else if("event".equals(request.getParameter("message"))){
            log.debug("EventsServlet ---> processRequest() --->  creating Event");
            //merge new Event to DB.
            //fields: name, date, content (email content) (for now) Still in development
            JsonObject json = Json.createObjectBuilder()
                    .add("message", "Event merged to db")
                    .build();
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
            return;
        }
        if("managers".equals(request.getParameter("message"))){
            log.debug("EventsServlet ---> processRequest() ---> forming JsonArray of Managers");
            //read list of users of this department. Get the department from moderator email (cookies.email)
            //json respond:
            //jsonObject [ {Name: "FirstName LastName Patronymmic"} , {Name: "FirstName LastName Patronymmic"} ]

            JsonArray json = this.getManagers(request);
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
            return;
        }
        log.debug("EventsServlet ---> processRequest() ---> wrong Paramenter sending 400 HTTP code ");
        // If we didn't entered any case of 
        JsonObject json = Json.createObjectBuilder()
                .add("message", "Error 400: Bad request")
                .build();
        response.setContentType("application/json");
        response.getWriter().write(json.toString());
        return;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    protected JsonArray getUsersFromDepartment(HttpServletRequest request)
    {
        Logger log = LogManager.getLogger(EventsServlet.class);
        try
        {
            JsonArrayBuilder jsonBuilder = Json.createArrayBuilder();
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie: cookies)
                if ("email".equals(cookie.getName()))
                {
                    HappyHibernate hHibernate = new HappyHibernate();
                    Users user = hHibernate.selectUsersByEmail(cookie.getValue()).get(0);
                    List<Users> list = hHibernate.selectUsersByDepartment(user.getDepId());
                    if (list.isEmpty())
                        return jsonBuilder.build();
                    for (Users person: list)
                    {
                        if ("".equals(person.getSurname()))
                            jsonBuilder.add(Json.createObjectBuilder().add("Name", person.getName() + " "
                                + person.getSurname()).add("id", person.getId()));
                        else
                            jsonBuilder.add(Json.createObjectBuilder().add("Name", person.getName() + " "
                                + " " + person.getPatronymic() + " " + person.getSurname()).add("id", person.getId()));
                    }
                }
            return jsonBuilder.build();
        }
        catch (Exception exc)
        {
            log.warn("Getting users from DB exception", exc);
            return null;
        }
    }
    
    protected JsonArray getManagers(HttpServletRequest request)
    {
        Logger log = LogManager.getLogger(EventsServlet.class);
        try
        {
            JsonArrayBuilder jsonBuilder = Json.createArrayBuilder();
            Cookie[] cookies = request.getCookies();
            Users person = null;
            HappyHibernate hHibernate = new HappyHibernate();
            for (Cookie cookie: cookies)
                if ("email".equals(cookie.getName()))
                {
                    Users user = hHibernate.selectUsersByEmail(cookie.getValue()).get(0);
                    person = user.getDepId().getManagerId();
                    if ("".equals(person.getSurname()))
                        jsonBuilder.add(Json.createObjectBuilder().add("Name", person.getName() + " "
                            + person.getSurname()).add("id", person.getId()));
                    else
                        jsonBuilder.add(Json.createObjectBuilder().add("Name", person.getName() + " "
                            + " " + person.getPatronymic() + " " + person.getSurname()).add("id", person.getId()));
                }
            Roles role = hHibernate.selectRoles("root").get(0);
            List<Users> list = hHibernate.selectUsersByRole(role);
            if ((person == null)||(list.get(0).getId() != person.getId()))
            {
                if ("".equals(list.get(0).getSurname()))
                    jsonBuilder.add(Json.createObjectBuilder().add("Name", list.get(0).getName() + " "
                        + list.get(0).getSurname()).add("id", list.get(0).getId()));
                else
                    jsonBuilder.add(Json.createObjectBuilder().add("Name", list.get(0).getName() + " "
                        + " " + list.get(0).getPatronymic() + " " + list.get(0).getSurname())
                            .add("id", list.get(0).getId()));
            }
            role = hHibernate.selectRoles("moderator").get(0);
            list = hHibernate.selectUsersByRole(role);
            for (int i = 0; i < list.size(); i++)
            {
                if ((person != null)&&(list.get(i).getId() == person.getId()))
                    continue;
                if ("".equals(list.get(i).getSurname()))
                    jsonBuilder.add(Json.createObjectBuilder().add("Name", list.get(i).getName() + " "
                        + list.get(i).getSurname()).add("id", list.get(i).getId()));
                else
                    jsonBuilder.add(Json.createObjectBuilder().add("Name", list.get(i).getName() + " "
                        + " " + list.get(i).getPatronymic() + " " + list.get(i).getSurname())
                            .add("id", list.get(i).getId()));
            }
            return jsonBuilder.build();
        }
        catch (Exception exc)
        {
            log.warn("Getting users from DB exception", exc);
            return null;
        }
    }
    
    protected JsonObject getEventExists(HttpServletRequest request)
    {
        Logger log = LogManager.getLogger(EventsServlet.class);
        try
        {
            JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
            String type = "";
            long userId = -1;
            type = request.getParameter("type");
            userId = Long.valueOf(request.getParameter("id"));
            if (!"".equals(type) && userId != -1)
            {
                HappyHibernate hHibernate = new HappyHibernate();
                Users user = hHibernate.selectUserById(userId);
                Event_types eType = hHibernate.selectEventTypes(type).get(0);
                List<Events> list = hHibernate.selectEventsByUserAndType(user, eType);
                if (list == null)
                {
                    jsonBuilder.add("message","0");
                }
                else
                {
                    Events event = list.get(0);
                    Calendar date = Calendar.getInstance();
                    date.setTime(event.getDate());
                    StringBuilder sb = new StringBuilder();
                    sb.append(date.get(Calendar.DATE))
                            .append("-")
                            .append(date.get(Calendar.MONTH))
                            .append("-")
                            .append(date.get(Calendar.YEAR));
                    jsonBuilder.add("message","1")
                    .add("type", event.getTypeId().getName())
                    .add("Date", sb.toString());
                    if (event.getUserId() != null)
                        jsonBuilder.add("User", event.getUserId().getSurname() + " "
                            + event.getUserId().getName().substring(0, 1) + ".");
                }
                return jsonBuilder.build();
            }
            return null;
        }
        catch (Exception exc)
        {
            log.warn("Getting users from DB exception", exc);
            return null;
        }
    }
}
