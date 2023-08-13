package com.example.proyectossistriegouacm;

import static java.util.UUID.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

import model.Plantas;

public class Registrar_Planta extends AppCompatActivity {

    Button ima_camara;
    ImageView ima_planta;
    EditText nom_planta;
    EditText caract_planta;
    EditText ima_nomplanta;
    ImageButton btn_planta;
    private static final int RCODE = 28;
    
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_planta);

        ima_camara= findViewById(R.id.btn_imagen);
        ima_planta= findViewById(R.id.image_plantas);

        nom_planta= findViewById(R.id.txt_nombreplanta);
        caract_planta= findViewById(R.id.txt_caracplanta);
        ima_nomplanta=findViewById(R.id.txt_nomimalanta);
        btn_planta= findViewById(R.id.btn_guardarplanta);
        btn_planta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                capturarVoz();
            }
        });
        InicializarFirebase();
    }

    public void InicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void capturarVoz(){
        Intent intent= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());
        if (intent.resolveActivity(getPackageManager())!=null)
        {
            startActivityIfNeeded(intent, RCODE);
        } else
        {
            Log.e("ERROR","Su dispositivo no admite entrada de voz");
        }
    }

    @Override
    public  void onActivityResult(int requestCode,
                  int resultCode, Intent data)
    {
        View view = null;
        Intent intent;

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RCODE && resultCode == RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String ins_nombreplanta,ins_caracteristicas,ins_imagenplanta;

            ins_nombreplanta= nom_planta.getText().toString();
            ins_caracteristicas=caract_planta.getText().toString();
            ins_imagenplanta=ima_nomplanta.getText().toString();
            if(result.toString()==""){
                validarPlanta();
            }else{
                Plantas plantas = new Plantas();
                plantas.setId_planta(UUID.randomUUID().variant());
                if(result.toString() != ""){
                    nom_planta.setText(result.get(0));
                    caract_planta.setText(result.get(1));
                    ima_nomplanta.setText(result.get(2));
                    plantas.setNombre_planta(ins_nombreplanta);
                    plantas.setCaracteristicas(ins_caracteristicas);
                    plantas.setImagen_planta(ins_imagenplanta);

                   if(result.toString()=="GUARDAR" || result.toString()== "guardar"){
                    databaseReference.child("Plantas").child(plantas.getId_planta()).setValue(0);
                    Toast.makeText(this, "Agregar planta", Toast.LENGTH_SHORT).show();
                       intent = new Intent (view.getContext(), Principal_planta.class);
                   }
                }
            }
        }
    }

    public void validarPlanta(){
        String v_nomplanta,v_caracteristicas,v_imagenplanta;

        v_nomplanta= nom_planta.getText().toString();
        v_caracteristicas= caract_planta.getText().toString();
        v_imagenplanta=ima_nomplanta.getText().toString();

        if(v_nomplanta.equals("")){
            nom_planta.setError("required");
        }else if(v_caracteristicas.equals("")){
            caract_planta.setError("required");
        }else if(v_imagenplanta.equals("")){
            ima_nomplanta.setError("required");
        }
    }



}

