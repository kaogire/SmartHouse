package com.ogresolutions.kaogire.smarthouse.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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
import com.ogresolutions.kaogire.smarthouse.adapter.ComplaintAdapter;
import com.ogresolutions.kaogire.smarthouse.dialog.AddComplaint;
import com.ogresolutions.kaogire.smarthouse.model.AppController;
import com.ogresolutions.kaogire.smarthouse.model.MyTime;
import com.ogresolutions.kaogire.smarthouse.objects.Complaint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Njuguna on 15-Jul-16.
 */
public class ComplaintActivity extends AppCompatActivity {
    ArrayList<Complaint> complaints = new ArrayList<>();
    private ProgressDialog pDialog;
    JSONArray jsonArray = new JSONArray();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaint);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
//
        makeJsonObjReq();

        ListView lvComplaint = (ListView)findViewById(R.id.lvComplaint);
        ComplaintAdapter adapter = new ComplaintAdapter(ComplaintActivity.this, complaints);
        lvComplaint.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabComplaint);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComplaintActivity.this, AddComplaint.class);
                startActivity(intent);
            }
        });
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
                Const.COMPLAINT_ALL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getBoolean("error")== false){
                                jsonArray = response.getJSONArray("complaint");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject complaint = (JSONObject) jsonArray.get(i);
                                    int id = (complaint.getInt("id"));
                                    String type = (complaint.getString("type"));
                                    String about = (complaint.getString("about"));
                                    String detail = (complaint.getString("detail"));
                                    boolean addressed = complaint.getInt("addressed") == 1;
                                    Date date = MyTime.stringToDate(complaint.getString("date"));
                                    if (complaint.getString("date_addressed") != null){
                                        Date d_addressed = MyTime.stringToDate(complaint.getString("date_addressed"));
                                        complaints.add(new Complaint(id, type, about, detail, date, addressed, d_addressed));
                                    }else{
                                        complaints.add(new Complaint(id, type, about, detail, date, addressed));
                                    }
                                    Log.i("joe", complaints.toString());
                                    int size = complaints.size();
                                    Log.i("joe", complaint.toString(2));

                                }
                                Toast.makeText(ComplaintActivity.this,"success", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(ComplaintActivity.this,"sorry there are no notices", Toast.LENGTH_LONG).show();
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
