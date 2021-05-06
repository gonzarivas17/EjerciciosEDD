/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.lineales;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Pila;
import utiles.TecladoIn;

/**
 *
 * @author gonzalo
 */
public class MixLineales {

    public static Cola generarOtraCola(Cola c1) {
        Cola c2, clonC1;
        /*c2 es la nueva cola que tendra el formato requerido
         clonC1 es la copia de c1*/
        c2 = new Cola();
        if(!c1.esVacia()){
        Object o;//almacena el elemento para luego observarlo
        Pila p = new Pila();//almacena temporalmente los objetos en orden inverso
        clonC1 = c1.clone();
        o = clonC1.obtenerFrente();
        p.apilar(o);
        c2.poner(o);
        while (clonC1.sacar() && !clonC1.esVacia()) {
            o = clonC1.obtenerFrente();
            if (!o.equals('$')) {
                p.apilar(o);
                c2.poner(o);
            } else {
                apilarInv(c2, p);
                c2.poner('$');
            }
        }
        if (clonC1.esVacia() && o != (Object) '$') {
            apilarInv(c2, p);
        }}
        return c2;
    }

    public static Cola apilarInv(Cola c, Pila p) {
        Object o;
        o = p.obtenerTope();
        while (p.desapilar()) {
            c.poner(o);
            o = p.obtenerTope();
        }
        return c;
    }

    public static void main(String[] args) {
        Cola c, otra;
        c = new Cola();
        int k;
        System.out.println("cuantos elementos?");
        k = TecladoIn.readLineInt();
        almacenarElem(c, k);
        otra = generar(c);
        System.out.println(c.toString());
        System.out.println("");
        System.out.println(otra.toString());
    }

    public static void almacenarElem(Cola c, int cant) {
        char elem;
        int i;
        for (i = 0; i < cant; i++) {
            System.out.println("Ingresar elemento");
            elem = TecladoIn.readLineNonwhiteChar();
            c.poner(elem);
        }
    }
    
    public static Cola generar(Cola c1){
        Cola clon,retorno,c3;
        Pila p=new Pila();
        clon=c1.clone();
        retorno=new Cola();
        c3=new Cola();
        char ch=(char)clon.obtenerFrente();
        while(!clon.esVacia()){
            ch=(char)clon.obtenerFrente();
            if(ch!='#'){
                retorno.poner(ch);
                p.apilar(ch);
                c3.poner(ch);
                clon.sacar();
            }
            else{
                generarAux(retorno,c3,p);
                retorno.poner('#');
                clon.sacar();
            }
        }
        if(clon.esVacia() && ch!='#'){
            generarAux(retorno,c3,p);
        }
        return retorno;
    }
    
    public static void generarAux(Cola c2,Cola c3,Pila p){
        while(!p.esVacia()){
            c2.poner(p.obtenerTope());
            p.desapilar();
        }
        
        while(!c3.esVacia()){
            c2.poner(c3.obtenerFrente());
            c3.sacar();
        }
    }
}
