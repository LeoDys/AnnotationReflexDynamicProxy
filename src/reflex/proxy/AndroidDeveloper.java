package reflex.proxy;

public class AndroidDeveloper implements Developer{

    private String name;

    public AndroidDeveloper(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void code() {
        System.out.println(name+"在编代码");
    }

    @Override
    public void debug() {
        System.out.println(name+"在反复调试，修改程序");
    }
}
