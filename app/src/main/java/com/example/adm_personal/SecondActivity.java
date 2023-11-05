package com.example.adm_personal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SecondActivity extends AppCompatActivity {
    private DatabaseReference nodo= FirebaseDatabase.getInstance().getReference("P-ASTX1");
    private Button registroB,turnosB,permisosB,entrandoB,saliendoB,enpozoB,btnAgregar,buscarVehiculo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//instanciando botones
        turnosB=findViewById(R.id.button);
        registroB=findViewById(R.id.button2);
        permisosB=findViewById(R.id.button3);
        entrandoB=findViewById(R.id.button4);
        saliendoB=findViewById(R.id.button5);
        enpozoB=findViewById(R.id.button6);
        btnAgregar=findViewById(R.id.button7);
        buscarVehiculo=findViewById(R.id.button13);
//agregando acciones a los botones
        turnosB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irTurno();
            }
        });
        registroB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irRegistro();
            }
        });
        permisosB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irPermisos();
            }
        });
        entrandoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                entrando();
            }
        });
        saliendoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saliendo();
            }
        });
        enpozoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enPozo();
            }
        });
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregar();
            }
        });
        buscarVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarVehiculito();
            }
        });
    }

    private void buscarVehiculito() {
        Toast.makeText(this,"buscando Vehiculito",Toast.LENGTH_SHORT).show();
    }

    private void agregar() {
        Toast.makeText(this,"Agregando Pasajero",Toast.LENGTH_SHORT).show();
    }
    private void enPozo() {
        Intent intent=new Intent(this,ActivityPresentes.class);
        startActivity(intent);
    }

    private void saliendo() {
        Toast.makeText(this,"saliendo",Toast.LENGTH_SHORT).show();
    }

    private void entrando() {
        Toast.makeText(this,"ingresando",Toast.LENGTH_SHORT).show();
    }

    private void irPermisos() {
        Intent intent=new Intent(this, ActivityPermisos.class);
        startActivity(intent);
    }

    private void irRegistro() {
        Intent intent=new Intent(this,ActivityRegistro.class);
        startActivity(intent);
    }

    private void irTurno() {
        Intent intent=new Intent(this,ActivityTurnos.class);
        startActivity(intent);
    }

}