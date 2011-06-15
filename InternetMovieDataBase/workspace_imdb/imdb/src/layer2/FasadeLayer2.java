package layer2;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import layer3.FasadeLayer3;

public class FasadeLayer2 {

	public static Object readSerializedObject(String inFileLocation) {
		ObjectInputStream objIn = null;
		try {
			objIn = new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream(inFileLocation)));
		} catch (Exception e) {
			FasadeLayer3.log("1" + e.getClass() + ": " + e.getMessage());
		} 
		Object serializedObject = null;
		
		try {
			serializedObject = objIn.readObject();
		} catch (Exception e) {
			FasadeLayer3.log("2" + e.getClass() + ": " + e.getMessage());
		} 
		
		try {
			objIn.close();
		} catch (Exception e) {
			FasadeLayer3.log("3" + e.getClass() + ": " + e.getMessage());
		} 
		return serializedObject;
	}
	
	public static void writeToSerialized(String inFileLocation,
			Object inSerializedObject) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(inFileLocation));
		oos.writeObject(inSerializedObject);
		oos.close();
	}

	public static File[] getFolderMovies(String inFolderMovies) {
		return (new File(inFolderMovies)).listFiles();
	}
	
	public static String getAPIString(String getAPIURL) throws MalformedURLException, IOException {
		return new BufferedReader(
				new InputStreamReader(
						new URL(getAPIURL).openConnection().getInputStream())).readLine();
	}
}
