package com.example.logica;

import android.util.Log;

import com.example.controlador.ControlTouch;

import java.util.ArrayList;

public class Body {
//    fila, columna
    int [] segmento, dat;
    ArrayList posicion=new ArrayList();
    String direccion;

    public Body() {
        segmento = new int[2];
        this.segmento[0]=7;
        this.segmento[1]=5;
        posicion.add(this.segmento);
        segmento = new int[2];
        this.segmento[0]=7;
        this.segmento[1]=6;
        posicion.add(this.segmento);
        segmento = new int[2];
        this.segmento[0]=7;
        this.segmento[1]=7;
        posicion.add(this.segmento);
        this.direccion = ControlTouch.IZQUIERDA;
    }

    public ArrayList getPosicion() {

        return posicion;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public boolean derecha(){

        return  true;
    }
    public boolean izquierda() {
        int aux=0;
        dat=new int[2];
        dat= (int[]) this.posicion.get(0);

        if(this.direccion != ControlTouch.DERECHA) {
            if(dat[1]!=0) {
                if(this.direccion == ControlTouch.ABAJO) {
                    for(int i=0; i< this.posicion.size(); i++){
                        this.segmento = new int[2];
                        this.segmento = (int[]) this.posicion.get(i);
                        if(i==0) {
                            this.segmento[1]=this.segmento[1]--;
                            this.posicion.set(0, this.segmento);
                        } else {
                            this.segmento[0]=this.segmento[0]--;
                            this.posicion.set(i, this.segmento);
                        }
                    }
                }else if(this.direccion == ControlTouch.IZQUIERDA) {
                    for(int i=0; i< this.posicion.size(); i++){
                        this.segmento = new int[2];
                        this.segmento = (int[]) this.posicion.get(i);
                        if(i==0) {
                            aux=this.segmento[0];
                        }
                        if(this.segmento[0]!=aux) {
                            if(this.segmento[0]>aux) {
                                this.segmento[0]--;
                            }else {
                                this.segmento[0]++;
                            }
                        }else {
                            this.segmento[1]--;
                        }
                        this.posicion.set(i, this.segmento);
                    }
                }



                this.direccion = ControlTouch.IZQUIERDA;
                return  true;
            }
        }


        Log.d("direccion", this.direccion);
        return  false;
    }
    public boolean arriba() {
        return  true;
    }
    public boolean abajo() {
        return  true;
    }

}
