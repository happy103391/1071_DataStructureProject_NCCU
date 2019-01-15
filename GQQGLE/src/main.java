import java.io.IOException;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
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
		ArrayList<Double> scoreRank = new ArrayList<Double>();
		ArrayList<String> webSort = new ArrayList<String>();
		/*
		 * ArrayList<Double> less = new ArrayList<Double>(); ArrayList<Double> greater =
		 * new ArrayList<Double>(); ArrayList<Double> allresult = new
		 * ArrayList<Double>();
		 */
		// HashMap<String, Double> beforesort =new HashMap<String, Double>();
		Map<String, Integer> beforesort = new HashMap<String, Integer>();
		// Map<String, Double> aftersort = new LinkedHashMap<String,Double>();

		// Map<String, Double> beforesort = new TreeMap<String, Double>(keyComparator);

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
				ArrayList<String> vList = new ArrayList<String>();
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

					for (int p = 0; p < vList.size(); p++) {
						// WebPage a1 = new WebPage("https://www.google.com/search?q=" + keyword +
						// "&oe=utf8&num=25");

						// WebTree.root.webPage.score = initialTotal;
						// System.out.println(value);

						WebTree tree = new WebTree(new WebPage(vList.get(p)));
						List<String> children = (List<String>) htmlContent.workurl(tree.root.webPage.url, 2);
						for (int a = 0; a < 5; a++) {

							try {
								String child = children.get(a);
								tree.root.addChild(new WebNode(new WebPage(child)));
								htmlContent h1 = new htmlContent(child);
								int wordcount1 = h1.countKeyword(keyword);// 算關鍵字在網頁中的數量
								// 获得html文本内容
								String HTML1 = h1.getHtml();
								// 获取图片标签
								List imgUrl1 = h1.getImageUrl(HTML);
								// 获取图片src地址
								int imgcount1 = h1.getImageSrc(imgUrl); // 算圖片個數

								int total = imgcount1 * 200 + wordcount1 * 1;

								tree.root.children.get(a).webPage.score = total;

								initialTotal = initialTotal + total;

								tree.root.webPage.score = initialTotal;
								tree.root.children.get(a).webPage.score = total;

								String link = "(母網頁：" + tree.root.getlink() + "        分數：" + initialTotal;
								String sublink = "            (子網頁：" + tree.root.children.get(a).getlink()
										+ "        分數：" + total;
								String links = link + "\n" + sublink;
								String number = "";
								System.out.println(links);

								beforesort.put(value, (int) tree.root.webPage.score);
								List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(
										beforesort.entrySet());
								// System.out.println(infoIds);
								// 对HashMap中的key 进行排序
								Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
									public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
										// System.out.println(o1.getKey()+" === "+o2.getKey());
										return (o2.getValue().compareTo(o1.getValue()));
									}
								});
								// 对HashMap中的key 进行排序后 显示排序结果
								for (int b = 0; b < infoIds.size(); b++) {
									String id = infoIds.get(b).toString();
									// System.out.print(id + " ");
								}

								System.out.println(infoIds);

								break;
							} catch (Exception e) {
								// System.out.println("bye!");
								break;
							}
							// System.out.println(allresult);

						}

					}
				}
				
				//System.out.println("找完了ㄎㄎ");
				
			}System.out.println("找完了ㄎㄎ");
		}sc.close();
	}

}
