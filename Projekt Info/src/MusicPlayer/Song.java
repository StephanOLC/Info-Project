package MusicPlayer;

public class Song {

	public String name;
	public int soundbuffer;
	public long length; //length in ms
	
	
	public void setName(String name){
		
		this.name = name;
		
	}
	
	public Song(long length, int soundbuffer){
		
		this.length = length;
		this.soundbuffer = soundbuffer;
		
	}
}
