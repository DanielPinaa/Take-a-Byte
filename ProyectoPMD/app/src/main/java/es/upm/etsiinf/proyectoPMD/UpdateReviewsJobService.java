package es.upm.etsiinf.proyectoPMD;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import es.upm.etsiinf.proyectoPMD.util.JobUtil;

public class UpdateReviewsJobService extends JobService {
    private ReviewsDataSource dataSource;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.i("TestJobService","Servicio ejecutado");

        dataSource = new ReviewsDataSource(this);

        sendBroadcast();

        JobUtil.scheduleJob(this);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    private void sendBroadcast() {
        Intent intent = new Intent("reseñas_actualizadas");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

}




