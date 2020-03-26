package DataBase;

import DataHolder.DataHolder;
import Panels.Logs_Panel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class SQLiteDB_action {
    SQLiteDB sqlite = SQLiteDB.getInstance();
      //MySQLite
      Connection SQLite_conn = (Connection) sqlite.getConn();
      String getURL = sqlite.getURL();
      DataHolder dh = DataHolder.getInstance();
      private static SQLiteDB_action instance = null;    
    
    public static SQLiteDB_action getInstance() {
      if(instance == null) {
         instance = new SQLiteDB_action();
      }
      return instance;
    }
    public void setDelete_logs(String type, String id){
        if(type.equalsIgnoreCase("Daily") || type.equalsIgnoreCase("Monthly") ||
           type.equalsIgnoreCase("Yearly") || type.equalsIgnoreCase("Free") ){
        String sql = "DELETE FROM TBL_LOGS_LIST WHERE ID = ?";
 
        try{
            PreparedStatement pstmt = SQLite_conn.prepareStatement(sql);
 
            // set the corresponding param
            pstmt.setString(1, id);
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        }else if(type.equalsIgnoreCase("Expired")){
        String sql = "DELETE FROM TBL_EXPIRED_LIST WHERE ID = ?";
 
        try{
            PreparedStatement pstmt = SQLite_conn.prepareStatement(sql);
 
            // set the corresponding param
            pstmt.setString(1, id);
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        }else if(type.equalsIgnoreCase("Discount")){
        String sql = "DELETE FROM DISCOUNT_LOGS WHERE ID = ?";
 
        try{
            PreparedStatement pstmt = SQLite_conn.prepareStatement(sql);
 
            // set the corresponding param
            pstmt.setString(1, id);
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        }else{
            System.out.println("Unknown type!");
        }
    }
    public void updateOut_of_stock(){
             String query = "UPDATE TBL_OUT_OF_STOCK SET DISK_CHECKER = ? "
                        +"WHERE DISK_CHECKER = ?";
        try {
            PreparedStatement pstmt = SQLite_conn.prepareStatement(query);
            // set the corresponding param
            pstmt.setString(1, "false");
            pstmt.setString(2, "true");
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeOutof_stock(String id){
        String sql = "DELETE FROM TBL_OUT_OF_STOCK WHERE ID = ?";
 
        try{
                PreparedStatement pstmt = SQLite_conn.prepareStatement(sql);
 
            // set the corresponding param
            pstmt.setString(1, id);
            // execute the delete statement
            pstmt.executeUpdate();
            sqlite.retrieveOut_of_stock_data();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void remove_zero_quantity(String id){
        String sql = "DELETE FROM TBL_PRODUCT_LIST WHERE ID = ?";
 
        try{
            PreparedStatement pstmt = SQLite_conn.prepareStatement(sql);
 
            pstmt.setString(1, id);
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }    }
    public void clearDiscountData() throws SQLException{
    Statement stmt = SQLite_conn.createStatement();

    // Use TRUNCATE
    String sql = "DELETE FROM DISCOUNT_LOGS";
    // Execute deletion
    stmt.executeUpdate(sql);
    }
    public void discountYearly_calculator(int year){
                 dh.clearIncome();
                 Logs_Panel lp = Logs_Panel.getInstance();
                 lp.clearDiscount_filter();
            try
               {
                 String query = "SELECT * FROM DISCOUNT_LOGS";
                 Statement st = (Statement) SQLite_conn.createStatement();
                 ResultSet rs = (ResultSet) st.executeQuery(query);
                 while (rs.next())
                 {
                     
                String setSplitString = rs.getString("DATE");
                String[] data = setSplitString.split(" ", 2);
                
                if(dh.getLastFour(data[0].substring(data[0].length() - 4)).equals(year+"")){

                    double setSelling_price = Double.parseDouble(rs.getString("SELLING_PRICE"));
                    int setQuantity = Integer.parseInt(rs.getString("QUANTITY"));
                    
                    dh.addTotal_details(setSelling_price,setQuantity,rs.getString("DISCOUNTED_PRICE"));

                    lp.discountFilter(rs.getString("ID"),rs.getString("PRODUCT_NAME"),rs.getString("DATE"),
                            rs.getString("QUANTITY"),rs.getString("SELLING_PRICE"),rs.getString("DISCOUNTED_PRICE"),
                            rs.getString("CASHIER"), rs.getString("STRENGTH"),
                            rs.getString("PREPARATION"), rs.getString("TRANSACTION_ID"),
                            rs.getString("UNIT_PRICE"));
                    
                   }
                 }
                 dh.discount_total();
               }
               catch (Exception e)
               {
                   JOptionPane.showMessageDialog(null,e+" discount");
               }   
    }
    public void discountMonth_calculator(int year, int month){
        dh.clearIncome();
            System.out.println("Retrieving discount list!");
            ArrayList<String> tokenize = new ArrayList<String>();
            try
               {
                 String query = "SELECT * FROM DISCOUNT_LOGS";
                 Statement st = (Statement) SQLite_conn.createStatement();
                 ResultSet rs = (ResultSet) st.executeQuery(query);
                 while (rs.next())
                 {
                    StringTokenizer stx = new StringTokenizer(rs.getString("DISCOUNTED_PRICE")," ");  
                      while (stx.hasMoreTokens()){  
                          tokenize.add(stx.nextToken());
                        }  
                      
                     dh.discountMonthly_Scann(year,month,rs.getString("DATE"),rs.getString("QUANTITY"),
                             rs.getString("SELLING_PRICE"),tokenize.get(0),rs.getString("ID"),
                             rs.getString("PRODUCT_NAME"),rs.getString("CASHIER"),
                             rs.getString("DISCOUNTED_PRICE"),rs.getString("STRENGTH"),
                             rs.getString("PREPARATION"), rs.getString("TRANSACTION_ID"),
                             rs.getString("UNIT_PRICE"));
                     
                     tokenize.clear();
                 }
                 dh.discount_total();
               }
               catch (Exception e)
               {
                   System.out.println(e);
               }   
               dh.print();
    }
//      public void updateQuantity(String product_name,String brand_name,int quantity,String purchased_from,
//              String quantity,String ){
//          
////          if(dh.scann_update_Quantity(product_id,quantity) == true){
//        String sql = "UPDATE CART_LIST SET QUANTITY = ? , "
//                        +"TOTAL = ? "
//                        + "WHERE ID = ?";
//        double set = 0;
//        try {
//                PreparedStatement pstmt = SQLite_conn.prepareStatement(sql);
//                int quantityx = Integer.parseInt(quantity);
//                if(price.equalsIgnoreCase("Free")){
//                    set = 0;
//                }else{
//                set = Double.parseDouble(price);                    
//                }
//                
//            // set the corresponding param
//            pstmt.setString(1, quantity);
//            pstmt.setString(2, quantityx*set+"");
//            pstmt.setInt(3, id);
//            // update 
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
////          }else{
////              
////          }
//      }
      public void updatePassword(String username,String password){
        String sql = "UPDATE TBL_ACCOUNTS SET PASSWORD = ? , "
                        +"WHERE USERNAME = ?";
 
        try {
            PreparedStatement pstmt = SQLite_conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, password);
            pstmt.setString(2, username);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
      }
      public void updateQuantity(String quantity, String id){
        String sql = "UPDATE TBL_PRODUCT_LIST SET QUANTITY = ? WHERE ID = ?";
 
        try {
            PreparedStatement pstmt = SQLite_conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, quantity);
            pstmt.setString(2, id);
            
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
      }
      public void updateProduct(String id,String product_name,String quantity,
            String expiration,String price,String selling_price,String date_purchase,
            String unit,String preparation,String purchase_from,String brand_name,String limit){
          
                  String sql = "UPDATE TBL_PRODUCT_LIST SET PRODUCT_NAME =?,"
                          + "BRAND_NAME = ?, QUANTITY = ?, EXPIRATION = ? , PRICE = ?,"
                          + "SELLING_PRICE = ?, DATE_PURCHASE = ?, UNIT = ?, PREPARATION =?,"
                          +"PURCHASE_FROM = ?, SET_LIMIT =?, UNIT =? WHERE ID = ?";
 
        try {
            PreparedStatement pstmt = SQLite_conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, product_name);
            pstmt.setString(2, brand_name);
            pstmt.setString(3, quantity);
            pstmt.setString(4, expiration);
            pstmt.setString(5, price);
            pstmt.setString(6, selling_price);
            pstmt.setString(7, date_purchase);
            pstmt.setString(9, preparation);
            pstmt.setString(10, purchase_from);
            pstmt.setString(11, limit);
            pstmt.setString(12, unit);
            pstmt.setString(13, id);
            
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
      }
      public void clear_Expired() throws SQLException{
            Statement stmt = SQLite_conn.createStatement();

            String sql = "DELETE FROM TBL_EXPIRED_LIST";
            stmt.executeUpdate(sql);
      }
    public void clear_Cart(){
    try{
        Statement stmt = SQLite_conn.createStatement();

        String  sql = "DELETE FROM CART_LIST";
        stmt.executeUpdate(sql);

    }catch(Exception e){
      JOptionPane.showMessageDialog(null,e);  
     }
    }
    public void addDiscount_logs(String product_name,String quantity,
            String selling_price, String discounted_price,String date,String cashier,
            String unit_price, String strength, String preparation,
            String transaction_id){
        String sql = "INSERT INTO DISCOUNT_LOGS(PRODUCT_NAME,DATE,QUANTITY,"
                + "SELLING_PRICE, DISCOUNTED_PRICE,CASHIER,UNIT_PRICE,"
                + "STRENGTH,PREPARATION, TRANSACTION_ID) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = SQLite_conn.prepareStatement(sql);
            pstmt.setString(1,product_name);
            pstmt.setString(2,date);
            pstmt.setString(3, quantity);
            pstmt.setString(4, selling_price);
            pstmt.setString(5, discounted_price);
            pstmt.setString(6,cashier);
            pstmt.setString(7,unit_price);
            pstmt.setString(8,strength);
            pstmt.setString(9,preparation);
            pstmt.setString(10,transaction_id);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void newAccount(String userType,String username,String password,
            String securityQuestion,String answer){
        String sql = "INSERT INTO TBL_ACCOUNTS(USERNAME,PASSWORD,"
                + "SECURITY_QUESTION,ANSWER,USER_TYPE) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pstmt = SQLite_conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2, password);
            pstmt.setString(3, securityQuestion);
            pstmt.setString(4, answer);
            pstmt.setString(5, userType);
            pstmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Successfully added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
      public void addCart_data(String product_name,String quantity,String price,
              String preparation,String total,String product_id,String original_price, String checker,
              String ID,String STRENGTH, String BRAND_NAME){
        String sql = "INSERT INTO CART_LIST(ID,PRODUCT_NAME,QUANTITY,"
                + "PRICE,PREPARATION,TOTAL,PRODUCT_ID,ORIGINAL_PRICE,"
                + "DISCOUNT_CHECKER,PRODUCT_ID, STRENGTH, BRAND_NAME"
                + ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = SQLite_conn.prepareStatement(sql);
            pstmt.setString(1,null);
            pstmt.setString(2, product_name);
            pstmt.setString(3, quantity);
            pstmt.setString(4, price);
            pstmt.setString(5, preparation);
            pstmt.setString(6, total);
            pstmt.setString(7, product_id);
            pstmt.setString(8, original_price);
            pstmt.setString(9, checker);
            pstmt.setString(10, product_id);
            pstmt.setString(11, STRENGTH);
            pstmt.setString(12, BRAND_NAME);
            pstmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Successfully added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
      }
      public void updateQuantity_total(String id, String total, String quantity){
//                  String sql = "UPDATE CART_LIST SET QUANTITY = ? , "
//                        +"TOTAL = ? "
//                        + "WHERE ID = ?";
//        double set = 0;
//        try {
//                PreparedStatement pstmt = SQLite_conn.prepareStatement(sql);
//                int quantityx = Integer.parseInt(quantity);
//                if(price.equalsIgnoreCase("Free")){
//                    set = 0;
//                }else{
//                set = Double.parseDouble(price);                    
//                }
//                
//            // set the corresponding param
//            pstmt.setString(1, quantity);
//            pstmt.setString(2, quantityx*set+"");
//            pstmt.setInt(3, id);
//            // update 
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }

      }
      public void updateQuantity(int id,String quantity,String product_id,
                                 String price){
          
//          if(dh.scann_update_Quantity(product_id,quantity) == true){
        String sql = "UPDATE CART_LIST SET QUANTITY = ? , "
                        +"TOTAL = ? "
                        + "WHERE ID = ?";
        double set = 0;
        try {
                PreparedStatement pstmt = SQLite_conn.prepareStatement(sql);
                int quantityx = Integer.parseInt(quantity);
                if(price.equalsIgnoreCase("Free")){
                    set = 0;
                }else{
                set = Double.parseDouble(price);                    
                }
                
            // set the corresponding param
            pstmt.setString(1, quantity);
            pstmt.setString(2, quantityx*set+"");
            pstmt.setInt(3, id);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
//          }else{
//              
//          }
      }
      public void deleteFree_logs(){
        try{
            Statement stmt = SQLite_conn.createStatement();

            String sql = "DELETE FROM TBL_LOGS_LIST";
            stmt.executeUpdate(sql);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
      }
      public void delete_out_of_stock_list(String product_name, 
              String brand_name){
        try{
            Statement stmt = SQLite_conn.createStatement();

            String sql = "DELETE FROM TBL_OUT_OF_STOCK WHERE PRODUCT_NAME = ?"
                    + " AND BRAND_NAME = ?";
            PreparedStatement pstmt = SQLite_conn.prepareStatement(sql);
 
            pstmt.setString(1, product_name);
            pstmt.setString(2, brand_name);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
      }
      public void delete_for_discount(String id, String product_name){
        try{
            Statement stmt = SQLite_conn.createStatement();

            String sql = "DELETE FROM DISCOUNT_LOGS WHERE TRANSACTION_ID = ?"
                    + " AND PRODUCT_NAME = ?";
            PreparedStatement pstmt = SQLite_conn.prepareStatement(sql);
 
            pstmt.setString(1, id);
            pstmt.setString(2, product_name);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
      }
      public void delete_for_logs(String id, String product_name){
        try{
            Statement stmt = SQLite_conn.createStatement();

            String sql = "DELETE FROM TBL_LOGS_LIST WHERE TRANSACTION_ID = ?"
                    + " AND PRODUCT_NAME = ? AND IF_DISCOUNT = ?";
            PreparedStatement pstmt = SQLite_conn.prepareStatement(sql);
 
            pstmt.setString(1, id);
            pstmt.setString(2, product_name);
            pstmt.setString(3, "yes");
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
      }
      public void deleteCart_product(int id){
        String sql = "DELETE FROM CART_LIST WHERE ID = ?";
 
        try{
            PreparedStatement pstmt = SQLite_conn.prepareStatement(sql);
 
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
      }
      public void deleteAccount(String username){
        String sql = "DELETE FROM TBL_ACCOUNTS WHERE USERNAME = ?";
 
        try{
            PreparedStatement pstmt = SQLite_conn.prepareStatement(sql);
 
            pstmt.setString(1, username);
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
}