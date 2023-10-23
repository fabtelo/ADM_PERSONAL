package com.example.adm_personal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SecondActivity extends AppCompatActivity {
    private DatabaseReference nodo= FirebaseDatabase.getInstance().getReference("P-ASTX1");
    private Button registroB,turnosB,permisosB,entrandoB,saliendoB,enpozoB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


    }
}