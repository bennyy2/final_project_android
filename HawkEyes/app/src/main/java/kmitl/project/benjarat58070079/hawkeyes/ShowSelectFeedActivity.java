package kmitl.project.benjarat58070079.hawkeyes;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kmitl.project.benjarat58070079.hawkeyes.Model.Post;
import kmitl.project.benjarat58070079.hawkeyes.Model.User;

import static kmitl.project.benjarat58070079.hawkeyes.R.id.mapFragment;

public class ShowSelectFeedActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Post post;
    private ArrayList<User> data_user;
    private GoogleMap mGoogleMap;
    TextView display_user, show_post, show_date, show_type;
    ImageView profile_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_select_feed);
        post = getIntent().getParcelableExtra("post");
        data_user = getIntent().getParcelableArrayListExtra("data_user");
        display_user = findViewById(R.id.display_user);
        show_post = findViewById(R.id.show_post);
        show_date = findViewById(R.id.show_date);
        show_type = findViewById(R.id.show_type);
        profile_user = findViewById(R.id.profile_user);
        setText();
        initMap();
    }

    public void setText(){
        show_post.setText(post.getText_post());
        show_date.setText(post.getDateTime());
        show_type.setText(post.getType());
        for(User user: data_user){
            if(user.getId().equals(post.getPost_user()) ){
                display_user.setText(user.getDisplay_name());
                Picasso.with(this).load(user.getImage_url()).into(profile_user);
                break;
            }
        }
    }

    private void initMap(){
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        pinLocation(post.getLatitude(), post.getLongtitude(), (float) 15.0);
    }

    private void pinLocation(Double latitude, Double longtitude, Float zoom) {
        LatLng latLng = new LatLng(latitude, longtitude);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        mGoogleMap.moveCamera(cameraUpdate);
        MarkerOptions options = new MarkerOptions().title("Post's Location")
                .position(new LatLng(latitude, longtitude));
        mGoogleMap.addMarker(options);
    }
}
