import java.util.*;

public class RecursiveTreePreview {

    static class Folder {
        String name;
        List<Folder> subfolders = new ArrayList<>();
        int files;

        Folder(String name, int files) {
            this.name = name;
            this.files = files;
        }

        void addSubfolder(Folder f) {
            subfolders.add(f);
        }
    }

    public static int countFiles(Folder folder) {
        int total = folder.files;
        for (Folder sub : folder.subfolders) {
            total += countFiles(sub);
        }
        return total;
    }

    public static void printMenu(String[] menu, int index, String indent) {
        if (index >= menu.length) return;
        System.out.println(indent + menu[index]);
        printMenu(menu, index + 1, indent + "  ");
    }

    public static List<Object> flatten(List<?> nested) {
        List<Object> result = new ArrayList<>();
        for (Object item : nested) {
            if (item instanceof List)
                result.addAll(flatten((List<?>) item));
            else
                result.add(item);
        }
        return result;
    }

    public static int maxDepth(Object obj) {
        if (!(obj instanceof List)) return 0;
        int depth = 0;
        for (Object item : (List<?>) obj)
            depth = Math.max(depth, maxDepth(item));
        return depth + 1;
    }

    public static void main(String[] args) {
        Folder root = new Folder("root", 2);
        Folder sub1 = new Folder("docs", 3);
        Folder sub2 = new Folder("images", 1);
        sub1.addSubfolder(new Folder("pdf", 5));
        root.addSubfolder(sub1);
        root.addSubfolder(sub2);
        System.out.println("總檔案數：" + countFiles(root));

        System.out.println("\n多層選單結構：");
        printMenu(new String[]{"首頁", "關於", "服務", "聯絡"}, 0, "");

        List<Object> nested = Arrays.asList(1, Arrays.asList(2, 3), Arrays.asList(Arrays.asList(4, 5)));
        System.out.println("\n展平後：" + flatten(nested));
        System.out.println("最大深度：" + maxDepth(nested));
    }
}
