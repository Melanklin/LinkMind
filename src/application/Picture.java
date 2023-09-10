package application;



import java.io.File;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;

public class Picture {

	public static void show(File file) {
		MouseClick.node.setImgPath(file.getAbsolutePath());
		ImageView imageView = new ImageView(file.getAbsolutePath());// 设置图片宽度和高度
		Label label = MouseClick.node;
		imageView.setFitWidth(100);
		imageView.setFitHeight(100);
		label.setGraphic(imageView);
		label.setContentDisplay(ContentDisplay.BOTTOM);
		label.setTextAlignment(TextAlignment.CENTER);
		MyDrawPane.g.applyCss();
		MyDrawPane.g.layout();
		CheckPane.controlPane();
		System.out.println("label.width"+label.getWidth());
		System.out.println("label.height"+label.getHeight());
		NodeLocater.locateX(NodeList.getRoot());
		NodeLocater.locateY(NodeList.getRoot());
		try {
			MyTreeView.setTreeView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MyDrawPane.draw();
	}

}

