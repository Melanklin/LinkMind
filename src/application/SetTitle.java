package application;

import javafx.stage.Stage;

public class SetTitle {
	static String title=null;
	public static void setTitle(String name) {
		title=name;
		
	}
	
	public static String getTitle() {
		return title;
	}
	
	public static void changeTitle(Stage stage) {
		
		if(NodeList.list.isEmpty()==true) {
			stage.setTitle("LinkMind");
		}
		
		else if((NodeList.list.isEmpty()!=true)&&(title==null)) {
		stage.setTitle("LinkMind-未命名");
	 }
		
	 else {
		 stage.setTitle("LinkMind-"+getTitle());
	 }
	}
	
}
