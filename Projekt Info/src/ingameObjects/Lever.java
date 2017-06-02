package ingameObjects;

import java.util.ArrayList;

import gameLogic.Vektor;
import gameLogic.World;
import graphicObjects.TextureObject;

public class Lever extends TextureObject implements IngameObject {
	
	private boolean activated;
	private World world;
	private Vektor position;
	private ArrayList<Device> deviceList;
	
	public Lever(Vektor position, World world){
		super(position.getX(), position.getY(), "Graphics/icon.png", "png", "Lever" ,world.getInterface());
		this.position = position;
		this.world = world;
		deviceList = new ArrayList<Device>();
	}

	@Override
	public char getTeam() {
		return 'l';
	}

	@Override
	public Vektor getPosition() {
		return position;
	}
	
	@Override
	public void tick() {
		collision(world.detectCollissionType(position));
		
	}
	
	public boolean getActivated(){
		return activated;
	}
	
	public void addDevice(Device device){
		deviceList.add(device);
	}
	
	public void activateDevices(){
		for(Device device : deviceList){
			device.activate(activated);
		}
	}
	
	public void collision(ArrayList<Integer> collisions){
		for(int effect : collisions){
			switch (effect){
				default: activated = !activated;
				break;
			}
		}
	}

	@Override
	public void updateGraphic() {
		setPosition(position.getX(), position.getY());
		
	}

}
