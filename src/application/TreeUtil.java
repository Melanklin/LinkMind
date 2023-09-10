package application;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TreeUtil {
	private static int id = 1;
	
	public static void creatRoot() {
		TreeNode node = new TreeNode(0,"中心主题");
		node.setNid(id++);
		MyDrawPane.g.getChildren().add(node);
		MyDrawPane.g.applyCss();
		MyDrawPane.g.layout();
		NodeList.list.add(node);
		CheckPane.controlPane();
		node.setLeft(MyDrawPane.drawPane.getWidth()/2-node.getWidth()/2);
		node.setTop(MyDrawPane.drawPane.getHeight()/2-node.getHeight()/2);	
	}
	
	
	public static void addNode(TreeNode p) {
		TreeNode node = new TreeNode(p.getNid(),"分支主题");
		node.setNid(id++);
		NodeList.list.add(node);
		p.getNodeChildren().add(node);
		MyDrawPane.g.getChildren().add(node);
		MyDrawPane.g.applyCss();
		MyDrawPane.g.layout();
		 if(p.getPos()==1) {
			    node.setLeft(p.getLeft() + p.getWidth() + NodeLocater.marginX);
		    }else{
			    node.setLeft(p.getLeft() - node.getWidth() - NodeLocater.marginX);
		    }
	}
	
	
	public static void deleteNode(TreeNode node) {
		//删除node父亲孩子list中的的结点
		//System.out.println(888);
		TreeNode p = NodeList.getParent(node);
		System.out.println(p.getNodeChildren().size());
		for(int i = 0;i < p.getNodeChildren().size();i++) {
			if(node.getNid() == p.getNodeChildren().get(i).getNid()) {
				p.getNodeChildren().remove(i);
				break;
			}			
		}
		System.out.println(p.getNodeChildren().size());
		deleteChildren(node);
			
	}	
	
	private static void deleteChildren(TreeNode node) {
		for(int i = 0;i < NodeList.list.size();i++) {
//			for(int j = 0;j<MyDrawPane.g.getChildren().size();j++) {
//				if(MyDrawPane.g.getChildren().get(j).getClass()==TreeNode.class) {
//					TreeNode n = (TreeNode)MyDrawPane.g.getChildren().get(j);
//					if(node.getNid() == n.getNid()  ) {
//						MyDrawPane.g.getChildren().remove(j);
//						break;
//					}
//				}
//			}
			if(NodeList.list.get(i).getNid() == node.getNid()) {
				NodeList.list.remove(i);
			}
		}
		//删除list中node所有的子结点
		if(!node.getNodeChildren().isEmpty()) {
				for(int i = 0;i < node.getNodeChildren().size(); i++) {
					deleteChildren(node.getNodeChildren().get(i));
				}
				//System.out.println("孩子不空");
		}
			//System.out.println(NodeList.list.size());
     }
}
