package Email_Gen;


import Email_Gen.Container.Container;

import java.util.List;


import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by epic on 27.04.14.
 */
public class Email_Main {

    public static void main(String[] args){

        List<String> str = Email_Reader.getListFromFile("e:\\source.txt");

        try {
            FileOutputStream fos = new FileOutputStream("e:\\cont.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Container c = HashMap_Generator.generate(str);
            oos.writeObject(c);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            System.out.println("Some exception occured: " + e);
        }
    }
    }

