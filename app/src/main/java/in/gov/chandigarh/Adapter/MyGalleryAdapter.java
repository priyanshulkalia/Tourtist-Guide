package in.gov.chandigarh.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.gov.chandigarh.Activities.DescActivity;
import in.gov.chandigarh.Activities.ListActivity;
import in.gov.chandigarh.Extra_Classes.ClassForArray;
import in.gov.chandigarh.R;

/**
 * Created by Microsoft on 11/1/2017.
 */

public class MyGalleryAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<String> arr_img1=new ArrayList<String>();
    public MyGalleryAdapter(Context mContext, ArrayList<String> arr_img1) {
        this.mContext = mContext;
        this.arr_img1=arr_img1;

    }

    @Override
    public int getCount() {
        return arr_img1.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView img;
        if (convertView == null) {

            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
            // if it's not recycled, initialize some attributes
            img= new ImageView(mContext);
            img.setBackgroundResource(R.color.grey);
            img.setLayoutParams(new GridLayout.LayoutParams());
            //img.setLayoutParams(new GridView.LayoutParams(300,300));`
            double w = 0.27*width;
            double h = 0.15*height;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)w,(int)h);
            img.setLayoutParams(new GridView.LayoutParams(params));

            img.setScaleType(ImageView.ScaleType.FIT_XY);
            //img.setPadding(8, 8, 8, 8);
        } else {
            img = (ImageView) convertView;
        }
        String url= "https://maps.googleapis.com/maps/api/place/photo?maxwidth=1000&photoreference="+arr_img1.get(position)+"&key=AIzaSyB8vQv9lmVt6NoR77T1SfzNndZJ55sWbas";
        Picasso.with(mContext).load(url).into(img);

        return img;
    }
}
