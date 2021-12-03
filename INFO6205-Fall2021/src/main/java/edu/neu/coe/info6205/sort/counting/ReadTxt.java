package edu.neu.coe.info6205.sort.counting;

import java.io.*;
import java.util.ArrayList;

public class ReadTxt {
//    public String filePath = "/Users/chenpeng/Documents/GitHub/INFO6205-Final-ZCL/shuffledChinese.txt";

    public static String[] readTxtFile(String filePath, int n){
//        ArrayList<String> names = new ArrayList<String>();
        String[] names = new String[n];

        try {
            File file = new File(filePath);

            if(file.isFile() && file.exists()){ //if the file exist
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                for (int i = 0; (lineTxt = bufferedReader.readLine()) != null; i++)
//                while((lineTxt = bufferedReader.readLine()) != null)
                {
                    names[i] = lineTxt;
//                    System.out.println(lineTxt);
                }

                read.close();
                System.out.println(names.length);

            }else{
                System.out.println("Cannot find the file");
            }

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }

        return names;
    }

//    public static void main(String[] args){
//        String filePath = "/Users/chenpeng/Documents/GitHub/INFO6205-Final-ZCL/shuffledChinese.txt";
//        String[] res = readTxtFile(filePath, 1000000);
//
//        for (int i = 0; i <= 100; i++) {
//            System.out.println(res[i]);
//        }
//    }
}
