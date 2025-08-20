import java.util.*;

public class M02_YouBikeNextArrival {
    static int toMin(String s){ String[] p=s.split(":"); return Integer.parseInt(p[0])*60+Integer.parseInt(p[1]); }
    static String toHHMM(int m){ return String.format("%02d:%02d", m/60, m%60); }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] t=new int[n];
        for(int i=0;i<n;i++) t[i]=toMin(sc.next());
        int q=toMin(sc.next());
        int l=0,r=n; // first > q
        while(l<r){
            int m=(l+r)>>>1;
            if(t[m]<=q) l=m+1; else r=m;
        }
        System.out.println(l==n? "No bike" : toHHMM(t[l]));
    }
}
