/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klosterteam.happiness_service;

import java.util.Calendar;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import org.quartz.CronTrigger;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author Cyberhawk
 */
public class AppCronTrigger {
    static long id = 0;
    public static int createTrigger(Map<String, Object> params, String cronEx, boolean isRepeatable)
    {
        Logger log = LogManager.getLogger(AppCronTrigger.class);
        try
        {
            //HappyHibernate hHibernate = new HappyHibernate();
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler sche = sf.getScheduler();
            sche.start();
            JobDataMap map = new JobDataMap();
            //map.put("type", "1");
            map.putAll(params);
            JobDetail job = newJob(DoEventActiveJob.class)
            .withIdentity("job" + id, "group1")
            .usingJobData(map)
            .build();
            //job.getJobDataMap().
            //Calendar date = Calendar.getInstance();
            //date.set(Calendar.SECOND, date.get(Calendar.SECOND) + 10);
            CronTrigger cron;
            if (isRepeatable)
            {
                cron = newTrigger()
                .withIdentity("trigger" + id, "group1")
                .withSchedule(cronSchedule(cronEx))//"0 0 3 * * ?" "0/10 * * * * ?"
                //.startAt(date.getTime())
                .forJob("job" + id, "group1")
                .build();
            }
            else
            {
                /*Calendar dateEnd = Calendar.getInstance();
                dateEnd.set(Calendar.SECOND, dateEnd.get(Calendar.SECOND) + 15);*/
                cron = newTrigger()
                .withIdentity("trigger" + id, "group1")
                .withSchedule(cronSchedule(cronEx))//"0 0 3 * * ?" "0/10 * * * * ?"
                //.startAt(date.getTime())
                //.endAt(dateEnd.getTime())
                .forJob("job" + id, "group1")
                .build();
            }
            sche.scheduleJob(job, cron);
            id++;
            return 0;
        }
        catch (Exception exc)
        {
            log.warn("Cron trigger executing exception!", exc);
            return -1;
        }
    }
}
