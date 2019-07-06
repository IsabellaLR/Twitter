package com.codepath.apps.restclienttemplate;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import cz.msebera.android.httpclient.Header;

public class DetailsActivity extends AppCompatActivity {

    TextView body;
    TextView username;
    ImageView profile;
    //    TextView name;
    private TwitterClient client;
    long uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
//        getSupportActionBar().setTitle("Tweet");

        client = TwitterApp.getRestClient(getApplicationContext());

        //1 - pass data back
        body = (TextView) findViewById(R.id.tvBody3);
        username = (TextView) findViewById(R.id.tvUserName3);
        profile = (ImageView) findViewById(R.id.ivProfileImage3);

        body.setText(getIntent().getStringExtra("description"));
        username.setText(getIntent().getStringExtra("name"));
//        Glide.with(this).load(getIntent().getStringExtra("url")).into(profile);

    }

    public void exit(View v) {
        finish();
    }
}
