package kmitl.project.benjarat58070079.hawkeyes.Fragment;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import kmitl.project.benjarat58070079.hawkeyes.AddNeewFeedActivity;
import kmitl.project.benjarat58070079.hawkeyes.HomePageActivity;
import kmitl.project.benjarat58070079.hawkeyes.MainActivity;
import kmitl.project.benjarat58070079.hawkeyes.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewFeedFragment extends Fragment {


    public NewFeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("New Feed");
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
                startActivity(intent);

            }
        });
//        if (serviceAvailable()){
//            Toast.makeText(this.getContext(), "success.", Toast.LENGTH_LONG).show();
//        }
        return rootView;
    }

//    public boolean serviceAvailable(){
//        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
//        int available = api.isGooglePlayServicesAvailable(this.getContext());
//        if(available == ConnectionResult.SUCCESS){
//            return true;
//        }else if(api.isUserResolvableError(available)){
//            Toast.makeText(this.getContext(), "not avilable.", Toast.LENGTH_LONG).show();
//        }else{
//            Toast.makeText(this.getContext(), "can not connet play service.", Toast.LENGTH_LONG).show();
//        }
//        return false;
//
//    }



}
