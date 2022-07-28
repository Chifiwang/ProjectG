import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Bson {
    static String cache;

    /** 
     * updates the bson structure text file
     * 
     * @param map provides the map class that needs to be updated
     * @param newMap provides the updated class structure
     */

    static void rewrite(String map, String newMap) {
        int saveLength = 8;
        File file = new File("Saves\\output.txt");
        String line = "";
        String __fileCache__ = "";
        boolean overWrite = false;

        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            
            while ((line = bufferedReader.readLine()) != null) { // checks to see if a line is present

                overWrite = line.equals(map + " {");

                if (overWrite) {

                    __fileCache__ += newMap;

                    for(int i = 0; i < saveLength; i++)
                        bufferedReader.readLine();

                } else { __fileCache__ += line + "\n"; }
            } 

            FileWriter writer = new FileWriter(file);
            writer.write(__fileCache__);

            bufferedReader.close();
            writer.close();

        } catch (IOException e) {
            System.err.println("File Not Found");
        }
    }

    
    /** 
     * Returns the bson txt file class structure with the given id
     * 
     * @param id provides the id of the bson struct class
     * @return String returns the bson struct class
     */
    static String getClass(String id) {
        int saveLength = 9;
        File file = new File("Saves\\output.txt");
        String line = "";
        String __fileCache__ = "";
        boolean overWrite = false;

        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null) { // checks to see if a line is present

                overWrite = line.equals("Map" + id + " {");

                if (overWrite) {
                    for(int i = 0; i < saveLength; i++) {
                        __fileCache__ += line + "\n";
                        line = bufferedReader.readLine();
                    }
                    break;
                }
            } 

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("File Not Found");
        }

        return __fileCache__;
    }
    
    /** 
     * Checks and provides which levels are unlocked by the player
     * 
     * @return int returns the last unlocked level
     */
    static int getUnlocked() {
    	int unlocked = -1;
    	File file = new File("Saves\\output.txt");
        String line = "";

        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null) { // checks to see if a line is present
            	if (line.indexOf("firstClear") != -1) unlocked++;
            	if (line.indexOf("true") != -1) break;
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("File Not Found");
        }
        
    	return unlocked;
    }
}

