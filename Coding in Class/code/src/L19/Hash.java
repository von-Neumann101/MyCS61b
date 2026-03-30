package L19;

public class Hash {
    public static void main(String[] args){
        String a = "冯-诺依曼101";
        String b = "\u0004\u0017\u000c\u0010\u0008\u001c\u0002";

        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(a.hashCode() == b.hashCode());
    }
}
