package application;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.WritableImage;

public class DeriveGraph {
    public static int padding = 50;
    public static TreeNode left = new TreeNode();
    public static TreeNode right = new TreeNode();
    private static TreeNode top = new TreeNode();
    private static TreeNode bottom = new TreeNode();
	
	public static void deriveGraph(File file) throws Exception {
		
		for(int i = 0;i<NodeList.list.size();i++) {
			TreeNode node = new TreeNode();
			node = NodeList.list.get(i);
			node.setClick(false);
		}
		
		MyDrawPane.redraw();
		
		getTop();
		//System.out.println(width);
		int x = (int)left.getLeft()- DeriveGraph.padding;
		int y = (int)top.getTop() - DeriveGraph.padding; 
		
		WritableImage wi = MyDrawPane.g.snapshot(null, null);
		double height = wi.getHeight()+ DeriveGraph.padding*2;
		//System.out.printf("%d,%d,%f\n",left.getId(),right.getId(),height);
		double width = wi.getWidth()+ DeriveGraph.padding*2;
		
		WritableImage wi1 = MyDrawPane.drawPane.snapshot(null, null);
		//起始点x,y，到终点x,y
		WritableImage wi2 = new WritableImage(wi1.getPixelReader(),x,y,(int)width,(int)height);

		BufferedImage bi = SwingFXUtils.fromFXImage(wi2,null);
		ImageIO.write(bi,"png",file);
	}
	
	public static double getMaxWidth() {
		if(NodeList.list.size()!=0) {
			left = right =NodeList.list.get(0);
		for(int i = 0;i < NodeList.list.size();i++) {
			TreeNode node = new TreeNode();
			node = NodeList.list.get(i);
			if(left.getLeft()-node.getLeft()>0) {
				left = node;
			}
		    if(right.getLeft()+right.getWidth()-(node.getLeft()+node.getWidth())<0) {
		    	right = node;
		    }
		}
		    return right.getLeft()+right.getWidth() - left.getLeft();
		}else {
			return 0;
		}
		
//		WritableImage wi1 = MyDrawPane.g.snapshot(null, null);
//		//System.out.println(wi1.getWidth());
//		return wi1.getWidth();
		
		
	}
	
	public static double getMaxHeight() {
		if(NodeList.list.size()!=0) {
			TreeNode r = new TreeNode();
			r = NodeList.getRoot();
			if(NodeList.list.size()==1) {
				return r.getHeight();
			}else {
				double leftHeight = NodeList.getChildHeight(r, 0);
				double rightHeight = NodeList.getChildHeight(r, 1);
				if(leftHeight > rightHeight) {
					return leftHeight;
				}
	            	return rightHeight;
				}
			}else {
				return 0;
			}
			
			
		
		//WritableImage wi1 = MyDrawPane.g.snapshot(null, null);
		//System.out.println(wi1.getHeight());
		//return wi1.getHeight();	
	}
	
	public static void getTop() {
		if(!NodeList.list.isEmpty()) {
			   top = bottom =NodeList.list.get(0);
			  for(int i = 0;i < NodeList.list.size();i++) {
			   TreeNode node = new TreeNode();
			   node = NodeList.list.get(i);
			   if(top.getTop()-node.getTop()>0) {
			    top = node;
			   }
			      if(bottom.getTop()+bottom.getHeight()-(node.getTop()+node.getHeight())<0) {
			       bottom = node;
			      }
			  }
			  }
	}

}
