package application;

import java.util.ArrayList;
import java.util.List;

public class Deque {
	//定义一个
	private static int maxSize = 20;	
	private static List<TreeNode>[] arr = new List[maxSize];
	private static int sp = -1;
	private static int size = 0;
	
	
	public static void insert(List<TreeNode> list) {
		if(isFull()) {
			if(sp<size-1) {
			remove_right();		
			arr[++sp] = getList(list);
			size++;
		   }else {
			  remove_left(); 
			  arr[sp] = getList(list);
		   }
		}else {
			if(sp<size-1) {
				remove_right();		
				arr[++sp] = getList(list);
				size++;
			   }else {
				   arr[++sp] = getList(list);
			       size++;
			   }
			
		}	
		
	}
	public static void remove_left() {
		//删掉最初的状态
		for(int i =0;i<size-1;i++) {
			arr[i] = arr[i+1];
		}
	}
	public static void remove_right() {
		//清空右边的状态
		while(size-1>sp) {
			arr[size-1] = null;			
			size--;
		}
	}
	public static List<TreeNode> undo(){
		List<TreeNode> list = new ArrayList<>();
		if(sp>0) {
			sp--;
			list = arr[sp];
			if(list.isEmpty()) {
				MyButtonBar.b1.setDisable(false);
				MyButtonBar.b2.setDisable(true);
				MyButtonBar.b3.setDisable(true);
				MyButtonBar.b4.setDisable(true);
				MyButtonBar.b5.setDisable(true);
			}else {
				MyButtonBar.b1.setDisable(true);
			}
			//System.out.println("获得撤销状态");
			return getList(list);
			
		}
		return null;
	}
	public static List<TreeNode> redo(){
		List<TreeNode> list = new ArrayList<>();
		if(sp<size-1) {
			sp++;
			list = arr[sp];
			//System.out.println("获得重做状态");
			if(list.isEmpty()) {
				MyButtonBar.b1.setDisable(false);
				MyButtonBar.b2.setDisable(true);
				MyButtonBar.b3.setDisable(true);
				MyButtonBar.b4.setDisable(true);
				MyButtonBar.b5.setDisable(true);
			}else {
				MyButtonBar.b1.setDisable(true);
			}
			return getList(list);
		}
		return null;
	}
	private static List<TreeNode> getList(List<TreeNode> list){
		List<TreeNode> l =  new ArrayList<>();
		for(int i = 0;i<list.size();i++) {
			TreeNode n = new TreeNode();
			n.setText(list.get(i).getText());
			n.setNid(list.get(i).getNid());
			n.setPid(list.get(i).getPid());
			n.setLeft(list.get(i).getLeft());
			n.setTop(list.get(i).getTop());
			n.setTxt(list.get(i).getTxt());
			n.setPos(list.get(i).getPos());
			n.setImgPath(list.get(i).getImgPath());
			MyDrawPane.g.getChildren().add(n);
			MyDrawPane.g.applyCss();
			MyDrawPane.g.layout();
			MyDrawPane.g.getChildren().clear();
			l.add(n);
		
		}
		for(int i = 0;i < l.size();i++) {
			for(int j = 0;j<list.get(i).getNodeChildren().size();j++) {
				TreeNode node = list.get(i).getNodeChildren().get(j);
				for(int k = 0;k<l.size();k++) {
					if(node.getNid() == l.get(k).getNid())
					l.get(i).getNodeChildren().add(l.get(k));
				}
				
			}
		}
		return l;
	}
	
	private static  boolean isFull() {//判满
		return size == maxSize;
	}

}