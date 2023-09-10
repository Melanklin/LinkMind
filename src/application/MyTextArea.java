package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MyTextArea {
	
	public static void showTextArea(TreeNode node) {
		AnchorPane an = new AnchorPane();
		Stage stage = new Stage();
  
		TextArea ta = new TextArea();
        ta.appendText(node.getText());
        ta.setFont( Font.font( 16));    
        ta.setPrefWidth(250);
        ta.setPrefHeight(150);
        ta.setWrapText(true);
  
        Button btn = new Button("确定");
        btn.setLayoutX(0);
        btn.setLayoutY(150);
        btn.setPrefWidth(250);  
        btn.setPrefHeight(35);
        an.getChildren().addAll(ta,btn);
        
        btn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
			//	System.out.println("确定");	
				String str = ta.getText();
			//	System.out.println(str);
				if(str!=node.getText()) {
					node.setText(str);
					node.setTxt(str);
					//NodeList.countNodeSize(node);
					MyDrawPane.g.applyCss();
					MyDrawPane.g.layout();
					CheckPane.controlPane();
					NodeLocater.locateX(NodeList.getRoot());
					NodeLocater.locateY(NodeList.getRoot());
				    MyDrawPane.draw();
				 //   Controler.controlPane(node);
					try {
						MyTreeView.setTreeView();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				stage.close();
			}
		});
  
        Scene scene = new Scene(an);
        stage.setScene(scene);
        stage.setTitle("文本输入框");
        stage.setWidth(265);
        stage.setHeight(225);
        stage.show();
        
	}
	

}