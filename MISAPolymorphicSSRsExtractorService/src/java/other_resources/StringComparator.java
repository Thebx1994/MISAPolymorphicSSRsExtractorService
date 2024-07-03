/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package other_resources;

import java.util.Comparator;

/**
 *
 * @author gironsti
 */
public class StringComparator implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        
        String s1=(String)o1;
        String s2=(String)o2;
        
        return s1.compareToIgnoreCase(s2);
    }
    
}
