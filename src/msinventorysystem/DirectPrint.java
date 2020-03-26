package msinventorysystem;

import java.awt.*;
import java.awt.print.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.print.PrintService;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;

public class DirectPrint implements Printable {

    private PrintService[] printService;
    
    String transactionNumber,date,cashier,total,printerIndex;
    
    ArrayList<String> quantity = new ArrayList<String>();
    ArrayList<String> item = new ArrayList<String>();
    ArrayList<String> price = new ArrayList<String>();
    ArrayList<String> extendedPrice = new ArrayList<String>();
    ArrayList<String> preparation = new ArrayList<String>();

    ArrayList<String> list_of_data = new ArrayList<String>();
    
    public DirectPrint() {
        this.printService = PrinterJob.lookupPrintServices();
        String line;
        try{
        BufferedReader in = new BufferedReader(new FileReader("Printer.settings"));
            while((line = in.readLine()) != null)
            {
                list_of_data.add(line);
            }
            in.close();
        }catch(Exception ex){
            System.out.println(ex);
        }
        System.out.println(list_of_data.get(0));
        String[] split = list_of_data.get(0).split(" ");
        printerIndex = split[2].replace("Printer[", "").replace("]", "");
    }
    
    public void setDetails(String transactionNumber, String date, String cashier,
            String total){
        this.transactionNumber = transactionNumber;
        this.date = date;
        this.cashier = cashier;
        this.total = total;
    }
    public void addData(String quantity, String item, String price,
            String extendedPrice, String preparation){
        this.quantity.add(quantity);
        this.item.add(item);
        this.price.add(price);
        this.extendedPrice.add(extendedPrice);
        this.preparation.add(preparation);
    }
    public void addDataTest(){
        System.out.println("/* Add test data */");
        quantity.add("2");
        item.add("Bioflu");
        price.add("10.00");
        extendedPrice.add("12.00");
        preparation.add("capsule/500 mcg");
        
        quantity.add("2");
        item.add("Tester test");
        price.add("10.00");
        extendedPrice.add("12.00");
        preparation.add("capsule/500 mcg");
        
        quantity.add("2");
        item.add("asdasd");
        price.add("10.00");
        extendedPrice.add("12.00");
        preparation.add("capsule/500 mcg");
        
        quantity.add("2");
        item.add("zxczxc");
        price.add("10.00");
        extendedPrice.add("12.00");
        preparation.add("capsule/500 mcg");
        
        quantity.add("2");
        item.add("z");
        price.add("10.00");
        extendedPrice.add("12.00");
        preparation.add("capsule/500 mcg");
    }
    
    public void printString() {
        System.out.println(extendedPrice);
        System.out.println("/* Print */");
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        aset.add(OrientationRequested.PORTRAIT);
        aset.add(MediaSizeName.INVOICE);
        aset.add(new PageRanges(1, 1));
        aset.add(new Copies(1));

        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable(this);

        try {
            printJob.setPrintService(printService[Integer.parseInt(printerIndex)]);
            System.out.println(printService[Integer.parseInt(printerIndex)].getName());
            //index of installed printers on you system
            //not sure if default-printer is always '0'
            printJob.print(aset);
        } catch (PrinterException err) {
            System.err.println(err);
        }
    }

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) {
        System.out.println("/* Print in progress */");
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(pf.getImageableX()-20, pf.getImageableY());
        
        //Info
        g2.translate(0, 30);
        g2.setFont(new Font("Courier",Font.BOLD,10));
        g2.drawString("                 SALES INVOICE", 12, 12);             
        g2.translate(0, 20);
        g2.setFont(new Font("Courier",Font.PLAIN,7));
        g2.drawString("         TRANSACTION #: "+transactionNumber, 12, 12); // ñ Peñero, Aaarns Cute
        g2.translate(0, 11);
        g2.drawString("         DATE: "+date, 12, 12);             
        g2.translate(0, 11);
        g2.drawString("         SERVED BY: "+cashier, 12, 12);             
        g2.translate(0, 11);
        g2.setFont(new Font("Courier",Font.BOLD,10));
        g2.drawString("_______________________________________________________", 12, 12);   
        g2.translate(0, 11);
        g2.setFont(new Font("Courier",Font.PLAIN,7));          
        g2.drawString("        QTY    ITEM     PRICE     EXTEND. PRICE", 12, 12);  
        g2.translate(0, 5);
        g2.setFont(new Font("Courier",Font.PLAIN,10));          
        g2.drawString("_______________________________________________________", 12, 12);   
        g2.translate(0, 11);

        String setQuantity = null;
        String setItem = null;
        String setPrice = null;
        String setExtendedPrice = null;
        
        g2.setFont(new Font("Courier",Font.PLAIN,7));  
        for(int x=0;x<quantity.size();x++){
            boolean set = false;
                setQuantity = quantity.get(x);
                for(int c=0;c<(4-quantity.get(0).length());c++){
                    setQuantity=setQuantity+" ";
                }
                System.out.println("Quantity size to be add is: "+setQuantity.length());
                setItem = item.get(x);
                for(int c=0;c<(20-item.get(x).length());c++){
                    setItem=setItem+" ";
                }
                System.out.println("Item size to be add is: "+setItem.length());
                setPrice = price.get(x);
                for(int c=0;c<(15-price.get(x).length());c++){
                    setPrice=setPrice+" ";
                }
                for(int c=0;c<(15-extendedPrice.get(x).length());c++){
                    setExtendedPrice=setExtendedPrice+" ";
                }                
                g2.drawString("        "+setQuantity+"    "+setItem+"  "+setPrice+"  "+extendedPrice.get(x), 12, 12);  
                g2.translate(0, 11);
                g2.drawString("                "+preparation.get(x), 12, 12);  
                g2.translate(0, 8);
        }
        
        //Payment Period
        g2.translate(0, 20);
        g2.setFont(new Font("Courier",Font.PLAIN,7));
        g2.translate(0, 20);
        g2.drawString("                      TOTAL: "+total, 12, 12);     
        g2.translate(0, 25);
        g2.drawString("        PAYMENT TYPE:__________________________", 12, 12);     
        g2.translate(0, 11);
        g2.drawString("        CASHIER'S SIGNATURE:___________________", 12, 12);     
        g2.translate(0, 11);
        g2.setFont(new Font("Courier",Font.PLAIN,10));
        g2.drawString("______________________________________________________", 12, 12); 
//        g2.setFont(new Font("Century Gothic", Font.BOLD, 7));
        g2.setFont(new Font("Courier",Font.PLAIN,7));
        g2.translate(0, 20);
        g2.drawString("        NAME:__________________________________", 12, 12);     
        g2.translate(0, 11);
        g2.drawString("        ADDRESS:_______________________________", 12, 12);     
        g2.translate(0, 11);
        g2.drawString("        OSCA/PWD ID NO:________________________", 12, 12);     
        g2.translate(0, 11);
        g2.setFont(new Font("Century Gothic", Font.BOLD, 7));
        g2.drawString(" *******************************************", 12, 12);  
        
        return PAGE_EXISTS;
    }
    
    public static void main(String[] args) {
            DirectPrint dp = new DirectPrint();
            dp.addDataTest();
            dp.printString();
    }
    
}
