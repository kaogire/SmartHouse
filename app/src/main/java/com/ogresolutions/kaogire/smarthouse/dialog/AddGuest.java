package com.ogresolutions.kaogire.smarthouse.dialog;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ogresolutions.kaogire.smarthouse.R;
import com.ogresolutions.kaogire.smarthouse.activities.GuestActivity;
import com.ogresolutions.kaogire.smarthouse.model.MySql;
import com.ogresolutions.kaogire.smarthouse.objects.Guest;

/**
 * Created by Njuguna on 4/30/2016.
 */
public class AddGuest extends AppCompatActivity {
    String guestName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dg_add_guest);

        final EditText edtName = (EditText)findViewById(R.id.edtGuestName);
        Button btnOk = (Button)findViewById(R.id.btnGuestOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edtName.getText())){
                    edtName.setError("Enter name");
                }
                else{
                    guestName = edtName.getText().toString();
                    Guest guest = new Guest(guestName);
                    MySql db = new MySql(AddGuest.this);
                    db.addGuest(guest);
                    Log.i("joe", "Guest created");
                    Intent intent = new Intent(AddGuest.this, GuestActivity.class);
                    startActivity(intent);
                }
            }
        });
        Button btnCancel = (Button)findViewById(R.id.btnGuestCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
