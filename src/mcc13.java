/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gonzalo
 */
public class mcc13 {
    public static void main(String[] args){
        double x=2;
        double x1=x-(funcion(x)/derivada(x));;
        while(Math.abs(funcion(x1))>0.01){
            x1=x-(funcion(x)/derivada(x));
            x=x1;
        }
        System.out.println("la raiz esta en r="+x1);
        System.out.println(funcion(x1));
    }
    
    public static double funcion(double t){
        double res;
        res=(80*Math.exp(-2*t))+(20*Math.exp(-0.5*t)-7);
        return res;
    }
    
    public static double derivada(double t){
       double res;
       res=(-160*Math.exp(-2*t))+(-10*Math.exp(-0.5*t));
       return res;
    }
}
