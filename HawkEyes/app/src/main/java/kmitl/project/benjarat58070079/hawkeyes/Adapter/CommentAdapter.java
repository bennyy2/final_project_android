package kmitl.project.benjarat58070079.hawkeyes.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kmitl.project.benjarat58070079.hawkeyes.Model.Comment;
import kmitl.project.benjarat58070079.hawkeyes.Model.User;
import kmitl.project.benjarat58070079.hawkeyes.R;

/**
 * Created by Benny on 24/11/2560.
 */

public class CommentAdapter extends RecyclerView.Adapter<Holder> {

    private ArrayList<Comment> allComment;
    private ArrayList<User> data_user;
    private Context context;

    public CommentAdapter(Context context) {
        this.context = context;
        allComment = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.comment_item, null);
        Holder holder = new Holder(itemView);
        Log.i("CommentPost", "onCreateView");
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Comment comment = this.allComment.get(position);
        holder.commentView.setText(comment.getComment_text());
        for(User user: data_user){
            if(user.getId().equals(comment.getUser_id()) ){
                holder.post_user.setText(user.getDisplay_name());
                Picasso.with(context).load(user.getImage_url()).into(holder.profileImg);
                break;
            }
        }
        Log.i("CommentPost", String.valueOf(data_user));

    }

    @Override
    public int getItemCount() {
        return this.allComment.size();
    }

    public void setAllComment(ArrayList<Comment> allComment) {
        this.allComment = allComment;
    }

    public void setData_user(ArrayList<User> data_user) {
        this.data_user = data_user;
    }


}
class Holder extends RecyclerView.ViewHolder {

    TextView post_user, commentView;
    ImageView profileImg;


    public Holder(View itemView) {
        super(itemView);
        post_user = itemView.findViewById(R.id.post_user);
        commentView = itemView.findViewById(R.id.commentView);
        profileImg = itemView.findViewById(R.id.profileImg);

    }
}
