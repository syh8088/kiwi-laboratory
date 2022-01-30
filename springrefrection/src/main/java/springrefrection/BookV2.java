package springrefrection;

public class BookV2 {

    public static String A = "A나옴";

    private String B = "B";

    public BookV2(String b) {
        B = b;
    }

    private void c() {
        System.out.println("C");
    }

    public int sum(int left, int right) {
        return left + right;
    }
}
