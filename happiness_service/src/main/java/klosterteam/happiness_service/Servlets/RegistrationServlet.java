package klosterteam.happiness_service.Servlets;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Stanislav on 07.12.2016.
 * stas33553377@yandex.ru
 */
public class RegistrationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if("init".equals(request.getParameter("message"))){
            System.out.println("DEBUG | RegistrationServlet ---> processRequest() ---> login in "+request.getParameter("email"));

            Cookie cookie_role = new Cookie("role", "admin");
            Cookie cookie_email = new Cookie("email", request.getParameter("email"));
            JsonObject json = Json.createObjectBuilder().add("message", "success").build();
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
            response.addCookie(cookie_role);
            response.addCookie(cookie_email);
            return;

        }
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
