package griffin.wilson.jobmarket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import griffin.wilson.jobmarket.data.GithubJobsService;
import griffin.wilson.jobmarket.data.Job;
import rx.Observable;
import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class JobServiceUnitTest {

    private GithubJobsService service;

    @Before
    public void setUp(){
        service = new MockJobService();
    }

    @Test
    public void shouldLoadJobsForCityAndLanguage(){
        assertNotNull(service);
        Observable<List<Job>> observable = service.jobsFor("java", "New York").mergeWith(service.jobsFor("C++", "New York")).mergeWith(service.jobsFor("Ruby", "New York")).asObservable();
        assertNotNull(observable);
        TestSubscriber<List<Job>> subscriber = new TestSubscriber<>();
        observable.subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertValueCount(3);//recevied 3 responses
    }

    @After
    public void tearDown(){
        service = null;
    }

}
