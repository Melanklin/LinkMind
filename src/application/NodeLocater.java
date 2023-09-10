package application;

import javafx.geometry.Bounds;

public class NodeLocater {
	public static int marginX = 50;
	public static int marginY = 20;
	
		
	public static void locateX(TreeNode node) {
		//判断是否为根节点
		if(node.getPid() != 0) {
			TreeNode p = new TreeNode();
		    p = NodeList.getParent(node);
		    if(node.getPos()==1) {
			    node.setLeft(p.getLeft() + p.getWidth() + marginX);
		    }else{
			    node.setLeft(p.getLeft() - node.getWidth() - marginX);
		    }
		}else {
			node.setLeft(MyDrawPane.drawPane.getWidth()/2-node.getWidth()/2);
			//System.out.println("MyDrawPanew:"+MyDrawPane.drawPane.getWidth());
		}
		for(int i = 0;i < node.getNodeChildren().size();i++) {
			locateX(node.getNodeChildren().get(i));
		}
		
	}
	
	public static void locateY(TreeNode node) {
		if(node.getPid() != 0) {
			TreeNode p = NodeList.getParent(node);
		    //第一个孩子
		    if(NodeList.isFirstChild(node)) {
		    	double pchildHeight = NodeList.getChildHeight(p,node.getPos());
		    	double childHeight = NodeList.getChildHeight(node,node.getPos());
		    	//System.out.println("p1+"+pchildHeight);
		    	//System.out.println("c2+"+childHeight);	
		    	if(node.getNid() == p.getNodeChildren().get(p.getNodeChildren().size()-1).getNid()) {
		    		node.setTop(p.getTop() + p.getHeight()/2 - node.getHeight()/2);
		    	}else {
		    		node.setTop(p.getTop() + p.getHeight()/2 - (pchildHeight - childHeight + node.getHeight())/2);
		    	}
		    	    
			    //System.out.printf("%d,%f,%f\n",node.getId(),node.getLeft(),node.getTop());
		    }else{//剩下的孩子
		    	TreeNode preChild = NodeList.getPreChild(node);
		    	double prechildHeight = NodeList.getChildHeight(preChild,node.getPos());
		    	double childHeight = NodeList.getChildHeight(node,node.getPos());
		    	//System.out.println("p2+"+prechildHeight);
		    	//System.out.println("c3+"+childHeight);
			    node.setTop(preChild.getTop() + preChild.getHeight()/2 +(prechildHeight + childHeight - node.getHeight())/2 + marginY);
			   // System.out.printf("%d,%f,%f\n",node.getId(),node.getLeft(),node.getTop());
		     }
		}else {//设置根节点的位置
			node.setTop(MyDrawPane.drawPane.getHeight()/2-node.getHeight()/2);
			//System.out.println("MyDrawPaneh:"+MyDrawPane.drawPane.getHeight());
		}
		for(int i = 0;i < node.getNodeChildren().size();i++) {
			locateY(node.getNodeChildren().get(i));
		}
		
	}
}
