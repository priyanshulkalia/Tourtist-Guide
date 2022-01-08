package in.gov.chandigarh.Adapter;

import java.util.ArrayList;
import java.util.zip.Inflater;

import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import in.gov.chandigarh.Database.DatabaseFavrt;
import in.gov.chandigarh.R;

public class ShowAdapter extends BaseAdapter{
	
	Context mc;
	LayoutInflater infl;
	DatabaseFavrt db;
	
	ArrayList<Integer> arr_id = new ArrayList<Integer>();
	public static ArrayList<String> arr_name = new ArrayList<String>();
	public static ArrayList<String> arr_image = new ArrayList<String>();
	public static ArrayList<String> arr_rating = new ArrayList<String>();
	public static ArrayList<String> arr_addess = new ArrayList<String>();
	public static ArrayList<String> arr_idd = new ArrayList<String>();
	public static ArrayList<String> arr_reference = new ArrayList<String>();
	
	public ShowAdapter(Context c) {
 		mc = c;
		infl = LayoutInflater.from(c);
		db = new DatabaseFavrt(c);
		AddRow();
	}

	public void AddRow(){
		db.open();
		arr_id.clear();
		arr_name.clear();
		arr_image.clear();
		arr_rating.clear();
		arr_addess.clear();
		arr_idd.clear();
		arr_reference.clear();
		
		Cursor mcur = db.getItemValues();
		
		mcur.moveToFirst();
		while (!mcur.isAfterLast()) {
			arr_id.add(mcur.getInt(mcur.getColumnIndex(db.ColItemID)));
			arr_image.add(mcur.getString(mcur.getColumnIndex(db.ColItemImage)));
			arr_name.add(mcur.getString(mcur.getColumnIndex(db.ColItemName)));
			arr_rating.add(mcur.getString(mcur.getColumnIndex(db.ColItemRating)));
			arr_addess.add(mcur.getString(mcur.getColumnIndex(db.ColItemAddress)));
			arr_idd.add(mcur.getString(mcur.getColumnIndex(db.ColItemIDD)));
			arr_reference.add(mcur.getString(mcur.getColumnIndex(db.ColItemReference)));
			mcur.moveToNext();
			}
		if(mcur.isAfterLast())
		{
			mcur.close();
		}
		db.close();
	}
	public int getCount() {
		
		return arr_idd.size();
	}

	@Override
	public Object getItem(int position) {
		
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	class ViewHolder{
		TextView txt_name,txt_address;
		RatingBar r1;
		ImageView img;

	}
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh;
		if(convertView==null)
		{
			vh = new ViewHolder();
			convertView = infl.inflate(R.layout.custlist, null);


			vh.txt_name = (TextView) convertView.findViewById(R.id.t1);


				vh.txt_address = (TextView) convertView.findViewById(R.id.t2);
				// vh.txt3 = (TextView) convertView.findViewById(R.id.t3);

				vh.r1 = (RatingBar) convertView.findViewById(R.id.r1);
				vh.img = (ImageView) convertView.findViewById(R.id.img);

				vh.txt_name.setText(arr_name.get(position));
				vh.txt_address.setText(arr_addess.get(position));
				if (arr_rating.get(position).equals("No Rating")) {
					vh.r1.setNumStars(0);
				} else {
					double rate = Double.parseDouble(arr_rating.get(position));
					vh.r1.setRating((float) rate);
					vh.r1.setIsIndicator(true);
					vh.r1.setActivated(true);
				}
				String url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=1000&photoreference=" + arr_image.get(position) + "&key=AIzaSyAZwSQEwGAFEzfJL81jt0c3Iz0JGSGdLHQ";
				Picasso.with(mc).load(url).resize(200, 150).into(vh.img);



		}else{
			vh = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}
	
}