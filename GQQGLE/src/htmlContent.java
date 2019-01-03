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
    public void match() throws IOException {     //也要拋出一個
		if(content==null) {            //如果沒有HTML內容
			content = fetchContent();      //去抓取html的原始碼
			
		}
		int FirstIndex = content.indexOf(">");
		content = content.substring(FirstIndex+1, content.length());  //從<!doctype> 後的內容開始搜尋
		content = content.replaceAll("<meta>", "");    //忽略掉meta
		
		
		Stack<String> tagStack = new Stack<>();      //先傳進去的東西會最後出來
		
		int indexOfOpen =0;      //一開始先歸零
		
		
		while((indexOfOpen = content.indexOf("<img class=", indexOfOpen))!=-1){    //找第一個tag在哪，前綴
			int indexOfClose = content.indexOf("alt=\"Post images\">", indexOfOpen);      //找與他相對應的後綴
			String fullTag = content.substring(indexOfOpen, indexOfClose);   //這是指前、後綴中間夾的內容		
   }
 }
}
    
    
	

