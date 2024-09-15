/*
* To change this license header, choose License Headers in Project Properties.
* To change this template uploadedFile, choose Tools | Templates
* and open the template in the editor.
*/
package UIBeans;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import static java.lang.Thread.sleep;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.util.IOUtils;
import org.primefaces.model.file.UploadedFile;
import other_resources.StringComparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;
import other_resources.Generic_Result;
import other_resources.HtBLASTParser_Unit;
import other_resources.MISAParser;
import other_resources.Polymorphism;
import other_resources.SSr;
import other_resources.Specific_Result;

/**
 *
 * @author carlos2
 */
@ManagedBean
@SessionScoped
public class PipelineManagedBean implements Serializable{
    
    /**
     * Creates a new instance of PipelineManagedBean
     */
    private UploadedFile uploadedFile;
    private int min_mono=10;
    private int min_di=6;
    private int min_three=5;
    private int min_tetra=5;
    private int min_pent=5;
    private int min_hexa=5;
    private String results;
    private String MISAInput="";
    private String organism;
    private int maxTargetSequences=100;
    private int expectThreshold=30;
    private float identityPercent=90;
    private float coveragePercent=90;
    private ArrayList<Specific_Result> specificResults;
    private ArrayList<Generic_Result> genericResults;
    private ArrayList<HtBLASTParser_Unit> hitTable;
    
    public void setByDefault(ActionEvent event){
        
        File file=new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(File.separator+"resources"+File.separator+"test"+File.separator+"Mycoplasmoides pneumoniae.fasta"));
        
        try {
            FileReader fr = new FileReader(file.getPath());
            try (BufferedReader enter = new BufferedReader(fr)) {
                
                String text=">NZ_LR214945.1 Mycoplasmoides pneumoniae strain NCTC10119 chromosome 1\n";
                
                while(enter.readLine()!=null)
                {
                    text+=enter.readLine();
                }
                
                MISAInput=text;
            }
            
            organism="Mycoplasmoides pneumoniae";
            maxTargetSequences=20;
        }
        catch(java.io.FileNotFoundException fnfex) {
            System.err.println("File not found: " + fnfex);
        }
        catch(java.io.IOException ioex) {
            
            System.err.print(ioex.getMessage());
        }
    }
    
    public ArrayList<String> search(String term){
        
        ArrayList<String> searchResult=new ArrayList<>(100);
        
        try {
            try (Connection c = DriverManager.getConnection("jdbc:mysql://10.100.54.74:3306/taxadb","root","QOVmdz56435")) {
                
                Statement s=c.createStatement();
                ResultSet r;
                
                term=term.toLowerCase();
                
                if(term.charAt(0)=='a')
                    r=s.executeQuery("SELECT * FROM namea WHERE taxaname LIKE '"+term+"%' LIMIT 100");
                else if(term.charAt(0)=='b' || term.charAt(0)=='c')
                    r=s.executeQuery("SELECT * FROM namebc WHERE taxaname LIKE '"+term+"%' LIMIT 100");
                else if(term.charAt(0)>='d' && term.charAt(0)<='g')
                    r=s.executeQuery("SELECT * FROM namedg WHERE taxaname LIKE '"+term+"%' LIMIT 100");
                else if(term.charAt(0)>='h' && term.charAt(0)<='k')
                    r=s.executeQuery("SELECT * FROM namehk WHERE taxaname LIKE '"+term+"%' LIMIT 100");
                else if(term.charAt(0)>='l' && term.charAt(0)<='o')
                    r=s.executeQuery("SELECT * FROM namelo WHERE taxaname LIKE '"+term+"%' LIMIT 100");
                else if(term.charAt(0)=='p' || term.charAt(0)=='q')
                    r=s.executeQuery("SELECT * FROM namepq WHERE taxaname LIKE '"+term+"%' LIMIT 100");
                else if(term.charAt(0)=='r' || term.charAt(0)=='s')
                    r=s.executeQuery("SELECT * FROM namers WHERE taxaname LIKE '"+term+"%' LIMIT 100");
                else
                    r=s.executeQuery("SELECT * FROM nametz WHERE taxaname LIKE '"+term+"%' LIMIT 100");
                
                while(r.next())
                    searchResult.add(r.getString("taxaname"));
                
                searchResult.sort(new StringComparator());
            }
        } catch (SQLException ex) {
            
            System.err.println("Connection fail");
        }
        
        return searchResult;
    }
    
    //  Method to extract the header from the HTML response
    private static String extractHeader(String html) {
        // Define the pattern to match the header
        Pattern pattern = Pattern.compile("<h1>(.*?)</h1>");
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            // Extract the header from the matched group
            return matcher.group(1).trim();
        } else {
            return "";
        }
    }
    
    public void getFileByAccessionNumber(String accessionNumber) throws UnsupportedEncodingException, MalformedURLException, IOException{
        
        String request = "https://www.ncbi.nlm.nih.gov/nuccore/"+accessionNumber;
        
        String header="";
        
        URL url;
        HttpURLConnection connection;
        BufferedReader reader;
        String text="";
        
        while(!header.contains(organism)){
            
            // Make the HTTP request to get the HTML page
            url = new URL(request);
            connection = (HttpURLConnection) url.openConnection();
            
            connection.setRequestMethod("GET");
            
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            
            while (reader.readLine()!= null) {
                text+=reader.readLine();
            }
            
            reader.close();
            connection.disconnect();
            
            header = extractHeader(text);
            
            text= ">"+accessionNumber+" "+ header +"\n";
        }
        
        request = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=nuccore&id="+accessionNumber+"&rettype=fasta&retmode=text";
        
        // Make the HTTP request
        
        url = new URL(request);
        connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        
        // Get the response
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
        while (reader.readLine()!= null) {
            text+=reader.readLine();
        }
        
        reader.close();
        
        // Output the response
        MISAInput=text;
        
        // Close the connection
        connection.disconnect();
    }
    
    public void modifyIni(){
        
        try {
            // Open the file in read-write mode
            RandomAccessFile file = new RandomAccessFile(FacesContext.getCurrentInstance().getExternalContext().getRealPath(File.separator+"resources"+File.separator+"Misa"+File.separator+"misa.ini"), "rw");
            StringBuilder content = new StringBuilder();
            
            String line;
            while ((line = file.readLine()) != null) {
                // Check if line starts with "GFF"
                if (line.startsWith("GFF:")) {
                    break;
                } else if (line.startsWith("definition(unit_size,min_repeats):")) {
                    // Modify the parameter if it matches
                    line = "definition(unit_size,min_repeats): 1-"+min_mono+" 2-"+min_di+" 3-"+min_three+" 4-"+min_tetra+" 5-"+min_pent+" 6-"+min_hexa;
                }
                content.append(line).append("\n");
            }
            
            // Move the file pointer to the beginning of the file
            file.seek(0);
            // Write modified content back to the file
            file.writeBytes(content.toString());
            // Truncate the file to the length of the new content
            file.setLength(content.length());
            // Close the file
            file.close();
            
            System.out.println("File modified successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
    
    public String doNavigation() throws IOException, InterruptedException{
        
        if((uploadedFile!=null && !MISAInput.equals("")) || organism==null)
            return "error";
        else if (uploadedFile!=null || !MISAInput.equals("")) {
            
            String filename;
            String basename;
            String extension;
            
            if(uploadedFile!=null){
                filename = FilenameUtils.getName(uploadedFile.getFileName());
                basename = FilenameUtils.getBaseName(filename) + "_";
                extension = "." + FilenameUtils.getExtension(filename);
            }
            else{
                filename="MISAImput";
                basename =filename + "_";
                extension=".fasta";
            }
            
            File file=null;
            try {
                
                file = File.createTempFile(basename, extension,new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(File.separator+"resources"+File.separator+"tempfiles"+File.separator)));
                
            } catch (IOException ex) {
            }
            
            if(uploadedFile!=null){
                
                InputStream input=null;
                try {
                    input = uploadedFile.getInputStream();
                } catch (IOException ex) {
                }
                
                FileOutputStream output=null;
                try {
                    output = new FileOutputStream(file);
                } catch (FileNotFoundException ex) {
                }
                
                try {
                    try {
                        IOUtils.copy(input, output);
                    } catch (IOException ex) {
                    }
                } finally {
                    IOUtils.closeQuietly(input);
                    IOUtils.closeQuietly(output);
                }
            }
            else{
                
                MISAInput = MISAInput.trim();
                
                if((int)MISAInput.toUpperCase().charAt(0)>=65 &&
                        (int)MISAInput.toUpperCase().charAt(0)<=90 &&
                        (((int)MISAInput.toUpperCase().charAt(1)>=65 &&
                        (int)MISAInput.toUpperCase().charAt(1)<=90) ||
                        ((int)MISAInput.toUpperCase().charAt(1)>=48 &&
                        (int)MISAInput.toUpperCase().charAt(1)<=57)) &&
                        ((int)MISAInput.toUpperCase().charAt(MISAInput.length()-1)>=48 &&
                        (int)MISAInput.toUpperCase().charAt(MISAInput.length()-1)<=57)){
                    
                    try {
                        
                        getFileByAccessionNumber(MISAInput);
                    } catch (IOException ex) {
                        return "error";
                    }
                }
                else if(MISAInput.charAt(0)!='>')
                    return "error";
                
                FileWriter fw = new FileWriter(file);
                fw.write(MISAInput);
            }
            
            String[] command = new String[]{FacesContext.getCurrentInstance().getExternalContext().getRealPath(File.separator+"resources"+File.separator+"Misa"+File.separator+"misa.pl"), file.getAbsolutePath()};
            
            modifyIni();
            
            ArrayList<String> toProcess=new ArrayList();
            
            try {
                
                ProcessBuilder builder = new ProcessBuilder("perl", command[0], command[1]);
                
                builder.directory(new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(File.separator+"resources"+File.separator+"Misa")));
                
                // Redirect error stream to /dev/null or NUL
                //builder.redirectError(ProcessBuilder.Redirect.to(new File("/dev/null"))); // On Unix-like systems
                builder.redirectError(ProcessBuilder.Redirect.to(new File("NUL"))); // On Windows
                
                Process process = builder.start();
                
                // Wait for the process to complete
                int exitCode = process.waitFor();
                System.out.println("Process exited with code: " + exitCode);
                
                Map<String, String> sequences=MISAParser.readFastaFile(command[1]);
                results=MISAParser.processFiles(file.getParent()+File.separator+file.getName()+".misa", sequences);
                
                toProcess=new ArrayList<>(Arrays.asList(results.split("\n")));
                
                if(results.isEmpty())
                {
                    clear();
                    return "error";
                }
                
                clear();
                
            } catch (IOException e) {
                System.err.println("misa.pl problem");
            }
            
            List<String> seq_array = new ArrayList<>();
            String seq="";
            int count = 1;
            Iterator<String> s=toProcess.iterator();
            
            seq=s.next()+"\n";
            while (s.hasNext()){
                count++;
                seq += s.next()+"\n";
                if (count == 200) {seq_array.add(seq); seq = ""; count=0;}
            }
            if (!"".equals(seq)) seq_array.add(seq);
            
            specificResults=new ArrayList();
            genericResults=new ArrayList();
            
            for (String seq1 : seq_array) {
                try {
                    
                    String req = "";
                    
                    while ("".equals(req = get_RID_RTOE(seq1)))
                        sleep(100);
                    
                    while ((hitTable = getHitTable(req)) == null)
                        sleep(100);
                    
                    specificResults.addAll(Polymorphism.evaluate(seq1, hitTable, identityPercent, coveragePercent));
                    
                    genericResults.addAll(Polymorphism.evaluate(seq1, specificResults));
                    
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            }
            
            return "result";
        }
        else
            return "error";
    }
    
    public String get_RID_RTOE(String seq) throws Exception{
        
        String url2 = "";
        boolean rid = false;
        String data = constructRequest(seq,"\""+organism.trim()+"\"[organism]",
                Integer.toString(maxTargetSequences),
                Integer.toString(expectThreshold));
        
        try{
            // Send data
            URL url = new URL("https://blast.ncbi.nlm.nih.gov/Blast.cgi");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            BufferedReader rd;
            String urlResults;
            String eta;
            
            try (OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())) {
                
                wr.write(data);
                wr.flush();
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                urlResults = url + "?RID=";
                String line;
                eta = "10";
                
                while ((line = rd.readLine()) != null){
                    
                    int index;
                    if ((index = line.indexOf("RID =")) > -1){
                        
                        line = line.substring(index + 5).trim();
                        urlResults = urlResults.concat(line);
                        rid = true;
                        
                        if (line.equals("")){
                            sleep(100);
                            System.err.println(urlResults + "Blast search error");
                            
                        }
                    }
                    else if ((index = line.indexOf("RTOE =")) > -1){
                        eta = line.substring(index + 6).trim();
                        System.out.println(line);
                        sleep(100);
                    }
                    
                    sleep(100);
                }
            }
            rd.close();
            int waitTime = Integer.parseInt(eta);
            Thread.sleep(waitTime * 1000);
            if (rid){
                url2 = urlResults + "&RESULTS_FILE=on&OLD_BLAST=false" + "&FORMAT_TYPE=Text&FORMAT_OBJECT=Alignment&ALIGNMENT_VIEW=Tabular&CMD=Get";
                System.out.println("External request: " + url2);
            }
            else url2="";
        }
        catch (HeadlessException | IOException | InterruptedException | NumberFormatException e){
            System.err.println(e.getMessage());
        }
        return url2;
    }
    
    public ArrayList<HtBLASTParser_Unit> getHitTable(String url) throws MalformedURLException, IOException {
        
        ArrayList<HtBLASTParser_Unit> units=new ArrayList<>();
        ArrayList<String> lecture=new ArrayList<>();
        List<String> check=new ArrayList<>();
        List<String> qav=new ArrayList<>();
        List<String> stream =new ArrayList<>();
        List<String> headers=new ArrayList<>();
        boolean rend_excp= false;
        BufferedReader rd=null;
        
        try {
            URL url2 = new URL(url);
            URLConnection conn = url2.openConnection();
            conn.setConnectTimeout(10000);
            conn.setDoOutput(true);
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
        }catch(IOException e){
            
            System.err.println(e.getMessage());
        }
        
        if (rend_excp) {units = null; return units;}
        
        String s;
        
        while((s=rd.readLine()) != null){
            stream.add(s);
        }
        
        rd.close();
        
        String checkHitTable = stream.toString();
        
        if(!checkHitTable.contains("subject acc.ver") ||
                !checkHitTable.contains("% identity") ||
                !checkHitTable.contains("q. start") ||
                !checkHitTable.contains("q. end") ||
                !checkHitTable.contains("s. start") ||
                !checkHitTable.contains("s. end") ||
                !checkHitTable.contains("evalue")){
            return null;
        }
        
        for(String s1:stream){
            if(s1.contains("#"))
            {
                if(s1.startsWith("# Query:") && !check.contains(s1))
                    check.add(s1);
                if( s1.equals("# blastn") && headers.size()==5)
                {
                    lecture.add(headers.get(2));
                    headers.clear();
                    headers.add(s1);
                }
                else
                    headers.add(s1);
            }
            else if(!s1.equals("") && !s1.contains("#"))
            {
                lecture.add(s1);
                headers.clear();
            }
        }
        for(String value:lecture)
        {
            if(value.startsWith("# Query:"))
                units.add(new HtBLASTParser_Unit(value.substring(9),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null));
            else{
                
                String[] lectemp=value.split("\t");
                
                units.add(new HtBLASTParser_Unit(lectemp[0],
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
        
        for (HtBLASTParser_Unit u: units)
        {
            if(!qav.contains(u.getQuery_acc_ver()))
                qav.add(u.getQuery_acc_ver());
        }
        
        if(qav.size()<check.size())
            units.add(new HtBLASTParser_Unit(check.get(check.size()-1).substring(9),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null));
        
        return units;
    }
    
    public void resetForm(){
        
        MISAInput="";
        organism="";
        min_mono=10;
        min_di=6;
        min_three=5;
        min_tetra=5;
        min_pent=5;
        min_hexa=5;
        maxTargetSequences=100;
        expectThreshold=30;
        identityPercent=90;
        coveragePercent=90;
    }
    
    public void clear(){
        
        File file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(File.separator+"resources"+File.separator+"tempfiles"));
        
        File[] listToDelete=file.listFiles();
        
        for (File f: listToDelete) {
            
            System.out.println("Deleting: " + f.getName());
            f.delete();
        }
    }
    
    private static String addData(String data, String key, String value)throws UnsupportedEncodingException  {
        
        data += "&" +URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8");
        return data;
    }
    
    private static String addUnits(String data){
        
        String[] lecture=data.split("\n");
        String result="";
        SSr query=null;
        String temp=null;
        
        for (int i = 0, j=1; i < lecture.length; i+=2,j+=2) {
            
            query=new SSr(lecture[i],lecture[j]);
            result+=lecture[i]+"\n";
            
            if(lecture[j].length()<60){
                
                temp=query.getF5()+query.getPattern().toLowerCase();
                
                do{
                    
                    temp+=query.getPattern().toLowerCase();
                    
                }while(temp.length()<40);
                
                temp+=query.getF3();
                
                result+=temp+"\n";
            }
            else
                result+=lecture[j]+"\n";
        }
        
        return result;
    }
    
    public static String constructRequest( String residues, String e_query, String hitListSize, String expect) throws UnsupportedEncodingException, Exception{
        
        String request = URLEncoder.encode("CMD", "UTF-8") + "=" + URLEncoder.encode("Put", "UTF-8");
        request = addData(request, "QUERY_BELIEVE_DEFLINE", "false");
        request = addData(request, "QUERY", addUnits(residues));
        request = addData(request, "DATABASE", "nr");
        request = addData(request, "ENTREZ_QUERY", e_query);
        request = addData(request, "LCASE_MASK", "true");
        //Number of hits to keep 500
        request = addData(request, "HITLIST_SIZE", hitListSize);
        // Filter: String[]{"None", "Low Complexity", "Human Repeats", "Masked" }
        request = addData(request, "FILTER", "F");
        //Expect value 10.0
        if(Float.parseFloat(expect)<30)
            throw new Exception("expect must be >= 30");
        else
            request = addData(request, "EXPECT", expect);
        //HTML, XML, Text, Tabular, etc.
        request = addData(request, "FORMAT_TYPE", "Tabular");
        request = addData(request, "PROGRAM", "blastn");
        request = addData(request, "CLIENT", "web");
        request = addData(request, "BLAST_PROGRAM", "blastn");
        return request;
    }
    
    public UploadedFile getUploadedFile() {
        
        return uploadedFile;
    }
    
    public String getResults() {
        return results;
    }
    
    public int getMin_mono() {
        return min_mono;
    }
    
    public void setMin_mono(int min_mono) {
        this.min_mono = min_mono;
    }
    
    public int getMin_di() {
        return min_di;
    }
    
    public void setMin_di(int min_di) {
        this.min_di = min_di;
    }
    
    public int getMin_three() {
        return min_three;
    }
    
    public void setMin_three(int min_three) {
        this.min_three = min_three;
    }
    
    public int getMin_tetra() {
        return min_tetra;
    }
    
    public void setMin_tetra(int min_tetra) {
        this.min_tetra = min_tetra;
    }
    
    public int getMin_pent() {
        return min_pent;
    }
    
    public void setMin_pent(int min_pent) {
        this.min_pent = min_pent;
    }
    
    public int getMin_hexa() {
        return min_hexa;
    }
    
    public void setMin_hexa(int min_hexa) {
        this.min_hexa = min_hexa;
    }
    
    public String getOrganism() {
        return organism;
    }
    
    public int getMaxTargetSequences() {
        return maxTargetSequences;
    }
    
    public int getExpectThreshold() {
        return expectThreshold;
    }
    
    public float getIdentityPercent() {
        return identityPercent;
    }
    
    public float getCoveragePercent() {
        return coveragePercent;
    }
    
    public ArrayList<HtBLASTParser_Unit> getHitTable() {
        return hitTable;
    }
    
    public String getMISAInput() {
        return MISAInput;
    }
    
    public void setMISAInput(String MISAInput) {
        this.MISAInput = MISAInput;
    }
    
    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    
    public void setHitTable(ArrayList<HtBLASTParser_Unit> hitTable) {
        this.hitTable = hitTable;
    }
    
    public void setOrganism(String Organism) {
        this.organism = Organism;
    }
    
    public void setMaxTargetSequences(int MaxTargetSequences) {
        this.maxTargetSequences = MaxTargetSequences;
    }
    
    public void setExpectThreshold(int ExpectThreshold) {
        this.expectThreshold = ExpectThreshold;
    }
    
    public void setIdentityPercent(float IdentityPercent) {
        this.identityPercent = IdentityPercent;
    }
    
    public void setCoveragePercent(float CoveragePercent) {
        this.coveragePercent = CoveragePercent;
    }
    
    public void setSpecificResults(ArrayList<Specific_Result> specificResults) {
        this.specificResults = specificResults;
    }
    
    public void setGenericResults(ArrayList<Generic_Result> genericResults) {
        this.genericResults = genericResults;
    }
    
    public ArrayList<Specific_Result> getSpecificResults() {
        return specificResults;
    }
    
    public ArrayList<Generic_Result> getGenericResults() {
        return genericResults;
    }
    
    public String getFilename(){
        
        String filename;
        
        if(!MISAInput.equals(""))
            filename=MISAInput.substring(1,MISAInput.indexOf("\n"));
        else
            filename=uploadedFile.getFileName();
        
        return filename;
    }
}
