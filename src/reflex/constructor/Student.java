package reflex.constructor;

public class Student {
    private String name;
    private int age;

    public Student(){
        System.out.println("调用Student()构造方法");
    }

    public Student(String name, int age){
        System.out.println("调用Student(String name,int age)构造方法");
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
