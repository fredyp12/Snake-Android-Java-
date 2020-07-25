package com.example.controlador;

import android.widget.Toast;

import com.example.snake.MainActivity;

public class Hilo  {
    private  String movimiento;
    private boolean dat=false;
    public MainActivity main;

    public Hilo (String msg, String movimiento, MainActivity main){
        this.movimiento=movimiento;
        this.main=main;
        hilo.start();
    }

    Thread hilo = new Thread() {
        public void run() {
            try {
                while(dat==false) {
                    Thread.sleep(1000);

                    main.runOnUiThread(new Runnable() {
                        @Override
                        public void run(){
//                            Toast.makeText(main.getApplicationContext(), "movimiento", Toast.LENGTH_SHORT).show();
                            dat=main.moverSnake(movimiento);
                        }
                    });
                }
            } catch (InterruptedException e) {
            }
        }
    };



}
