package in.gov.chandigarh.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import in.gov.chandigarh.Activities.ListActivity;
import in.gov.chandigarh.Adapter.MyGridAdapter;
import in.gov.chandigarh.R;


public class RestrauntFragment extends Fragment  {

    GridView g1;

   // Toolbar toolbar;
    int value;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview=inflater.inflate(R.layout.fragment_restraunt, container, false);
       // toolbar = (Toolbar) rootview.findViewById(R.id.toolbar);
        value=getArguments().getInt("key");
        g1=(GridView)rootview.findViewById(R.id.gridview);
        g1.setAdapter(new MyGridAdapter(getActivity(),value));
        g1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {

                Intent in=new Intent(getActivity(),ListActivity.class);
                //toolbar.setTitle(ClassForArray.arr_hotels[i]);
                in.putExtra("key1",i);
                in.putExtra("key2",value);
                startActivity(in);
            }
        });


        return rootview;
    }

}
