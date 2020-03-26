package DataHolder;

import DataBase.SQLiteDB;
import Panels.List_Panel;
import Panels.Logs_Panel;
import Panels.Sale_Panel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import msinventorysystem.MainFrame;
import msinventorysystem.myDecimalConverter;

public class DataHolder {
    static String setBackground = null;
    ArrayList<String> CLEAR_LOGS_REMOVER = new ArrayList<String>();
    //ACCOUNT LIST
    ArrayList<Integer> ID_ACCOUNTS = new ArrayList<Integer>();
    ArrayList<String> USERNAME = new ArrayList<String>();
    ArrayList<String> PASSWORD = new ArrayList<String>();
    ArrayList<String> SECURITY_QUESTION = new ArrayList<String>();
    ArrayList<String> ANSWER = new ArrayList<String>();
    ArrayList<String> USER_TYPE = new ArrayList<String>();
    
    static String userLogin = null;
    static String userType = null;
    static String password = null;
    static boolean isLogin = false;
    static int account_maximum_ID = 0;
    //PRODUCT LIST
    ArrayList<String> ID = new ArrayList<String>();
    ArrayList<String> PRODUCT_NAME = new ArrayList<String>();
    ArrayList<String> BRAND_NAME = new ArrayList<String>();
    ArrayList<String> QUANTITY = new ArrayList<String>();
    ArrayList<String> EXPIRATION_DATE = new ArrayList<String>();
    ArrayList<String> PRICE = new ArrayList<String>();
    ArrayList<String> SELLING_PRICE = new ArrayList<String>();
    ArrayList<String> DATE_PURCHASE = new ArrayList<String>();
    ArrayList<String> UNIT = new ArrayList<String>();
    ArrayList<String> PREPARATION = new ArrayList<String>();
    ArrayList<String> DAYS_LEFT = new ArrayList<String>();
    ArrayList<String> PURCHASE_FROM = new ArrayList<String>();
    ArrayList<String> SET_LIMIT = new ArrayList<String>();
    ArrayList<String> PRICE_INCREASE = new ArrayList<String>();
    
    ArrayList<String> ID_SCANNED_EXPIRED = new ArrayList<String>();
    //EXPIRED LIST
    ArrayList<String> EXPIRED_PRODUCT_NAME = new ArrayList<String>();
    ArrayList<String> EXPIRED_QUANTITY = new ArrayList<String>();
    ArrayList<String> EXPIRED_PRICE = new ArrayList<String>();
    ArrayList<String> EXPIRED_DATE_PURCHASE = new ArrayList<String>();
    ArrayList<String> EXPIRED_EXPIRATION_DATE = new ArrayList<String>();
    ArrayList<String> EXPIRED_PURCHASE_FROM = new ArrayList<String>();
    ArrayList<String> EXPIRED_ID = new ArrayList<String>();
    ArrayList<String> EXPIRED_STRENGTH = new ArrayList<String>();
    ArrayList<String> EXPIRED_PREPARATION = new ArrayList<String>();
    //INCOME LIST
    ArrayList<String> INCOME_ID = new ArrayList<String>();
    ArrayList<String> INCOME_ACTION = new ArrayList<String>();
    ArrayList<String> INCOME_PRODUCT_NAME = new ArrayList<String>();
    ArrayList<String> INCOME_QUANTITY = new ArrayList<String>();
    ArrayList<String> INCOME_SELLING_PRICE = new ArrayList<String>();
    ArrayList<String> INCOME_PAYMENT = new ArrayList<String>();
    ArrayList<String> INCOME_DATE = new ArrayList<String>();
    //CART LIST
    ArrayList<Integer> CART_ID = new ArrayList<Integer>();
    ArrayList<String> CART_PRODUCT_NAME = new ArrayList<String>();
    ArrayList<String> CART_STRENGTH = new ArrayList<String>();
    ArrayList<String> CART_QUANTITY = new ArrayList<String>();
    ArrayList<String> CART_SELLING_PRICE = new ArrayList<String>();
    ArrayList<String> CART_PRICE = new ArrayList<String>();
    ArrayList<String> CART_PREPARATION = new ArrayList<String>();
    ArrayList<String> CART_TOTAL = new ArrayList<String>();
    ArrayList<String> CART_PRODUCT_ID = new ArrayList<String>();
    ArrayList<String> CART_PRODUCT_CHECKER = new ArrayList<String>();
    ArrayList<String> CART_BRAND_NAME = new ArrayList<String>();
    //LOGS LIST
    ArrayList<Integer> LOGS_ID = new ArrayList<Integer>();
    ArrayList<String> LOGS_PRODUCT = new ArrayList<String>();
    ArrayList<String> LOGS_TRANSACTION_ID = new ArrayList<String>();
    ArrayList<String> LOGS_TIME = new ArrayList<String>();
    ArrayList<String> LOGS_DATE = new ArrayList<String>();
    ArrayList<String> LOGS_QUANTITY = new ArrayList<String>();
    ArrayList<String> LOGS_SELLING_PRICE = new ArrayList<String>();
    ArrayList<String> LOGS_PRICE = new ArrayList<String>();
    ArrayList<String> LOGS_PAYMENT = new ArrayList<String>();
    ArrayList<String> LOGS_CASHIER = new ArrayList<String>();
    ArrayList<String> LOGS_STRENGTH = new ArrayList<String>();
    ArrayList<String> LOGS_PREPARATION = new ArrayList<String>();
    ArrayList<String> LOGS_DISCOUNT_CHECKER = new ArrayList<String>();
    //LOGS VARIABLES
    ArrayList<Integer> TOTAL_ID_LOGS = new ArrayList<Integer>();
    ArrayList<Integer> TOTAL_QUANTITIES = new ArrayList<Integer>();
    ArrayList<Integer> TOTAL_INCOME = new ArrayList<Integer>();
    ArrayList<Integer> TOTAL_INCOME_ALL = new ArrayList<Integer>();
    ArrayList<String> PRODUCTS_SOLD = new ArrayList<String>();
    //WEEKLY LOGS
    ArrayList<String> WEEKLY_LOGS = new ArrayList<String>();
    //New ALGO computing
    ArrayList<Double> SET_TOTAL_SELLING_PRICE = new ArrayList<Double>();
    ArrayList<Integer> SET_TOTAL_QUANTITY = new ArrayList<Integer>();
    ArrayList<Double> SET_TOTAL_PRICE = new ArrayList<Double>();
    //STORED TOKENIZE STRINGS
    ArrayList<Integer> TOKENIZE_LOGS = new ArrayList<Integer>();
    ArrayList<Integer> STORE_LOGS_ID_SCANNED = new ArrayList<Integer>();
    
    ArrayList<JFrame> LIST_OF_JFRAME = new ArrayList<JFrame>();
    
    static int totalQuantities = 0;
    static double totalIncome = 0;
    //COUNT EXPIRED PRODUCTS
    static int setExpired_highestID = 0;
    //GET LOGS MAX ID
    static int logs_max_ID =0;
    static int index = 0;
    static int largestID_car = 0;
    //Total expire
    static int totalExpired_product =0;
    //Retrieve quantity get
    static String setQuantity = "";
    ArrayList<String> expiredProduct = new ArrayList<String>();
    myDecimalConverter mDc = new myDecimalConverter();
    
    static String myFunction_use=null;
    private static DataHolder instance = null;    
      public static DataHolder getInstance() {
      if(instance == null) {
         instance = new DataHolder();
      }
      System.out.println("/* Dataholder class */");
      return instance;
    }
    public void setFunction_use(String use){
        myFunction_use = use;
    }
    public String getFunction_use(){
        return myFunction_use;
    }
    public void CheckProductsPrint(){
        String toBePrint = null;
        
        for(int x=0;x<ID.size();x++){
            toBePrint = toBePrint+System.lineSeparator()+PRODUCT_NAME.get(x)+" "+QUANTITY.get(x);
        }
        JOptionPane.showMessageDialog(null, toBePrint);
    }
    public void set_Remove_sale(String id, String quantity){
            index = ID.indexOf(id);
            String set = QUANTITY.get(index);
            int cart = Integer.parseInt(quantity);
            int setE = Integer.parseInt(set);
            int total = setE-cart;
            QUANTITY.set(index, total+"");
    }
    public void addFrameList(JFrame jf){
        LIST_OF_JFRAME.add(jf);
    }
    public void checkSubFrameIsVisible(){
        boolean viewVisibility = false;
        for(int x=0;x<LIST_OF_JFRAME.size();x++){
            if(LIST_OF_JFRAME.get(x).isVisible()){
                    viewVisibility = true;
            }
        }
            MainFrame mf = MainFrame.getInstance();
        if(viewVisibility){
            mf.setEnabled(false);
        }else{
            mf.setEnabled(true);
        }
    }
    public void frameSetVisibleFalse(){
        for(int x=0;x<LIST_OF_JFRAME.size();x++){
            LIST_OF_JFRAME.get(x).setVisible(false);
        }
    }
    public void setTable(){
    Sale_Panel sp = Sale_Panel.getInstance();
    sp.filterTable();
    sp.filter_cart_table();
    List_Panel lp = List_Panel.getInstance();
    lp.filterTable();
    }
    public void setRemove(String ID,String quantity){
        if(this.ID.contains(ID)){
            index = this.ID.indexOf(ID);
            int total_quanity_left = Integer.parseInt(QUANTITY.get(index));
            int total_quanity_bought = Integer.parseInt(quantity);
            QUANTITY.set(index, total_quanity_left+total_quanity_bought+"");
        }else{
            
        }
    }
    public void setRemove_cart_table(String ID){
        int convert_cart_id = Integer.parseInt(ID);
        index = CART_ID.indexOf(convert_cart_id);
        
        CART_ID.remove(index);
        CART_PRODUCT_NAME.remove(index);
        CART_STRENGTH.remove(index);
        CART_QUANTITY.remove(index);
        CART_SELLING_PRICE.remove(index);
        CART_PRICE.remove(index);
        CART_PREPARATION.remove(index);
        CART_TOTAL.remove(index);
        CART_PRODUCT_ID.remove(index);
        CART_PRODUCT_CHECKER.remove(index);
        CART_BRAND_NAME.remove(index);
    }
    public void clearAll_product_list(){
        ID.clear();
        PRODUCT_NAME.clear();
        BRAND_NAME.clear();
        QUANTITY.clear();
        EXPIRATION_DATE.clear();
        PRICE.clear();
        SELLING_PRICE.clear();
        DATE_PURCHASE.clear();
        UNIT.clear();
        PREPARATION.clear();
        DAYS_LEFT.clear();
        PURCHASE_FROM.clear();
        SET_LIMIT.clear();
        PRICE_INCREASE.clear();
    }
    public void clearAll_product(){
    ID.clear();
    PRODUCT_NAME.clear();
    BRAND_NAME.clear();
    QUANTITY.clear();
    EXPIRATION_DATE.clear();
    PRICE.clear();
    SELLING_PRICE.clear();
    PRICE_INCREASE.clear();
    DATE_PURCHASE.clear();
    UNIT.clear();
    PREPARATION.clear();
    DAYS_LEFT.clear();
    PURCHASE_FROM.clear();
    SET_LIMIT.clear();
    
    SQLiteDB sdb = SQLiteDB.getInstance();
    sdb.retrieveData_products();
    Sale_Panel sp = Sale_Panel.getInstance();
    sp.filterTable();
    sp.filter_cart_table();
    List_Panel lp = List_Panel.getInstance();
    lp.filterTable();
    }
    public void subtractQuantity(String id, String setSubtract){
        index = ID.indexOf(id);
       float set = Float.parseFloat(setSubtract);
        int getQuantity = Integer.parseInt(QUANTITY.get(index));
        int b = (int)Math.round(set);

        QUANTITY.set(index, getQuantity-b+"");
    }
    public boolean setOut_of_stock(String product){
        boolean set = false;
        for(int x=0;x<PRODUCT_NAME.size();x++){
            if ( PRODUCT_NAME.get(x).toLowerCase().indexOf(product.toLowerCase()) != -1 ) {
               set = true;
            } else {
                set = false;
            }        
        }
        return set;
    }
    public void setLogin(boolean set){
        isLogin = set;
    }
    public boolean viewLogin(){
        return isLogin;
    }
    public void clearLogs(){
        CLEAR_LOGS_REMOVER.clear();
                        for(int x=0;x<LOGS_PAYMENT.size();x++){
                            if(LOGS_PAYMENT.get(x).equalsIgnoreCase("Free")){
                            }else{
                                CLEAR_LOGS_REMOVER.add(LOGS_ID.get(x)+"");
                            }
                        }
    }
    public void free_Remover(){
        CLEAR_LOGS_REMOVER.clear();
                        for(int x=0;x<LOGS_PAYMENT.size();x++){
                            if(LOGS_PAYMENT.get(x).equalsIgnoreCase("Free")){
                                CLEAR_LOGS_REMOVER.add(LOGS_ID.get(x)+"");
                            }
                        }
    }
    public void setData_remover(){
        for(int x=0;x<CLEAR_LOGS_REMOVER.size();x++){
                int parser = Integer.parseInt(CLEAR_LOGS_REMOVER.get(x));
                int index = LOGS_ID.indexOf(parser);
                LOGS_ID.remove(index);
        }
    }
    public ArrayList<String> retrieveRemovable_array(){
        return CLEAR_LOGS_REMOVER;
    }
    public void clearAll_expire(){
        EXPIRED_PRODUCT_NAME.clear();
        EXPIRED_QUANTITY.clear();
        EXPIRED_PRICE.clear();
        EXPIRED_DATE_PURCHASE.clear();
        EXPIRED_EXPIRATION_DATE.clear();
        EXPIRED_PURCHASE_FROM.clear();
    }
    public void clearIncome(){
        STORE_LOGS_ID_SCANNED.clear();
        SET_TOTAL_SELLING_PRICE.clear();
        SET_TOTAL_QUANTITY.clear();
        SET_TOTAL_PRICE.clear();
        
        TOTAL_ID_LOGS.clear();
        TOTAL_QUANTITIES.clear();
        TOTAL_INCOME.clear();
        PRODUCTS_SOLD.clear();
        totalQuantities = 0;
        totalIncome = 0;
    }
    public void discountMonthly_Scann(int year, int month, String date, String quantity, String selling_price,
        String discounted_price,String id, String product_name,String cashier,String set_discount_all,
        String strength, String preparation, String transaction_id, String unit_price){

        Logs_Panel lp = Logs_Panel.getInstance();

        ArrayList<String> remove_time = new ArrayList<String>();
            String toDelims =" ";
            String setTimeSplit_string = date;
            String[] setTime_splitter = setTimeSplit_string.split(toDelims);
            int setTimeToken_count = setTime_splitter.length;
            
                    for (int j = 0; j < setTimeToken_count; j++){
                        remove_time.add(setTime_splitter[j]);
                    }
            
            TOKENIZE_LOGS.clear();
                String delims = "/";
                String splitString = remove_time.get(0);
                
            String[] tokens = splitString.split(delims);
            int tokenCount = tokens.length;
            
            for (int j = 0; j < tokenCount; j++){
                int result = Integer.parseInt(tokens[j]);
                TOKENIZE_LOGS.add(result);
            }
            if(isFirstDayOfTheMonth(TOKENIZE_LOGS.get(2),TOKENIZE_LOGS.get(0),
                TOKENIZE_LOGS.get(1),month,year)==true){
                
                double setSelling_price = Double.parseDouble(selling_price);
                int setQuantity = Integer.parseInt(quantity);
                double setDiscounted_price = Double.parseDouble(discounted_price);
                
                SET_TOTAL_SELLING_PRICE.add(setSelling_price);
                SET_TOTAL_QUANTITY.add(setQuantity);
                SET_TOTAL_PRICE.add(setDiscounted_price);
                
                lp.discountFilter(id,product_name,date,quantity,selling_price, set_discount_all,cashier,
                strength,preparation,transaction_id,unit_price);
            }
    }
    public void addTotal_details(double selling_price, int quantity, String total_price){
            ArrayList<String> tokenize = new ArrayList<String>();
            tokenize.clear();
            StringTokenizer stx = new StringTokenizer(total_price+""," ");  
                      while (stx.hasMoreTokens()) {  
                          tokenize.add(stx.nextToken());
                        }  
        
                 double setString = Double.parseDouble(tokenize.get(0));
                SET_TOTAL_SELLING_PRICE.add(selling_price);
                SET_TOTAL_QUANTITY.add(quantity);
                SET_TOTAL_PRICE.add(setString);
    }
    public void discount_total(){
            for(int x=0;x<SET_TOTAL_SELLING_PRICE.size();x++){
                double viewAnswer = SET_TOTAL_PRICE.get(x)*SET_TOTAL_QUANTITY.get(x);

                totalIncome = totalIncome+viewAnswer;
            }
    }
    public int getIndex_username(String username){
        index = USERNAME.indexOf(username);
        return index;
    }
    public String getSecurityQuestionAnswer(){
        return ANSWER.get(index);
    }
    public void freeMonyhly_scann(int year,int month){
        STORE_LOGS_ID_SCANNED.clear();
        SET_TOTAL_SELLING_PRICE.clear();
        SET_TOTAL_QUANTITY.clear();
        SET_TOTAL_PRICE.clear();
        
        TOTAL_ID_LOGS.clear();
        TOTAL_QUANTITIES.clear();
        TOTAL_INCOME.clear();
        PRODUCTS_SOLD.clear();
        totalQuantities = 0;
        totalIncome = 0;
        
        for(int x=0;x<LOGS_ID.size();x++){
            TOKENIZE_LOGS.clear();
                String delims = "/";
                String splitString = LOGS_DATE.get(x);
                
            String[] tokens = splitString.split(delims);
            int tokenCount = tokens.length;
            
                for (int j = 0; j < tokenCount; j++){
                    int result = Integer.parseInt(tokens[j]);
                    TOKENIZE_LOGS.add(result);
                }

            if(isFirstDayOfTheMonth(TOKENIZE_LOGS.get(2),TOKENIZE_LOGS.get(0),
                TOKENIZE_LOGS.get(1),month,year)==true){
                if(LOGS_PAYMENT.get(x).equalsIgnoreCase("Free")){
                STORE_LOGS_ID_SCANNED.add(LOGS_ID.get(x));

                //My new Algo code
                double set_selling_price = 0;
                int set_quantity = Integer.parseInt(LOGS_QUANTITY.get(x));
                double set_price = 0;
                
                if(LOGS_SELLING_PRICE.get(x).equalsIgnoreCase("Free")){
                    set_selling_price = 0;
                    set_price = 0;
                }else{
                    set_selling_price = Double.parseDouble(LOGS_SELLING_PRICE.get(x));
                    set_price = Double.parseDouble(LOGS_PRICE.get(x));
                }
                
                SET_TOTAL_SELLING_PRICE.add(set_selling_price);
                SET_TOTAL_QUANTITY.add(set_quantity);
                SET_TOTAL_PRICE.add(set_price);
                    }
                }
        }
            for(int x=0;x<SET_TOTAL_SELLING_PRICE.size();x++){
                    double setTotal_selling_price = SET_TOTAL_SELLING_PRICE.get(x) * SET_TOTAL_QUANTITY.get(x);
                    double setTotal_total_income = SET_TOTAL_PRICE.get(x) * SET_TOTAL_QUANTITY.get(x);
                    double result = setTotal_selling_price - setTotal_total_income;
                    totalQuantities = totalQuantities+SET_TOTAL_QUANTITY.get(x);
                    totalIncome = totalIncome+result;
            }
    }
    public void setBackground(String x){
        setBackground = x;
    }
    public String getBackground(){
        return setBackground;
    }
    public void removeAccount(String username){
        index = USERNAME.indexOf(username);
        ID_ACCOUNTS.remove(index);
        USERNAME.remove(index);
        PASSWORD.remove(index);
        SECURITY_QUESTION.remove(index);
        ANSWER.remove(index);
        USER_TYPE.remove(index);
    }
    public void setAccount_indexAll(String x){
        index = USERNAME.indexOf(x);
    }
    public void scannUsername(String username){
        if(USERNAME.contains(username)){
            index = USERNAME.indexOf(username);            
        }else{
            JOptionPane.showMessageDialog(null,"Invalid username.");
        }
    }
    public int setSize_expired(){
        return expiredProduct.size();
    }
    public String getExpired_product(int x){
        return expiredProduct.get(x);
    }      
    public int getTotal_expiredProduct(){
        return setExpired_highestID;
    }
    public void setTotal_expired(){
        setExpired_highestID++;
    }
    public void setTotal_expired(String product){
        expiredProduct.add(product);
    }
    public void setExpired_ID(int id){
        if(setExpired_highestID<id){
            setExpired_highestID = id;
        }
    }
    public void removeExpired_product(String x){
                index = ID.indexOf(x);
                ID.remove(index);
                PRODUCT_NAME.remove(index);
                QUANTITY.remove(index);
                EXPIRATION_DATE.remove(index);
                PRICE.remove(index);
                SELLING_PRICE.remove(index);
                PRICE_INCREASE.remove(index);
                DATE_PURCHASE.remove(index);
                UNIT.remove(index);
                PREPARATION.remove(index);
                DAYS_LEFT.remove(index);
                PURCHASE_FROM.remove(index);
                BRAND_NAME.remove(index);
                SET_LIMIT.remove(index);
    }
    public void removeID_if_quantity_is_zero(String id){
                index = ID.indexOf(id);
        
                ID.remove(index);
                PRODUCT_NAME.remove(index);
                QUANTITY.remove(index);
                EXPIRATION_DATE.remove(index);
                PRICE.remove(index);
                SELLING_PRICE.remove(index);
                PRICE_INCREASE.remove(index);
                DATE_PURCHASE.remove(index);
                UNIT.remove(index);
                PREPARATION.remove(index);
                DAYS_LEFT.remove(index);
                PURCHASE_FROM.remove(index);
                BRAND_NAME.remove(index);
                SET_LIMIT.remove(index);
    }
    public void removeID_afterQuantityRetrieve(){
                ID.remove(index);
                PRODUCT_NAME.remove(index);
                QUANTITY.remove(index);
                EXPIRATION_DATE.remove(index);
                PRICE.remove(index);
                SELLING_PRICE.remove(index);
                PRICE_INCREASE.remove(index);
                DATE_PURCHASE.remove(index);
                UNIT.remove(index);
                PREPARATION.remove(index);
                DAYS_LEFT.remove(index);
                PURCHASE_FROM.remove(index);
                BRAND_NAME.remove(index);
                SET_LIMIT.remove(index);
    }
    public String retrieveID(){
                return ID.get(index);
    }
    public int getExpiredID(){
        return setExpired_highestID;
    }
    public void updateAccount(String password){
        PASSWORD.set(USERNAME.indexOf(userLogin), password);
    }
    public String retrievePassword(){
        return PASSWORD.get(USERNAME.indexOf(userLogin));
    }
    public void add_Expired(String product_name,String quantity,
                            String price,String date_purchase,
                            String expiration_date,String purchase_from,
                            String id,String strength,String preparation){
        EXPIRED_PRODUCT_NAME.add(product_name);
        EXPIRED_QUANTITY.add(quantity);
        
        if(price.contains("/")){
            String[] parts = price.split("/");
            EXPIRED_PRICE.add(parts[0]);
        }else{
            EXPIRED_PRICE.add(price);
        }
        
        EXPIRED_DATE_PURCHASE.add(date_purchase);
        EXPIRED_EXPIRATION_DATE.add(expiration_date);
        EXPIRED_PURCHASE_FROM.add(purchase_from);
        EXPIRED_ID.add(id);
        EXPIRED_STRENGTH.add(strength);
        EXPIRED_PREPARATION.add(preparation);
    }
    public void clearExpired(){
        EXPIRED_PRODUCT_NAME.clear();
        EXPIRED_QUANTITY.clear();
        EXPIRED_PRICE.clear();
        EXPIRED_DATE_PURCHASE.clear();
        EXPIRED_EXPIRATION_DATE.clear();
        EXPIRED_PURCHASE_FROM.clear();
        EXPIRED_ID.clear();
    }
    public int getExpiredSize(){
        return EXPIRED_PRODUCT_NAME.size();
    }
    public String getProduct_name(int x){
        return EXPIRED_PRODUCT_NAME.get(x);
    }
    public String getQuantity(int x){
        return EXPIRED_QUANTITY.get(x);
    }
    public String getPrice(int x){
        return EXPIRED_PRICE.get(x);
    }
    public String getDate_purchased(int x){
        return EXPIRED_DATE_PURCHASE.get(x);
    }
    public String getExpiration(int x){
        return EXPIRED_EXPIRATION_DATE.get(x);
    }
    public String getPurchased(int x){
        return EXPIRED_PURCHASE_FROM.get(x);
    }
    public String getID(int x){
        return EXPIRED_ID.get(x);
    }
    public String getStrength(int x){
        return EXPIRED_STRENGTH.get(x);
    }
    public String getPreparation(int x){
        return EXPIRED_PREPARATION.get(x);
    }
    public ArrayList<String> testUsername(){
        return USERNAME;
    }
    public void add_Account(int id,String user_type,String username,String password,
            String security_question,String answer){
        ID_ACCOUNTS.add(id);
        USERNAME.add(username);
        PASSWORD.add(password);
        SECURITY_QUESTION.add(security_question);
        ANSWER.add(answer);
        USER_TYPE.add(user_type);
        
        if(account_maximum_ID<id){
            account_maximum_ID = id;
        }
    }
    public int getAccount_max(){
        account_maximum_ID++;
        return account_maximum_ID;
    }
    public ArrayList<String> viewUsername(){
        return USERNAME;
    }
    public boolean setIndex_accounts(String username,String passwordx){
        password = passwordx;
        index = USERNAME.indexOf(username);
        if(password.equals(PASSWORD.get(index))){
            userType = USER_TYPE.get(index);
            return true;
        }else{
            return false;
        }
    }
    public String getPassword(){
        return password;
    }
    public String getUsert_type(){
        return userType;
    }
    public int getAccount_size(){
        return ID_ACCOUNTS.size();
    }
    public Integer getID_accounts(int x){
        return ID_ACCOUNTS.get(x);
    }
    public String getUsername(int x){
        return USERNAME.get(x);
    }
    public String getPassword(int x){
        return PASSWORD.get(x);
    }
    public String getSecurityQuestion(int x){
        return SECURITY_QUESTION.get(x);
    }
    public String getAnswer(int x){
        return ANSWER.get(x);
    }
    public String getUserType(int x){
        return USER_TYPE.get(x);
    }
    public static boolean isFirstDayOfTheMonth(int year,int month,int day,
            int setDesired_month,int setDesired_year){
        Calendar c = new GregorianCalendar();
        c.set(year, month, day);
        //YEAR MONTH DAY
        if (c.get(Calendar.MONTH) == setDesired_month && c.get(Calendar.YEAR) 
                == setDesired_year) {
          return true;
        }
    return false;
    }
    public int getScannedID_size(){
        return STORE_LOGS_ID_SCANNED.size();
    }
    public String getLastFour(String myString) {
    if(myString.length() > 4)
        return myString.substring(myString.length()-4);
    else
        return myString;
    }
    public void expireYearly_scann(String year){
        STORE_LOGS_ID_SCANNED.clear();
        SET_TOTAL_SELLING_PRICE.clear();
        SET_TOTAL_QUANTITY.clear();
        SET_TOTAL_PRICE.clear();
        
        TOTAL_ID_LOGS.clear();
        TOTAL_QUANTITIES.clear();
        TOTAL_INCOME.clear();
        PRODUCTS_SOLD.clear();
        totalQuantities = 0;
        totalIncome = 0;

        Logs_Panel lp = Logs_Panel.getInstance();
        lp.clearAll_expired_list();
        for(int x=0;x<EXPIRED_PRODUCT_NAME.size();x++){
             String setSplitString = EXPIRED_EXPIRATION_DATE.get(x);

                if(getLastFour(setSplitString).equals(year)){
                        double set_selling_price = Double.parseDouble(EXPIRED_PRICE.get(x));
                        int set_quantity = Integer.parseInt(EXPIRED_QUANTITY.get(x));
                        double set_price = 0;

                        SET_TOTAL_SELLING_PRICE.add(set_selling_price);
                        SET_TOTAL_QUANTITY.add(set_quantity);
                        SET_TOTAL_PRICE.add(set_price);
                        
                lp.expired_addData(EXPIRED_PRODUCT_NAME.get(x),EXPIRED_QUANTITY.get(x),
                            EXPIRED_PRICE.get(x),EXPIRED_DATE_PURCHASE.get(x),EXPIRED_EXPIRATION_DATE.get(x),
                            EXPIRED_PURCHASE_FROM.get(x));
                            System.out.println(EXPIRED_PRODUCT_NAME.get(x)+" is expired yearly!");
                }
            }
            for(int x=0;x<SET_TOTAL_SELLING_PRICE.size();x++){
                    double setTotal_selling_price = SET_TOTAL_SELLING_PRICE.get(x) * SET_TOTAL_QUANTITY.get(x);
                    
                    totalIncome = totalIncome+setTotal_selling_price;
            }
    }
    public void expireMonthly_scann(int year, int month){
        STORE_LOGS_ID_SCANNED.clear();
        SET_TOTAL_SELLING_PRICE.clear();
        SET_TOTAL_QUANTITY.clear();
        SET_TOTAL_PRICE.clear();
        
        TOTAL_ID_LOGS.clear();
        TOTAL_QUANTITIES.clear();
        TOTAL_INCOME.clear();
        PRODUCTS_SOLD.clear();
        totalQuantities = 0;
        totalIncome = 0;
        
        Logs_Panel lp = Logs_Panel.getInstance();
        lp.clearAll_expired_list();
        for(int x=0;x<EXPIRED_PRODUCT_NAME.size();x++){
            TOKENIZE_LOGS.clear();
                String delims = "/";
                String splitString = EXPIRED_EXPIRATION_DATE.get(x);
                
            String[] tokens = splitString.split(delims);
            int tokenCount = tokens.length;
            
            for (int j = 0; j < tokenCount; j++){
                int result = Integer.parseInt(tokens[j]);
                TOKENIZE_LOGS.add(result);
            }

            if(isFirstDayOfTheMonth(TOKENIZE_LOGS.get(2),TOKENIZE_LOGS.get(0),
                TOKENIZE_LOGS.get(1),month,year)==true){
                    //My new Algo code
                double set_selling_price = Double.parseDouble(EXPIRED_PRICE.get(x));
                int set_quantity = Integer.parseInt(EXPIRED_QUANTITY.get(x));
                double set_price = 0;
                
                SET_TOTAL_SELLING_PRICE.add(set_selling_price);
                SET_TOTAL_QUANTITY.add(set_quantity);
                SET_TOTAL_PRICE.add(set_price);

                lp.expired_addData(EXPIRED_PRODUCT_NAME.get(x),EXPIRED_QUANTITY.get(x),
                            EXPIRED_PRICE.get(x),EXPIRED_DATE_PURCHASE.get(x),EXPIRED_EXPIRATION_DATE.get(x),
                            EXPIRED_PURCHASE_FROM.get(x));
                }
        }
            for(int x=0;x<SET_TOTAL_SELLING_PRICE.size();x++){
                    double setTotal_selling_price = SET_TOTAL_SELLING_PRICE.get(x) * SET_TOTAL_QUANTITY.get(x);
                    
                    totalIncome = totalIncome+setTotal_selling_price;
            }
    }
    public boolean check_if_array_is_empty(){
        boolean checker=true;
        
        if(CART_ID.isEmpty()){
            checker=true;
        }else{
            checker=false;
        }
        
        return checker;
    }
    public void monthly_scann(int year,int month){
        STORE_LOGS_ID_SCANNED.clear();
        SET_TOTAL_SELLING_PRICE.clear();
        SET_TOTAL_QUANTITY.clear();
        SET_TOTAL_PRICE.clear();
        
        TOTAL_ID_LOGS.clear();
        TOTAL_QUANTITIES.clear();
        TOTAL_INCOME.clear();
        PRODUCTS_SOLD.clear();
        totalQuantities = 0;
        totalIncome = 0;
        
        for(int x=0;x<LOGS_ID.size();x++){
            TOKENIZE_LOGS.clear();
                String delims = "/";
                String splitString = LOGS_DATE.get(x);
                
            String[] tokens = splitString.split(delims);
            int tokenCount = tokens.length;
            
            for (int j = 0; j < tokenCount; j++){
                int result = Integer.parseInt(tokens[j]);
                TOKENIZE_LOGS.add(result);
            }

            if(isFirstDayOfTheMonth(TOKENIZE_LOGS.get(2),TOKENIZE_LOGS.get(0),
                TOKENIZE_LOGS.get(1),month,year)==true){
                STORE_LOGS_ID_SCANNED.add(LOGS_ID.get(x));
//                double quantities = Integer.parseInt(LOGS_QUANTITY.get(x));
//                double income = Integer.parseInt(LOGS_PAYMENT.get(x));
                    //My new Algo code
                double set_selling_price = 0;
                int set_quantity = Integer.parseInt(LOGS_QUANTITY.get(x));
                double set_price = 0;
                
                if(LOGS_SELLING_PRICE.get(x).equalsIgnoreCase("Free")){
                    set_selling_price = 0;
                    set_price = 0;
                }else{
                    set_selling_price = Double.parseDouble(LOGS_SELLING_PRICE.get(x));
                    set_price = Double.parseDouble(LOGS_PRICE.get(x));
                }
                
                SET_TOTAL_SELLING_PRICE.add(set_selling_price);
                SET_TOTAL_QUANTITY.add(set_quantity);
                SET_TOTAL_PRICE.add(set_price);
                }
        }
            for(int x=0;x<SET_TOTAL_SELLING_PRICE.size();x++){
                    double setTotal_selling_price = SET_TOTAL_SELLING_PRICE.get(x) * SET_TOTAL_QUANTITY.get(x);
                    double setTotal_total_income = SET_TOTAL_PRICE.get(x) * SET_TOTAL_QUANTITY.get(x);
                    double result = setTotal_selling_price - setTotal_total_income;
                    totalQuantities = totalQuantities+SET_TOTAL_QUANTITY.get(x);
                    totalIncome = totalIncome+result;
            }
    }
    public void yearly_Scann(String year){
        STORE_LOGS_ID_SCANNED.clear();
        SET_TOTAL_SELLING_PRICE.clear();
        SET_TOTAL_QUANTITY.clear();
        SET_TOTAL_PRICE.clear();
        
        TOTAL_ID_LOGS.clear();
        TOTAL_QUANTITIES.clear();
        TOTAL_INCOME.clear();
        PRODUCTS_SOLD.clear();
        totalQuantities = 0;
        totalIncome = 0;
        
        ArrayList<Integer> setDate = new ArrayList<Integer>();
        
        for(int x=0;x<LOGS_ID.size();x++){
                setDate.clear();
                String delims = "/";
                String splitString = LOGS_DATE.get(x);
                System.out.println(splitString+" split!");
                
            String[] tokens = splitString.split(delims);
            int tokenCount = tokens.length;
            
            for (int j = 0; j < tokenCount; j++){
                int result = Integer.parseInt(tokens[j]);
                setDate.add(result);
            }
            String set = setDate.get(2)+"";
            if(set.equals(year)){
                double set_selling_price = 0;
                int set_quantity = Integer.parseInt(LOGS_QUANTITY.get(x));
                double set_price = 0;
                
                if(LOGS_SELLING_PRICE.get(x).equalsIgnoreCase("Free")){
                    set_selling_price = 0;
                    set_price = 0;
                }else{
                    set_selling_price = Double.parseDouble(LOGS_SELLING_PRICE.get(x));
                    set_price = Double.parseDouble(LOGS_PRICE.get(x));
                }
                
                SET_TOTAL_SELLING_PRICE.add(set_selling_price);
                SET_TOTAL_QUANTITY.add(set_quantity);
                SET_TOTAL_PRICE.add(set_price);
                }
           }        
                    for(int x=0;x<SET_TOTAL_SELLING_PRICE.size();x++){
                    double setTotal_selling_price = SET_TOTAL_SELLING_PRICE.get(x) * SET_TOTAL_QUANTITY.get(x);
                    double setTotal_total_income = SET_TOTAL_PRICE.get(x) * SET_TOTAL_QUANTITY.get(x);
                    double result = setTotal_selling_price - setTotal_total_income;
                    totalQuantities = totalQuantities+SET_TOTAL_QUANTITY.get(x);
                    totalIncome = totalIncome+result;
            }
    }
    public int getTotal_logs_size(){
        return TOTAL_ID_LOGS.size();
    }
    public void monthly_scann(String month){
        for(int x=0;x<LOGS_DATE.size();x++){
            
        }
    }
    public void daily_scann(String date){
        STORE_LOGS_ID_SCANNED.clear();
        SET_TOTAL_SELLING_PRICE.clear();
        SET_TOTAL_QUANTITY.clear();
        SET_TOTAL_PRICE.clear();

        TOTAL_ID_LOGS.clear();
        TOTAL_QUANTITIES.clear();
        TOTAL_INCOME.clear();
        PRODUCTS_SOLD.clear();
        totalQuantities = 0;
        totalIncome = 0;
        try{

            for(int x=0;x<LOGS_DATE.size();x++){
                if(LOGS_DATE.get(x).equalsIgnoreCase(date)){
                STORE_LOGS_ID_SCANNED.add(LOGS_ID.get(x));
                    //My new Algo code
                double set_selling_price = 0;
                int set_quantity = Integer.parseInt(LOGS_QUANTITY.get(x));
                double set_price = 0;
                
                if(LOGS_SELLING_PRICE.get(x).equalsIgnoreCase("Free")){
                    set_selling_price = 0;
                    set_price = 0;
                }else{
                    set_selling_price = Double.parseDouble(LOGS_SELLING_PRICE.get(x));
                    set_price = Double.parseDouble(LOGS_PRICE.get(x));
                }
                
                SET_TOTAL_SELLING_PRICE.add(set_selling_price);
                SET_TOTAL_QUANTITY.add(set_quantity);
                SET_TOTAL_PRICE.add(set_price);
              }
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }

            for(int x=0;x<SET_TOTAL_SELLING_PRICE.size();x++){
                double setTotal_selling_price = SET_TOTAL_SELLING_PRICE.get(x) * SET_TOTAL_QUANTITY.get(x);
                double setTotal_total_income = SET_TOTAL_PRICE.get(x) * SET_TOTAL_QUANTITY.get(x);
                double result = setTotal_selling_price - setTotal_total_income;
                totalQuantities = totalQuantities+SET_TOTAL_QUANTITY.get(x);
                totalIncome = totalIncome+result;
            }
        }
    public double getTotalQuantities(){
        return totalQuantities;
    }
    public double getTotalIncome(){
        return totalIncome;
    }
    public void updateTotal_units(int id,int unit){
        index = CART_ID.indexOf(id);
        int setIndex = ID.indexOf(CART_PRODUCT_ID.get(index));
        int unitLeft = Integer.parseInt(QUANTITY.get(setIndex));

        String setTotal = unitLeft-unit+"";
        setQuantity = setTotal;
        QUANTITY.set(setIndex, setTotal);
        index = setIndex;
    }
    public String getTotalSubtract_quantity(){
        return setQuantity;
    }
    public ArrayList<String> getTransaction(){
        return LOGS_TRANSACTION_ID;
    }
    public String getTransaction_ID(int x){
        return LOGS_TRANSACTION_ID.get(x);
    }
    public void addLogs(String transaction_id,int id,String time,String date,String quantity,
                        String selling_price,String payment,String cashier,
                        String product_name,String price,String strength,
                        String preparation, String discount_checker){
        LOGS_ID.add(id);
        LOGS_TIME.add(time);
        LOGS_DATE.add(date);
        LOGS_QUANTITY.add(quantity);
        
        if(selling_price.contains("/")){
            String[] parts = price.split("/");
            LOGS_SELLING_PRICE.add(parts[0]);
        }else{
            LOGS_SELLING_PRICE.add(selling_price);
        }
        
        LOGS_PAYMENT.add(payment);
        LOGS_CASHIER.add(cashier);
        LOGS_PRODUCT.add(product_name);
        LOGS_TRANSACTION_ID.add(transaction_id);
        LOGS_PRICE.add(price);
        LOGS_STRENGTH.add(strength);
        LOGS_PREPARATION.add(preparation);
        LOGS_DISCOUNT_CHECKER.add(discount_checker);
        
        if(logs_max_ID<id){
            logs_max_ID = id;
        }
    }
    public void setClear(){
        LOGS_ID.clear();
        LOGS_TIME.clear();
        LOGS_DATE.clear();
        LOGS_QUANTITY.clear();
        LOGS_SELLING_PRICE.clear();
        LOGS_PAYMENT.clear();
        LOGS_CASHIER.clear();
        LOGS_PRODUCT.clear();
        LOGS_TRANSACTION_ID.clear();
        LOGS_PRICE.clear();
        LOGS_DISCOUNT_CHECKER.clear();
    }
    public int setLogs_max_ID(){
        logs_max_ID++;
        return logs_max_ID;
    }
    public int getLogs_size(){
        return LOGS_ID.size();
    }
    public void setIndex_forLogs(int x){
        index = LOGS_ID.indexOf(x);
    }
    public Integer getMonthly_ID(int x){
        return STORE_LOGS_ID_SCANNED.get(x);
    }
    public ArrayList<Integer> getStoreID(){
        return STORE_LOGS_ID_SCANNED;
    }
    public Integer getLog_id(int x){
        return LOGS_ID.get(x);
    }
    public String getLog_time(int x){
        return LOGS_TIME.get(x);
    }
    public String getLog_date(int x){
        return LOGS_DATE.get(x);
    }
    public String getLog_quantity(int x){
        return LOGS_QUANTITY.get(x);
    }
    public String getLog_selling_price(int x){
        return LOGS_SELLING_PRICE.get(x);
    }
    public String getLog_payment(int x){
        return LOGS_PAYMENT.get(x);
    }
    public String getLog_cashier(int x){
        return LOGS_CASHIER.get(x);
    }
    public String getLog_product(int x){
        return LOGS_PRODUCT.get(x);
    }
    public String getLog_price(int x){
        return LOGS_PRICE.get(x);
    }
    public String getLog_discount_checker(int x){
        return LOGS_DISCOUNT_CHECKER.get(x);
    }
    public String getLog_preparation(String id){
        return LOGS_PREPARATION.get(LOGS_TRANSACTION_ID.indexOf(id));
    }
    public void test(){
        System.out.println(LOGS_TRANSACTION_ID.size()+"");
    }
    public String getLog_strength(String id){
        return LOGS_STRENGTH.get(LOGS_TRANSACTION_ID.indexOf(id));
    }
    public String getUser(){
        return userLogin;       
    }
    public String setName_id(int x){
        return CART_PRODUCT_ID.get(x);
    }
    public int setProduct_removeRow(String x){
        return ID.indexOf(x);
    }
    public int setName(String name){
        return CART_PRODUCT_ID.indexOf(name);
    }
    public int getCartSize(){
        return CART_PRODUCT_ID.size();
    }
    public boolean scann_update_Quantity(String id,String quantity){
        index = ID.indexOf(id);
        int result = Integer.parseInt(quantity);
        int quantityx = Integer.parseInt(QUANTITY.get(index));
        if(quantityx<result){
            return false;
        }else{
            return true;
        }
    }
    public void addCart_data(int id,String product_name,String quantity,
                             String selling_price,String preparation,String total,
                             String product_id,String price,String checker,
                             String strength,String brand_name){
        CART_ID.add(id);
        CART_PRODUCT_NAME.add(product_name);
        CART_QUANTITY.add(quantity);
        CART_SELLING_PRICE.add(selling_price);
        CART_PREPARATION.add(preparation);
        CART_TOTAL.add(total);
        CART_PRODUCT_ID.add(product_id);
        CART_PRICE.add(price);
        CART_PRODUCT_CHECKER.add(checker);
        CART_STRENGTH.add(strength);
        CART_BRAND_NAME.add(brand_name);
    }
    public String getProduct_checker(int x){
        return CART_PRODUCT_CHECKER.get(x);
    }
    public void clear_all_cartData(){
        CART_ID.clear();
        CART_PRODUCT_NAME.clear();
        CART_QUANTITY.clear();
        CART_SELLING_PRICE.clear();
        CART_PREPARATION.clear();
        CART_TOTAL.clear();
        CART_PRODUCT_ID.clear();
        CART_PRODUCT_CHECKER.clear();
        CART_PRICE.clear();
        CART_STRENGTH.clear();
        CART_BRAND_NAME.clear();
    }
    public Integer getCart_size(){
        return CART_ID.size();
    }
    public Integer getCart_id(int x){
        return CART_ID.get(x);
    }
    public ArrayList<Integer> getCart_id_list(){
        return CART_ID;
    }
    public String getCart_product_name(int x){
        return CART_PRODUCT_NAME.get(x);
    }
    public String getCart_price(int x){
        return CART_PRICE.get(x);
    }
    public String getCart_quantity(int x){
        return CART_QUANTITY.get(x);
    }
    public String getCart_selling_price(int x){
        return CART_SELLING_PRICE.get(x);
    }
    public String getCart_preparation(int x){
        return CART_PREPARATION.get(x);
    }
    public String getCart_total(int x){
        return CART_TOTAL.get(x);
    }
    public String getCart_product_id(int x){
        return CART_PRODUCT_ID.get(x);
    }
    public String getCart_strength(int x){
        return CART_STRENGTH.get(x);
    }
    public String getCart_brand_name(int x){
        return CART_BRAND_NAME.get(x);
    }
    public void scanExpired_products(){
        for(int x=0;x<ID.size();x++){
            if(DAYS_LEFT.get(x).equals("0")){
                ID_SCANNED_EXPIRED.add(DAYS_LEFT.get(x));
            }
        }
    }
    public void setIndex(String x){
        index = ID.indexOf(x);
    }
    public int getIndex(){
        return index;
    }
    public ArrayList<String> getData_productName_array(){
        return PRODUCT_NAME;
    }
    public void deleteProduct_date(String x){
        index = ID.indexOf(x);
        ID.remove(index);
        PRODUCT_NAME.remove(index);
        QUANTITY.remove(index);
        EXPIRATION_DATE.remove(index);
        PRICE.remove(index);
        SELLING_PRICE.remove(index);
        PRICE_INCREASE.remove(index);
        DATE_PURCHASE.remove(index);
        UNIT.remove(index);
        PREPARATION.remove(index);
        DAYS_LEFT.remove(index);
        PURCHASE_FROM.remove(index);
        BRAND_NAME.remove(index);
        SET_LIMIT.remove(index);
    }
    public int retrieveProduct_size(){
        return ID.size();
    }
    public String retrieveID(int x){
        return ID.get(x);
    }
    public String retrieveProduct_name(int x){
        return PRODUCT_NAME.get(x);
    }
    public String retieveQuantity(int x){
        return QUANTITY.get(x);
    }
    public String retrieveExpiration_date(int x){
        return EXPIRATION_DATE.get(x);
    }
    public String retrievePrice(int x){
        return PRICE.get(x);
    }
    public String retrieveSelling_price(int x){
        return SELLING_PRICE.get(x);
    }
    public String retrievePriceIncrease(int x){
        return PRICE_INCREASE.get(x);
    }
    public String retrieveDate_purchase(int x){
        return DATE_PURCHASE.get(x);
    }
    public String retrieveUnit(int x){
        return UNIT.get(x);
    }
    public String retrievePreparation(int x){
        return PREPARATION.get(x);
    }
    public String retrieveDays_left(int x){
        return DAYS_LEFT.get(x);
    }
    public String retrievePurchase_from(int x){
        return PURCHASE_FROM.get(x);
    }
    public String retrieveBrand_name(int x){
        return BRAND_NAME.get(x);
    }
    public String retrieveLimit(int x){
        return SET_LIMIT.get(x);
    }
    public String getPriceIncreaseByID(String id){
        index = ID.indexOf(id);
        
        return PRICE_INCREASE.get(index);
    }
    public void addData_product(String id,String product_name,String quantity,
            String expiration,String price,String selling_price,String date_purchase,
            String unit,String preparation,String purchase_from,String brand_name,
            String set_limit){
        ID.add(id);
        PRODUCT_NAME.add(product_name);
        QUANTITY.add(quantity);
        EXPIRATION_DATE.add(expiration);
        PRICE.add(price);
        
        String[] parts = selling_price.split("/");
        
        SELLING_PRICE.add(parts[0]);
        PRICE_INCREASE.add(parts[1]);
        DATE_PURCHASE.add(date_purchase);
        UNIT.add(unit);
        PREPARATION.add(preparation);
        PURCHASE_FROM.add(purchase_from);
        BRAND_NAME.add(brand_name);
        SET_LIMIT.add(set_limit);
        
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        
                String dateToday = dateFormat.format(date);
		String expiryDate = expiration;

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

		Date d1 = null;
		Date d2 = null;

		try {
			d1 = format.parse(dateToday);
			d2 = format.parse(expiryDate);

			//in milliseconds
			long diff = d2.getTime() - d1.getTime();

			long diffDays = diff / (24 * 60 * 60 * 1000);
        DAYS_LEFT.add(diffDays+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public void updateData_product_list(String id,String product_name,String quantity,
            String expiration,String price,String selling_price,String date_purchase,
            String unit,String preparation,String purchase_from,String brand_name,String limit,
            String price_increase){
        index = ID.indexOf(id);
        PRODUCT_NAME.set(index,product_name);
        QUANTITY.set(index,quantity);
        EXPIRATION_DATE.set(index,expiration);
        PRICE.set(index,price);
        SELLING_PRICE.set(index,selling_price);
        PRICE_INCREASE.set(index,price_increase);
        DATE_PURCHASE.set(index,date_purchase);
        UNIT.set(index,unit);
        PREPARATION.set(index,preparation);
        PURCHASE_FROM.set(index,purchase_from);
        BRAND_NAME.set(index,brand_name);
        SET_LIMIT.set(index,limit);
        
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        
                String dateToday = dateFormat.format(date);
		String expiryDate = expiration;

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

		Date d1 = null;
		Date d2 = null;

		try {
			d1 = format.parse(dateToday);
			d2 = format.parse(expiryDate);

			//in milliseconds
			long diff = d2.getTime() - d1.getTime();
			long diffDays = diff / (24 * 60 * 60 * 1000);

        DAYS_LEFT.set(index,diffDays+"");
		} catch (Exception e) {
			e.printStackTrace();
	  }
    }
    public void setUserLogin(String userLogin){
        this.userLogin = userLogin;
        isLogin = true;
    }    
    public String retrieveUserLogin(){
        return userLogin;
    }
    public ArrayList<String> getProduct_ID(){
        return ID;
    }
    public void print(){
        System.out.println("Cart selling: "+CART_TOTAL);
    }
}