import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
//import legacy.Debug;

public class Bson {
    public static final int CLASS_LENGTH = 9;
    public static final int[] CLASS_INFO_POINTERS = {
        3, 10, 16, 18, 9, 9, 9, 16, 16
    };
    public static final String[] TEMPLATE_CLASS = {
        "Map",
        "   name : ",
        "   firstClear : ",
        "   starsAchived : ",
        "   col : ",
        "   row : ",
        "   map : ",
        "   starBounds : ",
        "   blockCount : "
    };
    
    public static String[] formatContent(String[] content) {

        for (int i = 0; i < content.length; i++) {
            content[i] = TEMPLATE_CLASS[i] + content[i] + ";\n";
        }

        return content;
    }

    public static void writeClass(String id, String[] content, String filePath) {
        File file = new File(filePath);

//        boolean isCustom = filePath.contains("CustomLevels.txt");
//        boolean isDel = false;
        String line;
        String __FileContentCache__ = "";
        int c = 0;

        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            
            while ((line = bufferedReader.readLine()) != null) {
            	
//            	System.out.println(id);
                
//                if (content == null && id != null && line.contains(id) && line.contains("Map")) {
//                    isDel = true;
//                    line = "";
//                    for (int i = 1; i < TEMPLATE_CLASS.length; i++) {
//                        bufferedReader.readLine();
//                    } 
//                /*} else*/ if (id != null && line.contains(id) && line.contains("Map")) {
            	if (line.contains(id)) {
                	
//                	System.out.println("here");
                    
                    for (int i = 0; i < CLASS_LENGTH; i++) {
                        __FileContentCache__ += content[i];
                        
                        if (i > 0) bufferedReader.readLine();
                    }
                    c++;
                }

//                if (line != null && line.contains("Map")) {
//                    
////                     if (content == null) {
//                        __FileContentCache__ += line.substring(0, CLASS_INFO_POINTERS[0]) + c + ";\n";
//                        line = bufferedReader.readLine();
////                     }
//                    c++;
//                } 

                /*if (line != null && !isDel )*/
                if (line != null && !line.contains(id)) __FileContentCache__ += line + "\n";
//                else isDel = false;
            } 

//            if (id == null) {
//                __FileContentCache__ += content[0].substring(0, CLASS_INFO_POINTERS[0]) + c + ";\n";
//                for (int i = 1; i < CLASS_LENGTH; i++) {
//                    __FileContentCache__ += content[i];
//                }
//
//            }
            FileWriter writer = new FileWriter(file);
            writer.write(__FileContentCache__);

            bufferedReader.close();
            writer.close();

        } catch (IOException e) {
            System.err.println("File Not Found");
        }
    }

    public static int numClasses(String filePath) {
        File file = new File(filePath);

        int c = 0;
        String line;

        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            
            while ((line = bufferedReader.readLine()) != null) {

                if (line.contains("Map"))
                    c++;
                
            } 

            bufferedReader.close();

        } catch (IOException e) {
            System.err.println("File Not Found");
        }

        return c;
    }

    public static String getClass(String id, String filePath) {
        File file = new File(filePath);

        String line;
        String __FileContentCache__ = "";

        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            
            while ((line = bufferedReader.readLine()) != null) {

                if (line.contains(id)) {

                    for (int i = 0; i < CLASS_LENGTH; i++) {
                        __FileContentCache__ += line + "\n";
                        line = bufferedReader.readLine();
                    }
                    break;

                }
            } 
            
            bufferedReader.close();


        } catch (IOException e) {
            System.err.println("File Not Found");
        }

        // Debug.print(("\""+__FileContentCache__)+"\"");
        return __FileContentCache__;
    }

    public static String[] extractClassInfo(String classCompressed) {
        String[] classInfo = new String[CLASS_LENGTH];
        String[] temp = classCompressed.split(";\n");

        for(int i = 0; i < CLASS_LENGTH; i++) {
            // Debug.print(temp[i] + ", " + CLASS_INFO_POINTERS[i]);
            classInfo[i] = temp[i].substring(CLASS_INFO_POINTERS[i]);
        }

        return classInfo;
    }

    public static String[] getAllClassNames(String filePath) {

        String[] names = new String[numClasses(filePath)];

        for (int i = 0; i < numClasses(filePath) - 1; i++) {
            names[i] = extractClassInfo(getClass(String.valueOf(i), filePath))[1];
        }

        return names;
    }

    /*  --------------------------------------------------------------------  */

    public static int getUnlocked() {
    	int unlocked = -1;
    	File file = new File("Saves\\Levels.txt");
        String line = "";

        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null) {
                
            	if (line.indexOf("firstClear") != -1) unlocked += (unlocked < numClasses("Saves\\Levels.txt") - 1) ? 1 : 0;
            	if (line.indexOf("true") != -1) break;
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("File Not Found");
        }
        
    	return unlocked;
    }

    /*  --------------------------------------------------------------------  */

    public static void updateMusic(int music) {
        File file = new File("Saves\\SoundSettings.txt");
        String line = "";
        String __fileCache__ = "";

        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            
            while ((line = bufferedReader.readLine()) != null) { // checks to see if a line is present

                if(line.contains("music")) {
                    line = line.substring(0, 12) + Integer.toString(music);
                }

                __fileCache__ += line + "\n";
            } 

            FileWriter writer = new FileWriter(file);
            writer.write(__fileCache__);

            bufferedReader.close();
            writer.close();

        } catch (IOException e) {
            System.err.println("File Not Found");
        }
    }

    public static void updateSFX(int sfx) {
        File file = new File("Saves\\SoundSettings.txt");
        String line = "";
        String __fileCache__ = "";

        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            
            while ((line = bufferedReader.readLine()) != null) { // checks to see if a line is present

                if(line.contains("sfx")) {
                    line = line.substring(0, 10) + Integer.toString(sfx);
                }
                __fileCache__ += line + "\n";
            } 

            FileWriter writer = new FileWriter(file);
            writer.write(__fileCache__);

            bufferedReader.close();
            writer.close();

        } catch (IOException e) {
            System.err.println("File Not Found");
        }
    }

    public static int getMusicVolume() {
        File file = new File("Saves\\SoundSettings.txt");
        String line = "";
        int musicLvl = 50;

        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            
            while ((line = bufferedReader.readLine()) != null) { // checks to see if a line is present

                line = bufferedReader.readLine();

                if (line.contains("music")) {
                    musicLvl = Integer.parseInt(line.substring(12));
                    break;
                }
            } 

            bufferedReader.close(); 

        } catch (IOException e) {
            System.err.println("File Not Found");
        }

        return musicLvl;
    }

    public static int getSFXVolume() {
        File file = new File("Saves\\SoundSettings.txt");
        String line = "";
        int musicLvl = 50;

        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            
            while ((line = bufferedReader.readLine()) != null) { // checks to see if a line is present

                if (line.contains("sfx")) {
                    musicLvl = Integer.parseInt(line.substring(10));
                    break;
                }
                line = bufferedReader.readLine();
            } 

            bufferedReader.close(); 

        } catch (IOException e) {
            System.err.println("File Not Found");
        }

        return musicLvl;
    }
}