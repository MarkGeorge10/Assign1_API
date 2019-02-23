package com.project.android.map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServiceActivity extends AppCompatActivity {



   // double [] longtitude = {80,100,29.95988436593};
   // double [] attitude = {15,100,30.91876745224};








    String str = "http://gms-sms.com:89/gmsred/api/employees";
    String basestr = "http://gms-sms.com:89";
    module moduleobject;
    Service_Adapter mAdapter;

    ArrayList<module> data = new ArrayList<module>();
    ArrayList<String> longtitude=new ArrayList<>();
    ArrayList<String> attitude=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        longtitude.add("29.95988436593"); attitude.add("30.91876745224");
        longtitude.add("-34.15"); attitude.add("151");
        longtitude.add("20"); attitude.add("200");
        longtitude.add("-20"); attitude.add("151");
        longtitude.add("80"); attitude.add("15");
        longtitude.add("100"); attitude.add("100");




///////////////////////////////////////////////set new location in map send longtitude and attidues////////////////////////////////////////////////////////////



    /*    Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // new MapsActivity(-34,151);



            }
        });*/

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




        final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.service_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);




        RequestQueue requestQueue = Volley.newRequestQueue(ServiceActivity.this);
        StringRequest stringrequest = new StringRequest(Request.Method.GET, str,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray jsonArray = object.getJSONArray("data");

                            // Toast.makeText(MainActivity.this,response,Toast.LENGTH_SHORT).show();


                            for (int i = 0; i < jsonArray.length(); i++) {


                                JSONObject newobject = jsonArray.getJSONObject(i);


                                String En_name = newobject.getString("en_name");

                                String Imag = newobject.getString("image");

                                moduleobject = new module(En_name,basestr+Imag);


                                data.add(moduleobject);

                                Toast.makeText(ServiceActivity.this, basestr+Imag, Toast.LENGTH_SHORT).show();
                                Log.e("LLLL",basestr+Imag);
                            }

                            mAdapter = new Service_Adapter(ServiceActivity.this, data,longtitude,attitude);

                            mRecyclerView.setAdapter(mAdapter);


                        } catch (JSONException e) {


                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ServiceActivity.this,"Sth wrong", Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            public Map<String,String> getHeaders()throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("Accept", "application/json");
                params.put("Content-Type", "application/json");

                return params;
            }

        };
        requestQueue.add(stringrequest);






    }

    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready for use.
     */




}
