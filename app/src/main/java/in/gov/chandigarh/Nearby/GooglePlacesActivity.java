package in.gov.chandigarh.Nearby;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import in.gov.chandigarh.Activities.DescActivity;
import in.gov.chandigarh.Extra_Classes.GPSTracker;
import in.gov.chandigarh.R;

public class GooglePlacesActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private static final String GOOGLE_API_KEY = "AIzaSyCozhbjthyFwMenhBUduSmaAdESz6RHtMI";
    EditText placeText;
    double latitude = 0;
    double longitude = 0;
    private int PROXIMITY_RADIUS = 5000;
    GPSTracker gps;
    boolean checkper, isGranted;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_places);
        //show error dialog if GoolglePlayServices not available
        if (!isGooglePlayServicesAvailable()) {
            finish();
        }

        gps = new GPSTracker(GooglePlacesActivity.this, GooglePlacesActivity.this);
        latitude = gps.getLatitude();
        longitude = gps.getLongitude();
        placeText = (EditText) findViewById(R.id.placeText);
        Button btnFind = (Button) findViewById(R.id.btnFind);
        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.gMap);
        fragment.getMapAsync(this);

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = placeText.getText().toString();
                type=type.replaceAll("\\s+","");
                //Toast.makeText(getApplicationContext(),""+type,Toast.LENGTH_SHORT).show();
                StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                googlePlacesUrl.append("location=" + latitude + "," + longitude);
                googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
                googlePlacesUrl.append("&types=" + type);
                googlePlacesUrl.append("&keyword="+type);
                googlePlacesUrl.append("&key=" + GOOGLE_API_KEY);

                GooglePlacesReadTask googlePlacesReadTask = new GooglePlacesReadTask();
                Object[] toPass = new Object[3];
                toPass[0] = googleMap;
                toPass[1] = googlePlacesUrl.toString();
                toPass[2] = getApplicationContext();
                googlePlacesReadTask.execute(toPass);
            }
        });
    }
    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        checkper = checkPermission();

        googleMap.setMyLocationEnabled(true);
    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(GooglePlacesActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            isGranted = false;
            ActivityCompat.requestPermissions(GooglePlacesActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }else  if (ContextCompat.checkSelfPermission(GooglePlacesActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            isGranted = false;
            ActivityCompat.requestPermissions(GooglePlacesActivity.this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
        }

        else {
            isGranted = true;

        }
        return isGranted;
    }
}
