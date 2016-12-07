/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klosterteam.happiness_service;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import klosterteam.hibernate.Events;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
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
    private final HappyHibernate hHiber;
    
    public DoEventActiveJob() {
        hHiber = new HappyHibernate();
    }

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        Logger log = LogManager.getLogger(DoEventActiveJob.class);
        try
        {
            List<Events> events = hHiber.selectEventsByActiveDate(new Date());
            if (events != null)
            {
                log.warn("\n\nMy Job is running!!!!!!!!!!!!!!!!\n");
                for (int i = 0; i < events.size(); i++)
                {
                    hHiber.changeEventActive(events.get(i), true);
                    log.warn(events.get(i).getName() + "is active (" + (new Date()) + "): " + events.get(i).isActive());
                }
            //long eventId = jec.getJobDetail().getJobDataMap().getLongValue("eventId");
            //String email = jec.getJobDetail().getJobDataMap().getString("email");
            //JobKey jk = jec.getTrigger().getJobKey();
            /*try {
                jec.getScheduler().pauseJob(jk);
                //jec.getMergedJobDataMap().getLongValue("id");
                //HappyHibernate hHib = new HappyHibernate();
                //hHib.*/
            }
        }
        catch (Exception exc) {
            log.warn("Sheduler exception", exc);
        }
    }
    
}
