package kmitl.project.benjarat58070079.hawkeyes.Fragment;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import kmitl.project.benjarat58070079.hawkeyes.Adapter.PostAdapter;
import kmitl.project.benjarat58070079.hawkeyes.AddNeewFeedActivity;
import kmitl.project.benjarat58070079.hawkeyes.HomePageActivity;
import kmitl.project.benjarat58070079.hawkeyes.MainActivity;
import kmitl.project.benjarat58070079.hawkeyes.Model.Post;
import kmitl.project.benjarat58070079.hawkeyes.Model.User;
import kmitl.project.benjarat58070079.hawkeyes.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewFeedFragment extends Fragment {

    private User user;
    private ArrayList<Post> allPost;
    private RecyclerView listView;


    public NewFeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("New Feed");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.allPost = new ArrayList<>();
        Log.i("Status", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_newfeed, container, false);
        FloatingActionButton addBtn = rootView.findViewById(R.id.btnFAB);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddNeewFeedActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);

            }
        });
        if (getArguments() != null) {
            user = getArguments().getParcelable("user");
        }

        this.listView = rootView.findViewById(R.id.listView);
        this.listView.setLayoutManager(new GridLayoutManager(rootView.getContext(), 1));
        readData();
        return rootView;
    }


    private void readData(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("post/");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                allPost = new ArrayList<>();

                for (DataSnapshot dn: dataSnapshot.getChildren()) {
                    Post post = dn.getValue(Post.class);
                    allPost.add(post);

//                    String key = dataSnapshot.getValue();
                }

                setUI();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setUI() {
        PostAdapter adapter = new PostAdapter(getActivity(), getContext());
        adapter.setData(this.allPost);
        this.listView.setAdapter(adapter);
    }



}
