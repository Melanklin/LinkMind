package application;

import java.util.ArrayList;
import java.util.List;

public class NodeList {
	public static List<TreeNode> list = new ArrayList<>();
	private static double height = 0;


	public static TreeNode getRoot() {
		TreeNode r = new TreeNode();
		for(int i = 0;i < NodeList.list.size();i++) {
			if(NodeList.list.get(i).getPid() == 0) {
				r = NodeList.list.get(i);
			}
		}
		return r;
	}

	public static TreeNode getParent(TreeNode node) {
		TreeNode p = new TreeNode();
		for(int i = 0;i < NodeList.list.size();i++) {
			if(NodeList.list.get(i).getNid() == node.getPid()) {
				p = NodeList.list.get(i);
				return p;
				//break;
			}
		}
		//System.out.println(p);
		//System.out.println(p.getNodeChildren());
		return p;
	}
    
	public static TreeNode getPreChild(TreeNode node) {
		TreeNode p = NodeList.getParent(node);
		System.out.println(NodeList.getParent(node));
		System.out.println("******");
		TreeNode pre = p.getNodeChildren().get(0);
			for(int i = 1;i < p.getNodeChildren().size(); i++) {
			if(node.getNid() == p.getNodeChildren().get(i).getNid()) {
				break;
			}
				if(node.getPos() == p.getNodeChildren().get(i).getPos()) {
				pre = p.getNodeChildren().get(i);
			}
			
			}
			return pre;
	}
	
	public static boolean isFirstChild(TreeNode node) {
		TreeNode p = NodeList.getParent(node);
  	    TreeNode l = new TreeNode();
    	TreeNode r = new TreeNode();
		for(int i = 0;i < p.getNodeChildren().size();i++) {
              if(p.getNodeChildren().get(i).getPos() == 0) {        
            	  l = p.getNodeChildren().get(i);
            	  break;
              }
		 }
		for(int i = 0;i < p.getNodeChildren().size();i++) {
            if(p.getNodeChildren().get(i).getPos() == 1) {
          	  r = p.getNodeChildren().get(i);
          	  break;
            }
		 }
		if(node.getNid()==l.getNid()||node.getNid()==r.getNid()) {
			//System.out.println(node.getNid()+"isfirstchild");
			return true;
		}
		//System.out.println(888);
		return false;
	}

	public static double getChildHeight(TreeNode p , int pos) {
		height = 0;
		if(p.getPid()==0) {
			List<TreeNode> list = new ArrayList<>();
			for(int i = 0; i < p.getNodeChildren().size(); i++) {
				if(p.getNodeChildren().get(i).getPos()==pos) {
					list.add(p.getNodeChildren().get(i));
				}
			}
			for(int i = 0;i<list.size();i++) {
				walkNode(list.get(i));
				if(i < list.size()-1)
				    height += NodeLocater.marginY;
				
			}
			
		}else {
			walkNode(p);
		}
				
		return height;
	}
    
	private static void walkNode(TreeNode p ) {
		for(int i = 0; i < p.getNodeChildren().size(); i++) {
			TreeNode node = new TreeNode();
			node = p.getNodeChildren().get(i);
			if(!node.getNodeChildren().isEmpty()) {
				walkNode(node);
				//System.out.println("1+"+height);
			}
			else {
				height += node.getHeight();
			   // System.out.println("2+"+height);
			}
			if(i < p.getNodeChildren().size()-1)
			    height += NodeLocater.marginY;
		}
		
		if(p.getNodeChildren().isEmpty()) {
			height += p.getHeight();
		}else if(height < p.getHeight()) {
			height = p.getHeight();
			//System.out.println("3**+"+height);
		}
		
		
	//	System.out.println("height:"+height);
	}
}
