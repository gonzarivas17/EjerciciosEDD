/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gonzalo
 */
public class PersonaComparable implements Comparable{

    @Override
    public int compareTo(Object t) {
        return -1;
    }
    
    public static void main (String [] args){
        Comparable a=1;
        System.out.println(a.toString());
        
    }
    
}
