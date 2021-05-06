/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.lineales;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;
import lineales.dinamicas.Pila;
import utiles.TecladoIn;

/**
 *
 * @author gonzalo
 */
public class PruebaLista {

    public static Lista concatenar(Lista l1, Lista l2) {
        int longL1, longL2, i, j, pos;
        longL1 = l1.longitud();
        longL2 = l2.longitud();
        Lista l3 = new Lista();
        Object elem;
        for (i = 1; i <= longL1; i++) {
            elem = l1.recuperar(i);
            l3.insertar(elem, i);
        }
        pos = i;
        for (j = 1; j <= longL2; j++) {
            elem = l2.recuperar(j);
            l3.insertar(elem, pos);
            pos++;
        }
        return l3;
    }

    public static void comprobar(Lista l) {
        if (!l.esVacia()) {
            Cola c1,c2;
            c1=new Cola();
            c2=new Cola();
            Pila p = new Pila();
            Object elem;
            int i=1;
            elem=l.recuperar(i);
            while(!elem.equals(0)){
                c1.poner(elem);
                i++;
                elem=l.recuperar(i);
            }
            i++;
            elem=l.recuperar(i);
            while(!elem.equals(0)){
                c2.poner(elem);
                i++;
                elem=l.recuperar(i);
            }
            i++;
            Cola c3=c1.clone();
            if(verifCadIguales(c1,c2)){
                while(i<=l.longitud()){
                    elem=l.recuperar(i);
                    p.apilar(elem);
                    i++;
                }
                if(verifCadInver(c3,p))
                    System.out.println("La lista cumple con el formato");
                else
                    System.out.println("No cumple");
            }
        }
    }
    
    public static boolean verifCadIguales(Cola c, Cola c1){
        boolean cadIguales;
        cadIguales=c.obtenerFrente().equals(c1.obtenerFrente());
        while(c.sacar() && c1.sacar() && cadIguales){
            if(!c.esVacia() && !c1.esVacia() && !(c.obtenerFrente().equals(c1.obtenerFrente())))// corta si los elementos son distintos
                cadIguales=false;
        }
        return cadIguales;
    }
    
    public static boolean verifCadInver(Cola c,Pila p){
        boolean cadInv;
        cadInv=c.obtenerFrente().equals(p.obtenerTope());
        while(c.sacar() && p.desapilar() && cadInv){
            if(!c.esVacia() && !p.esVacia() &&!(c.obtenerFrente().equals(p.obtenerTope()))){
                cadInv=false;
            }
        }
        return cadInv;
    }

    public static Lista invertir(Lista l) {
        Lista lInv = new Lista();
        int longList, i, j;
        Object elem;
        if (!l.esVacia()) {
            longList = l.longitud();
            j = 1;
            for (i = longList; i >= 1; i--) {
                elem = l.recuperar(i);
                lInv.insertar(elem, j);
                j++;
            }
        }
        return lInv;
    }

    public static void main(String[] args) {
        Lista l1;
        Lista l2;
        l1 = new Lista();
        l2 = new Lista();
        cargarListaPred(l1);
        cargarLista(l2);
        opciones(l1, l2);
    }

    public static void menu() {
        System.out.println("Seleccionar:"
                + "\n(0)Salir"
                + "\n(1)concatenar"
                + "\n(2)Comprobar"
                + "\n(3)Invertir");
    }

    public static void opciones(Lista l1, Lista l2) {
        int opcion;
        boolean seguir = true;
        Lista listaResult;
        while (seguir) {
            menu();
            opcion = TecladoIn.readLineInt();
            switch (opcion) {
                case 0:
                    seguir = false;
                    break;
                case 1:
                    listaResult = concatenar(l1, l2);
                    System.out.println(listaResult.toString());
                    break;
                case 2:
                    comprobar(l1);
                    break;
                case 3:
                    listaResult = invertir(l1);
                    System.out.println(listaResult);
                    break;
                default:
                    System.out.println("no es una opcion");
                    break;
            }
        }
    }

    public static void cargarLista(Lista l) {
        int elem, pos;
        pos = 1;
        System.out.println("Ingrese un elemento");
        elem = TecladoIn.readLineInt();
        while (elem >= 0) {
            l.insertar(elem, pos);
            pos++;
            System.out.println("Ingrese un elemento");
            elem = TecladoIn.readLineInt();
        }
        System.out.println(l.toString());
    }
    
    public static void cargarListaPred(Lista l){
        int i;
        int[] nums={9,6,5,0,9,6,5,0,5,6,9};
        for(i=1;i<=11;i++){
            l.insertar(nums[i-1], i);
        }
        System.out.println(l.toString());
    }
}
