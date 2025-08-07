
public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};
        int sum = 0, max = scores[0], min = scores[0];
        int[] gradeCount = new int[5]; // A, B, C, D, F

        for (int score : scores) {
            sum += score;
            if (score > max) max = score;
            if (score < min) min = score;

            if (score >= 90) gradeCount[0]++;
            else if (score >= 80) gradeCount[1]++;
            else if (score >= 70) gradeCount[2]++;
            else if (score >= 60) gradeCount[3]++;
            else gradeCount[4]++;
        }

        double average = sum / (double) scores.length;
        int aboveAvgCount = 0;
        for (int score : scores) {
            if (score > average) aboveAvgCount++;
        }

        System.out.println("=== 成績統計報表 ===");
        System.out.printf("平均分數：%.2f\n", average);
        System.out.println("最高分數：" + max);
        System.out.println("最低分數：" + min);
        System.out.println("等第統計：");
        System.out.println("A (90↑): " + gradeCount[0]);
        System.out.println("B (80–89): " + gradeCount[1]);
        System.out.println("C (70–79): " + gradeCount[2]);
        System.out.println("D (60–69): " + gradeCount[3]);
        System.out.println("F (<60): " + gradeCount[4]);
        System.out.println("高於平均分人數：" + aboveAvgCount);
    }
}
