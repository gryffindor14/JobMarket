package griffin.wilson.jobmarket.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Griffin on 4/25/2016.
 */
public class Job implements Parcelable {

    public String id;
    public String title;
    public String location;
    public String type;
    public String description;
    @SerializedName("how_to_apply")
    public String howToApply;
    public String company;
    @SerializedName("company_url")
    public String companyUrl;
    @SerializedName("company_logo")
    public String companyLogo;
    public String url;

    public Job(){

    }

    protected Job(Parcel in) {
        id = in.readString();
        title = in.readString();
        location = in.readString();
        type = in.readString();
        description = in.readString();
        howToApply = in.readString();
        company = in.readString();
        companyUrl = in.readString();
        companyLogo = in.readString();
        url = in.readString();
    }

    public static final Creator<Job> CREATOR = new Creator<Job>() {
        @Override
        public Job createFromParcel(Parcel in) {
            return new Job(in);
        }

        @Override
        public Job[] newArray(int size) {
            return new Job[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(location);
        dest.writeString(type);
        dest.writeString(description);
        dest.writeString(howToApply);
        dest.writeString(company);
        dest.writeString(companyUrl);
        dest.writeString(companyLogo);
        dest.writeString(url);
    }
}
