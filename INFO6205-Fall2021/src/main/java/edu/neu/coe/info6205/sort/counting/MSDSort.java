package edu.neu.coe.info6205.sort.counting;

public class MSDSort {
    private static int R=256;
    private static String[] aux;
    private static String[] auxName;

    public static int charAt(String s,int d){
        if(d<s.length()){
            return s.charAt(d);
        }else{
            return -1;
        }
    }

    public static void sort(String[] a, String[] names){
        int N = a.length;
        aux = new String[N];
        auxName=new String[N];
        sort(names, a,0,N-1,0);
    }

    public static void sort(String[] names, String[] a,int lo,int hi,int d){
        if(lo>=hi){
            return;
        }
        int[] count=new int[R+2];
        for(int i=lo;i<=hi;i++){
            count[charAt(a[i],d)+2]++;
        }
        for(int r=0;r<R+1;r++){
            count[r+1]+=count[r];
        }
        for(int i=lo;i<=hi;i++){
            aux[count[charAt(a[i],d)+1]]=a[i];
            auxName[count[charAt(a[i],d)+1]++]=names[i];
        }
        for(int i=lo;i<=hi;i++){
            a[i]=aux[i - lo];
            names[i]=auxName[i - lo];
        }
        for(int r=0;r<R;r++){
            sort(names, a,lo+count[r],lo+count[r+1]-1,d+1);
        }
    }

    public static void main(String[] args) {
        String filePath = "/Users/chenpeng/Documents/GitHub/INFO6205-Final-ZCL/shuffledChinese.txt";
        String[] ChineseWords = ReadTxt.readTxtFile(filePath,999998);
        String[] pinyin = Pinyin.getPinYinWithTone(ChineseWords);

        MSDSort.sort(pinyin, ChineseWords);

        for (int i = 0; i <= 100; i++) {
            System.out.println(ChineseWords[i]);
            System.out.println(pinyin[i]);
        }
    }
}
