package com.example.adm_personal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityPermisos extends AppCompatActivity {
    private Button btnVolver3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permisos);
        btnVolver3=findViewById(R.id.button12);
        btnVolver3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volver3();
            }
        });
    }

    private void volver3() {
        finish();
    }
}