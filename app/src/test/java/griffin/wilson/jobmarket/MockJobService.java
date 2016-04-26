package griffin.wilson.jobmarket;

import java.util.Arrays;
import java.util.List;

import griffin.wilson.jobmarket.data.GithubJobsService;
import griffin.wilson.jobmarket.data.Job;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Griffin on 4/25/2016.
 */
public class MockJobService implements GithubJobsService {


    public static List<Job> mock(){
        Job j  = new Job();
        j.location="New York";
        j.description = "java";
        Job j2 = new Job();
        j.description = "C++";
        j.location="New York";
        Job j3 = new Job();
        j.location = "New York";
        j.description = "Ruby";
        Job j4  = new Job();
        return Arrays.asList(j,j2,j3);
    }

    @Override
    public Observable<List<Job>> jobsFor(@Query("description") String language, @Query("location") String city) {
        if(language.equalsIgnoreCase("java"))
            return Observable.just(mock().subList(0,1));
        else if(language.equalsIgnoreCase("c++"))
            return Observable.just(mock().subList(1,2));
        else if(language.equalsIgnoreCase("Ruby"))
            return Observable.just(mock().subList(2,3));
        else
            return Observable.just(Arrays.asList(new Job()));
    }
}
