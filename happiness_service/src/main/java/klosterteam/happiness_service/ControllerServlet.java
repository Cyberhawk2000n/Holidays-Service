/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klosterteam.happiness_service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import klosterteam.hibernate.Event_types;
import klosterteam.hibernate.Events;
import klosterteam.hibernate.Gifts;
import klosterteam.hibernate.History;
import klosterteam.hibernate.Roles;
import klosterteam.hibernate.Users;
import klosterteam.hibernate.Vote;
import org.hibernate.Session;

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
        //Logger log = new Logger();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            //request.setAttribute("a", "2");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            if (hHibernate == null)
            {
                hHibernate = new HappyHibernate();
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
                out.println("<h1>Servlet of " + /*hHibernate.selectVote(10).size() + */" is working!!!</h1>");
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
