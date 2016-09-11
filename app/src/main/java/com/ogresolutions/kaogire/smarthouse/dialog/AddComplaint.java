package com.ogresolutions.kaogire.smarthouse.dialog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ogresolutions.kaogire.smarthouse.Const;
import com.ogresolutions.kaogire.smarthouse.R;
import com.ogresolutions.kaogire.smarthouse.activities.ComplaintActivity;
import com.ogresolutions.kaogire.smarthouse.model.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Njuguna on 15-Jul-16.
 */
public class AddComplaint extends AppCompatActivity {
    String category, repair, service, detail;
    Spinner spnVhead;
    ArrayAdapter<String> aRepairs, aServices;
    String[] categories, repairs, services;
    private ProgressDialog pDialog;
    EditText edtDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaint_new);


        categories = getResources().getStringArray(R.array.complaint_type);
        repairs = getResources().getStringArray(R.array.repair_list);
        services = getResources().getStringArray(R.array.service_types);
        Spinner spnCategory = (Spinner) findViewById(R.id.spnCategory);
        ArrayAdapter<String> aCategories = new ArrayAdapter<String>(AddComplaint.this, android.R.layout.simple_spinner_item, categories);
        aRepairs = new ArrayAdapter<String>(AddComplaint.this, android.R.layout.simple_spinner_item, repairs);
        aServices = new ArrayAdapter<String>(AddComplaint.this, android.R.layout.simple_spinner_item, services);

        spnVhead = (Spinner) findViewById(R.id.spnVHead);
        edtDetail = (EditText) findViewById(R.id.edtDetail);
        spnCategory.setAdapter(aCategories);
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        category = "Repairs";
                        spnVhead.setAdapter(aRepairs);
                        spnVhead.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch (position){
                                    case 0:
                                        repair = "Electrical";
                                        break;
                                    case 1:
                                        repair = "Plumbing";
                                        break;
                                    case 2:
                                        repair = "Carpentry";
                                        break;
                                    default:
                                        repair = "Electrical";
                                }
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                repair = "Electrical";
                            }
                        });
                        break;
                    case 1:
                        category = "Services";
                        spnVhead.setAdapter(aServices);
                        spnVhead.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch (position){
                                    case 0:
                                        repair = "Bills";
                                        break;
                                    case 1:
                                        repair = "Disturbance";
                                        break;
                                    case 2:
                                        repair = "Parking";
                                        break;
                                    case 3:
                                        repair = "Security";
                                        break;
                                    default:
                                        repair = "Bills";
                                }
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                repair = "Bills";
                            }
                        });
                        break;
                    default:
                        category = "Repairs";
                        repair = "Electrical";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                category = "Repairs";
                spnVhead.setAdapter(aRepairs);
            }
        });
        Button btnOk = (Button) findViewById(R.id.btnCompOk);
        Button btnCancel = (Button) findViewById(R.id.btnCompCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edtDetail.getText())){
                    edtDetail.setError("Enter name");
                }else{
                    detail = edtDetail.getText().toString();
                    JSONObject request = new JSONObject();
                    try {
                        request.put("type", category);
                        request.put("about", repair);
                        request.put("detail", detail);

                        Log.i("joe", request.toString(1));

                        pDialog = new ProgressDialog(AddComplaint.this);
                        pDialog.setMessage("Loading...");
                        pDialog.setCancelable(false);
//
                        makeJsonObjReq(request);
                        startActivity(new Intent(AddComplaint.this, ComplaintActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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

    private void makeJsonObjReq(JSONObject json) {
        showProgressDialog();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Const.COMPLAINT_ADD, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getBoolean("error")== false){
                                Log.i("joe",response.getString("message"));
                                Toast.makeText(AddComplaint.this,"success", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(AddComplaint.this,"sorry try again", Toast.LENGTH_LONG).show();
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

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                return super.parseNetworkResponse(response);
            }
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq,
                "joeObj");

        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
    }
}
