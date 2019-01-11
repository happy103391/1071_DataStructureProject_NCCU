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
	 startNode.setNodeScore();  //算完小孩後，計算自己的分數
 	 }
 
 public void printTree() {  //把樹的整體都印出來
		printTree(root);
	}
	private void printTree(WebNode startNode) {
		//(root,score
		//    (node1,score)
		//    (node2,score)
		// )
		 for(int i =0;i<startNode.getDepth();i++) {
			   System.out.print("    ");
		   }if(startNode.children.isEmpty()) {
			     System.out.println("("+startNode.webPage.url+" , "+startNode.nodeScore+")");
		   }else { System.out.println("("+startNode.webPage.url+" , "+startNode.nodeScore);
			   for(WebNode child :startNode.children) {
				   printTree(child); 	   
			   }  
			  
			   
			  for(int i =0;i<startNode.getDepth();i++) {
				   System.out.print("     ");
			   }
			   System.out.println(")");
		   }
	}
}