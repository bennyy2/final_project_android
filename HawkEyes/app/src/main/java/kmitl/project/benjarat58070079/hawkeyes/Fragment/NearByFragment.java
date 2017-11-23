package kmitl.project.benjarat58070079.hawkeyes.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kmitl.project.benjarat58070079.hawkeyes.Model.Post;
import kmitl.project.benjarat58070079.hawkeyes.R;

import static android.content.Context.LOCATION_SERVICE;


/**
 * A simple {@link Fragment} subclass.

 */
public class NearByFragment extends Fragment implements OnMapReadyCallback {

    private TextView locationView;
    private Button getLocation;
    private LocationManager locationManager;
    private ArrayList<String> type = new ArrayList<>();
    private Spinner spinner;
    private MapView mapView;
    private GoogleMap mGoogleMap;
    private Post post;
    private ArrayList<Post> postByType;
    private String selectType;
    private ImageButton btnType;
    private Marker marker;
    private DatabaseReference databaseReference;




    public NearByFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Near By Event");
        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_near_by, container, false);
        eventType();
        spinner = rootView.findViewById(R.id.event_type);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_dropdown_item_1line, type);
        spinner.setAdapter(adapter);
        btnType = rootView.findViewById(R.id.selectType);
        btnType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectType = String.valueOf(spinner.getSelectedItem());
                fetchData();
            }
        });








//        locationView = rootView.findViewById(R.id.locationView);
//        getLocation = rootView.findViewById(R.id.getLocation);
//        locationManager = (LocationManager) this.getContext().getSystemService(LOCATION_SERVICE);
//        listener = new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//                locationView.append("\n " + location.getLongitude() + " " + location.getLatitude());
//            }
//
//            @Override
//            public void onStatusChanged(String s, int i, Bundle bundle) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String s) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String s) {
//
//            }
//        };
//        configure_button();
        return rootView;


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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
    }

    public void fetchData(){
        databaseReference = FirebaseDatabase.getInstance().getReference("post/");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                postByType = new ArrayList<>();

                for (DataSnapshot dn : dataSnapshot.getChildren()) {
                        Post post = dn.getValue(Post.class);

                        if(post.getType().equals(selectType)){
                            postByType.add(post);
                        }

                }
                pinLocation();

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    private void pinLocation() {
        mGoogleMap.clear();
        for(Post post:postByType){

            MarkerOptions options = new MarkerOptions().title(post.getText_post())
                    .position(new LatLng(post.getLatitude(), post.getLongtitude()));
            mGoogleMap.addMarker(options);
            LatLng latLng = new LatLng(post.getLatitude(), post.getLongtitude());
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 8);
            mGoogleMap.moveCamera(cameraUpdate);
        }



    }




}






