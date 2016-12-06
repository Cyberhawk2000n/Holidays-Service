/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klosterteam.happiness_service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author Cyberhawk
 */
public class DoEventActiveJob implements Job {

    public DoEventActiveJob() {
    }

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        Logger log = LogManager.getLogger(DoEventActiveJob.class);
        log.warn("My Job is running!!!!!!!!!!!!!!!!");
        //HappyHibernate hHib = new HappyHibernate();
        //hHib.
    }
    
}
