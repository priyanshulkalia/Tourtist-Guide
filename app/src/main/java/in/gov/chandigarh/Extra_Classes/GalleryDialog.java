package in.gov.chandigarh.Extra_Classes;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.gov.chandigarh.R;

/**
 * Created by Microsoft on 11/3/2017.
 */

public class GalleryDialog extends Dialog {

    Context context; int pos;
    public ArrayList<String> arr_img=new ArrayList<String>();

    public GalleryDialog(@NonNull Context context, int pos, ArrayList<String> arr_img) {
        super(context);
        this.context = context;
        this.pos = pos;
        this.arr_img = arr_img;

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custgallery);


        ImageView img = (ImageView)  dialog.findViewById(R.id.gallery_img);
        String url= "https://maps.googleapis.com/maps/api/place/photo?maxwidth=1000&photoreference="+arr_img.get(pos)+"&key=AIzaSyDo13Xg5ijXne1ujiA0dEnVo1lrxN02zwA";
        Picasso.with(context).load(url).resize(700,700).into(img);

        //Picasso.with(context).load(arr_img[pos]).into(img);
        dialog.show();

    }



}
