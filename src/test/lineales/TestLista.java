/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.lineales;

import lineales.dinamicas.Lista;
import utiles.TecladoIn;

/**
 *
 * @author gonzalo
 */
public class TestLista {

    public static void main(String[] args) {
        Lista l = new Lista();
        codifOpc(l);
    }

    public static void menu() {
        System.out.println("");
        System.out.println("Oprima:"
                + "\n(0) Salir"
                + "\n(1) Insertar un elemento"
                + "\n(2) Eliminar un elemento"
                + "\n(3) Recuperar elemento en una posicion determinada"
                + "\n(4) Localizar un elemento"
                + "\n(5) ver la cantidad de elementos"
                + "\n(6) Verificar si la lista esta vacia"
                + "\n(7) Vaciar la lista"
                + "\n(8) Clonar la lista"
                + "\n(9) Mostar cada uno de los elementos de la lista"
                + "\n(10) invertir lista"
                + "\n(11) eliminar apariciones de un elemento");
        System.out.println("");
    }

    public static void codifOpc(Lista list) {
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
                    insertarElem(list);
                    break;
                case 2:
                    eliminarElem(list);
                    break;
                case 3:
                    recuperarElem(list);
                    break;
                case 4:
                    localizarElem(list);
                    break;
                case 5:
                    System.out.println(list.longitud());
                    break;
                case 6:
                    verifListaVacia(list);
                    break;
                case 7:
                    list.vaciar();
                    System.out.println("La lista ha sido vaciada");
                    break;
                case 8:
                    clonarLista(list);
                    break;
                case 9:
                    System.out.println(list.toString());
                    break;
                case 11:
                    eliminarNodos(list);
                    break;
                default:
                    System.out.println("No es una opcion");
                    break;
            }
        }

    }

    public static void insertarElem(Lista list) {
        int e, p;
        boolean exito;
        System.out.println("Ingrese un elemento");
        e = TecladoIn.readLineInt();
        System.out.println("Indique la posicion en la que se insertara");
        p = TecladoIn.readLineInt();
        exito = list.insertar(e, p);
        if (exito == false) {
            System.out.println("posicion no valida");
        }
    }

    public static void eliminarElem(Lista list) {
        int p;
        boolean exito;
        System.out.println("Indicar la posicion");
        p = TecladoIn.readLineInt();
        exito = list.eliminar(p);
        if (exito == false) {
            System.out.println("No fue posible eliminar");
        }
    }

    public static void recuperarElem(Lista l) {
        int pos;
        System.out.println("Determine la posicion a buscar");
        pos = TecladoIn.readLineInt();
        if (l.recuperar(pos) == null) {
            System.out.println("no fue posible encontrarlo");
        } else {
            System.out.println(l.recuperar(pos).toString());
        }
    }

    public static void localizarElem(Lista l) {
        int elem, posi;
        System.out.println("Indicar elemento");
        elem = TecladoIn.readLineInt();
        posi = l.localizar(elem);
        if (posi == -1) {
            System.out.println("No se encontro el elemento");
        } else {
            System.out.println(posi);
        }
    }

    public static void verifListaVacia(Lista l) {
        if (l.esVacia()) {
            System.out.println("La lista esta vacia");
        } else {
            System.out.println("No esta vacia");
        }
    }

    public static void clonarLista(Lista l) {
        Lista nvaList;
        nvaList = l.clone();
        System.out.println(l.toString());
        System.out.println("");
        System.out.println(nvaList.toString());
    }
    
    public static void eliminarNodos(Lista l){
        int busc;
        System.out.println("Indique que elemento eliminar");
        busc=TecladoIn.readLineInt();
        l.eliminarApariciones(busc);
    }
}
