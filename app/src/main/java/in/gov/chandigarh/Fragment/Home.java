package in.gov.chandigarh.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import in.gov.chandigarh.Database.Model_Getter_Setter;
import in.gov.chandigarh.R;
import android.text.format.DateFormat;

public class Home extends Fragment {
ImageView img;
    RequestQueue queue;
    String TAG="courserequest";
    String response1;
    ListView lst1;
    static String url;
    Model_Getter_Setter model;
    public static ArrayList<Model_Getter_Setter> arr = new ArrayList<Model_Getter_Setter>();
    TextView txt_day,txt_type,txt_temp,txt_name,txt_c_temp,txt_f_temp;
    String Unit1;
    double Value1;
    String LocalObservationDateTime;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = null;
    String WeatherText;
    boolean IsDayTime;
    ImageView img_temp;
    RelativeLayout rel_home;
    double Value2;
    String Unit2;
    Calendar c;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview=inflater.inflate(R.layout.fragment_home, container, false);
        txt_name=(TextView)rootview.findViewById(R.id.name_home);
        txt_day=(TextView)rootview.findViewById(R.id.day_home);
        txt_type=(TextView)rootview.findViewById(R.id.type_home);
        txt_temp=(TextView)rootview.findViewById(R.id.temp_home);
        txt_c_temp=(TextView)rootview.findViewById(R.id.temp_c_home);
        txt_f_temp=(TextView)rootview.findViewById(R.id.temp_f_home);
        img_temp=(ImageView) rootview.findViewById(R.id.img_temp);
        rel_home=(RelativeLayout)rootview.findViewById(R.id.rel_home);
        url ="http://dataservice.accuweather.com/currentconditions/v1/202350.json?apikey=0MtkE3VBo50hwOXZr1UKLeJyqshPq31f";
        //  VollyLibClass obj = new VollyLibClass(getActivity(),url);
        //   obj.VollyResponse();



        queue = Volley.newRequestQueue(getActivity());
        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jarr = new JSONArray(response);
                            for (int i = 0; i < jarr.length(); i++)
                            {
                                JSONObject jobj1=jarr.getJSONObject(i);
                                LocalObservationDateTime=jobj1.getString("LocalObservationDateTime");
                                int EpochTime=jobj1.getInt("EpochTime");
                                WeatherText=jobj1.getString("WeatherText");
                                int WeatherIcon=jobj1.getInt("WeatherIcon");
                                 IsDayTime=jobj1.getBoolean("IsDayTime");
                                JSONObject jobj2=jobj1.getJSONObject("Temperature");
                                JSONObject jobj3=jobj2.getJSONObject("Metric");
                                Value1=jobj3.getDouble("Value");
                                Unit1=jobj3.getString("Unit");
                                int UnitType=jobj3.getInt("UnitType");
                                JSONObject joj4=jobj2.getJSONObject("Imperial");
                                Value2=joj4.getDouble("Value");
                                Unit2=joj4.getString("Unit");
                                int UnitType2=jobj3.getInt("UnitType");

                                String MobileLink=jobj1.getString("MobileLink");
                                String Link=jobj1.getString("Link");
                            }
                            c = Calendar.getInstance();
                     //       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                           String formatteddate= format.format(c.getTime());
// Now formattedDate have current date/time
                           /* Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();
                            String[] dat = LocalObservationDateTime.split("T");
                            String[] time=dat[1].split("\\+");*/
                            date = format.parse(formatteddate);
                            String dayOfTheWeek = (String) DateFormat.format("EEEE", date); // Thursday
                            String day          = (String) DateFormat.format("dd",   date); // 20
                            String monthString  = (String) DateFormat.format("MMM",  date); // Jun
                            String monthNumber  = (String) DateFormat.format("MM",   date); // 06
                            String year         = (String) DateFormat.format("yyyy", date);
                            String hour         = (String) DateFormat.format("hh", date);
                            String mm         = (String) DateFormat.format("mm", date);
                            String zone         = (String) DateFormat.format("a", date);

                            txt_day.setText(dayOfTheWeek+" "+hour+":"+mm+" "+zone);
                            txt_type.setText(""+WeatherText);
                            txt_temp.setText(""+Value1);
                            txt_c_temp.setText(" ⁰"+Unit1+" ");
                            txt_f_temp.setText("| ⁰"+Unit2);
                            if(IsDayTime==true)
                            {
                                if(WeatherText.equals("Clear")) {
                                    img_temp.setBackgroundResource(R.drawable.sun_clear);
                                    rel_home.setBackgroundResource(R.drawable.day_clear);
                                }
                               else if(WeatherText.equals("Mostly sunny")) {
                                    img_temp.setBackgroundResource(R.drawable.sun_mostly_sunny);
                                    rel_home.setBackgroundResource(R.drawable.day_mostly_sunny);
                                }
                                else if(WeatherText.equals("Partly sunny")) {
                                    img_temp.setBackgroundResource(R.drawable.sun_partly_sunny);
                                    rel_home.setBackgroundResource(R.drawable.day_partly_sunny);
                                }
                                else if(WeatherText.equals("Mostly cloudy")) {
                                    img_temp.setBackgroundResource(R.drawable.sun_partly_sunny);
                                    rel_home.setBackgroundResource(R.drawable.day_mostly_cloudy);
                                    txt_name.setTextColor(getResources().getColor(R.color.temp));
                                    txt_day.setTextColor(getResources().getColor(R.color.temp));
                                    txt_type.setTextColor(getResources().getColor(R.color.temp));
                                    txt_temp.setTextColor(getResources().getColor(R.color.temp));
                                    txt_c_temp.setTextColor(getResources().getColor(R.color.temp));
                                    txt_f_temp.setTextColor(getResources().getColor(R.color.temp));
                                }
                                else if(WeatherText.equals("Partly cloudy")) {
                                    img_temp.setBackgroundResource(R.drawable.sun_partly_sunny);
                                    rel_home.setBackgroundResource(R.drawable.day_partly_cloudy);
                                }
                                else if(WeatherText.equals("Some clouds")) {
                                    img_temp.setBackgroundResource(R.drawable.sun_partly_sunny);
                                    rel_home.setBackgroundResource(R.drawable.day_partly_sunny);
                                }
                                else if(WeatherText.equals("Cloudy")) {
                                    img_temp.setBackgroundResource(R.drawable.cloudy);
                                    rel_home.setBackgroundResource(R.drawable.day_cloudy);
                                    txt_name.setTextColor(getResources().getColor(R.color.temp));
                                    txt_day.setTextColor(getResources().getColor(R.color.temp));
                                    txt_type.setTextColor(getResources().getColor(R.color.temp));
                                    txt_temp.setTextColor(getResources().getColor(R.color.temp));
                                    txt_c_temp.setTextColor(getResources().getColor(R.color.temp));
                                    txt_f_temp.setTextColor(getResources().getColor(R.color.temp));
                                }
                                else if(WeatherText.equals("Clouds and sun")) {
                                    img_temp.setBackgroundResource(R.drawable.sun_mostly_sunny);
                                    rel_home.setBackgroundResource(R.drawable.day_partly_sunny);
                                }
                                else if(WeatherText.equals("Sunny")) {
                                    img_temp.setBackgroundResource(R.drawable.sun_clear);
                                    rel_home.setBackgroundResource(R.drawable.day_clear);
                                }
                                else if(WeatherText.equals("Clear with periodic clouds")) {
                                    img_temp.setBackgroundResource(R.drawable.sun_partly_sunny);
                                    rel_home.setBackgroundResource(R.drawable.day_partly_sunny);
                                }
                                else
                                {
                                    img_temp.setBackgroundResource(R.drawable.sun_mostly_sunny);
                                    rel_home.setBackgroundResource(R.drawable.day_mostly_sunny);
                                }
                            }
                            else
                            {
                                if(WeatherText.equals("Clear")) {
                                    img_temp.setBackgroundResource(R.drawable.moon_clear);
                                    rel_home.setBackgroundResource(R.drawable.night_clear);
                                    txt_name.setTextColor(getResources().getColor(R.color.temp));
                                    txt_day.setTextColor(getResources().getColor(R.color.temp));
                                    txt_type.setTextColor(getResources().getColor(R.color.temp));
                                    txt_temp.setTextColor(getResources().getColor(R.color.temp));
                                    txt_c_temp.setTextColor(getResources().getColor(R.color.temp));
                                    txt_f_temp.setTextColor(getResources().getColor(R.color.temp));
                                }
                                else if(WeatherText.equals("Mostly clear")) {
                                    img_temp.setBackgroundResource(R.drawable.moon_mostly_clear);
                                    rel_home.setBackgroundResource(R.drawable.night_mostly_clear);
                                    txt_name.setTextColor(getResources().getColor(R.color.temp));
                                    txt_day.setTextColor(getResources().getColor(R.color.temp));
                                    txt_type.setTextColor(getResources().getColor(R.color.temp));
                                    txt_temp.setTextColor(getResources().getColor(R.color.temp));
                                    txt_c_temp.setTextColor(getResources().getColor(R.color.temp));
                                    txt_f_temp.setTextColor(getResources().getColor(R.color.temp));
                                }
                                else if(WeatherText.equals("Partly clear")) {
                                    img_temp.setBackgroundResource(R.drawable.moon_partly_clear);
                                    rel_home.setBackgroundResource(R.drawable.night_partly_clear);
                                    txt_name.setTextColor(getResources().getColor(R.color.temp));
                                    txt_day.setTextColor(getResources().getColor(R.color.temp));
                                    txt_type.setTextColor(getResources().getColor(R.color.temp));
                                    txt_temp.setTextColor(getResources().getColor(R.color.temp));
                                    txt_c_temp.setTextColor(getResources().getColor(R.color.temp));
                                    txt_f_temp.setTextColor(getResources().getColor(R.color.temp));
                                }
                                else if(WeatherText.equals("Cloudy")) {
                                    img_temp.setBackgroundResource(R.drawable.cloudy);
                                    rel_home.setBackgroundResource(R.drawable.night_cloudy);
                                    txt_name.setTextColor(getResources().getColor(R.color.temp));
                                    txt_day.setTextColor(getResources().getColor(R.color.temp));
                                    txt_type.setTextColor(getResources().getColor(R.color.temp));
                                    txt_temp.setTextColor(getResources().getColor(R.color.temp));
                                    txt_c_temp.setTextColor(getResources().getColor(R.color.temp));
                                    txt_f_temp.setTextColor(getResources().getColor(R.color.temp));
                                }
                                else if(WeatherText.equals("Mostly cloudy")) {
                                    img_temp.setBackgroundResource(R.drawable.moon_partly_clear);
                                    rel_home.setBackgroundResource(R.drawable.night_mostly_cloudy);
                                    txt_name.setTextColor(getResources().getColor(R.color.temp));
                                    txt_day.setTextColor(getResources().getColor(R.color.temp));
                                    txt_type.setTextColor(getResources().getColor(R.color.temp));
                                    txt_temp.setTextColor(getResources().getColor(R.color.temp));
                                    txt_c_temp.setTextColor(getResources().getColor(R.color.temp));
                                    txt_f_temp.setTextColor(getResources().getColor(R.color.temp));
                                }
                                else if(WeatherText.equals("Partly cloudy")) {
                                    img_temp.setBackgroundResource(R.drawable.moon_mostly_clear);
                                    rel_home.setBackgroundResource(R.drawable.night_partly_cloudy);
                                    txt_name.setTextColor(getResources().getColor(R.color.temp));
                                    txt_day.setTextColor(getResources().getColor(R.color.temp));
                                    txt_type.setTextColor(getResources().getColor(R.color.temp));
                                    txt_temp.setTextColor(getResources().getColor(R.color.temp));
                                    txt_c_temp.setTextColor(getResources().getColor(R.color.temp));
                                    txt_f_temp.setTextColor(getResources().getColor(R.color.temp));
                                }
                                else if(WeatherText.equals("Some clouds")) {
                                    img_temp.setBackgroundResource(R.drawable.moon_partly_clear);
                                    rel_home.setBackgroundResource(R.drawable.night_partly_clear);
                                    txt_name.setTextColor(getResources().getColor(R.color.temp));
                                    txt_day.setTextColor(getResources().getColor(R.color.temp));
                                    txt_type.setTextColor(getResources().getColor(R.color.temp));
                                    txt_temp.setTextColor(getResources().getColor(R.color.temp));
                                    txt_c_temp.setTextColor(getResources().getColor(R.color.temp));
                                    txt_f_temp.setTextColor(getResources().getColor(R.color.temp));
                                }
                                else if(WeatherText.equals("Clear with periodic clouds")) {
                                    img_temp.setBackgroundResource(R.drawable.moon_partly_clear);
                                    rel_home.setBackgroundResource(R.drawable.night_partly_clear);
                                    txt_name.setTextColor(getResources().getColor(R.color.temp));
                                    txt_day.setTextColor(getResources().getColor(R.color.temp));
                                    txt_type.setTextColor(getResources().getColor(R.color.temp));
                                    txt_temp.setTextColor(getResources().getColor(R.color.temp));
                                    txt_c_temp.setTextColor(getResources().getColor(R.color.temp));
                                    txt_f_temp.setTextColor(getResources().getColor(R.color.temp));
                                }

                                else
                                {
                                    img_temp.setBackgroundResource(R.drawable.moon_mostly_clear);
                                    rel_home.setBackgroundResource(R.drawable.night_clear);
                                    txt_name.setTextColor(getResources().getColor(R.color.temp));
                                    txt_day.setTextColor(getResources().getColor(R.color.temp));
                                    txt_type.setTextColor(getResources().getColor(R.color.temp));
                                    txt_temp.setTextColor(getResources().getColor(R.color.temp));
                                    txt_c_temp.setTextColor(getResources().getColor(R.color.temp));
                                    txt_f_temp.setTextColor(getResources().getColor(R.color.temp));
                                }
                            }
                            txt_c_temp.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    txt_temp.setText(""+Value1);
                                }
                            });
                            txt_f_temp.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    txt_temp.setText(""+Value2);
                                }
                            });

                        }
                        catch( Exception e)
                        {}

                        pDialog.dismiss();


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("===error==","=="+error);
                pDialog.dismiss();
            }
        });
        stringRequest.setTag(TAG);
// Add the request to the RequestQueue.
        queue.add(stringRequest);
        //  Log.e("===response==","=="+arr.size());



        return rootview;
    }

}
