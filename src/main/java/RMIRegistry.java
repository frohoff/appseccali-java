

import java.rmi.registry.LocateRegistry;

public class RMIRegistry {
	public static void main(String[] args) throws Exception {
		LocateRegistry.createRegistry(Integer.parseInt(args[0]));		

		System.out.println("started");
		
		while(true) {
			Thread.sleep(1000);
		}
	}

}
