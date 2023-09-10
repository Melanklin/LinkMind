package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javafx.stage.Stage;

public class ReadTree {

	public static void read(File file,Stage stage) {
		 NodeList.list= new ArrayList<>();
		// TODO Auto-generated method stub
		try {
		         ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		         NodeList.list = (List<TreeNode>) ois.readObject();             
		         
		}catch(Exception e) {
	         e.printStackTrace();
	     }
		
//		for(int i=0;i<NodeList.list.size();i++) {
//        System.out.println("名字为：" + NodeList.list.get(i).getNid() + "\n年龄为：" +  NodeList.list.get(i).getHeight() + " 孩子 " +  NodeList.list.get(i).getNodeChildren().size()
//                +" 内容 " +  NodeList.list.get(i).getTxt()+"图片路径"+NodeList.list.get(i).getImgPath());
//      }
		String name=file.getName();
		SetTitle.setTitle(name);
		SetTitle.changeTitle(stage);
		
	 }
		        
}
      
    
