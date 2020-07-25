package com.example.controlador;

import android.text.method.Touch;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.snake.MainActivity;

public class ControlTouch {

    public final static  String ARRIBA= "Arriba";
    public final static  String ABAJO= "Abajo";
    public final static  String DERECHA= "Derecha";
    public final static  String IZQUIERDA= "Izquierda";

    public MainActivity main;
    private   String movimiento;

    public ControlTouch(MainActivity main) {
        this.main=main;
        boolean fin=false;

        View fs = main.getWindow().getDecorView();
        fs.setOnTouchListener(new View.OnTouchListener() {
            float x1,x2;
            float y1,y2;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    x1 = event.getX();
                    y1 = event.getY();
                }else if(event.getAction() == MotionEvent.ACTION_UP) {
                    x2 = event.getX();
                    y2 = event.getY();
                    movimiento(x1,x2,y1,y2);
                }
                return false;
            }
        });

    }


    public void  movimiento(float x1,float x2,float y1, float y2) {
        float difX = x2-x1;
        float difY = y2-y1;
        boolean fin;

        if(Math.abs(difX) > Math.abs(difY)) {
            if(difX > 0) {
                movimiento = ControlTouch.DERECHA;
            }
            else {
                movimiento = ControlTouch.IZQUIERDA;
            }
        } else {
            if(difY > 0) {
                movimiento =ControlTouch.ABAJO;
            } else {
                movimiento = ControlTouch.ARRIBA;
            }
        }
        Toast.makeText(this.main.getApplicationContext(), movimiento, Toast.LENGTH_SHORT).show();
        this.main.changeSnake(movimiento);
//        this.main.moverSnake(movimiento);
    }

}
