package com.freelance.ahmed.tourguideapp.Activities;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.freelance.ahmed.tourguideapp.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity {
    private String name;
    private String pic;
    private String bio;
    private String longi;
    private String lati;
    private ImageView mPoster;
    private TextView mName;
    private TextView mOverView;
    private Button mLocation;
    private final static String NAME_KEY = "NAME_KEY";
    private final static String PIC_KEY = "PIC_KEY";
    private final static String BIO_KEY = "BIO_KEY";
    private final static String LONGI_KEY = "LONGI_KEY";
    private final static String LATI_KEY = "LATI_KEY";
    private final static String FLAG = "Frag1";
    private final static String PACKAGE_MAP = "com.google.android.apps.maps";
    Uri mapUri;
    String urlofPoster;
    final static String BASE_POSTER_URL = "https://ahmedayman1708.000webhostapp.com/TourGuideApp/pics/phar/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        name = i.getExtras().getString(NAME_KEY);
        pic = i.getExtras().getString(PIC_KEY);
        bio = i.getExtras().getString(BIO_KEY);
        longi = i.getExtras().getString(LONGI_KEY);
        lati = i.getExtras().getString(LATI_KEY);
        mPoster = (ImageView) findViewById(R.id.posterimage);
        mName = (TextView) findViewById(R.id.tv_name);
        mOverView = (TextView) findViewById(R.id.tv_bio);
        mLocation = (Button) findViewById(R.id.location_btn);
        this.setTitle(name);
        mLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMap(lati, longi);
            }
        });
        urlofPoster = BASE_POSTER_URL + pic;
        settingDataToViews();

    }

    public void showMap(String lat, String longi) {
        String locattion = "geo:" + lat + "," + longi;
        Uri gmmIntentUri = Uri.parse(locattion);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage(PACKAGE_MAP);
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    public void settingDataToViews() {
        Picasso.with(this).load(urlofPoster).fit()
                .placeholder(R.drawable.noimage).centerCrop()
                .error(R.drawable.error).centerCrop()
                .into(mPoster);
        mName.setText(name);
        mOverView.setText(bio);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent parentIntent = NavUtils.getParentActivityIntent(this);
        parentIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(parentIntent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent parentIntent = NavUtils.getParentActivityIntent(this);
                parentIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(parentIntent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
