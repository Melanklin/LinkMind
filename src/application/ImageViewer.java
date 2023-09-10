package application;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ImageViewer {
	public static void view() {
		Stage s=new Stage();
	     ImageView imageView = new ImageView(new Image(MouseClick.node.getImgPath()));
	     StackPane root = new StackPane();
	     root.getChildren().add(imageView);
	     Scene scene = new Scene(root, 800, 600);
	     imageView.setFitWidth(800);
		imageView.setFitHeight(600);
	     s.setTitle("查看图片");
	     s.setScene(scene);
	     s.show();
	}

}
