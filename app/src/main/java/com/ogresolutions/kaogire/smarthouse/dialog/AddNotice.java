package com.ogresolutions.kaogire.smarthouse.dialog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.ogresolutions.kaogire.smarthouse.activities.NoticeActivity;
import com.ogresolutions.kaogire.smarthouse.model.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Njuguna on 16-Jul-16.
 */
public class AddNotice extends AppCompatActivity {
    EditText edtDetail, edtTitle;
    private ProgressDialog pDialog;
    Button btnOk, btnCancel;
    String detail, title;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_new);

        edtDetail = (EditText)findViewById(R.id.edtNoticeDetail);
        edtTitle = (EditText)findViewById(R.id.edtNoticeTitle);
        btnOk = (Button)findViewById(R.id.btnNoticeOk);
        btnCancel = (Button)findViewById(R.id.btnNoticeCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edtDetail.getText()) || TextUtils.isEmpty(edtTitle.getText())){
                    if(TextUtils.isEmpty(edtDetail.getText())){
                        edtDetail.setError("Please enter Detail");
                    }
                    if(TextUtils.isEmpty(edtTitle.getText())){
                        edtTitle.setError(("Please enter Title"));
                    }
                }else{
                    JSONObject json = new JSONObject();
                    try {
                        json.put("title", edtTitle.getText().toString());
                        json.put("body", edtDetail.getText().toString());
                        Log.i("joe", json.toString(1));


                        pDialog = new ProgressDialog(AddNotice.this);
                        pDialog.setMessage("Loading...");
                        pDialog.setCancelable(false);
//
                        makeJsonObjReq(json);
                        startActivity(new Intent(AddNotice.this, NoticeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
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
                Const.NOTICE_ADD, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getBoolean("error")== false){
                                Log.i("joe",response.getString("message"));
                                Toast.makeText(AddNotice.this,"success", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(AddNotice.this,"sorry try again", Toast.LENGTH_LONG).show();
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
