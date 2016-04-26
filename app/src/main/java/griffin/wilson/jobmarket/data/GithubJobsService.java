package griffin.wilson.jobmarket.data;

import android.support.annotation.VisibleForTesting;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

@VisibleForTesting
public interface GithubJobsService {

    String SERVICE_ENDPOINT = "http://jobs.github.com";

    @GET("positions.json")
    Observable<List<Job>> jobsFor(@Query("description") String language, @Query("location") String city);
}
