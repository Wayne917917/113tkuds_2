import java.util.*;

public class M05_GCD_LCM_Recursive {
    static long gcd(long a,long b){ return b==0? Math.abs(a): gcd(b, a%b); }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        long a=sc.nextLong(), b=sc.nextLong();
        long g=gcd(a,b);
        long l=(a/g)*b; // 先除後乘避免溢位
        System.out.println("GCD: "+g);
        System.out.println("LCM: "+Math.abs(l));
    }
}
/*
 * Time Complexity: O(log min(a,b))
 * 說明：歐幾里得演算法每步取餘數縮小問題，迭代次數為對數級。
 */
