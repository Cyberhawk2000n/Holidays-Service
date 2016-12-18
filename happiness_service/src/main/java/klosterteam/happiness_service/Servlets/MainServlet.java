package klosterteam.happiness_service.Servlets;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import javax.json.JsonArrayBuilder;
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
        if("event".equals(request.getParameter("message"))){
            // here we go to DB for an event info using the Id
            // still in development
            //todo need to figure out which fields of event do we allow to change
            JsonObject json = Json.createObjectBuilder()
                    .add("message", "eh. Loaded")
                    .build();
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
    
}

