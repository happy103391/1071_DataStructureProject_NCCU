import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;
import javax.swing.text.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebPage extends htmlContent {

	public String url;
	public String name;
	public Counter counter;
	public static String content;
	public GoogleQuery googleQuery;
	public double score;
	private static List<String> allwaiturl=new ArrayList<>();
	private static Set<String> alloverurl=new HashSet<>();
	private static Map<String,Integer> allurldepth=new HashMap<>();
	private static int maxdepth=2;
	
	public WebPage(String url) { // 網址和關鍵字
		super(content);
		this.url = url;
		this.content = content;
		// this.counter = new Counter(url);
	}
	
	/*public ArrayList<String> finder() throws IOException { // findSubLinks
		ArrayList<String> linklist = new ArrayList<String>(); // 把所有抓到的子網頁放到arrayList裡
		if (content == null) {
			content = fetchContent();
		}
	try {
		org.jsoup.nodes.Document document = Jsoup.parse(url); // 抓子網頁裡的整篇html
		//Element content = document.getElementById("content");
		Elements links = document.select("a");

		for (Element link : links) {
			
			String linkHref = link.attr("href"); //取得子網頁的網址
			//String linkText = link.text();
			if (linkHref.contains("http") != true) {
				linkHref = url + "/" + linkHref;
				break;
			}
			linklist.add(linkHref);
			System.out.println(linkHref);
		}
	} catch (Exception e) {
		System.out.println("no");
	}
		
		return linklist; 
		
	}
		
	}*/
	public static List<String> workurl(String strurl,int depth){
	       //判斷當前url是否爬取過
	         if(!(alloverurl.contains(strurl)||depth>maxdepth)){
	          //創建url爬取核心對象
	         try {
	             URL url=new URL(strurl);
	              //通過url創建與網頁的連接
	              URLConnection conn=url.openConnection();
	              //通過鏈接取得網頁返回的數據
	              InputStream is=conn.getInputStream();
	              
	              System.out.println(conn.getContentEncoding());
	             //一般按行讀取網頁數據，並進行內容分析
	              //因此用BufferedReader和InputStreamReader把字節流轉化為字符流的緩衝流
	              //進行轉換時，需要處理編碼格式問題
	              BufferedReader br=new BufferedReader(new InputStreamReader(is,"GB2312"));
	          
	              //按行讀取並打印
	              String line=null;
	             //正則表達式的匹配規則提取該網頁的鏈接
	              Pattern p=Pattern.compile("<a .*href=.+</a>");
	              //創建一個輸出流，用於保存文檔,文檔名為執行時間，以防重複
	              //PrintWriter pw=new PrintWriter(new File(savepath+System.currentTimeMillis()+".txt"));
	              
	              while((line=br.readLine())!=null){
	                  //System.out.println(line);
	                  //編寫正則，匹配超鏈接地址
	                  //pw.println(line);
	                  Matcher m=p.matcher(line);
	                  while(m.find()){
	                      String href=m.group();
	                      //找到超鏈接地址並截取字符串
	                      //有無引號
	                      href=href.substring(href.indexOf("href="));
	                      if(href.charAt(5)=='\"'){
	                          href=href.substring(6);
	                      }else{
	                          href=href.substring(5);
	                      }
	                      //截取到引號或者空格或者到">"結束
	                  try{
	                      href=href.substring(0,href.indexOf("\""));
	                  }catch(Exception e){
	                      try{
	                          href=href.substring(0,href.indexOf(" "));
	                      }catch(Exception e1){
	                          href=href.substring(0,href.indexOf(">"));
	                      }
	                  }
	                  if(href.startsWith("http:")||href.startsWith("https:")){
	                      //輸出該網頁存在的鏈接
	                      //System.out.println(href);
	                      //將url地址放到隊列中
	                      allwaiturl.add(href);
	                      allurldepth.put(href,depth+1);
	                          }
	                  
	                      }
	                  
	                  }
	              //pw.close();
	              br.close();
	        } catch (Exception e) {
	              // TODO Auto-generated catch block
	             System.out.println("QQ");
	          }
	         
	        //將當

	
	/*
	 * public int countKeyword(String keyword) throws IOException { //找關鍵字
	 * if(content == null) { content = fetchContent(); //去擷取內容 }
	 * 
	 * content = content.toUpperCase(); //把格式變一樣(都大寫) keyword =
	 * keyword.toUpperCase();
	 * 
	 * //找出這個content有幾個keyword //to do : indexOf(keyword) int wcount = 0; int i =
	 * content.indexOf(keyword); while(i!=-1) { wcount++; content =
	 * content.substring(i + keyword.length(), content.length()); //(從 i+keyword
	 * 開始,在content之前結束) i = content.indexOf(keyword);
	 * 
	 * }return wcount; }
	 * 
	 * 
	 * public int countPic(String content) throws IOException { if(content==null){
	 * content=fetchContent(); } int pcount=0;
	 * 
	 * String img ="<img class="; String img1 ="<img alt src="; int j =
	 * content.indexOf(img); while(j!=-1) { pcount++; content=content.substring(j+
	 * img.length(), content.length()); } int k = content.indexOf(img1);
	 * while(k!=-1) { pcount++; content=content.substring(k+img1.length(),
	 * content.length()); } return pcount; } /*public String findSubLinks() throws
	 * IOException { if(content == null) { content = fetchContent(); //去擷取內容 } int
	 * indexStart =0; indexStart =indexStart+ content.indexOf("a href="); int
	 * indexEnd = content.indexOf("\">"); String sublink
	 * ="http://"+content.substring(indexStart+8, indexEnd);
	 * 
	 * return sublink; }
	 */
	/*
	 * public double calculate(int wcount,int pcount) { double score =
	 * (wcount*10)+(pcount*4); //關鍵字的比重比較多，圖片的比重少一點 return score; }
	 */

	

	/*
	 * public void setScore(ArrayList<Keyword> keywords) throws IOException {
	 * this.score = 0;
	 * 
	 * for(Keyword k: keywords) { //從keywords陣列中的第一個數取到最後一個數 this.googleQuery = new
	 * GoogleQuery(k.name); this.score += counter.countKeyword(k.name) * k.weight; }
	 * }
	 */
	         }return allwaiturl;
	}
}
	
	

	     