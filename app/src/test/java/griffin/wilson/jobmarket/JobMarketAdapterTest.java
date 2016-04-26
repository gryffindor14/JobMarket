package griffin.wilson.jobmarket;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import griffin.wilson.jobmarket.data.JobMarket;

import static griffin.wilson.jobmarket.JobMarketAdapter.*;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertSame;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,sdk=18)
public class JobMarketAdapterTest {
    private JobMarketAdapter adapter;
    private View mockView;
    private Activity mainActivity;
    private JobMarketViewHolder viewHolder;


    @Before
    public void setUp(){
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        adapter = new JobMarketAdapter(mainActivity, createJobMarkets());
    }

    @Test
    public void itemCount(){
        JobMarket m = new JobMarket("New York");
        m.addLanguage("java", 33);
        m.addLanguage("Ruby", 33);
        m.addLanguage("C++", 34);
        adapter.setJobMarkets(Arrays.asList(m,m,m));
        assertEquals(adapter.getItemCount(),3);
    }

    @Test
    public void getItemAtPosition(){
        JobMarket m = new JobMarket("New York");
        JobMarket m2 = new JobMarket("Chicago");
        adapter.setJobMarkets(Arrays.asList(m,m2));
        assertSame(adapter.getItemAtPosition(0),m);
        assertSame(adapter.getItemAtPosition(1),m2);
    }

    @Test
    public void testOnBindViewHolder(){
        adapter.setJobMarkets(createJobMarkets());
        LayoutInflater inflater = (LayoutInflater) RuntimeEnvironment.application.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.job_market,null,false);
        viewHolder = new JobMarketViewHolder(item);
        adapter.onBindViewHolder(viewHolder,0);
        assertSame(viewHolder.title.getText().toString(),"New York");
        assertSame(viewHolder.languageLayout.getChildCount(),3);
    }

    private List<JobMarket> createJobMarkets() {
        JobMarket m = new JobMarket("New York");
        m.addLanguage("java", 33);
        m.addLanguage("Ruby", 33);
        m.addLanguage("C++", 34);
        JobMarket m2 = new JobMarket("Chicago");
        m2.addLanguage("java", 25);
        m2.addLanguage("ruby", 25);
        m2.addLanguage("javascript", 26);
        m2.addLanguage("C++",25);
        return Arrays.asList(m,m2);
    }
}
