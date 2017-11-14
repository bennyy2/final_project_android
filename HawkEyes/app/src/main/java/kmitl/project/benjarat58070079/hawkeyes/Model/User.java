package kmitl.project.benjarat58070079.hawkeyes.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Benny on 10/11/2560.
 */

public class User implements Parcelable {


    private String id;
    private String display_name;
    private String email;
    private String image_url;

    public User(String display_name, String email, String image_url) {
        this.display_name = display_name;
        this.email = email;
        this.image_url = image_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.display_name);
        dest.writeString(this.email);
        dest.writeString(this.image_url);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.id = in.readString();
        this.display_name = in.readString();
        this.email = in.readString();
        this.image_url = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
