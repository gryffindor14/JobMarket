package griffin.wilson.jobmarket;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import griffin.wilson.jobmarket.data.JobMarket;

/**
 * Created by Griffin on 4/25/2016.
 */
public class JobMarketAdapter extends RecyclerView.Adapter<JobMarketViewHolder>{
    private final Context context;
    private List<JobMarket> jobMarkets;


    public JobMarketAdapter(Context context, List<JobMarket> jobMarkets) {
        this.jobMarkets = jobMarkets;
        this.context = context;
    }

    @Override
    public JobMarketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new JobMarketViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.job_market,parent,false));
//        return null;
    }

    @Override
    public void onBindViewHolder(JobMarketViewHolder holder, int position) {
        holder.languageLayout.removeAllViews();
        JobMarket market = jobMarkets.get(position);
        holder.title.setText(market.getLocation());
        for(String l: market.getLanguages().keySet()){
            TextView t = new TextView(context);
            t.setText(String.format("In %s, %.2f%% of all current programming is %s",market.getLocation(),market.getTotalJobs()>0?((float)market.getLanguages().get(l)/market.getTotalJobs()*100):0,l));
            holder.languageLayout.addView(t);
        }
    }

    @Override
    public int getItemCount() {
        return jobMarkets.size();
    }

    public void setJobMarkets(List<JobMarket> markets) {
        jobMarkets = markets;
        notifyDataSetChanged();
    }

    public JobMarket getItemAtPosition(int i) {
        if(i<jobMarkets.size())
            return jobMarkets.get(i);
        else return null;
    }


}
