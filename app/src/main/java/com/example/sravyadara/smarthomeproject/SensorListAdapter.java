package com.example.sravyadara.smarthomeproject;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sravyadara on 4/28/15.
 */

/*class SensorListAdapter extends BaseAdapter {

    Context mContext;
    Sensor sensors = new Sensor();
    ArrayList<JSONObject> sensorDetails = sensors.getSensorObject();


    public int getCount() {
        return sensorDetails.size();
    }

    @Override
    public Object getItem(int i) {
        return sensorDetails.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public JSONObject getSensorObject(int position) {
        return sensorDetails.get(position);
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        try {
            if (view == null) {
                LayoutInflater layoutInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                //LayoutInflater layoutInflater = getActivity().getLayoutInflater();
                view = layoutInflater.inflate(R.layout.list_item_layout, parent, false);
                TextView nameTextView = (TextView) view.findViewById(R.id.listItem);
                TextView valueTextView = (TextView) view.findViewById(R.id.latlngtext);
                TextView addressTextView = (TextView) view.findViewById(R.id.address);
                TextView zipValue = (TextView) view.findViewById(R.id.zipcode);

                JSONObject record = sensorDetails.get(i);

                nameTextView.setText(record.getString("Name"));
                valueTextView.setText("LatLng: "+record.getString("LatLng"));
                addressTextView.setText("Address: "+record.getString("Address"));
                zipValue.setText(record.getString("ZipCode"));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;

    }

}*/


public class SensorListAdapter extends BaseAdapter {

    private Activity activity;
    private List sensorList;
    private static LayoutInflater layoutInflater = null;
    public Resources res;
    SensorDAO tempValues = null;
    int i = 0;
    private List<SensorDAO> searchList ;

    public SensorListAdapter(Activity activity, List sensorData, Resources resLocal) {

        this.activity = activity;
        this.sensorList = sensorData;
        this.res = resLocal;
        this.searchList = new ArrayList<SensorDAO>();

        this.searchList.addAll(sensorData);
       // this.searchList = sensorData;
        System.out.println(searchList);
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (sensorList.size() <= 0)
            return 1;
        return sensorList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {

        public TextView nameTextView;
        public TextView valueTextView;
        public TextView addressTextView;
        public TextView zipValue;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if (convertView == null) {

            view = layoutInflater.inflate(R.layout.list_item_layout, parent, false);

            view = layoutInflater.inflate(R.layout.list_item_layout, parent, false);
            TextView nameTextView = (TextView) view.findViewById(R.id.listItem);
            TextView valueTextView = (TextView) view.findViewById(R.id.latlngtext);
            TextView addressTextView = (TextView) view.findViewById(R.id.address);
            TextView zipValue = (TextView) view.findViewById(R.id.zipcode);


            viewHolder = new ViewHolder();
            viewHolder.nameTextView = (TextView) view.findViewById(R.id.listItem);
            viewHolder.valueTextView = (TextView) view.findViewById(R.id.latlngtext);
            viewHolder.addressTextView = (TextView) view.findViewById(R.id.address);
            viewHolder.zipValue = (TextView) view.findViewById(R.id.zipcode);

            view.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) view.getTag();

        if (sensorList.size() <= 0) {
            viewHolder.nameTextView.setText("No Data");

        } else {
            tempValues = null;
            tempValues = (SensorDAO) sensorList.get(position);

            viewHolder.nameTextView.setText("Network Name: "+tempValues.getNetworkName());
            viewHolder.valueTextView.setText("LatLng: "+tempValues.getCoordinates());
            viewHolder.addressTextView.setText("Address: "+tempValues.getAddress());
            viewHolder.zipValue.setText("Zipcode: "+tempValues.getZipCode());



        }
      return  view;

    }

    public void filter(String s){
        String inputChar = s.toLowerCase();
        sensorList.clear();
        if(inputChar.length()==0){
            sensorList.addAll(searchList);

        }else{
            for(int i=0;i< searchList.size();i++){
                try{

                    String networkNameString = searchList.get(i).getNetworkName().toLowerCase();
                    if(networkNameString.startsWith(inputChar)) {
                        sensorList.add(searchList.get(i));
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        notifyDataSetChanged();
    }



}


