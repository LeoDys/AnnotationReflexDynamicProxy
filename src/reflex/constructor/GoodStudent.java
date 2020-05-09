package reflex.constructor;

public class GoodStudent extends Student {

    private int level;

    public GoodStudent() {
        System.out.println("调用GoodStudent()构造方法");
    }

    private GoodStudent(int level) {
        System.out.println("调用GoodStudent(int level)构造方法");
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
