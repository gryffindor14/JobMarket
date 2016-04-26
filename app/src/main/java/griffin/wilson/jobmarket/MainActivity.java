package griffin.wilson.jobmarket;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import griffin.wilson.jobmarket.data.GithubJobsService;
import griffin.wilson.jobmarket.data.Job;
import griffin.wilson.jobmarket.data.JobMarket;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.main)
    RelativeLayout layout;
    @Bind(R.id.jobs_list)
    SuperRecyclerView jobList;
    private GithubJobsService jobsService;
    private JobMarket bostonJobMarket =new JobMarket("Boston");
    private JobMarket nyMarket = new JobMarket("New York");
    private JobMarket denverMarket = new JobMarket("Denver");
    private JobMarket boulderMarket = new JobMarket("Boulder");
    private JobMarket  sfMarket = new JobMarket("San Francisco");
    private JobMarket chicagoMarket = new JobMarket("Chicago");
    private JobMarketAdapter adapter;

    private Handler mainHandler = new Handler(Looper.getMainLooper());
    private JobMarket laMarket = new JobMarket("Lost Angeles");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        JobMarketApp app = (JobMarketApp) getApplicationContext();
        jobsService = app.getJobsService();
        adapter = new JobMarketAdapter(this, Arrays.asList(bostonJobMarket,nyMarket,denverMarket,boulderMarket,sfMarket,chicagoMarket));
        jobList.setLayoutManager(new LinearLayoutManager(this));
        jobList.setAdapter(adapter);
    }

    @Override
    public void onResume(){
        super.onResume();
        reloadData();
    }

    private void reloadData() {
        refreshJobMarkets();
    }

    public void refreshJobMarkets(){

        createBostonObservable("java").mergeWith(createBostonObservable("C++").mergeWith(createBostonObservable("Ruby"))).observeOn(AndroidSchedulers.mainThread()).subscribe();
        createBoulderObservable("java").mergeWith(createBoulderObservable("C++").mergeWith(createBoulderObservable("Ruby"))).observeOn(AndroidSchedulers.mainThread()).subscribe();
        createDenverObservable("java").mergeWith(createDenverObservable("C++").mergeWith(createDenverObservable("Ruby"))).observeOn(AndroidSchedulers.mainThread()).subscribe();
        createSanFranObservable("java").mergeWith(createSanFranObservable("C++").mergeWith(createSanFranObservable("Ruby"))).observeOn(AndroidSchedulers.mainThread()).subscribe();
        createChicagoObservable("java").mergeWith(createChicagoObservable("C++").mergeWith(createChicagoObservable("Ruby"))).observeOn(AndroidSchedulers.mainThread()).subscribe();
        createLAObservable("java").mergeWith(createLAObservable("C++").mergeWith(createLAObservable("Ruby"))).observeOn(AndroidSchedulers.mainThread()).subscribe();
        createNYObservable("java").mergeWith(createNYObservable("C++").mergeWith(createNYObservable("Ruby"))).observeOn(AndroidSchedulers.mainThread()).subscribe();


    }

    private void parseChicagoJobs(final List<Job> jobs, final String java) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                chicagoMarket.parse(jobs,java);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });

            }
        });

    }

    private void parseSanFranJobs(final List<Job> jobs, final String language) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                sfMarket.parse(jobs,language);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }

    private void parseDenverJobs(final List<Job> jobs, final String language) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                denverMarket.parse(jobs,language);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }

    private void parseNYJobs(final List<Job> jobs, final String lanaguage) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                nyMarket.parse(jobs,lanaguage);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }


    private void parseBostonJobs(final List<Job> jobs, final String language) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                bostonJobMarket.parse(jobs,language);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }

    public Observable<List<Job>> createBostonObservable(final String language){
        return jobsService.jobsFor(language,"Boston").doOnNext(new Action1<List<Job>>() {
            @Override
            public void call(List<Job> jobs) {
                parseBostonJobs(jobs,language);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<List<Job>> createSanFranObservable(final String language){
        return jobsService.jobsFor(language,"San Francisco").doOnNext(new Action1<List<Job>>() {
            @Override
            public void call(List<Job> jobs) {

                parseSanFranJobs(jobs,language);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<List<Job>> createLAObservable(final String language){
        return jobsService.jobsFor(language,"Los Angeles").doOnNext(new Action1<List<Job>>() {
            @Override
            public void call(List<Job> jobs) {
                parseLAJobs(jobs,language);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    private void parseLAJobs(final List<Job> jobs, final String language) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                laMarket.parse(jobs,language);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }

    public Observable<List<Job>> createDenverObservable(final String language){
        return jobsService.jobsFor(language,"Denver").doOnNext(new Action1<List<Job>>() {
            @Override
            public void call(List<Job> jobs) {
                parseDenverJobs(jobs,language);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<List<Job>> createBoulderObservable(final String language){
        return jobsService.jobsFor(language,"Boulder").doOnNext(new Action1<List<Job>>() {
            @Override
            public void call(List<Job> jobs) {
                parseBoulderJobs(jobs,language);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    }

    private void parseBoulderJobs(final List<Job> jobs, final String language) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                boulderMarket.parse(jobs,language);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }

    public Observable<List<Job>> createChicagoObservable(final String language){
        return jobsService.jobsFor(language,"Chicago").doOnNext(new Action1<List<Job>>() {
            @Override
            public void call(List<Job> jobs) {
                parseChicagoJobs(jobs,language);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    }
    public Observable<List<Job>> createNYObservable(final String language){
        return jobsService.jobsFor(language,"San Francisco").doOnNext(new Action1<List<Job>>() {
            @Override
            public void call(List<Job> jobs) {
                parseNYJobs(jobs,language);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static class MainActivityTest extends MainActivity{
        private LinearLayoutManager mockLayoutManager;
        public void setLayoutManager(LinearLayoutManager mockLayoutManager) {
            this.mockLayoutManager = mockLayoutManager;
        }


        public LinearLayoutManager getLayoutManager() {
            return mockLayoutManager;
        }
    }



}
