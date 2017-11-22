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

import java.lang.reflect.Array;
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

public class NewFeedFragment extends Fragment implements NewFeedListener {

    private User user;

    private ArrayList<Post> allPost;
    private ArrayList<User> allUser;
    private RecyclerView listView;
    private DatabaseReference databaseReference;

    private NewFeedListener listener;


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
        listener = this;
        readData();
        return rootView;
    }


    private void readData(){
        databaseReference = FirebaseDatabase.getInstance().getReference("post/");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                allPost = new ArrayList<>();

                for (DataSnapshot dn : dataSnapshot.getChildren()) {
                    Post post = dn.getValue(Post.class);
                    allPost.add(post);
                }
                if (listener != null) {
                    listener.OnFetchPostsData();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void setUI() {

        PostAdapter adapter = new PostAdapter(getActivity(), getContext());
        adapter.setData(this.allPost);
        adapter.setData_user(this.allUser);
        this.listView.setAdapter(adapter);
    }


    @Override
    public void OnFetchPostsData() {
        databaseReference = FirebaseDatabase.getInstance().getReference("user/");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                allUser = new ArrayList<>();
                for(DataSnapshot dn: dataSnapshot.getChildren()){
                    User user = dn.getValue(User.class);
                    allUser.add(user);
                }

                if (listener != null) {
                    listener.OnFetchUserData();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    @Override
    public void OnFetchUserData() {
        setUI();
    }
}

interface NewFeedListener {
    void OnFetchPostsData();
    void OnFetchUserData();
}
