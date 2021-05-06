/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.Conjuntistas;
import conjuntistas.ArbolHeap;
import utiles.TecladoIn;
/**
 *
 * @author gonzalo
 */
public class TestHeap {
    public static void main(String [] args){
        ArbolHeap h=new ArbolHeap();
        opciones(h);
    }
    
    public static void menu(){
        System.out.println("Seleccionar:"
                + "\n(0) Salir"
                + "\n(1) Insertar"
                + "\n(4) ver");
    }
    
    public static void opciones(ArbolHeap h){
        int opc;
        boolean seguir=true;
        while(seguir){
            menu();
            opc=TecladoIn.readLineInt();
            switch(opc){
                case 0:
                    seguir=false;
                    break;
                case 1:
                    insertarElem(h);
                    break;
                case 4:
                    System.out.println(h.toString());
                    break;
                default:
                    System.out.println("No es una opcion");
                    break;
            }
        }
    }
    
    public static void insertarElem(ArbolHeap h){
        int e;
        System.out.println("elemento");
        e=TecladoIn.readLineInt();
        boolean resp=h.insertar(e);
        System.out.println(resp);
    }
    
}
