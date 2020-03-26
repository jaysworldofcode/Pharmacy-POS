package DataBase;

import DataHolder.DataHolder;
import Frames.out_of_stock_list;
import LoginPanel.login_jp;
import Panels.List_Panel;
import Panels.Logs_Panel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JOptionPane;
import msinventorysystem.ConvertExpiredDateFormatToZonedDateTimeFormat;
import msinventorysystem.get_settings;

public class SQLiteDB {
    //SQLite
    static Connection connLITE;
    static String URL = "jdbc:sqlite:Data/DB_LIST/PRODUCT";
    private static SQLiteDB instance = null;    
    //SET CLASSES
    DataHolder dh = DataHolder.getInstance();
    public static SQLiteDB getInstance() {
      if(instance == null) {
         instance = new SQLiteDB();
      }
      return instance;
    }
    SQLiteDB(){
                System.out.println("SQLiteDB!");
        //MYSQLite DATABASE
        try{
          Class.forName("org.sqlite.JDBC");
          connLITE = (Connection) DriverManager.getConnection(URL);
          System.out.println("Successfully connected to sqlite database!");
        } catch ( Exception e ){
            JOptionPane.showMessageDialog(null,e);
        }
        createTable();
        retrieveData();
        retrieveData_products();
        retrieveLogs();
        retrieveExpired();
        expired_scanner();
        retrieveAccounts();
        setTable();
        retrieveDiscount_data();
        retrieveOut_of_stock_data();
        setRetrieve_to_be_remove_cart();
    }
    public Connection getConn(){
        return connLITE;
    }
    public String getURL(){
        return URL;
    }
    String[][] OutOfStockDataFromTableUI;
    public void add_element(String[] element){
        OutOfStockDataFromTableUI = Arrays.copyOf(OutOfStockDataFromTableUI, 
        OutOfStockDataFromTableUI.length +1);
        OutOfStockDataFromTableUI[OutOfStockDataFromTableUI.length - 1] = element;
    }
    public String[][] retrieveOutOfStockList(String[][] OutOfStack){
                OutOfStockDataFromTableUI = OutOfStack;
               try
               {
                 String query = "SELECT * FROM TBL_OUT_OF_STOCK";
                 Statement st = (Statement) connLITE.createStatement();
                 ResultSet rs = (ResultSet) st.executeQuery(query);

                 while (rs.next())
                 {
                     if(rs.getString("TYPE").equals("out_of_stock")){
                        add_element(new String[]{
                           "➤",
                           String.valueOf(rs.getInt("ID")),
                           rs.getString("TYPE"),
                           rs.getString("PRODUCT_NAME"),
                           rs.getString("BRAND_NAME"),
                           rs.getString("PRICE"),
                           rs.getString("STRENGTH"),
                           rs.getString("PURCHASED_FROM"),
                           rs.getString("DISK_CHECKER"),
                           rs.getString("PREPARATION")});
                     }
                  }
               }
               catch (Exception e)
               {
                   JOptionPane.showMessageDialog(null,e+" discount");
               }
        return OutOfStockDataFromTableUI;
    }
    public String[][] retrieveExpiredStockList(String[][] OutOfStack){
                OutOfStockDataFromTableUI = OutOfStack;
               try
               {
                 String query = "SELECT * FROM TBL_EXPIRED_LIST";
                 Statement st = (Statement) connLITE.createStatement();
                 ResultSet rs = (ResultSet) st.executeQuery(query);

                 while (rs.next())
                 {
                     add_element(new String[]{
                        "➤",
                        String.valueOf(rs.getInt("ID")),
                        rs.getString("PRODUCT_NAME"),
                        rs.getString("QUANTITY"),
                        rs.getString("PRICE"),
                        rs.getString("DATE_PURCHASE"),
                        rs.getString("EXPIRATION_DATE"),
                        rs.getString("PURCHASE_FROM"),
                        rs.getString("STRENGTH"),
                        rs.getString("PREPARATION")});
                 }
               }
               catch (Exception e)
               {
                   JOptionPane.showMessageDialog(null,e+" discount");
               }
        return OutOfStockDataFromTableUI;
    }
    public void setRetrieve_to_be_remove_cart(){
               try
               {
                   
                 String query = "SELECT * FROM CART_LIST";
                 Statement st = (Statement) connLITE.createStatement();
                 ResultSet rs = (ResultSet) st.executeQuery(query);

                 while (rs.next())
                 {
                     dh.set_Remove_sale(rs.getString("PRODUCT_ID"),rs.getString("QUANTITY"));
                 }
               }
               catch (Exception e)
               {
                   JOptionPane.showMessageDialog(null,e+" discount");
               }
//               dh.setTable();
    }
        public void retrieveDiscount_data(){

               Logs_Panel lp = Logs_Panel.getInstance();
               lp.clearDiscount_filter();
                 
               try
               {
                   
                 String query = "SELECT * FROM DISCOUNT_LOGS";
                 Statement st = (Statement) connLITE.createStatement();
                 ResultSet rs = (ResultSet) st.executeQuery(query);

                 while (rs.next())
                 {
                     lp.discountFilter(
                      rs.getString("ID"),
                      rs.getString("PRODUCT_NAME"),
                      rs.getString("DATE"),
                      rs.getString("QUANTITY"),
                      rs.getString("SELLING_PRICE"),
                      rs.getString("DISCOUNTED_PRICE"),
                      rs.getString("CASHIER"),
                      rs.getString("STRENGTH"),
                      rs.getString("PREPARATION"),
                      rs.getString("TRANSACTION_ID"),
                      rs.getString("UNIT_PRICE")
                     );
                 }
               }
               catch (Exception e)
               {
                   JOptionPane.showMessageDialog(null,e+" discount");
               }   
               dh.print();
    }
    public void iconChecker(){
                List_Panel lp = List_Panel.getInstance();
                lp.setIcon_stock(checkFor_new());
                     System.out.println("Check for new is "+checkFor_new()+" !");
    }
    public boolean checkFor_new(){
        boolean set = false;
        out_of_stock_list o = out_of_stock_list.getInstance();
        o.removeAll_data();
        try
           {
             String query = "SELECT * FROM TBL_OUT_OF_STOCK";

             Statement st = (Statement) connLITE.createStatement();

             ResultSet rs = (ResultSet) st.executeQuery(query);

             while (rs.next())
             {
                 o.setRow(rs.getString("ID"),rs.getString("TYPE"),rs.getString("PRODUCT_NAME"),
                 rs.getString("BRAND_NAME"),rs.getString("PRICE"),rs.getString("STRENGTH"),
                 rs.getString("PURCHASED_FROM"),rs.getString("DISK_CHECKER"),rs.getString("PREPARATION"));
                 if(rs.getString("DISK_CHECKER").equalsIgnoreCase("true")){
                     set = true;
                 }
             }
           }
           catch (Exception e)
           {
               JOptionPane.showMessageDialog(null,e);
           }   
        return set;
    }
    public void retrieveOut_of_stock_data(){
        out_of_stock_list o = out_of_stock_list.getInstance();
        o.removeAll_data();
        try
           {
             String query = "SELECT * FROM TBL_OUT_OF_STOCK";

             Statement st = (Statement) connLITE.createStatement();

             ResultSet rs = (ResultSet) st.executeQuery(query);

             while (rs.next())
             {
                 o.setRow(rs.getString("ID"),rs.getString("TYPE"),rs.getString("PRODUCT_NAME"),
                         rs.getString("BRAND_NAME"),rs.getString("PRICE"),rs.getString("STRENGTH"),
                 rs.getString("PURCHASED_FROM"),rs.getString("DISK_CHECKER"),rs.getString("PREPARATION"));
                            removeBeyondData(
                                    rs.getString("DATE_OUT_OF_STOCK"),
                                    rs.getString("ID"),
                                    false
                            );
             }
           }
           catch (Exception e)
           {
               JOptionPane.showMessageDialog(null,e);
           }   
    }
    public void EXPIRED_products(String product_name,String quantity,
            String price,String date_purchase,String expiration_date,
            String purchase_from, String strength, String preparation){
            try{

                  String query = "INSERT INTO TBL_EXPIRED_LIST (PRODUCT_NAME, "
                          + "QUANTITY,PRICE,DATE_PURCHASE,EXPIRATION_DATE,"
                          + "PURCHASE_FROM,STRENGTH,PREPARATION) values (?,?,?,?,?,?,?,?)";

                  // create the mysql insert preparedstatement
                  PreparedStatement preparedStmt = connLITE.prepareStatement(query);
                  preparedStmt.setString (1, product_name);
                  preparedStmt.setString (2, quantity);
                  preparedStmt.setString (3, price);
                  preparedStmt.setString (4, date_purchase);
                  preparedStmt.setString (5, expiration_date);
                  preparedStmt.setString (6, purchase_from);
                  preparedStmt.setString (7, strength);
                  preparedStmt.setString (8, preparation);

                  preparedStmt.execute();

                }catch (Exception e){
             JOptionPane.showMessageDialog(null,e+" EXPIRED_products error");   
             }   
    }
    void delete_expired_products(String id){
                String sql = "DELETE FROM TBL_PRODUCT_LIST WHERE ID = ?";
 
        try {
            PreparedStatement pstmt = connLITE.prepareStatement(sql);
 
            // set the corresponding param
            pstmt.setString(1, id);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void addOut_of_stock(String product_name, String brand_name, String type, String price,
            String strength, String purchased_from, String preparation){
        String sql = "INSERT INTO TBL_OUT_OF_STOCK(PRODUCT_NAME,BRAND_NAME,TYPE,PRICE,STRENGTH,"
                + "PURCHASED_FROM,DISK_CHECKER,PREPARATION,DATE_OUT_OF_STOCK) "
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        Date date = new Date();  
        
        try {
            PreparedStatement pstmt = connLITE.prepareStatement(sql);
            pstmt.setString(1,product_name);
            pstmt.setString(2, brand_name);
            pstmt.setString(3, type);
            pstmt.setString(4, price);
            pstmt.setString(5, strength);
            pstmt.setString(6, purchased_from);
            pstmt.setString(7, "true");
            pstmt.setString(8, preparation);
            pstmt.setString(9, formatter.format(date));
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void expired_scanner(){
        for(int x=0;x<dh.retrieveProduct_size();x++){
            int result = Integer.parseInt(dh.retrieveDays_left(x));
            if(result<=0){
                dh.setTotal_expired();
                
                //Add stock table
                addOut_of_stock(dh.retrieveProduct_name(x),dh.retrieveBrand_name(x),
                        "expire",dh.retrievePrice(x),dh.retrieveUnit(x),dh.retrievePurchase_from(x),dh.retrievePreparation(dh.getIndex()));

                //Add to database
                EXPIRED_products(dh.retrieveProduct_name(x),dh.retieveQuantity(x),
                        dh.retrieveSelling_price(x),dh.retrieveDate_purchase(x),
                        dh.retrieveExpiration_date(x),dh.retrievePurchase_from(x),
                        dh.retrieveUnit(x),dh.retrievePreparation(x));

                //Add to dataHolder
                dh.add_Expired(dh.retrieveProduct_name(x),dh.retieveQuantity(x),
                        dh.retrievePrice(x),dh.retrieveDate_purchase(x),
                        dh.retrieveExpiration_date(x),dh.retrievePurchase_from(x),
                        dh.retrieveID(x),dh.retrieveUnit(x),dh.retrievePreparation(x));
                //Remove to database
                delete_expired_products(dh.retrieveID(x));                    
                //Remove to dataHolder
                dh.setTotal_expired(dh.retrieveProduct_name(x));
                dh.removeExpired_product(dh.retrieveID(x));
            }
        }
    }
public void retrieveUsername(){
    login_jp lp = login_jp.getInstance();
    lp.clearUser_list();
 try
    {
      String query = "SELECT * FROM TBL_ACCOUNTS";

      Statement st = (Statement) connLITE.createStatement();
      
      ResultSet rs = (ResultSet) st.executeQuery(query);
      
      while (rs.next())
      {
        lp.addUser_data(rs.getString("USERNAME"));
      }
    }
    catch (Exception e)
    {
        JOptionPane.showMessageDialog(null,e);
    }   
    dh.print();
}
public void retrieveData_products(){
 try
    {
      String query = "SELECT * FROM tbl_product_list";

      Statement st = (Statement) connLITE.createStatement();
      
      ResultSet rs = (ResultSet) st.executeQuery(query);
      
      while (rs.next())
      {
        dh.addData_product(rs.getString("ID"),rs.getString("PRODUCT_NAME"),
        rs.getString("QUANTITY"),rs.getString("EXPIRATION"), rs.getString("PRICE"),
        rs.getString("SELLING_PRICE"),rs.getString("DATE_PURCHASE"),
        rs.getString("UNIT"), rs.getString("PREPARATION"),rs.getString("PURCHASE_FROM"),
        rs.getString("BRAND_NAME"),rs.getString("SET_LIMIT"));
      }
    }
    catch (Exception e)
    {
        JOptionPane.showMessageDialog(null,e);
    }   
    dh.print();
    }
    public void retrieveLogs(){
                String sql = "SELECT * FROM TBL_LOGS_LIST";
                dh.setClear();
                try {
             Statement stmt  = connLITE.createStatement();
             ResultSet rs    = stmt.executeQuery(sql);
            
            // loop through the result set
            while (rs.next()) {
                dh.addLogs(
                        rs.getString("TRANSACTION_ID"),
                        rs.getInt("ID"),                        
                        rs.getString("TIME"),
                        rs.getString("DATE"),
                        rs.getString("QUANTITY"),
                        rs.getString("SELLING_PRICE"),
                        rs.getString("PAYMENT"),
                        rs.getString("CASHIER"),
                        rs.getString("PRODUCT_NAME"),
                        rs.getString("PRICE"),
                        rs.getString("STRENGTH"),
                        rs.getString("PREPARATION"),
                        rs.getString("IF_DISCOUNT")
                );                    
        System.out.println("Retrieve strength is: "+rs.getString("PREPARATION"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }        
        dh.print();
    }
    void deleteBeyondLimitInExpired(String id){
                String sql = "DELETE FROM TBL_EXPIRED_LIST WHERE ID = ?";
 
        try {
            PreparedStatement pstmt = connLITE.prepareStatement(sql);
 
            // set the corresponding param
            pstmt.setString(1, id);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    void deleteBeyondLimitInOutOfStock(String id){
                String sql = "DELETE FROM TBL_OUT_OF_STOCK WHERE ID = ?";
 
        try {
            PreparedStatement pstmt = connLITE.prepareStatement(sql);
 
            // set the corresponding param
            pstmt.setString(1, id);
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    void removeBeyondData(String date, String id, boolean isExpiredList){
        System.out.println("ID: "+id);
        int limitDays = Integer.parseInt(new get_settings().getSettings(2));
        
            //Format yyyy-MM-dd
        
        if(isExpiredList){
            ZonedDateTime now = ZonedDateTime.now();
            ZonedDateTime oldDate = ZonedDateTime.parse(
                    new ConvertExpiredDateFormatToZonedDateTimeFormat(date)
                        .getConvertedData()+"T08:00:00+00:00[Europe/London]"
            );
            Duration duration = Duration.between(oldDate, now);
            if(limitDays<duration.toDays()){
                deleteBeyondLimitInExpired(id);
            }
        }else{
            ZonedDateTime now = ZonedDateTime.now();
            ZonedDateTime oldDate = ZonedDateTime.parse(
                    date+"T08:00:00+00:00[Europe/London]"
            );
            Duration duration = Duration.between(oldDate, now);
            if(limitDays<duration.toDays()){
                deleteBeyondLimitInOutOfStock(id);
            }
        }
//        System.out.println("Days: " + duration.toDays());
    }
    public void retrieveExpired(){
                String sql = "SELECT * FROM TBL_EXPIRED_LIST";
        dh.clearExpired();
        try {
             Statement stmt  = connLITE.createStatement();
             ResultSet rs    = stmt.executeQuery(sql);
            
            while (rs.next()) {
                    dh.add_Expired(
                        rs.getString("PRODUCT_NAME"),
                        rs.getString("QUANTITY"),
                        rs.getString("PRICE"),
                        rs.getString("DATE_PURCHASE"),
                        rs.getString("EXPIRATION_DATE"),
                        rs.getString("PURCHASE_FROM"),
                        rs.getString("ID"),
                        rs.getString("STRENGTH"),
                        rs.getString("PREPARATION")
                    );
                    
                System.out.println("Expire: "+rs.getString("EXPIRATION_DATE"));
                
                removeBeyondData(
                        rs.getString("EXPIRATION_DATE"),
                        rs.getString("ID"),
                        true
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }        
    }
    public void retrieveAccounts(){
                String sql = "SELECT * FROM TBL_ACCOUNTS";
        
        try {
             Statement stmt  = connLITE.createStatement();
             ResultSet rs    = stmt.executeQuery(sql);
            
            // loop through the result set
            while (rs.next()) {
                dh.add_Account(
                        rs.getInt("ID"),
                        rs.getString("USER_TYPE"),
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD"),
                        rs.getString("SECURITY_QUESTION"),
                        rs.getString("ANSWER")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }        
    }
    public void retrieveData(){
                String sql = "SELECT * FROM CART_LIST";
        
        try {
             Statement stmt  = connLITE.createStatement();
             ResultSet rs    = stmt.executeQuery(sql);
            
            // loop through the result set
            while (rs.next()) {
                dh.addCart_data(
                        rs.getInt("id"),
                        rs.getString("PRODUCT_NAME"),
                        rs.getString("QUANTITY"),
                        rs.getString("PRICE"),
                        rs.getString("PREPARATION"),
                        rs.getString("TOTAL"),
                        rs.getString("PRODUCT_ID"),
                        rs.getString("ORIGINAL_PRICE"),
                        rs.getString("DISCOUNT_CHECKER"),
                        rs.getString("STRENGTH"),
                        rs.getString("BRAND_NAME")
                );
                System.out.println(rs.getString("PRODUCT_NAME")+" "+rs.getString("QUANTITY"));
            }
            dh.print();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }        
    }
    void setTable(){
        
    String CREATE_CART = "CREATE TABLE IF NOT EXISTS CART_LIST "
            + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,PRODUCT_NAME VARCHAR(50),QUANTITY VARCHAR(10),"
            + "PRICE VARCHAR(15),PREPARATION VARCHAR(50),TOTAL VARCHAR(30),PRODUCT_ID VARCHAR(20))";
     try{
        java.sql.Statement stmt = connLITE.createStatement();
        stmt.execute(CREATE_CART);
     }catch(Exception e){
         JOptionPane.showMessageDialog(null,e);
     }
    }
    void createTable(){
             try{
    String CREATE_ACCOUNTS_LIST = "CREATE TABLE IF NOT EXISTS TBL_ACCOUNTS "
            + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME VARCHAR(10),PASSWORD VARCHAR(20),"
            + "SECURITY_QUESTION VARCHAR(50),ANSWER VARCHAR(50),USER_TYPE VARCHAR(50))";
    String CREATE_PRODUCT_LIST = "CREATE TABLE IF NOT EXISTS TBL_PRODUCT_LIST "
            + "(ID VARCHAR(15),PRODUCT_NAME VARCHAR(100),BRAND_NAME VARCHAR(100),"
            + "QUANTITY VARCHAR(5),EXPIRATION VARCHAR(15),"
            + "PRICE VARCHAR(15),SELLING_PRICE VARCHAR(15),DATE_PURCHASE VARCHAR(15),"
            + "UNIT VARCHAR(5),PREPARATION VARCHAR(50),PURCHASE_FROM VARCHAR(50), SET_LIMIT VARCHAR(50), "
            + "PRIMARY KEY(ID))";
    String CREATE_LOGS = "CREATE TABLE IF NOT EXISTS TBL_LOGS_LIST "
            + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,TRANSACTION_ID VARCHAR(30),TIME VARCHAR(30),"
            + "DATE VARCHAR(30),QUANTITY VARCHAR(100),SELLING_PRICE VARCHAR(30),"
            + "PAYMENT VARCHAR(30),CASHIER VARCHAR(50),PRODUCT_NAME VARCHAR(50),"
            + "PRICE VARCHAR(30))";
    String CREATE_EXPIRED_LOGS = "CREATE TABLE IF NOT EXISTS TBL_EXPIRED_LIST "
            + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,PRODUCT_NAME VARCHAR(30),QUANTITY VARCHAR(30),"
            + "PRICE VARCHAR(30),DATE_PURCHASE VARCHAR(100),EXPIRATION_DATE VARCHAR(30),PURCHASE_FROM VARCHAR(50))";
    String CREATE_CART_TABLE = "CREATE TABLE IF NOT EXISTS CART_LIST "
            + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,PRODUCT_NAME VARCHAR(30),QUANTITY VARCHAR(30),"
            + "PRICE VARCHAR(30),PREPARATION VARCHAR(100),TOTAL VARCHAR(30),PRODUCT_ID VARCHAR(30),"
            + "DISCOUNT_CHECKER VARCHAR(30), ORIGINAL_PRICE VARCHAR(30))";
    String CREATE_DISCOUNT_TABLE =  "CREATE TABLE IF NOT EXISTS DISCOUNT_LOGS (ID INTEGER PRIMARY KEY "
            + "AUTOINCREMENT, PRODUCT_NAME VARCHAR(50), DATE VARCHAR(50), QUANTITY VARCHAR(50),"
            + "SELLING_PRICE VARCHAR(50), DISCOUNTED_PRICE VARCHAR(50),CASHIER VARCHAR(50),"
            + "UNIT_PRICE VARCHAR(50) )";
     String CREATE_OUT_OF_STOCK_TABLE = "CREATE TABLE IF NOT EXISTS TBL_OUT_OF_STOCK("
             + "ID INTEGER PRIMARY KEY AUTOINCREMENT, TYPE VARCHAR(50), PRODUCT_NAME VARCHAR(50), BRAND_NAME VARCHAR(50),"
             + "PRICE VARCHAR(50), STRENGTH VARCHAR(50), PURCHASED_FROM VARCHAR(50),"
             + "DISK_CHECKER VARCHAR(10),PREPARATION VARCHAR(50))";
    
    java.sql.Statement stmt = connLITE.createStatement();
    stmt.execute(CREATE_ACCOUNTS_LIST);
    stmt.execute(CREATE_OUT_OF_STOCK_TABLE);
    stmt.execute(CREATE_PRODUCT_LIST);
    stmt.execute(CREATE_LOGS);
    stmt.execute(CREATE_EXPIRED_LOGS);
    stmt.execute(CREATE_CART_TABLE);
    stmt.execute(CREATE_DISCOUNT_TABLE);
    System.out.println("Done creating tables");
     }catch(Exception e){
         JOptionPane.showMessageDialog(null,e);
     }   
    }
}
