package com.dsideal.jbp.login;

import org.eclipse.jface.dialogs.IInputValidator;

public class MyValidator implements IInputValidator{   
    public String isValid(String newText){   
        float value = 0;   
        try{   
             value = Float.valueOf(newText).floatValue();   
         }catch(NumberFormatException e){   
            return "错误:请输入数值";   
         }   
        if(value>0 && value<100){   
            return null;   
         }else{   
            return "错误：请输入大于0，小于100的数";   
         }   
     }   
}