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
import java.util.List;
import klosterteam.happiness_service.HappyHibernate;
import klosterteam.hibernate.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Stanislav on 07.12.2016.
 * stas33553377@yandex.ru
 */
public class RegistrationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logger log = LogManager.getLogger(RegistrationServlet.class);
        if("init".equals(request.getParameter("message"))){
            log.debug("RegistrationServlet ---> processRequest() ---> login in "+request.getParameter("email"));
            
            HappyHibernate hHibernate = new HappyHibernate();
            List<Users> users = hHibernate.selectUsersByEmail(request.getParameter("email"));
            if (users != null && !users.isEmpty())
            {
                Users user = users.get(0);
                if (hHibernate.authUser(request.getParameter("email"), request.getParameter("pw")) == 0)
                {
                    Cookie cookie_role = new Cookie("role", user.getRoleId().getRole());
                    Cookie cookie_email = new Cookie("email", request.getParameter("email"));
                    JsonObject json = Json.createObjectBuilder().add("message", "success").build();
                    response.setContentType("application/json");
                    response.getWriter().write(json.toString());
                    response.addCookie(cookie_role);
                    response.addCookie(cookie_email);
                }
                else
                {
                    JsonObject json = Json.createObjectBuilder().add("message", "fail").build();
                    response.setContentType("application/json");
                    response.getWriter().write(json.toString());
                }
            }
            else
            {
                JsonObject json = Json.createObjectBuilder().add("message", "user not found").build();
                response.setContentType("application/json");
                response.getWriter().write(json.toString());
            }
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
