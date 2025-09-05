public class lt_38_count_and_say {
    public static String countAndSay(int n){
        String s="1";
        for(int t=1;t<n;t++){
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<s.length();){
                int j=i; while(j<s.length() && s.charAt(j)==s.charAt(i)) j++;
                sb.append(j-i).append(s.charAt(i)); i=j;
            }
            s=sb.toString();
        }
        return s;
    }
    public static void main(String[] args) {
        System.out.println(countAndSay(4)); // 1211
    }
}
