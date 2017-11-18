package kmitl.project.benjarat58070079.hawkeyes;

import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class AddNeewFeedActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mgoogleMap;
    private Spinner spinner;

    private ArrayList<String> type = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_neew_feed);

//        if (serviceAvailable()){
//            Toast.makeText(this, "success.", Toast.LENGTH_LONG).show();

//        }

        initMap();
        spinner = findViewById(lli.id.event_type);
        eventType();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, type);
        spinner.setAdapter(adapter);

    }

    private void eventType() {
        type.add("Accident");
        type.add("Murder");
        type.add("Robbery");
        type.add("Kidnap");
        type.add("Illegal trading");
        type.add("Gambling");
        type.add("Stalking");
        type.add("Other");
    }

    private void initMap() {
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        supportMapFragment.getMapAsync(this);
    }

    public boolean serviceAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int available = api.isGooglePlayServicesAvailable(this);
        if (available == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(available)) {
            Toast.makeText(this, "not avilable.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "can not connet play service.", Toast.LENGTH_LONG).show();
        }
        return false;

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mgoogleMap = googleMap;
//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        mFusedLocationClient.getLastLocation()
//                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
//                    @Override
//                    public void onSuccess(Location location) {
//                        if(location != null)
//                    }
//                });
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();

        pinLocation(latitude, longitude);
    }



    private void pinLocation(double latitude, double longtitude) {
        LatLng currentLocation = new LatLng(latitude, longtitude);

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mgoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,15));
        // Zoom in, animating the camera.
        mgoogleMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        mgoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
//        CameraPosition camPos = new CameraPosition.Builder().target(currentLocation)
//                .zoom(60)
//                .bearing(45)
//                .tilt(65)
//                .build();
//
//        mgoogleMap.setMyLocationEnabled(true);
//        CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);
//        mgoogleMap.animateCamera(camUpd3);
        mgoogleMap.addMarker(new MarkerOptions().position(currentLocation).title("Your location is" + latitude + " : " + longtitude));

//        mgoogleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mgoogleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }





}
