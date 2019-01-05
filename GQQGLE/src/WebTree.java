import java.io.IOException;
import java.util.ArrayList;

public class WebTree {

	public WebNode root;
	
	public WebTree(WebPage rootPage) {
		this.root= new WebNode(rootPage);
	}
	public void setPostOrderScore(ArrayList<Keyword>keywords) throws IOException { //讓外界使用，所以設為public
		setPostOrderScore(root, keywords);  //先呼叫，但傳入的參數不一樣（傳root進去）
	}
	private void setPostOrderScore(WebNode startNode, ArrayList<Keyword>keywords) throws IOException { 
		//只能在這個class使用，所以設為private
		for(WebNode child:startNode.children) {  //先算出最底層的node分數，再往下加 
			setPostOrderScore(child,keywords);   //把所有小孩呼叫出來，先算他們的score，直到最底層結束
		}
		startNode.setNodeScore(keywords);  //算完小孩後，計算自己的分數
	}
}
