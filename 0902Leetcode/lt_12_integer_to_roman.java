
public class lt_12_integer_to_roman {

    public static String intToRoman(int num) {
        int[] vals = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] syms = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vals.length; i++) {
            while (num >= vals[i]) {
                sb.append(syms[i]);
                num -= vals[i];
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(3));    // III
        System.out.println(intToRoman(58));   // LVIII
        System.out.println(intToRoman(1994)); // MCMXCIV
    }
}

/*
[Explanation]
貪心：從大到小（含減法記號 CM,CD,XC,XL,IX,IV）一直扣到 0。
羅馬數字的構造保證此策略正確。
Complexity：Time O(1)（常數次迭代），Space O(1)。
*/
