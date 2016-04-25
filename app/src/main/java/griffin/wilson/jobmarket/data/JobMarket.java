package griffin.wilson.jobmarket.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Griffin on 4/25/2016.
 */
public class JobMarket {
    String location;
    Map<String, Integer> languages;
    private int totalJobs;

    public void parse(List<Job> jobs, String lanaguage){
        if(!languages.containsKey(lanaguage)) {
            languages.put(lanaguage, jobs.size());
            totalJobs += jobs.size();
        }
    }


    public JobMarket(String l) {
        this.location = l;
        languages = new HashMap<>();
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public void addLanguage(String language, int percentage){
        languages.put(language, percentage);
    }

    public Map<String, Integer> getLanguages() {
        return languages;
    }

    public int getTotalJobs() {
        return totalJobs;
    }
}
