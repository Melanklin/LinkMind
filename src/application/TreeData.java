package application;

import javafx.scene.control.Label;

public class TreeData extends Label implements java.io.Serializable {
	  private static final long serialVersionUID = 1L;
    private String txt;
	private String imgPath;
    
    public TreeData(String text) {
      super(text);
  	  this.txt=text;
  	  
    }
    
    public TreeData() {

      }
    

	public String getTxt() {
		return txt;
	}


	public void setTxt(String text) {
		this.txt = text;
	}
	
	
	public String getImgPath() {
			return imgPath;
		}

	public void setImgPath(String imgPath) {
			this.imgPath = imgPath;
		}
    
}
