package in.gov.chandigarh.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import in.gov.chandigarh.Adapter.MyAdapter;
import in.gov.chandigarh.Database.DatabaseFavrt;
import in.gov.chandigarh.Database.Model_Getter_Setter;
import in.gov.chandigarh.Extra_Classes.ClassForArray;
import in.gov.chandigarh.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    RequestQueue queue;
    String TAG = "courserequest";
    String response1;
    ListView lst1;
    static String url;
    Model_Getter_Setter model;
    public static ArrayList<Model_Getter_Setter> arr = new ArrayList<Model_Getter_Setter>();
    int index;
    Toolbar toolbar;
    int value;
    Button btn_loc;
    String item;
    DatabaseFavrt dbf1;


    //android.app.ActionBar ab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        dbf1 = new DatabaseFavrt(ListActivity.this);
        index = getIntent().getIntExtra("key1", 0);
        value = getIntent().getIntExtra("key2", 0);
        if (value == 1) {
            toolbar.setTitle(ClassForArray.hotels_title[index]);
            item = ClassForArray.arr_hotels[index];
        }
        if (value == 2) {
            toolbar.setTitle(ClassForArray.places_title[index]);
            item = ClassForArray.arr_places[index];
        }
        if (value == 3) {
            toolbar.setTitle(ClassForArray.entertainment_Title[index]);
            item = ClassForArray.arr_entertainment[index];
        }
        if (value == 4) {
            toolbar.setTitle(ClassForArray.shoping_title[index]);
            item = ClassForArray.arr_shoping[index];
        }
        if (value == 5) {
            toolbar.setTitle(ClassForArray.saloon_title[index]);
            item = ClassForArray.arr_saloon[index];
        }
        if (value == 6) {
            toolbar.setTitle(ClassForArray.education_title[index]);
            item = ClassForArray.arr_education[index];
        }
        if (value == 8) {
            toolbar.setTitle(ClassForArray.transport_title[index]);
            item = ClassForArray.arr_transport[index];
        }
        if (value == 9) {
            toolbar.setTitle(ClassForArray.health_title[index]);
            item = ClassForArray.arr_health[index];
        }

        if (value == 10) {
            toolbar.setTitle(ClassForArray.helpdesk_title[index]);
            item = ClassForArray.arr_helpdesk[index];
        }
        if (value == 11) {
            toolbar.setTitle(ClassForArray.service_title[index]);
            item = ClassForArray.arr_service[index];
        }

        setSupportActionBar(toolbar);


        //back button code
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
       /* toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });*/
        lst1 = (ListView) findViewById(R.id.lst);
        lst1.setOnItemClickListener(this);
        btn_loc = (Button) findViewById(R.id.btn_loc);
        btn_loc.setOnClickListener(this);
        arr.clear();
        url = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + item + "+in+Chandigarh&key=AIzaSyDDGnGXfMSJASUkudAzyIjaOXuCeWxxyN0";
        //  VollyLibClass obj = new VollyLibClass(getActivity(),url);
        //   obj.VollyResponse();

        queue = Volley.newRequestQueue(this);
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject Joj = new JSONObject(response);
                            JSONArray jarr = Joj.getJSONArray("results");
                            for (int i = 0; i < jarr.length(); i++) {
                                JSONObject jobj1 = jarr.getJSONObject(i);
                                String format_address = jobj1.getString("formatted_address");
                                JSONObject jobj2 = jobj1.getJSONObject("geometry");
                                JSONObject jobj3 = jobj2.getJSONObject("location");
                                double lat = jobj3.getDouble("lat");
                                double log = jobj3.getDouble("lng");

                                JSONObject jobj4 = jobj2.getJSONObject("viewport");
                                JSONObject jobj5 = jobj4.getJSONObject("northeast");
                                String nlat = jobj5.getString("lat");
                                String nlog = jobj5.getString("lng");
                                JSONObject jobj6 = jobj4.getJSONObject("southwest");
                                String slat = jobj6.getString("lat");
                                String slog = jobj6.getString("lng");
                                String icon = jobj1.getString("icon");
                                String id = jobj1.optString("id","");
                                String name = jobj1.getString("name");
                                String photo = null;
                                String width = null;

                                if (jobj1.has("photos")) {
                                    JSONArray jarr1 = jobj1.getJSONArray("photos");
                                    for (int j = 0; j < jarr1.length(); j++) {
                                        JSONObject jobj7 = jarr1.getJSONObject(j);
                                        String hight = jobj7.getString("height");
                                        photo = jobj7.getString("photo_reference");
                                        width = jobj7.getString("width");

                                    }
                                }
                                String rating = "No Rating";
                                String place_id = jobj1.getString("place_id");
                                if (jobj1.has("rating")) {
                                    rating = jobj1.getString("rating");
                                }
                                String reference = jobj1.getString("reference");
                                model = new Model_Getter_Setter(name, format_address, rating, lat, log, icon, photo, width, reference, id);
                                arr.add(model);
                            }
                            lst1.setAdapter(new MyAdapter(ListActivity.this, arr));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        pDialog.dismiss();


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
            }
        });
        stringRequest.setTag(TAG);
// Add the request to the RequestQueue.
        queue.add(stringRequest);
        //  Log.e("===response==","=="+arr.size());

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

    @Override
    public void onClick(View view) {
        if (view == btn_loc) {
            Intent in = new Intent(ListActivity.this, MapsActivity.class);
            startActivity(in);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        int count;
        try {
            dbf1.open();
            count = dbf1.getItem(arr.get(i).getId());
            dbf1.close();
        } catch (Exception e) {
            count = 0;
        }
        Intent in = new Intent(ListActivity.this, DescActivity.class);
        in.putExtra("count", count);
        in.putExtra("index", i);
        // in.putExtra("nm",count);
        startActivity(in);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Intent in = new Intent(ListActivity.this,TestActivity.class);
        //startActivity(in);
    }
}
