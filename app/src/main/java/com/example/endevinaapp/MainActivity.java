package com.example.endevinaapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView titulo, intentos;
    private EditText datos;
    private Button adivina, records;

    private int numRandom = (int)(Math.random() * 100 + 1);

    public static List<Player> players = new ArrayList<>();
    private String nombre = "";
    private int numIntentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datos = (EditText) findViewById(R.id.txtDatos);
        numIntentos = 1;
        intentos = (TextView) findViewById(R.id.lblIntentos);
        intentos.setText("INTENTOS  " + numIntentos);
        adivina = (Button) findViewById(R.id.btnAdivina);
        records = (Button) findViewById(R.id.btnRecords);

        adivina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero = datos.getText().toString();
                if(numero.equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(), "INTRODUCE UN NUMERO", Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    int num = Integer.parseInt(numero);
                    if(numRandom == num){
                        Toast toast = Toast.makeText(getApplicationContext(), "HAS ACERTADO!", Toast.LENGTH_LONG);
                        toast.show();
                        guardarNombre();
                        numRandom = (int)(Math.random()*100+1);
                        intentos.setText("INTENTOS  " + numIntentos);
                    }else{
                        numIntentos++;
                        intentos.setText("INTENTOS  " + numIntentos);
                        datos.setText("");
                        if(num < numRandom){
                            Toast.makeText(getApplicationContext(), "EL NUMERO ES MAYOR!",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "EL NUMERO ES MENOR!",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableRecords();
            }
        });
    }

    private void guardarNombre(){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.name_dialog);
        dialog.setTitle("INTRODUCE TU NOMBRE");
        dialog.show();
        Button aceptar = dialog.findViewById(R.id.btnAceptar);
        aceptar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText textName = dialog.findViewById(R.id.txtNombre);
                nombre = textName.getText().toString();
                int intents = numIntentos;
                Player p1 = new Player(nombre, intents);
                players.add(p1);
                crearFichero(p1);
                tableRecords();
                dialog.dismiss();
            }
        });
    }

    public void tableRecords() {
        Intent intent = new Intent(getApplicationContext(), TableRecords.class);
        startActivity(intent);
    }

    //El txt se guarda en /data/data/com.example.endevinaapp/files/logPlayers.txt
    private void crearFichero(Player p){
        try {
            OutputStreamWriter osw = new OutputStreamWriter(openFileOutput("logPlayers.txt", Context.MODE_APPEND));
            osw.write(p.getNombre() + " => " + p.getIntents());
            osw.append("\r\n");
            osw.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
