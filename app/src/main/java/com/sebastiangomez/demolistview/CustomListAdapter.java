package com.sebastiangomez.demolistview;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sebastiangomez.demolistview.model.MedalByCountry;
import com.sebastiangomez.demolistview.service.TOKIO2020Service;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private MainActivity context;
    private List<MedalByCountry> resources;
    private TOKIO2020Service TOKIO2020Service;
    MediaPlayer mediaPlayer;

    public CustomListAdapter (MainActivity newContext, List<MedalByCountry> newResources, TOKIO2020Service newTOKIO2020Service){
        context = newContext;
        resources = newResources;
        TOKIO2020Service = newTOKIO2020Service;
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public int getCount() {
        return resources.size();
    }

    @Override
    public MedalByCountry getItem(int i) {
        return resources.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_list_view_row_item,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MedalByCountry currentMedalByCountry = (MedalByCountry) getItem(position);

        ViewHolder finalViewHolder = viewHolder;

        viewHolder.txtNameCountry.setText(currentMedalByCountry.getNameCountry());
        return convertView;
    }

    public class ViewHolder{
        TextView txtNameCountry;

        public ViewHolder(View view){
            txtNameCountry = view.findViewById(R.id.txtNameCountry);
        }
    }
}
