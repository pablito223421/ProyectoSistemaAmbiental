package com.example.proyectossistriegouacm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

import model.Agua;


public class Agua_plantas extends AppCompatActivity {

    ImageButton boton_agua;
    EditText text_agua;

    private static final int RCODE = 28;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agua_plantas);
        text_agua=findViewById(R.id.txt_cantidadagua);
        boton_agua=findViewById(R.id.btn_agua);

        boton_agua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturarInfoagua();
            }
        });
        InicializarFirebase();
    }

    public void InicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void capturarInfoagua(){
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
                                  int resultCode, Intent data) {
        View view = null;
        Intent intent;

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RCODE && resultCode == RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            double ins_vaagua= Double.parseDouble(text_agua.getText().toString());
            if(result.toString()==""){
                validarAgua();
            }else{
               Agua agua = new Agua();
               agua.setId_agua(UUID.randomUUID().variant());
               if(result.toString()=="ACTIVAR" || result.toString()== "activar"){
                   text_agua.setText(result.get(0));
                   //Falta activación y conexión con Node Esp MCU 8266

                   if(result.toString()=="GUARDAR" || result.toString()== "guardar"){
                       databaseReference.child("Agua").child(agua.getId_agua()).setValue(0);
                       Toast.makeText(this, "Guardar agua", Toast.LENGTH_SHORT).show();
                       intent = new Intent (view.getContext(), Principal_planta.class);
                   }
               }
            }
        }
    }

    public void validarAgua(){
       String v_cantagua=text_agua.getText().toString();
        if(v_cantagua.equals("")){
            text_agua.setError("required");
        }
    }

}