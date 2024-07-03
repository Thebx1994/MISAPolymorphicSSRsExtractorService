/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package other_resources;

/**
 *
 * @author carlos1
 */
public class SSr {
    private String ID;
    private final String type;
    private String pattern;
    private int copies;
    private final int size;
    private final int start;
    private final int end;
    private String f5; // the tag is 5f
    private float ent5;
    private String f3; // the tag is 3f
    private float ent3;
    private String ssr;
    
    public SSr(String ln1, String ln2) {
        
        String[] values=ln1.split(" ");
        
        this.ID = values[0].substring(values[0].indexOf(">")+1);
        this.type=values[1]; 
        this.pattern="";
        
        for (int i = 1; i < values[2].lastIndexOf(')'); i++) {
            
            if (values[2].charAt(i)=='A' ||
                    values[2].charAt(i)=='a' ||
                    values[2].charAt(i)=='T' ||
                    values[2].charAt(i)=='t' ||
                    values[2].charAt(i)=='C' ||
                    values[2].charAt(i)=='c' ||
                    values[2].charAt(i)=='G' ||
                    values[2].charAt(i)=='g')
                
                pattern+=values[2].charAt(i);
        }
        
        
        if (!"c".equals(type))
            this.copies = Integer.parseInt(values[2].substring(values[2].indexOf(")")+1));
        else{
            
            int i=values[2].indexOf(")")+1;
            
            while (values[2].charAt(i)!='a' &&
                   values[2].charAt(i)!='t' &&
                   values[2].charAt(i)!='c' &&
                   values[2].charAt(i)!='g') {
                
                i++;
            }
          
            this.copies = Integer.parseInt(values[2].substring(values[2].indexOf(")")+1, i)) + Integer.parseInt(values[2].substring(values[2].lastIndexOf(")")+1)); 
        }
        
        this.size = Integer.parseInt(values[4]);
        this.start = Integer.parseInt(values[6]) - 20;
        this.end = Integer.parseInt(values[8]) + 20;
        this.f5 = ln2.substring(0, 20);
        this.ent5 = entropy(f5);
        this.f3 = ln2.substring(ln2.length() - 20);
        this.ent3 = entropy(f3);
        this.ssr = ln2;
    }
    
    private float entropy(String repeat){
        
        float ca=0.01F,cc=0.01F,cg=0.01F, ct=0.01F, L = repeat.length();
        
        for (int i=0;  i < L; i++){
            
            switch (repeat.charAt(i)){
                
                case 'A': ca += 1 ; break;
                case 'C': cc += 1 ; break;
                case 'G': cg += 1 ; break;
                case 'T': ct += 1 ; break;
            }
        }
        
        double e=(ca/(float)L) * (Math.log(ca/(float)L)/Math.log(2)) +
                (cc/(float)L) * (Math.log(cc/(float)L)/Math.log(2)) +
                (cg/(float)L) * (Math.log(cg/(float)L)/Math.log(2)) +
                (ct/(float)L) * (Math.log(ct/(float)L)/Math.log(2));
        
        return (float) (-1*e);
    }
    
    public String getID() {
        return ID;
    }
    
    public void setID(String ID) {
        this.ID = ID;
    }
    
    public String getType() {
        return type;
    }
    
    public String getPattern() {
        return pattern;
    }
    
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
    
    public String getSsr() {
        return ssr;
    }
    
    public int getCopies() {
        return copies;
    }
    
    public void setCopies(int copies) {
        this.copies = copies;
    }
    
    public int getSize() {
        return size;
    }
    
    public int getStart() {
        return start;
    }
    
    public int getEnd() {
        return end;
    }
    
    public String getF5() {
        return f5;
    }
    
    public void setF5(String f5) {
        this.f5 = f5;
    }
    
    public float getEnt5() {
        return ent5;
    }
    
    public void setEnt5(float ent5) {
        this.ent5 = ent5;
    }
    
    public String getF3() {
        return f3;
    }
    
    public void setF3(String f3) {
        this.f3 = f3;
    }
    
    public float getEnt3() {
        return ent3;
    }
    
    public void setEnt3(float ent3) {
        this.ent3 = ent3;
    }
    
    
    
    public void setSsr(String ssr) {
        this.ssr = ssr;
    }
}
