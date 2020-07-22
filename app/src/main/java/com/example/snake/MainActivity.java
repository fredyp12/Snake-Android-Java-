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
import com.example.logica.Snake;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity {

    private Snake snake;
    private ControlTouch controlTouch;
    Dibujo dibujo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//--------------------------------------------------------------------------------
        this.dibujo = new Dibujo(this);
        this.controlTouch = new ControlTouch(this);
        this.snake=new Snake();
        this.snake.addObserver(this.dibujo);
        this.snake.newGame();
    }

    public void moverSnake(String movimiento) {
        this.snake.moverSnake(movimiento);
    }
}