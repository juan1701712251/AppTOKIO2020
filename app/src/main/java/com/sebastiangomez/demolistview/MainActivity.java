package com.sebastiangomez.demolistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.sebastiangomez.demolistview.model.MedalByType;
import com.sebastiangomez.demolistview.model.MedalByEvent;
import com.sebastiangomez.demolistview.model.Root;
import com.sebastiangomez.demolistview.service.TOKIO2020Service;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lviewItems;
    private Root data;
    private Button btnGetDataMedals;
    private TextView txtNameType;
    private TextView txtCountryTitle;
    private TextView txtNameCountry;

    private TOKIO2020Service TOKIO2020Service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TOKIO2020Service =  new TOKIO2020Service();

        initViews();
        initEvents();
    }

    public void initEvents(){
        btnGetDataMedals.setOnClickListener(v -> {
            getDataMedalsByCountryInfo();
        });
    }


    public void initViews(){
        lviewItems = findViewById(R.id.lvieItems);
        txtNameType = findViewById(R.id.txtNameType);
        txtCountryTitle = findViewById(R.id.txtCountryTitle);
        txtNameCountry = findViewById(R.id.txtNameCountry);
        btnGetDataMedals = findViewById(R.id.btnGetDataMedals);
    }
    /*
    public Country getDataCountryInfo(int id_country){
        final Country[] outCountry = {null};
        TOKIO2020Service.requestCountryData(id_country,(statusCode, country) -> {
            switch (statusCode){
                case -1:
                    Log.d("Country ","Network Error");
                    break;
                case 404:
                    Log.d("Country ","Country not found");
                    break;
                case 200:
                    outCountry[0] = country;
                    break;
            }
        });
        Log.d("Country ","Country found! Name Country:"+ outCountry[0].getName());
        return outCountry[0];
    }
    */
    public void getDataMedalsByCountryInfo(){
        btnGetDataMedals.setText(R.string.Get_Data_Medals_By_Country);
        txtNameType.setText(R.string.Country);
        txtNameCountry.setVisibility(View.GONE);
        txtCountryTitle.setVisibility(View.GONE);


        TOKIO2020Service.requestMedalsByEventData((statusCode, root) -> {
            switch (statusCode){
                case -1:
                    Log.d("MedalByEvent ","Network Error");
                    break;
                case 404:
                    Log.d("MedalByEvent ","Data not found");
                    break;
                case 200:
                    setData(root);
                    runOnUiThread(() -> {
                        if(getData() != null) {
                            showDataMedalByCountry(getData());
                        }
                    });
                    break;
            }
        });
    }

    public void showDataMedalsByEvent(int id_country){
        btnGetDataMedals.setText(R.string.Back);
        txtNameType.setText(R.string.Sport_Event);
        txtNameCountry.setVisibility(View.VISIBLE);
        txtCountryTitle.setVisibility(View.VISIBLE);


        /*
        btnBackHome = findViewById(R.id.btnBackHome);
        btnBackHome.setOnClickListener(v -> {
            setContentView(R.layout.activity_main);
        });
        */
        List<MedalByType> medalsByEvent= convertToMedalsByEventFromCountry(getData().getResource(),id_country);
        CustomListAdapter adapter = new CustomListAdapter(this, medalsByEvent,this.TOKIO2020Service);
        lviewItems.setAdapter(adapter);
    }

    private List<MedalByType> convertToMedalsByEventFromCountry(List<MedalByEvent> resource,int id_country){
        List<MedalByType> out = new ArrayList<>();

        //Agrupar las medallas por evento a partir de un id de un country
        for(MedalByEvent mbye : resource){
            MedalByType mbyt = findMedalByEventById(out,mbye.getId_country());

            if (mbyt == null) {
                mbyt = new MedalByType(mbye.getId_event(), mbye.getSport_event_by_id_event().getName());
                out.add(mbyt);
            }
            switch (mbye.getType_medal()) {
                case ("GOLD"):
                    mbyt.setCantGoldMedal(1);
                    break;
                case ("SILVER"):
                    mbyt.setCantSilverMedal(1);
                    break;
                case ("BRONZE"):
                    mbyt.setCantBronzeMedal(1);
                    break;
            }

        }
        txtNameCountry.setText(getNameByCountryId(resource,id_country));
        return out;
    }

    public String getNameByCountryId(List<MedalByEvent> resource,int id_country){
        for(MedalByEvent mbye : resource){
            if(mbye.getId_country() == id_country){
                return mbye.getCountry_by_id_country().getName();
            }
        }
        return "";
    }

    public MedalByType findMedalByEventById(List<MedalByType> listmbyc, int id_event){
        MedalByType out = null;
        for(MedalByType mbyc:listmbyc){
            if(mbyc.getId_event() == id_event){
                out = mbyc;
            }
        }
        return out;
    }




    public void showDataMedalByCountry(Root root){
        List<MedalByType> medalsByCountry= convertMedalsByEventInMedalsByCountry(root.getResource());
        CustomListAdapter adapter = new CustomListAdapter(this, medalsByCountry,this.TOKIO2020Service);
        lviewItems.setAdapter(adapter);
    }

    private List<MedalByType> convertMedalsByEventInMedalsByCountry(List<MedalByEvent> resource) {
        List<MedalByType> out = new ArrayList<>();

        //Agrupar las medallas por país
        for(MedalByEvent mbye : resource){
            MedalByType mbyc = findMedalByCountryById(out,mbye.getId_country());
            if(mbyc == null){
                mbyc = new MedalByType(mbye.getId_country(),mbye.getCountry_by_id_country().getName());
                out.add(mbyc);
            }
            switch(mbye.getType_medal()){
                case("GOLD"):
                    mbyc.setCantGoldMedal(1);
                    break;
                case("SILVER"):
                    mbyc.setCantSilverMedal(1);
                    break;
                case("BRONZE"):
                    mbyc.setCantBronzeMedal(1);
                    break;
            }
        }
        return out;
    }

    public MedalByType findMedalByCountryById(List<MedalByType> listmbyc, int id_country){
        MedalByType out = null;
        for(MedalByType mbyc:listmbyc){
            if(mbyc.getId_country() == id_country){
                out = mbyc;
            }
        }
        return out;
    }

    public Root getData() {
        return data;
    }

    public void setData(Root data) {
        //Preguntar porque no funciona
        //this.data = (Root) data.clone();
        this.data = data;
    }

}