package com.sebastiangomez.demolistview;

import android.media.MediaPlayer;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintSet;

import com.sebastiangomez.demolistview.model.MedalByType;
import com.sebastiangomez.demolistview.service.TOKIO2020Service;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private MainActivity context;
    private List<MedalByType> resources;
    private TOKIO2020Service TOKIO2020Service;
    MediaPlayer mediaPlayer;

    public CustomListAdapter (MainActivity newContext, List<MedalByType> newResources, TOKIO2020Service newTOKIO2020Service){
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
    public MedalByType getItem(int i) {
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
        MedalByType currentMedalByType = (MedalByType) getItem(position);

        ViewHolder finalViewHolder = viewHolder;

        viewHolder.txtNameCountry.setText(currentMedalByType.getName());
        viewHolder.txtMedalGold.setText(String.valueOf(currentMedalByType.getCantGoldMedal()));
        viewHolder.txtMedalSilver.setText(String.valueOf(currentMedalByType.getCantSilverMedal()));
        viewHolder.txtMedalBronze.setText(String.valueOf(currentMedalByType.getCantBronzeMedal()));
        viewHolder.txtTotalMedal.setText(String.valueOf(currentMedalByType.getTotalMedal()));

        viewHolder.layoutItemListView.setOnClickListener(v -> {
            context.showDataMedalsByEvent(currentMedalByType.getId_country());
        });

        return convertView;
    }



    public class ViewHolder{
        TextView txtNameCountry;
        TextView txtMedalGold;
        TextView txtMedalSilver;
        TextView txtMedalBronze;
        TextView txtTotalMedal;
        ViewGroup layoutItemListView;

        public ViewHolder(View view){
            layoutItemListView = view.findViewById(R.id.layoutItemListView);
            txtNameCountry = view.findViewById(R.id.txtNameItem);
            txtMedalGold = view.findViewById(R.id.txtMedalGold);
            txtMedalSilver = view.findViewById(R.id.txtMedalSilver);
            txtMedalBronze = view.findViewById(R.id.txtMedalBronze);
            txtTotalMedal = view.findViewById(R.id.txtTotalMedals);
        }
    }
}
