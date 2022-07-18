import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Bson {
    static String cache;

    // static void writer() throws IOException {
    //     // Accept a string
    //     String str = "Map00 {\n\t\"Hallo\" : true\n    h";

    //     FileWriter fw = new FileWriter("output.txt");

    //     for (int i = 0; i < str.length(); i++)
    //         fw.write(str.charAt(i));
 
    //     System.out.println("Writing successful");

    //     //close the file
    //     fw.close();
    // }

    // static void readFile() throws IOException {

    //     try {
    //         FileReader input = new FileReader("output.txt");
    //         BufferedReader row = new BufferedReader(input);

    //         // if((cache = row.readLine().substring(1, 6)).equals("Hallo")) {
    //         //     Debug.print(true);
    //         // }

    //         Debug.print(cache);
    //         row.close();
    //     } catch (IOException e) {
    //         System.err.println("File Not Found");
    //     }
    // }

    static void rewrite(String map, String newMap) {
        int saveLength = 8;
        File file = new File("output.txt");
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

            // Debug.print(__fileCache__);

            FileWriter writer = new FileWriter(file);
            writer.write(__fileCache__);

            bufferedReader.close();
            writer.close();

        } catch (IOException e) {
            System.err.println("File Not Found");
        }
    }

    static String getClass(String id) {
        int saveLength = 9;
        File file = new File("output.txt");
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
            // Debug.print(__fileCache__.indexOf("\"starBounds\""));
            // Debug.print(__fileCache__.charAt(81));
            // Debug.print(__fileCache__.substring(__fileCache__.indexOf("map") + 8, __fileCache__.indexOf("starBounds") - 4).length());

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("File Not Found");
        }

        return __fileCache__;
    }
}

