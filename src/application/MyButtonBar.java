package application;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Menu;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MyButtonBar extends Menu {
	public AnchorPane ap = new AnchorPane();
	public static ButtonBar btnBar = new ButtonBar();
	public static Button b1 = new Button("新建");
	public static Button b2 = new Button("增加子节点");
	public static Button b3 = new Button("增加同级节点");
	public static Button b4 = new Button("删除");
	public static Button b5 = new Button("清空");
	public static Button b6 = new Button("插入图片");
	public static Button b7 = new Button("查看图片");
	
	MyButtonBar(){
        super();
        btnBar.getButtons().addAll(b1,b2,b3,b4,b5,b6,b7);
	    ap.getChildren().add(btnBar);
	    ap.setLayoutX(100);
	    eventHandler();
	    MouseClick.Click();
	    MouseClick.DoubleClick();
	    Zoom.zoom();
	}
	
//	监听工具栏按钮事件
	public void eventHandler() {
		//初始化按钮是否隐藏
		b2.setDisable(true);
		b3.setDisable(true);
		b4.setDisable(true);
		b5.setDisable(true);
		b6.setDisable(true);
		b7.setDisable(true);
		
		//按钮监控事件，点击“新建”，出现根节点
		b1.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
			//	System.out.println("新建");	
				b1.setDisable(true);
				b5.setDisable(false);
				//创建根节点
				if(NodeList.list.isEmpty()) {
					TreeUtil.creatRoot();
					MyDrawPane.draw();
					//Controler.controlPane(NodeList.getRoot());
					SetTitle.setTitle(null);
					SetTitle.changeTitle(Main.primarystage);
					try {
						MyTreeView.setTreeView();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		b2.setOnAction(new EventHandler<ActionEvent>(){
			 @Override
			 public void handle(ActionEvent e) {
				// System.out.println("增加子节点");
				 TreeNode node=MouseClick.node;
				// System.out.println("增加子节点"+node.getId());
				 TreeUtil.addNode(node);
				 LayoutSetter.SelectItem();
				 CheckPane.controlPane();					 
				 NodeLocater.locateX(NodeList.getRoot());
				 NodeLocater.locateY(NodeList.getRoot());
				
				 MyDrawPane.draw();
				// if(node.getNodeChildren().size()!=0)
				// Controler.controlPane(node.getNodeChildren().get(node.getNodeChildren().size()-1));
					try {
						MyTreeView.setTreeView();
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				 }
			 });
		
		 b3.setOnAction(new EventHandler<ActionEvent>(){
			 @Override
			 public void handle(ActionEvent e) {
				// System.out.println("增加同级节点");
				 TreeNode node=MouseClick.node;	
				 TreeNode p = NodeList.getParent(node);
				 TreeUtil.addNode(p);				
				 LayoutSetter.SelectItem();		
				 CheckPane.controlPane();		 
				 NodeLocater.locateX(NodeList.getRoot());
				 NodeLocater.locateY(NodeList.getRoot());
				 
				 MyDrawPane.draw();
			//	 Controler.controlPane(p.getNodeChildren().get(p.getNodeChildren().size()-1));
					try {
						MyTreeView.setTreeView();
					} catch (Exception e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
				 
				 }
			 });
		 
		 b4.setOnAction(new EventHandler<ActionEvent>(){
			 @Override
			 public void handle(ActionEvent e) {
				 b2.setDisable(true);
				 b3.setDisable(true);
				 b4.setDisable(true);
				 b5.setDisable(false);
				// System.out.println("删除");
				 TreeNode node=MouseClick.node;						 
				 TreeUtil.deleteNode(node);				
				 if(NodeList.list.isEmpty()) {
					 b1.setDisable(false);
					 b2.setDisable(true);
					 b3.setDisable(true);
					 b4.setDisable(true);
					 b5.setDisable(true);
				 }
				 LayoutSetter.SelectItem();
				 CheckPane.controlPane();
				 NodeLocater.locateX(NodeList.getRoot());
				 NodeLocater.locateY(NodeList.getRoot());
				 
				 MyDrawPane.draw();
					try {
						MyTreeView.setTreeView();
					} catch (Exception e4) {
						// TODO Auto-generated catch block
						e4.printStackTrace();
					}
				 }
			 });
			 
		 b5.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					b1.setDisable(false);
					b2.setDisable(true);
					b3.setDisable(true);
					b4.setDisable(true);
					b5.setDisable(true);
				//	System.out.println("清空");	
					NodeList.list.clear();
					MyDrawPane.g.getChildren().clear();
					CheckPane.controlPane();
					MyDrawPane.draw();
					try {
						MyTreeView.setTreeView();
					} catch (Exception e5) {
						// TODO Auto-generated catch block
						e5.printStackTrace();
					}
				}
			});
		 
		 b6.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle (ActionEvent event) {
				  Stage stage=new Stage();
					FileChooser fc=new FileChooser();
					fc.setTitle("插入图片");
					fc.setInitialDirectory(new File(System.getProperty("user.home")));
					fc.getExtensionFilters().addAll(
							new FileChooser.ExtensionFilter("所有","*.*"),
							new FileChooser.ExtensionFilter("JPG", "*.jpg"),
							new FileChooser.ExtensionFilter("PNG", "*.png")
							);		
					File file=fc.showOpenDialog(stage);
					if(file==null) return;
					System.out.println(file.getAbsolutePath());
					MouseClick.node.setImgPath(file.getAbsolutePath());
					NodeLocater.locateX(NodeList.getRoot());
					NodeLocater.locateY(NodeList.getRoot());
					Picture.show(file);
					
			 }
		});
		 b7.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle (ActionEvent event) {
				 if(MouseClick.node.getImgPath()!=null) {
					 ImageViewer.view();
				 }
				 else {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("注意");
						alert.setHeaderText(null);
						alert.setContentText("该结点不含图片");
						alert.show( );
						return;
					}
				 
			 }
		 });
	}
	
}
	


