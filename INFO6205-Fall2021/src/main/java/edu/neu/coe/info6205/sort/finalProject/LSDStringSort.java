package edu.neu.coe.info6205.sort.finalProject;

import java.util.ArrayList;
import java.util.Map;

public class LSDStringSort {

//    private final int ASCII_RANGE = 256;

    /**
     * findMaxLength method returns maximum length of all available strings in an array
     *
     * @param strArr It contains an array of String from which maximum length needs to be found
     * @return int Returns maximum length value
     */
    private static int findMaxLength(String[] strArr) {
        int maxLength = strArr[0].length();
        for (String str : strArr)
            maxLength = Math.max(maxLength, str.length());
        return maxLength;
    }

    /**
     * charAsciiVal method returns ASCII value of particular character in a String.
     *
     * @param str          String input for which ASCII Value need to be found
     * @param charPosition Character position of which ASCII value needs to be found. If character
     *                     doesn't exist then ASCII value of null i.e. 0 is returned
     * @return int Returns ASCII value
     */
    private static int charAsciiVal(String str, int charPosition) {
        if (charPosition >= str.length()) {
            return 0;
        }
        return str.charAt(charPosition);
    }

    /**
     * charSort method is implementation of LSD sort algorithm at particular character.
     *
     * @param strArr       It contains an array of String on which LSD char sort needs to be performed
     * @param charPosition This is the character position on which sort would be performed
     * @param from         This is the starting index from which sorting operation will begin
     * @param to           This is the ending index up until which sorting operation will be continued
     */
    private static void charSort(String[] strArr, int charPosition, int from, int to) {
        int ASCII_RANGE = 256;

        int[] count = new int[ASCII_RANGE + 2];
        String[] result = new String[strArr.length];
//        String[] resultName = new String[strArr.length];

        for (int i = from; i <= to; i++) {
            int c = charAsciiVal(strArr[i], charPosition);
            count[c + 2]++;
        }

        // transform counts to indices
        for (int r = 1; r < ASCII_RANGE + 2; r++)
            count[r] += count[r - 1];

        // distribute
        for (int i = from; i <= to; i++) {
            int c = charAsciiVal(strArr[i], charPosition);
            result[count[c + 1]++] = strArr[i];
//            resultName[count[c + 1]++] = names[i];
        }

        // copy back
        if (to + 1 - from >= 0) System.arraycopy(result, 0, strArr, from, to + 1 - from);
//        if (to + 1 - from >= 0) System.arraycopy(resultName, 0, names, from, to + 1 - from);
    }

    /**
     * sort method is implementation of LSD String sort algorithm.
     *
     * @param strArr It contains an array of String on which LSD sort needs to be performed
     * @param from   This is the starting index from which sorting operation will begin
     * @param to     This is the ending index up until which sorting operation will be continued
     */
    public static void sort(String[] strArr, int from, int to) {
        int maxLength = findMaxLength(strArr);
        for (int i = maxLength - 1; i >= 0; i--)
            charSort(strArr, i, from, to);
    }

    /**
     * sort method is implementation of LSD String sort algorithm.
     *
     * @param strArr It contains an array of String on which LSD sort needs to be performed
     */
    public static void sort(String[] strArr) {
        sort(strArr, 0, strArr.length - 1);
    }

    public void LSDStringSortTest(int n) {
        String filePath = "/Users/chenpeng/Documents/GitHub/INFO6205-Final-ZCL/shuffledChinese.txt";
        String[] ChineseWords = ReadTxt.readTxtFile(filePath,n);
        Pinyin PinYin = new Pinyin();
        String[] pinyin = PinYin.getPinYinWithTone(ChineseWords);

        LSDStringSort.sort(pinyin);

        Map<String, ArrayList> map = PinYin.getMap();
        String[] result = new String[pinyin.length];
        int index = 0;

        for(String pinyinword : pinyin){

            if(map.containsKey(pinyinword)){
                ArrayList<String> newList = map.get(pinyinword);

                for(String str: newList){
                    result[index++] = str;
                }

                map.remove(pinyinword);
            }

        }

//        for (int i = 0; i <= 100; i++) {
//            System.out.println(result[i]);
//            System.out.println(pinyin[i]);
//        }

        WriteTxt wt = new WriteTxt();
        wt.writeTxt("LSDStringSorted.txt", result, 1000);
    }

    public static void main(String[] args) {
        LSDStringSort test = new LSDStringSort();
        test.LSDStringSortTest(999998);
    }
}
