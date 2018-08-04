
public class Input {
	private String node1;
	private String node2;
	private int distance;
	
	public String getNode1() {
		return node1;
	}
	public void setNode1(String node1) {
		this.node1 = node1;
	}
	public String getNode2() {
		return node2;
	}
	public void setNode2(String node2) {
		this.node2 = node2;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public Input(String pNode1, String pNode2, int pDistance){
		this.node1 = pNode1;
		this.node2 = pNode2;
		this.distance = pDistance;
	}
}
