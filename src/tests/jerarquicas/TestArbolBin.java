/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.jerarquicas;

import jerarquicas.ArbolBin;
import utiles.TecladoIn;
import lineales.dinamicas.Lista; //borrar
/**
 *
 * @author gonzalo
 */
public class TestArbolBin {

    public static void main(String[] args) {
        ArbolBin a = new ArbolBin();
        opciones(a);
    }

    public static void menu() {
        System.out.println("Oprima:"
                + "\n(0) salir"
                + "\n(1) insertar"
                + "\n(2) verificar si esta vacio"
                + "\n(3) buscar padre de un elemento"
                + "\n(4) altura del arbol"
                + "\n(5) calcular nivel de un elemento"
                + "\n(6) vaciar arbol"
                + "\n(7) clonar arbol"
                + "\n(8) toString"
                + "\n(9) listar en pre-orden"
                + "\n(10) listar en pos-orden"
                + "\n(11) listar en in-orden"
                + "\n(12) listar por niveles"
                + "\n(13) verificar patron");
    }

    public static void opciones(ArbolBin a) {
        boolean seguir = true;
        int opc;
        while (seguir) {
            menu();
            opc = TecladoIn.readLineInt();
            switch (opc) {
                case 0:
                    seguir = false;
                    break;
                case 1:
                    insertarElem(a);
                    break;
                case 2:
                    System.out.println(a.esVacio());
                    break;
                case 3:
                    buscarPadre(a);
                    break;
                case 4:
                    System.out.println(a.altura());
                    break;
                case 5:
                    nivelDeElemento(a);
                    break;
                case 6:
                    a.vaciar();
                    System.out.println("El arbol ha sido vaciado");
                    break;
                case 7:
                    clonarArbol(a);
                    break;
                case 8:
                    System.out.println(a.toString());
                    break;
                case 9:
                    System.out.println(a.listarPreorden().toString());
                    break;
                case 10:
                    System.out.println(a.listarPosorden().toString());
                    break;
                case 11:
                    System.out.println(a.listarInorden().toString());
                    break;
                case 13:
                    seVerificaPatron(a);
                    break;
                default:
                    System.out.println("No es una opcion");
                    break;
            }
        }
    }

    public static void insertarElem(ArbolBin a) {
        char c, p, lugar;
        System.out.println("Ingrese un elemento");
        c = TecladoIn.readLineNonwhiteChar();
        System.out.println("Ingrese el padre");
        p = TecladoIn.readLineNonwhiteChar();
        System.out.println("Indique si el elemento será hijo izquierdo ó hijo "
                + "derecho");
        lugar = TecladoIn.readLineNonwhiteChar();
        System.out.println(a.insertar(c, p, lugar));
    }

    public static void buscarPadre(ArbolBin a) {
        char buscado;
        char resultado = ' ';
        System.out.println("Indique el elemento del cual se buscara el padre");
        buscado = TecladoIn.readLineNonwhiteChar();
        if (a.padre(buscado) != null) {
            resultado = (char) a.padre(buscado);
        }
        System.out.println(resultado);
    }

    public static void clonarArbol(ArbolBin original) {
        ArbolBin aCopia = original.clonar();
        System.out.println(aCopia.toString());

    }

    public static void comprobarArbolVacio(ArbolBin a) {
        if (a.esVacio()) {
            System.out.println("El arbol esta vacio");
        } else {
            System.out.println("El arbol no esta vacio");
        }
    }
    
    public static void nivelDeElemento(ArbolBin a){
        char elto;
        System.out.println("Indique el elemento al cual calcular su nivel");
        elto=TecladoIn.readLineNonwhiteChar();
        System.out.println(a.nivel(elto));
    }
    
    public static void seVerificaPatron(ArbolBin a){   
        a.insertar('a', 'a', 'd');
        System.out.println(a.toString());
        Lista l=new Lista();
        l.insertar('a', 1);
        l.insertar('b', 2);
        l.insertar('c', 3);

        System.out.println(l.toString());
        boolean es=a.verificarPatron(l);
        System.out.println(es);
        System.out.println("");
    }
}
