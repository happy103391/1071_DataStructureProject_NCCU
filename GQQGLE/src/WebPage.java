import java.io.IOException;
import java.util.ArrayList;

public class WebPage  {
	public String url; 		//網址
	public String name;		//網站名稱
	public Counter counter;
	public double score;   //最後算出來的分數
	
	public WebPage(String url,String name) {   
		this.url = url;
		this.name = name;
		this.counter = new Counter(url);
	}
	public void setScore(ArrayList<Keyword> keywords) throws IOException {
		this.score = 0;   //歸零
		for(Keyword k :keywords) {     //傳入keyword，要先判斷k是否屬於keyword
			this.score+=counter.countKeyword(k.name)*(k.weight);  //累加
		}
	}
}