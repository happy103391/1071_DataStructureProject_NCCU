import java.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.*;
public class htmlKeyword {   //擷取html的原始碼
    private String urlStr;  //這是網址
    private String content; //html原始碼的內容
    
    URL url;
    HttpURLConnection urlCon;
    BufferedReader urlIn;
    String str;
   
    
    public htmlKeyword(String urlStr) { 
    	this.urlStr = urlStr;
    }
    
}
    
    
	

