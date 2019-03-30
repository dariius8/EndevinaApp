package com.example.endevinaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class TableRecords extends AppCompatActivity {

    private List<Player> players2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_records);

        players2 = MainActivity.players;
        final TextView nombre = findViewById(R.id.lbl_Nombre);
        final TextView intentos = findViewById(R.id.lbl_Intentos);
        nombre.setText("");
        intentos.setText("");
        if(players2.size() > 0){
            Collections.sort(players2);
            for (Player play: players2) {
                nombre.setText(nombre.getText() + play.getNombre() + "\n");
                intentos.setText(intentos.getText() + String.valueOf(play.getIntents()) + "\n");
            }
        }else{
            nombre.setText(nombre.getText() + "NO HAY DATOS!");
        }
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}