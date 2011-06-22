package layer3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.LinkedList;
import java.util.Locale;

import layer2.FL2;

public class FL3 {
	
	private static final String[] ISOLANGUAGES = Locale.getISOLanguages();
	private static LinkedList<Locale> LOCALES = null;
	private static final String INPUTSOURCE = "inputsource.imdb";
	private static final String INPUTSOURCESER = "inputsource.ser";
	private static final String SEPARATOR = System.getProperty("line.separator");
	private static final String TESTWRITABLE = "test.imdb";
	private static final String TESTONLINE = "http://www.xkcd.com";
	private static final String OUTPUTSOURCE = "output";
	
	public static String[] getMovieTuples() {
		String [] sources = null;
		File aFile = new File (INPUTSOURCE);
		if (aFile.isFile()){
			sources = FL3.getMovieTuplesFromFile(aFile);
		} else {
			sources = FL3.getMovieTuplesFromDirectory(aFile);
		}
		return sources;
	}
	
	public static String[] getMovieTuplesFromDirectory(File inFile) {
		File aFile = inFile.getAbsoluteFile().getParentFile();
		String[] foldernames = null;
		File[] files = layer2.FL2.getFolder(aFile);
		foldernames = new String[files.length];
		for ( int i = 0 ; i < files.length ; i++ ) {
			foldernames[i] = files[i].getName();
		}			
		return foldernames;
	}
	
	public static String[] getMovieTuplesFromFile(File inFile){
		String [] localProxyFiles = null;
		try {
			localProxyFiles = layer2.FL2.readFile(inFile).split(SEPARATOR);
		} catch (Exception e) {
			FL3.log(e);
		}
		return localProxyFiles;
	}
	
	private static String[] GETISOLANGUAGES() {
		return FL3.ISOLANGUAGES;
	}
	
	private static LinkedList<Locale> GETLOCALES() {
		if (FL3.LOCALES == null) {
			FL3.SETLOCALES(new LinkedList<Locale>());
		}
		return FL3.LOCALES;
	}

	@SuppressWarnings("unchecked")
	public static EntryList<Entry> getSerializedEntries() {
		Object o = null;
		try {
			o = layer2.FL2.readSerializedObject(FL3.INPUTSOURCESER);
		} catch (Exception e) {
			FL3.log(e);
			o = new EntryList<Entry>();
		} finally {
			;
		}
		return (EntryList<Entry>) o;
	}
	
	public static boolean isValidLocale(String inStringLocale) {
		boolean isValid = false;
		if (!FL3.GETLOCALES().contains(inStringLocale)) {
			for (int i = 0; i < FL3.GETISOLANGUAGES().length; i++) {
				if (FL3.GETISOLANGUAGES()[i].equals(inStringLocale)) {
					isValid = FL3.GETLOCALES().add(new Locale(inStringLocale));
					break;
				}
			}
		}
		return isValid;
	}
	
	public static void log(Exception e) {
		System.err.println(e.getClass() + ": " + e.getMessage());
	}
	
	public static void inf(String inString, int importance) {
		System.out.println(inString);
	}

	public static String readScrapedString(URL inURL){
		String returnAPIEntry = null;
		try {
			returnAPIEntry = layer2.FL2.readHTTPRequest(inURL);
		} catch (Exception e) {
			FL3.log(e);
		}
		return returnAPIEntry;
	}

	public static Object readSerializedObject (String inFileLocation) throws FileNotFoundException, IOException, ClassNotFoundException{
		return layer2.FL2.readSerializedObject(inFileLocation);
	}

	private static LinkedList<Locale> SETLOCALES(LinkedList<Locale> inLocales) {
		FL3.LOCALES = inLocales;
		return FL3.GETLOCALES();
	}

	public static String toString(Object inObject) {
		
		StringBuilder aSB = new StringBuilder();
        Class<?> c = inObject.getClass();
        
        aSB.append("Fields:" + SEPARATOR );        
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

        aSB.append("Methods:" + SEPARATOR);        
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
				aSB.append(SEPARATOR);
        	}
        }
        return aSB.toString();
	}

	public static String toString(Object[] objects) {
		StringBuilder aSB = new StringBuilder();
		for ( int i = 0 ; i < objects.length ; i++ ) {
			aSB.append(i + ". " + objects[i].toString() + SEPARATOR);					
		}
		return aSB.toString();
	}

	public static <T extends Entry> String toString (String inTitle, LinkedList<T> linkedList){
		StringBuilder aSB = new StringBuilder();
		aSB.append(inTitle + ":");
		for (Entry e : linkedList){
			aSB.append(" " + e.getName());
		}
		aSB.append(SEPARATOR);
		return aSB.toString();
	}
	
	public static boolean writeFile(EntryList<Entry> inEntries, RepConable inRepconable) {
		Object data = inRepconable.convert(inEntries);
		String fileLocation = FL3.OUTPUTSOURCE + inRepconable.getFileEnding();
		boolean isWritten = false;
		try {
			if(data instanceof String){
				layer2.FL2.writeFile(fileLocation, data.toString());				
			} else {
				layer2.FL2.writeFile(fileLocation, data);	
			}
			isWritten = true;
		} catch (Exception e) {
			FL3.log(e);
		}
		return isWritten;
	}
	
	public static boolean deleteFile(String inFileLocation) {
		boolean isDeleted = false;
		try {
			layer2.FL2.deleteFile(inFileLocation);
			isDeleted = true;
		} catch (Exception e) {
			FL3.log(e);
		}
		return isDeleted;
	}

	public static boolean isWritable() {
		boolean isWritten = true;
		try {
			FL2.writeFile(FL3.TESTWRITABLE, FL3.TESTWRITABLE);
		} catch (Exception e) {
			FL3.log(e);
		}
		return isWritten; 
	}

	public static boolean isDeletable() {
		return FL3.deleteFile(FL3.TESTWRITABLE);
	}

	public static boolean isOnline(){
		boolean isOnline = false;
		try {
			String aString = FL2.readHTTPRequest(new URL(FL3.TESTONLINE));
			if (aString != null){
				isOnline = true;
			}
		} catch (Exception e) {
			FL3.log(e);
		}
		return isOnline;
	}
}