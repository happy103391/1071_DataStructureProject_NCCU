import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		Scanner sc= new Scanner(System.in);
		while(sc.hasNextLine()) {
			String keyword =sc.next();
			GoogleQuery googleQuery = new GoogleQuery(keyword);
			googleQuery.query();
			//int a = 0;
			
//			WebPage a1 = new WebPage(a[0]);`
//			WebNode a1Node = new WebNode(a1);
//			String a1child1 = "";
//			WebPage a1child1111 = new WebPage(a1child1);
//			WebNode a1Childnode = new WebNode(a1child1111);
//			a1Node.addChild(a1Childnode);
			
			
		}
		sc.close();
	}
}


