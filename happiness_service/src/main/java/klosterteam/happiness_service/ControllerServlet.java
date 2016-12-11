/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klosterteam.happiness_service;

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
import klosterteam.hibernate.Events;
import klosterteam.hibernate.Gifts;
import klosterteam.hibernate.User_vote;
import klosterteam.hibernate.Users;
import klosterteam.hibernate.Vote;
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
        Logger log = LogManager.getLogger(ControllerServlet.class);
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
                
                /*Users user = hHibernate.selectUsersByEmail("nochds@gmail.com").get(0);
                Events event = hHibernate.selectEventsByUser(user).get(0);
                User_vote uVote = hHibernate.selectUserVotes(user, event).get(0);
                hHibernate.deleteUserVote(uVote);*/
                /*Gifts gift = hHibernate.selectGiftByName("Prestigio PAP3350");
                Vote vote = hHibernate.createVote(event, gift, 1);
                if (vote == null)
                {
                    vote = hHibernate.selectVote(event, gift);
                    User_vote uVote = hHibernate.createUserVote(user, event, vote);
                    hHibernate.changeVoteCount(vote, vote.getCount() + 1);
                }*/
                    
                //sendMessage("nochds@gmail.com", "Hello, now you are user of Happiness Service!");
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
                List<Pack> packs = hHibernate.getCategoriesAndSubcategories();
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
                else
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