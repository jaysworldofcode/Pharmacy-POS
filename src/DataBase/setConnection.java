package DataBase;

import DataHolder.DataHolder;
import com.mysql.jdbc.*;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class setConnection {
    //MYSQL
    static Connection conn;
    static String myUrl = "jdbc:mysql://localhost:3306/pharmacy";
    String user = "root";
    String pass = "jaylord";
    String myDriver = "com.mysql.jdbc.Driver";

    DataHolder dh = DataHolder.getInstance();
//    DataBase db = DataBase.getInstance();
    private static setConnection instance = null;    
    
    public static setConnection getInstance() {
      if(instance == null) {
         instance = new setConnection();
      }
      return instance;
    }
    public setConnection(){
        System.out.println("Set connection!");
        //MYSQL DATABASE
        try{
        Class.forName(myDriver);  
        conn=(Connection) DriverManager.getConnection(myUrl,user,pass);
        System.out.println("Successfully connected to database!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        createTable();
        retrieveData();
        dh.scanExpired_products();
        
    }
    public Connection getConnection(){
        return conn;
    }
    public String getMyDriver(){
        return myDriver;
    }
    public String getUser(){
        return user;
    }
    public String getPass(){
        return pass;
    }
    public String getMyUrl(){
        return myUrl;
    }
    void createTable(){
             try{       
    String CREATE_ACCOUNTS_LIST = "CREATE TABLE IF NOT EXISTS TBL_ACCOUNTS "
            + "(ID INTEGER AUTO_INCREMENT,USERNAME VARCHAR(10),PASSWORD VARCHAR(20),"
            + "SECURITY_QUESTION VARCHAR(50),ANSWER VARCHAR(50),PRIMARY KEY(ID))";
    String CREATE_PRODUCT_LIST = "CREATE TABLE IF NOT EXISTS TBL_PRODUCT_LIST "
            + "(ID VARCHAR(15),PRODUCT_NAME VARCHAR(100),BRAND_NAME VARCHAR(100),"
            + "QUANTITY VARCHAR(5),DAYS_LEFT VARCHAR(5),EXPIRATION VARCHAR(15),"
            + "PRICE VARCHAR(15),SELLING_PRICE VARCHAR(15),DATE_PURCHASE VARCHAR(15),"
            + "UNIT VARCHAR(5),PREPARATION VARCHAR(50),LIMIT VARCHAR(50), PRIMARY KEY(ID))";
    String CREATE_LOGS = "CREATE TABLE IF NOT EXISTS TBL_LOGS_LIST "
            + "(ID INTEGER AUTO_INCREMENT,ACTION VARCHAR(30),TIME VARCHAR(30),"
            + "DATE VARCHAR(30),QUANTITY VARCHAR(100),SELLING_PRICE VARCHAR(30),"
            + "PAYMENT VARCHAR(30),PRIMARY KEY(ID))";
//    String eventpresent = "CREATE TABLE IF NOT EXISTS tbl_eventpresent"
//            + "(id_event INTEGER AUTO_INCREMENT, id_number INTEGER, "
//            + "student_name VARCHAR(50),event_id INTEGER,event_name VARCHAR(100),"
//            + "time_login VARCHAR(10),PRIMARY KEY(id_event))";
//    String eventreminder = "CREATE TABLE IF NOT EXISTS tbl_eventreminder("
//            + "reminder_before3days VARCHAR(10),reminder_exactDay VARCHAR(10),"
//            + "event_id INTEGER,automatic VARCHAR(5), PRIMARY KEY(event_id))";
//    String members = "CREATE TABLE IF NOT EXISTS tbl_members(member_ID INTEGER,"
//            + "firstname VARCHAR(20),middlename VARCHAR(50),lastname VARCHAR(50),"
//            + "gender VARCHAR(10),birthday VARCHAR(15),age INTEGER,status INTEGER,"
//            + "contact_No VARCHAR(11),cellgroup VARCHAR(5),"
//            + " PRIMARY KEY(member_ID))";
//    String sentitems = "CREATE TABLE IF NOT EXISTS tbl_sentitems(id INTEGER,"
//            + "sent_to VARCHAR(50),time_send VARCHAR(50),date_sent VARCHAR(50),"
//            + "sent_item VARCHAR(160), PRIMARY KEY(id))";
    java.sql.Statement stmt = conn.createStatement();
    stmt.execute(CREATE_ACCOUNTS_LIST);
    stmt.execute(CREATE_PRODUCT_LIST);
    stmt.execute(CREATE_LOGS);
    System.out.println("Done scanning tables");
     }catch(Exception e){
         JOptionPane.showMessageDialog(null,e);
     }   

    }
        public void retrieveData(){
      System.out.println("asdsadsdasdasasdasdasdasdsadsadasdsadsdasdasasdasdasdasdsadsadasdsadsdasdasasdasdasdasdsadsadasdsadsdasdasasdasdasdasdsadsad");
 try
    {
      String query = "SELECT * FROM tbl_product_list";

      Statement st = (Statement) conn.createStatement();
      
      ResultSet rs = (ResultSet) st.executeQuery(query);
      
      while (rs.next())
      {
//        dh.addData_product(rs.getString("ID"),rs.getString("PRODUCT_NAME"),
//        rs.getString("QUANTITY"),rs.getString("EXPIRATION"), rs.getString("PRICE"),
//        rs.getString("SELLING_PRICE"),rs.getString("DATE_PURCHASE"),
//        rs.getString("UNIT"), rs.getString("PREPARATION"));
      }
    }
    catch (Exception e)
    {
        JOptionPane.showMessageDialog(null,e);
    }   
 dh.print();
        }
}