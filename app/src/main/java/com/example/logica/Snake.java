package com.example.logica;

import android.util.Log;

import com.example.controlador.ControlTouch;

import java.util.ArrayList;
import java.util.Observable;

public class Snake extends Observable {

    public final static int FILAS = 15;
    public final static int COLUMNAS = 10;



    int matris [][] =new int[Snake.FILAS][Snake.COLUMNAS];
    private  Body snakeBody;
    private  ArrayList elemento;

    public Snake(){

    }

    public void newGame(){
        this.elemento = new ArrayList();
        this.snakeBody= new Body();
        this.elemento.add(this.snakeBody.getPosicion());
        this.setChanged();
        this.notifyObservers(this.elemento);
    }

    public void moverSnake(String movimiento) {
        this.elemento= new ArrayList();

        ArrayList posicionAnterior = new ArrayList();

        for(int i=0;i<this.snakeBody.getPosicion().size(); i++) {
            int vec []= new  int[2];
            int vec1 [] = (int[]) this.snakeBody.getPosicion().get(i);
            for (int j=0; j<2;j++){
                vec[j]=vec1[j];
            }
            posicionAnterior.add(vec);
        }



        if(movimiento == ControlTouch.IZQUIERDA) {
            if(this.snakeBody.izquierda()) {
                this.elemento.add(this.snakeBody.getPosicion());
                this.elemento.add(posicionAnterior);
                this.setChanged();
                this.notifyObservers(this.elemento);
            }

        }
    }
}
