package kmitl.project.benjarat58070079.hawkeyes.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import kmitl.project.benjarat58070079.hawkeyes.AddNeewFeedActivity;
import kmitl.project.benjarat58070079.hawkeyes.Model.Post;
import kmitl.project.benjarat58070079.hawkeyes.Model.User;
import kmitl.project.benjarat58070079.hawkeyes.R;
import kmitl.project.benjarat58070079.hawkeyes.ShowSelectFeedActivity;

/**
 * Created by Benny on 21/11/2560.
 */



public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder> {

    private ArrayList<Post> data;
    private ArrayList<User> data_user;
    private User user;
    private Activity activity;
    private Context context;

    public PostAdapter(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.post_item, null);
        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Post post = this.data.get(position);
        holder.post.setText(post.getText_post());
        holder.date.setText(post.getDateTime());

        for(User user: data_user){
            if(user.getId().equals(post.getPost_user()) ){
                holder.post_user.setText(user.getDisplay_name());
                Picasso.with(context).load(user.getImage_url()).into(holder.profile_image);
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setData(ArrayList<Post> data) {
        this.data = data;
    }

    public void setData_user(ArrayList<User> data_user) {
        this.data_user = data_user;
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private CircleImageView profile_image;
        private TextView post, date, post_user;
        private CardView post_view;
        private Holder(View itemView){
            super(itemView);

            profile_image = itemView.findViewById(R.id.profileImg);
            post = itemView.findViewById(R.id.post);
            date = itemView.findViewById(R.id.date);
            post_user = itemView.findViewById(R.id.post_user);
            post_view = itemView.findViewById(R.id.post_view);
            post_view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Post post = data.get(getAdapterPosition());
            Intent intent = new Intent(context, ShowSelectFeedActivity.class);
            intent.putExtra("post", post);
            intent.putExtra("user", user);
            intent.putParcelableArrayListExtra("data_user", data_user);
            context.startActivity(intent);
        }
    }

}
