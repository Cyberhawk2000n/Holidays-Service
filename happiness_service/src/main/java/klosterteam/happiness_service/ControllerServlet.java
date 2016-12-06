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
import klosterteam.hibernate.Logins;
import klosterteam.hibernate.Preferences;
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
                hHibernate = new HappyHibernate();
                /*hHibernate.createEventType("Birthday");
                hHibernate.createEventType("Corporate");
                hHibernate.createEventType("Adding to the family");*/
                /*hHibernate.createCategory("Devices", null);
                Categories cat = hHibernate.createCategory("Vehicle", null);
                hHibernate.createCategory("Motorcycle", cat);
                hHibernate.createCategory("Other", null);
                hHibernate.createCategory("Smartphone", cat);*/
                //Categories cat = (Categories)hHibernate.selectCategoryByName("Others").get(0);
                //hHibernate.createCategory("Others", cat);
                //cat = (Categories)hHibernate.selectCategoryByName("Smartphone").get(0);
                //Users user = (Users)hHibernate.selectUsersByEmail("nochds@gmail.com").get(0);
                //Preferences prefs = (Preferences)hHibernate.createPreferences(user, cat);
                //cat = (Categories)hHibernate.selectCategoryByName("Laptop").get(0);
                //hHibernate.createPreferences(user, cat);
                //hHibernate.createRole("admin");
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
                Map<Categories, List<Categories>> map = hHibernate.selectCategoriesAndSubCategories();
                Set set = map.keySet();
                for (Iterator i = set.iterator(); i.hasNext();)
                {
                    Categories cat = (Categories)i.next();
                    out.println("<h1>Category " + i + " (" + cat.getName() + ") contains:</h1>");
                    List<Categories> list = map.get(cat);
                    for (int j = 0; j < list.size(); j++)
                    {
                        out.println("<h5> -> " + list.get(j).getName() + "</h5>");
                    }
                }
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
            log.debug("ControllerServet exception!", exc);
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
            String str;// = "Nochevnoy;Dmitriy;Sergeevich;26.12.1995;1;Dep1;nochds3@gmail.com\n";
            //fos.write(str);
            str = "Nochevnoy;Dmitriy;;7.12.1995;4;Dep4;nochds2@gmail.com\n";
            fos.write(str);
            fos.close();
            BufferedReader csv = new BufferedReader(new FileReader("in.csv"));
            while ((line = csv.readLine()) != null)
            {
                columns = line.split(csvSplitBy);
                for (int i = 0; i < columns.length; i++)
                {
                    log.warn(columns[i] + "\n\n");
                }
                rows.put(columns[columns.length - 1], columns);
            }
            List<Users> users = hHibernate.selectUsers();
            //log.warn("SIZE!!!!!!!!\n" + users.size());
            for (int i = 0; i < users.size(); i++)
            {
                if (!rows.containsKey(users.get(i).getEmail()))
                {
                    //hHibernate.deleteLogin(users.get(i));
                    /*hHibernate.deletePreferences(users.get(i));
                    hHibernate.deleteUser(users.get(i));*/
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
            Roles role = hHibernate.selectRoles("user").get(0);
            for (Iterator i = set.iterator(); i.hasNext();)
            {
                String email = (String)i.next();
                if (hHibernate.selectUsersByEmail(email).isEmpty())
                {
                    Departments depId = (Departments)hHibernate.selectDepartments(rows.get(email)[5]).get(0);
                    String[] dayMonthYear = rows.get(email)[3].split("\\.");
                    log.warn("day!!!!!!!!\n" + dayMonthYear[0]);
                    log.warn("Month!!!!!!!!\n" + dayMonthYear[1]);
                    log.warn("Year!!!!!!!!\n" + dayMonthYear[2]);
                    log.debug("\n " + rows.get(email)[3] + " ::: " + dayMonthYear.length + " \n");
                    Users user = hHibernate.createUser(rows.get(email)[1], rows.get(email)[0], rows.get(email)[2],
                            new Date(Integer.valueOf(dayMonthYear[0]), Integer.valueOf(dayMonthYear[1]),
                                    Integer.valueOf(dayMonthYear[2])),
                            role, email, "about", depId, true);
                    hHibernate.createLogin(user, user.getEmail(), user.getEmail());
                    Event_types typeId = hHibernate.selectEventTypes("Birthday").get(0);
                    hHibernate.createEvent("Birthday of " + user.getEmail(), user.getBirthday(),
                            true, typeId, user, false, "template", null);
                    SchedulerFactory sf = new StdSchedulerFactory();
                    Scheduler sche = sf.getScheduler();
                    sche.start();
                    JobDetail job = newJob(DoEventActiveJob.class)
                    .withIdentity("job1", "group1")
                    .build();
                    CronTrigger cron = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .withSchedule(cronSchedule("0/5 * * * * ?"))
                    .forJob("job1", "group1")
                    .build();
                    sche.scheduleJob(job, cron);
                    //sendMessage(); login, password
                    
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
