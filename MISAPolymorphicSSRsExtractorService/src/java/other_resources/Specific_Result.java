/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package other_resources;

import java.util.Objects;

/**
 *A object that have useful information about the nitrogenated bases between two flanks.
 * 
 * @author Alejandro
 */
public class Specific_Result {
    
    private final String Query_acc_ver;
    private final String Subject_acc_ver;
    private final Integer Start_p;
    private final Integer End_p;
    private final Integer Number_of_Nbases;
    private final Integer Number_of_Runits;
    private final char Direction;
    private final String Outlier;
    private final String Exceptions;
    
    /**
     * @param Query_acc_ver The access version of the input sequence (or other type of search term) to
     *        which all of the entries in a database are to be compared.
     *
     * @param Subject_acc_ver The access version of a data base sequence to which the query are compared.
     *
     * @param Start_p The start position of the subject sequence.
     *
     * @param End_p The end position of the subject sequence.
     *
     * @param Number_of_Nbases The quantity of nitrogenated bases between two flanks in the subject sequence.
     *
     * @param Number_of_Runits The quantity of repeats units between two flanks in the subject sequence.
     *
     * @param Direction A symbol plus(a direct sequence) or minus(a reverse complement sequence) depends of the direction of the subject sequence.
     * @param Outlier Represent how many times the query is >= that the quantity of repeats units that define an outlier:
     * <ul>
     * <li> if the quantity of r.units of the query is > that the limit then this method return " * ".
     * <li> if the quantity of r.units of the query is >= that the limit*2 then this method return " ** ".
     * <li> if the quantity of r.units of the query is >= that the limit*3 then this method return " *** ".
     * <li> if the quantity of r.units of the query is >= that the limit*4 then this method return " **** ".
     * <li> if the quantity of r.units of the query is >= that the limit*5 then this method return " ***** ".
     * </ul>
     * <p>
     * The limit of quantity for a repeat unit of length 1 is 157, 
     * for length 2 are 364, 
     * for length 3 are 109,
     * for length 4 are 45,
     * for length 5 are 150, 
     * for length 6 are 193.
     * <p>
     * @param Exception The exceptions that may have a HtBLASTParser_Unit that falsify 
     * the calculus:  
     * <ul>
     * <li>D: degenerate: It means that the subject sequence have flanks with
     * a percent of identity and a percent of cover less than the establishes.
     * <li>NF: not fount: It means that the query sequence don't 
     * appear in the NCBI data base.
     * <li>U: unpair: It means that the subject sequence is only formed by one
     * flank because don't exist a HtBLASTParser_Unit that represent other flank
     * with the same subject access ver, a percent of identity and a percent of 
     * cover greater or equals than the arguments establishes.
     * <li>O: outlier: It means that the number of repeats units not guarantee 
     * that the subject sequence be a microsatellite.
     * </ul>
     * <p>
     * This exceptions are may be mixed for eg:
     * <ul>
     * <li>If the same subject are unpair but also degenerate 
     * then the exception will be "UD".
     */
    public Specific_Result(String Query_acc_ver, String Subject_acc_ver, Integer Start_p, Integer End_p, Integer Number_of_Nbases, Integer Number_of_Runits, char Direction, String Outlier, String Exception) {
        
        this.Query_acc_ver = Query_acc_ver;
        this.Subject_acc_ver = Subject_acc_ver;
        this.Start_p = Start_p;
        this.End_p = End_p;
        this.Number_of_Nbases = Number_of_Nbases;
        this.Number_of_Runits = Number_of_Runits;
        this.Direction = Direction;
        this.Outlier=Outlier;
        this.Exceptions=Exception;
    }
    
    public Specific_Result() {
        
        this.Query_acc_ver = "";
        this.Subject_acc_ver = "";
        this.Start_p = 0;
        this.End_p = 0;
        this.Number_of_Nbases = 0;
        this.Number_of_Runits = 0;
        this.Direction = ' ';
        this.Outlier= "";
        this.Exceptions="";
    }
    
    /**
     * Get the query acc ver.
     *
     * @return A String that represent the query acc ver.
     */
    public String getQuery_acc_ver() {
        return Query_acc_ver;
    }
      
    /**
     *Get the subject acc ver.
     *
     * @return A String that represent the subject acc ver.
     */
    public String getSubject_acc_ver() {
        return Subject_acc_ver;
    }
    
    /**
     * Get the start position of the subject sequence.
     *
     * @return A int that represent the start position.
     */
    public Integer getStart_p() {
        return Start_p;
    }
    
    /**
     *Get the end position of the subject sequence.
     *
     * @return A int that represent the end position.
     */
    public Integer getEnd_p() {
        return End_p;
    }
    
    /**
     *Get the quantity of nitrogenated bases between two flanks in the subject sequence.
     *
     * @return A Integer that represent the quantity of nitrogenated bases.
     */
    public Integer getNumber_of_Nbases() {
        return Number_of_Nbases;
    }
    
    /**
     *Get the quantity of repeats units between two flanks in the subject sequence.
     *
     * @return A Integer that represent the quantity of repeats units.
     */
    public Integer getNumber_of_Runits() {
        return Number_of_Runits;
    }
    
    /**
     *Get a symbol plus(a direct sequence) or minus(a reverse complement sequence) 
     * depends of the direction of the subject sequence.
     *
     * @return A char that represent the symbol of the direction.
     */
    public char getDirection() {
        return Direction;
    }

    /**
     *
     * @return Return a String that represent how many times the query 
     * is >= that the quantity of repeats units that define an outlier:
     * <ul>
     * <li> if the quantity of r.units of the query is > that the limit then this method return " * ".
     * <li> if the quantity of r.units of the query is >= that the limit*2 then this method return " ** ".
     * <li> if the quantity of r.units of the query is >= that the limit*3 then this method return " *** ".
     * <li> if the quantity of r.units of the query is >= that the limit*4 then this method return " **** ".
     * <li> if the quantity of r.units of the query is >= that the limit*5 then this method return " ***** ".
     * </ul>
     * <p>
     * The limit of quantity for a repeat unit of length 1 is 157, 
     * for length 2 are 364, 
     * for length 3 are 109, 
     * for length 4 are 45, 
     * for length 5 are 150, 
     * for length 6 are 193.
     */
    public String getOutlier() {
        return Outlier;
    }

    /**
     *
     * @return The exceptions that may have a HtBLASTParser_Unit that falsify 
     * the calculus:  
     *
     * <ul>
     * <li>D: degenerate: It means that the subject sequence have flanks with
     * a percent of identity and a percent of cover less than the establishes.
     * <li>NF: not fount: It means that the query sequence don't 
     * appear in the NCBI data base.
     * <li>U: unpair: It means that the subject sequence is only formed by one
     * flank because don't exist a HtBLASTParser_Unit that represent other flank
     * with the same subject access ver, a percent of identity and a percent of 
     * cover greater or equals than the arguments establishes.
     * <li>O: outlier: It means that the number of repeats units not guarantee 
     * that the subject sequence be a microsatellite.   
     */
    public String getExceptions() {
        return Exceptions;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.Query_acc_ver);
        hash = 53 * hash + Objects.hashCode(this.Subject_acc_ver);
        hash = 53 * hash + Objects.hashCode(this.Start_p);
        hash = 53 * hash + Objects.hashCode(this.End_p);
        hash = 53 * hash + Objects.hashCode(this.Number_of_Nbases);
        hash = 53 * hash + Objects.hashCode(this.Number_of_Runits);
        hash = 53 * hash + this.Direction;
        hash = 53 * hash + Objects.hashCode(this.Outlier);
        hash = 53 * hash + Objects.hashCode(this.Exceptions);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Specific_Result other = (Specific_Result) obj;
        if (!Objects.equals(this.Query_acc_ver, other.Query_acc_ver)) {
            return false;
        }
        if (!Objects.equals(this.Subject_acc_ver, other.Subject_acc_ver)) {
            return false;
        }
        if (!Objects.equals(this.Start_p, other.Start_p)) {
            return false;
        }
        if (!Objects.equals(this.End_p, other.End_p)) {
            return false;
        }
        if (!Objects.equals(this.Number_of_Nbases, other.Number_of_Nbases)) {
            return false;
        }
        if (!Objects.equals(this.Number_of_Runits, other.Number_of_Runits)) {
            return false;
        }
        if (this.Direction != other.Direction) {
            return false;
        }
        if (!Objects.equals(this.Outlier, other.Outlier)) {
            return false;
        }
        return Objects.equals(this.Exceptions, other.Exceptions);
    }
   
    @Override
    public String toString(){
        
        return Query_acc_ver.substring(0,Query_acc_ver.indexOf("]")+1)+"\t"+
                Subject_acc_ver+"\t"+
                Start_p+"\t"+
                End_p+"\t"+
                Number_of_Nbases+"\t"+
                Number_of_Runits+"\t"+
                Direction+"\t"+
                Outlier+"\t"+
                Exceptions;
    }
}
