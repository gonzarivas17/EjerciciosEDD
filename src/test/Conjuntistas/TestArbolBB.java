/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.Conjuntistas;

import conjuntistas.ArbolBB;
import utiles.TecladoIn;
import lineales.dinamicas.Lista;

/**
 *
 * @author gonzalo
 */
public class TestArbolBB {

    public static void main(String[] args) {
        ArbolBB a = new ArbolBB();
        preCargarArbol(a);
        opciones(a);
    }

    public static void menu() {
        System.out.println("Oprimir:"
                + "\n(0) salir"
                + "\n(1) pertenece"
                + "\n(2) insertar"
                + "\n(3) eliminar"
                + "\n(4) listar"
                + "\n(5) listar en un rango"
                + "\n(6) elemento minimo"
                + "\n(7) elemento maximo"
                + "\n(8) vacio");
        System.out.println("");
    }

    public static void opciones(ArbolBB a) {
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
                    verifElemPertenece(a);
                    break;
                case 2:
                    insertarElemento(a);
                    break;
                case 3:
                    eliminarElemento(a);
                    break;
                case 4:
                    listarArbol(a);
                    break;
                case 5:
                    listarPorRango(a);
                    break;
                case 6:
                    System.out.println(a.minimoElem());
                    break;
                case 7:
                    System.out.println(a.maximoElem());
                    break;
                default:
                    System.out.println("No es una opcion");
                    break;
            }
        }
    }

    public static void verifElemPertenece(ArbolBB a) {
        int buscado;
        System.out.println("Indicar elemento a buscar");
        buscado = TecladoIn.readLineInt();
        System.out.println(a.pertenece(buscado));
    }

    public static void insertarElemento(ArbolBB a) {
        int elem;
        boolean exito;
        System.out.println("Ingresar el nuevo elemento");
        elem = TecladoIn.readLineInt();
        exito = a.insertar(elem);
        System.out.println(exito);
    }

    public static void eliminarElemento(ArbolBB a) {
        int elem;
        boolean exito;
        System.out.println("Indicar el elemento a eliminar");
        elem = TecladoIn.readLineInt();
        exito = a.eliminar(elem);
        System.out.println(exito);
    }

    public static void listarArbol(ArbolBB a) {
        Lista l;
        l = a.listar();
        System.out.println(l.toString());
    }

    public static void listarPorRango(ArbolBB a) {
        Lista l;
        int inf, sup;
        System.out.println("Indicar el limite inferior");
        inf = TecladoIn.readLineInt();
        System.out.println("indicar el limite superior");
        sup = TecladoIn.readLineInt();
        l = a.listarRango(inf, sup);
        System.out.println(l.toString());
    }

    public static void preCargarArbol(ArbolBB a) {
        a.insertar(45);
        a.insertar(34);
        a.insertar(65);
        a.insertar(13);
        a.insertar(55);
        a.insertar(73);
        a.insertar(96);
    }
}
