package com.ogresolutions.kaogire.smarthouse.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ogresolutions.kaogire.smarthouse.Const;
import com.ogresolutions.kaogire.smarthouse.R;
import com.ogresolutions.kaogire.smarthouse.adapter.NoticeAdapter;
import com.ogresolutions.kaogire.smarthouse.dialog.AddNotice;
import com.ogresolutions.kaogire.smarthouse.model.AppController;
import com.ogresolutions.kaogire.smarthouse.model.MyTime;
import com.ogresolutions.kaogire.smarthouse.objects.Notice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Njuguna on 5/8/2016.
 */
public class NoticeActivity extends AppCompatActivity {
    private String TAG = NoticeActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    JSONArray notices = new JSONArray();
    ArrayList<Notice> myArray = new ArrayList<>();
    NoticeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice);


        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        makeJsonObjReq();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        ListView lvNotice = (ListView) findViewById(R.id.lvNotice);
        adapter = new NoticeAdapter(NoticeActivity.this, myArray);
        lvNotice.setAdapter(adapter);
        final LayoutInflater inflater = NoticeActivity.this.getLayoutInflater();
        lvNotice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NoticeActivity.this);
                View dialogView = inflater.inflate(R.layout.notice_item_full, null);
                builder.setView(dialogView);
                TextView title = (TextView) dialogView.findViewById(R.id.tvNoticeTitleF);
                TextView body = (TextView) dialogView.findViewById(R.id.tvNoticeBodyF);
                TextView house = (TextView) dialogView.findViewById(R.id.tvNoticeHouseF);
                TextView time = (TextView) dialogView.findViewById(R.id.tvNoticeTimeF);
                title.setText(myArray.get(position).getTitle());
                house.setText("House: " + myArray.get(position).getHouseNo());
                time.setText(MyTime.displayDate(myArray.get(position).getTimeUp()));
                body.setText(myArray.get(position).getBody());
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabNotice);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoticeActivity.this, AddNotice.class);
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
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
                Const.NOTICE_ALL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("error") == false) {
                                notices = response.getJSONArray("notice");
                                for (int i = 0; i < notices.length(); i++) {
                                    JSONObject notice = (JSONObject) notices.get(i);
                                    int id = (notice.getInt("id"));
                                    String title = (notice.getString("title"));
                                    String body = (notice.getString("body"));
                                    String time = (notice.getString("time"));
                                    String hse_no = (notice.getString("hse_no"));

                                    myArray.add(new Notice(id, hse_no, title, body, time));
                                    Log.i("joe", myArray.toString());
                                    int size = myArray.size();
                                    Log.i("joe", notice.toString(2));

                                }
                                Toast.makeText(NoticeActivity.this, "success", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(NoticeActivity.this, "sorry there are no notices", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        hideProgressDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
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

//    public void makeArraRequest(){
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                try {
//                    Log.i("joe", response.toString(1));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    VolleyLog.d(TAG, "Error: " + error.getMessage());
//                    hideProgressDialog();
//                }
//        });
//        AppController.getInstance().addToRequestQueue(jsonArrayRequest,"joeArr");
//    }

}
