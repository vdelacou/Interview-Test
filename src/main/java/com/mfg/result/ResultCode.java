package com.mfg.result;

public enum ResultCode {  
    SUCCESS("200", "success"),    
   
      
    ERROR("500", "fail");  
      
    private ResultCode(String value, String msg){  
        this.val = value;  
        this.msg = msg;  
    }  
      
    public String val() {  
        return val;  
    }  
  
    public String msg() {  
        return msg;  
    }  
      
    private String val;  
    private String msg;  
}  