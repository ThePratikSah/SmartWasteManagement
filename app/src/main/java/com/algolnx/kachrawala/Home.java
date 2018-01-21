package com.algolnx.kachrawala;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Home extends AppCompatActivity {

    Button mSelectImage;
    ImageView imageView;
    FirebaseAuth mAuth;
    StorageReference mStorage;
    Toolbar toolbar;
    private static final int GALLERY_INTENT = 2;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setUpToolbar();

        mStorage = FirebaseStorage.getInstance().getReference();
        mSelectImage = findViewById(R.id.uploadImage);
        imageView = findViewById(R.id.recentUpload);
        mProgressDialog = new ProgressDialog(this);

        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });


        mAuth = FirebaseAuth.getInstance();
    }

    private void setUpToolbar(){
        toolbar = findViewById(R.id.my_awesome_toolbar);
        toolbar.setTitle("Home");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menuShare:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, "Read more on https://algolnx.com");
                share.putExtra(android.content.Intent.EXTRA_SUBJECT,"Programming knowledge");
                startActivity(Intent.createChooser(share, "Share via"));
                break;

            case R.id.profile:
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);
                break;

            case R.id.menuGuide:
                Intent intent1 = new Intent(getApplicationContext(), RecycleGuide.class);
                startActivity(intent1);
                break;

            case R.id.menuEmergency:
                Intent intent2 = new Intent(getApplicationContext(), EmergencyPickup.class);
                startActivity(intent2);
                break;

            case R.id.graph:
                Intent intent3 = new Intent(getApplicationContext(), Graph.class);
                startActivity(intent3);
                break;

            case R.id.registerVolunteer:
                Intent intent4 = new Intent(getApplicationContext(), RegisterVolunteer.class);
                startActivity(intent4);
                break;

            case R.id.menuLogout:
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                finish();
                break;

        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK){

            mProgressDialog.setMessage("Uploading...");
            mProgressDialog.show();

            Uri uri = data.getData();

            StorageReference filepath = mStorage.child("Photos").child(uri.getLastPathSegment());

            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    mProgressDialog.dismiss();

                    Uri mDownloadUri = taskSnapshot.getDownloadUrl();
                    Picasso.with(Home.this).load(mDownloadUri).fit().centerCrop().into(imageView);

                    Toast.makeText(Home.this, "Image received by the municipality", Toast.LENGTH_SHORT).show();

                }
            });
        }

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() == null){
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
