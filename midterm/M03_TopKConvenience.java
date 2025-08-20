import java.util.*;

public class M03_TopKConvenience {
    static class Item{ String name; int qty; Item(String n,int q){name=n;qty=q;} }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(), K=sc.nextInt();
        PriorityQueue<Item> pq=new PriorityQueue<>((a,b)->{
            if(a.qty!=b.qty) return a.qty-b.qty; // qty 小者較差
            return b.name.compareTo(a.name);     // 名稱較大者較差（為了輸出排序）
        });
        for(int i=0;i<n;i++){
            String name=sc.next();
            int qty=sc.nextInt();
            if(pq.size()<K) pq.offer(new Item(name,qty));
            else{
                Item worst=pq.peek();
                if(qty>worst.qty || (qty==worst.qty && name.compareTo(worst.name)<0)){
                    pq.poll(); pq.offer(new Item(name,qty));
                }
            }
        }
        List<Item> ans=new ArrayList<>(pq);
        ans.sort((a,b)-> b.qty==a.qty? a.name.compareTo(b.name) : b.qty-a.qty);
        for(Item it: ans) System.out.println(it.name+" "+it.qty);
    }
}

/*
 * Time Complexity: O(n log K) + O(K log K)
 * 說明：維護大小 K 的最小堆，逐筆插入/替換 O(log K)；最後對 K 筆結果排序 O(K log K)。
 */
