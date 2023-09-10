package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class TreeNode extends TreeData{
	
	private static final long serialVersionUID = 1L;
	private int pid;
	private int nid;
	private int pos = 1;
	private double left = 0;
	private double top = 0;
	private boolean click = false;
	private List<TreeNode> nodeChildren = new ArrayList<>();
	
	
    public TreeNode(int pid,String text) {
    	super(text);
    	this.pid=pid;
    	this.setStyle(
    		"-fx-background-color:#FAEBD7;"	+
    	    "-fx-background-radius:10;"+
    	   // "-fx-border-color:#FAEBD7;"+   	    
    	    //"-fx-border-radius:10;"+
    		"-fx-padding:10;"
         );
    	this.setMaxSize(200, 80);
    	this.setWrapText(true);
    	this.setTextAlignment(TextAlignment.CENTER);
    	this.setTextOverrun(OverrunStyle.ELLIPSIS);
    	this.setTextFill(Color.WHITE);
        this.setFont(new Font("Arial",20));           
    	
    }
    
    public TreeNode() {
       super();
       this.setStyle(
       		"-fx-background-color:#FAEBD7;"	+
       	    "-fx-background-radius:10;"+
       	   // "-fx-border-color:#FAEBD7;"+   	    
       	    //"-fx-border-radius:10;"+
       		"-fx-padding:10;"
            );
       	this.setMaxSize(200, 80);
       	this.setWrapText(true);
       	this.setTextAlignment(TextAlignment.CENTER);
       	this.setTextOverrun(OverrunStyle.ELLIPSIS);
       	this.setTextFill(Color.WHITE);
           this.setFont(new Font("Arial",20));  
    }

	public int getPid() {
		return pid;
	}


	public void setPid(int pid) {
		this.pid = pid;
	}

	public double getLeft() {
		return left;
	}


	public void setLeft(double left) {
		this.left = left;
	}


	public double getTop() {
		return top;
	}


	public void setTop(double top) {
		this.top = top;
	}


	public List<TreeNode> getNodeChildren() {
		return nodeChildren;
	}
    
	public int getNid() {
		return nid;
	}


	public void setNid(int nid) {
		this.nid = nid;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}
	

	
	public boolean isClick() {
		return click;
	}

	public void setClick(boolean click) {
		this.click = click;
	}



}
