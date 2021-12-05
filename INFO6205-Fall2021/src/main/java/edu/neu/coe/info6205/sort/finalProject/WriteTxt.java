package edu.neu.coe.info6205.sort.finalProject;

import java.io.*;

public class WriteTxt {
    public void writeTxt(String fileName, String[] content, int n)  {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            for (int i = 0; i < n; i++) {
                out.write(content[i]);
                out.write("\n");
            }
            out.close();
        } catch (IOException e) {
        }
    }

//    public static void main(String[] args) {
//        String[] content = {"Hello", "World", "Java"};
//        WriteTxt wt = new WriteTxt();
//        wt.writeTxt("a.txt", content, 2);
//    }
}
