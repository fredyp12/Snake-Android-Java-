package com.example.logica;

import android.util.Log;

import com.example.controlador.ControlTouch;

import java.util.ArrayList;

public class Body {
//    fila, columna
    int [] segmento;
    ArrayList posicion=new ArrayList();
    ArrayList direccionArray= new ArrayList();
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
        for(int i=0; i<3; i++) {
            this.direccionArray.add(ControlTouch.IZQUIERDA);
        }
    }

    public ArrayList getDireccionArray() {
        return direccionArray;
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

// Mueve las posiciones del arreglo
    public boolean moverSnake(String direccion, ArrayList comida)  {
        ArrayList listaComida= (ArrayList) comida.clone();
        boolean comer=false;
        int [] notomar= new int[2];

        int [] segementoAnterior = new int[0];
        this.direccion=direccion;
        for(int i=0; i<this.direccionArray.size();i++) {
            this.segmento = new int[2];
            this.segmento = (int[]) this.posicion.get(i);
            if(i==0) {
//                comer
                if(this.segmento[0]==(int)listaComida.get(0) &&  this.segmento[1]==(int) listaComida.get(1)) {
                    comer=true;
                }

//                mover
                this.direccionArray.set(0,direccion);
                if(this.direccionArray.get(0)==ControlTouch.IZQUIERDA) {
                    if(this.segmento[1]==0) return false;
                    else this.segmento[1]--;
                }else if (this.direccionArray.get(0)==ControlTouch.DERECHA) {
                    this.segmento[1]++;
                }else if (this.direccionArray.get(0)==ControlTouch.ARRIBA) {
                    this.segmento[0]--;
                }else if (this.direccionArray.get(0)==ControlTouch.ABAJO) {
                    this.segmento[0]++;
                }
                segementoAnterior= this.segmento;
            }else {
                if(i==this.direccionArray.size()-1) {
                    notomar[0]=this.segmento[0];
                    notomar[1]=this.segmento[1];
                }
                if(this.direccionArray.get(i-1)!=this.direccionArray.get(i)) {
                    if(this.direccionArray.get(i-1)==ControlTouch.ARRIBA) {
                        if(this.segmento[0]-2==segementoAnterior[0]) {
                            this.segmento[0]--;
                            this.direccionArray.set(i,this.direccionArray.get(i-1));
                        }else seguir((String) this.direccionArray.get(i));

                    } else if(this.direccionArray.get(i-1)==ControlTouch.ABAJO) {
                        if(this.segmento[0]+2==segementoAnterior[0]) {
                            this.segmento[0]++;
                            this.direccionArray.set(i,this.direccionArray.get(i-1));
                        }else seguir((String) this.direccionArray.get(i));

                    }else if(this.direccionArray.get(i-1)==ControlTouch.DERECHA) {
                        if(this.segmento[1]+2==segementoAnterior[1]) {
                            this.segmento[1]++;
                            this.direccionArray.set(i,this.direccionArray.get(i-1));
                        }else seguir((String) this.direccionArray.get(i));
                    }else if(this.direccionArray.get(i-1)==ControlTouch.IZQUIERDA) {
                        if(this.segmento[1]-2==segementoAnterior[1]) {
                            this.segmento[1]--;
                            this.direccionArray.set(i,this.direccionArray.get(i-1));
                        }else seguir((String) this.direccionArray.get(i));
                    }


                }else {
                    seguir((String) this.direccionArray.get(i));
                }
                segementoAnterior=this.segmento;
            }
            this.posicion.set(i,this.segmento);

        }
        if(comer==true) {
            String tomarDireccion;

            int[] tomar= new int[2];

            tomarDireccion= (String) this.direccionArray.get(this.direccionArray.size()-1);
            tomar[0]=notomar[0];
            tomar[1]=notomar[1];
            this.posicion.add(tomar);
            this.direccionArray.add(tomarDireccion);


            comer= false;
        }

        return  true;
    }

    public void seguir(String direccion) {
       if(direccion==ControlTouch.ARRIBA) {
           this.segmento[0]--;
       }else if(direccion==ControlTouch.ABAJO) {
            this.segmento[0]++;
        }else  if(direccion==ControlTouch.DERECHA) {
            this.segmento[1]++;
        }else if (direccion==ControlTouch.IZQUIERDA) {
            this.segmento[1]--;
        }
    }

}
