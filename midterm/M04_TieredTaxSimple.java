import java.util.*;

public class M04_TieredTaxSimple {
    static long tax(long x){
        long t=0;
        if(x>0) t += Math.min(x,120000)*5/100;
        if(x>120000) t += (Math.min(x,500000)-120000)*12/100;
        if(x>500000) t += (Math.min(x,1000000)-500000)*20/100;
        if(x>1000000) t += (x-1000000)*30/100;
        return t;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(); long sum=0;
        for(int i=0;i<n;i++){
            long x=sc.nextLong();
            long tt=tax(x);
            sum+=tt;
            System.out.println("Tax: "+tt);
        }
        System.out.println("Average: "+(sum/n));
    }
}

/*
 * Time Complexity: O(n)
 * 說明：每筆收入以固定段距常數時間計算，總計 O(n)。
 */
