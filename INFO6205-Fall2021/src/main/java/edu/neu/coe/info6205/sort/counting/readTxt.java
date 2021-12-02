package edu.neu.coe.info6205.sort.counting;

import java.io.*;
import java.util.ArrayList;

public class readTxt {
    public static ArrayList readTxtFile(String filePath){
        ArrayList<String> names = new ArrayList<String>();

        try {
            File file=new File(filePath);

            if(file.isFile() && file.exists()){ //if the file exist
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while((lineTxt = bufferedReader.readLine()) != null){
                    names.add(lineTxt);
//                    System.out.println(lineTxt);
                }

                read.close();
                System.out.println(names.size());

            }else{
                System.out.println("Cannot find the file");
            }

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }

        return names;
    }

    public static void main(String[] args){
        String filePath = "/Users/chenpeng/Documents/GitHub/INFO6205-Final-ZCL/shuffledChinese.txt";
        ArrayList res = readTxtFile(filePath);
        System.out.println(res.get(0));
//        Collections.sort(res);
//        System.out.println(res.get(0));
    }
}
