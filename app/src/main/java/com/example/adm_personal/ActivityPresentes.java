package com.example.adm_personal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityPresentes extends AppCompatActivity {
private Button btnVolver4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentes);
        btnVolver4=findViewById(R.id.button14);
        btnVolver4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volver4();
            }
        });
    }

    private void volver4() {
        finish();
    }
}