package griffin.wilson.jobmarket;

import android.app.Application;

import griffin.wilson.jobmarket.data.GithubJobsService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG? HttpLoggingInterceptor.Level.BODY: HttpLoggingInterceptor.Level.NONE);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit r = new Retrofit.Builder().baseUrl(GithubJobsService.SERVICE_ENDPOINT).client(client).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
        jobsService = r.create(GithubJobsService.class);
    }

    public GithubJobsService getJobsService() {
        return jobsService;
    }


}
