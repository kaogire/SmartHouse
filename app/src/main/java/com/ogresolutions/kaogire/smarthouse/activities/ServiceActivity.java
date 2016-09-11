package com.ogresolutions.kaogire.smarthouse.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ogresolutions.kaogire.smarthouse.Const;
import com.ogresolutions.kaogire.smarthouse.R;
import com.ogresolutions.kaogire.smarthouse.adapter.ServiceAdapter;
import com.ogresolutions.kaogire.smarthouse.model.AppController;
import com.ogresolutions.kaogire.smarthouse.model.MyTime;
import com.ogresolutions.kaogire.smarthouse.objects.Service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Njuguna on 14-Jul-16.
 */
public class ServiceActivity extends AppCompatActivity {
    ArrayList<Service> services = new ArrayList<>();
    private ProgressDialog pDialog;
    JSONArray jsonArray = new JSONArray();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_list);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
//
        makeJsonObjReq();

        ListView lvService = (ListView)findViewById(R.id.lvService);
        ServiceAdapter adapter = new ServiceAdapter(ServiceActivity.this, services);
        lvService.setAdapter(adapter);
    }

    private void showProgressDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
    }

    private void makeJsonObjReq() {
        showProgressDialog();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                Const.SERVICE_ALL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getBoolean("error")== false){
                                jsonArray = response.getJSONArray("service");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject service = (JSONObject) jsonArray.get(i);
                                    int id = (service.getInt("id"));
                                    String about = (service.getString("about"));
                                    String amount = (service.getString("amount"));
                                    Date date_due = MyTime.stringToDate(service.getString("date_due"));
                                    boolean settled = (service.getInt("settled")==1);
                                    Date date_paid = MyTime.stringToDate(service.getString("date_paid"));
                                    String hse_id = (service.getString("hse_id"));
                                    int d_diff = (service.getInt("d_diff"));

                                    services.add(new Service(id, about, amount, date_due, d_diff, date_paid, settled));
//                                    Log.i("joe", services.toString());
                                    Log.i("joe", service.toString(2));

                                }
                                Toast.makeText(ServiceActivity.this,"success", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(ServiceActivity.this,"sorry there are no notices", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        hideProgressDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("joe", "Error: " + error.getMessage());
                hideProgressDialog();
            }
        }) {
            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "5dd02c57d5e3402afea830a7f7c65360");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", "Androidhive");
                params.put("email", "abc@androidhive.info");
                params.put("pass", "password123");

                return params;
            }

        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq,
                "joeObj");

        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
    }
}
