package klosterteam.happiness_service.Servlets;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.Cookie;
import klosterteam.happiness_service.HappyHibernate;
import klosterteam.hibernate.Events;
import klosterteam.hibernate.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Stanislav on 23.11.2016.
 * stas33553377@yandex.ru
 */
public class MainServlet extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logger log = LogManager.getLogger(MainServlet.class);
        if("info".equals(request.getParameter("message"))){
            // here we go to DB to get array of active events
            // json format:
            // jsonArray[
            // {date:"", name:""} , {date:"", name:""}
            // ]
            
            log.debug("MainServlet ---> processRequest() ---> reading list of active events from DB");
            JsonArray json = getCurrentEvents();
            /*Json.createArrayBuilder()
                    .add(Json.createObjectBuilder()
                        .add("date","16/09/2016")
                        .add("name","test event 1")
                        .add("id","1"))
                    .add(Json.createObjectBuilder()
                        .add("date","05/09/2016")
                        .add("name","test event 2")
                        .add("id","2"))
                    .build();*/
            if (json != null)
            {
                response.setContentType("application/json");
                response.getWriter().write(json.toString());
            }
            return;
        }
        if("refresh".equals(request.getParameter("message"))){
            // here we go to DB to get array of active events
            // json format:
            // jsonArray[
            // {date:"", name:""} , {date:"", name:""}
            // ]
            
            log.debug("MainServlet ---> processRequest() ---> reading list of active events on date from DB");
            JsonArray json = this.getCurrentEventsByDate(request);
            response.setContentType("application/json");
            if (json != null)
            {
                response.getWriter().write(json.toString());
            }
            else
            {
                response.getWriter().write(Json.createArrayBuilder().build().toString());
            }
            return;
        }
        if("event".equals(request.getParameter("message"))){
            // here we go to DB for an event info using the Id
            // still in development
            //todo need to figure out which fields of event do we allow to change
            JsonObject json = this.getEventById(request);
            response.setContentType("text/html;charset=UTF-8");
            if (json != null)
                response.getWriter().write(json.toString());
            else
                response.getWriter().write(Json.createObjectBuilder().build().toString());
            return;
        }
        if("del_event".equals(request.getParameter("message"))){
            // here we go to DB for an event info using the Id
            // still in development
            //todo need to figure out which fields of event do we allow to change
            log.debug("MainServlet ---> processRequest() ---> delete event from DB");
            JsonObject json;
            JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
            if (this.deleteEvent(request) >= 0)
            {
                json = jsonBuilder.add("message", "Deleted")
                    .build();
            }
            else
            {
                json = jsonBuilder.add("message", "NOT Deleted!")
                    .build();
            }
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(json.toString());
            return;
        }
        log.debug("MainServlet ---> processRequest() --->  ");
        // If we didn't entered any case of
        JsonObject json = Json.createObjectBuilder()
                .add("message", "Error 400: Bad request")
                .build();
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json.toString());
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
    
    protected JsonArray getCurrentEvents()
    {
        Logger log = LogManager.getLogger(EventsServlet.class);
        try
        {
            JsonArrayBuilder jsonBuilder = Json.createArrayBuilder();
            HappyHibernate hHibernate = new HappyHibernate();
            List<Events> list = hHibernate.selectEventsByActive(true);
            if (list.isEmpty())
                return jsonBuilder.build();
            for (int i = 0; i < list.size(); i++)
            {
                Calendar date = Calendar.getInstance();
                date.setTime(list.get(i).getDate());
                StringBuilder sb = new StringBuilder();
                sb.append(date.get(Calendar.DATE))
                        .append("/")
                        .append(date.get(Calendar.MONTH) + 1)
                        .append("/")
                        .append(date.get(Calendar.YEAR));
                jsonBuilder.add(Json.createObjectBuilder().add("date", sb.toString())
                        .add("name", list.get(i).getName())
                        .add("id", list.get(i).getId()));
            }
            return jsonBuilder.build();
        }
        catch (Exception exc)
        {
            log.warn("Getting events from DB exception", exc);
            return null;
        }
    }
    
    protected JsonArray getCurrentEventsByDate(HttpServletRequest request)
    {
        Logger log = LogManager.getLogger(EventsServlet.class);
        try
        {
            JsonArrayBuilder jsonBuilder = Json.createArrayBuilder();
            HappyHibernate hHibernate = new HappyHibernate();
            Calendar specDate = Calendar.getInstance();
            if (request.getParameter("date") != null)
            {
                String[] dateString = request.getParameter("date").split("/");
                specDate.set(Calendar.DATE, Integer.valueOf(dateString[1]));
                specDate.set(Calendar.MONTH, Integer.valueOf(dateString[0]) - 1);
                specDate.set(Calendar.YEAR, Integer.valueOf(dateString[2]));
            }
            List<Events> list = hHibernate.selectEventsByActiveDate(specDate.getTime());
            if (list.isEmpty())
                return jsonBuilder.build();
            for (int i = 0; i < list.size(); i++)
            {
                
                Calendar date = Calendar.getInstance();
                date.setTime(list.get(i).getDate());
                StringBuilder sb = new StringBuilder();
                sb.append(date.get(Calendar.DATE))
                        .append("/")
                        .append(date.get(Calendar.MONTH) + 1)
                        .append("/")
                        .append(date.get(Calendar.YEAR));
                jsonBuilder.add(Json.createObjectBuilder().add("date", sb.toString())
                        .add("name", list.get(i).getName())
                        .add("id", list.get(i).getId()));
            }
            return jsonBuilder.build();
        }
        catch (Exception exc)
        {
            log.warn("Getting events from DB exception", exc);
            return null;
        }
    }
    
    protected JsonObject getEventById(HttpServletRequest request)
    {
        Logger log = LogManager.getLogger(EventsServlet.class);
        try
        {
            Events event = null;
            JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
            HappyHibernate hHibernate = new HappyHibernate();
            if (request.getParameter("id") != null)
                event = hHibernate.selectEventById(Long.valueOf(request.getParameter("id")));
            if (event == null)
                return jsonBuilder.build();
            else
            {
                Calendar date = Calendar.getInstance();
                date.setTime(event.getDate());
                StringBuilder sb = new StringBuilder();
                sb.append(date.get(Calendar.MONTH) + 1)
                        .append("/")
                        .append(date.get(Calendar.DATE))
                        .append("/")
                        .append(date.get(Calendar.YEAR));
                long user = -1;
                if (event.getUserId() != null)
                    user = event.getUserId().getId();
                jsonBuilder.add("name", event.getName())
                    .add("type", event.getTypeId().getName())
                    .add("date", sb.toString())
                    .add("user", user)
                    .add("manager", event.getManagerId().getId())
                    .add("template", event.getTemplate());
                return jsonBuilder.build();
            }
        }
        catch (Exception exc)
        {
            log.warn("Save to DB exception!", exc);
            return null;
        }
    }
    
    protected int deleteEvent(HttpServletRequest request)
    {
        Logger log = LogManager.getLogger(EventsServlet.class);
        try
        {
            Events event = null;
            HappyHibernate hHibernate = new HappyHibernate();
            if (request.getParameter("id") != null)
                event = hHibernate.selectEventById(Long.valueOf(request.getParameter("id")));
            if (event == null)
                return 0;
            else
            {
                log.warn("\n\n" + event.getName() + "\n\n");
                //hHibernate.deleteEvent(event);
                return 1;
            }
        }
        catch (Exception exc)
        {
            log.warn("Save to DB exception!", exc);
            return -1;
        }
    }
}

