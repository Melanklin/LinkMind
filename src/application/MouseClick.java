package application;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;

public class MouseClick {
	public static TreeNode node = new TreeNode();
	
	//单击事件：监控节点和按钮，进行相应操作
	public static void Click() {
		
		MyDrawPane.drawPane.setOnMouseClicked(event->{
			if(!NodeList.list.isEmpty()) {
		    	 MyButtonBar.b1.setDisable(true);
				 MyButtonBar.b2.setDisable(true);
				 MyButtonBar.b3.setDisable(true);
				 MyButtonBar.b4.setDisable(true);
				 MyButtonBar.b6.setDisable(true);
				 MyButtonBar.b7.setDisable(true);
		     }
//			System.out.println("isclick");
//			 double x1 = event.getX();
//		     double y1 = event.getY();
			 double x = event.getSceneX();
		     double y = event.getSceneY();
		     
//		    System.out.printf("%f,%f:\n",x1,y1);
		     for(int i=0;i<NodeList.list.size();i++) {
					TreeNode n = new TreeNode();
					n = NodeList.list.get(i);
					n.setClick(false);
					Bounds b = n.getLayoutBounds();
					Bounds bd = n.localToScene(b);
					n.setStyle(
							"-fx-background-color:#d6ecf0;"+
			        	    "-fx-background-radius:10;"+
			        	    "-fx-padding:10;"
			             );
					//System.out.printf("%f,%f:\n",bd.getWidth(),bd.getHeight());
//					System.out.printf("%f,%f:\n",bd.getMinX(),bd.getMaxX());
					if( 
							bd.getMinX()<=x&&bd.getMaxX()>=x&&bd.getMinY()<=y&&bd.getMaxY()>=y
//							n.getLeft()<= x&& n.getLeft()+n.getWidth()>=x
//					     &&n.getTop()<=y && n.getTop()+n.getHeight()>=y
					     ) {
						n.setClick(true);				
						node = n;
					  	n.setStyle(
				        		"-fx-background-color:#faff72;"	+
				        	    "-fx-background-radius:10;"+
				        	    "-fx-padding:10;"
				             ); 
					  	if(node.getImgPath()!=null) {
					  		MyButtonBar.b7.setDisable(false);
					  		MyButtonBar.b6.setDisable(true);
					  	}
						MyButtonBar.b1.setDisable(true);
						MyButtonBar.b2.setDisable(false);
						MyButtonBar.b4.setDisable(false);
						MyButtonBar.b5.setDisable(false);
						MyButtonBar.b6.setDisable(false);
						if(node != NodeList.getRoot()) {
							MyButtonBar.b3.setDisable(false);
						}
						else MyButtonBar.b3.setDisable(true);
					}
				}
		    // MyDrawPane.draw();  //另起方法画框
		});
	}
	
	//鼠标双击事件：监听节点，双击出现文本输入框，编辑节点文字内容
	public static void DoubleClick() {
				MyDrawPane.drawPane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				 @Override
				 public void handle(MouseEvent event) {		 
				  if(event.getClickCount() == 2){
					  double x = event.getSceneX();
					  double y = event.getSceneY();
//				     System.out.println(x+","+y);
				     for(int i=0;i<NodeList.list.size();i++) {
							TreeNode node = new TreeNode();
							node = NodeList.list.get(i);
							Bounds b = node.getLayoutBounds();
							Bounds bd =node.localToScene(b);
						//	node.setClick(false);
//							System.out.println(node.getLeft()+","+node.getWidth());
//							System.out.println(node.getTop()+","+node.getHeight());
							if(bd.getMinX()<=x&&bd.getMaxX()>=x&&bd.getMinY()<=y&&bd.getMaxY()>=y
//									node.getLeft()<= x&& node.getLeft()+node.getWidth()>=x
//							     &&node.getTop()<=y && node.getTop()+node.getHeight()>=y
							     ) {
							//	node.setClick(true);
								MyTextArea.showTextArea(node);
							}
						//	MyDrawPane.draw(); 另起方法画框
						}
				  }
				 }
				});
	}
	

	
}
