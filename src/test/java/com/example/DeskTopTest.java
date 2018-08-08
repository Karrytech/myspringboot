package com.example;
   
import java.awt.*;
import java.net.URI;  
   
   
   
public class DeskTopTest {  
       
    private static Desktop desktop;  
   
    //使用默认的浏览器打开网页   
    public static void browse(){  
        if (Desktop.isDesktopSupported()) {  
            desktop = Desktop.getDesktop();  
            try {  
                //URI指定网页的地址   
                desktop.browse(new URI("https://blog.csdn.net/weixin_42156742/article/details/81383628"));
            } catch (Exception e) {  
                // TODO: handle exception   
                e.printStackTrace();  
            }  
        }  
    }  

    /** 
     * @param args 
     */ 
    public static void main(String[] args) {  
        // TODO Auto-generated method stub   
   
        browse();  
    }
   
}  