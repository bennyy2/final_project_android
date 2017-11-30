package kmitl.project.benjarat58070079.hawkeyes;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.opengl.Visibility;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wang.avi.AVLoadingIndicatorView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import kmitl.project.benjarat58070079.hawkeyes.Model.Post;
import kmitl.project.benjarat58070079.hawkeyes.Model.User;


public class MainActivity extends AppCompatActivity  {

    private CallbackManager mCallbackManager;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private AVLoadingIndicatorView avLoadingIndicatorView;
    private ConstraintLayout loadingView;
    private DatabaseReference databaseReference;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginButton loginButton = (LoginButton) findViewById(R.id.btnLogin);

        loginButton.setReadPermissions("email", "public_profile");
        mCallbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>(){

            @Override
            public void onSuccess(LoginResult loginResult) {
                showLoadingView();
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() == null) {
            LoginManager.getInstance().logOut();
        }

        loadingView = findViewById(R.id.loadingView);
        avLoadingIndicatorView = findViewById(R.id.loading);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        signInFaceBook(currentUser);
    }

    private void signInFaceBook(FirebaseUser currentUser) {
        if(currentUser != null){
            user = new User(currentUser.getUid(), currentUser.getDisplayName(),
                    currentUser.getEmail(), String.valueOf(currentUser.getPhotoUrl()));
            user.saveUserData();
            showHomePageActivity();

        }

    }

    private void showHomePageActivity() {
        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }



    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            signInFaceBook(user);
                        } else {
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            signInFaceBook(null);
                        }

                    }
                });
    }

    private void showLoadingView() {
        avLoadingIndicatorView.show();
        loadingView.setVisibility(View.VISIBLE);
    }

    private void hideLoadingView() {
        loadingView.setVisibility(View.GONE);
    }

}
