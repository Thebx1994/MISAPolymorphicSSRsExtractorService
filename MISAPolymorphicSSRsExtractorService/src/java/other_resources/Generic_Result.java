/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package other_resources;

import java.util.Objects;

/**
 * A object that have useful general information about all the results with the same query acc ver.
 *
 * @author Alejandro
 */
public class Generic_Result {
    
    private final String Query_acc_ver;
    private final int Locat_l;
    private final int Locat_r;
    private final String Type;
    private final String Pattern;
    private final int Pattern_length;
    private final Integer Number_of_Runits;
    private final String F5;
    private final Float Entropy_5;
    private final String F3;
    private final Float Entropy_3;
    private final int Min_quantity;
    private final int Max_quantity;
    private final int Range;
    private final double Alelic_frequency;
    private final int Alelics_Number;
    private final double PIC;
    private final String Exceptions;
    
    
    /**
     * @param Query_acc_ver The access version of the input sequence (or other type of search term) to
     *        which all of the entries in a database are to be compared.
     * @param Locat_l
     * @param Locat_r
     * @param Type
     * @param Pattern The repeat unit of the query.
     * @param Pattern_length The quantity of nitrogenated bases that have the repeat unit.
     * @param Number_of_Runits The quantity of repeat unit that have the query between two flanks.
     * @param F5
     * @param Entropy_5 Conformational entropy of the 5' flank.
     * @param F3
     * @param Entropy_3 Conformational entropy of the 3' flank.
     * @param Min_quantity The quantity of repeat unit in the subject of minimum distance.
     * @param Max_quantity The quantity of repeat unit in the subject of maximum distance.
     * @param Range The difference between the max and the minimum number of nitrogenated bases of a query acc ver.
     * @param Alelic_frequency This variable also known like "polymorphism cup(Pj)" represent the frequency of the allele.
     * A gene is defined polymorphic when:
     * <ul>
     * <li>Pj<=0.95 or Pj<=0.99.
     * </ul>
     * <p>
     * @param Alelics_Number The number of variant in one sample.
     * @param PIC The probability that, in a unique locus, each alelle par, chosen by azar of the population, are different.
     * @param Exceptions All the exceptions that may have the subjects of the
     * same query that falsify the calculus:
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
     *
     * <li>If the subjects with the same query have exceptions then this ones
     * will be accumulates like "D O UD".
     * <p>
     * The exceptions in a generic result only are reports when all the subjects
     * of the same query have exceptions.
     */
    
    public Generic_Result(String Query_acc_ver, int Locat_l, int Locat_r,String Type ,String Pattern, int Pattern_length, Integer Number_of_Runits, String F5, Float Entropy_5, String F3, Float Entropy_3, int Min_quantity, int Max_quantity, int Range, double Alelic_frequency, int Alelics_Number, double PIC, String Exceptions) {
        this.Query_acc_ver = Query_acc_ver;
        this.Locat_l = Locat_l;
        this.Locat_r = Locat_r;
        this.Type = Type;
        this.Pattern = Pattern;
        this.Pattern_length = Pattern_length;
        this.Number_of_Runits = Number_of_Runits;
        this.F5 = F5;
        this.Entropy_5 = Entropy_5;
        this.F3 = F3;
        this.Entropy_3 = Entropy_3;
        this.Min_quantity = Min_quantity;
        this.Max_quantity = Max_quantity;
        this.Range = Range;
        this.Alelic_frequency = Alelic_frequency;
        this.Alelics_Number = Alelics_Number;
        this.PIC = PIC;
        this.Exceptions = Exceptions;
    }
    
    public Generic_Result(){
        
        this.Query_acc_ver = "";
        this.Locat_l = 0;
        this.Locat_r = 0;
        this.Type = "";
        this.Pattern = "";
        this.Pattern_length = 0;
        this.Number_of_Runits = 0;
        this.F5 = "";
        this.Entropy_5 = 0f;
        this.F3 = "";
        this.Entropy_3 = 0f;
        this.Min_quantity = 0;
        this.Max_quantity = 0;
        this.Range = 0;
        this.Alelic_frequency = 0;
        this.Alelics_Number = 0;
        this.PIC = 0;
        this.Exceptions = "";
    }
    
    public String getQuery_acc_ver() {
        return Query_acc_ver;
    }

    public String getType() {
        return Type;
    }
        
    public String getPattern() {
        return Pattern;
    }
    
    public int getPattern_length() {
        return Pattern_length;
    }
    
    public Integer getNumber_of_Runits() {
        return Number_of_Runits;
    }
    
    public int getMin_quantity() {
        return Min_quantity;
    }
    
    public int getMax_quantity() {
        return Max_quantity;
    }
    
    /**
     *Gets the range of this generic result.
     *
     * @return A integer that represent the range.
     */
    public int getRange() {
        return Range;
    }
    
    /**
     *Gets the polymorphism of this generic result.
     *
     * @return A double that represent the polymorphism.
     */
    public double getAlelic_Frequency() {
        return Alelic_frequency;
    }
    
    /**
     *Gets the alelic variant abundance of this generic result.
     *
     * @return A double that represent the alelic variant abundance.
     */
    public int getAlelics_Number() {
        return Alelics_Number;
    }
    
    /**
     *Gets the locus heterocigocity of this generic result.
     *
     * @return A double that represent the locus heterocigocity.
     */
    public double getPIC() {
        return PIC;
    }
    
    public String getExceptions() {
        return Exceptions;
    }
    
    public int getLocat_l() {
        return Locat_l;
    }
    
    public int getLocat_r() {
        return Locat_r;
    }
    
    public String getF5() {
        return F5;
    }
    
    public Float getEntropy_5() {
        return Entropy_5;
    }
    
    public String getF3() {
        return F3;
    }
    
    public Float getEntropy_3() {
        return Entropy_3;
    }
    
    public double getAlelic_frequency() {
        return Alelic_frequency;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.Query_acc_ver);
        hash = 19 * hash + this.Locat_l;
        hash = 19 * hash + this.Locat_r;
        hash = 19 * hash + Objects.hashCode(this.Type);
        hash = 19 * hash + Objects.hashCode(this.Pattern);
        hash = 19 * hash + this.Pattern_length;
        hash = 19 * hash + Objects.hashCode(this.Number_of_Runits);
        hash = 19 * hash + Objects.hashCode(this.F5);
        hash = 19 * hash + Objects.hashCode(this.Entropy_5);
        hash = 19 * hash + Objects.hashCode(this.F3);
        hash = 19 * hash + Objects.hashCode(this.Entropy_3);
        hash = 19 * hash + this.Min_quantity;
        hash = 19 * hash + this.Max_quantity;
        hash = 19 * hash + this.Range;
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.Alelic_frequency) ^ (Double.doubleToLongBits(this.Alelic_frequency) >>> 32));
        hash = 19 * hash + this.Alelics_Number;
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.PIC) ^ (Double.doubleToLongBits(this.PIC) >>> 32));
        hash = 19 * hash + Objects.hashCode(this.Exceptions);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Generic_Result other = (Generic_Result) obj;
        if (this.Locat_l != other.Locat_l) {
            return false;
        }
        if (this.Locat_r != other.Locat_r) {
            return false;
        }
        if (this.Pattern_length != other.Pattern_length) {
            return false;
        }
        if (this.Min_quantity != other.Min_quantity) {
            return false;
        }
        if (this.Max_quantity != other.Max_quantity) {
            return false;
        }
        if (this.Range != other.Range) {
            return false;
        }
        if (Double.doubleToLongBits(this.Alelic_frequency) != Double.doubleToLongBits(other.Alelic_frequency)) {
            return false;
        }
        if (this.Alelics_Number != other.Alelics_Number) {
            return false;
        }
        if (Double.doubleToLongBits(this.PIC) != Double.doubleToLongBits(other.PIC)) {
            return false;
        }
        if (!Objects.equals(this.Query_acc_ver, other.Query_acc_ver)) {
            return false;
        }
        if (!Objects.equals(this.Type, other.Type)) {
            return false;
        }
        if (!Objects.equals(this.Pattern, other.Pattern)) {
            return false;
        }
        if (!Objects.equals(this.F5, other.F5)) {
            return false;
        }
        if (!Objects.equals(this.F3, other.F3)) {
            return false;
        }
        if (!Objects.equals(this.Exceptions, other.Exceptions)) {
            return false;
        }
        if (!Objects.equals(this.Number_of_Runits, other.Number_of_Runits)) {
            return false;
        }
        if (!Objects.equals(this.Entropy_5, other.Entropy_5)) {
            return false;
        }
        if (!Objects.equals(this.Entropy_3, other.Entropy_3)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        
        return Query_acc_ver+"\t"+
                Locat_l+"\t"+
                Locat_r+"\t"+
                Type+"\t"+
                Pattern+"\t"+
                Pattern_length+"\t"+
                Number_of_Runits+"\t"+
                F5+"\t"+
                Entropy_5+"\t"+
                F3+"\t"+
                Entropy_3+"\t"+
                Min_quantity+"\t"+
                Max_quantity+"\t"+
                Range+"\t"+
                Alelic_frequency+"\t"+
                Alelics_Number+"\t"+
                PIC+"\t"+
                Exceptions;
    }
}

