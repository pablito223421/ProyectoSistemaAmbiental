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

import model.Temperatura;

public class temperatura_plantas extends AppCompatActivity {

    ImageButton btn_temperatura;
    EditText txt_tempinicial;
    EditText txt_tempfinal;
    EditText txt_tempbase;
    EditText txt_temptotal;

    private static final int RCODE = 28;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura_plantas);
        txt_tempinicial=findViewById(R.id.txt_inicial);
        txt_tempbase=findViewById(R.id.txt_base);
        txt_tempfinal=findViewById(R.id.txt_final);
        txt_temptotal=findViewById(R.id.txt_total);

        btn_temperatura=findViewById(R.id.boton_temperatura);

        btn_temperatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturarInfoTemperatura();
            }
        });
          inicializar_basetemperatura();
    }

    public void capturarInfoTemperatura(){
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

    public void inicializar_basetemperatura(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
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
            double ins_tempinicial = Double.parseDouble(txt_tempinicial.getText().toString());
            double ins_tempbase = Double.parseDouble(txt_tempbase.getText().toString());
            double ins_tempfinal = Double.parseDouble(txt_tempfinal.getText().toString());
            double ins_temptotal = Double.parseDouble(txt_temptotal.getText().toString());
            if (result.toString() == "") {
                validarTemperatura();
            } else {
                Temperatura temperatura = new Temperatura();
                temperatura.setId_temperatura(UUID.randomUUID().variant());
                if(result.toString()=="ACTIVAR" || result.toString()== "activar"){
                    txt_tempbase.setText(result.get(0));
                    txt_tempinicial.setText(result.get(1));
                    txt_tempfinal.setText(result.get(2));
                    txt_temptotal.setText(result.get(3));
                    //Falta activación y conexión con Node Esp MCU 8266

                    if(result.toString()=="GUARDAR" || result.toString()== "guardar"){
                        databaseReference.child("Temperatura").child(temperatura.getId_temperatura()).setValue(0);
                        Toast.makeText(this, "Guardar temperatura", Toast.LENGTH_SHORT).show();
                        intent = new Intent (view.getContext(), Principal_planta.class);
                    }
                }
            }
        }
    }

    public void validarTemperatura(){
        String v_tempinicial= txt_tempinicial.getText().toString();
        String v_tempbase=txt_tempbase.getText().toString();
        String v_tempfinal=txt_tempfinal.getText().toString();
        String v_temptotal=txt_temptotal.getText().toString();

        if(v_tempinicial.equals("")){
           txt_tempinicial.setError("required");
        } else if (v_tempbase.equals("")) {
            txt_tempbase.setError("required");
        }else if (v_tempfinal.equals("")){
            txt_tempfinal.setError("required");
        } else if (v_temptotal.equals("")) {
            txt_temptotal.setError("required");
        }
    }
}