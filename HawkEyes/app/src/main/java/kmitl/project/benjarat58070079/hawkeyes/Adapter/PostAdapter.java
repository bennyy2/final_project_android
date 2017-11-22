package kmitl.project.benjarat58070079.hawkeyes.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kmitl.project.benjarat58070079.hawkeyes.AddNeewFeedActivity;
import kmitl.project.benjarat58070079.hawkeyes.Model.Post;
import kmitl.project.benjarat58070079.hawkeyes.R;
import kmitl.project.benjarat58070079.hawkeyes.ShowSelectFeedActivity;

/**
 * Created by Benny on 21/11/2560.
 */



public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder> {

    private ArrayList<Post> data;
    private Activity activity;
    private Context context;
//    private final View.OnClickListener mOnClickListener = new MyOnClickListener();


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
        holder.post_user.setText(post.getPost_user());

//        String url = data.get(position).getUrl();
//        String getlike = data.get(position).getLike();
//        String getcomment = data.get(position).getComment();
//        //context get from activity
//        Glide.with(context)
//                .load(url)
//                .into(holder.image);
//
//        TextView post = holder.post;
//        post.setText();
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public void setData(ArrayList<Post> data) {
        this.data = data;
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView profile_image;

        private TextView post, date, post_user;
        private LinearLayout post_view;

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
            context.startActivity(intent);
        }
    }

}
