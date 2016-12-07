/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klosterteam.happiness_service;

import java.util.ArrayList;
import klosterteam.hibernate.Events;
import klosterteam.hibernate.Users;
import klosterteam.hibernate.Event_types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import klosterteam.hibernate.Categories;
import klosterteam.hibernate.Departments;
import klosterteam.hibernate.Gift_history;
import klosterteam.hibernate.Gifts;
import klosterteam.hibernate.Logins;
import klosterteam.hibernate.Money;
import klosterteam.hibernate.Preferences;
import klosterteam.hibernate.Roles;
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
                    throw new NullPointerException();
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
                    throw new NullPointerException();
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
                throw new NullPointerException();
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
                throw new NullPointerException();
            gift = (Gifts)session.get(Gifts.class, gift.getId());
            if (gift == null)
                throw new NullPointerException();
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
    
    public Preferences createPreferences(Users user, Categories cat)
    {
        try
        {
            session.beginTransaction();
            user = (Users)session.get(Users.class, user.getId());
            if (user == null)
                throw new NullPointerException();
            cat = (Categories)session.get(Categories.class, cat.getId());
            if (cat == null)
                throw new NullPointerException();
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
                throw new NullPointerException();
            session.beginTransaction();
            vote = (Vote)session.get(Vote.class, vote.getId());
            if (vote == null)
                throw new NullPointerException();
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
                throw new NullPointerException();
            gift = (Gifts)session.get(Gifts.class, gift.getId());
            if (gift == null)
                throw new NullPointerException();
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
                throw new NullPointerException();
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
                throw new NullPointerException();
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
                throw new NullPointerException();
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
                throw new NullPointerException();
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
                throw new NullPointerException();
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
                throw new NullPointerException();
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
                throw new NullPointerException();
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
                throw new NullPointerException();
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
                throw new NullPointerException();
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
                throw new NullPointerException();
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
                throw new NullPointerException();
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
                throw new NullPointerException();
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
    
    public Users changeUserSurname(Users user, String surname)
    {
        try
        {
            session.beginTransaction();
            user = (Users)session.get(Users.class, user.getId());
            if (user == null)
                throw new NullPointerException();
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
                throw new NullPointerException();
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
                throw new NullPointerException();
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
                throw new NullPointerException();
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
                throw new NullPointerException();
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
                throw new NullPointerException();
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
                throw new NullPointerException();
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
                throw new NullPointerException();
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
                throw new NullPointerException();
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
                throw new NullPointerException();
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
                throw new NullPointerException();
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
                throw new NullPointerException();
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
                throw new NullPointerException();
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
                throw new NullPointerException();
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
            vote = (Vote)session.get(Vote.class, vote.getHistId());
            if (vote == null)
                throw new NullPointerException();
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
    
    public Map<Categories, List<Categories>> selectPreferencesByUser(Users user)
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
            cr.add(Restrictions.eq("userId", user.getId()));
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
}

