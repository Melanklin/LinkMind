package application;

import javafx.scene.image.WritableImage;
import javafx.scene.transform.Scale;

public class Zoom {
	
	public static int count=0;
	
	public static void zoom() {
		MyDrawPane.drawPane.setOnScroll(event -> {
		     if (event.isControlDown()) { // 检查是否按下了Ctrl键
		         double deltaY = event.getDeltaY();
		         if(event.getDeltaY()>0) {
		        	 enlarge();
		         }
		         if(event.getDeltaY()<0) {
		        	 reduce();
		         }
		     }
		 });
	}
	
	public static void enlarge() {
		if(count<5) {
			count++;
			MyButtonBar.b1.setDisable(true);
			MyButtonBar.b5.setDisable(false);
			if(NodeList.list.isEmpty()) {
				MyButtonBar.b1.setDisable(false);
				MyButtonBar.b2.setDisable(true);
				MyButtonBar.b3.setDisable(true);
				MyButtonBar.b4.setDisable(true);
				MyButtonBar.b5.setDisable(true);
				MyButtonBar.b6.setDisable(true);
			 }
			MyDrawPane.g.getTransforms().add(new Scale(1.1,1.1,MyDrawPane.drawPane.getWidth()/2,MyDrawPane.drawPane.getHeight()/2));
			
			WritableImage wi1 = MyDrawPane.g.snapshot(null, null);
			 double width = 1000 + wi1.getWidth()*2;
			  double height = 1000+ wi1.getHeight();
			  MyDrawPane.drawPane.setPrefWidth(width);
			  MyDrawPane.drawPane.setPrefHeight(height);
			  
			
			//NodeLocater.locateX(NodeList.getRoot());
			//NodeLocater.locateY(NodeList.getRoot());
			MyDrawPane.redraw();
			try {
				MyTreeView.setTreeView();
			} catch (Exception e6) {
				// TODO Auto-generated catch block
				e6.printStackTrace();
			}
		}
		
	}
	
	public static void reduce() {
		if(count>-5) {
			count--;
			MyButtonBar.b1.setDisable(true);
			MyButtonBar.b5.setDisable(false);
			if(NodeList.list.isEmpty()) {
				MyButtonBar.b1.setDisable(false);
				MyButtonBar.b2.setDisable(true);
				MyButtonBar.b3.setDisable(true);
				MyButtonBar.b4.setDisable(true);
				MyButtonBar.b5.setDisable(true);
				MyButtonBar.b6.setDisable(true);
			 }
//			Scale scale = new Scale(0.9,0.9,MyDrawPane.drawPane.getWidth()/2,MyDrawPane.drawPane.getHeight()/2);
//			for(int i = 0; i<NodeList.list.size();i++) {
//				  TreeNode node = NodeList.list.get(i);
//				  node.getTransforms().add(scale);
//			}
			MyDrawPane.g.getTransforms().add(new Scale(0.9,0.9,MyDrawPane.drawPane.getWidth()/2,MyDrawPane.drawPane.getHeight()/2));		
			
			WritableImage wi1 = MyDrawPane.g.snapshot(null, null);
			  double width = 1000 + wi1.getWidth()*2;
			  double height = 1000+ wi1.getHeight();
			  MyDrawPane.drawPane.setPrefWidth(width);
			  MyDrawPane.drawPane.setPrefHeight(height);
			  
			  

			//NodeLocater.locateX(NodeList.getRoot());
			//NodeLocater.locateY(NodeList.getRoot());
			MyDrawPane.redraw();
			try {
				MyTreeView.setTreeView();
			} catch (Exception e7) {
				// TODO Auto-generated catch block
				e7.printStackTrace();
			}
		}
		
	}
	
	public static void recover() {
		count=0;
		MyButtonBar.b1.setDisable(true);
		MyButtonBar.b5.setDisable(false);
		if(NodeList.list.isEmpty()) {
			MyButtonBar.b1.setDisable(false);
			MyButtonBar.b2.setDisable(true);
			MyButtonBar.b3.setDisable(true);
			MyButtonBar.b4.setDisable(true);
			MyButtonBar.b5.setDisable(true);
			MyButtonBar.b6.setDisable(true);
		 }
		MyDrawPane.g.getTransforms().clear();		
		NodeLocater.locateX(NodeList.getRoot());
		NodeLocater.locateY(NodeList.getRoot());
		CheckPane.controlPane();
		MyDrawPane.redraw();
		try {
			MyTreeView.setTreeView();
		} catch (Exception e8) {
			// TODO Auto-generated catch block
			e8.printStackTrace();
		}
	}
}
