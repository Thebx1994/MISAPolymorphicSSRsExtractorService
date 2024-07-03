/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package other_resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public class HtBLASTParser {
    
    private final String Pathway;
    private final ArrayList<HtBLASTParser_Unit> Units;
    
    /**
     *
     * @param pathway The direction of the file to read.
     * @throws java.lang.Exception If the file don't have .htt extension.
     */
    @SuppressWarnings("empty-statement")
    public HtBLASTParser(String pathway) throws Exception{
        
        Pathway=pathway;
        Units=new ArrayList<>();
        
        File f=new File(Pathway);
//        System.out.println(f.getPath());
//        System.out.println(f.getPath().endsWith(".htt"));

if(f.getPath().endsWith(".htt") && f.length()>0){
    
    ArrayList<String> lecture=new ArrayList<>();
    List<String> check=new ArrayList<>();
    List<String> qav=new ArrayList<>();
    
    try {
        FileReader fr = new FileReader(f.getPath());
        try (BufferedReader enter = new BufferedReader(fr)) {
            
            String s;
            List<String> headers=new ArrayList<>();
            
            while((s=enter.readLine())!=null)
            {
                if(s.contains("#"))
                {
                    if(s.startsWith("# Query:") && !check.contains(s))
                        check.add(s);
                    if( s.equals("# blastn") && headers.size()==5)
                    {
                        lecture.add(headers.get(2));
                        headers.clear();
                        headers.add(s);
                    }
                    else
                        headers.add(s);
                }
                else if(!s.equals("") && !s.contains("#"))
                {
                    lecture.add(s);
                    headers.clear();
                }
            }
        }
    }
    catch(java.io.FileNotFoundException fnfex) {
        System.out.println("File not found: " + fnfex);
    }
    catch(java.io.IOException ioex) {}
    
    
    
    for(String s:lecture)
    {
        if(s.startsWith("# Query:"))
            Units.add(new HtBLASTParser_Unit(s.substring(9),null,null,null,null,null,null,null,null,null,null,null));
        else{
            
            String[] lectemp=s.split("\t");
            
            Units.add(new HtBLASTParser_Unit(lectemp[0],
                    lectemp[1],
                    Float.parseFloat(lectemp[2]),
                    Integer.parseInt(lectemp[3]),
                    Integer.parseInt(lectemp[4]),
                    Integer.parseInt(lectemp[5]),
                    Integer.parseInt(lectemp[6]),
                    Integer.parseInt(lectemp[7]),
                    Integer.parseInt(lectemp[8]),
                    Integer.parseInt(lectemp[9]),
                    Float.parseFloat(lectemp[10]),
                    Float.parseFloat(lectemp[11])));
        }
    }
    
    for (HtBLASTParser_Unit u: Units)
    {
        if(!qav.contains(u.getQuery_acc_ver()))
            qav.add(u.getQuery_acc_ver());
    }
    
    if(qav.size()<check.size())
        Units.add(new HtBLASTParser_Unit(check.get(check.size()-1).substring(9),null,null,null,null,null,null,null,null,null,null,null));
}
else
    throw new Exception("The file must have a .htt extension and a length > 0.");
    }
    
    
    /**
     *Gets the direction of the file that the HtBLASTParser read.
     * @return A ArrayList with the directions of the files.
     */
    public String getPathway() {
        return Pathway;
    }
    
    /**
     *Gets the HtBlastParser_Units of the read file.
     * @return A ArrayList of HtBLASTParser_Unit.
     */
    public ArrayList<HtBLASTParser_Unit> getUnits() {
        return Units;
    }
    
    /**
     *Search HtBlastParser_Units among the HtBlastParser_Units that the
     * HtBLASTParser read to satisfy the parameters of this method.
     *
     * @param query_acc_ver
     *        The access version of the input sequence (or other type of search term) to
     *        which all of the entries in a database are to be compared.
     * @param subject_acc_ver
     *        The access version of a data base sequence to which the query are compared.
     * @param percent_of_identity
     *        A interval of Floats that contains the percents of the extent
     *        to which two (nucleotide or amino acid) sequence have the same residues
     *        at the same positions in an alignment. For example:
     * <ul>
     *<li>Tuple t=new Tuple(0.0F,1.0F) The percent_of_identity to search are greater or equal than 0 and less or equal than 1.
     *<li>Tuple t=new Tuple(0.0F,null) The percent_of_identity to search are greater or equal than 0.
     *<li>Tuple t=new Tuple(null,1.0F) The percent_of_identity to search are less or equal than 1.
     * </ul>
     * <p>
     *This rules are applicable for other parameters like gap_opens,e value and bit_score.
     *
     * <p>
     *@param alignment_length
     *        Length of the result of matching up the nucleotide or amino acid residues of
     *        two or more biological sequences, conservation, for the purpose of assessing
     *        the degree of similarity and the possibility of homology.
     *
     * @param mismatches
     *        A number that indicate the quantity of letters (nucleotide or
     *        amino acid) that are different in the alignment.
     *
     * @param gap_opens
     *        A interval of Integers that contains the numbers of spaces
     *        introduced into an alignments to compensate for
     *        insertions and deletions in one sequence relative to another.
     *        To prevent the accumulation of too many gaps in an alignment,
     *        introduction of gap causes the deduction of a fixed amount(the gap score) from
     *        the alignment score. Extension of the gap to encompass additional nucleotides or
     *        amino acid is also penalized in the scoring of an alignment.
     *
     * @param q_start
     *        The index of start of the alignment in the query sequence.
     * @param q_end
     *        The index of end of the alignment in the query sequence.
     * @param s_start
     *        The index of start of the alignment in the subject sequence.
     * @param s_end
     *        The index of end of the alignment in the subject sequence.
     * @param evalue
     *        A interval of Floats that contains the Expectations values or Expects values that
     *        represents the numbers of different alignments with scores equivalents to or better than
     *        S that is expected to occur in a database search by chance. The lower the E value,
     *        the more significant the score and the alignment.
     *
     * @param bit_score
     *        A interval of Floats that contains the bit score, S', that is derived from the raw alignment score, S,
     *        taking the statistical properties of the scoring system into account.
     *        Because bit scores are normalized with respect to the scoring system, they can
     *        be used to compare alignment scores from different searches.
     *
     * @return A ArrayList with the HtBlastParser_Units to satisfy the search.
     * @throws Exception if don't exist any HtBLASTParser_Unit to satisfy the parameters of this method.
     */
    public ArrayList<HtBLASTParser_Unit> search(String query_acc_ver,String subject_acc_ver, Tuple percent_of_identity, Integer alignment_length, Integer mismatches, Tuple gap_opens, Integer q_start, Integer q_end, Integer s_start, Integer s_end, Tuple evalue, Tuple  bit_score) throws Exception{
        
        Object[] params=new Object[]{query_acc_ver,
            subject_acc_ver,
            percent_of_identity,
            alignment_length,
            mismatches,
            gap_opens,
            q_start,
            q_end,
            s_start,
            s_end,
            evalue,
            bit_score};
        
        ArrayList<HtBLASTParser_Unit> result=new ArrayList<>();
        
        
        for (HtBLASTParser_Unit unit : Units) {
            
            int isNotNull=0;
            int count=0;
            
            for(int i=0;i<params.length;i++)
            {
                if(params[i]!=null)
                {
                    isNotNull++;
                    
                    switch(i)
                    {
                        case 0:
                            if(unit.getQuery_acc_ver().equals((String)params[i]))
                                count++;
                            break;
                            
                        case 1:
                            if(unit.getSubject_acc_ver().equals((String)params[i]))
                                count++;
                            break;
                            
                        case 2:
                            if(params[i].getClass()==Tuple.class)
                            {
                                Tuple range=(Tuple)params[i];
                                
                                if(range.Item1!=null)
                                {
                                    if(unit.getPercent_of_identity()>=(float)range.Item1 && range.Item2==null)
                                        count++;
                                }
                                else if(range.Item2!=null)
                                {
                                    if(range.Item1==null && unit.getPercent_of_identity()<=(float)range.Item2)
                                        count++;
                                }
                                else if(unit.getPercent_of_identity()>=(float)range.Item1 && unit.getPercent_of_identity()<=(float)range.Item2)
                                    count++;
                            }
                            break;
                            
                        case 3:
                            if(unit.getAlignment_length()==(int)params[i])
                                count++;
                            break;
                            
                        case 4:
                            if(unit.getMismatches()==(int)params[i])
                                count++;
                            break;
                            
                        case 5:
                            if(params[i].getClass()==Tuple.class)
                            {
                                Tuple range=(Tuple)params[i];
                                
                                if(range.Item1!=null)
                                {
                                    if(unit.getGap_opens()>=(int)range.Item1 && range.Item2==null)
                                        count++;
                                }
                                else if(range.Item2!=null)
                                {
                                    if(range.Item1==null && unit.getGap_opens()<=(int)range.Item2)
                                        count++;
                                }
                                else if(unit.getGap_opens()>=(int)range.Item1 && unit.getGap_opens()<=(int)range.Item2)
                                    count++;
                            }
                            break;
                            
                        case 6:
                            if(unit.getQ_start()==(int)params[i])
                                count++;
                            break;
                            
                        case 7:
                            if(unit.getQ_end()==(int)params[i])
                                count++;
                            break;
                            
                        case 8:
                            if(unit.getS_start()==(int)params[i])
                                count++;
                            break;
                            
                        case 9:
                            if(unit.getS_end()==(int)params[i])
                                count++;
                            break;
                            
                        case 10:
                            if(params[i].getClass()==Tuple.class)
                            {
                                Tuple range=(Tuple)params[i];
                                
                                if(range.Item1!=null)
                                {
                                    if(unit.getEvalue()>=(float)range.Item1 && range.Item2==null)
                                        count++;
                                }
                                else if(range.Item2!=null)
                                {
                                    if(range.Item1==null && unit.getEvalue()<=(float)range.Item2)
                                        count++;
                                }
                                else if(unit.getEvalue()>=(float)range.Item1 && unit.getEvalue()<=(float)range.Item2)
                                    count++;
                            }
                            break;
                            
                        case 11:
                            if(params[i].getClass()==Tuple.class)
                            {
                                Tuple range=(Tuple)params[i];
                                
                                if(range.Item1!=null)
                                {
                                    if(unit.getBit_score()>=(float)range.Item1 && range.Item2==null)
                                        count++;
                                }
                                else if(range.Item2!=null)
                                {
                                    if(range.Item1==null && unit.getBit_score()<=(float)range.Item2)
                                        count++;
                                }
                                else if(unit.getBit_score()>=(float)range.Item1 && unit.getBit_score()<=(float)range.Item2)
                                    count++;
                            }
                            break;
                    }
                }
            }
            if(count==isNotNull && !result.contains(unit))
                result.add(unit);
        }
        
        if(result.isEmpty())
            throw new Exception("Units not founds");
        else
            return result;
    }
}
