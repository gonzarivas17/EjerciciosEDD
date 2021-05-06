/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.Conjuntistas;

import conjuntistas.ArbolAVL;
import utiles.TecladoIn;

/**
 *
 * @author gonzalo
 */
public class TestAVL {

    public static void main(String[] args) {
        ArbolAVL a = new ArbolAVL();
        preCargarArbol(a);
        opciones(a);
    }

    public static void menu() {
        System.out.println("--------------------------------------------------");
        System.out.println("Oprimir:"
                + "\n(0) Salir"
                + "\n(1) insertar"
                + "\n(2) eliminar"
                + "\n(3) listar"
                + "\n(4) listar por rango"
                + "\n(5) mostrar minimo elemento"
                + "\n(6) mostrar maximo elemento"
                + "\n(7) verificar si esta vacio");
        System.out.println("--------------------------------------------------");
    }

    public static void opciones(ArbolAVL a) {
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
                    eliminarElemento(a);
                    break;
                case 3:
                    System.out.println(a.listar());
                    break;
                case 4:
                    listaPorRango(a);
                    break;
                case 5:
                    mostrarMinimo(a);
                    break;
                case 6:
                    mostrarMaximo(a);
                    break;
                case 7:
                    System.out.println(a.vacio());
                    break;
                default:
                    System.out.println("No es una opcion");
                    break;
            }
        }
    }

    public static void insertarElemento(ArbolAVL a) {
        int elemento;
        System.out.println("Ingresar el elmeneto");
        elemento = TecladoIn.readLineInt();
        System.out.println(a.insertar(elemento));
        System.out.println(a.toString());
    }

    public static void eliminarElemento(ArbolAVL a) {
        int elemento;
        System.out.println("Indique el elemento a eliminar");
        elemento = TecladoIn.readLineInt();
        System.out.println(a.eliminar(elemento));
        System.out.println(a.toString());
    }

    public static void preCargarArbol(ArbolAVL a) {
        a.insertar(50);
        a.insertar(20);
        a.insertar(60);
        a.insertar(15);
        a.insertar(30);
        a.insertar(55);
        a.insertar(70);
        a.insertar(10);
        a.insertar(16);
        a.insertar(22);
        a.insertar(31);
        a.insertar(51);
        a.insertar(58);
        a.insertar(62);
        a.insertar(80);
        System.out.println(a.toString());
    }

    public static void listaPorRango(ArbolAVL a) {
        int minimo, maximo;
        System.out.println("Indicar minimo");
        minimo = TecladoIn.readLineInt();
        System.out.println("Indicar Maximo");
        maximo = TecladoIn.readLineInt();
        System.out.println(a.listarRango(minimo, maximo));
    }

    public static void mostrarMinimo(ArbolAVL a) {
        if (!a.vacio()) {
            System.out.println(a.minimoElem());
        } else {
            System.out.println("El arbol esta vacio");
        }
    }

    public static void mostrarMaximo(ArbolAVL a) {
        if (!a.vacio()) {
            System.out.println(a.maximoElem());
        } else {
            System.out.println("El arbol esta vacio");
        }
    }

    public static void probarRotaciones(ArbolAVL a) {
        int opc;
        System.out.println("seleccionar rotacion"
                + "\n(1) simple a derecha [50-45-60-40-35]"
                + "\n(2) simple a izquierda [50-45-60-70-80]"
                + "\n(3) doble derecha izquierda [50-45-60-65-70]"
                + "\n(4) doble izquierda derecha [50-45-60-40-43]");
        opc = TecladoIn.readLineInt();
        switch (opc) {
            case 1:
                simpleDerecha(a);
                break;
            case 2:
                simpleIzquierda(a);
                break;
            case 3:
                dobleDerIzq(a);
                break;
            case 4:
                dobleIzqDer(a);
                break;
        }
    }

    public static void simpleDerecha(ArbolAVL a) {
        a.insertar(50);
        a.insertar(45);
        a.insertar(60);
        a.insertar(40);
        a.insertar(35);
    }

    public static void simpleIzquierda(ArbolAVL a) {
        a.insertar(50);
        a.insertar(45);
        a.insertar(60);
        a.insertar(70);
        a.insertar(80);
    }

    public static void dobleDerIzq(ArbolAVL a) {
        a.insertar(50);
        a.insertar(45);
        a.insertar(60);
        a.insertar(65);
        a.insertar(63);
    }

    public static void dobleIzqDer(ArbolAVL a) {
        a.insertar(50);
        a.insertar(45);
        a.insertar(60);
        a.insertar(40);
        a.insertar(43);

    }
}
