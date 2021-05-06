/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafoDinamico;

/**
 *
 * @author gonzalo
 */
public class Grafo {

    private NodoVert inicio;

    public Grafo() {
        this.inicio = null;
    }

    public boolean insertarVertice(Object elem) {
        boolean exito = false;
        NodoVert aux = ubicarVertice(elem);
        if (aux == null) {
            this.inicio = new NodoVert(elem, this.inicio);
            exito = true;
        }

        return exito;
    }

    private NodoVert ubicarVertice(Object buscado) {
        NodoVert aux = this.inicio;
        while (aux != null && !aux.getElem().equals(buscado)) {
            aux = aux.getSigVertice();
        }
        return aux;
    }

    public boolean eliminarVertice(Object elem) {
        boolean exito = false;
        NodoVert temp = null;
        if (this.inicio != null) {
            // quita al vertice de la lista de vertices 
            //primero se verifica si el vertice a eliminar es el inicio
            if (this.inicio.getElem().equals(elem)) {
                temp = this.inicio;
                this.inicio = this.inicio.getSigVertice();
                exito = true;
            } else {
                //si no es inicio, se procede a buscar en la lista de vertice
                NodoVert ant = this.inicio;
                NodoVert aux = this.inicio.getSigVertice();
                while (aux != null && !aux.getElem().equals(elem)) {
                    ant = aux;
                    aux = aux.getSigVertice();
                }
                //si aux es distinto de null, entoces existe el vertice que se
                //pretende eliminar
                if (aux != null) {
                    temp = aux;
                    ant.setSigVertice(aux.getSigVertice());
                    exito = true;
                }
            }
            // recorre los adyacentes del eliminado para quitar los arcos 
            // que vuelven a el
            //temp tiene al vertice que se va a eliminar
            if (temp != null) {
                //auxAdy tiene los nodos adyasentes a temp
                /*en el caso de un grafo no dirigido, auxAdy tambien representa
                los vertices que apuntan a temp*/
                NodoAdy auxAdy = temp.getPrimerAdy();
                while (auxAdy != null) {
                    //auxAdy.getVertice() [origen] - temp [destino]
                    eliminarArcoAux(auxAdy.getVertice(), temp);
                    auxAdy = auxAdy.getSigAdyacente();
                }
            }

        }

        return exito;
    }

    public boolean existeVertice(Object elem) {
        boolean existe = false;
        NodoVert aux = this.inicio;
        while (aux != null && !aux.getElem().equals(elem)) {
            aux = aux.getSigVertice();
        }
        if (aux != null) {
            existe = true;
        }
        return existe;
    }

    public boolean insertarArco(Object elem1, Object elem2) {
        boolean exito = false;
        NodoVert n1, n2;
        n1 = ubicarVertice(elem1);
        //n1 es el origen que debe tener el nuevo arco
        if (n1 != null) {
            n2 = ubicarVertice(elem2);
            //n2 es el destino que debe tener el nuevo arco
            if (n2 != null) {
                //verifico que no exista otro camino ente n1 y n2      //es probable que no sea necesario
                if (!existeArco(elem1, elem2)) {
                    NodoAdy nuevo = new NodoAdy(n2);
                    nuevo.setSigAdyacente(n1.getPrimerAdy());
                    n1.setPrimerAdy(nuevo);
                    exito = true;
                }
            }
        }
        return exito;
    }

    public boolean eliminarArco(Object elem1, Object elem2) {
        boolean exito = false;
        NodoVert n1, n2;
        n1 = ubicarVertice(elem1);
        if (n1 != null) {
            n2 = ubicarVertice(elem2);
            if (n2 != null) {
                exito = eliminarArcoAux(n1, n2);
                /*para un grafo no dirigido, es necesario "eliminar el arco en
                ambas direcciones"*/
                if (exito) {
                    exito = eliminarArco(n2, n1);
                }
            }
        }
        return exito;
    }

    private boolean eliminarArcoAux(NodoVert origen, NodoVert destino) {
        boolean exito = false;
        NodoAdy adyAux = origen.getPrimerAdy();
        /*como en la insercion de arco se verifica que no exista otro arco entre
        los vertices (arcos paralelos) es seguro que si existe un arco entre
        origen y destino, es unico (para esta implementacion)*/
        if (adyAux.getVertice() == destino) {
            origen.setPrimerAdy(adyAux.getSigAdyacente());
            exito = true;
        } else {
            NodoAdy adyAux1 = adyAux.getSigAdyacente();
            while (adyAux1 != null && adyAux1.getVertice() != destino) {
                adyAux = adyAux1;
                adyAux1.getSigAdyacente();
            }
            if (adyAux1 != null) {
                adyAux.setSigAdyacente(adyAux1.getSigAdyacente());
                exito = true;
            }
        }
        return exito;
    }

    public boolean existeArco(Object elem1, Object elem2) {
        boolean existe = false;
        NodoVert n1, n2;
        n1 = ubicarVertice(elem1);
        if (n1 != null) {
            n2 = ubicarVertice(elem2);
            if (n2 != null) {
                existe = existeArcoAux(n1, n2);
            }
        }
        return existe;
    }

    private boolean existeArcoAux(NodoVert n1, NodoVert n2) {
        boolean existe = false;
        NodoAdy aux = n1.getPrimerAdy();
        while (aux != null && aux.getVertice() != n2) {
            aux = aux.getSigAdyacente();
        }
        if (aux != null) {
            existe = true;
        }
        return existe;
    }

    public boolean vacio() {
        return this.inicio == null;
    }
    
    //otros
    public String toString(){
        String salida="";
        if(this.inicio!=null){
            salida=concatenar(salida,this.inicio);
        }
        return salida;
    }
    
    private String concatenar(String s, NodoVert n){
        NodoVert aux=n;
        NodoAdy auxAdy=n.getPrimerAdy();
        while(aux!=null){
            s=s+aux.getElem()+" adyasentes: ";
            while(auxAdy!=null){
                s=s+auxAdy.getVertice().getElem()+"|";
                auxAdy=auxAdy.getSigAdyacente();
            }
            s=s+"\n";
            aux=aux.getSigVertice();
        }
        return s;
    }
}
