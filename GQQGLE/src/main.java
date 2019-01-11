import java.io.IOException;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Set;

import javax.sql.rowset.FilteredRowSet;
import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;
import javax.swing.text.Document;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

public class main {
	// public static String keyWord;
	private static int maxdepth = 2;

	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub

		ArrayList<WebTree> forest = new ArrayList<WebTree>();
		Scanner sc = new Scanner(System.in);
		System.out.println("請輸入想找的關鍵字：");
		while (sc.hasNext()) {
			String keyword = sc.nextLine();
			GoogleQuery googleQuery = new GoogleQuery(keyword);

			HashMap<String, String> result = googleQuery.query();
			
			Set r = result.keySet(); // 把所有的網站抓出來

			// 把網站塞進node裡面 在呼叫finder

			Iterator iterator = r.iterator();
			while (iterator.hasNext()) {
				ArrayList<String> vList = new ArrayList<>();
				String key = iterator.next().toString();
				String value = result.get(key);
				vList.add(value);

				// String aString = sc.nextLine();
				for (int i = 0; i < vList.size(); i++) {
					htmlContent h = new htmlContent(vList.get(i));
					int wordcount = h.countKeyword(keyword);// 算關鍵字在網頁中的數量
					// 获得html文本内容
					String HTML = h.getHtml();
					// 获取图片标签
					List imgUrl = h.getImageUrl(HTML);
					// 获取图片src地址
					int imgcount = h.getImageSrc(imgUrl); // 算圖片個數

					int initialTotal = imgcount * 200 + wordcount * 1;
					if (initialTotal != 0) {
						for (int p = 0; p < vList.size(); p++) {
							//WebPage a1 = new WebPage("https://www.google.com/search?q=" + keyword + "&oe=utf8&num=25");
							WebTree tree = new WebTree(new WebPage(vList.get(p)));
							/*WebPage a11 = new WebPage(vList.get(p));
							WebNode a1Node = new WebNode(a11);*/
							forest.add(tree);
							tree.root.webPage.score = initialTotal;

						}
					} else {
						vList.remove(initialTotal);
					}

					System.out.println(value + "        分數：" + initialTotal);

				}
				
				for(WebTree tree : forest) {
					ArrayList<String> children = (ArrayList<String>) tree.root.webPage.workurl(tree.root.webPage.url, 2);
					
					
					for(int i = 0; i < children.size(); i++) {
						String child = children.get(i);
						tree.root.addChild(new WebNode(new WebPage(child)));
						htmlContent h = new htmlContent(child);
						int wordcount = h.countKeyword(keyword);// 算關鍵字在網頁中的數量
						// 获得html文本内容
						String HTML = h.getHtml();
						// 获取图片标签
						List imgUrl = h.getImageUrl(HTML);
						// 获取图片src地址
						int imgcount = h.getImageSrc(imgUrl); // 算圖片個數

						int total = imgcount * 200 + wordcount * 1;
						
						tree.root.children.get(i).webPage.score = total;
						tree.root.children.get(i).setNodeScore();

					}
					tree.printTree();
				}
				
				
				
				
				
				// 在這裡建node，將網頁放進node裡面
				/*
				 * WebPage a1 = new WebPage("https://www.google.com/search?q="+ keyword
				 * +"&oe=utf8&num=10"); WebNode a1Node = new WebNode(a1); WebPage a11 = new
				 * WebPage(a1.finder().get(0)); WebNode a11Node = new WebNode(a11);
				 * a1Node.addChild(a11Node); WebPage a12 = new WebPage(a1.finder().get(1));
				 * WebNode a12Node = new WebNode(a12); a1Node.addChild(a12Node); WebPage a111 =
				 * new WebPage(a11.finder().get(0)); WebNode a111Node = new WebNode(a111);
				 * a11Node.addChild(a111Node); WebPage a112 = new WebPage(a11.finder().get(1));
				 * WebNode a112Node = new WebNode(a112); a11Node.addChild(a112Node); WebPage
				 * a121 = new WebPage(a12.finder().get(0)); WebNode a121Node = new
				 * WebNode(a121); a12Node.addChild(a121Node); WebPage a122 = new
				 * WebPage(a12.finder().get(1)); WebNode a122Node = new WebNode(a122);
				 * a12Node.addChild(a122Node);
				 * 
				 * WebTree tree = new WebTree(a1);
				 */

			}
			// System.out.println(r);
			// for (String kString : vList) {
			// String url = kString.substring(kString.indexOf("https"),kString.length());
			// }

			// String keyword = scanner.nextLine();
			/*
			 * String ht = h.fetchhtml(); System.out.println(count); int countp =
			 * h.countPic(); System.out.println(imgSrc); System.out.println(countp);
			 * 
			 * 
			 * //int a = 0;
			 */

			// System.out.print(tree.toString());*/

			/*
			 * WebPage a1 = new WebPage(a[0]); WebNode a1Node = new WebNode(a1); String
			 * a1child1 = ""; WebPage a1child1111 = new WebPage(a1child1); WebNode
			 * a1Childnode = new WebNode(a1child1111); a1Node.addChild(a1Childnode);
			 */

		}
		sc.close();
	}
}
