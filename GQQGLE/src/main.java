import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		/*WebPage rootPage = new WebPage("https://www.google.com.tw/webhp?hl=zh-TW&dcr=0&sa=X&ved=0ahUKEwic9t2e6dbfAhWCdd4KHd8JDycQPAgH");
		WebTree tree = new WebTree(rootPage);*/
		
	/*tree.root.addChild(new WebNode(new WebPage(rootPage.finder().get(0))));
	tree.root.addChild(new WebNode(new WebPage(rootPage.finder().get(1))));
	tree.root.children.get(0).addChild(new WebNode(new WebPage(rootPage.finder().get(0))));*/
		
		WebPage a1 = new WebPage("https://www.google.com.tw/webhp?hl=zh-TW&dcr=0&sa=X&ved=0ahUKEwic9t2e6dbfAhWCdd4KHd8JDycQPAgH");
		WebNode a1Node = new WebNode(a1);
		WebPage a11 = new WebPage(a1.finder().get(0));
		WebNode a11Node = new WebNode(a11);
		a1Node.addChild(a11Node);
		WebPage a12 = new WebPage(a1.finder().get(1));
		WebNode a12Node = new WebNode(a12);
		a1Node.addChild(a12Node);
		WebPage a111 = new WebPage(a11.finder().get(0));
		WebNode a111Node = new WebNode(a111);
		WebPage a112 = new WebPage(a11.finder().get(1));
		WebNode a112Node = new WebNode(a112);
		WebPage a121 = new WebPage(a12.finder().get(0));
		WebNode a121Node = new WebNode(a121);
		WebPage a122 = new WebPage(a12.finder().get(1));
		WebNode a122Node = new WebNode(a122);
		

		
		
		
		
	
	
		Scanner sc= new Scanner(System.in);
		while(sc.hasNextLine()) {
			String keyword =sc.next();
			GoogleQuery googleQuery = new GoogleQuery(keyword);
			googleQuery.query();
			//int a = 0;
			
			
	/*		WebPage a1 = new WebPage(a[0]);
			WebNode a1Node = new WebNode(a1);
			String a1child1 = "";
			WebPage a1child1111 = new WebPage(a1child1);
			WebNode a1Childnode = new WebNode(a1child1111);
			a1Node.addChild(a1Childnode);*/
			
			
		}
		sc.close();
	}
}


