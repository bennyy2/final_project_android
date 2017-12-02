package kmitl.project.benjarat58070079.hawkeyes;

import android.*;
import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ServerValue;

import java.io.IOException;
import java.io.StringBufferInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import kmitl.project.benjarat58070079.hawkeyes.Model.Post;
import kmitl.project.benjarat58070079.hawkeyes.Model.User;

public class AddNeewFeedActivity extends AppCompatActivity  {


    private Spinner spinner;
    private ArrayList<String> type = new ArrayList<>();
    private TextView placeAddress, tvLatitude, tvLongtitude;
    private final static int MY_PERMISSION_FINE_LOCATION = 101;
    private final static int PLACE_PICKER_REQUEST = 1;
    private Post post;
    private User user;
    private Place place;
    private EditText text_post;
    private int clickCheck = 0;
    private PostValidation postValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_neew_feed);
        requestPermissions();
        eventType();
        user = getIntent().getParcelableExtra("user");
        spinner = findViewById(R.id.event_type);
        text_post = findViewById(R.id.text_post);
        placeAddress = findViewById(R.id.placeAddress);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, type);
        spinner.setAdapter(adapter);
    }

    private void requestPermissions() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case MY_PERMISSION_FINE_LOCATION:
                if(grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "require permission to be granted.", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }

    private void eventType() {
        type.add("Accident");
        type.add("Murder");
        type.add("Robbery");
        type.add("Kidnap");
        type.add("Illegal trading");
        type.add("Gambling");
        type.add("Stalking");
        type.add("Physically assaulted");
        type.add("Things lost");
        type.add("Other");

    }


    public void onPlacePicker(View view) throws GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        Intent intent = builder.build(AddNeewFeedActivity.this);
        startActivityForResult(intent, PLACE_PICKER_REQUEST);
        clickCheck = 5;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PLACE_PICKER_REQUEST){
            if(resultCode == RESULT_OK){
                place = PlacePicker.getPlace(AddNeewFeedActivity.this, data);
                placeAddress.setText(String.valueOf(place.getAddress()));
            }
        }
    }

    public void onSubmitData(View view) {
        if(clickCheck == 0){
            Toast.makeText(this, "Select location of event", Toast.LENGTH_LONG).show();
        }
        else if(String.valueOf(text_post.getText()).equals("") || String.valueOf(text_post.getText()) == null){
            Toast.makeText(this, "please write your post before submit.", Toast.LENGTH_LONG).show();
        }
        else{
            Date currentTime = Calendar.getInstance().getTime();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String date = format.format(currentTime);
            Post post = new Post();
            post.setText_post(String.valueOf(text_post.getText()));
            post.setAddress(String.valueOf(place.getAddress()));
            post.setLatitude(place.getLatLng().latitude);
            post.setLongtitude(place.getLatLng().longitude);
            post.setPost_user(user.getId());
            post.setType(String.valueOf(spinner.getSelectedItem()));
            post.setDateTime(date);
            post.savePost();
            finish();
            Toast.makeText(this, "post", Toast.LENGTH_LONG).show();
        }


    }
}
