package klosterteam.happiness_service.Servlets;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.servlet.http.Cookie;
import klosterteam.happiness_service.HappyHibernate;
import klosterteam.hibernate.Logins;
import klosterteam.hibernate.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Stanislav on 23.11.2016.
 * stas33553377@yandex.ru
 */
public class AboutMeProfileServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logger log = LogManager.getLogger(AboutMeProfileServlet.class);
        if("info".equals(request.getParameter("message"))){
            // read user info from db
            // json format:
            // jsonObject{
            //      "email":<>
            //      "pw":<>
            //      "date":<>
            //      "comment":<>
            // }
            log.debug("AboutMeServlet ---> processRequest() ---> reading user info from db by ... eh cookies info) ");
            Users userId = null;
            HappyHibernate hHibernate = new HappyHibernate();
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie: cookies)
                if ("email".equals(cookie.getName()))
                {
                    userId = hHibernate.selectUsersByEmail(cookie.getValue()).get(0);
                }
            if (userId != null)
            {
                Logins login = hHibernate.selectLoginByUser(userId);
                Calendar date = Calendar.getInstance();
                date.setTime(userId.getBirthday());
                StringBuilder sb = new StringBuilder();
                sb.append(date.get(Calendar.MONTH) + 1)
                        .append("/")
                        .append(date.get(Calendar.DATE))
                        .append("/")
                        .append(date.get(Calendar.YEAR));
                JsonObject json = Json.createObjectBuilder()
                        .add("email", login.getLogin())
                        .add("pw", "")
                        .add("date", sb.toString())
                        .add("marked", !userId.isGiveGift())
                        .add("comment", userId.getAbout()).build();
                log.warn("\n\n" + !userId.isGiveGift());
                response.setContentType("application/json");
                response.getWriter().write(json.toString());
            }
            return;
        }
        else if("update".equals(request.getParameter("message"))){
            // update info in db
            // fields for request - email, pw, date, comment
            log.debug("AboutMeServlet ---> processRequest() ---> updating user info ");
            JsonObject json;
            if (editUser(request) == 0)
            {
                json = Json.createObjectBuilder()
                        .add("message", "Success on updating user =) kinda lel")
                        .build();
            }
            else
            {
                json = Json.createObjectBuilder()
                        .add("message", "Fail on updating user")
                        .build();
            }
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
            return;
        }
        else if("mark".equals(request.getParameter("message"))){
            // update info in db
            // fields for request - email, pw, date, comment
            log.debug("AboutMeServlet ---> processRequest() ---> don't give a gift");
            JsonObject json = Json.createObjectBuilder()
                    .add("message", "success")
                    .build();
            this.editUserGiveGift(request, false);
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
            return;
        }
        else if("unmark".equals(request.getParameter("message"))){
            // update info in db
            // fields for request - email, pw, date, comment
            log.debug("AboutMeServlet ---> processRequest() ---> give a gift");
            JsonObject json = Json.createObjectBuilder()
                    .add("message", "success")
                    .build();
            this.editUserGiveGift(request, true);
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
            return;
        }
        log.debug("AboutMeServlet ---> processRequest() ---> wrong Paramenter sending 400 HTTP code ");
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
    
    protected int editUser(HttpServletRequest request)
    {
        Logger log = LogManager.getLogger(EventsServlet.class);
        try
        {
            Users userId = null;
            HappyHibernate hHibernate = new HappyHibernate();
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie: cookies)
                if ("email".equals(cookie.getName()))
                {
                    userId = hHibernate.selectUsersByEmail(cookie.getValue()).get(0);
                }
            if (userId == null)
                return -1;
            {
                Logins login = hHibernate.selectLoginByUser(userId);
                String about = request.getParameter("comment");
                String passwd = request.getParameter("pw");
                String date = request.getParameter("date");
                hHibernate.changeUserAbout(userId, about);
                hHibernate.changeLoginPassword(login, passwd);
                String[] fields = date.split("/");
                if (fields.length == 3)
                {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.MONTH, Integer.valueOf(fields[0]) - 1);
                    calendar.set(Calendar.DATE, Integer.valueOf(fields[1]));
                    calendar.set(Calendar.YEAR, Integer.valueOf(fields[2]));
                    hHibernate.changeUserBirthday(userId, calendar.getTime());
                }
                return 0;
            }
        }
        catch (Exception exc)
        {
            log.warn("Getting users from DB exception", exc);
            return -1;
        }
    }
    
    protected int editUserGiveGift(HttpServletRequest request, boolean giveGift)
    {
        Logger log = LogManager.getLogger(EventsServlet.class);
        try
        {
            Users userId = null;
            log.warn("\n\nHERE!!!!!!!!!!!!!!!!!\n\n");
            HappyHibernate hHibernate = new HappyHibernate();
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie: cookies)
                if ("email".equals(cookie.getName()))
                {
                    userId = hHibernate.selectUsersByEmail(cookie.getValue()).get(0);
                }
            if (userId == null){
                log.warn("No user with such id");
                return -1;
            }
            else
            {
                log.warn("\n\n changing status for\n"+userId.getName()+"\n"+giveGift+"\n\n");
                hHibernate.changeUserGiveGift(userId, giveGift);
                return 0;
            }
        }
        catch (Exception exc)
        {
            log.warn("Setting users from DB exception", exc);
            return -1;
        }
    }
}
