package org.kamal;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;

/**
 * Created by kmuralidharan on 9/21/13.
 */
public class DBJob implements Job {


    public void execute(JobExecutionContext context)
            throws JobExecutionException
    {
        DBPoller poller = new DBPoller();
        try {
            poller.pollDB();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
