package in.gov.chandigarh.Activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import in.gov.chandigarh.Database.DatabaseFavrt;
import in.gov.chandigarh.Database.Model_Getter_Setter;
import in.gov.chandigarh.Extra_Classes.GPSTracker;
import in.gov.chandigarh.R;

public class DescActivity extends AppCompatActivity{
    Toolbar toolbar;
    int index;
    ImageView img_desc;
    TextView text_name,text_address,rating,text_status,text_number,text_web,text_gallery,text_distance;
    FloatingActionButton fab;
    String reference;
    RequestQueue queue;
    String TAG="courserequest";
    String response1;
   // ListView lst1;
    static String url1;
   Model_Getter_Setter model1;
    public ArrayList<String> arr1 = new ArrayList<String>();

    private GoogleMap mMap;
    GPSTracker gps;
    LatLng aaa;
    boolean checkper,isGranted;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION=0;
    static  final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION=0;
    SupportMapFragment mapFragment;
    LatLng mak;
    double lati,lng;
    String photo_reference = "";
    String formatted_phone_number = "";
    String international_phone_number = "";
    String format_address = "";
    String open_now = "";
    String website = "";
    String nam = "";
    String id="";
    DatabaseFavrt dbf;
    ImageButton favrt;
  int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        index=getIntent().getIntExtra("index",0);
        count=getIntent().getIntExtra("count",0);
        toolbar.setTitle(ListActivity.arr.get(index).getName());

        setSupportActionBar(toolbar);

        reference=ListActivity.arr.get(index).getReference();
        arr1.clear();
        favrt=(ImageButton)findViewById(R.id.favorite_button);
        img_desc=(ImageView)findViewById(R.id.img_desc);
        text_name=(TextView)findViewById(R.id.text_name_desc);
        text_address=(TextView)findViewById(R.id.text_address_desc);
        rating=(TextView) findViewById(R.id.rating_desc);
        text_status=(TextView)findViewById(R.id.text_status_desc);
        text_number=(TextView)findViewById(R.id.text_number_desc);
        text_web=(TextView)findViewById(R.id.text_web_desc);
        text_gallery=(TextView)findViewById(R.id.text_gallery_desc);
        text_distance=(TextView)findViewById(R.id.text_distance_desc);
        text_name.setText(ListActivity.arr.get(index).getName());
        text_address.setText(ListActivity.arr.get(index).getFormat_add());
        rating.setText(ListActivity.arr.get(index).getRating());
        dbf=new DatabaseFavrt(DescActivity.this);



        String url= "https://maps.googleapis.com/maps/api/place/photo?maxwidth="+ListActivity.arr.get(index).getWidth()+"&photoreference="+ListActivity.arr.get(index).getPhoto()+"&key=AIzaSyDZJHfok-TWWSkoWj5mN8ewfnIiWnMaWNs";
        Picasso.with(this).load(url).resize(2500,1200).into(img_desc);


        gps = new GPSTracker(DescActivity.this, DescActivity.this);

        if(!reference.equals(null)) {
            url1 = "https://maps.googleapis.com/maps/api/place/details/json?reference=" + reference + "&key=AIzaSyBcvQ6--bA84u0bGVBL1XXNrl7Pjv1M3rY";

            queue = Volley.newRequestQueue(this);
            final ProgressDialog pDialog = new ProgressDialog(this);
            pDialog.setMessage("Loading...");
            pDialog.show();

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url1,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jobj = new JSONObject(response);
                              JSONObject jobj1 = jobj.getJSONObject("result");
                                  if (jobj1.has("formatted_address")) {
                                    format_address = jobj1.getString("formatted_address");
                                }
                                if (jobj1.has("formatted_phone_number")) {
                                    formatted_phone_number = jobj1.getString("formatted_phone_number");
                                }

                                if (jobj1.has("icon")) {
                                    String icon = jobj1.getString("icon");
                                }
                                if (jobj1.has("id")) {
                                    id = jobj1.getString("id");
                                }

                                if (jobj1.has("international_phone_number")) {
                                    international_phone_number = jobj1.getString("international_phone_number");
                                }
                                if (jobj1.has("name")) {
                                    nam = jobj1.getString("name");
                                }
                                if (jobj1.has("opening_hours")) {
                                    JSONObject jobj7 = jobj1.getJSONObject("opening_hours");

                                    if (jobj7.has("open_now")) {
                                        open_now = jobj7.getString("open_now");
                                    }

                                    if (jobj7.has("weekday_text")) {
                                        JSONArray jarr5 = jobj7.getJSONArray("weekday_text");
                                        String[] weekday_text = new String[jarr5.length()];
                                        for (int i = 0; i < jarr5.length(); i++) {
                                            weekday_text[i] = jarr5.getString(i);
                                        }
                                    }
                                }

                                if (jobj1.has("photos")) {
                                    JSONArray jarr3 = jobj1.getJSONArray("photos");
                                    for (int k = 0; k < jarr3.length(); k++) {
                                        JSONObject jobj11 = jarr3.getJSONObject(k);
                                        if (jobj11.has("photo_reference")) {
                                            photo_reference = jobj11.getString("photo_reference");
                                            arr1.add(photo_reference);
                                        }
                                    }
                                }
                                if (jobj1.has("reference")) {
                                    String reference1 = jobj1.getString("reference");
                                }
                                if (jobj1.has("website")) {
                                    website = jobj1.getString("website");

                                }

                            } catch (Exception e) {
                            }

                            pDialog.dismiss();
                            if (open_now.equals("")) {
                                text_status.setText("No Status");
                            } else {
                                if (open_now.equals("true")) {
                                    text_status.setText("Open");
                                } else {
                                    text_status.setText("Close");
                                }
                            }

                            if (formatted_phone_number.equals("") && international_phone_number.equals("")) {
                                text_number.setText("Unavailable");
                            } else {
                                text_number.setText(formatted_phone_number + "," + international_phone_number);
                            }

                            if (website.equals("")) {
                                text_web.setText("Unavailable");
                            } else {
                                text_web.setClickable(true);
                                text_web.setMovementMethod(LinkMovementMethod.getInstance());
                                String text = "<a href="+website+"> "+website+" </a>";
                                text_web.setText(Html.fromHtml(text));
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("===error==", "==" + error);
                    pDialog.dismiss();
                }
            });
            stringRequest.setTag(TAG);
// Add the request to the RequestQueue.
            queue.add(stringRequest);
            //  Log.e("===response==","=="+arr.size());
}


        text_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arr1.size() > 0) {

                    Intent in = new Intent(DescActivity.this, GalleryActivity.class);
                    in.putStringArrayListExtra("img_arr", arr1);
                    startActivity(in);

                } else {
                    Toast.makeText(getApplicationContext(), "No more photos...", Toast.LENGTH_LONG).show();
                }
            }
        });
        if(count>0)
        {
            favrt.setBackgroundResource(R.drawable.btn_star_big_on);
        }
        else
        {
            favrt.setBackgroundResource(R.drawable.btn_star_big_off);
        }
        favrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                   Drawable drawable = favrt.getBackground();
                    // if(drawable.equals())
                    if (drawable.getConstantState().equals(getResources().getDrawable(R.drawable.btn_star_big_off).getConstantState())) {
                        //Do your work here
                        favrt.setBackgroundResource(R.drawable.btn_star_big_on);
                        Toast.makeText(getApplicationContext(), "Added to Favorites", Toast.LENGTH_SHORT).show();
                        try {
                            dbf.open();
                            Log.e("...........",""+ListActivity.arr.get(index).getName());
                            Log.e("...........",""+ListActivity.arr.get(index).getId());
                            Log.e("...........",""+ListActivity.arr.get(index).getPhoto());

                            dbf.insert(ListActivity.arr.get(index).getPhoto(), ListActivity.arr.get(index).getName(), ListActivity.arr.get(index).getRating(), ListActivity.arr.get(index).getFormat_add(), ListActivity.arr.get(index).getId(),ListActivity.arr.get(index).getReference());
                            dbf.close();
                        } catch (Exception e) {
                            System.out.println("====Insert_Exception===" + e);
                        }


                    }
                    if (drawable.getConstantState().equals(getResources().getDrawable(R.drawable.btn_star_big_on).getConstantState())) {
                        //Do your work here
                        favrt.setBackgroundResource(R.drawable.btn_star_big_off);
                        Toast.makeText(getApplicationContext(), "Removed from Favorites", Toast.LENGTH_SHORT).show();
                        try {
                            dbf.open();
                           dbf.deleteItem(ListActivity.arr.get(index).getId());
                           // Log.e("....delete.....",""+chk);
                            dbf.close();
                        } catch (Exception e) {
                            System.out.println("====Delete_Exception===" + e);
                        }

                    }
            }
        });




        //CURRENT LOCATION
        if (gps.canGetLocation()) {


           double longitude = gps.getLongitude();
           double latitude = gps.getLatitude();
            aaa = new LatLng(latitude, longitude);
           // Toast.makeText(getApplicationContext(), "Longitude:" + Double.toString(longitude) + "\nLatitude:" + Double.toString(latitude), Toast.LENGTH_SHORT).show();
        } else {
            gps.showSettingsAlert();
        }
        checkper = checkPermission();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lati=aaa.latitude;
                lng=aaa.longitude;
                Intent intent = new Intent( Intent.ACTION_VIEW,
                        Uri.parse("http://ditu.google.cn/maps?f=d&source=s_d" +
                                "&saddr="+lati+","+lng+"&daddr="+ListActivity.arr.get(index).getLat()+", "+ListActivity.arr.get(index).getLng()+"&hl=zh&t=m&dirflg=w"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK&Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
                       /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
        //back button code
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        DistanceCal();

    }
    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(DescActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            isGranted = false;
            ActivityCompat.requestPermissions(DescActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }else  if (ContextCompat.checkSelfPermission(DescActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            isGranted = false;
            ActivityCompat.requestPermissions(DescActivity.this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
        }

        else {
            isGranted = true;

        }
        return isGranted;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void DistanceCal()
    {
        Location loc1 = new Location("");
        loc1.setLatitude(aaa.latitude);
        loc1.setLongitude(aaa.longitude);
        Location loc2 = new Location("");
        loc2.setLatitude(ListActivity.arr.get(index).getLat());
        loc2.setLongitude(ListActivity.arr.get(index).getLng());

        float distanceInMeters = loc1.distanceTo(loc2);

        int km=(int)distanceInMeters/1000;
        int meter=(int)distanceInMeters%1000;

        text_distance.setText(km+"."+meter+" Km");
    }

}
