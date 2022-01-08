package in.gov.chandigarh.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import in.gov.chandigarh.Database.Model_Getter_Setter;
import in.gov.chandigarh.R;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Microsoft on 9/17/2017.
 */

public class MyAdapter extends BaseAdapter {

    Context activity; ArrayList<Model_Getter_Setter> arr;
    LayoutInflater linf;
    public MyAdapter(Context activity, ArrayList<Model_Getter_Setter> arr) {

        this.activity = activity;
        this.arr = arr;
        linf = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder{
        TextView txt1,txt2,txt3;
        RatingBar r1;
        ImageView img;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh = new ViewHolder();

        convertView = linf.inflate(R.layout.custlist,null);
        vh.txt1 = (TextView) convertView.findViewById(R.id.t1);
        vh.txt2 = (TextView) convertView.findViewById(R.id.t2);
       // vh.txt3 = (TextView) convertView.findViewById(R.id.t3);
        vh.r1 = (RatingBar) convertView.findViewById(R.id.r1);
        vh.img = (ImageView) convertView.findViewById(R.id.img);

        vh.txt1.setText(arr.get(position).getName());
        vh.txt2.setText(arr.get(position).getFormat_add());
        if(arr.get(position).getRating().equals("No Rating")) {
            vh.r1.setNumStars(0);
        }
        else{
            double rate = Double.parseDouble(arr.get(position).getRating());
            /*Drawable drawable = vh.r1.getProgressDrawable();
            drawable.setColorFilter(Color.parseColor("#ffcc01"), PorterDuff.Mode.DST_ATOP);*/
            vh.r1.setRating((float)rate);
            vh.r1.setIsIndicator(true);
            vh.r1.setActivated(true);
        }

        Log.e("Rating==",arr.get(position).getRating());

      //  vh.txt3.setText("Rating : "+);

        String url= "https://maps.googleapis.com/maps/api/place/photo?maxwidth="+arr.get(position).getWidth()+"&photoreference="+arr.get(position).getPhoto()+"&key=AIzaSyDV7Kau9VG9zq0Q0n5dWBMh4otMbPFgGj0";
        Picasso.with(activity).load(url).resize(200,150).into(vh.img);

        return convertView;
    }
}
