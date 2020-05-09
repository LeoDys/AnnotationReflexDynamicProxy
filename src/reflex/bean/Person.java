package reflex.bean;

import java.util.List;

public class Person {

    private String name;
    private int age;
    private float height;
    private boolean marryFlag;

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

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public boolean isMarryFlag() {
        return marryFlag;
    }

    public void setMarryFlag(boolean marryFlag) {
        this.marryFlag = marryFlag;
    }

    public static class Student extends Person{
        private String cartId;

        public String getCartId() {
            return cartId;
        }

        public void setCartId(String cartId) {
            this.cartId = cartId;
        }
    }

}
