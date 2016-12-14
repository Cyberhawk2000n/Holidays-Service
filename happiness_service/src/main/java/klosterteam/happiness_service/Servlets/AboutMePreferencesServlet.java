package klosterteam.happiness_service.Servlets;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.json.JsonArrayBuilder;
import javax.servlet.http.Cookie;
import klosterteam.happiness_service.HappyHibernate;
import klosterteam.happiness_service.Pack;
import klosterteam.hibernate.Categories;
import klosterteam.hibernate.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Stanislav on 23.11.2016.
 * stas33553377@yandex.ru
 */
public class AboutMePreferencesServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logger log = LogManager.getLogger(AboutMePreferencesServlet.class);
        if("info".equals(request.getParameter("message"))){
            // read user info from db
            // json format:
            // jsonObject{
            //      { Cat_Name: cat1[ {test} , {test} ] },  { cat2[ {test} , {test} ] } ;

            log.debug("AboutMeServlet ---> processRequest() ---> reading preferences list ");
            JsonArray json = this.getCategoriesAndSubcategories();
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
            return;
        }
        else if("update".equals(request.getParameter("message"))){
            // update preferences in db
            // request has :
            // - "categories" - array of selected categories
            // - "sub_categories" - array of selected sub-categories
            // - "sub_count" - array of numbers to separate sub_categories.{3,5} - means first 3 sub-cats from first cat, then 5 from second
            log.debug("AboutMeServlet ---> processRequest() ---> updating user preferences ");
            JsonObject json;
            if (savePreferences(request) == 0)
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
    
    protected JsonArray getCategoriesAndSubcategories()
    {
        Logger log = LogManager.getLogger(EventsServlet.class);
        try
        {
            JsonArrayBuilder jsonBuilder = Json.createArrayBuilder();
            HappyHibernate hHibernate = new HappyHibernate();
            List<Pack> pack = hHibernate.getCategoriesAndSubcategories();
            if (pack.isEmpty())
                return null;
            for (int i = 0; i < pack.size(); i++)
            {
                String[] subcats;
                JsonArrayBuilder jsonSubCatsBuilder = Json.createArrayBuilder();
                subcats = pack.get(i).getNames();
                for (String subcat : subcats) {
                    jsonSubCatsBuilder.add(subcat);
                }
                jsonBuilder.add(Json.createObjectBuilder()
                        .add("Name", pack.get(i).getName())
                        .add("Subname", jsonSubCatsBuilder));
            }
            return jsonBuilder.build();
        }
        catch (Exception exc)
        {
            log.warn("Getting categories from DB exception", exc);
            return null;
        }
    }
    
    protected int savePreferences(HttpServletRequest request)
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
            else
            {
                String[] subCats = request.getParameterValues("sub_categories");
                String _subCats = request.getParameter("sub_categories");
                log.warn("!!!!!!!!!!!!!!!!!!!!!!!\n\n" + _subCats);
                for (int i = 0; i < subCats.length; i++)
                {
                    Categories catId = hHibernate.selectCategoryByName(subCats[i]).get(0);
                    hHibernate.addPreferences(userId, catId);
                }
                return 0;
            }
        }
        catch (Exception exc)
        {
            log.warn("Save to DB exception!", exc);
            return -1;
        }
    }
}
