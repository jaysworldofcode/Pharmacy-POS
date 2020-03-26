package msinventorysystem;

import java.awt.Dimension;

public class StringToDimension {
    
    public Dimension convertToDimension(String unconvertedDimension){
        return new Dimension(
                Integer.parseInt(unconvertedDimension.split(",")[0]),
                Integer.parseInt(unconvertedDimension.split(",")[1])
        );
    }    
}