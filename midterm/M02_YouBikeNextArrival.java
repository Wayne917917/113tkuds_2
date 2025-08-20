import java.util.*;

public class M02_YouBikeNextArrival {
    static int toMin(String s) {
        String[] p = s.split(":");
        return Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
    }

    static String toHHMM(int m) {
        return String.format("%02d:%02d", m / 60, m % 60);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = toMin(sc.next());
        int q = toMin(sc.next());

        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] <= q)
                l = mid + 1;
            else
                r = mid;
        }
        if (l == n)
            System.out.println("No bike");
        else
            System.out.println(toHHMM(arr[l]));
    }
}
