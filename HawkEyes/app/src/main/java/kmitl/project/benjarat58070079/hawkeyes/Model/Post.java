package kmitl.project.benjarat58070079.hawkeyes.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Benny on 20/11/2560.
 */

public class Post implements Parcelable {
    private String text_post;
    private String type;
    private String address;
    private Double latitude;
    private Double longtitude;
    private String dateTime;
    private String post_user;
    private DatabaseReference databaseReference;



    public Post() {
    }

    public void savePost(){

        databaseReference = FirebaseDatabase.getInstance().getReference("post");
        String key = databaseReference.push().getKey();
        databaseReference.child(key).child("text_post").setValue(this.text_post);
        databaseReference.child(key).child("type").setValue(this.type);
        databaseReference.child(key).child("address").setValue(this.address);
        databaseReference.child(key).child("latitude").setValue(this.latitude);
        databaseReference.child(key).child("longtitude").setValue(this.longtitude);
        databaseReference.child(key).child("dateTime").setValue(this.dateTime);
        databaseReference.child(key).child("post_user").setValue(this.post_user);

    }


    public String getText_post() {
        return text_post;
    }

    public void setText_post(String text_post) {
        this.text_post = text_post;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double londtitude) {
        this.longtitude = londtitude;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getPost_user() {
        return post_user;
    }

    public void setPost_user(String post_user) {
        this.post_user = post_user;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text_post);
        dest.writeString(this.type);
        dest.writeString(this.address);
        dest.writeValue(this.latitude);
        dest.writeValue(this.longtitude);
        dest.writeString(this.dateTime);
        dest.writeString(this.post_user);
        dest.writeParcelable((Parcelable) this.databaseReference, flags);
    }

    protected Post(Parcel in) {
        this.text_post = in.readString();
        this.type = in.readString();
        this.address = in.readString();
        this.latitude = (Double) in.readValue(Double.class.getClassLoader());
        this.longtitude = (Double) in.readValue(Double.class.getClassLoader());
        this.dateTime = in.readString();
        this.post_user = in.readString();
        this.databaseReference = in.readParcelable(DatabaseReference.class.getClassLoader());
    }

    public static final Parcelable.Creator<Post> CREATOR = new Parcelable.Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
