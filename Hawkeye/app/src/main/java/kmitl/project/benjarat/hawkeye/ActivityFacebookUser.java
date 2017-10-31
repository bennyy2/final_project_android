package kmitl.project.benjarat.hawkeye;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import kmitl.project.benjarat.hawkeye.model.User;

public class ActivityFacebookUser extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    private Button btnLogOut;
    private TextView txtEmail, txtUser, txtId;
    private ImageView imgProfile;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_user);

        mAuth = FirebaseAuth.getInstance();
        btnLogOut = (LoginButton) findViewById(R.id.btnLogOut);
        txtUser = (TextView) findViewById(R.id.txtUser);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtId = (TextView) findViewById(R.id.txtId);
        imgProfile = (ImageView) findViewById(R.id.imgProfile);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseuser = firebaseAuth.getCurrentUser();
//                Log.v("Status", firebaseuser.getDisplayName());
                if (firebaseuser != null) {

                    for (UserInfo userInfo : firebaseuser.getProviderData()) {
                        Log.d("TAG", userInfo.getProviderId());
                    }

                    User user = new User(firebaseuser.getUid(), firebaseuser.getDisplayName(), firebaseuser.getEmail(), firebaseuser.getPhotoUrl());

                    txtId.setText(user.getId());
                    txtUser.setText(user.getName());
                    txtEmail.setText(user.getEmail());
                    Picasso.with(ActivityFacebookUser.this).load(user.getProfileImg()).into(imgProfile);
                } else {
                    Intent intent = new Intent(ActivityFacebookUser.this, MainActivity.class);
                    intent.putExtra("logout", true);
                    startActivity(intent);
                    finish();
                }
            }
        };

        mAuth.addAuthStateListener(mAuthListener);
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        User user = new User();
//        user.writeNewUser();




    }
}
