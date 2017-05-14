package Objects;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.opengl.Texture;

import Inputs.MouseController;
import Main.GameController;
import Main.Interface;

public class Button implements object {
	
	int x,y,width,height;
	String pathnormal, fileformatnormal, pathpressed, fileformatpressed, name;
	Texture texturenormal, texturepressed;
	Interface inter;
	boolean pressed;
	List<ButtonListener> listener = new ArrayList<ButtonListener>();
	MouseController mousecontroller;
	
	public Button(int x,int y, String path, String fileformat, String name, Interface inter,GameController controller){
		
		this(x,y,path,fileformat,path,fileformat,name,inter,controller);
		
		
	}
	
	public Button(int x , int y, String pathnormal, String fileforamtnormal, String pathpressed, String fileformatpressed,String name, Interface inter, GameController controller){
		
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
		
		inter.addobject(this);
		mousecontroller = controller.getMouseController();
		
	}
	
	private void loadtextures(){
		
		texturenormal = new getTexture().gettexture(fileformatnormal, pathnormal);
		texturepressed = new getTexture().gettexture(fileformatpressed, pathpressed);
		
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

	@Override
	public int gety() {
		
		return y;
	}
	
	public String getName(){
		
		return name;
		
	}
	
	public boolean addListener(ButtonListener buttonListener){
		
		return listener.add(buttonListener);
		
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
		
		for(ButtonListener buttonListener : listener){
			
			buttonListener.onpress();
			
		}
		
	}
	
}
