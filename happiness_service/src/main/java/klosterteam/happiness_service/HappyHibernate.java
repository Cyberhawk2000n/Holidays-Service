/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klosterteam.happiness_service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import klosterteam.hibernate.Events;
import klosterteam.hibernate.Users;
import klosterteam.hibernate.Event_types;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import klosterteam.hibernate.Categories;
import klosterteam.hibernate.Departments;
import klosterteam.hibernate.Gift_history;
import klosterteam.hibernate.Gifts;
import klosterteam.hibernate.Logins;
import klosterteam.hibernate.Money;
import klosterteam.hibernate.Passwd;
import klosterteam.hibernate.Preferences;
import klosterteam.hibernate.Roles;
import klosterteam.hibernate.User_vote;
import klosterteam.hibernate.Vote;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Cyberhawk
 */
public class HappyHibernate {
    Session session;
    Logger log;
    public HappyHibernate() {
        log = LogManager.getLogger(HappyHibernate.class);
        try
        {
            //session = HibernateSessionFactory.buildSessionFactory().openSession();
            session = HibernateSessionFactory.getSessionFactory().openSession();
            /*session.beginTransaction();
            Roles role = new Roles();
            role.setId(1);
            role.setRole("User");
            session.save(role);
            session.getTransaction().commit();
            session.beginTransaction();
            Users user = new Users();
            user.setId(1);
            user.setName("Dima");
            user.setSurname("One");
            Date dt = new Date();
            user.setBirthday(dt);
            user.setEmail("nochdssldjlskd");
            user.setRoleId(role);
            session.save(user);
            session.getTransaction().commit();
            System.out.println("close transaction");*/
            //session.close();
        }
        catch (Exception exc)
        {
            log.debug("Create session for database error!", exc);
        }
    }
    
    public Events createEvent(String name, Date date, boolean everyYear, Event_types typeId, Users userId,
            boolean active, String template, Users managerId)
    {
        try
        {
            session.beginTransaction();
            if (userId != null)
            {
                userId = (Users)session.get(Users.class, userId.getId());
                if (userId == null)
                    return null;
            }
            Events event = new Events();
            event.setName(name);
            event.setDate(date);
            event.setEveryYear(everyYear);
            event.setTypeId(typeId);
            event.setUserId(userId);
            event.setActive(active);
            event.setTemplate(template);
            event.setManagerId(managerId);
            session.save(event);
            session.getTransaction().commit();
            return event;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Event_types createEventType(String name)
    {
        try
        {
            session.beginTransaction();
            Event_types eventType = new Event_types();
            eventType.setName(name);
            session.save(eventType);
            session.getTransaction().commit();
            return eventType;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Roles createRole(String name)
    {
        try
        {
            session.beginTransaction();
            Roles role = new Roles();
            role.setRole(name);
            session.save(role);
            session.getTransaction().commit();
            return role;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Departments createDepartments(int id, String name)
    {
        try
        {
            session.beginTransaction();
            Departments dep = new Departments();
            dep.setId(id);
            dep.setName(name);
            session.save(dep);
            session.getTransaction().commit();
            return dep;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Departments createDepartments(int id, String name, Users manager)
    {
        try
        {
            session.beginTransaction();
            Departments dep = new Departments();
            dep.setId(id);
            dep.setName(name);
            dep.setManagerId(manager);
            session.save(dep);
            session.getTransaction().commit();
            return dep;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Categories createCategory(String name, Categories parent)
    {
        try
        {
            session.beginTransaction();
            if (parent != null)
            {
                parent = (Categories)session.get(Categories.class, parent.getId());
                if (parent == null)
                    return null;
            }
            Categories cat = new Categories();
            cat.setName(name);
            cat.setParentId(parent);
            session.save(cat);
            session.getTransaction().commit();
            return cat;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Gifts createGift(String name, Categories cat)
    {
        try
        {
            session.beginTransaction();
            cat = (Categories)session.get(Categories.class, cat.getId());
            if (cat == null)
                return null;
            Gifts gift = new Gifts();
            gift.setName(name);
            gift.setCatId(cat);
            session.save(gift);
            session.getTransaction().commit();
            return gift;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Gift_history createGiftHistory(Users user, Gifts gift)
    {
        try
        {
            session.beginTransaction();
            user = (Users)session.get(Users.class, user.getId());
            if (user == null)
                return null;
            gift = (Gifts)session.get(Gifts.class, gift.getId());
            if (gift == null)
                return null;
            Gift_history gHist = new Gift_history();
            gHist.setUserId(user);
            gHist.setGiftId(gift);
            session.save(gHist);
            session.getTransaction().commit();
            return gHist;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public User_vote createUserVote(Users user, Events event, Vote vote)
    {
        try
        {
            session.beginTransaction();
            user = (Users)session.get(Users.class, user.getId());
            if (user == null)
                return null;
            event = (Events)session.get(Events.class, event.getId());
            if (event == null)
                return null;
            vote = (Vote)session.get(Vote.class, vote.getId());
            if (vote == null)
                return null;
            if (!vote.getHistId().equals(event))
                return null;
            User_vote uVote = new User_vote();
            uVote.setUserId(user);
            uVote.setHistId(event);
            uVote.setVoteId(vote);
            session.save(uVote);
            session.getTransaction().commit();
            return uVote;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Preferences createPreferences(Users user, Categories cat)
    {
        try
        {
            session.beginTransaction();
            user = (Users)session.get(Users.class, user.getId());
            if (user == null)
                return null;
            cat = (Categories)session.get(Categories.class, cat.getId());
            if (cat == null)
                return null;
            Preferences prefs = new Preferences();
            prefs.setUserId(user);
            prefs.setCatId(cat);
            session.save(prefs);
            session.getTransaction().commit();
            return prefs;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Gift_history createGiftHistory(Vote vote)
    {
        try
        {
            if (vote == null)
                return null;
            session.beginTransaction();
            vote = (Vote)session.get(Vote.class, vote.getId());
            if (vote == null)
                return null;
            Gift_history gHist = new Gift_history();
            gHist.setUserId(vote.getHistId().getUserId());
            gHist.setGiftId(vote.getGiftId());
            session.save(gHist);
            session.getTransaction().commit();
            return gHist;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Vote createVote(Events event, Gifts gift, int count)
    {
        try
        {
            session.beginTransaction();
            event = (Events)session.get(Events.class, event.getId());
            if (event == null)
                return null;
            gift = (Gifts)session.get(Gifts.class, gift.getId());
            if (gift == null)
                return null;
            session.getTransaction().commit();
            if (this.selectVote(event, gift) != null)
                return null;
            session.beginTransaction();
            Vote vote = new Vote();
            vote.setHistId(event);
            vote.setGiftId(gift);
            vote.setCount(count);
            session.save(vote);
            session.getTransaction().commit();
            return vote;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Money createMoney(Events event, long money, long moneyMax)
    {
        try
        {
            session.beginTransaction();
            event = (Events)session.get(Events.class, event.getId());
            if (event == null)
                return null;
            Money collecting = new Money();
            collecting.setHistId(event);
            collecting.setMoney(money);
            collecting.setMoneyMax(moneyMax);
            session.save(collecting);
            session.getTransaction().commit();
            return collecting;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Users createUser(String name, String surname, String patronymic, Date birthday, Roles role,
            String email, String about, Departments depId, boolean giveGift)
    {
        try
        {
            session.beginTransaction();
            role = (Roles)session.get(Roles.class, role.getId());
            if (role == null)
                return null;
            Users user = new Users();
            user.setName(name);
            user.setSurname(surname);
            user.setPatronymic(patronymic);
            user.setBirthday(birthday);
            user.setRoleId(role);
            user.setEmail(email);
            user.setAbout(about);
            user.setDepId(depId);
            user.setGiveGift(giveGift);
            session.save(user);
            session.getTransaction().commit();
            return user;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Logins createLogin(Users user, String login, String password)
    {
        try
        {
            session.beginTransaction();
            user = (Users)session.get(Users.class, user.getId());
            if (user == null)
                return null;
            Logins userLog = new Logins();
            userLog.setUserId(user);
            userLog.setLogin(login);
            userLog.setPassword(DigestUtils.md5Hex(password));
            session.save(userLog);
            session.getTransaction().commit();
            return userLog;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Events changeEventName(Events event, String name)
    {
        try
        {
            session.beginTransaction();
            event = (Events)session.get(Events.class, event.getId());
            if (event == null)
                return null;
            event.setName(name);
            session.save(event);
            session.getTransaction().commit();
            return event;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Events changeEventDate(Events event, Date date)
    {
        try
        {
            session.beginTransaction();
            event = (Events)session.get(Events.class, event.getId());
            if (event == null)
                return null;
            event.setDate(date);
            session.save(event);
            session.getTransaction().commit();
            return event;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Events changeEventEveryYear(Events event, boolean everyYear)
    {
        try
        {
            session.beginTransaction();
            event = (Events)session.get(Events.class, event.getId());
            if (event == null)
                return null;
            event.setEveryYear(everyYear);
            session.save(event);
            session.getTransaction().commit();
            return event;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Events changeEventEventType(Events event, Event_types typeId)
    {
        try
        {
            session.beginTransaction();
            event = (Events)session.get(Events.class, event.getId());
            if (event == null)
                return null;
            event.setTypeId(typeId);
            session.save(event);
            session.getTransaction().commit();
            return event;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Events changeEventUser(Events event, Users userId)
    {
        try
        {
            session.beginTransaction();
            event = (Events)session.get(Events.class, event.getId());
            if (event == null)
                return null;
            event.setUserId(userId);
            session.save(event);
            session.getTransaction().commit();
            return event;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Events changeEventActive(Events hEvent, boolean active)
    {
        try
        {
            session.beginTransaction();
            hEvent = (Events)session.get(Events.class, hEvent.getId());
            if (hEvent == null)
                return null;
            hEvent.setActive(active);
            session.save(hEvent);
            session.getTransaction().commit();
            return hEvent;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Events changeEventTemplate(Events hEvent, String template)
    {
        try
        {
            session.beginTransaction();
            hEvent = (Events)session.get(Events.class, hEvent.getId());
            if (hEvent == null)
                return null;
            hEvent.setTemplate(template);
            session.save(hEvent);
            session.getTransaction().commit();
            return hEvent;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Events changeEventManager(Events hEvent, Users manager)
    {
        try
        {
            session.beginTransaction();
            hEvent = (Events)session.get(Events.class, hEvent.getId());
            if (hEvent == null)
                return null;
            hEvent.setManagerId(manager);
            session.save(hEvent);
            session.getTransaction().commit();
            return hEvent;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Users changeUserName(Users user, String name)
    {
        try
        {
            session.beginTransaction();
            user = (Users)session.get(Users.class, user.getId());
            if (user == null)
                return null;
            user.setName(name);
            session.save(user);
            session.getTransaction().commit();
            return user;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Logins changeLoginPassword(Logins login, String password)
    {
        try
        {
            session.beginTransaction();
            login = (Logins)session.get(Logins.class, login);
            if (login == null)
                return null;
            login.setPassword(DigestUtils.md5Hex(password));
            session.save(login);
            session.getTransaction().commit();
            return login;
        }
        catch (Exception exc)
        {
            log.debug("Editing database error!", exc);
            return null;
        }
    }
    
    public Users changeUserSurname(Users user, String surname)
    {
        try
        {
            session.beginTransaction();
            user = (Users)session.get(Users.class, user.getId());
            if (user == null)
                return null;
            user.setSurname(surname);
            session.save(user);
            session.getTransaction().commit();
            return user;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Users changeUserPatronymic(Users user, String patronymic)
    {
        try
        {
            session.beginTransaction();
            user = (Users)session.get(Users.class, user.getId());
            if (user == null)
                return null;
            user.setPatronymic(patronymic);
            session.save(user);
            session.getTransaction().commit();
            return user;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Users changeUserBirthday(Users user, Date birthday)
    {
        try
        {
            session.beginTransaction();
            user = (Users)session.get(Users.class, user.getId());
            if (user == null)
                return null;
            user.setBirthday(birthday);
            session.save(user);
            session.getTransaction().commit();
            return user;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Users changeUserRole(Users user, Roles role)
    {
        try
        {
            session.beginTransaction();
            user = (Users)session.get(Users.class, user.getId());
            if (user == null)
                return null;
            user.setRoleId(role);
            session.save(user);
            session.getTransaction().commit();
            return user;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Users changeUserDepartment(Users user, Departments depId)
    {
        try
        {
            session.beginTransaction();
            user = (Users)session.get(Users.class, user.getId());
            if (user == null)
                return null;
            user.setDepId(depId);
            session.save(user);
            session.getTransaction().commit();
            return user;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Users changeUserEmail(Users user, String email)
    {
        try
        {
            session.beginTransaction();
            user = (Users)session.get(Users.class, user.getId());
            if (user == null)
                return null;
            user.setEmail(email);
            session.save(user);
            session.getTransaction().commit();
            return user;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Users changeUserAbout(Users user, String about)
    {
        try
        {
            session.beginTransaction();
            user = (Users)session.get(Users.class, user.getId());
            if (user == null)
                return null;
            user.setAbout(about);
            session.save(user);
            session.getTransaction().commit();
            return user;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Users changeUserGiveGift(Users user, boolean giveGift)
    {
        try
        {
            session.beginTransaction();
            user = (Users)session.get(Users.class, user.getId());
            if (user == null)
                return null;
            user.setGiveGift(giveGift);
            session.save(user);
            session.getTransaction().commit();
            return user;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Logins changeUserLogin(Users user, String login)
    {
        try
        {
            session.beginTransaction();
            Logins userLog = (Logins)session.get(Logins.class, user.getId());
            if (userLog == null)
                return null;
            userLog.setLogin(login);
            session.save(userLog);
            session.getTransaction().commit();
            return userLog;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Logins changeUserPassword(Users user, String password)
    {
        try
        {
            session.beginTransaction();
            Logins userLog = (Logins)session.get(Logins.class, user.getId());
            if (userLog == null)
                return null;
            userLog.setPassword(DigestUtils.md5Hex(password));
            session.save(userLog);
            session.getTransaction().commit();
            return userLog;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Departments changeDepartmentManager(Departments dep, Users manager)
    {
        try
        {
            session.beginTransaction();
            Departments userLog = (Departments)session.get(Departments.class, dep.getId());
            if (userLog == null)
                return null;
            dep.setManagerId(manager);
            session.save(dep);
            session.getTransaction().commit();
            return dep;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Categories changeParentCategory(Categories cat, Categories parent)
    {
        try
        {
            session.beginTransaction();
            cat = (Categories)session.get(Categories.class, cat.getId());
            if (cat == null)
                return null;
            cat.setParentId(parent);
            session.save(cat);
            session.getTransaction().commit();
            return cat;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Money changeMoneyMoney(Money collecting, long money)
    {
        try
        {
            session.beginTransaction();
            collecting = (Money)session.get(Money.class, collecting.getHistId());
            if (collecting == null)
                return null;
            collecting.setMoney(money);
            session.save(collecting);
            session.getTransaction().commit();
            return collecting;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Money changeMoneyMoneyMax(Money collecting, long moneyMax)
    {
        try
        {
            session.beginTransaction();
            collecting = (Money)session.get(Money.class, collecting.getHistId());
            if (collecting == null)
                return null;
            collecting.setMoneyMax(moneyMax);
            session.save(collecting);
            session.getTransaction().commit();
            return collecting;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    public Vote changeVoteCount(Vote vote, int count)
    {
        try
        {
            session.beginTransaction();
            vote = (Vote)session.get(Vote.class, vote.getId());
            if (vote == null)
                return null;
            vote.setCount(count);
            session.save(vote);
            session.getTransaction().commit();
            return vote;
        }
        catch (Exception exc)
        {
            log.debug("Adding to database error!", exc);
            return null;
        }
    }
    
    /*public List<Events> selectGiftHistory(String name)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Gift_history.class)
                .setProjection(Projections.projectionList()
                  .add(Projections.property("histId"), name))
                .setResultTransformer(Transformers.aliasToBean(Gift_history.class));

            List<Events> list = cr.list();
            
            session.getTransaction().commit();
            return null;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }*/
    
    public List<Event_types> selectEventTypes(String name)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Event_types.class);
            cr.add(Restrictions.eq("name", name));
            List<Event_types> list = cr.list();
            session.getTransaction().commit();
            return list;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public List<Roles> selectRoles(String role)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Roles.class);
            cr.add(Restrictions.eq("role", role));
            List<Roles> list = cr.list();
            session.getTransaction().commit();
            return list;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public List<User_vote> selectUserVotes(Users userId, Events histId)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(User_vote.class);
            cr.add(Restrictions.eq("userId", userId));
            cr.add(Restrictions.eq("histId", histId));
            List<User_vote> list = cr.list();
            session.getTransaction().commit();
            return list;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public List<Departments> selectDepartments(String name)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Departments.class);
            cr.add(Restrictions.eq("name", name));
            List<Departments> list = cr.list();
            session.getTransaction().commit();
            return list;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public List<Vote> selectVoteByEvent(Events histId)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Vote.class);
            cr.add(Restrictions.eq("histId", histId));
            List<Vote> list = cr.list();
            session.getTransaction().commit();
            return list;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public Vote selectVote(Events histId, Gifts giftId)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Vote.class);
            cr.add(Restrictions.eq("histId", histId));
            cr.add(Restrictions.eq("giftId", giftId));
            List<Vote> list = cr.list();
            session.getTransaction().commit();
            if (list != null)
                return list.get(0);
            else
                return null;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public List<Users> selectUsers()
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Users.class);
            List<Users> list = cr.list();
            session.getTransaction().commit();
            return list;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public List<Categories> selectCategoryByName(String name)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Categories.class);
            cr.add(Restrictions.eq("name", name));
            List<Categories> list = cr.list();
            session.getTransaction().commit();
            return list;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public List<Users> selectUsersByEmail(String email)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Users.class);
            cr.add(Restrictions.eq("email", email));
            List<Users> list = cr.list();
            session.getTransaction().commit();
            return list;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public List<Users> selectUsersByRole(Roles role)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Users.class);
            cr.add(Restrictions.eq("roleId", role));
            List<Users> list = cr.list();
            session.getTransaction().commit();
            return list;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public List<Users> selectUsersByDepartment(Departments depId)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Users.class);
            cr.add(Restrictions.eq("depId", depId));
            List<Users> list = cr.list();
            session.getTransaction().commit();
            return list;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public Logins selectLoginByUser(Users userId)
    {
        try
        {
            session.beginTransaction();
            Logins login = (Logins)session.get(Logins.class, userId.getId());
            session.getTransaction().commit();
            return login;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public List<Events> selectEvents()
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Events.class);
            List<Events> list = cr.list();
            session.getTransaction().commit();
            return list;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public List<Events> selectEventsByUser(Users user)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Events.class);
            cr.add(Restrictions.eq("userId", user));
            List<Events> list = cr.list();
            session.getTransaction().commit();
            return list;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public List<Events> selectEventsByActive(boolean active)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Events.class);
            cr.add(Restrictions.eq("active", active));
            List<Events> list = cr.list();
            session.getTransaction().commit();
            return list;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public Gifts selectGiftByName(String name)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Gifts.class);
            cr.add(Restrictions.eq("name", name));
            List<Gifts> list = cr.list();
            session.getTransaction().commit();
            if (list != null)
                return list.get(0);
            else
                return null;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public List<Events> selectEventsByActiveDate(Date date)
    {
        try
        {
            session.beginTransaction();
            Date hi = new Date();
            hi.setTime(date.getTime() + 1209600000L); //2 weeks
            Date lo = new Date();
            lo.setTime(date.getTime() - 43200000L); // 12 hours
            Criteria cr = session.createCriteria(Events.class);
            cr.add(Restrictions.between("date", lo, hi));
            List<Events> list = cr.list();
            session.getTransaction().commit();
            return list;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public List<Events> selectEventsByUserAndType(Users user, Event_types type)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Events.class);
            cr.add(Restrictions.eq("user", user));
            cr.add(Restrictions.eq("typeId", type));
            List<Events> list = cr.list();
            session.getTransaction().commit();
            return list;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public List<Preferences> selectPreferencesByUser(Users userId)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Preferences.class);
            cr.add(Restrictions.eq("userId", userId));
            List<Preferences> list = cr.list();
            session.getTransaction().commit();
            if (list == null)
                return null;
            else
                return list;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public Preferences selectPreferencesByUserAndCat(Users userId, Categories catId)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Preferences.class);
            cr.add(Restrictions.eq("userId", userId));
            cr.add(Restrictions.eq("catId", catId));
            List<Preferences> list = cr.list();
            session.getTransaction().commit();
            if (list == null||list.isEmpty())
                return null;
            else
                return list.get(0);
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public List<Events> selectEventsByType(Event_types type)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Events.class);
            cr.add(Restrictions.eq("typeId", type));
            List<Events> list = cr.list();
            session.getTransaction().commit();
            return list;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public Events selectEventById(long id)
    {
        try
        {
            session.beginTransaction();
            Events event = (Events)session.get(Events.class, id);
            session.getTransaction().commit();
            return event;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public Users selectUserById(long id)
    {
        try
        {
            session.beginTransaction();
            Users user = (Users)session.get(Users.class, id);
            session.getTransaction().commit();
            return user;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public Map<Categories, List<Categories>> selectCategoriesAndSubCategories()
    {
        try
        {
            Map<Categories, List<Categories>> map = new HashMap<>();
            session.beginTransaction();
            Criteria cr = session.createCriteria(Categories.class);
            cr.add(Restrictions.isNotNull("parentId"));
            List<Categories> list = cr.list();
            session.getTransaction().commit();
            for (int i = 0; i < list.size(); i++)
            {
                session.beginTransaction();
                Categories tmpParent = (Categories)session.get(Categories.class, list.get(i).getParentId().getId());
                List<Categories> list2 = new ArrayList<>();
                if (map.containsKey(tmpParent))
                    list2 = map.get(tmpParent);
                list2.add(list.get(i));
                map.put(tmpParent, list2);
                session.getTransaction().commit();
            }
            return map;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public Map<Categories, List<Gifts>> selectCategoriesAndGifts()
    {
        try
        {
            Map<Categories, List<Gifts>> map = new HashMap<>();
            session.beginTransaction();
            Criteria cr = session.createCriteria(Gifts.class);
            List<Gifts> list = cr.list();
            session.getTransaction().commit();
            for (int i = 0; i < list.size(); i++)
            {
                session.beginTransaction();
                Categories tmpCat = (Categories)session.get(Categories.class, list.get(i).getCatId().getId());
                List<Gifts> list2 = new ArrayList<>();
                if (map.containsKey(tmpCat))
                    list2 = map.get(tmpCat);
                list2.add(list.get(i));
                map.put(tmpCat, list2);
                session.getTransaction().commit();
            }
            return map;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public Map<Categories, List<Categories>> selectMapPreferencesByUser(Users user)
    {
        try
        {
            Map<Categories, List<Categories>> map = new HashMap<>();
            session.beginTransaction();
            Criteria cr = session.createCriteria(Preferences.class);
            cr.add(Restrictions.eq("userId", user));
            List<Preferences> list = cr.list();
            session.getTransaction().commit();
            for (int i = 0; i < list.size(); i++)
            {
                session.beginTransaction();
                Categories tmpParent = (Categories)session.get(Categories.class,
                        list.get(i).getCatId().getParentId().getId());
                List<Categories> list2 = new ArrayList<>();
                if (map.containsKey(tmpParent))
                    list2 = map.get(tmpParent);
                list2.add(list.get(i).getCatId());
                map.put(tmpParent, list2);
                session.getTransaction().commit();
            }
            return map;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public List<Gift_history> selectGiftHistoryByUser(Users user)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Gift_history.class);
            cr.add(Restrictions.eq("userId", user));
            List<Gift_history> list = cr.list();
            session.getTransaction().commit();
            return list;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public int deleteUser(Users user)
    {
        try
        {
            session.beginTransaction();
            user = session.get(Users.class, user.getId());
            if (user != null)
                session.delete(user);
            session.getTransaction().commit();
            return 0;
        }
        catch (Exception exc)
        {
            log.debug("Deleting from database error!", exc);
            return -1;
        }
        
    }
    
    public int deleteLogin(Users user)
    {
        try
        {
            session.beginTransaction();
            Logins login = session.get(Logins.class, user.getId());
            if (login != null)
                session.delete(login);
            session.getTransaction().commit();
            return 0;
        }
        catch (Exception exc)
        {
            log.debug("Deleting from database error!", exc);
            return -1;
        }
        
    }
    
    public int deletePreferences(Users user)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Preferences.class);
            cr.add(Restrictions.eq("userId", user));
            List<Preferences> list = cr.list();
            for (int i = 0; i < list.size(); i++)
            {
                session.delete((Preferences)list.get(i));
            }
            session.getTransaction().commit();
            return 0;
        }
        catch (Exception exc)
        {
            log.debug("Deleting from database error!", exc);
            return -1;
        }
        
    }
    
    public int deletePreferencesByCategoryId(Users user, Categories catId)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Preferences.class);
            cr.add(Restrictions.eq("userId", user));
            cr.add(Restrictions.eq("catId", catId));
            List<Preferences> list = cr.list();
            for (int i = 0; i < list.size(); i++)
            {
                session.delete((Preferences)list.get(i));
            }
            session.getTransaction().commit();
            return 0;
        }
        catch (Exception exc)
        {
            log.debug("Deleting from database error!", exc);
            return -1;
        }
        
    }
    
    public int deleteGiftHistory(Gift_history giftHistory)
    {
        try
        {
            session.beginTransaction();
            giftHistory = session.get(Gift_history.class, giftHistory.getId());
            if (giftHistory != null)
                session.delete(giftHistory);
            session.getTransaction().commit();
            return 0;
        }
        catch (Exception exc)
        {
            log.debug("Deleting from database error!", exc);
            return -1;
        }
        
    }
    
    public int deleteUserVote(User_vote uVote)
    {
        try
        {
            session.beginTransaction();
            uVote = session.get(User_vote.class, uVote.getId());
            if (uVote != null)
                session.delete(uVote);
            session.getTransaction().commit();
            return 0;
        }
        catch (Exception exc)
        {
            log.debug("Deleting from database error!", exc);
            return -1;
        }
        
    }
    
    public int deleteEvent(Events event)
    {
        try
        {
            session.beginTransaction();
            event = session.get(Events.class, event.getId());
            if (event != null)
                session.delete(event);
            session.getTransaction().commit();
            return 0;
        }
        catch (Exception exc)
        {
            log.debug("Deleting from database error!", exc);
            return -1;
        }
        
    }
    
    public int deleteVote(Vote vote)
    {
        try
        {
            session.beginTransaction();
            vote = session.get(Vote.class, vote.getId());
            if (vote != null)
                session.delete(vote);
            session.getTransaction().commit();
            return 0;
        }
        catch (Exception exc)
        {
            log.debug("Deleting from database error!", exc);
            return -1;
        }
        
    }
    
    public int deleteMoney(Events event)
    {
        try
        {
            session.beginTransaction();
            Money money = session.get(Money.class, event.getId());
            if (money != null)
                session.delete(money);
            session.getTransaction().commit();
            return 0;
        }
        catch (Exception exc)
        {
            log.debug("Deleting from database error!", exc);
            return -1;
        }
        
    }
    
    public List<Pack> getCategoriesAndSubcategories() {
        Logger log = LogManager.getLogger(HappyHibernate.class);
        try
        {
            List<Pack> packs = new ArrayList<>();
            Map<Categories, List<Categories>> map = this.selectCategoriesAndSubCategories();
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
    
    public List<Pack> getUserPreferences(Users user) {
        Logger log = LogManager.getLogger(HappyHibernate.class);
        try
        {
            List<Pack> packs = new ArrayList<>();
            Map<Categories, List<Categories>> map = this.selectMapPreferencesByUser(user);
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
    
    public List<Pack> getCategoriesAndGifts() {
        Logger log = LogManager.getLogger(HappyHibernate.class);
        try
        {
            List<Pack> packs = new ArrayList<>();
            Map<Categories, List<Gifts>> map = this.selectCategoriesAndGifts();
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
    
    public Events createEvent(String name, Date eventDate, boolean everyYear, Event_types typeId,
            Users userId, String template, Users managerId)
    {
        Logger log = LogManager.getLogger(HappyHibernate.class);
        try
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(eventDate);

            Calendar date = Calendar.getInstance();
            date.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
            date.set(Calendar.DATE, calendar.get(Calendar.DATE));

            Calendar current = Calendar.getInstance();
            if (date.compareTo(current) > 0)
                calendar.set(Calendar.YEAR, current.get(Calendar.YEAR));
            else
                calendar.set(Calendar.YEAR, current.get(Calendar.YEAR) + 1);
            boolean active = false;
            long diff = calendar.getTimeInMillis() - (new Date()).getTime();
            if (diff > -43200000L && diff < 1209600000L)
                active = true;
            return this.createEvent(name, calendar.getTime(), everyYear, typeId, userId, active, template, managerId);
        }
        catch (Exception exc)
        {
            log.debug("Creating event exception!", exc);
            return null;
        }
    }
    
    public int editEvent(Events event, String name, Date date, boolean everyYear, Event_types typeId,
            Users userId, boolean active, String template, Users managerId)
    {
        Logger log = LogManager.getLogger(HappyHibernate.class);
        try
        {
            if (name != null)
                this.changeEventName(event, name);
            if (date != null)
                this.changeEventDate(event, date);
            this.changeEventEveryYear(event, everyYear);
            if (typeId != null)
                this.changeEventEventType(event, typeId);
            if (userId != null)
                this.changeEventUser(event, userId);
            this.changeEventActive(event, active);
            if (template != null)
                this.changeEventTemplate(event, template);
            if (managerId != null)
                this.changeEventManager(event, managerId);
            return 0;
        }
        catch (Exception exc)
        {
            log.debug("Editing event exception!", exc);
            return -1;
        }
    }
    
    public int editUser(Users user, String name, String surname, String patronymic, Date birthday, Roles role,
            String email, String about, Departments depId, boolean giveGift)
    {
        Logger log = LogManager.getLogger(HappyHibernate.class);
        try
        {
            if (name != null)
                this.changeUserName(user, name);
            if (surname != null)
                this.changeUserSurname(user, surname);
            this.changeUserPatronymic(user, patronymic);
            if (birthday != null)
                this.changeUserBirthday(user, birthday);
            if (role != null)
                this.changeUserRole(user, role);
            if (email != null)
                this.changeUserEmail(user, email);
            if (about != null)
                this.changeUserAbout(user, about);
            if (depId != null)
                this.changeUserDepartment(user, depId);
            this.changeUserGiveGift(user, giveGift);
            return 0;
        }
        catch (Exception exc)
        {
            log.debug("Editing user exception!", exc);
            return -1;
        }
    }
    
    public int authUser(String email, String password)
    {
        Logger log = LogManager.getLogger(HappyHibernate.class);
        try
        {
            Users user = this.selectUsersByEmail(email).get(0);
            Logins login = this.selectLoginByUser(user);
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
    
    public Gifts addGift(String name, Categories cat)
    {
        Logger log = LogManager.getLogger(HappyHibernate.class);
        try
        {
            if (this.selectGiftByName(name) == null)
            {
                return this.createGift(name, cat);
            }
            else
                return null;
        }
        catch (Exception exc)
        {
            log.debug("Editing gift exception!", exc);
            return null;
        }
    }
    
    public Vote addVote(Events event, Gifts gift)
    {
        Logger log = LogManager.getLogger(HappyHibernate.class);
        try
        {
            if (this.selectVote(event, gift) == null)
            {
                return this.createVote(event, gift, 0);
            }
            else
                return null;
        }
        catch (Exception exc)
        {
            log.debug("Editing vote exception!", exc);
            return null;
        }
    }
    
    public Preferences addPreferences(Users userId, Categories catId)
    {
        Logger log = LogManager.getLogger(HappyHibernate.class);
        try
        {
            if (this.selectPreferencesByUserAndCat(userId, catId) == null)
            {
                return this.createPreferences(userId, catId);
            }
            else
                return null;
        }
        catch (Exception exc)
        {
            log.debug("Editing vote exception!", exc);
            return null;
        }
    }
    
    public int updateMembers(FileInputStream csvInput) throws FileNotFoundException
    {
        Logger log = LogManager.getLogger(HappyHibernate.class);
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
            str = "Read;Jim;;12.12.1995;5;Dep5;cyberhawk2000n@gmail.com\n";
            fos.write(str);
            str = "King;John;Dunbar;5.1.1995;5;Dep5;nochds11@gmail.com\n";
            fos.write(str);
            str = "Reevs;Eujene;Olegovich;7.1.1995;5;Dep5;nochds22@gmail.com\n";
            fos.write(str);
            fos.close();
            BufferedReader csv = new BufferedReader(new FileReader("in.csv"));
            while ((line = csv.readLine()) != null)//while
            {
                columns = line.split(csvSplitBy);
                //for (int i = 0; i < columns.length; i++)
                //{
                //    log.warn(columns[i] + "\n\n");
                //}
                rows.put(columns[columns.length - 1], columns);
            }
            List<Users> users = this.selectUsers();
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
                    this.deleteUser(users.get(i));
                }
            }
            //add departments
            Set set = rows.keySet();
            for (Iterator i = set.iterator(); i.hasNext();)
            {
                String email = (String)i.next();
                if (this.selectDepartments(rows.get(email)[5]).isEmpty())
                    this.createDepartments(Integer.valueOf(rows.get(email)[4]), rows.get(email)[5]);
            }
            //add users and events
            List<Roles> roles = this.selectRoles("user");
            Roles role;
            if (roles != null)
            {
                role = roles.get(0);
                for (Iterator i = set.iterator(); i.hasNext();)
                {
                    String email = (String)i.next();
                    if (this.selectUsersByEmail(email).isEmpty())
                    {
                        Departments depId = (Departments)this.selectDepartments(rows.get(email)[5]).get(0);
                        String[] dayMonthYear = rows.get(email)[3].split("\\.");
                        Users user = this.createUser(rows.get(email)[1], rows.get(email)[0], rows.get(email)[2],
                                new Date(Integer.valueOf(dayMonthYear[2]) - 1900, Integer.valueOf(dayMonthYear[1]) - 1,
                                        Integer.valueOf(dayMonthYear[0])),
                                role, email, "about", depId, true);
                        this.createLogin(user, user.getEmail(), user.getEmail());
                        Event_types typeId = this.selectEventTypes("Birthday").get(0);
                        //Date eventDate = user.getBirthday();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(user.getBirthday());

                        Calendar date = Calendar.getInstance();
                        date.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
                        date.set(Calendar.DATE, calendar.get(Calendar.DATE));

                        Calendar current = Calendar.getInstance();
                        if (date.compareTo(current) > 0)
                            calendar.set(Calendar.YEAR, current.get(Calendar.YEAR));
                        else
                            calendar.set(Calendar.YEAR, current.get(Calendar.YEAR) + 1);
                        //eventDate.setYear((new Date()).getYear());
                        boolean active = false;
                        long diff = calendar.getTimeInMillis() - (new Date()).getTime();
                        if (diff > -43200000L && diff < 1209600000L)
                            active = true;
                        Events event = this.createEvent("Birthday of " + user.getEmail(), calendar.getTime(),
                                true, typeId, user, active, "template", null);
                        //this.createEventShedule(event, true);
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
    
    public int sendMessage(String email, String message)
    {
        Logger log = LogManager.getLogger(HappyHibernate.class);
        try
        {
            Passwd passwd = new Passwd((short)0, "");
            passwd = this.session.get(Passwd.class, passwd.getId());
            final String username = "optimusprime1024@gmail.com";
            final String password = passwd.getPasswd();
            Properties props = System.getProperties();
            props.load(getClass().getClassLoader().getResourceAsStream("mail.properties"));
            javax.mail.Session session1 = javax.mail.Session.getDefaultInstance(props, 
                          new Authenticator(){
                             protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username, password);
                             }});
            // -- Create a new message --
            Message msg = new MimeMessage(session1);
            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(Message.RecipientType.TO, 
                             InternetAddress.parse(email, false));
            msg.setSubject("Happiness Service!");
            msg.setText(message);
            msg.setSentDate(new Date());
            Transport.send(msg);
            return 0;
        }
        catch (Exception exc)
        {
            log.debug("Send e-mail error!", exc);
            return -1;
        }
    }
    
    public int createEventShedule(Events event, boolean isRepeatable)
    {
        Logger log = LogManager.getLogger(HappyHibernate.class);
        try
        {
            Map<String, Object> params = new HashMap<>();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(event.getDate());
            calendar.setTimeInMillis(calendar.getTimeInMillis() - 1209600000L);
            //if (calendar.compareTo(Calendar.getInstance()) < 0)
            //    ;
            String cronEx = "0 0 0 " + calendar.get(Calendar.DATE) + " "
                    + calendar.get(Calendar.MONTH) + " ?";/*"0 * * * * ?"; */
            /*if (calendar.getTimeInMillis() < Calendar.getInstance().getTimeInMillis())
                cronEx = "0 0 0 " + calendar.get(Calendar.DATE) + " "
                    + calendar.get(Calendar.MONTH) + " ?"
                    + (Calendar.getInstance().get(Calendar.YEAR) + 1);
            else
                cronEx = "0 0 0 " + calendar.get(Calendar.DATE) + " "
                    + calendar.get(Calendar.MONTH) + " ?"
                    + (Calendar.getInstance().get(Calendar.YEAR));*/
            params.put("type", "1"); //shedule do event active
            params.put("event", event);
            AppCronTrigger.createTrigger(params, cronEx, isRepeatable);
            calendar.setTime(event.getDate());
            calendar.setTimeInMillis(calendar.getTimeInMillis() - 259200000L);
            cronEx = "0 0 0 " + calendar.get(Calendar.DATE) + " "
                    + calendar.get(Calendar.MONTH) + " ?";/*cronEx = "20 * * * * ?";*/
            //params.remove("type");
            params.replace("type", "2"); //end of vote
            //params.put("event", event);
            AppCronTrigger.createTrigger(params, cronEx, isRepeatable);
            calendar.setTime(event.getDate());
            cronEx = "0 0 0 " + calendar.get(Calendar.DATE) + " "
                    + calendar.get(Calendar.MONTH) + " ?";/*cronEx = "40 * * * * ?";*/
            params.replace("type", "3"); //end of event and others (inactive)
            //params.put("event", event);
            AppCronTrigger.createTrigger(params, cronEx, isRepeatable);
            return 0;
        }
        catch (Exception exc)
        {
            log.debug("Send e-mail error!", exc);
            return -1;
        }
    }
}

