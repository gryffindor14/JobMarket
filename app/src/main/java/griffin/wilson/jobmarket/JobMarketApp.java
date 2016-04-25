package griffin.wilson.jobmarket;

import android.app.Application;

import griffin.wilson.jobmarket.data.GithubJobsService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Griffin on 4/25/2016.
 */
public class JobMarketApp extends Application {
    private GithubJobsService jobsService;

    public void onCreate(){
        super.onCreate();
        buildService();
    }

    private void buildService() {
        Retrofit r = new Retrofit.Builder().baseUrl(GithubJobsService.SERVICE_ENDPOINT).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
        jobsService = r.create(GithubJobsService.class);
    }

    public GithubJobsService getJobsService() {
        return jobsService;
    }
}
