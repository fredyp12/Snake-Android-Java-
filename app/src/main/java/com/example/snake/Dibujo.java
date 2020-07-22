package com.example.snake;

import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logica.Snake;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Dibujo  implements Observer {

    private GridLayout gridLayoutMatris;
    private LinearLayout linearLayoutMatris;
    private TextView textViewMatris [][];
    private MainActivity main;
    private java.lang.Object Object;

    public Dibujo (MainActivity main) {
        this.main = main;

        this.textViewMatris=new TextView[Snake.FILAS][Snake.COLUMNAS];
        this.gridLayoutMatris = new GridLayout(this.main);
        this.gridLayoutMatris.setRowCount(Snake.FILAS);
        this.gridLayoutMatris.setColumnCount(Snake.COLUMNAS);
        this.linearLayoutMatris = this.main.findViewById(R.id.LayoutPrincipal);

//        Tamaño de pantalla
        Display display =this.main.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        for (int i=0;i<Snake.FILAS;i++) {
            for(int j=0;j<Snake.COLUMNAS;j++) {
                this.textViewMatris[i][j]=new TextView(this.main);
                this.textViewMatris[i][j].setWidth((int) (width*0.99/10));
                this.textViewMatris[i][j].setHeight((int) (height*0.5/10));
                this.textViewMatris[i][j].setBackgroundColor(Color.rgb(67, 129, 91));
                this.gridLayoutMatris.addView(this.textViewMatris[i][j]);
            }
        }
        this.linearLayoutMatris.addView(this.gridLayoutMatris);

    }

    @Override
    public void update(Observable observable, Object o) {
        ArrayList<Object>  elemento = (ArrayList <Object>) o;


        ArrayList posicion  = (ArrayList) elemento.get(0);

        if(elemento.size()>1) {
            ArrayList posicionAnterior = (ArrayList) elemento.get(1);
            for(int i=0;i<posicionAnterior.size();i++){
                int segmento[]= new int[2];
                segmento = (int[]) posicionAnterior.get(i);

                this.textViewMatris[segmento[0]][segmento[1]].setText("");
                this.textViewMatris[segmento[0]][segmento[1]].setBackgroundColor(Color.rgb(67, 129, 91));
            }
        }


        for(int i=0;i<posicion.size();i++){
            int segmento[] = (int[]) posicion.get(i);
            if(i==0) {
                this.textViewMatris[segmento[0]][segmento[1]].setText(":");
                this.textViewMatris[segmento[0]][segmento[1]].setTextScaleX(20);
            }
            this.textViewMatris[segmento[0]][segmento[1]].setBackgroundColor(Color.RED);
        }

    }
}
