/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package other_resources;

import java.util.Objects;

/**
 *A object that store tow object at the same time.
 * 
 * @author Alejandro
 */
public class Tuple{
    
    /**
     *The first object of the tuple.
     */
    public Object Item1;
    /**
     *The second object of the tuple.
     */
    public Object Item2;
    
    public Tuple(Object Item1, Object Item2) {
        this.Item1 = Item1;
        this.Item2 = Item2;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(o.getClass()==Tuple.class)
        {
            Tuple object=(Tuple)o;
            
            if(object.Item1==this.Item1 && object.Item2==this.Item2)
                return true;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.Item1);
        hash = 83 * hash + Objects.hashCode(this.Item2);
        return hash;
    }
    
    @Override
    public String toString()
    {
        String r;
        
        if(this.Item1==null && this.Item2!=null)
            r= "(null"+", "+this.Item2.toString()+")";
        else if(this.Item1!=null && this.Item2==null)
            r= "("+this.Item1.toString()+", "+"null)";
        else if(this.Item1==null && this.Item2==null)
            r= "(null"+", "+"null)";
        else
            r="("+this.Item1.toString()+", "+this.Item2.toString()+")";
        
        return r;
    }
}

