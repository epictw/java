package Email_Gen;

import Email_Gen.Container.Container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by epic on 27.04.14.
 */
public class HashMap_Generator {

    public HashMap_Generator() {

    }

    public static Container generate(List<String> str) {

        if (str == null) {
            return null;
        }


        Container c = new Container();
        c.hm = new HashMap<String, ArrayList<String>>();
        for (String s : str) {
            String[] parts = s.split("@");
            String part1 = parts[0];
            String part2 = parts[1];

            List<String> myList1 = new ArrayList<String>(Arrays.asList(part2));

            c.hm.put(part1, (ArrayList<String>) myList1);
            System.out.println(part1 + " : " + myList1);

        }
        return c;
    }
}





