package com.arcentales.streamreader;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText ced, apell,nom,cajaDatos;
    Button escribir,leer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ced = (EditText)findViewById(R.id.cedula);
        apell = (EditText)findViewById(R.id.apellido);
        nom = (EditText)findViewById(R.id.nombre);
        cajaDatos = (EditText)findViewById(R.id.caja);

        escribir = (Button) findViewById(R.id.boton_escribir);
        leer = (Button)findViewById(R.id.boton_leer);

        escribir.setOnClickListener(this);
        leer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.boton_escribir:
                try {
                    OutputStreamWriter escritor = new OutputStreamWriter(openFileOutput("archivo2.txt", Context.MODE_PRIVATE));
                    escritor.write(ced.getText().toString()+", "+apell.getText().toString()+", "+nom.getText().toString()+", ");
                    escritor.flush();
                    escritor.close();
                }catch (IOException ex){
                    Log.e("Archivo Mi", "error en el archivo de escritura");
                }
                break;
            case R.id.boton_leer:
                try {
                    BufferedReader lector = new BufferedReader(new InputStreamReader(openFileInput("archivo2.txt")));
                    String datos = lector.readLine();
                    String [] listaPersonas = datos.split(";");
                    for (int i =0; i<listaPersonas.length; i++){
                        cajaDatos.append(listaPersonas[i].split(",")[0]+" "+listaPersonas[i].split(",")[1]+" "+listaPersonas[i].split(",")[2]+", ");
                    }
                    //cajaDatos.setText(datos);
                    lector.close();
                }catch (IOException ex){
                    Log.e("Archivo mi","Error en la lectura del archivo"+ex.getMessage());
                }
                break;
        }

    }

}
