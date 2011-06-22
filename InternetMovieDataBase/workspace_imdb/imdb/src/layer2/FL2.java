package layer2;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

public class FL2 {

	public static boolean exists(String inFile) {
		return new File(inFile).exists();
	}
	
	public static File[] getFolder(File inFolder) {
		return inFolder.listFiles();
	}

	public static String readFile(File inFile) throws FileNotFoundException, IOException {
		byte[] buffer = new byte[(int) inFile.length()];
		BufferedInputStream f = new BufferedInputStream(new FileInputStream(inFile));
		f.read(buffer);
		return new String (buffer);
	}
	
	public static String readHTTPRequest(URL inURL) throws IOException {
		return
			new BufferedReader(
				new InputStreamReader(
						inURL.openConnection().getInputStream())).readLine();
	}

	public static Object readSerializedObject(String inFileLocation) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream objIn = new ObjectInputStream(
				new BufferedInputStream(
						new FileInputStream(inFileLocation)));
		Object returnSerializedObject = objIn.readObject();
		objIn.close();			
		return returnSerializedObject;
	}

	public static void writeFile(String inFileLocation,
			Object inObject) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(inFileLocation));
		oos.writeObject(inObject);
		oos.close();
	}

	public static void writeFile(String inFileLocation, String inString) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(
				inFileLocation));
		out.write(inString);
		out.close();
	}

	public static boolean deleteFile(String inFileLocation) {
		boolean isDeleted = false;
		File aFile = new File(inFileLocation);
		if (aFile.exists()) {
			if (aFile.isFile()) {
		}
			isDeleted = aFile.delete();
		}
		return isDeleted;			
	}
}