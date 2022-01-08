package in.gov.chandigarh.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import in.gov.chandigarh.Extra_Classes.ClassForArray;

import static java.security.AccessController.getContext;

public class MyGridAdapter extends BaseAdapter {
    Context mContext;
    int value;
    //LayoutInflater linf;
    public MyGridAdapter(Context mContext, int value) {
        this.mContext = mContext;
        this.value = value;
      //  linf = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        int size = 1;
            if(value==1) {
                 size= ClassForArray.mThumbIds.length;
            }
            if(value==2)
            {
                size=ClassForArray.places_Thumbs.length;
            }
        if(value==3)
        {
            size=ClassForArray.enter_thumbs.length;
        }
        if(value==4)
        {
            size=ClassForArray.shoping_thumbs.length;
        }
        if(value==5)
        {
            size=ClassForArray.saloon_thumbs.length;
        }
        if(value==6)
        {
            size=ClassForArray.education_thumbs.length;
        }
        if(value==8)
        {
            size=ClassForArray.transport_thumbs.length;
        }
        if(value==9)
        {
            size=ClassForArray.health_thumbs.length;
        }
        if(value==10)
        {
            size=ClassForArray.helpdesk_thumbs.length;
        }
        if(value==11)
        {
            size=ClassForArray.service_thumbs.length;
        }
        return size;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    class ViewHolder{
        TextView txt1;
        ImageView img;

    }
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder vh = new ViewHolder();

       // convertView = linf.inflate(R.layout.custgrid,null);
        ImageView img;
        /*vh.txt1 = (TextView) convertView.findViewById(R.id.t1);
        vh.img = (ImageView) convertView.findViewById(R.id.img);*/

        if (convertView == null) {

            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
            // if it's not recycled, initialize some attributes
            img= new ImageView(mContext);
            img.setLayoutParams(new GridLayout.LayoutParams());
            //img.setLayoutParams(new GridView.LayoutParams(300,300));`
            double w = 0.4*width;
            double h = 0.26*height;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)w,(int)h);
          img.setLayoutParams(new GridView.LayoutParams(params));

           img.setScaleType(ImageView.ScaleType.FIT_XY);
            //img.setPadding(8, 8, 8, 8);
        } else {
            img = (ImageView) convertView;

        }
        if(value==1) {
            img.setImageResource(ClassForArray.mThumbIds[i]);
        }
        if(value==2)
        {
            img.setImageResource(ClassForArray.places_Thumbs[i]);
        }
        if(value==3) {
            img.setImageResource(ClassForArray.enter_thumbs[i]);
        }
        if(value==4) {
            img.setImageResource(ClassForArray.shoping_thumbs[i]);
        }
        if(value==5) {
            img.setImageResource(ClassForArray.saloon_thumbs[i]);
        }
        if(value==6) {
            img.setImageResource(ClassForArray.education_thumbs[i]);
        }
        if(value==8) {
            img.setImageResource(ClassForArray.transport_thumbs[i]);
        }
        if(value==9) {
            img.setImageResource(ClassForArray.health_thumbs[i]);
        }
        if(value==10) {
            img.setImageResource(ClassForArray.helpdesk_thumbs[i]);
        }
        if(value==11) {
            img.setImageResource(ClassForArray.service_thumbs[i]);
        }

       // vh.txt1.setText(arr[i]);
        return img;
    }
}
