package com.example.adm_personal;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    private ScrollView scPasajeros;
    private EditText txtPlaca,txtnEmpresa,txtmotivoT,txtnPasajero,txtnChofer,txtnroCI;
    private int choferSN;
    private String placaC;
    private DatabaseReference nodo= FirebaseDatabase.getInstance().getReference("g-astx1");
    private Button registroB,turnosB,permisosB,entrandoB,saliendoB,enpozoB,btnAgregar,buscarVehiculo;
//seteo spinners
    private Spinner spTipoV,spTurnos,spOrigen,spDestino,spChofer;
    private ArrayList<String> spArrayTipoV,spArrayTurnos,spArrayOD,spArrayOD2,spArrayChofer;
    private ArrayAdapter<String> adapterArrayTipoV,adapterArrayTurnos,adapterArrayOD,adapterArrayOD2,adapterArrayChofer;
    private DatabaseReference refTipoV;
//METODO ON CREATE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        refTipoV=FirebaseDatabase.getInstance().getReference("g-astx1").child("tipo vehiculo");
//instaciando ScrollView
        scPasajeros=findViewById(R.id.scrollviewPasajeros);
        scPasajeros.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                    esconderKeyboard();
                return false;
            }
        });
//instanciando editText
        txtPlaca=findViewById(R.id.editTextPlaca);
        txtnEmpresa=findViewById(R.id.editTextEmpresa);
        txtmotivoT=findViewById(R.id.editTextMotivo);
        txtnPasajero=findViewById(R.id.editTextText);
        txtnChofer=findViewById(R.id.editTextNameChofer);
        txtnroCI=findViewById(R.id.editTextCiChofer);
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
        spArrayOD2=new ArrayList<String>();
        adapterArrayOD= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,spArrayOD);
        adapterArrayOD2= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,spArrayOD2);
        spDestino.setAdapter(adapterArrayOD2);
        spOrigen.setAdapter(adapterArrayOD);
        llenarSpOD();

        spChofer=findViewById(R.id.spinnerChofer);
        spArrayChofer=new ArrayList<String>();
        adapterArrayChofer=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,spArrayChofer);
        spChofer.setAdapter(adapterArrayChofer);
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

    private void esconderKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
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
                Toast.makeText(getApplicationContext(),"Error con la Base de Datos",Toast.LENGTH_SHORT).show();
            }
        });
        llenarSpOD2();
    }
    private void llenarSpOD2() {
        nodo.child("destino").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item:snapshot.getChildren()){
                    spArrayOD2.add(item.getValue().toString());
                }
                adapterArrayOD2.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Error con la Base de Datos",Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getApplicationContext(),"Error con la Base de Datos",Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getApplicationContext(),"Error con la Base de Datos",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void buscarVehiculito() {
        esconderKeyboard();
        choferSN=0;
        placaC=" ";
        nodo.child("vehiculos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item:snapshot.getChildren()){
                    if(item.getKey().toString().equalsIgnoreCase(txtPlaca.getText().toString())){
                        choferSN++;
                        placaC=item.getKey().toString();
                    }
                }
                setearSpChofer();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Error con base de datos",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setearSpChofer() {
        spArrayChofer.clear();
        if(choferSN==1){
            nodo.child("vehiculos").child(placaC).child("choferes").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot item:snapshot.getChildren()){
                        spArrayChofer.add(item.getValue().toString());
                    }
                    adapterArrayChofer.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else Toast.makeText(this,"No se encuentra vehiculo",Toast.LENGTH_SHORT).show();
    }


    private void agregar() {
        esconderKeyboard();
        Toast.makeText(this,"Agregando Pasajero",Toast.LENGTH_SHORT).show();
    }
    private void enPozo() {
        Intent intent=new Intent(this,ActivityPresentes.class);
        startActivity(intent);
    }

    private void saliendo() {
        esconderKeyboard();
        Toast.makeText(this,"saliendo",Toast.LENGTH_SHORT).show();
    }

    private void entrando() {
        esconderKeyboard();
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