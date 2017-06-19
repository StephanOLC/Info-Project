package graphicObjects;

import static org.lwjgl.opengl.GL11.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.opengl.Texture;

import Inputs.MouseController;
import Interfaces.ClickListener;
import Interfaces.Drawableobject;
import Main.GameController;
import Main.Interface;

public abstract class ClickableObject implements Drawableobject {
	
	protected int x,y,width,height;
	protected String pathnormal, fileformatnormal, pathpressed, fileformatpressed, name;
	protected Texture texturenormal, texturepressed;
	protected Interface inter;
	protected boolean pressed;
	protected List<ClickListener> listener = new CopyOnWriteArrayList<ClickListener>();
	protected MouseController mousecontroller;
	
	public ClickableObject(int x,int y, String path, String fileformat, String name, Interface inter,GameController controller){
		
		this(x,y,path,fileformat,path,fileformat,name,inter,controller);
		
		
	}
	
	public ClickableObject(int x , int y, String pathnormal, String fileforamtnormal, String pathpressed, String fileformatpressed,String name, Interface inter, GameController controller){
		
		this.x = x;
		this.y = y;
		this.pathnormal = pathnormal;
		this.fileformatnormal = fileforamtnormal;
		this.pathpressed = pathpressed;
		this.fileformatpressed = fileformatpressed;
		this.inter = inter;
		this.name = name;
		loadtextures();
		width = texturenormal.getImageWidth();
		height = texturenormal.getImageHeight();
		
		if(width != texturepressed.getImageWidth() || height != texturepressed.getImageHeight()){
			
			texturepressed = texturenormal;
			System.err.println("Height or Width of the pressed texture differs from the height or width of the normal texture");
			
		}
		
		inter.addDrawableobject(this);
		mousecontroller = controller.getMouseController();
		
	}
	
	private void loadtextures(){
		
		texturenormal = new getTexture().gettexture(fileformatnormal, pathnormal);
		texturepressed = new getTexture().gettexture(fileformatpressed, pathpressed);
		if(width != texturepressed.getImageWidth() || height != texturepressed.getImageHeight()){
			
			texturepressed = texturenormal;
			System.err.println("Height or Width of the pressed texture differs from the height or width of the normal texture");
			
		}
		
		
	}

	@Override
	public void draw() {
		
		if(pressed){
			
			drawtexture(texturepressed);
			
		}else{
			
			drawtexture(texturenormal);
			
		}

	}
	
	public void drawtexture(Texture texture){
		
		texture.bind();
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
	
	public Texture getTexture(){
		
		return texturenormal;
		
	}
	
	public Texture getTexturepressed(){
		
		return texturepressed;
		
	}
	
	protected void setTexturenormal(Texture texture){
		
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
		
	}

	public String getName(){
		
		return name;
		
	}
	
	public boolean addListener(ClickListener clickListener){
		
		return listener.add(clickListener);
		
	}

	@Override
	public void run() {
		
		while(inter.run){
		
			int x = (int) mousecontroller.getabsolutex();
			int y = (int) mousecontroller.getabsolutey();
		
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
	
}
