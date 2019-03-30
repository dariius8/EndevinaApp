package com.example.endevinaapp;

public class Player implements Comparable<Player>{

    private String nombre;
    private int intents;

    public Player (String nombre, int intents) {
        this.nombre = nombre;
        this.intents = intents;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIntents() {
        return intents;
    }

    @Override
    public int compareTo(Player play){
        return this.intents - play.intents;
    }
}