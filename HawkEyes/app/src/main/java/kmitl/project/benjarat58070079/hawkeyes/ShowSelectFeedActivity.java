package kmitl.project.benjarat58070079.hawkeyes;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import kmitl.project.benjarat58070079.hawkeyes.Model.Post;
import kmitl.project.benjarat58070079.hawkeyes.Model.User;

public class ShowSelectFeedActivity extends AppCompatActivity {

    private Post post;
    TextView display_user, show_post, show_date, show_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_select_feed);
        post = getIntent().getParcelableExtra("post");
        display_user = findViewById(R.id.display_user);
        show_post = findViewById(R.id.show_post);
        show_date = findViewById(R.id.show_date);
        show_type = findViewById(R.id.show_type);
        setText();
    }

    public void setText(){
        display_user.setText(post.getPost_user());
        show_post.setText(post.getText_post());
        show_date.setText(post.getDateTime());
        show_type.setText(post.getType());
    }


}
