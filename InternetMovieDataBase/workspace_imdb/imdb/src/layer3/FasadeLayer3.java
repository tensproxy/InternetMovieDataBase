package layer3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.Locale;

public class FasadeLayer3 {
	
	private static final String[] ISOLANGUAGES = Locale.getISOLanguages();

	private static LinkedList<Locale> LOCALES = null;

	public static String getAPIEntry(String inAPIURL) throws MalformedURLException, IOException{
		return layer2.FasadeLayer2.getAPIString(inAPIURL);
	}
	
	public static <T extends Entry> String toString (String inTitle, LinkedList<T> linkedList){
		StringBuilder aSB = new StringBuilder();
		aSB.append(inTitle + ":");
		for (Entry e : linkedList){
			aSB.append(" " + e.getName());
		}
		aSB.append(System.getProperty("line.separator"));
		return aSB.toString();
	}
	
	public static File[] getFolderMovies(String inFolderMovies) {
		return layer2.FasadeLayer2.getFolderMovies(inFolderMovies);
	}

	private static String[] GETISOLANGUAGES() {
		return FasadeLayer3.ISOLANGUAGES;
	}
	
	private static LinkedList<Locale> GETLOCALES() {
		if (FasadeLayer3.LOCALES == null) {
			FasadeLayer3.SETLOCALES(new LinkedList<Locale>());
		}
		return FasadeLayer3.LOCALES;
	}
	
	public static Object readSerializedObject (String inFileLocation) throws FileNotFoundException, IOException, ClassNotFoundException{
		return layer2.FasadeLayer2.readSerializedObject(inFileLocation);
	}
	private static LinkedList<Locale> SETLOCALES(LinkedList<Locale> inLocales) {
		FasadeLayer3.LOCALES = inLocales;
		return FasadeLayer3.GETLOCALES();
	}
	
	static public void setSerializedDB (String inFileLocation, Object inObject) throws FileNotFoundException, IOException, ClassNotFoundException{
		layer2.FasadeLayer2.writeToSerialized(inFileLocation, inObject);
	}

	public static String toString(Object inObject) {
		
		StringBuilder aSB = new StringBuilder();
        Class<?> c = inObject.getClass();
        
        aSB.append("Fields:" + System.getProperty("line.separator"));        
        Field[] fields = c.getDeclaredFields();
        for ( Field f : fields ) {
			aSB.append(f.getType() + " " + f.getName());
        	try {
        		aSB.append("=" + f.get(f));
			} catch (IllegalArgumentException e) {
				;
			} catch (IllegalAccessException e) {
				;
			}
			aSB.append("\n");
        }

        aSB.append("Methods:" + System.getProperty("line.separator"));        
        Method[] methods = c.getMethods();
    	Object methobj = inObject;
        Object[] arguments = new Object[0];
        for (Method m : methods){
        	if (m.getName().substring(0,3).equals("get")){
        		aSB.append(m.getName() + " = " );
				try {
					aSB.append(m.invoke(methobj, arguments)); 
				} catch (Exception e) {
					aSB.append("err:" + e.getClass().getSimpleName());
				}
				aSB.append(System.getProperty("line.separator"));
        	}
        }
        return aSB.toString();
	}

	public static String toString(Object[] objects) {
		StringBuilder aSB = new StringBuilder();
		for ( int i = 0 ; i < objects.length ; i++ ) {
			aSB.append(objects[i].toString() + " ");					
		}
		return aSB.toString();
	}

	public static boolean isValidLocale(String inStringLocale) {
		boolean isValid = false;
		if (!FasadeLayer3.GETLOCALES().contains(inStringLocale)) {
			for (int i = 0; i < FasadeLayer3.GETISOLANGUAGES().length; i++) {
				if (FasadeLayer3.GETISOLANGUAGES()[i].equals(inStringLocale)) {
					isValid = FasadeLayer3.GETLOCALES().add(new Locale(inStringLocale));
				}
			}
		}
		return isValid;
	}

	public static void log(String string) {
		System.out.println(string);
	}

	public static void writeTXT(String inFileLocation, String inString) {
		layer2.FasadeLayer2.writeTXT(inFileLocation, inString);
	}
}