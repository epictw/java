package Email_Read;

import Email_Gen.Container.Container;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by epic on 28.04.14.
 */
public class Email_R {

    public static HashMap <String, ArrayList<String>> readSerializeFile(String fileName){

        Container c = null;
        try{
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream oin = new ObjectInputStream(fis);
        c = (Container) oin.readObject();
    } catch (Exception e) {
        System.out.println("Some exception: " + e);
    }

    if (c == null) {
        return null;
    } else {
        return c.hm;
    }

    }


}
