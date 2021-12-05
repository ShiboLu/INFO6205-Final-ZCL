package edu.neu.coe.info6205.sort.counting;

import edu.neu.coe.info6205.sort.counting.ReadTxt;
import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pinyin {

    private static Map<String, ArrayList> map = new HashMap<>();

    public static void setMap(Map<String, ArrayList> newmap) {
        map = newmap;
    }

    public static Map<String, ArrayList> getMap() {
        return map;
    }

    public static String[] getPinYinWithTone(String[] ChineseWords) {
        String[] result = new String[ChineseWords.length];
        int count = 0;

        for (String ChineseWord: ChineseWords) {
            if (null != ChineseWord && !"".equals(ChineseWord)) {
                char[] charArray = ChineseWord.toCharArray();
                StringBuffer stringBuffer = new StringBuffer();

                for (char ch : charArray) {
                    // Chinese words to pinyin one by one, and return a String list for every word
                    String[] stringArray = PinyinHelper.toHanyuPinyinStringArray(ch);

                    if (null != stringArray) {
                        stringBuffer.append(stringArray[0]);
//                        stringBuffer.append(stringArray[0].replaceAll("\\d", ""));
                    }
                }

                if (stringBuffer.length() > 0) {
                    result[count] = stringBuffer.toString();
                    Map<String, ArrayList> newMap = new HashMap<>();
                    newMap = getMap();
                    if(newMap.containsKey(result[count])) {
                        newMap.get(result[count]).add(ChineseWord);
                    } else{
                        ArrayList<String> newList = new ArrayList<>();
                        newList.add(ChineseWord);
                        newMap.put(result[count], newList);
                    }
                    setMap(newMap);
                }

            }

            count++;
        }

        return result;
    }

//    public static void main(String[] args) {
//        String filePath = "/Users/chenpeng/Documents/GitHub/INFO6205-Final-ZCL/shuffledChinese.txt";
//        String[] ChineseWords = ReadTxt.readTxtFile(filePath,1000000);
//        String[] pinyin = Pinyin.getPinYinWithTone(ChineseWords);
//
//        for (int i = 0; i <= 100; i++) {
//            System.out.println(ChineseWords[i]);
//            System.out.println(pinyin[i]);
//        }
//    }
}
