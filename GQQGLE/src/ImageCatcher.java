import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ImageCatcher {
	private String urlStr;
	private String content;
    private int g = 0;
	public ImageCatcher(String urlStr) {
		this.urlStr = urlStr;
	}

	private String fetchContent() throws IOException{
		URL url = new URL(this.urlStr);
		URLConnection conn = url.openConnection();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
		
		String retVal = " ";
		String line  =  null;
		
		while ((line = br.readLine())!=null) {
			retVal = retVal+line+"\n";
		}
		return retVal;
		}
	
	public int countImage() throws IOException {

		if(content == null) {
			content = fetchContent();
		}
		//content = content.toUpperCase();
		while(content.indexOf("<img")>-1) {
			content = content.substring(content.indexOf("<img")+4);
			
			
		//	while(content.indexOf("img")>-1) {
		//		content = content.substring(content.indexOf("img")+3);
			//if(content.indexOf("https:")>-1) {
			//content = content.substring(content.indexOf("https:")+6);
			//	while(content.indexOf(">")>-1) {
			//		content = content.substring(content.indexOf(">")+1);
					g++;
				}
			
	//	}
			
			
		//}
		
		
		return g;
		
	}

	public String showContent() throws IOException {
		// TODO Auto-generated method stub

		if(content == null) {
			content = fetchContent();
		}
		return content;
	}
	
}
