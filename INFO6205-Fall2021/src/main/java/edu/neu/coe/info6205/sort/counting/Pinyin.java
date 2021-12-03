package edu.neu.coe.info6205.sort.counting;

import edu.neu.coe.info6205.sort.counting.ReadTxt;
import net.sourceforge.pinyin4j.PinyinHelper;

public class Pinyin {
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
