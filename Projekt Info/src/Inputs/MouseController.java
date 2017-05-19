package Inputs;

import org.lwjgl.input.Mouse;

import Main.Interface;

public class MouseController {
	
	int x,y,dx,dy;
	float ax,ay, xtrans, ytrans;
	boolean left,right;
	Interface inter;
	
	public MouseController(Interface inter){
		
		this.inter = inter;
		
	}
	
	public int getx(){
		
		return x;
		
	}
	
	public int gety(){
		
		return y;
		
	}
	
	public float getabsolutex(){
		
		return ax;
		
	}

	public float getabsolutey(){
		
		return ay;
		
	}

	public int getdynamicx(){
		
		return dx;
		
	}
	
	public int getdynamicy(){
		
		return dy;
		
	}
	
	public boolean isleftdown(){
		
		return left;
		
	}
	
	public boolean isrightdown(){
		
		return right;
		
	}
	
	public void refresh(){
		
		x = Mouse.getX();
		y = inter.getheight() - Mouse.getY();
		dx = Mouse.getDX();
		dy = Mouse.getDY();
		ax = x + xtrans;
		ay = y + ytrans;
		left = Mouse.isButtonDown(0);
		right = Mouse.isButtonDown(1);
		
	}
	
	public void settrans(float transx, float transy){
		
		this.xtrans = transx;
		this.ytrans = transy;
		
	}
}
