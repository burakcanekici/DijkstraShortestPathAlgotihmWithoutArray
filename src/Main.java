import java.security.AllPermission;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// This hashmap is used for contain all paths from source to possible target.
		// e.x. If key is D, value is ABCD because according to input the shortest path between A and D is ABCD.
		HashMap<String, String> allPath = new HashMap<String, String>();
		
		ArrayList<Input> input = InitializeInput();
		String source = "A";
		String target = "D";
		DijkstraAlgorithm(input, source, allPath);
		PrintPathBetweenSourceAndTarget(allPath, target);
	}
	
	public static ArrayList<Input> InitializeInput(){
		ArrayList<Input> input = new ArrayList<Input>();
		input.add(new Input("A", "B", 6));
		input.add(new Input("A", "H", 9));
		input.add(new Input("B", "C", 9));
		input.add(new Input("B", "H", 11));
		input.add(new Input("C", "D", 5));
		input.add(new Input("C", "F", 6));
		input.add(new Input("C", "I", 2));
		input.add(new Input("D", "E", 9));
		input.add(new Input("D", "F", 16));
		input.add(new Input("E", "F", 10));
		input.add(new Input("F", "G", 2));
		input.add(new Input("G", "H", 1));
		input.add(new Input("G", "I", 6));
		input.add(new Input("H", "I", 5));
		
		return input;
	}
	
	public static Integer GetDistance(ArrayList<Input> input, String p1, String p2){
		for(Input i : input){
			if((i.getNode1().equals(p1) && i.getNode2().equals(p2)))
				return i.getDistance();
		}
		return 0;
	}
	
	public static String TheClosestNeighbour(ArrayList<Input> input, HashMap<String, Integer> distance, HashMap<String, Boolean> passSet){
		String neighbour = "";
		int min = Integer.MAX_VALUE;
		
		for(String node : distance.keySet()){
			if(!passSet.get(node) && distance.get(node) <= min){
				min = distance.get(node);
				neighbour = node;
			}
		}
		
		return neighbour;
	}
	
	public static void PrintPathBetweenSourceAndTarget(HashMap<String, String> allPath, String target){
		String path = allPath.get(target);
		if(path == null)
			System.out.println("The path to " + target + "has not been found!");
		
		System.out.println("The path to " + target + " is :");
		System.out.println(path);
	}
	
	public static void DijkstraAlgorithm(ArrayList<Input> input, String source, HashMap<String, String> allPath){
		HashMap<String, Integer> distance = new HashMap<String, Integer>();
		HashMap<String, Boolean> passSet = new HashMap<String, Boolean>();
		
		for(int i=0;i<input.size();i++){
			distance.put(input.get(i).getNode1(), Integer.MAX_VALUE);
			passSet.put(input.get(i).getNode1(), false);
		}
		
		distance.replace(source, 0);
		
		for(String node : distance.keySet()){
			String theClosestNeighbour = TheClosestNeighbour(input, distance, passSet);
			passSet.replace(theClosestNeighbour, true);
			for(String node1 : distance.keySet()){
				if(!passSet.get(node1) && GetDistance(input, theClosestNeighbour, node1) != 0 &&
					distance.get(theClosestNeighbour) != Integer.MAX_VALUE &&
					distance.get(theClosestNeighbour) + GetDistance(input, theClosestNeighbour, node1) < distance.get(node1)){
					distance.replace(node1, distance.get(theClosestNeighbour) + GetDistance(input, theClosestNeighbour, node1));
					
					// besides Dijkstra, these lines modify allPath
					String previous = allPath.get(theClosestNeighbour);
					if(previous == null){
						previous = theClosestNeighbour;
					}
					
					previous += "->" + node1;
					allPath.put(node1, previous);
				}
			}
		}
		
		System.out.println("The distance between " + source + " to :");
		for(String n : distance.keySet()){
			System.out.println(n + " : " + distance.get(n));
		}
		
	}

}
