import java.*;
import java.applet.*;
import java.awt.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
public class PictureCounter {			//(1)圖片"img"越多越好(2)"gif"比重佔多少
	public String img;
	//public String gif;
	public int imgcount;	
	//public int gifcount;
	public int score;				//=((imgcount*imgweigh)t+(gifcount+gifweight))
	public int imgweight = 1;
	//public int gifweight = 2;
	//public double picweight;

	public PictureCounter(String img, int piccount, int picweight) {
		this.img = img;
		//this.piccount = piccount;
		//this.picweight = picweight;
	}
	


	
	/*private ArrayList<String> getImageUrl(String html){
		String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
        Matcher matcher=Pattern.compile(IMGURL_REG).matcher(html);
        ArrayList<String>listimgurl=new ArrayList<String>();
        while (matcher.find()){
            listimgurl.add(matcher.group());
        }
        return listimgurl;
    }

    //获取ImageSrc地址
    private ArrayList<String> getImageSrc(ArrayList<String> listimageurl){
    	 String IMGSRC_REG = "http:\"?(.*?)(\"|>|\\s+)";
        ArrayList<String> listImageSrc=new ArrayList<String>();
        for (String image:listimageurl){
            Matcher matcher=Pattern.compile(IMGSRC_REG).matcher(image);
            while (matcher.find()){
                listImageSrc.add(matcher.group().substring(0, matcher.group().length()-1));
            }
        }
        return listImageSrc;
    }*/

	
}
