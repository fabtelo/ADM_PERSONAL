package com.example.adm_personal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityRegistro extends AppCompatActivity {
private Button btnVolver2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        btnVolver2=findViewById(R.id.button11);
        btnVolver2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volver2();
            }
        });
    }

    private void volver2() {
        finish();
    }
}