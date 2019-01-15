import java.*;
import java.applet.*;
import java.io.*;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.*;

public class htmlContent { // 擷取html的原始碼 找圖片
	private String urlStr; // 這是網址
	protected String content; // html原始碼的內容

	URL url;
	HttpURLConnection urlCon;
	BufferedReader urlIn;
	String str;
	private ArrayList<WebTree> urlTree;   //以url作為樹
	public static Set<String> alloverurl = new HashSet<String>();
	
	// 获取img标签正则
	private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
	// 获取src路径的正则
	private static final String IMGSRC_REG = "[a-zA-z]+://[^\\s]*";
	
	public static final double maxdepth =2;

	public htmlContent(String urlStr) {
		this.urlStr = urlStr;
		
	}

	public String fetchContent() { // 網頁上的內容
		String retVal = " ";
		String line = null;
		try {
			URL url = new URL(this.urlStr); // 把使用者輸進去的網址傳入新的object裡，也就是url
			URLConnection uc = url.openConnection(); // 檢查網址的狀態是否存在or無法存取
			InputStream is = uc.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is)); // 需要BufferedReader去讀網頁內容

			while ((line = br.readLine()) != null) {
				retVal = retVal + line + "\n";
			}
		} catch (Exception e) {
			//System.out.println("OMG");
		}
		return retVal;
	}

	
	
	public int countKeyword(String keyword) throws IOException { // 找關鍵字
		if (content == null) {
			content = fetchContent(); // 去擷取內容
		}

		content = content.toUpperCase(); // 把格式變一樣(都大寫)
		keyword = keyword.toUpperCase();

		// 找出這個content有幾個keyword
		// to do : indexOf(keyword)
		int wcount = 0;
		int i = content.indexOf(keyword);
		while (i != -1) {
			wcount++;
			content = content.substring(i + keyword.length(), content.length()); // (從 i+keyword 開始,在content之前結束)
			i = content.indexOf(keyword);

		}
		return wcount;
	}
	
	//找圖片囉
	public String getHtml() throws Exception {
		String line;
		StringBuffer sb = new StringBuffer();
		try {
			URL url1 = new URL(this.urlStr);
			URLConnection connection = url1.openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0(Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
			InputStream in = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(isr);

			while ((line = br.readLine()) != null) {
				sb.append(line, 0, line.length());
				sb.append('\n');
			}
			br.close();
			isr.close();
			in.close();
		} catch (Exception e) {
			//System.out.println("WTF");
		}
		return sb.toString();
	}

	public List<String> getImageUrl(String content) {     //找到圖片的url
		Matcher matcher = Pattern.compile(IMGURL_REG).matcher(content);
		List<String> listimgurl = new ArrayList<String>();
		while (matcher.find()) {
			listimgurl.add(matcher.group());
		}
		return listimgurl;
	}

	// 得到ImageSrc地址
	public int getImageSrc(List<String> listimageurl ) {   //找到圖片的來源
		List<String> listImageSrc = new ArrayList<String>();
		for (String image : listimageurl) {
			Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(image);
			while (matcher.find()) {
				listImageSrc.add(matcher.group().substring(0, matcher.group().length() - 1));
			}
		}
		return listImageSrc.size();   //這就是圖片數量
	}
	
	public static List<String> workurl(String strurl,int depth){
		ArrayList<String> subWeb = new ArrayList<String>();
	       
			//判斷當前url是否爬取過
	         if(!(alloverurl.contains(strurl)||depth>maxdepth)){
	          //創建url爬取核心對象
	         try {
	             URL url=new URL(strurl);
	              //通過url創建與網頁的連接
	              URLConnection conn=url.openConnection();
	              conn.setRequestProperty("User-Agent", "Mozilla/5.0(Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
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
	                     // allwaiturl.add(href);
	                      subWeb.add(href);
	                      //allurldepth.put(href,depth+1);
	                          }
	                  
	                      }
	                  
	                  }
	              //pw.close();
	              br.close();
	        } catch (Exception e) {
	        	//do nothing
	          }
	         
	     }return subWeb;
	}

	

}
	
	
