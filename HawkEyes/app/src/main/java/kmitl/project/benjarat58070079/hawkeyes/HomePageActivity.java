package kmitl.project.benjarat58070079.hawkeyes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import kmitl.project.benjarat58070079.hawkeyes.Fragment.NearByFragment;
import kmitl.project.benjarat58070079.hawkeyes.Fragment.NewFeedFragment;
import kmitl.project.benjarat58070079.hawkeyes.Model.User;

public class HomePageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private User user;
    private Button btnLogOut;
    private TextView txtEmail, txtUser;
    private CircleImageView imgProfile;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        user = getIntent().getParcelableExtra("user");
        txtUser = navigationView.getHeaderView(0).findViewById(R.id.txtUser);
        txtUser.setText(user.getDisplay_name());
        txtEmail = navigationView.getHeaderView(0).findViewById(R.id.txtEmail);
        txtEmail.setText(user.getEmail());
        imgProfile = navigationView.getHeaderView(0).findViewById(R.id.imgProfile);
        Picasso.with(this).load(user.getImage_url()).into(imgProfile);
        onNavigationItemSelected(this.navigationView.getMenu().getItem(0));

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    private void displaySelectedScreen(int id){
        Fragment fragment = null;

        switch(id){
            case R.id.nav_newfeed:
                Bundle bundle = new Bundle();
                bundle.putParcelable("user", user);
                fragment = new NewFeedFragment();
                fragment.setArguments(bundle);
                this.navigationView.setCheckedItem(R.id.nav_newfeed);

                break;
            case R.id.nav_nearby:
                fragment = new NearByFragment();
                this.navigationView.setCheckedItem(R.id.nav_nearby);
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(HomePageActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }

        if(fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_home_page, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        displaySelectedScreen(id);
        return true;
    }
}
