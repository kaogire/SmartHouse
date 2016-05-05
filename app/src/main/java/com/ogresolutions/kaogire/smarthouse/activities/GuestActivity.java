package com.ogresolutions.kaogire.smarthouse.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.ogresolutions.kaogire.smarthouse.R;
import com.ogresolutions.kaogire.smarthouse.adapter.GuestAdapter;
import com.ogresolutions.kaogire.smarthouse.dialog.AddGuest;
import com.ogresolutions.kaogire.smarthouse.model.MySql;
import com.ogresolutions.kaogire.smarthouse.objects.Guest;

import java.util.ArrayList;

/**
 * Created by Njuguna on 4/27/2016.
 */
public class GuestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView listGuest = (ListView)findViewById(R.id.lvGuest);
        MySql db = new MySql(this);
        ArrayList<Guest> arrayGuest = db.getAllGuests();
        GuestAdapter adapter = new GuestAdapter(this, arrayGuest);
        listGuest.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabGuest);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuestActivity.this, AddGuest.class);
                startActivity(intent);
            }
        });
    }
}
