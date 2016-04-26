package griffin.wilson.jobmarket;

import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.mockito.internal.runners.JUnit45AndHigherRunnerImpl;

import java.util.List;

import griffin.wilson.jobmarket.data.Job;
import griffin.wilson.jobmarket.data.JobMarket;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertSame;

@RunWith(JUnit4ClassRunner.class)
public class JobMarketTest {

    @Test
    public void testJobMarket(){
        JobMarket m= new JobMarket("Chicago");
        m.addLanguage("java", 50);
        assertEquals(m.getLocation(), "Chicago");
        assertEquals(m.getLanguages().size(),1);
        m.addLanguage("ruby", 50);
        assertEquals(m.getLanguages().size(),2);
    }

    @Test
    public void testParseJobs(){
        List<Job> jobs = MockJobService.mock();
        JobMarket m = new JobMarket("New York");
        m.parse(jobs,"java");
        assertEquals(m.getTotalJobs(),3);
    }
}
