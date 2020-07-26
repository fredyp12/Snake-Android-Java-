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
    private Food food;
    private Body snakeBody;

    private ArrayList elemento;
    private ArrayList elementoFood;

    private MainActivity main;
    private boolean dat=false;

    public Snake(MainActivity main){
        this.main=main;
    }

    public void newGame(){

        this.elemento = new ArrayList();
        this.snakeBody= new Body();
        this.elemento.add(this.snakeBody.getPosicion());
        //notifica cuerpo
        this.setChanged();
        this.notifyObservers(this.elemento);

//      crea food
        this.elementoFood= new ArrayList();
        food= new Food(this.snakeBody.getPosicion());
        this.elementoFood.add(food.getSegmento());
        this.elementoFood.add(food.getColor());
        this.elementoFood.add(food.getPoint());
        this.setChanged();
        this.notifyObservers(this.elementoFood);



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
//                                Log.d("Segmento1", snakeBody.getPosicion().size()+"  D");

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

        if(this.snakeBody.moverSnake(movimiento, (ArrayList) this.elementoFood.get(0))) {
            this.elemento.add(this.snakeBody.getPosicion());
            this.elemento.add(posicionAnterior);
            this.setChanged();
            this.notifyObservers(this.elemento);
//            si come
            if(this.snakeBody.getPosicion().size()>posicionAnterior.size()) {
                this.food.iniSeg(this.snakeBody.getPosicion());
                this.elementoFood.set(0,this.food.getSegmento());
                this.setChanged();
                this.notifyObservers(this.elementoFood);
            }
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
