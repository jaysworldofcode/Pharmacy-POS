package msinventorysystem;

public class ConvertExpiredDateFormatToZonedDateTimeFormat {
    
    String convertedDate = "";
    
    public ConvertExpiredDateFormatToZonedDateTimeFormat(String date){
        String[] tokenizeString = date.split("/");
        
        if(tokenizeString[0].length()==1){
            tokenizeString[0] = "0"+tokenizeString[0];
        }
        if(tokenizeString[1].length()==1){
            tokenizeString[1] = "0"+tokenizeString[1];
        }
        convertedDate = tokenizeString[2]+"-"+tokenizeString[0]+"-"+tokenizeString[1];
        System.out.println("Converted date is :"+convertedDate);
    }
    public String getConvertedData(){
        return convertedDate;
    }
}