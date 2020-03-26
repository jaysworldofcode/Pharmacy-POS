package msinventorysystem;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import javax.swing.JOptionPane;

public class ChangePrinterIndex{
    //Template for the settings 'Set File Printer[indexHere]'    
    ChangePrinterIndex(int index){
     try{
        Writer writer = null;
        writer = new BufferedWriter(new OutputStreamWriter(
        new FileOutputStream("Printer.settings"), "utf-8"));
        writer.write("Set File Printer["+index+"]");
        writer.close();
     }catch(Exception ex){
         JOptionPane.showMessageDialog(null,ex);
     }
   }
}