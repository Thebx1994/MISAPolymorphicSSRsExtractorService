/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package other_resources;

import java.util.Objects;

/**
 * A object with the properties of a result of a hit table Blast data.
 *
 * @author Alejandro
 */
public class HtBLASTParser_Unit{
    
    private final String Query_acc_ver;
    private final String Subject_acc_ver;
    private final Float Percent_of_identity;
    private final Integer Alignment_length;
    private final Integer Mismatches;
    private final Integer Gap_opens;
    private final Integer Q_start;
    private final Integer Q_end;
    private final Integer S_start;
    private final Integer S_end;
    private final Float Evalue;
    private final Float Bit_score;
    
    /**
     * @param Query_acc_ver The access version of the input sequence (or other 
     * type of search term) to which all of the entries in a database are to 
     * be compared.
     * 
     * @param Subject_acc_ver The access version of a data base sequence to 
     * which the query are compared.
     * 
     * @param Percent_of_identity The percents of the extent
     * to which two (nucleotide or amino acid) sequence have the same residues
     * at the same positions in an alignment.
     * 
     * @param Alignment_length Length of the result of matching up the 
     * nucleotide or amino acid residues of two or more biological sequences, 
     * conservation, for the purpose of assessing the degree of similarity and 
     * the possibility of homology.
     * 
     * @param Mismatches A number that indicate the quantity of letters 
     * (nucleotide or amino acid) that are different in the alignment.
     * 
     * @param Gap_opens The number of spaces introduced into an alignments to 
     * compensate for insertions and deletions in one sequence relative to 
     * another. To prevent the accumulation of too many gaps in an alignment,
     * introduction of gap causes the deduction of a fixed amount
     * (the gap score) from the alignment score. Extension of the gap to 
     * encompass additional nucleotides or amino acid is also penalized in the 
     * scoring of an alignment.
     * 
     * @param Q_start The index of start of the alignment in the query sequence.
     * 
     * @param Q_end The index of end of the alignment in the query sequence.
     * 
     * @param S_start The index of start of the alignment in the subject sequence.
     * 
     * @param S_end The index of end of the alignment in the subject sequence.
     * 
     * @param Evalue The Expectations values or Expects values represents the 
     * numbers of different alignments with scores equivalents to or better than
     * S that is expected to occur in a database search by chance. The lower the
     * E value, the more significant the score and the alignment.
     * 
     * @param Bit_score The bit score, S', is derived from the raw alignment 
     * score, S, taking the statistical properties of the scoring system into 
     * account. Because bit scores are normalized with respect to the scoring 
     * system, they can be used to compare alignment scores from different 
     * searches.
     */
    public HtBLASTParser_Unit(String Query_acc_ver,String Subject_acc_ver, Float Percent_of_identity, Integer Alignment_length, Integer Mismatches, Integer Gap_opens, Integer Q_start, Integer Q_end, Integer S_start, Integer S_end, Float Evalue, Float Bit_score) {
        
        this.Query_acc_ver = Query_acc_ver;
        this.Subject_acc_ver=Subject_acc_ver;
        this.Percent_of_identity = Percent_of_identity;
        this.Alignment_length = Alignment_length;
        this.Mismatches = Mismatches;
        this.Gap_opens = Gap_opens;
        this.Q_start = Q_start;
        this.Q_end = Q_end;
        this.S_start = S_start;
        this.S_end = S_end;
        this.Evalue = Evalue;
        this.Bit_score = Bit_score;
    }
    
    /**
     *Get the subject id acc.ver of this HtBLASTParser_Unit.
     * @return A String that represent the subject acc.ver of this HtBLASTParser_Unit.
     */
    public String getSubject_acc_ver() {
        return Subject_acc_ver;
    }
    
    /**
     *Get the query id acc.ver of this HtBLASTParser_Unit.
     * @return A String that represent the query acc.ver of this HtBLASTParser_Unit.
     */
    public String getQuery_acc_ver() {
        return Query_acc_ver;
    }
    
    /**
     *Get the percent of identity of this HtBLASTParser_Unit.
     * @return A float that represent the percent of identity of this HtBLASTParser_Unit.
     */
    public Float getPercent_of_identity() {
        return Percent_of_identity;
    }
    
    /**
     *Get the alignment length of this HtBLASTParser_Unit.
     * @return A integer that represent the alignment length of this HtBLASTParser_Unit.
     */
    public Integer getAlignment_length() {
        return Alignment_length;
    }
    
    /**
     *Gets the mismatches of this HtBLASTParser_Unit.
     * @return A integer that represent the  mismatches of this HtBLASTParser_Unit.
     */
    public Integer getMismatches() {
        return Mismatches;
    }
    
    /**
     *Gets the gap opens of this HtBLASTParser_Unit.
     * @return  A integer that represent the gap opens of this HtBLASTParser_Unit.
     */
    public Integer getGap_opens() {
        return Gap_opens;
    }
    
    /**
     *Get the q.star of this HtBLASTParser_Unit.
     * @return  A integer that represent the q.star of this HtBLASTParser_Unit.
     */
    public Integer getQ_start() {
        return Q_start;
    }
    
    /**
     *Get the q.end of this HtBLASTParser_Unit.
     * @return A integer that represent the q.end of this HtBLASTParser_Unit.
     */
    public Integer getQ_end() {
        return Q_end;
    }
    
    /**
     *Get the s.star of this HtBLASTParser_Unit.
     * @return A integer that represent the s.star of this HtBLASTParser_Unit.
     */
    public Integer getS_start() {
        return S_start;
    }
    
    /**
     *Get the s.end of this HtBLASTParser_Unit.
     * @return A integer that represent the s.end of this HtBLASTParser_Unit.
     */
    public Integer getS_end() {
        return S_end;
    }
    
    /**
     *Get the evalue of this HtBLASTParser_Unit.
     * @return A float that represent the s.end of this HtBLASTParser_Unit.
     */
    public Float getEvalue() {
        return Evalue;
    }
    
    /**
     *Get the bit score of this HtBLASTParser_Unit.
     * @return  A float that represent the bit score of this HtBLASTParser_Unit.
     */
    public Float getBit_score() {
        return Bit_score;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.Query_acc_ver);
        hash = 97 * hash + Objects.hashCode(this.Subject_acc_ver);
        hash = 97 * hash + Objects.hashCode(this.Percent_of_identity);
        hash = 97 * hash + Objects.hashCode(this.Alignment_length);
        hash = 97 * hash + Objects.hashCode(this.Mismatches);
        hash = 97 * hash + Objects.hashCode(this.Gap_opens);
        hash = 97 * hash + Objects.hashCode(this.Q_start);
        hash = 97 * hash + Objects.hashCode(this.Q_end);
        hash = 97 * hash + Objects.hashCode(this.S_start);
        hash = 97 * hash + Objects.hashCode(this.S_end);
        hash = 97 * hash + Objects.hashCode(this.Evalue);
        hash = 97 * hash + Objects.hashCode(this.Bit_score);
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
        final HtBLASTParser_Unit other = (HtBLASTParser_Unit) obj;
        if (!Objects.equals(this.Query_acc_ver, other.Query_acc_ver)) {
            return false;
        }
        if (!Objects.equals(this.Subject_acc_ver, other.Subject_acc_ver)) {
            return false;
        }
        if (!Objects.equals(this.Percent_of_identity, other.Percent_of_identity)) {
            return false;
        }
        if (!Objects.equals(this.Alignment_length, other.Alignment_length)) {
            return false;
        }
        if (!Objects.equals(this.Mismatches, other.Mismatches)) {
            return false;
        }
        if (!Objects.equals(this.Gap_opens, other.Gap_opens)) {
            return false;
        }
        if (!Objects.equals(this.Q_start, other.Q_start)) {
            return false;
        }
        if (!Objects.equals(this.Q_end, other.Q_end)) {
            return false;
        }
        if (!Objects.equals(this.S_start, other.S_start)) {
            return false;
        }
        if (!Objects.equals(this.S_end, other.S_end)) {
            return false;
        }
        if (!Objects.equals(this.Evalue, other.Evalue)) {
            return false;
        }
        return Objects.equals(this.Bit_score, other.Bit_score);
    }
   
    @Override
    public String toString()
    {
        return Query_acc_ver+"\t"+
                Subject_acc_ver+"\t"+
                Percent_of_identity+"\t"+
                Alignment_length+"\t"+
                Mismatches+"\t"+
                Gap_opens+"\t"+
                Q_start+"\t"+
                Q_end+"\t"+
                S_start+"\t"+
                S_end+"\t"+
                Evalue+"\t"+
                Bit_score;
    }
}

