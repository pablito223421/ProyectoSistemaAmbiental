package com.example.proyectossistriegouacm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

import model.Humedad;

public class Humedad_plantas extends AppCompatActivity {

    ImageButton btn_humedad;
    EditText txt_suelohumedo;
    EditText txt_suelo_seco;
    EditText txt_porcentajehumedad;

    private static final int RCODE = 28;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humedad_plantas);
        txt_suelohumedo=findViewById(R.id.txt_suelohum);
        txt_suelo_seco=findViewById(R.id.txt_suelosec);
        txt_porcentajehumedad=findViewById(R.id.txt_porcentaje);
        btn_humedad=findViewById(R.id.btn_guardahum);

        btn_humedad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturarinfoHumedad();
            }
        });
        InicializarFirebase();
    }

    public void InicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void capturarinfoHumedad(){
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
            double ins_sueloseco = Double.parseDouble(txt_suelo_seco.getText().toString());
            double ins_suelohumedo = Double.parseDouble(txt_suelohumedo.getText().toString());
            double ins_porcentaje = Double.parseDouble(txt_porcentajehumedad.getText().toString());

            if (result.toString() == "") {
                validarHumedad();
            } else {
                Humedad humedad = new Humedad();
                humedad.setId_humedad(UUID.randomUUID().variant());
                if (result.toString() == "ACTIVAR" || result.toString() == "activar") {
                    txt_suelo_seco.setText(result.get(0));
                    txt_suelohumedo.setText(result.get(1));
                    txt_porcentajehumedad.setText(result.get(2));
                    //Falta activación y conexión con Node Esp MCU 8266

                    

                    if (result.toString() == "GUARDAR" || result.toString() == "guardar") {
                        databaseReference.child("Agua").child(humedad.getId_humedad()).setValue(0);
                        Toast.makeText(this, "Guardar agua", Toast.LENGTH_SHORT).show();
                        intent = new Intent(view.getContext(), Principal_planta.class);
                    }
                }
            }
        }
    }


    public void validarHumedad(){
      String  v_sueloseco=txt_suelo_seco.getText().toString();
      String v_suelohumedo=txt_suelohumedo.getText().toString();
      String v_porcentaje=txt_porcentajehumedad.getText().toString();

      if(v_suelohumedo.equals("")){
          txt_suelohumedo.setError("required");
      } else if (v_sueloseco.equals("")) {
          txt_suelo_seco.setError("required");
      } else if (v_porcentaje.equals("")) {
          txt_porcentajehumedad.setError("required");
      }
    }
}