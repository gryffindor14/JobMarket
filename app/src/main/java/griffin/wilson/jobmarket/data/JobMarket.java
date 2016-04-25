package griffin.wilson.jobmarket.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

/**
 * Created by Griffin on 4/25/2016.
 */
public class JobMarket implements Parcelable{
    String location;
    Map<String, Integer> languagePercentages;

    protected JobMarket(Parcel in) {
        location = in.readString();
    }

    public static final Creator<JobMarket> CREATOR = new Creator<JobMarket>() {
        @Override
        public JobMarket createFromParcel(Parcel in) {
            return new JobMarket(in);
        }

        @Override
        public JobMarket[] newArray(int size) {
            return new JobMarket[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(location);
    }
}
