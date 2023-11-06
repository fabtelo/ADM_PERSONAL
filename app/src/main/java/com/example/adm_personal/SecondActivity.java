package com.example.adm_personal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    private DatabaseReference nodo= FirebaseDatabase.getInstance().getReference("g-astx1");
    private Button registroB,turnosB,permisosB,entrandoB,saliendoB,enpozoB,btnAgregar,buscarVehiculo;
//seteo spinners
    private Spinner spTipoV,spTurnos,spOrigen,spDestino;
    private ArrayList<String> spArrayTipoV,spArrayTurnos,spArrayOD;
    private ArrayAdapter<String> adapterArrayTipoV,adapterArrayTurnos,adapterArrayOD;
    private DatabaseReference refTipoV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        refTipoV=FirebaseDatabase.getInstance().getReference("g-astx1").child("tipo vehiculo");
//instanciando Spinners
        spTipoV=findViewById(R.id.spinnerTipoV);
        spArrayTipoV=new ArrayList<String>();
        adapterArrayTipoV=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,spArrayTipoV);
        spTipoV.setAdapter(adapterArrayTipoV);
        llenarSpTipoV();

        spTurnos=findViewById(R.id.spinnerTurnos);
        spArrayTurnos=new ArrayList<String>();
        adapterArrayTurnos=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,spArrayTurnos);
        spTurnos.setAdapter(adapterArrayTurnos);
        llenarSpTurnos();

        spOrigen=findViewById(R.id.spinnerOrigen);
        spDestino=findViewById(R.id.spinnerDestino);
        spArrayOD=new ArrayList<String>();
        adapterArrayOD= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,spArrayOD);
        spDestino.setAdapter(adapterArrayOD);
        spOrigen.setAdapter(adapterArrayOD);
        llenarSpOD();

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

    private void llenarSpOD() {
        nodo.child("locaciones").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item:snapshot.getChildren()){
                    spArrayOD.add(item.getValue().toString());
                }
                adapterArrayOD.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void llenarSpTurnos() {
        nodo.child("turnos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item:snapshot.getChildren()){
                    spArrayTurnos.add(item.getKey().toString());
                }
                adapterArrayTurnos.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void llenarSpTipoV() {

        refTipoV.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item:snapshot.getChildren()){
                    spArrayTipoV.add(item.getValue().toString());
                }
                adapterArrayTipoV.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void buscarVehiculito() {

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