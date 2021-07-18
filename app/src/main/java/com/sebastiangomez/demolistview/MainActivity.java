package com.sebastiangomez.demolistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import com.sebastiangomez.demolistview.model.MedalByCountry;
import com.sebastiangomez.demolistview.model.MedalByEvent;
import com.sebastiangomez.demolistview.model.Root;
import com.sebastiangomez.demolistview.service.TOKIO2020Service;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lviewItems;
    private Root data;
    private Button btnGetDataMedals;

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
        Log.d("Debug:","Se ejecuta método getDataMedalsByCountry");
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

    public void showDataMedalByCountry(Root root){
        List<MedalByCountry> medalsByCountry= convertMedalsByEventInMedalsByCountry(root.getResource());
        CustomListAdapter adapter = new CustomListAdapter(this, medalsByCountry,this.TOKIO2020Service);
        lviewItems.setAdapter(adapter);
    }

    private List<MedalByCountry> convertMedalsByEventInMedalsByCountry(List<MedalByEvent> resource) {
        List<MedalByCountry> out = new ArrayList<>();

        //Agrupar las medallas por país
        for(MedalByEvent mbye : resource){
            MedalByCountry mbyc = findMedalByCountryById(out,mbye.getId_country());
            if(mbyc == null){
                mbyc = new MedalByCountry(mbye.getId_country(),mbye.getCountry_by_id_country().getName());
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

    public MedalByCountry findMedalByCountryById(List<MedalByCountry> listmbyc,int id_country){
        MedalByCountry out = null;
        for(MedalByCountry mbyc:listmbyc){
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