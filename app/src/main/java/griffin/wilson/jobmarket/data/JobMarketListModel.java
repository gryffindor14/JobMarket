package griffin.wilson.jobmarket.data;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


public class JobMarketListModel implements Parcelable{

    private ObservableArrayList<JobMarket> markets = new ObservableArrayList<>();
    private ObservableBoolean error = new ObservableBoolean();
    private boolean loaded;

    public JobMarketListModel(){}


    protected JobMarketListModel(Parcel in) {
        error = in.readParcelable(ObservableBoolean.class.getClassLoader());
        loaded = in.readByte() != 0;
    }

    public static final Creator<JobMarketListModel> CREATOR = new Creator<JobMarketListModel>() {
        @Override
        public JobMarketListModel createFromParcel(Parcel in) {
            return new JobMarketListModel(in);
        }

        @Override
        public JobMarketListModel[] newArray(int size) {
            return new JobMarketListModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(error, flags);
        dest.writeByte((byte) (loaded ? 1 : 0));
    }

    public void loadedData(List<JobMarket> items){
        markets.addAll(items);
        error.set(false);
        loaded = true;
    }

    public ObservableArrayList<JobMarket> getMarkets() {
        return markets;
    }

    public boolean isLoaded(){
        return loaded;
    }

    public void loadedWithError() {
        error.set(true);
        loaded = true;
    }
    public void updateItem(JobMarket market) {
        int pos = 0;
        for (JobMarket m : markets) {
            if (m.location.equals(market.location)) {
                markets.set(pos, m);
                return;
            }
            pos++;
        }
        markets.add(market);
    }
}
