package com.ogresolutions.kaogire.smarthouse.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ogresolutions.kaogire.smarthouse.R;

/**
 * Created by Njuguna on 16-Jul-16.
 */
public class Emergency extends AppCompatActivity {
    Button btnFire, btnHealth, btnGas, btnIntrusion;
    String emergency;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency);

        btnFire = (Button)findViewById(R.id.btnEFire);
        btnGas = (Button)findViewById(R.id.btnEGas);
        btnHealth = (Button)findViewById(R.id.btnEHealth);
        btnIntrusion = (Button)findViewById(R.id.btnEIntrusion);
        btnHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Emergency.this, "Calling for ambulance service", Toast.LENGTH_LONG).show();
            }
        });
        btnGas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Emergency.this, "Calling for fire service", Toast.LENGTH_LONG).show();
            }
        });
        btnIntrusion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Emergency.this, "Calling security personnel", Toast.LENGTH_LONG).show();
            }
        });
        btnFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Emergency.this, "Calling for fire service", Toast.LENGTH_LONG).show();
            }
        });
    }
}
