package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;



public class Main extends Application {
	public static Stage primarystage;
	public static BorderPane pane;

	@Override
	public void start(Stage stage) throws Exception {
		    
			pane = new BorderPane();
            pane.setMinWidth(800);
            pane.setMaxWidth(1920);
            pane.setMaxHeight(600);
            
			//菜单
			MyMenuBar menuBar = new MyMenuBar();
			pane.setTop(menuBar.menu);
			
			//工具栏
			MyButtonBar buttonBar = new MyButtonBar();
			pane.getChildren().add(buttonBar.ap);
			
			//绘图区
			MyDrawPane drawPane = new MyDrawPane();
			drawPane.setMinWidth(800);
			drawPane.setMinHeight(570);
			
			//结构区
			MyTreeView listView = new MyTreeView();
			MyTreeView.ap.setMinWidth(150);
			MyTreeView.ap.setMaxWidth(200);
			
            //结构区和绘图区分区
			SplitPane spane = new SplitPane();
			spane.setDividerPositions(0.25);
			spane.getItems().addAll(drawPane,MyTreeView.ap);
			pane.setCenter(spane);
			primarystage = stage;
			SetTitle.changeTitle(primarystage);
			
            stage.getIcons().add(new Image("yangmi.png"));
			Scene scene = new Scene(pane,1000,600);
			scene.setOnKeyPressed(event -> {
			    if (event.isControlDown()) {
			    	if(event.getCode() == KeyCode.EQUALS||event.getCode() == KeyCode.PLUS) {
			    		Zoom.enlarge();
			    	}
			    	if(event.getCode() == KeyCode.MINUS) {
			    		Zoom.reduce();
			    	}
			    	if(event.getCode() == KeyCode.DIGIT0) {
			    		Zoom.recover();
			    	}
			    }
			});
			stage.setScene(scene);
			
			stage.show();
		
	}
	
	public static void main(String[] args) {	
		
	        launch(args);
	}
}
