package reflex.bean;

import java.io.Serializable;
import java.util.List;

public class Worker extends Person implements Serializable {

    private Integer level;
    private List<String> addressList;
    private String[] phoneArray;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<String> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<String> addressList) {
        this.addressList = addressList;
    }

    public String[] getPhoneArray() {
        return phoneArray;
    }

    public void setPhoneArray(String[] phoneArray) {
        this.phoneArray = phoneArray;
    }

}
