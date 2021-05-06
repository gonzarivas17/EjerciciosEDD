/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.jerarquicas;

import jerarquicas.ArbolGen;
import lineales.dinamicas.Lista;
import utiles.TecladoIn;

/**
 *
 * @author gonzalo
 */
public class TestArbolGen {

    public static void main(String[] args) {
        ArbolGen a = new ArbolGen();
        preCargarArbol(a);
        opciones(a);
    }

    public static void menu() {
        System.out.println("oprimir:"
                + "\n(0) salir"
                + "\n(1) insertar"
                + "\n(2) elemento pertenece al arbol"
                + "\n(3) verificar si esta vacio"
                + "\n(4) buscar padre de un elemento"
                + "\n(5) calcular altura del arbol"
                + "\n(6) calcular el nivel de un elemento"
                + "\n(7) ancestros"
                + "\n(8) clonar"
                + "\n(9)"
                + "\n(10) listar en pre-orden"
                + "\n(11) listar en in-orden"
                + "\n(12) listar en pos-orden"
                + "\n(13) "
                + "\n(14) toString");
    }

    public static void opciones(ArbolGen a) {
        int opc;
        boolean seguir = true;
        while (seguir) {
            menu();
            opc = TecladoIn.readLineInt();
            switch (opc) {
                case 0:
                    seguir = false;
                    break;
                case 1:
                    insertarElemento(a);
                    break;
                case 2:
                    verifPertenece(a);
                    break;
                case 3:
                    System.out.println(a.esVacio());
                    break;
                case 4:
                    buscarPadreDe(a);
                    break;
                case 5:
                    System.out.println(a.altura());
                    break;
                case 6:
                    nivelDeUnElemento(a);
                    break;
                case 7:
                    listarAncestros(a);
                    break;
                case 8:
                    clonarArbol(a);
                    break;
                case 10:
                    listaEnPreorden(a);
                    break;
                case 11:
                    listaEnInorden(a);
                    break;
                case 12:
                    listaEnPosorden(a);
                    break;
                case 14:
                    System.out.println(a.toString());
                    break;
                default:
                    System.out.println("No es una opcion");
                    break;
            }
        }
    }

    public static void insertarElemento(ArbolGen a) {
        char elem, elemPadre;
        System.out.println("Ingrese el elemento");
        elem = TecladoIn.readLineNonwhiteChar();
        System.out.println("Indique cual es el padre del elemento");
        elemPadre = TecladoIn.readLineNonwhiteChar();
        System.out.println(a.insertar(elem, elemPadre));
    }

    public static void verifPertenece(ArbolGen a) {
        char elem;
        System.out.println("Ingrese el elemento a buscar");
        elem = TecladoIn.readLineNonwhiteChar();
        System.out.println(a.pertenece(elem));
    }

    public static void buscarPadreDe(ArbolGen a) {
        char e, res;
        System.out.println("Elemento a buscar su padre");
        e = TecladoIn.readLineNonwhiteChar();
        if (a.padre(e) != null) {
            res = (char) a.padre(e);
            System.out.println(res);
        }
        else
            System.out.println("No se puedo encontrar el padre");
    }
    
    public static void nivelDeUnElemento(ArbolGen a){
        char e;
        System.out.println("indicar elemento a calcular nivel");
        e=TecladoIn.readLineNonwhiteChar();
        System.out.println(a.nivel(e));
    }
    
    public static void listarAncestros(ArbolGen a){
        char e;
        System.out.println("indicar elemento");
        e=TecladoIn.readLineNonwhiteChar();
        System.out.println(a.ancestros(e));
    }
    
    public static void clonarArbol(ArbolGen a){
        ArbolGen a1;
        a1=a.clonar();
        System.out.println(a.toString());
        System.out.println("");
        System.out.println("Arbol clonado:");
        System.out.println(a1.toString());
    }

    public static void listaEnPreorden(ArbolGen a) {
        Lista list;
        list = a.listarPreorden();
        System.out.println(list.toString());
    }

    public static void listaEnInorden(ArbolGen a) {
        Lista list;
        list = a.listarInorden();
        System.out.println(list.toString());
    }

    public static void listaEnPosorden(ArbolGen a) {
        Lista list;
        list = a.listarPosorden();
        System.out.println(list.toString());
    }

    public static void preCargarArbol(ArbolGen a) {
        a.insertar('a', 'a');
        a.insertar('b', 'a');
        a.insertar('c', 'a');
        a.insertar('d', 'b');
        a.insertar('e', 'b');
        a.insertar('f', 'b');
        a.insertar('g', 'c');
        a.insertar('h', 'c');
        a.insertar('i', 'd');
        a.insertar('j', 'g');
        a.insertar('k', 'g');
    }
}
