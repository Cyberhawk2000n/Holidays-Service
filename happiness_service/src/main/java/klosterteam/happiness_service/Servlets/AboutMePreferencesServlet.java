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
public class AboutMePreferencesServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if("info".equals(request.getParameter("message"))){
            // we are reading user info from
            // json format:
            // jsonObject{
            //      { Cat_Name: cat1[ {test} , {test} ] },  { cat2[ {test} , {test} ] } ;

            System.out.println("DEBUG | AboutMeServlet ---> processRequest() ---> reading preferences list ");
            JsonArray json = Json.createArrayBuilder()
                    .add(Json.createObjectBuilder()
                        .add("Name","Cat1")
                        .add("Subname",Json.createArrayBuilder()
                            .add("sub-test1")
                            .add("sub-test2")))
                    .add(Json.createObjectBuilder()
                        .add("Name","Cat2")
                        .add("Subname",Json.createArrayBuilder()
                            .add("sub-test3")
                            .add("sub-test4")))
                    .build();

            response.setContentType("application/json");
            response.getWriter().write(json.toString());
            return;
        }
        else if("update".equals(request.getParameter("message"))){
            // we are updating preferences in db
            System.out.println("DEBUG | AboutMeServlet ---> processRequest() ---> updating user preferences ");
            JsonObject json = Json.createObjectBuilder()
                    .add("message", "Success on updating user =) kinda lel")
                    .build();
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
            return;
        }
        System.out.println("DEBUG | AboutMeServlet ---> processRequest() ---> wrong Paramenter sending 400 HTTP code ");
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
