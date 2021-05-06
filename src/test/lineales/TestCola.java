/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.lineales;

import lineales.dinamicas.Cola;
import utiles.TecladoIn;

/**
 *
 * @author gonzalo
 */
public class TestCola {

    public static void main(String[] args) {
        Cola c = new Cola();
        codifOpc(c);
    }

    public static void menu() {
        System.out.println("Presione:"
                + "\n(0) Salir"
                + "\n(1) Apilar un elemento"
                + "\n(2) sacar un elemento"
                + "\n(3) Obtener frente"
                + "\n(4) Verificar si es vacia"
                + "\n(5) Vaciar cola"
                + "\n(6) Clonar"
                + "\n(7) mostrar los elementos almacenado");
    }

    public static void codifOpc(Cola c) {
        int opc;
        boolean seguir = true;
        Cola c1;
        while (seguir) {
            menu();
            opc = TecladoIn.readLineInt();
            switch (opc) {
                case 0:
                    seguir = false;
                    break;
                case 1:
                    agregarElem(c);
                    break;
                case 2:
                    quitarElem(c);
                    break;
                case 3:
                    observarFrente(c);
                    break;
                case 4:
                    verifColaVacia(c);
                    break;
                case 5:
                    vaciarCola(c);
                    break;
                case 6:
                    c1 = clonarCola(c);
                    mostrarElem(c1);
                    break;
                case 7:
                    mostrarElem(c);
                    break;
                default:
                    System.out.println("No es una opción");
                    break;
            }
        }
    }

    public static void agregarElem(Cola c) {
        int elem;
        System.out.println("Ingrese un elemento");
        elem = TecladoIn.readLineInt();
        if (!c.poner(elem)) {
            System.out.println("Cola llena");
        }
    }

    public static void quitarElem(Cola c) {
        if (!c.sacar()) {
            System.out.println("No hay elemnto que extraer");
        }
    }

    public static void observarFrente(Cola c) {
        if (c.esVacia()) {
            System.out.println("No hay elementos");
        } else {
            System.out.println(c.obtenerFrente());
        }
    }

    public static void verifColaVacia(Cola c) {
        if (c.esVacia()) {
            System.out.println("La cola esta vacia");
        } else {
            System.out.println("La cola no esta vacia");
        }
    }

    public static void vaciarCola(Cola c) {
        c.vaciar();
        System.out.println("La cola ha sido vaciada");
    }

    public static Cola clonarCola(Cola c) {
        Cola c1;
        c1 = c.clone();
        return c1; //return c.clone() tambien es valido
    }

    public static void mostrarElem(Cola c) {
        System.out.println(c.toString());
    }
}
