package Objects;

import org.newdawn.slick.opengl.Texture;

import Main.Interface;

public class Button implements object {
	
	int x,y,width,height;
	String pathnormal, fileformatnormal, pathpressed, fileformatpressed;
	Texture texturenormal, texturepressed;
	Interface inter;
	
	public Button(int x,int y, String path, String fileformat, Interface inter){
		
		this.x = x;
		this.y = y;
		this.pathnormal = path;
		this.pathpressed = path;
		this.fileformatnormal = fileformat;
		this.fileformatpressed = fileformat;
		this.inter = inter;
		loadtextures();
		width = texturenormal.getImageWidth();
		height = texturenormal.getImageHeight();
		
		
	}
	
	public Button(int x , int y, String pathnormal, String fileforamtnormal, String pathpressed, String fileformatpressed, Interface inter){
		
		this.x = x;
		this.y = y;
		this.pathnormal = pathnormal;
		this.fileformatnormal = fileforamtnormal;
		this.pathpressed = pathpressed;
		this.fileformatpressed = fileformatpressed;
		this.inter = inter;
		loadtextures();
		width = texturenormal.getImageWidth();
		height = texturenormal.getImageHeight();
		
		if(width != texturepressed.getImageWidth() || height != texturepressed.getImageHeight()){
			
			texturepressed = texturenormal;
			System.err.println("Hieght or Width of the pressed texture differs from the height or width of the normal texture");
			
		}
		
	}
	
	public void loadtextures(){
		
		texturenormal = new getTexture().gettexture(fileformatnormal, pathnormal);
		texturepressed = new getTexture().gettexture(fileformatpressed, pathpressed);
		
	}

	@Override
	public void draw() {
		

	}

	@Override
	public int gety() {
		// TODO Auto-generated method stub
		return 0;
	}

}
