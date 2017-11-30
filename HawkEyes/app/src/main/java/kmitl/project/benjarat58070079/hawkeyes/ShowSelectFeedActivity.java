package kmitl.project.benjarat58070079.hawkeyes;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import kmitl.project.benjarat58070079.hawkeyes.Adapter.CommentAdapter;
import kmitl.project.benjarat58070079.hawkeyes.Adapter.PostAdapter;
import kmitl.project.benjarat58070079.hawkeyes.Model.Comment;
import kmitl.project.benjarat58070079.hawkeyes.Model.Post;
import kmitl.project.benjarat58070079.hawkeyes.Model.User;

import static java.security.AccessController.getContext;
import static kmitl.project.benjarat58070079.hawkeyes.R.id.mapFragment;

public class ShowSelectFeedActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Post post;
    private ArrayList<User> data_user;
    private ArrayList<Comment> allComment;
    private GoogleMap mGoogleMap;
    private User user;
    private TextView display_user, show_post, show_date, show_type;
    private EditText commentText;
    private CircleImageView profile_user;
    private RecyclerView listView;
    private DatabaseReference databaseReference;
    private CommentValidation commentValidation;

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
        commentText = findViewById(R.id.comment);
        user = getIntent().getParcelableExtra("user");
        setText();
        initMap();
        listView = findViewById(R.id.listView);
        listView.setLayoutManager(new GridLayoutManager(this, 1));
        readData();
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

    private void readData(){
        databaseReference = FirebaseDatabase.getInstance().getReference("comment/");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                allComment = new ArrayList<>();
                for(DataSnapshot dn: dataSnapshot.getChildren()){
                    Comment comment = dn.getValue(Comment.class);
                    if(comment.getPost_id().equals(post.getId())){
                        allComment.add(comment);
                    }

                }
                setUI();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void setUI() {

        CommentAdapter adapter = new CommentAdapter(this);
        adapter.setAllComment(this.allComment);
        adapter.setData_user(this.data_user);
        listView.setAdapter(adapter);
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

    public void onSaveComment(View view) {
        if(String.valueOf(commentText.getText()).equals("")){
            Toast.makeText(this.getApplicationContext(), commentValidation.getResult(String.valueOf(commentText.getText())), Toast.LENGTH_LONG).show();
        }else{
            Comment comment = new Comment();
            comment.setPost_id(post.getId());
            comment.setComment_text(String.valueOf(commentText.getText()));
            comment.setUser_id(user.getId());
            comment.saveComment();
            commentText.setText("");
            InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }


    }
}
