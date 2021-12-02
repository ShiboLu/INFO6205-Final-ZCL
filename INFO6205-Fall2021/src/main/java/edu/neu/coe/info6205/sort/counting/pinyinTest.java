package edu.neu.coe.info6205.sort.counting;

import net.sourceforge.pinyin4j.PinyinHelper;

public class pinyinTest {
    public static String getPinYinWithTone(String ChineseWord) {
        String result = null;

        if(null != ChineseWord && !"".equals(ChineseWord)) {
            char[] charArray = ChineseWord.toCharArray();
            StringBuffer stringBuffer = new StringBuffer();

            for (char ch : charArray) {
                // Chinese words to pinyin one by one, and return a String list for every word
                String[] stringArray = PinyinHelper.toHanyuPinyinStringArray(ch);

                if(null != stringArray) {
                    stringBuffer.append(stringArray[0]);
//                    stringBuffer.append(stringArray[0].replaceAll("\\d", ""));
                }
            }

            if(stringBuffer.length() > 0) {
                result = stringBuffer.toString();
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(pinyinTest.getPinYinWithTone("刘持平"));
    }
}
