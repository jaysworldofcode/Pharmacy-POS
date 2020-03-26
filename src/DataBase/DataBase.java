package DataBase;

import DataHolder.DataHolder;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DataBase{
//    setConnection sc = setConnection.getInstance();
    SQLiteDB sqlite = SQLiteDB.getInstance();
    DataHolder dh = DataHolder.getInstance();
    private static DataBase instance = null;    
      //MySQLite
      java.sql.Connection SQLite_conn = (java.sql.Connection) sqlite.getConn();
      String getURL = sqlite.getURL();

      public static DataBase getInstance() {
      if(instance == null) {
         instance = new DataBase();
      }
      return instance;
    }  
      //MySQL
//      Connection conn = sqlite.getConnection();
//      String myDriver = sc.getMyDriver();
//      String user = sc.getUser();
//      String pass = sc.getPass();
//      String myUrl = sc.getMyUrl();
      
      public void clearAll_cartList(){
          try{
        Statement statement = SQLite_conn.createStatement();
        statement.executeUpdate("DELETE FROM CART_LIST");
          }catch(Exception ex){
              System.out.println(ex);
          }
      }
      
      public void remove_product_list(String x){
        String sql = "DELETE FROM tbl_product_list where ID = ?";
        try {
        PreparedStatement pstmt = SQLite_conn.prepareStatement(sql);
 
            // set the corresponding param
              pstmt.setString(1, x);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
          }
    public void updateDataLogsQuantities(String id){
        String sql = "UPDATE TBL_PRODUCT_LIST SET QUANTITY = ? "
                + "WHERE ID = ?";
        
        try {
                PreparedStatement pstmt = SQLite_conn.prepareStatement(sql);
 
            // set the corresponding param
            pstmt.setString(1, dh.getTotalSubtract_quantity());
            pstmt.setString(2, id);
            // update 
            pstmt.executeUpdate();
            System.out.println("Successfully updated!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void add_Logs_list(String Action,String Time,String Date,String Quantity,
            String Selling_price,String Payment,String User,String product_name,String price,
            String Strenght, String Preparation, String discount_checker){
            try{
                  // create a mysql database connection

                  String query = " INSERT INTO TBL_LOGS_LIST (TRANSACTION_ID,TIME, "
                          + "DATE,QUANTITY,SELLING_PRICE,PAYMENT,CASHIER,PRODUCT_NAME,"
                          + "PRICE,STRENGTH,PREPARATION,IF_DISCOUNT)"
                          + " values (?,?,?,?,?,?,?,?,?,?,?,?)";

                  // create the mysql insert preparedstatement
                  PreparedStatement preparedStmt = SQLite_conn.prepareStatement(query);
                  preparedStmt.setString (1, Action);
                  preparedStmt.setString (2, Time);
                  preparedStmt.setString (3, Date);
                  preparedStmt.setString (4, Quantity);
                  preparedStmt.setString (5, Selling_price);
                  preparedStmt.setString (6, Payment);
                  preparedStmt.setString (7, User);
                  preparedStmt.setString (8, product_name);
                  preparedStmt.setString (9, price);
                  preparedStmt.setString (10, Strenght);
                  preparedStmt.setString (11, Preparation);
                  preparedStmt.setString (12, discount_checker);

                  preparedStmt.execute();

                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,e);   
             }   
    }
    public void EXPIRED_products(String product_name,String quantity,
            String price,String date_purchase,String expiration_date){
            try{
                  // create a mysql database connection
//                  String myDriver = "org.gjt.mm.mysql.Driver";
//                  Class.forName(myDriver);
//                  conn = (Connection) DriverManager.getConnection(myUrl, user, pass);

                  String query = " INSERT INTO TBL_PRODUCT_LIST (PRODUCT_NAME, "
                          + "QUANTITY,PRICE,DATE_PURCHASE,EXPIRATION_DATE,"
                          + "PREPARATION) values (?,?,?,?,?)";

                  // create the mysql insert preparedstatement
                  PreparedStatement preparedStmt = SQLite_conn.prepareStatement(query);
                  preparedStmt.setString (1, product_name);
                  preparedStmt.setString (2, quantity);
                  preparedStmt.setString (3, price);
                  preparedStmt.setString (4, date_purchase);
                  preparedStmt.setString (5, expiration_date);

                  preparedStmt.execute();

                }catch (Exception e){
             JOptionPane.showMessageDialog(null,e);   
             }   
    }
    public void add_product_list(String id, String product_name,String quantity,
            String expiration,String price,String selling_price,
            String date_purchase,String unit,String preparation,String purchase_from,
            String brand_name,String set_limit){
            try{
                  // create a mysql database connection
//                  String myDriver = "org.gjt.mm.mysql.Driver";
//                  Class.forName(myDriver);
//                  conn = (Connection) DriverManager.getConnection(myUrl, user, pass);

                  String query = "INSERT INTO TBL_PRODUCT_LIST (ID,PRODUCT_NAME, "
                          + "QUANTITY,EXPIRATION,PRICE,SELLING_PRICE,DATE_PURCHASE,UNIT,"
                          + "PREPARATION,PURCHASE_FROM,BRAND_NAME,SET_LIMIT) values (?,?,?,?,?,?,?,?,?,?,?,?)";

                  // create the mysql insert preparedstatement
                  PreparedStatement preparedStmt = SQLite_conn.prepareStatement(query);
                  preparedStmt.setString (1, id);
                  preparedStmt.setString (2, product_name);
                  preparedStmt.setString (3, quantity);
                  preparedStmt.setString (4, expiration);
                  preparedStmt.setString (5, price);
                  preparedStmt.setString (6, selling_price);
                  preparedStmt.setString (7, date_purchase);
                  preparedStmt.setString (8, unit);
                  preparedStmt.setString (9, preparation);
                  preparedStmt.setString (10, purchase_from);
                  preparedStmt.setString (11, brand_name);
                  preparedStmt.setString (12, set_limit);

                  preparedStmt.execute();

                }catch (Exception e){
             JOptionPane.showMessageDialog(null,e);   
             }   
    }
    public void update_product_list(String id, String product_name,String quantity,
            String expiration,String price,String selling_price,
            String date_purchase,String unit,String preparation,String purchased_from,
            String brand_name){
        try
            {

              String query = "UPDATE TBL_PRODUCT_LIST SET PRODUCT_NAME = ?, QUANTITY = ? "
                      + ", EXPIRATION =?, PRICE = ?, SELLING_PRICE = ?, DATE_PURCHASE =? "
                      + ", UNIT = ?, PREPARATION = ?,PURCHASE_FROM = ?, BRAND_NAME = ? WHERE ID = ?";
              PreparedStatement preparedStmt = SQLite_conn.prepareStatement(query);
              preparedStmt.setString(1, product_name);
              preparedStmt.setString(2, quantity);
              preparedStmt.setString(3, expiration);
              preparedStmt.setString(4, price);
              preparedStmt.setString(5, selling_price);
              preparedStmt.setString(6, date_purchase);
              preparedStmt.setString(7, unit);
              preparedStmt.setString(8, preparation);
              preparedStmt.setString(9, purchased_from);
              preparedStmt.setString(10, brand_name);
              preparedStmt.setString(11, id);

              // execute the java preparedstatement
              preparedStmt.executeUpdate();

            }
            catch (Exception e)
            {
             JOptionPane.showMessageDialog(null,e);   
            }
          }        
    }