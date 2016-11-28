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
import klosterteam.hibernate.Gift_history;
import klosterteam.hibernate.Gifts;
import klosterteam.hibernate.History;
import klosterteam.hibernate.Logins;
import klosterteam.hibernate.Money;
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
    
    public Events createEvent(String name, Date date, boolean everyYear, Event_types typeId, Users userId)
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
    
    public History createHistoryEvent(String name, Date date, boolean active, Events event)
    {
        try
        {
            session.beginTransaction();
            event = (Events)session.get(Events.class, event.getId());
            if (event == null)
                throw new NullPointerException();
            History hEvent = new History();
            hEvent.setName(name);
            hEvent.setDate(date);
            hEvent.setActive(active);
            hEvent.setEventId(event);
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
    
    public Gift_history createGiftHistory(History event, Gifts gift)
    {
        try
        {
            session.beginTransaction();
            event = (History)session.get(History.class, event.getId());
            if (event == null)
                throw new NullPointerException();
            gift = (Gifts)session.get(Gifts.class, gift.getId());
            if (gift == null)
                throw new NullPointerException();
            Gift_history gHist = new Gift_history();
            gHist.setHistId(event);
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
    
    public Gift_history createGiftHistory(Vote vote)
    {
        try
        {
            if (vote == null)
                throw new NullPointerException();
            session.beginTransaction();
            vote = (Vote)session.get(Vote.class, vote.getHistId());
            if (vote == null)
                throw new NullPointerException();
            Gift_history gHist = new Gift_history();
            gHist.setHistId(vote.getHistId());
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
    
    public Vote createVote(History event, Gifts gift, int count)
    {
        try
        {
            session.beginTransaction();
            event = (History)session.get(History.class, event.getId());
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
    
    public Money createMoney(History event, long money, long moneyMax)
    {
        try
        {
            session.beginTransaction();
            event = (History)session.get(History.class, event.getId());
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
    
    public Users createUser(String name, String surname, String patronymic, Date birthday, Roles role, String email, String about)
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
    
    public History changeHistoryEventName(History hEvent, String name)
    {
        try
        {
            session.beginTransaction();
            hEvent = (History)session.get(History.class, hEvent.getId());
            if (hEvent == null)
                throw new NullPointerException();
            hEvent.setName(name);
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
    
    public History changeHistoryEventDate(History hEvent, Date date)
    {
        try
        {
            session.beginTransaction();
            hEvent = (History)session.get(History.class, hEvent.getId());
            if (hEvent == null)
                throw new NullPointerException();
            hEvent.setDate(date);
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
    
    public History changeHistoryEventActive(History hEvent, boolean active)
    {
        try
        {
            session.beginTransaction();
            hEvent = (History)session.get(History.class, hEvent.getId());
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
    
    public History changeHistoryEventEvent(History hEvent, Events event)
    {
        try
        {
            session.beginTransaction();
            hEvent = (History)session.get(History.class, hEvent.getId());
            if (hEvent == null)
                throw new NullPointerException();
            hEvent.setEventId(event);
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
    
    public Logins changeUserLogin(Users user, String login)
    {
        try
        {
            session.beginTransaction();
            Logins userLog = (Logins)session.get(Logins.class, user.getId());
            if (userLog == null)
                throw new NullPointerException();
            userLog.setLogin(login);
            session.save(user);
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
            session.save(user);
            session.getTransaction().commit();
            return userLog;
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
            cr.add(Restrictions.like("name", name));
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
            cr.add(Restrictions.like("role", role));
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
    
    public List<Vote> selectVote(long histId)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Vote.class);
            cr.add(Restrictions.like("histId", histId));
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
    
    public List<Users> selectUsersByEmail(String email)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Users.class);
            cr.add(Restrictions.like("email", email));
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
            cr.add(Restrictions.like("user", user));
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
            cr.add(Restrictions.like("typeId", type));
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
    
    public List<History> selectHistoryEvents()
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(History.class);
            List<History> list = cr.list();
            session.getTransaction().commit();
            return list;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public List<History> selectHistoryEventsByUser(Users user)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Events.class);
            cr.add(Restrictions.like("user", user));
            List<Events> list = cr.list();
            session.getTransaction().commit();
            List<History> list2 = new ArrayList<>();
            for (int i = 0; i < list.size(); i++)
            {
                session.beginTransaction();
                Criteria cr2 = session.createCriteria(History.class);
                cr2.add(Restrictions.like("eventId", list.get(i).getId()));
                list2.addAll(cr2.list());
                session.getTransaction().commit();
            }
            return list2;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
    
    public List<History> selectHistoryEventsByType(Event_types type)
    {
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Events.class);
            cr.add(Restrictions.like("typeId", type));
            List<Events> list = cr.list();
            session.getTransaction().commit();
            List<History> list2 = new ArrayList<>();
            for (int i = 0; i < list.size(); i++)
            {
                session.beginTransaction();
                Criteria cr2 = session.createCriteria(History.class);
                cr2.add(Restrictions.like("eventId", list.get(i).getId()));
                list2.addAll(cr2.list());
                session.getTransaction().commit();
            }
            return list2;
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
            cr.add(Restrictions.like("parent", null));
            List<Categories> list = cr.list();
            session.getTransaction().commit();
            for (int i = 0; i < list.size(); i++)
            {
                session.beginTransaction();
                Criteria cr2 = session.createCriteria(Categories.class);
                cr2.add(Restrictions.like("parent", list.get(i).getId()));
                map.put(list.get(i), cr2.list());
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
            List<Gifts> list2;
            for (int i = 0; i < list.size(); i++)
            {
                session.beginTransaction();
                Criteria cr2 = session.createCriteria(Categories.class);
                cr2.add(Restrictions.like("id", list.get(i).getCatId()));
                Categories cat = (Categories)cr2.list().get(0);
                list2 = map.get(cat);
                list2.add(list.get(i));
                map.put(cat, list2);
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
            Criteria cr = session.createCriteria(Events.class);
            cr.add(Restrictions.like("user", user));
            List<Events> list = cr.list();
            session.getTransaction().commit();
            List<History> list2 = new ArrayList<>();
            for (int i = 0; i < list.size(); i++)
            {
                session.beginTransaction();
                Criteria cr2 = session.createCriteria(History.class);
                cr2.add(Restrictions.like("eventId", list.get(i).getId()));
                list2.addAll(cr2.list());
                session.getTransaction().commit();
            }
            List<Gift_history> list3 = new ArrayList<>();
            for (int i = 0; i < list2.size(); i++)
            {
                session.beginTransaction();
                Criteria cr3 = session.createCriteria(Gift_history.class);
                cr3.add(Restrictions.like("histId", list2.get(i).getId()));
                list3.addAll(cr3.list());
                session.getTransaction().commit();
            }
            return list3;
        }
        catch (Exception exc)
        {
            log.debug("Getting from database error!", exc);
            return null;
        }
        
    }
}
