import java.*;
import java.applet.*;
public class PictureCounter {			//(1)圖片"img"越多越好(2)"gif"比重佔多少
	public String img;
	public String gif;
	public int imgcount;	
	public int gifcount;
	public int score;				//=((imgcount*imgweigh)t+(gifcount+gifweight))
	public int imgweight = 1;
	public int gifweight = 2;
	//public double picweight;

	public PictureCounter(String img, int piccount, int picweight) {
		this.img = img;
		//this.piccount = piccount;
		//this.picweight = picweight;
	}
	
	
}
