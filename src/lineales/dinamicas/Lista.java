/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.dinamicas;

/**
 *
 * @author gonzalo
 */
public class Lista {

    private Nodo cabecera;
    private int longitud = 0;

    public Lista() {
        this.cabecera = null;
        //es mas eficiente longitud como atributo

    }

    public boolean insertar(Object elem, int pos) {
        boolean exito = true;
        Nodo aux, aux1;
        if (pos < 1 || pos > this.longitud + 1) {
            exito = false;
        } else {
            if (pos == 1) {
                this.cabecera = new Nodo(elem, this.cabecera);
                this.longitud++;
            } else {
                aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux1 = new Nodo(elem, aux.getEnlace());
                aux.setEnlace(aux1);
                this.longitud++;
            }
        }
        return exito;
    }

    public boolean eliminar(int pos) {
        boolean exito = true;
        if (this.cabecera == null || pos < 1 || pos > this.longitud) {
            exito = false;
        } else {
            if (pos == 1) {
                this.cabecera = this.cabecera.getEnlace();
                this.longitud--;
            } else {
                Nodo aux;
                aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
                this.longitud--;
            }
        }
        return exito;
    }

    public Object recuperar(int pos) {
        Object o;
        if (pos < 1 || pos > this.longitud) {
            o = null;
        } else {
            Nodo aux;
            aux = this.cabecera;
            int i = 1;
            while (i != pos) {
                aux = aux.getEnlace();
                i++;
            }
            o = aux.getElem();
        }
        return o;
    }

    public int localizar(Object o) {
        int pos = -1;
        Nodo aux;
        if (this.cabecera != null) {
            /*si no es vacia, aux apunta al pirmer nodo e inicializa a i como
             i=0 que tomara los valores de la posiciones de la lista*/
            aux = this.cabecera;
            int i = 1;
            while (aux != null && !aux.getElem().equals(o)) {
                aux = aux.getEnlace();
                i++;
            }//es mas legible
            if (aux != null && aux.getElem().equals(o))// el aux.getElem() no es necesario por el aux!=null
            {
                pos = i;
            }
        }
        return pos;
    }

    public int longitud() {
        return this.longitud;
    }

    public boolean esVacia() {
        boolean vacia = false;
        if (this.cabecera == null) {
            vacia = true;
        }
        return vacia;
    }

    public void vaciar() {
        this.cabecera = null;
        this.longitud = 0;
    }

    public Lista clone() {
        Lista l2 = new Lista();
        if (this.cabecera != null) {
            int i;
            Nodo aux, aux1, nvo;
            aux = this.cabecera;
            aux1 = new Nodo(aux.getElem(), null);
            l2.cabecera = aux1;
            aux = aux.getEnlace();
            for (i = 2; i <= this.longitud; i++) {
                nvo = new Nodo(aux.getElem(), null);
                aux1.setEnlace(nvo);
                aux1 = aux1.getEnlace();
                aux = aux.getEnlace();
            }
            l2.longitud = this.longitud;
        }
        return l2;
    }

    public String toString() {
        int i;
        String s = "";
        if (this.cabecera != null) {
            Nodo n = this.cabecera;
            for (i = 1; i <= this.longitud; i++) {
                s = s + n.getElem().toString() + "|";
                n = n.getEnlace();
            }
        }
        return s;
    }

    //metodos adicionales
    //se puede tener longitud como metodo y atributo?: si
    public Lista invertir() {
        Lista lInv = new Lista();
        if (this.cabecera != null) {
           invertirAux(this.cabecera,lInv);
        }
        return lInv;
    }

    private void invertirAux(Nodo n, Lista inv) {
        
    }

    public void eliminarApariciones(Object x) {

        while (this.cabecera!=null && this.cabecera.getElem().equals(x)) {
            this.cabecera=this.cabecera.getEnlace();
            this.longitud--;
        }
        Nodo aux,aux1;
        aux=this.cabecera;
        aux1=this.cabecera.getEnlace();
        while(aux1!=null){
            if(aux1.getElem().equals(x)){
                aux.setEnlace(aux1.getEnlace());
            }
        }
    }
}
