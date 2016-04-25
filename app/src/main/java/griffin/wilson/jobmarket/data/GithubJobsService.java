package griffin.wilson.jobmarket.data;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Griffin on 4/25/2016.
 */
public interface GithubJobsService {

    String SERVICE_ENDPOINT = "http://jobs.github.com";

    @GET("/positions")
    Observable<List<Job>> jobsFor(@Query("description") String language, @Query("location") String city);
}
