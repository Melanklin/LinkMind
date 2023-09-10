package application;



public class CheckPane { 
	
	 public static double width = 0;
	 public static double height = 0;
	
	
	public static void controlPane() {
		 //控制画板的大小
		  double width = 1000+DeriveGraph.getMaxWidth()*2;
		  double height = 1000+DeriveGraph.getMaxHeight();
		  MyDrawPane.drawPane.setPrefWidth(width);
		  MyDrawPane.drawPane.setPrefHeight(height);
		 
		  		  
		  Main.pane.applyCss();
		  Main.pane.layout();
		  //设置滚轮为中间值
		  MyDrawPane.sp.setVvalue(0.5);
		  MyDrawPane.sp.setHvalue(0.5);	

	}
		
}
