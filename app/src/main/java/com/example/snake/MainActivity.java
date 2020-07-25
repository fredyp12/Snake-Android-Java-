package com.example.snake;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.view.Display;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.controlador.ControlTouch;
import com.example.controlador.Hilo;
import com.example.logica.Snake;

import java.util.Observable;
import java.util.Observer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Snake snake;
    private ControlTouch controlTouch;
    Dibujo dibujo;
    private boolean fin=false;
    private Hilo hilo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//--------------------------------------------------------------------------------
        this.dibujo = new Dibujo(this);
        this.controlTouch = new ControlTouch(this);
        this.snake=new Snake(this);
        this.snake.addObserver(this.dibujo);
        this.snake.newGame();




    }


    public boolean moverSnake(String movimiento) {
        return this.snake.moverSnake(movimiento);
    }

    public void changeSnake(String movimiento) {
        this.snake.changeSnake(movimiento);
    }

}