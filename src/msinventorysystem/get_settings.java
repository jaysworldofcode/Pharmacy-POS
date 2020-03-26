package msinventorysystem;

import java.io.*;
import java.util.ArrayList;

public class get_settings {
    
    ArrayList<String> settings = new ArrayList<String>();
    String dataToString ="";
    
    public get_settings(){
        retrieveText();
    }
    void retrieveText(){
        try {
            File f = new File("run.settings");
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";

            while ((readLine = b.readLine()) != null) {
                settings.add(readLine.replace("[Settings[>]]", "").trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getSettings(int index){
        return settings.get(index);
    }
    public void setSettings(int index, String updateValue){
        settings.set(index, updateValue);
        setDataToString();
        
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                  new FileOutputStream("run.settings"), "utf-8"));
            writer.write(removeLastChar(dataToString));
        } catch (IOException ex) {
            // Report
        } finally {
           try {writer.close();} catch (Exception ex) {/*ignore*/}
        }
    }
    void setDataToString(){
        for(int text=0;text<settings.size();text++)
            dataToString = dataToString+"[Settings[>]]"+settings.get(text).trim()+"\n";
    }
    String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }
    public static void main(String [] args){
        get_settings settings = new get_settings();
//        settings.setSettings(0,"Hello pajamas");
    }
}