import java.io.IOException;
import java.util.ArrayList;

public class WebPage {
	public String url;
	public String name;
	public Counter counter;
	public GoogleQuery googleQuery;
	public double score;
	
	public WebPage(String url, String name) {		//網址和關鍵字
		this.url = url;
		this.name = name;
		this.counter = new Counter(url);
	}
	
	public void setScore(ArrayList<Keyword> keywords) throws IOException {
		this.score = 0;
		
		for(Keyword k: keywords) {										//從keywords陣列中的第一個數取到最後一個數
			this.googleQuery = new GoogleQuery(k.name);
			this.score += counter.countKeyword(k.name) * k.weight;		
		}
	}
	
	
}

