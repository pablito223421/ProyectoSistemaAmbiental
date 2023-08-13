package com.example.proyectossistriegouacm;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.Locale;

public class Principal_planta extends AppCompatActivity {
    ImageButton imbtnmicro;
    private static final int RCODE = 28;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_planta);
        imbtnmicro =findViewById(R.id.microprin);
        imbtnmicro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                capturaVoz();
            }
        });
    }

    private void capturaVoz(){
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
    protected  void onActivityResult(int requestCode,
                                     int resultCode, Intent data)
    {
        View view = null;
        Intent intent;
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RCODE && resultCode == RESULT_OK && data != null)
        {
            ArrayList<String> result = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
                 if(result.toString()== "agua" || result.toString()=="AGUA"){
                   intent = new Intent (view.getContext(),Agua_plantas.class);
                 } else if (result.toString()== "temperatura" || result.toString()=="TEMPERATURA") {
                     intent = new Intent (view.getContext(),temperatura_plantas.class);
                 } else if (result.toString()== "humedad" || result.toString()=="HUMEDAD") {
                     intent = new Intent (view.getContext(),Humedad_plantas.class);
                 }else if(result.toString()== "salir" || result.toString()=="SALIR"){
                     AlertDialog.Builder alerta = new AlertDialog.Builder(Principal_planta.this);
                     alerta.setMessage("Desea  salir del programa")
                             .setCancelable(false)
                             .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialog, int which) {
                                     finish();
                                 }
                             }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialog, int which) {
                                     dialog.cancel();
                                 }
                             });
                         AlertDialog titulo =alerta.create();
                         titulo.setTitle("Salir del programa");
                         titulo.show();
                 }else{
                 }
        }

    }
}