package kmitl.project.benjarat58070079.hawkeyes.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Benny on 24/11/2560.
 */

public class Comment implements Parcelable {
    private String post_id;
    private String comment_text;
    private String user_id;
    private DatabaseReference databaseReference;

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void saveComment() {

        databaseReference = FirebaseDatabase.getInstance().getReference("comment/");
        String key = databaseReference.push().getKey();
        databaseReference.child(key).child("post_id").setValue(this.post_id);
        databaseReference.child(key).child("comment_text").setValue(this.comment_text);
        databaseReference.child(key).child("user_id").setValue(this.user_id);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.post_id);
        dest.writeString(this.comment_text);
        dest.writeString(this.user_id);
    }

    public Comment() {
    }

    protected Comment(Parcel in) {
        this.post_id = in.readString();
        this.comment_text = in.readString();
        this.user_id = in.readString();
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel source) {
            return new Comment(source);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
}
