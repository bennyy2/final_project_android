package kmitl.project.benjarat.hawkeye.model;

import android.net.Uri;

import com.google.firebase.database.DatabaseReference;


/**
 * Created by Benny on 27/10/2560.
 */

public class User {

    private String id;
    private String name;
    private String email;
    private Uri profileImg;
    private DatabaseReference mDatabase;

    public User() {
    }

    public User(String id, String name, String email, Uri profileImg) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profileImg = profileImg;
    }

    public void writeNewUser() {

        mDatabase.child("users").child(getId()).setValue(getName());
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Uri getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(Uri profileImg) {
        this.profileImg = profileImg;
    }
}
