package Main;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.ImageIOImageData;

import Inputs.MouseController;
import Interfaces.Drawableobject;

public class Interface {
	
	private int height = 480;
	private int width = 640;
	private int fps = 160;
	private float movex,movey,transx,transy,transxmaxpositive,transxmaxnegative,transymaxpositive,transymaxnegative;
	private List<Drawableobject> Drawableobjects = new CopyOnWriteArrayList<Drawableobject>();
	private List<Drawableobject> insert = new CopyOnWriteArrayList<Drawableobject>();
	private Drawableobject back;
	public boolean run;
	private boolean closerequested, cameramovement, reset;
	private GameController controller;
	private MouseController mousecontroller;
	
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
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_CONSTANT_ALPHA);
		//glClearColor(1f, 1f, 1f, 1.0f);
		
	}
	
	public void setMouseController(MouseController mousecontroller){
		
		this.mousecontroller = mousecontroller;
		
	}
	
	public void run() {
		
		while(!Display.isCloseRequested() && !closerequested){
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			updateGamecontroller();
			updateMouse();
			setKeyboardGamemechanic();
			getmovement();
			moveCamera();
			draw();
			
			Display.update();
			Display.sync(fps);
		}
		
		close();
		
	}
	
	public void setKeyboardGamemechanic(){
		
		controller.getKeyboardController().direction();
		
	}
	
	public void setGamecontroller(GameController controller){
		
		this.controller = controller;
		
	}
	
	public void setCameramoveable(boolean moveable){
		
		cameramovement = moveable;
		
	}
	
	private void updateGamecontroller(){
		
		controller.updateWorld();
		
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
	
	public void setCameraLimits(float transxmaxpositive, float transxmaxnegative, float transymaxpositiv, float transymaxnegativ){
		
		this.transxmaxpositive = transxmaxpositive;
		this.transxmaxnegative = transxmaxnegative;
		this.transymaxpositive = transymaxpositiv;
		this.transymaxnegative = transymaxnegativ;
		
	}
	
	private void moveCamera(){
		
		if(cameramovement){
			
			if(transx - movex > transxmaxpositive){
				
				movex = transxmaxpositive - transx;
				
			}
			
			if(transy - movey > transymaxpositive){
				
				movey = transymaxpositive - transy;
				
			}
			
			if(transx - movex< transxmaxnegative){
				
				movex = transxmaxnegative - transx;
				
			}
			
			if(transy - movey < transymaxnegative){
				
				movey = transymaxnegative - transy;
				
			}
			
			transx -= movex;
			transy -= movey;
			
			glTranslatef(movex, movey, 0);
			
		}
		
		if(reset){
			
			movex = transx;
			movey = transy;
			
			reset = false;
			
			moveCamera();
			
			
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
	
	public void setfps(int fps){
		
		this.fps = fps;
		
	}
	
	public void setmovement(float x ,float y, boolean cameramovement){
		
		movex = x;
		movey = y;
		this.cameramovement = cameramovement;
		
	}
	
	public void resetCamera(){
		
		reset = true;
		
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
	
	public List<Drawableobject> clear(){
		
		List<Drawableobject> old = Drawableobjects;
		Drawableobjects = new ArrayList<Drawableobject>();
		
		return old;
		
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
