/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klosterteam.happiness_service;

import klosterteam.hibernate.Events;
import klosterteam.hibernate.Users;
import klosterteam.hibernate.Event_types;
import java.util.Date;
import klosterteam.hibernate.Categories;
import klosterteam.hibernate.Gift_history;
import klosterteam.hibernate.Gifts;
import klosterteam.hibernate.History;
import klosterteam.hibernate.Logins;
import klosterteam.hibernate.Money;
import klosterteam.hibernate.Roles;
import klosterteam.hibernate.Vote;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

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
            session.close();
        }
        catch (Exception exc)
        {
            log.debug("Create session for database error!", exc);
        }
    }
    
    public void createEvent(String name, Date date, boolean everyYear, Event_types typeId, Users userId)
    {
        try
        {
            session.beginTransaction();
            Events event = new Events();
            event.setId(0);
            event.setName(name);
            event.setDate(date);
            event.setEveryYear(everyYear);
            event.setTypeId(typeId);
            event.setUserId(userId);
            session.save(event);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void createHistoryEvent(String name, Date date, boolean active, Events event)
    {
        try
        {
            session.beginTransaction();
            History hEvent = new History();
            hEvent.setId(0);
            hEvent.setName(name);
            hEvent.setDate(date);
            hEvent.setActive(active);
            hEvent.setEventId(event);
            session.save(hEvent);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void createEventType(String name)
    {
        try
        {
            session.beginTransaction();
            Event_types eventType = new Event_types();
            eventType.setId((short)0);
            eventType.setName(name);
            session.save(eventType);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void createRole(String name)
    {
        try
        {
            session.beginTransaction();
            Roles role = new Roles();
            role.setId((short)0);
            role.setRole(name);
            session.save(role);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void createCategory(String name, Categories parent)
    {
        try
        {
            session.beginTransaction();
            Categories cat = new Categories();
            cat.setId(0);
            cat.setName(name);
            cat.setParentId(parent);
            session.save(cat);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void createGift(String name, Categories cat)
    {
        try
        {
            session.beginTransaction();
            Gifts gift = new Gifts();
            gift.setId(0);
            gift.setName(name);
            gift.setCatId(cat);
            session.save(gift);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void createGiftHistory(History event, Gifts gift)
    {
        try
        {
            session.beginTransaction();
            Gift_history gHist = new Gift_history();
            gHist.setHistId(event);
            gHist.setGiftId(gift);
            session.save(gHist);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void createGiftHistory(Vote vote)
    {
        try
        {
            session.beginTransaction();
            Gift_history gHist = new Gift_history();
            gHist.setHistId(vote.getHistId());
            gHist.setGiftId(vote.getGiftId());
            session.save(gHist);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void createVote(History event, Gifts gift, int count)
    {
        try
        {
            session.beginTransaction();
            Vote vote = new Vote();
            vote.setHistId(event);
            vote.setGiftId(gift);
            vote.setCount(count);
            session.save(vote);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void createMoney(History event, long money, long moneyMax)
    {
        try
        {
            session.beginTransaction();
            Money collecting = new Money();
            collecting.setHistId(event);
            collecting.setMoney(money);
            collecting.setMoneyMax(moneyMax);
            session.save(collecting);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void createUser(String name, String surname, String patronymic, Date birthday, Roles role, String email, String about)
    {
        try
        {
            session.beginTransaction();
            Users user = new Users();
            user.setId(0);
            user.setName(name);
            user.setSurname(surname);
            user.setPatronymic(patronymic);
            user.setBirthday(birthday);
            user.setRoleId(role);
            user.setEmail(email);
            user.setAbout(about);
            session.save(user);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void createLogin(Users user, String login, String password)
    {
        try
        {
            session.beginTransaction();
            Logins userLog = new Logins();
            userLog.setUserId(user);
            userLog.setLogin(login);
            userLog.setPassword(password);
            session.save(user);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void changeEventName(Events event, String name)
    {
        try
        {
            session.beginTransaction();
            event = (Events)session.get(Events.class, event.getId());
            event.setName(name);
            session.save(event);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void changeEventDate(Events event, Date date)
    {
        try
        {
            session.beginTransaction();
            event = (Events)session.get(Events.class, event.getId());
            event.setDate(date);
            session.save(event);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void changeEventEveryYear(Events event, boolean everyYear)
    {
        try
        {
            session.beginTransaction();
            event = (Events)session.get(Events.class, event.getId());
            event.setEveryYear(everyYear);
            session.save(event);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void changeEventEventType(Events event, Event_types typeId)
    {
        try
        {
            session.beginTransaction();
            event = (Events)session.get(Events.class, event.getId());
            event.setTypeId(typeId);
            session.save(event);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void changeEventUser(Events event, Users userId)
    {
        try
        {
            session.beginTransaction();
            event = (Events)session.get(Events.class, event.getId());
            event.setUserId(userId);
            session.save(event);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void changeHistoryEventName(History hEvent, String name)
    {
        try
        {
            session.beginTransaction();
            hEvent = (History)session.get(History.class, hEvent.getId());
            hEvent.setName(name);
            session.save(hEvent);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void changeHistoryEventDate(History hEvent, Date date)
    {
        try
        {
            session.beginTransaction();
            hEvent = (History)session.get(History.class, hEvent.getId());
            hEvent.setDate(date);
            session.save(hEvent);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void changeHistoryEventActive(History hEvent, boolean active)
    {
        try
        {
            session.beginTransaction();
            hEvent = (History)session.get(History.class, hEvent.getId());
            hEvent.setActive(active);
            session.save(hEvent);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void changeHistoryEventEvent(History hEvent, Events event)
    {
        try
        {
            session.beginTransaction();
            hEvent = (History)session.get(History.class, hEvent.getId());
            hEvent.setEventId(event);
            session.save(hEvent);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void changeUserName(Users user, String name)
    {
        try
        {
            session.beginTransaction();
            user = (Users)session.get(Users.class, user.getId());
            user.setName(name);
            session.save(user);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void changeUserSurname(Users user, String surname)
    {
        try
        {
            session.beginTransaction();
            user = (Users)session.get(Users.class, user.getId());
            user.setSurname(surname);
            session.save(user);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void changeUserPatronymic(Users user, String patronymic)
    {
        try
        {
            session.beginTransaction();
            user = (Users)session.get(Users.class, user.getId());
            user.setPatronymic(patronymic);
            session.save(user);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void changeUserBirthday(Users user, Date birthday)
    {
        try
        {
            session.beginTransaction();
            user = (Users)session.get(Users.class, user.getId());
            user.setBirthday(birthday);
            session.save(user);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void changeUserRole(Users user, Roles role)
    {
        try
        {
            session.beginTransaction();
            user = (Users)session.get(Users.class, user.getId());
            user.setRoleId(role);
            session.save(user);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void changeUserEmail(Users user, String email)
    {
        try
        {
            session.beginTransaction();
            user = (Users)session.get(Users.class, user.getId());
            user.setEmail(email);
            session.save(user);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void changeUserAbout(Users user, String about)
    {
        try
        {
            session.beginTransaction();
            user = (Users)session.get(Users.class, user.getId());
            user.setAbout(about);
            session.save(user);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void changeUserLogin(Users user, String login)
    {
        try
        {
            session.beginTransaction();
            Logins userLog = (Logins)session.get(Logins.class, user.getId());
            userLog.setLogin(login);
            session.save(user);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void changeUserPassword(Users user, String password)
    {
        try
        {
            session.beginTransaction();
            Logins userLog = (Logins)session.get(Logins.class, user.getId());
            userLog.setPassword(password);
            session.save(user);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void changeParentCategory(Categories cat, Categories parent)
    {
        try
        {
            session.beginTransaction();
            cat = (Categories)session.get(Categories.class, cat.getId());
            cat.setParentId(parent);
            session.save(cat);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void changeMoneyMoney(Money collecting, long money)
    {
        try
        {
            session.beginTransaction();
            collecting = (Money)session.get(Money.class, collecting.getHistId());
            collecting.setMoney(money);
            session.save(collecting);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void changeMoneyMoneyMax(Money collecting, long moneyMax)
    {
        try
        {
            session.beginTransaction();
            collecting = (Money)session.get(Money.class, collecting.getHistId());
            collecting.setMoneyMax(moneyMax);
            session.save(collecting);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
    
    public void changeVoteCount(Vote vote, int count)
    {
        try
        {
            session.beginTransaction();
            vote = (Vote)session.get(Vote.class, vote.getHistId());
            vote.setCount(count);
            session.save(vote);
            session.getTransaction().commit();
        }
        catch (Exception exc)
        {
            log.debug("Adding to a database error!", exc);
        }
    }
}
