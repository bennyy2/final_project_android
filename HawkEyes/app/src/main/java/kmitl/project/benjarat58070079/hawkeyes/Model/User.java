package kmitl.project.benjarat58070079.hawkeyes.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Benny on 10/11/2560.
 */

public class User implements Parcelable {

    private DatabaseReference databaseReference;
    private UserListener listener;

    public interface UserListener{
        void onCheckedUser(boolean status);

    }

    public void setListener(UserListener listener) {
        this.listener = listener;
    }

    private String id;
    private String display_name;
    private String email;
    private String image_url;

    public User(String id, String display_name, String email, String image_url) {
        this.id = id;
        this.display_name = display_name;
        this.email = email;
        this.image_url = image_url;
    }


    public void saveUserData(){
        Log.i("Status","saveUserData");
        databaseReference = FirebaseDatabase.getInstance().getReference("user/");
        databaseReference.child(this.id).child("display_name").setValue(this.display_name);
        databaseReference.child(this.id).child("id").setValue(this.id);
        databaseReference.child(this.id).child("email").setValue(this.email);
        databaseReference.child(this.id).child("image_url").setValue(this.image_url);
    }

    public void checkUser(){
        databaseReference = FirebaseDatabase.getInstance().getReference("user/");
        databaseReference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Status", "onDataChange");
                boolean status = true;

                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    if (data.getKey().equals(id)) {
                        status = true;
                        break;
                    } else {
                        status = false;
                    }
                }

                if (listener != null) {
                    Log.i("Status", "Listener " + String.valueOf(status));
                    listener.onCheckedUser(status);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("Status", "onCancelled");
            }


        });
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
