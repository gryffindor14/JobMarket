package griffin.wilson.jobmarket;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk=18)
public class MainActivityUnitTest {
    private LinearLayoutManager layoutManager;
    private MainActivity.MainActivityTest mainActivity;
    private JobMarketAdapter adapter;

    @Before
    public void setup(){
        mainActivity = Robolectric.setupActivity(MainActivity.MainActivityTest.class);
        layoutManager = Mockito.mock(LinearLayoutManager.class);
        adapter = Mockito.mock(JobMarketAdapter.class);
        mainActivity.setLayoutManager(layoutManager);


    }

    @Test
    public void testDefaultDisplay(){
        SuperRecyclerView recyclerView = (SuperRecyclerView) mainActivity.findViewById(R.id.jobs_list);
        RecyclerView.LayoutManager layoutManager = mainActivity.getLayoutManager();
        assertEquals(layoutManager, mainActivity.getLayoutManager());
    }

    @After
    public void tearDown(){
        mainActivity = null;
        layoutManager = null;
        adapter = null;
    }
}
