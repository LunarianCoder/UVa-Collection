import java.util.*;

class Component {
	String name;
	int nPointer;
	boolean isExplicit;

	Component(String name) {
		this.name = name;
		nPointer = -1;
		isExplicit = false;
	}
}

class DependencyGraph implements Iterable<Component> {

	Map<Component, Vector<Component>> mGraph = new HashMap<Component, Vector<Component>>();

	public boolean addComponent(Component item) {
		if (mGraph.containsKey(item))
        	return false;
    	mGraph.put(item, new Vector<Component>());
    		return true;
	}

	public void addDependency(Component item1, Component item2) {
		mGraph.get(item1).add(item2);
	}

	public Vector<Component> dependencyFrom(Component item) {
		Vector<Component> dependencies = mGraph.get(item);
		return dependencies;
	}

	public Iterator<Component> iterator() {
    	return mGraph.keySet().iterator();
	}

}

class Main {
	
	static Map<String, Component> componentMapping = new HashMap<String, Component>();
	static Vector<Component> order = new Vector<Component>();
	static DependencyGraph DG = new DependencyGraph();

	public static Component getComponent(String name) {
		if (componentMapping.containsKey(name)) {
			return componentMapping.get(name);
		} else {
			Component item = new Component(name);
			componentMapping.put(name, item);
			return item;
		}
	}	

	public static void proceedInstalling(Component item1) {

		DG.addComponent(item1);

		if(item1.nPointer >= 0) {
			System.out.println("   "+item1.name+" is already installed.");
			return;
		}

		for(Component item2: DG.dependencyFrom(item1)) {	
			if (item2.nPointer == -1) {
				proceedInstalling(item2);
				item2.nPointer++;
			} else {
				item2.nPointer++;
			}
		}

		System.out.println("   Installing "+item1.name);
		order.add(item1);
		item1.nPointer++;
		
	}

	public static void proceedRemoving(Component item1) {
		if (item1.nPointer == 0) {
			System.out.println("   Removing "+item1.name);
			item1.isExplicit = false;
			order.remove(item1);
			item1.nPointer--;
		} else if (item1.nPointer >= 1){
			System.out.println("   "+item1.name+" is still needed.");
			return;
		} else {
			System.out.println("   "+item1.name+" is not installed.");
			return;
		}
		for (Component item2: DG.dependencyFrom(item1)) {	
			item2.nPointer--;
		}
		for (Component item2: DG.dependencyFrom(item1)) {	
			if ((item2.nPointer == 0) && (item2.isExplicit == false)){
				proceedRemoving(item2);
			}
		}
	}

	public static void proceedListing() {

		for (Component item1: order) {
			//System.out.println("   "+item1.name+" "+item1.isExplicit+" "+item1.nPointer);
			System.out.println("   "+item1.name);
		}
		// }
	}


	public static void main(String args[]) {
 		Scanner input = new Scanner(System.in);
		while(input.hasNextLine()){
			String line = input.nextLine(); 
			String[] tokens = line.split("\\s+");

			if (tokens[0].equals("DEPEND")) {
				//action to DEPEND command
				Component item1 = getComponent(tokens[1]);
				DG.addComponent(item1);
				for (int  i=2; i<tokens.length; i++) {
					Component item2 = getComponent(tokens[i]);
					DG.addComponent(item2);
					DG.addDependency(item1, item2);
				}
				//response to DEPEND command
				System.out.println(line);
			} 
			else if (tokens[0].equals("INSTALL")) {
				//response to INSTALL command
				System.out.println(line);
				Component item1 = getComponent(tokens[1]);
				if(item1.nPointer == -1) {
					item1.isExplicit = true;
				}
				proceedInstalling(item1);
			} 
			else if (tokens[0].equals("REMOVE")) {
				//response to REMOVE command
				System.out.println(line);
				Component item1 = getComponent(tokens[1]);
				proceedRemoving(item1);
			} 
			else if (tokens[0].equals("LIST")) {
				//response to LIST command
				System.out.println(line);
				proceedListing();
				
			}
			else if (tokens[0].equals("END")) {
				System.out.println(line);
			}
		}
	}



}