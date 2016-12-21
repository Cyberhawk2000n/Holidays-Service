/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klosterteam.happiness_service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import klosterteam.hibernate.Events;
import klosterteam.hibernate.Vote;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

/**
 *
 * @author Cyberhawk
 */
public class DoEventActiveJob implements Job {
    private final HappyHibernate hHibernate;
    
    public DoEventActiveJob() {
        hHibernate = new HappyHibernate();
    }

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        Logger log = LogManager.getLogger(DoEventActiveJob.class);
        try
        {
            //Map<String, String> params = new HashMap<>();
            JobDataMap params = jec.getJobDetail().getJobDataMap();
            if ("1".equals(params.get("type")))
            {
                Events event = (Events)params.get("event");
                event = hHibernate.selectEventById(event.getId());
                if (event == null)
                    jec.getScheduler().deleteJob(jec.getTrigger().getJobKey());
                else
                {
                    if ((event.getTypeId().getId() != 0)||(event.getUserId().isGiveGift()))
                    {
                        hHibernate.changeEventActive(event, true);
                        hHibernate.createMoney(event, 0, 10000);
                        hHibernate.sendMessage(event.getManagerId().getEmail(),
                            Calendar.getInstance().getTime().toString() + "Vote for event \" "
                                   + event.getName() + "\" is ready!");
                        //можно указать путь к нему "/Voting.jsp?id=" + event.getId()
                    }
                }
            }
            else if ("2".equals(params.get("type")))
            {
                Events event = (Events)params.get("event");
                event = hHibernate.selectEventById(event.getId());
                if (event == null)
                    jec.getScheduler().deleteJob(jec.getTrigger().getJobKey());
                else
                {
                    if ((event.getTypeId().getId() != 0)||(event.getUserId().isGiveGift()))
                    {
                        hHibernate.sendMessage(event.getManagerId().getEmail(),
                            Calendar.getInstance().getTime().toString() + "\nVote for event \" "
                                    + event.getName() + "\" has been ended!");
                        List<Vote> gifts = hHibernate.selectVoteByEvent(event);
                        int max = 0;
                        for (int i = 0; i < gifts.size(); i++)
                            if (gifts.get(i).getCount() > max)
                                max = gifts.get(i).getCount();
                        List<Vote> tmp = new ArrayList<>();
                        for (int i = 0; i < gifts.size(); i++)
                            if (gifts.get(i).getCount() == max)
                                tmp.add(gifts.get(i));
                        StringBuilder sb = new StringBuilder();
                        if (!tmp.isEmpty())
                        {
                            sb.append(Calendar.getInstance().getTime().toString())
                                .append("\nVote for event \" ")
                                .append(event.getName())
                                .append("\" has been ended!");
                            if (tmp.size() == 1)
                            {
                                sb.append("\nGift \" ")
                                .append(tmp.get(0).getGiftId().getName())
                                .append("\" has been chosen!");
                                hHibernate.createGiftHistory(tmp.get(0));
                                for (int i = 0; i < gifts.size(); i++)
                                    hHibernate.deleteVote(gifts.get(i));
                            }
                            else if (tmp.size() > 1)
                            {
                                sb.append("\nGifts: \" \n");
                                for (int i = 0; i < tmp.size(); i++)
                                    sb.append("\"")
                                        .append(tmp.get(i).getGiftId().getName())
                                        .append("\"\n");
                                sb.append("You must choose something!");
                            }
                            hHibernate.sendMessage(event.getManagerId().getEmail(), sb.toString());
                        }
                    }
                    else
                    {
                        List<Vote> gifts = hHibernate.selectVoteByEvent(event);
                        for (int i = 0; i < gifts.size(); i++)
                            hHibernate.deleteVote(gifts.get(i));
                    }
                }
            }
            else if ("3".equals(params.get("type")))
            {
                Events event = (Events)params.get("event");
                event = hHibernate.selectEventById(event.getId());
                if (event == null)
                    jec.getScheduler().deleteJob(jec.getTrigger().getJobKey());
                else
                {
                    if (!event.isEveryYear())
                    {
                        hHibernate.deleteEvent(event);
                        jec.getScheduler().deleteJob(jec.getTrigger().getJobKey());
                    }
                    else
                    {
                        hHibernate.changeEventActive(event, false);
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(event.getDate());
                        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
                        hHibernate.changeEventDate(event, calendar.getTime());
                    }
                    hHibernate.deleteMoney(event);
                }
            }
            /*List<Events> events = hHibernate.selectEventsByActiveDate(new Date());
            if (events != null)
            {
                //log.warn("\n\nMy Job is running!!!!!!!!!!!!!!!!\n");
                for (int i = 0; i < events.size(); i++)
                {
                    hHibernate.changeEventActive(events.get(i), true);
                    log.warn(events.get(i).getName() + "is active (" + (new Date()) + "): " + events.get(i).isActive());
                }*/
            //long eventId = jec.getJobDetail().getJobDataMap().getLongValue("eventId");
            //String email = jec.getJobDetail().getJobDataMap().getString("email");
            //JobKey jk = jec.getTrigger().getJobKey();
            /*try {
                jec.getScheduler().pauseJob(jk);
                //jec.getMergedJobDataMap().getLongValue("id");
                //HappyHibernate hHib = new HappyHibernate();
                //hHib.*/
            //}
        }
        catch (Exception exc) {
            log.warn("Sheduler exception", exc);
        }
    }
    
}
