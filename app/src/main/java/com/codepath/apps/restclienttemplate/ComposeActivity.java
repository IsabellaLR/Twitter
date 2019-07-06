package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {

    EditText description;
    TextView name;
    private TwitterClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        getSupportActionBar().setTitle("Post Tweet");

        client = TwitterApp.getRestClient(getApplicationContext());
//        name = (TextView) findViewById(R.id.tvName);
//        String username = "@"+ getIntent().getStringExtra("name");

        //1 - pass data back
        description = (EditText) findViewById(R.id.et_simple);
//        description.setText("@"+ getIntent().getStringExtra("name") + " ");
    }

    public void onSend(View v) {

        String text = description.getText().toString();

        //2 - sendTweet
        //on success
        client.sendTweet(text, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                // Prepare data intent
                Intent data = new Intent();
                // Pass relevant data back as a result
                // Activity finished ok, return the data
                 // set result code and bundle data for response
                //sendTweet
                Tweet tweet = null;
                try {
                    tweet = Tweet.fromJSON((response));
                    data.putExtra("tweet", Parcels.wrap(tweet));
//                    data.putExtra("setThread", setThread);
                    setResult(RESULT_OK, data);
                    finish(); // closes the activity, pass data to parent
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("TwitterClient", responseString);
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();
            }
        });
    }
}
