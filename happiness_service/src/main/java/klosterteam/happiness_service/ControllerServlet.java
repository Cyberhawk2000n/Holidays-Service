/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klosterteam.happiness_service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import klosterteam.hibernate.Event_types;
import klosterteam.hibernate.Events;
import klosterteam.hibernate.Gifts;
import klosterteam.hibernate.Roles;
import klosterteam.hibernate.Users;
import klosterteam.hibernate.Vote;
import org.hibernate.Session;
import java.util.Properties;
import java.util.Set;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import klosterteam.hibernate.Categories;
import klosterteam.hibernate.Departments;
import klosterteam.hibernate.Gift_history;
import klosterteam.hibernate.Logins;
import klosterteam.hibernate.Preferences;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import org.quartz.CronTrigger;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author Cyberhawk
 */
public class ControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    HappyHibernate hHibernate;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Logger log = LogManager.getLogger(HappyHibernate.class);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            //request.setAttribute("a", "2");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            //this.sendMessage("nochds@gmail.com", "I am dangerous!");
            
            //Users user = (Users)hHibernate.selectUsersByEmail("nochds@gmail.com").get(0);
            if (hHibernate == null)
            {
                //hHibernate = new HappyHibernate();
                //Users user = hHibernate.selectUserById(100);
                //hHibernate.deleteUser(user);
                /*SchedulerFactory sf = new StdSchedulerFactory();
                Scheduler sche = sf.getScheduler();
                sche.start();
                JobDetail job = newJob(DoEventActiveJob.class)
                .withIdentity("job", "group1")
                .usingJobData("email", user.getEmail())
                .usingJobData("eventId", event.getId())
                .build();
                //job.getJobDataMap().
                CronTrigger cron = newTrigger()
                .withIdentity("trigger", "group1")
                .withSchedule(cronSchedule("0/10 * * * * ?"))//"0 0 3 * * ?"
                .forJob("job", "group1")
                .build();
                sche.scheduleJob(job, cron);*/
                
                /*Users user = hHibernate.selectUsersByEmail("nochds@gmail.com").get(0);
                Events event = hHibernate.selectEventsByUser(user).get(0);
                hHibernate.createMoney(event, 0, 10000);
                Categories cat = hHibernate.selectCategoryByName("Laptop").get(0);
                Gifts gift = this.addGift("Asus K53SJ", cat);
                hHibernate.createVote(event, gift, 2);
                cat = hHibernate.selectCategoryByName("Smartphone").get(0);
                gift = this.addGift("Prestigio PAP3350", cat);
                Vote vote = hHibernate.createVote(event, gift, 5);
                hHibernate.createGiftHistory(vote);*/
                /*Users user = hHibernate.selectUsersByEmail("nochds@gmail.com").get(0);
                Events event = hHibernate.selectEventsByUser(user).get(0);
                Vote vote = hHibernate.selectVote(event).get(0);
                hHibernate.createGiftHistory(vote);*/
                /*hHibernate.createEventType("Birthday");
                hHibernate.createEventType("Corporate");
                hHibernate.createEventType("Adding to the family");*/
                
                /*Categories cat = hHibernate.createCategory("Vehicle", null);
                hHibernate.createCategory("Motorcycle", cat);
                cat = hHibernate.createCategory("Other", null);
                hHibernate.createCategory("Others", cat);
                cat = hHibernate.createCategory("Devices", null);
                hHibernate.createCategory("Smartphone", cat);
                hHibernate.createCategory("Laptop", cat);*/
                /*Categories cat = (Categories)hHibernate.selectCategoryByName("Others").get(0);
                cat = (Categories)hHibernate.selectCategoryByName("Smartphone").get(0);
                Users user = (Users)hHibernate.selectUsersByEmail("nochds@gmail.com").get(0);
                Preferences prefs = (Preferences)hHibernate.createPreferences(user, cat);
                cat = (Categories)hHibernate.selectCategoryByName("Laptop").get(0);
                hHibernate.createPreferences(user, cat);*/
                //hHibernate.createRole("admin");
                //hHibernate.createRole("user");
                //role = (Roles)hHibernate.selectRoles("user").get(0);
                //hHibernate.createDepartments(1, "Dep1");
                //hHibernate.createDepartments(2, "Dep2");
                //Departments dep = (Departments)hHibernate.selectDepartments("Dep1").get(0);
                //Users user = (Users)hHibernate.selectUsersByEmail("nochds@gmail.com").get(0);
                //hHibernate.createLogin(user, user.getEmail(), user.getEmail());
                //History hist = new History();
                //hist.setId(10);
                //Roles role = new Roles();
                //Roles role = hHibernate.selectRoles("Admin").get(0);
                //Users user = new Users();
                //user.setId(17);
                //hHibernate.createLogin(user, "milfguy1", "12345");
                //hHibernate.changeUserRole(user, role);
                //hHibernate.createVote(hist, new Gifts(), 10);
                //hHibernate.createVote(hist, new Gifts(), 20);
                //hHibernate.createVote(hist, new Gifts(), 30);
            }
            else
            {
                //updateMembers(null);
                //Map<Categories, List<Categories>> map = hHibernate.selectCategoriesAndSubCategories();
                //Set set = map.keySet();
                //Users user = hHibernate.selectUsersByEmail("nochds@gmail.com").get(0);
                //Events event = hHibernate.selectEventsByUser(user).get(0);
                
                /*List<Vote> vote = hHibernate.selectVote(event);
                if (vote != null)
                    for (int i = 0; i < vote.size(); i++)
                        log.warn("HIBERNATE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + vote.get(i).getGiftId().getName()
                                + ": "+ vote.get(i).getCount());*/
                /*List<Pack> packs = this.getCategoriesAndGifts();
                if (packs != null)
                    for (int i = 0; i < packs.size(); i++)
                    {
                        out.println("<h1>Category " + i + " (" + packs.get(i).getName() + ") contains:</h1>");
                        //log.warn("ACTIVE!!!!!!!!!!!!!!!!!!!!!: " + hHibernate.selectEventsByActiveDate(user.getBirthday()).get(0).getName());
                        //out.println("<h5> ::: " + packs.get(i).getNames().length + "</h5>");
                        for (String name : packs.get(i).getNames()) {
                            out.println("<h5> -> " + name + "</h5>");
                        }
                    }
                else*/
                    out.println("<h1>No categories!</h1>");
            }
            //((Logins)hHibernate.selectLoginsByUser(user).get(0)).getPassword()
            //Event_types typeId = new Event_types();
            //typeId.setName("HOLIDAY");
            /*Events event = new Events();
            event.setDate(new Date());
            event.setEveryYear(true);
            event.setName("holiday");
            event.setTypeId(typeId);
            History hist = new History();
            hist.setActive(false);
            hist.setDate(new Date());
            hist.setName("holiday2");
            hist.setEventId(event);
            Vote vote = new Vote();
            vote.setCount(10);
            vote.setGiftId(null);
            vote.setHistId(hist);*/
            //hHibernate.createEventType("HOLIDAY");
            /*hHibernate.createGiftHistory(vote);
            hHibernate.selectGiftHistory("holiday2");*/
            //if ("1".equals(request.getParameter("a")))
            //{
                /*HappyHibernate hh = new HappyHibernate();
                Event_types typeId = new Event_types();
                typeId.setId((short)0);
                typeId.setName("Birthday");
                Roles roleId = new Roles();
                roleId.setId((short)0);
                roleId.setRole("admin");
                Users userId = new Users();
                userId.setName("Dima");
                userId.setEmail("nochds");
                userId.setSurname("Surname");
                userId.setBirthday(new Date());
                userId.setRoleId(roleId);
                userId.setId(0);
                hh.createEvent("Birthday", new Date(), true, typeId, userId);*/
                //try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
                //    session.beginTransaction();
                //    Event_types typeId = new Event_types();
                //    typeId.setName("HOLIDAY");
                    /*Roles role = new Roles();
                    role.setId((short)2);
                    role.setRole("User2222");
                    session.save(role);
                    session.getTransaction().commit();
                    session.beginTransaction();
                    Users user = new Users();
                    user.setId(2);
                    user.setName("Dima");
                    user.setSurname("One");
                    Date dt = new Date();
                    user.setBirthday(dt);
                    user.setEmail("nochds");
                    user.setRoleId(role);*/
                /*    session.save(typeId);
                    session.getTransaction().commit();
                    System.out.println("close transaction");
                }
                catch (Exception exc)
                {
                    System.out.println(exc.getMessage());
                }*/
            /*}
            else if ("2".equals(request.getParameter("a")))
            {
                
            }*/
            out.println("</body>");
            out.println("</html>");
        }
        catch (Exception exc)
        {
            log.warn("ControllerServet exception!", exc);
        }
    }

    List<Pack> getCategoriesAndSubcategories() {
        Logger log = LogManager.getLogger(ControllerServlet.class);
        try
        {
            List<Pack> packs = new ArrayList<>();
            if (hHibernate == null)
                hHibernate = new HappyHibernate();
            Map<Categories, List<Categories>> map = hHibernate.selectCategoriesAndSubCategories();
            Set set = map.keySet();
            for (Iterator i = set.iterator(); i.hasNext();)
            {
                Pack pack = new Pack();
                Categories cat = (Categories)i.next();
                pack.setName(cat.getName());
                List<Categories> list = map.get(cat);
                String[] names = new String[list.size()];
                for (int j = 0; j < list.size(); j++)
                    names[j] = list.get(j).getName();
                pack.setNames(names);
                packs.add(pack);
            }
            return packs;
        }
        catch (Exception exc)
        {
            log.debug("Getting categories pack exception!", exc);
            return null;
        }
    }
    
    List<Pack> getUserPreferences(Users user) {
        Logger log = LogManager.getLogger(ControllerServlet.class);
        try
        {
            List<Pack> packs = new ArrayList<>();
            if (hHibernate == null)
                hHibernate = new HappyHibernate();
            Map<Categories, List<Categories>> map = hHibernate.selectPreferencesByUser(user);
            if (map == null)
                return null;
            Set set = map.keySet();
            for (Iterator i = set.iterator(); i.hasNext();)
            {
                Pack pack = new Pack();
                Categories cat = (Categories)i.next();
                pack.setName(cat.getName());
                List<Categories> list = map.get(cat);
                String[] names = new String[list.size()];
                for (int j = 0; j < list.size(); j++)
                    names[j] = list.get(j).getName();
                pack.setNames(names);
                packs.add(pack);
            }
            return packs;
        }
        catch (Exception exc)
        {
            log.debug("Getting preferences pack exception!", exc);
            return null;
        }
    }
    
    List<Pack> getCategoriesAndGifts() {
        Logger log = LogManager.getLogger(ControllerServlet.class);
        try
        {
            List<Pack> packs = new ArrayList<>();
            if (hHibernate == null)
                hHibernate = new HappyHibernate();
            Map<Categories, List<Gifts>> map = hHibernate.selectCategoriesAndGifts();
            Set set = map.keySet();
            for (Iterator i = set.iterator(); i.hasNext();)
            {
                Pack pack = new Pack();
                Categories cat = (Categories)i.next();
                pack.setName(cat.getName());
                List<Gifts> list = map.get(cat);
                String[] names = new String[list.size()];
                for (int j = 0; j < list.size(); j++)
                    names[j] = list.get(j).getName();
                pack.setNames(names);
                packs.add(pack);
            }
            return packs;
        }
        catch (Exception exc)
        {
            log.debug("Getting categories pack exception!", exc);
            return null;
        }
    }
    
    int createEvent(String name, Date date, boolean everyYear, Event_types typeId,
            Users userId, String template, Users managerId)
    {
        Logger log = LogManager.getLogger(ControllerServlet.class);
        try
        {
            boolean active = false;
            if (hHibernate == null)
                hHibernate = new HappyHibernate();
            long diff = date.getTime() - (new Date()).getTime();
            if (diff > -43200000L && diff < 1209600000L)
                active = true;
            hHibernate.createEvent(name, date, everyYear, typeId, userId, active, template, managerId);
            return 0;
        }
        catch (Exception exc)
        {
            log.debug("Editing event exception!", exc);
            return -1;
        }
    }
    
    int editEvent(Events event, String name, Date date, boolean everyYear, Event_types typeId,
            Users userId, boolean active, String template, Users managerId)
    {
        Logger log = LogManager.getLogger(ControllerServlet.class);
        try
        {
            if (hHibernate == null)
                hHibernate = new HappyHibernate();
            if (name != null)
                hHibernate.changeEventName(event, name);
            if (date != null)
                hHibernate.changeEventDate(event, date);
            hHibernate.changeEventEveryYear(event, everyYear);
            if (typeId != null)
                hHibernate.changeEventEventType(event, typeId);
            if (userId != null)
                hHibernate.changeEventUser(event, userId);
            hHibernate.changeEventActive(event, active);
            if (template != null)
                hHibernate.changeEventTemplate(event, template);
            if (managerId != null)
                hHibernate.changeEventManager(event, managerId);
            return 0;
        }
        catch (Exception exc)
        {
            log.debug("Editing event exception!", exc);
            return -1;
        }
    }
    
    int editUser(Users user, String name, String surname, String patronymic, Date birthday, Roles role,
            String email, String about, Departments depId, boolean giveGift)
    {
        Logger log = LogManager.getLogger(ControllerServlet.class);
        try
        {
            if (hHibernate == null)
                hHibernate = new HappyHibernate();
            if (name != null)
                hHibernate.changeUserName(user, name);
            if (surname != null)
                hHibernate.changeUserSurname(user, surname);
            hHibernate.changeUserPatronymic(user, patronymic);
            if (birthday != null)
                hHibernate.changeUserBirthday(user, birthday);
            if (role != null)
                hHibernate.changeUserRole(user, role);
            if (email != null)
                hHibernate.changeUserEmail(user, email);
            if (about != null)
                hHibernate.changeUserAbout(user, about);
            if (depId != null)
                hHibernate.changeUserDepartment(user, depId);
            hHibernate.changeUserGiveGift(user, giveGift);
            return 0;
        }
        catch (Exception exc)
        {
            log.debug("Editing user exception!", exc);
            return -1;
        }
    }
    
    int authUser(String email, String password)
    {
        Logger log = LogManager.getLogger(ControllerServlet.class);
        try
        {
            if (hHibernate == null)
                hHibernate = new HappyHibernate();
            Users user = hHibernate.selectUsersByEmail(email).get(0);
            Logins login = hHibernate.selectLoginByUser(user);
            if (login.getPassword().equals(DigestUtils.md5Hex(password)))
                return 0;
            else return -1;
        }
        catch (Exception exc)
        {
            log.debug("Checking password exception!", exc);
            return -1;
        }
    }
    
    Gifts addGift(String name, Categories cat)
    {
        Logger log = LogManager.getLogger(ControllerServlet.class);
        try
        {
            if (hHibernate == null)
                hHibernate = new HappyHibernate();
            if (hHibernate.selectGiftByName(name) == null)
            {
                return hHibernate.createGift(name, cat);
            }
            else
                return null;
        }
        catch (Exception exc)
        {
            log.debug("Editing user exception!", exc);
            return null;
        }
    }
    
    Vote addVote(Events event, Gifts gift)
    {
        Logger log = LogManager.getLogger(ControllerServlet.class);
        try
        {
            if (hHibernate == null)
                hHibernate = new HappyHibernate();
            if (hHibernate.selectVote(event, gift) == null)
            {
                return hHibernate.createVote(event, gift, 0);
            }
            else
                return null;
        }
        catch (Exception exc)
        {
            log.debug("Editing user exception!", exc);
            return null;
        }
    }
    
    int updateMembers(FileInputStream csvInput) throws FileNotFoundException
    {
        Logger log = LogManager.getLogger(ControllerServlet.class);
        try
        {
            String line = "";
            String csvSplitBy = ";";
            Map<String, String[]> rows = new HashMap<>();
            String[] columns;
            FileWriter fos = new FileWriter("in.csv");
            //fos.
            String str = "Nochevnoy;Dmitriy;Sergeevich;26.12.1995;1;Dep1;nochds@gmail.com\n";
            fos.write(str);
            str = "Nochevnoy;Dmitriy;;7.12.1995;5;Dep5;nochds2@gmail.com\n";
            fos.write(str);
            fos.close();
            BufferedReader csv = new BufferedReader(new FileReader("in.csv"));
            /*while ((line = csv.readLine()) != null)//while
            {
                columns = line.split(csvSplitBy);
                for (int i = 0; i < columns.length; i++)
                {
                    log.warn(columns[i] + "\n\n");
                }
                rows.put(columns[columns.length - 1], columns);
            }*/
            List<Users> users = hHibernate.selectUsers();
            for (int i = 0; i < users.size(); i++)
            {
                if (!rows.containsKey(users.get(i).getEmail()))
                {
                    //log.warn(users.get(i).getEmail() + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    //hHibernate.deleteLogin(users.get(i));
                    //hHibernate.deletePreferences(users.get(i));
                    /*List<Gift_history> gHist = hHibernate.selectGiftHistoryByUser(users.get(i));
                    if (gHist != null)
                        for (int j = 0; j < gHist.size(); j++)
                            hHibernate.deleteGiftHistory(gHist.get(i));*/
                    /*List<Event_types> evTypes;
                    Event_types typeId;
                    Events event;
                    if ((evTypes = hHibernate.selectEventTypes("Birthday")) != null)
                    {
                        log.warn("HERE!!!!!!!!\n");
                        typeId = evTypes.get(0);
                        if (typeId != null)
                        {
                            List<Events> events;
                            log.warn("HERE!!!!!!!!\n");
                            events = hHibernate.selectEventsByUserAndType(users.get(i), typeId);
                            if (events != null)
                            {
                                event = events.get(0);
                                log.warn("HERE2222!!!!!!!!\n");
                                List<Vote> votes;
                                if (event != null)
                                {
                                    votes = hHibernate.selectVote(event.getId());
                                    if (votes != null)
                                        for (int j = 0; j < votes.size(); j++)
                                            hHibernate.deleteVote(votes.get(i));
                                    hHibernate.deleteMoney(event);
                                    hHibernate.deleteEvent(event);
                                }
                            }
                        }
                    }*/
                    hHibernate.deleteUser(users.get(i));
                }
            }
            //add departments
            Set set = rows.keySet();
            for (Iterator i = set.iterator(); i.hasNext();)
            {
                String email = (String)i.next();
                if (hHibernate.selectDepartments(rows.get(email)[5]).isEmpty())
                    hHibernate.createDepartments(Integer.valueOf(rows.get(email)[4]), rows.get(email)[5]);
            }
            //add users and events
            List<Roles> roles = hHibernate.selectRoles("user");
            Roles role;
            if (roles != null)
            {
                role = roles.get(0);
                for (Iterator i = set.iterator(); i.hasNext();)
                {
                    String email = (String)i.next();
                    if (hHibernate.selectUsersByEmail(email).isEmpty())
                    {
                        Departments depId = (Departments)hHibernate.selectDepartments(rows.get(email)[5]).get(0);
                        String[] dayMonthYear = rows.get(email)[3].split("\\.");
                        /*log.warn("\nDay!!!!!!!!\n" + dayMonthYear[0] + ": " + Integer.valueOf(dayMonthYear[0]));
                        log.warn("\nMonth!!!!!!!!\n" + dayMonthYear[1] + ": " + Integer.valueOf(dayMonthYear[1]));
                        log.warn("\nYear!!!!!!!!\n" + dayMonthYear[2] + ": " + Integer.valueOf(dayMonthYear[2]));
                        log.debug("\n " + rows.get(email)[3] + " ::: " + dayMonthYear.length + " \n");*/
                        //Date date = new Date(Integer.valueOf(dayMonthYear[0]), );
                        Users user = hHibernate.createUser(rows.get(email)[1], rows.get(email)[0], rows.get(email)[2],
                                new Date(Integer.valueOf(dayMonthYear[2]) - 1900, Integer.valueOf(dayMonthYear[1]) - 1,
                                        Integer.valueOf(dayMonthYear[0])),
                                role, email, "about", depId, true);
                        hHibernate.createLogin(user, user.getEmail(), user.getEmail());
                        Event_types typeId = hHibernate.selectEventTypes("Birthday").get(0);
                        Date eventDate = user.getBirthday();
                        eventDate.setYear((new Date()).getYear());
                        boolean active = false;
                        long diff = eventDate.getTime() - (new Date()).getTime();
                        if (diff > -43200000L && diff < 1209600000L)
                            active = true;
                        Events event = hHibernate.createEvent("Birthday of " + user.getEmail(), eventDate,
                                true, typeId, user, active, "template", null);
                        
                        //sendMessage(); login, password

                    }
                }
            }
            log.warn("SUCCESS!!!!!!!!!!!!!!!!!!!!!!!");
        }
        catch (Exception exc)
        {
            log.warn("updateMembers error!", exc);
            return -1;
        }
        return 0;
    }
    
    int sendMessage(String email, String message)
    {
        Logger log = LogManager.getLogger(HappyHibernate.class);
        // Recipient's email ID needs to be mentioned.
        String to = email;

        // Sender's email ID needs to be mentioned
        String from = "nochds@gmail.com";

        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage mMessage = new MimeMessage(session);

            // Set From: header field of the header.
            mMessage.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            mMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            mMessage.setSubject("This is the Subject Line!");

            // Now set the actual message
            mMessage.setText(message);

            // Send message
            Transport.send(mMessage);
            log.debug("Sent message successfully....");
            return 0;
        }
        catch (Exception exc)
        {
            log.debug("Send e-mail error!", exc);
            return -1;
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
