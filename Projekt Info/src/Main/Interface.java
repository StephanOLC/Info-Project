package Main;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.ImageIOImageData;

import Inputs.MouseController;
import Interfaces.Drawableobject;

public class Interface {
	
	int height = 480;
	int width = 640;
	float movex,movey,transx,transy;
	List<Drawableobject> Drawableobjects = new ArrayList<Drawableobject>();
	List<Drawableobject> insert = new ArrayList<Drawableobject>();
	Drawableobject back;
	public boolean run;
	boolean closerequested, cameramovement;
	MouseController mousecontroller;
	
	public Interface(){
		
		run = true;
		closerequested = false;
		cameramovement = true;
		init();
		
	}
	
	public void init(){
		
		try {
			
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle("Project: Osiris");
			
			try {
				
				Display.setIcon(new ByteBuffer[] {
				        new ImageIOImageData().imageToByteBuffer(ImageIO.read(new File("Graphics/icon.png")), false, false, null),
				        new ImageIOImageData().imageToByteBuffer(ImageIO.read(new File("Graphics/icon.png")), false, false, null)
				        });
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
			}
			
			Display.create();
			
		} catch (LWJGLException e) {
		
			e.printStackTrace();
			
		}
		
		//Initialization code OpenGL
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, height, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		
	}
	
	public void setMouseController(MouseController mousecontroller){
		
		this.mousecontroller = mousecontroller;
		
	}
	
	public void run() {
		
		while(!Display.isCloseRequested() && !closerequested){
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			updateMouse();
			getmovement();
			moveCamera();
			draw();
			
			Display.update();
			Display.sync(160);
		}
		
		close();
		
	}
	
	public void updateMouse(){
		
		mousecontroller.settrans(transx, transy);
		mousecontroller.refresh();
		
	}
	
	private void close(){
		
		run = false;
		Display.destroy();
		System.exit(1);
		
	}
	
	public void requestclose(){
		
		closerequested = true;
		
	}
	
	public int getwidth(){
		
		return width;
		
	}
	
	public int getheight(){
		
		return height;
		
	}
	
	
	private void moveCamera(){
		
		if(cameramovement){
			
			transx -= movex;
			transy -= movey;
		
			glTranslatef(movex, movey, 0);
			
		}
		
		
	}
	
	private void getmovement(){
		
		if(mousecontroller.isleftdown()){
			
			movex = mousecontroller.getdynamicx();
			movey = -mousecontroller.getdynamicy();
			
		}else{
			
			movex *= 0.25f;
			movey *= 0.35f;
			
			if(movex < 0.7){
				
				movex = 0;
				
			}
			
			if(movey < 0.7){
				
				movey = 0;
				
			}
			
		}
		
	}
	
	
	public void setmovement(float x ,float y){
		
		movex = x;
		movey = y;
		
	}
	
	private void draw(){
		
		if(back != null){
			
			back.draw();
		}
		
		Drawableobjects.sort(new Sorter());
		
		for(Drawableobject obj : Drawableobjects){
			
			obj.draw();
			
		}
		
		for(Drawableobject obj : insert){
			
			Drawableobjects.add(obj);
			
		}
		
		insert = new ArrayList<Drawableobject>();
		
	}

	public void addDrawableobject(Drawableobject obj){
		
		insert.add(obj);
		
	}

	public void setbackground(Drawableobject obj){
		
		back = obj;
		
	}

	public static class Sorter implements Comparator<Drawableobject>{
	
		@Override
		public int compare(Drawableobject obj1, Drawableobject obj2) {
			
			return obj1.gety()- obj2.gety();
			
		}
		
	}
}
