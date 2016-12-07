package klosterteam.happiness_service.Servlets;

import java.io.*;
import javax.json.JsonArray;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.json.Json;
import javax.json.JsonObject;


/**
 * Created by Stanislav on 22.11.2016.
 * stas33553377@yandex.ru
 */
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        boolean cookie_init = false;
        Cookie[] cookies = request.getCookies();

        if(cookies!=null)
            for(Cookie c: cookies){
                if(c.getName()=="role")
                    cookie_init=true;
            }

        if(cookie_init == false){
            // get user role by his email
            System.out.println("Initialized cookies for user:"+" name");
            Cookie cookie = new Cookie("role","user");
            JsonObject json = Json.createObjectBuilder().add("message","success").build();
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
            response.addCookie(cookie);
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
