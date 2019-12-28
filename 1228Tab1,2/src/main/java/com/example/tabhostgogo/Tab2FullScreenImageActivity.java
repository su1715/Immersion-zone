package com.example.tabhostgogo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class Tab2FullScreenImageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2_activity_full_screen_image);
        ImageView fullScreenImageView=(ImageView) findViewById(R.id.fullScreenImageView);
        Intent callingActivityIntent=getIntent();
        String name=callingActivityIntent.getExtras().getString("imgPath");


        if(callingActivityIntent!=null){
//            Uri imageUri=callingActivityIntent.getData();
            if(name!=null && fullScreenImageView!=null){
                Glide.with(this)
                        .load(name)
                        .into(fullScreenImageView);
            }
        }

    }
}
