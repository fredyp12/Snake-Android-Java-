package com.example.logica;

import android.util.Log;

import com.example.controlador.ControlTouch;
import com.example.snake.MainActivity;

import java.util.ArrayList;
import java.util.Observable;

public class Snake extends Observable {

    public final static int FILAS = 15;
    public final static int COLUMNAS = 10;



    int matris [][] =new int[Snake.FILAS][Snake.COLUMNAS];
    private   Body snakeBody;
    private  ArrayList elemento;
    private MainActivity main;
    private boolean dat=false;

    public Snake(MainActivity main){
        this.main=main;
    }

    public void newGame(){

        this.elemento = new ArrayList();
        this.snakeBody= new Body();
        this.elemento.add(this.snakeBody.getPosicion());
        this.setChanged();
        this.notifyObservers(this.elemento);

//
        Thread hilo = new Thread() {
            public void run() {
                try {
                    while(dat==false) {
                        Thread.sleep(1000);

                        main.runOnUiThread(new Runnable() {

                            @Override
                            public void run(){
//                            Toast.makeText(main.getApplicationContext(), "movimiento", Toast.LENGTH_SHORT).show();
                               main.moverSnake(snakeBody.direccion);
                                Log.d("Segmento1", snakeBody.direccion+"  D");

                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        hilo.start();

    }

    public boolean moverSnake(String movimiento) {
        this.elemento= new ArrayList();
        boolean fin=false;

        ArrayList posicionAnterior = new ArrayList();
        for(int i=0;i<this.snakeBody.getPosicion().size(); i++) {
            int vec []= new  int[2];
            int vec1 [] = (int[]) this.snakeBody.getPosicion().get(i);
            if(vec1[1]==0) return true;
            for (int j=0; j<2;j++){
                vec[j]=vec1[j];
            }
            posicionAnterior.add(vec);
        }

        if(this.snakeBody.moverSnake(movimiento)) {
            this.elemento.add(this.snakeBody.getPosicion());
            this.elemento.add(posicionAnterior);
            this.setChanged();
            this.notifyObservers(this.elemento);
        }



        return fin;
    }

    public void changeSnake(String movimiento) {
        if(movimiento==ControlTouch.ARRIBA) {
            if(this.snakeBody.direccion!= ControlTouch.ARRIBA) {
                this.snakeBody.direccion=movimiento;
            }
        }else if(movimiento==ControlTouch.ABAJO) {
            if(this.snakeBody.direccion!=ControlTouch.ABAJO) {
                this.snakeBody.direccion=movimiento;
            }
        }else if(movimiento==ControlTouch.DERECHA) {
            if(this.snakeBody.direccion!=ControlTouch.IZQUIERDA) {
                this.snakeBody.direccion=movimiento;
            }
        }else if(movimiento==ControlTouch.IZQUIERDA) {
            if(this.snakeBody.direccion!=ControlTouch.DERECHA) {
                this.snakeBody.direccion=movimiento;
            }
        }

    }



}
