package application;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class LayoutSetter {
	public static int item = 1;
	//自由布局
	public static void Item1(){
		MyMenuBar.item1.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				item = 1;
				SelectItem();
				CheckPane.controlPane();
				NodeLocater.locateX(NodeList.getRoot());
				NodeLocater.locateY(NodeList.getRoot());
				MyDrawPane.draw();
			}
		});
	}
	//左布局
//	if(NodeList.getParent(node)==NodeList.getRoot()) {
//		
//	}
	public static void Item2(){
		MyMenuBar.item2.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				item = 2;
				SelectItem();
				NodeLocater.locateX(NodeList.getRoot());
				NodeLocater.locateY(NodeList.getRoot());
				CheckPane.controlPane();
				MyDrawPane.draw();
			}
		});
	}
	//右布局
	public static void Item3(){
		MyMenuBar.item3.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				item = 3;
				SelectItem();
				NodeLocater.locateX(NodeList.getRoot());
				NodeLocater.locateY(NodeList.getRoot());
				CheckPane.controlPane();
				MyDrawPane.draw();
			}
		});
	}
	
	public static void SelectItem() {
		if(item==1) {
			for(int i = 0; i<NodeList.list.size();i++) {
				TreeNode node = new TreeNode();
				node = NodeList.list.get(i);
				//System.out.println("ChildHeight:"+NodeList.getChildHeight(node, node.getPos()));
				if(NodeList.getParent(node)==NodeList.getRoot()) {
					Balance();
//					c++;
//					if(c%2==1) {
//						node.setPos(1);
//					}
//					else node.setPos(0);
//					NodeLocater.locateX(NodeList.getRoot());
//					NodeLocater.locateY(NodeList.getRoot());
				}
				else {
					node.setPos(NodeList.getParent(node).getPos());
				}
			}
		}
		if(item==2) {
			for(int i = 0; i<NodeList.list.size();i++) {
				TreeNode node = new TreeNode();
				node = NodeList.list.get(i);
				node.setPos(0);
			}
		}
		if(item==3) {
			for(int i = 0; i<NodeList.list.size();i++) {
				TreeNode node = new TreeNode();
				node = NodeList.list.get(i);
				node.setPos(1);
			}
		}
	}
	
	public static void Balance() {
		 List<TreeNode> node = NodeList.getRoot().getNodeChildren();
		 int length = node.size();
		 double sum = 0;
		 int max = 0;
		 double[] height = new double[length];
//		 int[] left = new int[length/2+1];
		 for(int i = 0;i < length;i++) {
			 height[i] = NodeList.getChildHeight(node.get(i), node.get(i).getPos());
			 if(height[i] > height[max])
				 max = i;
			 sum+=height[i];
		}
//		 left[0] = max;
		 node.get(max).setPos(0);
		 double nowsum = height[max];
		 for(int i = 0;i < length;i++) {
			 if(i!=max) {
				 if(height[i]+nowsum<=sum/2+42) {
					 node.get(i).setPos(0);
					 nowsum+=height[i];
				 }
				 else node.get(i).setPos(1);
			 }
		 }
	}
	
}
