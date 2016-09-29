package com.mfg.result;

public class JsonResult {  
    
    private String code;  
    private String message;  
      
    public JsonResult() {  
        this.setCode(ResultCode.SUCCESS);  
        this.setMessage("success£¡");  
          
    }  
      
    public JsonResult(ResultCode code) {  
        this.setCode(code);  
        this.setMessage(code.msg());  
    }  
      
    public JsonResult(ResultCode code, String message) {  
        this.setCode(code);  
        this.setMessage(message);  
    }  
      
    public JsonResult(ResultCode code, String message, Object data) {  
        this.setCode(code);  
        this.setMessage(message);  
    }  
      
    public String getCode() {  
        return code;  
    }  
    public void setCode(ResultCode code) {  
        this.code = code.val();  
    }  
    public String getMessage() {  
        return message;  
    }  
    public void setMessage(String message) {  
        this.message = message;  
    }  
  
   
}  