package application;

import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;


public class MyTreeView extends TreeView<Label> {
    public static AnchorPane ap = new AnchorPane();
    MyTreeView() throws IOException {
         
        setTreeView();
    }
    
    public static void setTreeView() throws IOException {
    	TreeView<Label> treeview = new TreeView<Label>();
    	TreeNode node=NodeList.getRoot();
    	Label label = new Label(node.getTxt());
    	label.setMaxWidth(150);
    	label.setPrefWidth(100); //设置Label宽度
    	label.setTextOverrun(OverrunStyle.ELLIPSIS);
    	TreeItem<Label> root = new TreeItem<Label>(label);
		treeview.setRoot(root);
		if(node.getImgPath()!=null) {
			label.setText(node.getTxt()+"\n"+"(图片)");
    		label.setTextAlignment(TextAlignment.CENTER);
		}
		label.setTextOverrun(OverrunStyle.CENTER_WORD_ELLIPSIS); 
		walk(root,node);
		if(NodeList.list.isEmpty()) {
			root=null;
			treeview.setRoot(root);
    	}
		Tooltip tooltip = new Tooltip(node.getText());
		Tooltip.install(label, tooltip);
        treeview.setPrefWidth(200);
        treeview.setMinHeight(570);

        ap.getChildren().addAll(treeview);
    }
    public static void walk(TreeItem<Label> root,TreeNode node) {
		for(int i=0;i<node.getNodeChildren().size();i++) {
			TreeNode childnode=node.getNodeChildren().get(i);
			Label label = new Label(childnode.getTxt());
			label.setPrefWidth(100); //设置Label宽度
			label.setPrefHeight(30);
			label.setTextOverrun(OverrunStyle.CENTER_WORD_ELLIPSIS); 
			if(childnode.getImgPath()!=null) {
	    		label.setText(childnode.getTxt()+"\n"+"(图片)");
	    		label.setTextAlignment(TextAlignment.CENTER);
			}
			Tooltip tooltip = new Tooltip(childnode.getText());
    		Tooltip.install(label, tooltip);
			TreeItem<Label> child = new TreeItem<Label>(label);
			root.getChildren().add(child);
			root.setExpanded(true);
			walk(child,childnode);
		}
	}

}
