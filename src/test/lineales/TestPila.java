/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.lineales;

import utiles.TecladoIn;
import lineales.dinamicas.Pila;

/**
 *
 * @author gonzalo
 */
public class TestPila {

    public static void main(String[] args) {
        /*Realiza un test sobre el TDA Pila con numeros enteros*/
        Pila p = new Pila();
        codifOpc(p);
    }

    public static void menu() {
        System.out.println("Oprima: "
                + "\n (0) Salir"
                + "\n (1) Agregar un elemento"
                + "\n (2) Quitar un elemento"
                + "\n (3) Obtener tope"
                + "\n (4) verificar si la pila esta vacia"
                + "\n (5) vaciar pila"
                + "\n (6) clonar pila"
                + "\n (7) mostrar los objetos almacenados en la pila"
                + "\n (8) verificar si los digitos forman un numero capicua");
    }

    public static void codifOpc(Pila p) {
        /*Realiza los llamados a los metos necesarios segun la operacion
         seleccionada por el usuario*/
        int opc, resp;
        Pila p1;
        boolean seguir = true;
        System.out.println("Indique que contendra la pila "
                + "\n [1]Enteros/[2]Texto");
        resp = TecladoIn.readLineInt();
        while (seguir) {
            menu();
            opc = TecladoIn.readLineInt();
            switch (opc) {
                case 0:
                    seguir = false;
                    break;
                case 1:
                    agregar(p, resp);
                    break;
                case 2:
                    quitarElem(p);
                    break;
                case 3:
                    mostrarTope(p);
                    break;
                case 4:
                    verifPilaVacia(p);
                    break;
                case 5:
                    vaciarPilaP(p);
                    break;
                case 6:
                    p1 = p.clone();
                    mostrarObjetos(p1);
                    break;
                case 7:
                    mostrarObjetos(p);
                    break;
                case 8:
                    //Los numeros deben tener cierta cantidad de digitos
                    if (resp == 1) {
                        if (p.esVacia()) {
                            System.out.println("La pila no contiene ningun digito");
                        } else {
                            if (verifCapicua(p)) {
                                System.out.println("El numero es capicua");
                            } else {
                                System.out.println("No es capicua");
                            }
                        }
                    }
                    break;
                /*p1=invertir(p);
                 if(p1.esVacia)
                 System.out.println("No se pudo clonar");
                 else
                 System.out.println(p1.toString());*/
                default:
                    System.out.println("No es una opcion");
                    break;
            }
        }

    }

    public static void agregarElemInt(Pila p) {
        int e;
        System.out.println("Ingresar elemento");
        e = TecladoIn.readLineInt();
        if (!p.apilar(e)) {
            System.out.println("La pila esta llena");
        }
    }

    public static void agregarElemString(Pila p) {
        String s;
        System.out.println("Ingrese el texto");
        s = TecladoIn.readLine();
        if (!p.apilar(s)) {
            System.out.println("La pila esta llena");
        }
    }

    public static void agregar(Pila p, int resp) {
        switch (resp) {
            case 1:
                agregarElemInt(p);
                break;
            case 2:
                agregarElemString(p);
                break;
            default:
                System.out.println("No es una opcion");
        }
    }

    public static void quitarElem(Pila p) {
        if (!p.desapilar()) {
            System.out.println("No fue posible desapilar, la pila esta vacia");
        }
    }

    public static void mostrarTope(Pila p) {
        if (p.obtenerTope() == null) {
            System.out.println("La pila esta vacia");
        } else {
            System.out.println(p.obtenerTope());
        }
    }

    public static void verifPilaVacia(Pila p) {
        if (p.esVacia()) {
            System.out.println("La pila esta vacia");
        } else {
            System.out.println("La pila no esta vacia");
        }
    }

    public static void vaciarPilaP(Pila p) {
        p.vaciar();
        System.out.println("La pila ha sido vaciada");
    }

    public static Pila clonarPila(Pila p) {
        Pila pilaClon;
        pilaClon = p.clone();
        return pilaClon;
    }

    public static void mostrarObjetos(Pila p) {
        System.out.println(p.toString());
    }

    public static boolean verifCapicua(Pila p) {
        /*Crea dos pilas, p1 que es la copia de p y p2 que esta ordenada a la
         inversa de p
         nota: se borran todas las referencias de p*/
        boolean esCapicua, desap1, desap2;
        esCapicua = true;
        Pila p1, p2;
        p1 = p.clone();
        p2 = apilarInversa(p);
        do {
            if (p1.obtenerTope() != p2.obtenerTope()) {
                esCapicua = false;
            }
            desap1 = p1.desapilar();/*por cuestion de prolijidad almacenar el boolean*/
            desap2 = p2.desapilar();
        } while (esCapicua && desap2);

        return esCapicua;
    }

    public static Pila apilarInversa(Pila p) {
        //mejorar while
        Pila clon = new Pila();
        Object e;
        e = p.obtenerTope();
        //apila un null
        while (e != null && clon.apilar(e) && p.desapilar()) {
            e = p.obtenerTope();
        }
        return clon;
    }
}
