import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.text.Document;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class WebPage extends htmlContent  {
	

	public String url;
	public String name;
	public Counter counter;
	public static String content;
	public GoogleQuery googleQuery;
	public double score;
	
	public WebPage(String sublink) {		//網址和關鍵字
		super(content);
		this.url = url;
		this.content=content;
		this.counter = new Counter(url);
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
	/*public String findSubLinks() throws IOException {
		if(content == null) {
	    	content = fetchContent();    //去擷取內容
	     }
		int indexStart =0;
		indexStart =indexStart+ content.indexOf("a href=");
		int indexEnd = content.indexOf("\">");
		String sublink ="http://"+content.substring(indexStart+8, indexEnd);
	    
		return sublink;
	}*/
	public double calculate(int wcount,int pcount) {
		
		double score = (wcount*10)+(pcount*8);
		return score;
	}
	
	public ArrayList<String> finder() throws IOException{		//findSubLinks
		ArrayList<String>linklist = new ArrayList<String>();	//把所有抓到的子網頁放到arrayList裡
		if(content == null) {
			content = fetchContent();
		}
		
		org.jsoup.nodes.Document document = Jsoup.parse(url);	//抓子網頁裡的整篇html
		//Elements content = document.select("a");	
		Elements links = document.select("a[href]");
		
		for(Element link:links) {
			
			//String linkHref = link.attr("href");	//取得子網頁的網址
			String linkHref = link.text();
			if(linkHref.contains("http")!=true) {
				linkHref = url +"/" +  linkHref;  
			}
			linklist.add(linkHref);
			System.out.println(linkHref);
		}
		
		return linklist;
	}
	
	
	/*public void setScore(ArrayList<Keyword> keywords) throws IOException {
		this.score = 0;
		
		for(Keyword k: keywords) {										//從keywords陣列中的第一個數取到最後一個數
			this.googleQuery = new GoogleQuery(k.name);
			this.score += counter.countKeyword(k.name) * k.weight;		
		}
	}*/
	
	
}


