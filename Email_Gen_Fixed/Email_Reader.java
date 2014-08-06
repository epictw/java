package Email_Gen;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

/**
 * Created by epic on 27.04.14.
 */
public class Email_Reader {

    public Email_Reader() {
    }

    public static List<String> getListFromFile(String fileName) {

        List<String> str = new ArrayList<String>();

        File file = new File(fileName);

        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {

                //str.add(line);
		String[] splited = str.split(",");
		
		for (int i = 0; i < splited.length; i++) {
			str.add(splited[i].trim());
		} 
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Some exception occured: " + e);
        }
        return str;
    }

}
