package application;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;

import javafx.stage.Stage;

public class MyMenuBar{
	public MenuBar menu = new MenuBar();
	MenuItem openItem = new MenuItem("打开");
    MenuItem saveItem = new MenuItem("保存");	
    MenuItem undo = new MenuItem("撤销");
    MenuItem redo = new MenuItem("重做");
    Menu outputItem = new Menu("导出");
    MenuItem outputItem1 = new MenuItem("png图片");
    MenuItem outputItem2 = new MenuItem("jpg图片");
	public static RadioMenuItem item1 = new RadioMenuItem("自动布局");
	public static RadioMenuItem item2 = new RadioMenuItem("左布局");
	public static RadioMenuItem item3 = new RadioMenuItem("右布局");
	MyMenuBar() {
        outputItem.getItems().addAll(          
            outputItem1,
            outputItem2);
        
         Menu fileMenu = new Menu("菜单");         
         fileMenu.getItems().addAll(openItem, saveItem,undo,redo,outputItem);
        
        Menu layoutMenu = new Menu("布局");
        ToggleGroup tGroup = new ToggleGroup();
        
        item1.setToggleGroup(tGroup);
        item1.setSelected(true);               
        item2.setToggleGroup(tGroup);
        item3.setToggleGroup(tGroup);
        layoutMenu.getItems().addAll(item1, item2,item3);
        menu.getMenus().addAll(fileMenu, layoutMenu);
        LayoutSetter.Item1();
        LayoutSetter.Item2();
        LayoutSetter.Item3();
        
        eventHandler();  
	}
//	监听菜单按钮事件
	public void eventHandler() {
		
				
		openItem.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Stage stage=new Stage();
				FileChooser fc=new FileChooser();
				
				fc.setTitle("打开文件");
				fc.setInitialDirectory(new File(System.getProperty("user.home")));
				fc.getExtensionFilters().addAll(
						//new FileChooser.ExtensionFilter("所有","*.*"),
						new FileChooser.ExtensionFilter("LinkMind", "*.LinkMind")
						);		
				File file=fc.showOpenDialog(stage);
				if(file==null) return;
				System.out.println(file.getAbsolutePath());
				ReadTree.read(file,Main.primarystage);	
				
				MyDrawPane.redraw();
				CheckPane.controlPane();
				LayoutSetter.SelectItem();					 
				NodeLocater.locateX(NodeList.getRoot());
				NodeLocater.locateY(NodeList.getRoot());
				
				MyDrawPane.draw();
				
				
				try {
					MyTreeView.setTreeView();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		//保存
        saveItem.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if(NodeList.list.isEmpty()) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("注意");
						alert.setHeaderText(null);
						alert.setContentText("你还没有绘制思维导图");
						alert.show( );
						return;
					}
					for(int i = 0;i<NodeList.list.size();i++) {
						TreeNode node = new TreeNode();
						node = NodeList.list.get(i);
						node.setClick(false);
					}
					Stage stage=new Stage();
					FileChooser fc=new FileChooser();
					fc.setTitle("保存文件");
					fc.setInitialFileName("思维导图");;
					fc.setInitialDirectory(new File(System.getProperty("user.home")));
					fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("LinkMind","*.LinkMind"));															
//					FileExtensionFilter filter = new FileExtensionFilter("LinkMind", "*.LinkMind");	
//					filter.setIcon(new Image("yangmi.png"));									
//					fc.getExtensionFilters().add(filter);
					
					File file=fc.showSaveDialog(stage);
					if(file==null) return;
					System.out.println(file.getAbsolutePath());
					WriteTree.write(file,Main.primarystage);
					
				}
			});
        
        undo.setOnAction(new EventHandler<ActionEvent>() {
			
  				@Override
  				public void handle(ActionEvent arg0) {
  					//System.out.println("撤销");
  					List<TreeNode> list = Deque.undo();
  					if(list!=null) {
  						NodeList.list = list;
  						try {
  							MyTreeView.setTreeView();
  						} catch (Exception e) {
  							// TODO Auto-generated catch block
  							e.printStackTrace();
  						}
  						
  						
  						MyDrawPane.redraw(); 
  						CheckPane.controlPane();
  						
  						//System.out.println("已撤销");
  					}				
  					
  				}
  			});
        redo.setOnAction(new EventHandler<ActionEvent>() {
			
				@Override
				public void handle(ActionEvent arg0) {
					//System.out.println("重做");
					List<TreeNode> list = Deque.redo();
					if(list!=null) {
						NodeList.list = list;
						try {
  							MyTreeView.setTreeView();
  						} catch (Exception e) {
  							// TODO Auto-generated catch block
  							e.printStackTrace();
  						}
						
  						MyDrawPane.redraw();
  						CheckPane.controlPane();  
  						//System.out.println("已重做");
					}
					
				}
			});
		outputItem1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(NodeList.list.isEmpty()) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("注意");
					alert.setHeaderText(null);
					alert.setContentText("你还没有绘制思维导图");
					alert.show( );
					return;
				}
				Stage stage=new Stage();
				FileChooser fc=new FileChooser();
				fc.setTitle("保存为png图片");
				fc.setInitialFileName("思维导图");
				fc.setInitialDirectory(new File(System.getProperty("user.home")));
				fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG","*.png"));
				File file=fc.showSaveDialog(stage);
				if(file==null) return;
			//	System.out.println(file.getAbsolutePath());
				try {
					Zoom.recover();
					DeriveGraph.deriveGraph(file);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		
        outputItem2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(NodeList.list.isEmpty()) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("注意");
					alert.setHeaderText(null);
					alert.setContentText("你还没有绘制思维导图");
					alert.show( );
					return;
				}
				Stage stage=new Stage();
				FileChooser fc=new FileChooser();
				fc.setTitle("保存为jpg图片");
				fc.setInitialFileName("思维导图");
				fc.setInitialDirectory(new File(System.getProperty("user.home")));
				fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG","*.jpg"));
				File file=fc.showSaveDialog(stage);
				if(file==null) return;
				System.out.println(file.getAbsolutePath());
				try {
					Zoom.recover();
					DeriveGraph.deriveGraph(file);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});

		
		
		openItem.setAccelerator(KeyCombination.valueOf( "ctrl+o" ));
		saveItem.setAccelerator(KeyCombination.valueOf( "ctrl+s" ));
		undo.setAccelerator(KeyCombination.valueOf( "ctrl+z" ));
		redo.setAccelerator(KeyCombination.valueOf( "ctrl+y" ));
		item1.setAccelerator(KeyCombination.valueOf( "ctrl+a" ));
		item2.setAccelerator(KeyCombination.valueOf( "ctrl+l" ));
		item3.setAccelerator(KeyCombination.valueOf( "ctrl+r" ));
	}

			
		
}
	

