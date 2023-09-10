package application;



import javafx.scene.Group;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class MyDrawPane extends ScrollPane{
	  //public static Canvas canvas = new Canvas(3500,3500);
	  public static int fontSize = 20;
	 // public static GraphicsContext gc = canvas.getGraphicsContext2D();
	  public static Pane drawPane = new Pane();
	  public static ScrollPane sp = new ScrollPane();
	  public static Group g = new Group();
	  
	  MyDrawPane(){
		  super(drawPane);
		  drawPane.setMinWidth(1000);
		  drawPane.setMinHeight(1000);
		  sp = this;
		  draw();
		  this.setVvalue(0.5);
		  this.setHvalue(0.5);
		  drawPane.getChildren().add(g);
	  }
//	  绘图
	  public static void draw() {
		  //清空画布
		//  gc.clearRect(0, 0, 3500, 3500);
		  
		  //添加节点
		  //LayoutSetter.SelectItem();  
		  MyDrawPane.g.getChildren().clear();

		  Deque.insert(NodeList.list);	
		  
		  
		 // System.out.println("new state");
		  for(int i = 0; i<NodeList.list.size();i++) {
			  TreeNode node = NodeList.list.get(i);	
			  node.setText(node.getTxt());
		    	node.setStyle(
		    			"-fx-background-color:#d6ecf0;"+
		        	    "-fx-background-radius:10;"+
		        	    //"-fx-border-color:#FAEBD7;"+   	    
		        	   // "-fx-border-radius:10;"+
		        	    "-fx-padding:10;"
		             );
		        	node.setMaxSize(200, 80);
		        	node.setWrapText(true);
		        	node.setTextAlignment(TextAlignment.CENTER);
		        	node.setTextOverrun(OverrunStyle.ELLIPSIS);
		        	node.setTextFill(Color.BLACK);
		            node.setFont(new Font("Arial",20));    	
		            
		                  
			        if(node.isClick()) {
			        	node.setStyle(
		        		"-fx-background-color:#faff72;"	+
		        	    "-fx-background-radius:10;"+
		        	    "-fx-padding:10;"
		             ); 
			        			  
               }
			        MyDrawPane.g.getChildren().add(node);
			        MyDrawPane.g.applyCss();  //确保更新样式
			        MyDrawPane.g.layout();	//确保更新节点位置和大小
			        
			        node.setLayoutX(node.getLeft());
			        node.setLayoutY(node.getTop());  
			        
			        if(node != NodeList.getRoot()) {
						  line(node);
					  } 
			        if(node.getImgPath()!=null) {
			        	ImageView imageView = new ImageView(node.getImgPath());// 设置图片宽度和高度
			    		Label label = node;
			    		imageView.setFitWidth(100);
			    		imageView.setFitHeight(100);
			    		label.setGraphic(imageView);
			    		label.setContentDisplay(ContentDisplay.BOTTOM);
			    		label.setTextAlignment(TextAlignment.CENTER);
			        }
		  }		  
	  }
	  
	  
	  
	  public static void line(TreeNode node) {
		  TreeNode p = NodeList.getParent(node);
		  Line line1 = new Line();
		  Line line2 = new Line();
		  Line line3 = new Line();
//		   x1 = p.getLeft()+p.getWidth();
//		   x2 = node.getLeft();
//		   y1 = p.getTop()+p.getHeight()/2;
//		   y2 = node.getTop()+node.getHeight()/2;
//		  gc.strokeLine(x1,y1,(x1+x2)/2,y1);
//		  gc.strokeLine((x1+x2)/2,y1,(x1+x2)/2,y2);
//		  gc.strokeLine((x1+x2)/2,y2,x2,y2);
		  if(node.getPos()==1) {
			  line1.startXProperty().bind(p.layoutXProperty().add(p.widthProperty()));
			  line1.startYProperty().bind(p.layoutYProperty().add(p.heightProperty().divide(2)));
			  line1.endXProperty().bind((p.layoutXProperty().add(p.widthProperty().add(node.layoutXProperty()))).divide(2));
			  line1.endYProperty().bind(p.layoutYProperty().add(p.heightProperty().divide(2)));
			  line2.startXProperty().bind((p.layoutXProperty().add(p.widthProperty().add(node.layoutXProperty()))).divide(2));
			  line2.startYProperty().bind(p.layoutYProperty().add(p.heightProperty().divide(2)));
			  line2.endXProperty().bind((p.layoutXProperty().add(p.widthProperty().add(node.layoutXProperty()))).divide(2));
			  line2.endYProperty().bind(node.layoutYProperty().add(node.heightProperty().divide(2)));
			  line3.startXProperty().bind((p.layoutXProperty().add(p.widthProperty().add(node.layoutXProperty()))).divide(2));
			  line3.startYProperty().bind(node.layoutYProperty().add(node.heightProperty().divide(2)));
			  line3.endXProperty().bind(node.layoutXProperty());
			  line3.endYProperty().bind(node.layoutYProperty().add(node.heightProperty().divide(2)));
		  }
		  else {
			  line1.startXProperty().bind(node.layoutXProperty().add(node.widthProperty()));
			  line1.startYProperty().bind(node.layoutYProperty().add(node.heightProperty().divide(2)));
			  line1.endXProperty().bind((node.layoutXProperty().add(node.widthProperty().add(p.layoutXProperty()))).divide(2));
			  line1.endYProperty().bind(node.layoutYProperty().add(node.heightProperty().divide(2)));
			  line2.startXProperty().bind((node.layoutXProperty().add(node.widthProperty().add(p.layoutXProperty()))).divide(2));
			  line2.startYProperty().bind(node.layoutYProperty().add(node.heightProperty().divide(2)));
			  line2.endXProperty().bind((node.layoutXProperty().add(node.widthProperty().add(p.layoutXProperty()))).divide(2));
			  line2.endYProperty().bind(p.layoutYProperty().add(p.heightProperty().divide(2)));
			  line3.startXProperty().bind((node.layoutXProperty().add(node.widthProperty().add(p.layoutXProperty()))).divide(2));
			  line3.startYProperty().bind(p.layoutYProperty().add(p.heightProperty().divide(2)));
			  line3.endXProperty().bind(p.layoutXProperty());
			  line3.endYProperty().bind(p.layoutYProperty().add(p.heightProperty().divide(2)));
		  }
		  line1.setStroke(Color.web("#d6ecf0"));
		  line2.setStroke(Color.web("#d6ecf0"));
		  line3.setStroke(Color.web("#d6ecf0"));
		  line1.setStrokeWidth(4);
		  line2.setStrokeWidth(4);
		  line3.setStrokeWidth(4);
		  MyDrawPane.g.getChildren().addAll(line1,line2,line3); 		  
	  }
	  
	  public static void redraw() {
		  MyDrawPane.g.getChildren().clear();
				  		  
		  //System.out.println("re state");
		  for(int i = 0; i<NodeList.list.size();i++) {
			  TreeNode node = NodeList.list.get(i);	
			  node.setText(node.getTxt());
		    	node.setStyle(
		    			"-fx-background-color:#d6ecf0;"	+
		        	    "-fx-background-radius:10;"+
		        	    //"-fx-border-color:#FAEBD7;"+   	    
		        	   // "-fx-border-radius:10;"+
		        	    "-fx-padding:10;"
		             );
		    //	System.out.print(node.getLeft());
		    //	System.out.println(node.getTop());
		        	node.setMaxSize(200, 80);
		        	node.setWrapText(true);
		        	node.setTextAlignment(TextAlignment.CENTER);
		        	node.setTextOverrun(OverrunStyle.ELLIPSIS);
		        	node.setTextFill(Color.BLACK);
		            node.setFont(new Font("Arial",20));    	
		            
			        MyDrawPane.g.getChildren().add(node);			        
			        MyDrawPane.g.applyCss();
			        MyDrawPane.g.layout();
			        
			        node.setLayoutX(node.getLeft());
			        node.setLayoutY(node.getTop());  

			        if(node != NodeList.getRoot()) {
						  line(node);
					  }   
			        if(node.getImgPath()!=null) {
			        	ImageView imageView = new ImageView(node.getImgPath());
			    		Label label = node;
			    		imageView.setFitWidth(100);
			    		imageView.setFitHeight(100);
			    		label.setGraphic(imageView);
			    		label.setContentDisplay(ContentDisplay.BOTTOM);
			    		label.setTextAlignment(TextAlignment.CENTER);
			        }
			        
		  }
	  }

	
	 
}
