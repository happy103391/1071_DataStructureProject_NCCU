import java.*;
import java.applet.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Stack;
import java.net.*;
public class htmlContent {   //擷取html的原始碼  找圖片
    private String urlStr;  //這是網址
    protected String content; //html原始碼的內容
    
    URL url;
    HttpURLConnection urlCon;
    BufferedReader urlIn;
    String str;
  
    public htmlContent(String urlStr) {
    	this.urlStr = urlStr;
    }
    public String fetchContent()throws IOException {    //網頁上的內容
		URL url = new URL(this.urlStr);               //把使用者輸進去的網址傳入新的object裡，也就是url
		URLConnection uc = url.openConnection();      //檢查網址的狀態是否存在or無法存取
		InputStream is = uc.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is)); //需要BufferedReader去讀網頁內容
		
		String retVal = " ";
		String line = null;
		while((line = br.readLine())!=null) {
			retVal = retVal + line + "\n";
		}
		
		return retVal;
	}
    public int countKeyword(String keyword) throws IOException {   //找關鍵字
		if(content == null) {
	    	content = fetchContent();    //去擷取內容
	    }
	    	
		content = content.toUpperCase();   //把格式變一樣(都大寫)
	    keyword = keyword.toUpperCase();
	    
	    	//找出這個content有幾個keyword
	    	//to do : indexOf(keyword)
	    	int wcount = 0;
	    	int i = content.indexOf(keyword);
	    	while(i!=-1) {
	    		wcount++;
	    	    content = content.substring(i + keyword.length(), content.length()); //(從 i+keyword 開始,在content之前結束)
	    	    i = content.indexOf(keyword);

	    	 }return wcount;
	      }
	 
   
	public int countPic(String content) throws IOException {
		if(content==null){				
			content=fetchContent();
		}
		 int pcount=0;
		 
		 String img ="<img class=";
		 String img1 ="<img alt src=";
		 int j = content.indexOf(img);
		 while(j!=-1) {
			 pcount++;
			 content=content.substring(j+ img.length(), content.length());
		 }
		 int k = content.indexOf(img1);
		 while(k!=-1) {
			 pcount++;
			 content=content.substring(k+img1.length(), content.length());
		 }
		 return pcount;
	 }
	
	
}
    
    
	

