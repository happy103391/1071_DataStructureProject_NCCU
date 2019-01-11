import java.io.IOException;
import java.util.ArrayList;

public class WebNode {
	public WebNode parent; // 節點會有parent
	public ArrayList<WebNode> children;
	public WebPage webPage;
	public double nodeScore;

	public WebNode(WebPage webPage) {
		this.webPage = webPage;
		this.children = new ArrayList<WebNode>();
	}

	public void setNodeScore() throws IOException { // 算node的分數
		// 讓webPage先算出自己的分數
		this.nodeScore = webPage.score; // 把webPage的分數傳給nodeScore

		for (WebNode child : children) { // 先判斷是否為children
			this.nodeScore += child.nodeScore; // 累加到nodeScore身上
		}
	}

	public void addChild(WebNode child) { // 增加下面的節點（子孫）
		this.children.add(child); // 新增了下面的節點
		child.parent = this; // 一開始的child變parent
	}

	public int getDepth() { // 所在節點的深度，從最上面的root開始算
		int retVal = 1; // root在第一層
		WebNode currNode = this; // 在此定義一個node，叫做currNode

		while (currNode.parent != null) { // 如果這個node有parent
			retVal++; // 深度+1
			currNode = currNode.parent; // 此時新的currNode會變成他的parent
		}
		return retVal;
	}

}