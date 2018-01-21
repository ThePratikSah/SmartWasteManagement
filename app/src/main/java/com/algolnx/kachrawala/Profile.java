package com.algolnx.kachrawala;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextView textName, textEmail;
    ImageView profilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();

        setUpToolbar();

        FirebaseUser user = mAuth.getCurrentUser();
        profilePicture = findViewById(R.id.profileImage);
        Uri profilePicUrl = user.getPhotoUrl();
        Picasso.with(Profile.this).load(profilePicUrl).centerCrop().fit().into(profilePicture);
        textName = findViewById(R.id.name);
        textName.setText(user.getDisplayName());
        textEmail = findViewById(R.id.email);
        textEmail.setText(user.getEmail());

    }

    private void setUpToolbar(){
        Toolbar toolbar = findViewById(R.id.my_awesome_toolbar);
        toolbar.setTitle("Profile");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar);
    }

}
