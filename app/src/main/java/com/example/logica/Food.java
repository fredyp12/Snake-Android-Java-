package com.example.logica;

import java.util.ArrayList;

public class Food {
    private ArrayList  segmento= new ArrayList();
    private int point;
    private int[] color = new int[3];

    public Food(ArrayList dat) {
        this.point=100;
        segmento.add(0);
        segmento.add(0);
        iniSeg(dat);
        color[0]=251;
        color[1]=183;
        color[2]=26;

    }

    public ArrayList getSegmento() {
        return segmento;
    }

    public int getPoint() {
        return point;
    }

    public int[] getColor() {
        return color;
    }

    public void iniSeg(ArrayList arreglo) {
        boolean dat;
        do{
            dat=true;
            this.segmento.set(0,(int) (Math.random()*Snake.FILAS));
            this.segmento.set(1,(int) (Math.random()*Snake.COLUMNAS));
            for(int i=0;i<arreglo.size();i++) {
                int[] posicion;
                posicion= (int[]) arreglo.get(i);
                if(posicion[0]==(int)segmento.get(0)) {
                    dat=false;
                }else if(posicion[1]==(int)segmento.get(1)) {
                    dat=false;
                }
            }
        }while(dat==false);
    }


}
