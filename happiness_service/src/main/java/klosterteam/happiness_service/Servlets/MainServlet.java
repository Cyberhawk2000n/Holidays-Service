package klosterteam.happiness_service.Servlets;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Stanislav on 23.11.2016.
 * stas33553377@yandex.ru
 */
public class MainServlet extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if("info".equals(request.getParameter("message"))){
            // here we go to DB to get array of active events
            // json format:
            // jsonArray[
            // {date:"", name:""} , {date:"", name:""}
            // ]
            System.out.println("DEBUG | MainServlet ---> processRequest() ---> reading list of active events from DB");
            JsonArray json = Json.createArrayBuilder()
                    .add(Json.createObjectBuilder()
                        .add("date","16/09/2016")
                        .add("name","test event 1")
                        .add("id","1"))
                    .add(Json.createObjectBuilder()
                        .add("date","05/09/2016")
                        .add("name","test event 2")
                        .add("id","2"))
                    .build();
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
            return;
        }
        if("event".equals(request.getParameter("message"))){
            // here we go to DB for an event info using the Id
            JsonObject json = Json.createObjectBuilder()
                    .add("message", "eh. Loaded")
                    .build();
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(json.toString());
            return;
        }

        System.out.println("DEBUG | MainServlet ---> processRequest() --->  ");
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
}

