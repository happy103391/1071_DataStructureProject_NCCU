import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleQuery {           //找關鍵字
	public String searchKeyword; 	//要找的關鍵字
	public String url;				//網址
	public String content;			 //爬下來的網頁內容
	
	public GoogleQuery(String searchKeyword) {
		this.searchKeyword= searchKeyword;
		this.url= "https://www.google.com/search?q="+ searchKeyword +"&oe=utf8&num=10";
	}										//搜尋結果＋	關鍵字+		編碼（輸入中文字後不會跑出亂碼）＋搜尋筆數
	private String fetchContent()throws IOException {
		           
		String retVal = "";
		URL urlStr = new URL(this.url);		//把使用者的URL 傳進去
		URLConnection connection = urlStr.openConnection();		//連結它
		connection.setRequestProperty("User-Agent", "Mozilla/5.0(Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
		//設定一些property，假裝自己是使用者，如果發現你是機器人，他就會鎖你IP，你的網路就爬不到任何東西
		//"User-Agent"：設一個user address
		//設一個瀏覽器編號、電腦編號（隨便設），一開始沒設定好的話之後可能沒辦法連。
		
		connection.connect();		//連結
		InputStream inputStream = connection.getInputStream();    //去拿他的input Stream
		InputStreamReader inReader = new InputStreamReader(inputStream,"UTF8");  //讀完這個input stream後，轉成UTF8的形式
		BufferedReader bf = new BufferedReader(inReader);	//用buffer reader去讀（一次讀一行）
		
		String line = null;
		while((line=bf.readLine())!=null) {		//所以用一個while迴圈，把她一行一行讀出來，如果還有東西，就繼續讀
			retVal+=line;
		}
		return retVal;		//讀完後加到retVal裡面
	}
	
	public HashMap<String,String>query() throws IOException{	 //用Jsoup去分析它
		if(this.content==null) {			 				//看是不是空的
			this.content=fetchContent();					//去抓網頁內容資料
		}
		HashMap<String,String>retVal= new HashMap<String,String>();	//String：網頁的title、網址
		Document document = Jsoup.parse(this.content);	//Document：用jsoup去跑（parse）內容
		Elements lis = document.select("div.g");		//用select這個function，可以select HTML裡面的tag，抓div這個tag，他的class叫做g
		
		for(Element li:lis) {			//用for迴圈把每個抓到的element拿出來
			try {					//抓的時候可能會有錯誤，所以用try catch，程式才不會中斷
				Element h3 = li.select("h3.r").get(0);  //每個g class裡面會包很多個小tag，去抓第一個叫做「r」的class，裡面有個tag（叫做h3）
				String title = h3.text();   //這就是那個網頁的title，要抓他的文字，所以再用text()
				
				Element cite = li.select("cite").get(0);   //放在一個叫做「cite」的tag裡面，也是抓第一個
				String citeUrl= cite.text();				//轉成文字的部分
				System.out.println(title+" "+citeUrl);		//將title和網址印出來
				retVal.put(title,citeUrl);					//將title和網址放入retVal裡面
			}catch(IndexOutOfBoundsException e) {
				//do nothing
			}
		}
		return retVal;
	}
}
