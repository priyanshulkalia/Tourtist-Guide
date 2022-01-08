package in.gov.chandigarh.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import in.gov.chandigarh.Activities.DescActivity;
import in.gov.chandigarh.Activities.FavDesc;
import in.gov.chandigarh.Activities.ListActivity;
import in.gov.chandigarh.Adapter.ShowAdapter;
import in.gov.chandigarh.Database.DatabaseFavrt;
import in.gov.chandigarh.R;

public class FavoriteFragment extends Fragment implements AdapterView.OnItemClickListener {
    ListView lst_favrt;
    DatabaseFavrt dbf2;
    TextView txt_fav;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview=inflater.inflate(R.layout.fragment_favorite, container, false);
        lst_favrt=(ListView)rootview.findViewById(R.id.lst_favrt);
        txt_fav=(TextView) rootview.findViewById(R.id.fav_text);
        dbf2=new DatabaseFavrt(getActivity());



       lst_favrt.setAdapter(new ShowAdapter(getActivity()));
        if(ShowAdapter.arr_idd.size()>0)
        {
            txt_fav.setVisibility(View.INVISIBLE);
        }
        else
        {
            txt_fav.setVisibility(View.VISIBLE);
        }
       lst_favrt.setOnItemClickListener(this);

        return rootview;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int count;
        Intent in=new Intent(getActivity(),FavDesc.class);
        in.putExtra("ind",position);
        // in.putExtra("nm",count);
        startActivity(in);
    }

    @Override
    public void onResume() {
        super.onResume();
        lst_favrt.setAdapter(new ShowAdapter(getActivity()));
        if(ShowAdapter.arr_idd.size()>0)
        {
            txt_fav.setVisibility(View.INVISIBLE);
        }
        else
        {
            txt_fav.setVisibility(View.VISIBLE);
        }
    }
}
