/**
 * Interface for Water type Pokemon
 * @author Ivan Kim
 *
 */
public interface Water {
	
	public static final int type = 1;
	
	public static final String typeMenu = "1. Water Gun\n2. Bubblebeam\n3. Hydro Pump";
	
	public int waterGun();
	
	public int bubblebeam();
	
	public int hydroPump();
}
