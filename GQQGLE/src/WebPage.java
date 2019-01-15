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
	
	public static String content;
	public GoogleQuery googleQuery;
	public double score;
	private static List<String> allwaiturl=new ArrayList<String>();
	private static Set<String> alloverurl=new HashSet<String>();
	private static Map<String,Integer> allurldepth=new HashMap<String, Integer>();
	private static int maxdepth=2;
	public String keyword;
	public ArrayList<String> vList;
	
	public WebPage(String url) { // 網址和關鍵字
		super(content);
		this.url = url;
		this.content = content;
		this.keyword = keyword;
		this.alloverurl = alloverurl;
		
		// this.counter = new Counter(url);
	}
	
	
	
	public double getScore() throws Exception  {						//把google的搜尋結果抓出來後，算圖片，算他的分數
		try {
			for (int i = 0; i < vList.size(); i++) {
			htmlContent h = new htmlContent(vList.get(i));
			int wordcount;
			wordcount = h.countKeyword(keyword);
			String HTML = h.getHtml();
			// 获取图片标签
			List imgUrl = h.getImageUrl(HTML);
			// 获取图片src地址
			int imgcount = h.getImageSrc(imgUrl); // 算圖片個數
			double score = imgcount*200	+	wordcount*10 ;
		
			} 
		}catch (IOException e) {
				
			}
		
			return score;
	}
}

	
	

	          