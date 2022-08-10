import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

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

    public static void main(String[] args) {
        // System.out.println(getClass("1", "Saves\\Levels.txt"));
        for (int i = 0; i < 2; i++) {
            System.out.println(getAllClassNames("Saves\\Levels.txt")[i]);
        }
    }
    
    public static String[] writeContent(String id, String name, Boolean firstClear, int starsAchived, int col, int row, String map, int[] starBounds, int blockCount) {
        String[] content = TEMPLATE_CLASS;
        String temp;

        content[0] += id + ";\n";
        content[1] += name + ";\n";
        content[2] += (firstClear) ? "true" : "false" + ";\n";
        content[3] += Integer.toString(starsAchived) + ";\n";
        content[4] += Integer.toString(col) + ";\n";
        content[5] += Integer.toString(row) + ";\n";
        content[6] += map + ";\n";
        content[7] += (temp = Arrays.toString(starBounds)).substring(1, temp.length() - 1) + ";\n";
        content[8] += Integer.toString(blockCount) + ";\n";

        // for(String a : content) {
        //     System.out.println(a);
        // }
        return content;
    }

    public static void writeClass(String id, String[] content, String filePath) {
        File file = new File(filePath);

        String line;
        String __FileContentCache__ = "";

        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            
            while ((line = bufferedReader.readLine()) != null) {
                
                if (id != null && line.contains(id) && line.contains("Map")) {
                    
                    for (String lines : content) {
                        __FileContentCache__ += lines;
                        line = bufferedReader.readLine();
                    }

                }

                if (line != null) __FileContentCache__ += line + "\n";
                
            } 
            if (id == null) {
                
                for (int i = 0; i < CLASS_LENGTH; i++) {
                    __FileContentCache__ += content[i];
                }

            }
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

                if (id != null && line.contains(id) && line.contains("Map")) {

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

        return __FileContentCache__;
    }

    public static String[] extractClassInfo(String classCompressed) {
        String[] classInfo = new String[CLASS_LENGTH];
        String[] temp = classCompressed.split(";\n");

        for(int i = 0; i < CLASS_LENGTH; i++) {
            // System.out.println(temp[i] + ", " + CLASS_INFO_POINTERS[i]);
            classInfo[i] = temp[i].substring(CLASS_INFO_POINTERS[i]);
        }

        return classInfo;
    }

    public static String[] getAllClassNames(String filePath) {

        String[] names = new String[numClasses(filePath)];

        for (int i = 0; i < numClasses(filePath); i++) {
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
                
            	if (line.indexOf("firstClear") != -1) unlocked++;
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
