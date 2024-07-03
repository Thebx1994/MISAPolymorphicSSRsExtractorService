/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package other_resources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *A class that write objects that a have toString method in a file.
 *
 * @author Alejandro
 */
public class Writer {
    private boolean has_head;
    private String Filter;
    private ArrayList<?> Items;
    private String Dirname;
    private String Filepath;
//    private int Count;
    
    public Writer() {
        this.has_head = true;
        this.Filter = null;
        this.Items = null;
        this.Dirname = null;
        this.Filepath = null;
        
    }
    
    /**
     *Write the items of the ArrayList while have a toString method.
     *
     * @param head boolean attribute to write or not header.
     * @param filter Represent the meaning of the information write in the file.
     * @param items The objects to write.
     * @param dirname The directory to write the file.
     * @param filepath The file to write.
     */
    public void write(boolean head, String filter, ArrayList<?> items, String dirname, String filepath){
        this.has_head = head;
        this.Filter = filter;
        this.Items = items;
        this.Dirname = dirname;
        this.Filepath = filepath;
        
        
        File dir=new File(this.Dirname);
        
        if(!dir.exists())
            dir.mkdir();
        
        if(dir.exists())
        {
            try (FileWriter fw = new FileWriter(this.Filepath, true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter exit = new PrintWriter(bw)) {
                
                if (head)  exit.println(this.Filter);
                
                for(Object o : Items)
                    exit.println(o.toString());
            }
            catch(java.io.IOException ioex) { }
        }
    }
    
    /**
     *Gets the filter write in the file.
     *
     * @return A string that represent the filter.
     */
    public String getFilter() {
        return Filter;
    }
    
    /**
     *Gets the items write in the file.
     *
     * @return A ArrayList that contains the Items to write.
     */
    public ArrayList<?> getItems() {
        return Items;
    }
    
    /**
     *Gets the name of the directory that contain the file wrote.
     *
     * @return A string that represent the name of the directory.
     */
    public String getDirname() {
        return Dirname;
    }
    
    /**
     *Gets the direction of the file that contain the items wrote.
     *
     * @return A string that represent the path of the file.
     */
    public String getFilepath() {
        return Filepath;
    }
    
    
}
