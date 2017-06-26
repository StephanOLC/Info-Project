package graphicObjects;

import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.opengl.Texture;

import Inputs.MouseController;
import Interfaces.ClickListener;
import Interfaces.Drawableobject;
import Main.GameController;
import Main.Interface;

public abstract class ClickableObject implements Drawableobject {
	
	protected int x,y,width,height,imageIDnormal, imageIDpressed;
	protected String pathnormal, pathpressed, name;
	protected Interface inter;
	protected boolean pressed,stop;
	protected List<ClickListener> listener = new CopyOnWriteArrayList<ClickListener>();
	protected MouseController mousecontroller;
	
	public ClickableObject(int x,int y, String path, String name, Interface inter,GameController controller){
		
		this(x,y,path,path,name,inter,controller);
		
		
	}
	
	public ClickableObject(int x , int y, String pathnormal, String pathpressed,String name, Interface inter, GameController controller){
		
		this.x = x;
		this.y = y;
		this.pathnormal = pathnormal;
		this.pathpressed = pathpressed;
		this.inter = inter;
		this.name = name;
		BufferedImage image = TextureLoader.loadImage(pathnormal);
		width = image.getWidth();
		height = image.getHeight();
		imageIDnormal = TextureLoader.loadTexture(image);
		BufferedImage imagepressed = TextureLoader.loadImage(pathpressed);
		width = imagepressed.getWidth();
		height = imagepressed.getHeight();
		imageIDpressed = TextureLoader.loadTexture(image);
		
		if(width != imagepressed.getWidth() || height != imagepressed.getHeight()){
			
			imageIDnormal = imageIDpressed;
			System.err.println("Height or Width of the pressed texture differs from the height or width of the normal texture");
			
		}
		
		inter.addDrawableobject(this);
		mousecontroller = controller.getMouseController();
		
	}
	
	@Override
	public void draw() {
		
		if(pressed){
			
			drawtexture(imageIDpressed);
			
		}else{
			
			drawtexture(imageIDnormal);
			
		}

	}
	
	public void drawtexture(int imageID){
		
		glBindTexture(GL_TEXTURE_2D, imageID);
		glColor3f(1, 1, 1);
		glEnable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2i(x, y);
			glTexCoord2f(1,0);
			glVertex2i(x + width, y);
			glTexCoord2f(1,1);
			glVertex2i(x + width, y + height);
			glTexCoord2f(0,1);
			glVertex2i(x, y + height);
		glEnd();
		glBindTexture(GL_TEXTURE_2D, 0);
		
	}
	
	public int getx(){
		
		return x;
		
	}
	
	@Override
	public int gety() {
		
		return y;
	}
	
	public int getwidth(){
		
		return width;
		
	}
	
	public int getheight(){
		
		return height;
		
	}
	
	public int getTextureID(){
		
		return imageIDnormal;
		
	}
	
	public int getTextureIDpressed(){
		
		return imageIDpressed;
		
	}
	
	protected void setwidth(int width){
		
		this.width = width;
		
	}
	
	protected void setheight(int height){
		
		this.height = height;
		
	}
	
	/**protected void setTexturenormal(Texture texture){
		
		pathnormal = "";
		fileformatnormal = "";
		this.texturenormal = texture;
		
	}
	
	protected void newTexturenormal(String path, String fileformnat){
		
		pathnormal = path;
		fileformatnormal = fileformnat;
		
	}
	
	protected void setTexturepressed(Texture texture){
		
		pathpressed = "";
		fileformatpressed = "";
		this.texturepressed = texture;
		
	}
	
	protected void newTexturepressed(String path, String fileformat){
		
		pathpressed = path;
		fileformatpressed = fileformat;
		loadtextures();
		
	}*/

	public String getName(){
		
		return name;
		
	}
	
	public boolean addListener(ClickListener clickListener){
		
		return listener.add(clickListener);
		
	}

	@Override
	public void run() {
		
		while(inter.run && !stop){
		
			int x = (int) mousecontroller.getabsolutex();
			int y = (int) mousecontroller.getabsolutey();
			
			System.out.print("");
		
			if(x > this.x && x < this.x + width && y > this.y && y  < this.y + height && mousecontroller.isleftdown()){
			
				if(!pressed){
				
					pressed = true;
					onpress();
			
				}
			
			}else{
			
				pressed = false;
			
			}
			
		}
		
		
	}
	
	public void onpress(){
		
		System.out.println("Button pressed: " + name);
		
		for(ClickListener clickListener : listener){
			
			clickListener.onpress(getName());
			
		}
		
	}
	
	public void stop(){
		
		stop = true;
		
	}
	
}
