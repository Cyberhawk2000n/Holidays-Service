package klosterteam.happiness_service.Servlets;

import javax.json.Json;
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
public class AboutMeProfileServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if("info".equals(request.getParameter("message"))){
            // read user info from db
            // json format:
            // jsonObject{
            //      "email":<>
            //      "pw":<>
            //      "date":<>
            //      "comment":<>
            // }
            System.out.println("DEBUG | AboutMeServlet ---> processRequest() ---> reading user info from db by ... eh cookies info) ");
            JsonObject json = Json.createObjectBuilder()
                    .add("email","test@test")
                    .add("pw","1235")
                    .add("date","16/09/2016")
                    .add("comment","this is test comment about myself hehe Xd").build();
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
            return;
        }
        else if("update".equals(request.getParameter("message"))){
            // update info in db
            // fields for request - email, pw, date, comment
            System.out.println("DEBUG | AboutMeServlet ---> processRequest() ---> updating user info ");
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
