package in.gov.chandigarh.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
//import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import in.gov.chandigarh.Adapter.MyGalleryAdapter;
import in.gov.chandigarh.Adapter.MyGridAdapter;
import in.gov.chandigarh.Extra_Classes.GalleryDialog;
import in.gov.chandigarh.R;

public class GalleryActivity extends AppCompatActivity {

    GridView grid_gallery;
    Toolbar toolbar;
  public ArrayList<String> arr_img=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

       toolbar = (Toolbar) findViewById(R.id.toolbar);
       toolbar.setTitle("Gallery");
        setSupportActionBar(toolbar);

        //back button code
     this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
     this.getSupportActionBar().setDisplayShowHomeEnabled(true);

        arr_img=getIntent().getStringArrayListExtra("img_arr");
        grid_gallery=(GridView)findViewById(R.id.grid_gallery);
        grid_gallery.setAdapter(new MyGalleryAdapter(GalleryActivity.this,arr_img));
        grid_gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {

                new GalleryDialog(GalleryActivity.this,i,arr_img);
            }
        });
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

}
