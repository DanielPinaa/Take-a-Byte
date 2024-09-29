package es.upm.etsiinf.proyectoPMD.util;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;

import es.upm.etsiinf.proyectoPMD.UpdateReviewsJobService;

public class JobUtil {
    private static int ID_SERVICIO =1;
    public static void scheduleJob(Context context){
        ComponentName serviceComponent = new ComponentName(context, UpdateReviewsJobService.class);
        JobInfo.Builder jobBuilderInfo = new JobInfo.Builder(ID_SERVICIO, serviceComponent);


        jobBuilderInfo.setPersisted(true); //


        jobBuilderInfo.setMinimumLatency(15000); // cada 15 secs
        jobBuilderInfo.setOverrideDeadline(15000); // cada 15 secs

        JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
        jobScheduler.schedule(jobBuilderInfo.build());
    }
}