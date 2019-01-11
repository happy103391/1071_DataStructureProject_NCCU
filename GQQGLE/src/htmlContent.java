import java.*;
import java.applet.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
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
	// 获取img标签正则
	private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
	// 获取src路径的正则
	private static final String IMGSRC_REG = "[a-zA-z]+://[^\\s]*";

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
			System.out.println("OMG");
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

	/*
	 * public int countPic(String content) throws IOException { if(content==null){
	 * content=fetchContent(); } int pcount=0;
	 * 
	 * String img ="<img class="; String img1 ="<img alt src="; int j =
	 * content.indexOf(img); while(j!=-1) { pcount++; content=content.substring(j+
	 * img.length(), content.length()); j = content.indexOf(img); } int k =
	 * content.indexOf(img1); while(k!=-1) { pcount++;
	 * content=content.substring(k+img1.length(), content.length()); k =
	 * content.indexOf(img1); } return pcount; }
	 */
	public String getHtml() throws Exception {
		String line;
		StringBuffer sb = new StringBuffer();
		try {
			URL url1 = new URL(this.urlStr);
			URLConnection connection = url1.openConnection();

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
			System.out.println("WTF");
		}
		return sb.toString();
	}

	public List<String> getImageUrl(String content) {
		Matcher matcher = Pattern.compile(IMGURL_REG).matcher(content);
		List<String> listimgurl = new ArrayList<String>();
		while (matcher.find()) {
			listimgurl.add(matcher.group());
		}
		return listimgurl;
	}

	// 获取ImageSrc地址
	public int getImageSrc(List<String> listimageurl) {
		List<String> listImageSrc = new ArrayList<String>();
		for (String image : listimageurl) {
			Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(image);
			while (matcher.find()) {
				listImageSrc.add(matcher.group().substring(0, matcher.group().length() - 1));
			}
		}
		return listImageSrc.size();
	}

}