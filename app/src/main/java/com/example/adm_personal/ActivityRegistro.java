package com.example.adm_personal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityRegistro extends AppCompatActivity {
//spinners    
    private Spinner spAgno,spMes,spDia;
    private ArrayList<String> arrayListAgno,arrayListMes,arrayListDia;
    private ArrayAdapter<String> adArrayAgno,adArrayMes,adArrayDia;
    private DatabaseReference nodo= FirebaseDatabase.getInstance().getReference("g-astx1");
//scroll view
    private String tempo;
    private ArrayList<String> arrayListRegistros=new ArrayList<String>();
    private ArrayList<String> arrayListIndividual=new ArrayList<>();
    private String muestraRegistr="";
    private TextView textoScroll;
//otros
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
        textoScroll=findViewById(R.id.textView4);
//instanciando spinners        
        spAgno=findViewById(R.id.spinnerAgno);
        spMes=findViewById(R.id.spinnerMes);
        spDia=findViewById(R.id.spinnerDia);
        
        arrayListAgno=new ArrayList<String>();
        arrayListMes=new ArrayList<String>();
        arrayListDia=new ArrayList<String>();
        
        adArrayAgno=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,arrayListAgno);
        adArrayMes=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,arrayListMes);
        adArrayDia=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,arrayListDia);
        
        spAgno.setAdapter(adArrayAgno);
        spMes.setAdapter(adArrayMes);
        spDia.setAdapter(adArrayDia);
        
        spAgno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                seteaMes();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spMes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                seteaDia();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spDia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                arrayListRegistros.clear();
                muestraRegistr="";
                textoScroll.setText(muestraRegistr);
                seteaRegistro();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        seteaAgno();
    }

    private void seteaAgno() {
        nodo.child("registro").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item:snapshot.getChildren()){
                    arrayListAgno.add(item.getKey().toString());
                }adArrayAgno.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void seteaRegistro() {
        nodo.child("registro").child(spAgno.getSelectedItem().toString()).child(spMes.getSelectedItem().toString()).child(spDia.getSelectedItem().toString()).
                addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item:snapshot.getChildren()){
                    arrayListRegistros.add(item.getKey().toString());
                }imprimeRegistro();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Firebase 404",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void imprimeRegistro() {
            for(String i:arrayListRegistros){
            nodo.child("registro").child(spAgno.getSelectedItem().toString()).child(spMes.getSelectedItem().toString()).
                    child(spDia.getSelectedItem().toString()).child(i).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            muestraRegistr=muestraRegistr+"\n"+snapshot.child("hora").getValue().toString()+"\n        CHOFER= "+snapshot.child("nombre").getValue().toString()+"\n"+"        CI="+
                                    snapshot.child("CI").getValue().toString()+"; PLACA="+snapshot.child("placa").getValue().toString()+"\n"+"        EMPRESA="+
                                    snapshot.child("empresa").getValue().toString()+"; MOTIVO="+snapshot.child("motivo").getValue().toString()+"\n"+"        "+
                                    snapshot.child("origen").getValue().toString()+"/"+snapshot.child("destino").getValue().toString()+"\n"+"                  PASAJEROS:"+
                                    snapshot.child("pasajeros").getValue().toString()+"\n";
                            textoScroll.setText(muestraRegistr);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
        }
    }

    private void seteaDia() {
        nodo.child("registro").child(spAgno.getSelectedItem().toString()).
            child(spMes.getSelectedItem().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item:snapshot.getChildren()){
                    arrayListDia.add(item.getKey().toString());
                }adArrayDia.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void seteaMes() {
        nodo.child("registro").child(spAgno.getSelectedItem().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item:snapshot.getChildren()){
                    arrayListMes.add(item.getKey().toString());
                }adArrayMes.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void volver2() {
        finish();
    }
}