/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas;

/**
 *
 * @author gonzalo
 */
public class ArbolHeap {

    private int TAM = 15;
    private Comparable[] heap;
    private int ultimo = 0;

    public ArbolHeap() {
        this.heap = new Comparable[TAM];
    }

    public boolean insertar(Comparable elem) {
        boolean exito = true;
        if (ultimo <= this.heap.length-1) {
            this.heap[ultimo + 1] = elem;
            ultimo++;
            int k,i;
            k=ultimo/2;
            i=ultimo;
            while(k>0 && this.heap[k].compareTo(this.heap[i])>0){
                Comparable aux;
                aux=this.heap[k];
                this.heap[k]=this.heap[i];
                this.heap[i]=aux;
                i=k;
                k=k/2;
            }
        } else {
            exito = false;
        }
        return exito;
    }
    
    public String toString(){
        String s="";
        int i=1;
        while(i<=ultimo){
            s=s+this.heap[i].toString()+"|";
            i++;
        }
        return s;
    }

}
