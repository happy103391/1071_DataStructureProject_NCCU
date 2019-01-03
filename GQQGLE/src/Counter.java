import java.io.IOException;
import java.net.URL;
import java.util.Stack;

public class Counter extends htmlContent  {
	private String searchKeyword;
	public Counter(String searchKeyword) {
		super(searchKeyword);
		this.searchKeyword=searchKeyword;	 
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
	 /*public double calculate(int wcount,int pcount) {
		 double score = (wcount*10)+(pcount*8);
		 return score;
	 }

	    	}
	    	return wcount;

	}*/
	
	/*public int countPic(String url) {
		int pcount = 0;
		String img = "<img class=";
		int j = content.indexOf(img);
		while(j!=-1) {
			 pcount++;
			 content = content.substring(j+ img.length(), content.length());
			 j = content.indexOf(img);
		}
		return pcount;
	}*/
	
	public double calculate(int wcount,int pcount) {
		double score = (wcount*10)+(pcount*8);
		return score;
	}
	
	public String findSubLinks() throws IOException {
		if(content == null) {
	    	content = fetchContent();    //去擷取內容
	    }
	    	
		String subLink;
	}
}

