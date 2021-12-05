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

                for (int i = 0; (lineTxt = bufferedReader.readLine()) != null && i < n; i++)
//                while((lineTxt = bufferedReader.readLine()) != null)
                {
                    names[i] = lineTxt;
//                    System.out.println(lineTxt);
                }

                read.close();
//                System.out.println(names.length);

            }else{
                System.out.println("Cannot find the file");
            }

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }

//        names = extentList(names, n);
//        if (n == 999998 * 2) {
//            for (int i = 0; i < 999998; i++) {
//                names[999998 + i] = names[i];
//            }
//        }

        for (int j = 1; j < (n / 999998 / 2 + 1); j++) {
            for (int i = 0; i < 999998 * j; i++) {
                names[999998 * j + i] = names[i];
            }
        }

        return names;
    }

//    public static String[] extentList(String[] list, int n) {
//        if (list.length <= n) {
//            return list;
//        }
//
//        for (int i = 0; i < list.length && list.length + i < n; i++) {
//            System.out.println(list.length);
//            list[list.length + i] = list[i];
//        }
//
//        list = extentList(list, n - list.length);
//
//        return list;
//    }

    public static void main(String[] args){
        String filePath = "/Users/chenpeng/Documents/GitHub/INFO6205-Final-ZCL/shuffledChinese.txt";
        String[] res = readTxtFile(filePath, 999998 * 4);

        System.out.println(res.length);
        System.out.println(res[0]);
        System.out.println(res[res.length - 1]);

//        for (int i = 0; i <= 24; i++) {
//            System.out.println(res[i]);
//        }
    }
}
