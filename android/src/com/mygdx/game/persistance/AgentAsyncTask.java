package com.mygdx.game.persistance;

import android.app.Activity;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public class AgentAsyncTask extends AsyncTask<Void, Void, Integer> {

    //Prevent leak
    private WeakReference<Activity> weakActivity;
    private String email;
    private String phone;
    private String license;

    public AgentAsyncTask(Activity activity, String email, String phone, String license) {
        weakActivity = new WeakReference<>(activity);
        this.email = email;
        this.phone = phone;
        this.license = license;
    }

    @Override
    protected Integer doInBackground(Void... params) {
        AgentDao agentDao = MyApp.DatabaseSetup.getDatabase().agentDao();
        return agentDao.agentsCount(email, phone, license);
    }

    @Override
    protected void onPostExecute(Integer agentsCount) {
        Activity activity = weakActivity.get();
        if(activity == null) {
            return;
        }
    }
}