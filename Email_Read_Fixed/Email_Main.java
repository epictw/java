package Email_Read;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by epic on 28.04.14.
 */
public class Email_Main {

    public static void main(String [] args){


        HashMap<String, ArrayList<String>> hm = Email_R.readSerializeFile("e:\\cont.dat");
        if (hm == null){
            return;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
           String a = br.readLine();
            System.out.println("Enter your value: ");
            ArrayList<String> hk = hm.get(a);
            System.out.println(hk);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
