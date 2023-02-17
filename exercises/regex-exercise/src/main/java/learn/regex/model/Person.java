package learn.regex.model;

public class Person {
    
    private String firstName;
    private String lastName;
    
    private int age;
    
    private String getHairColor;
    private boolean isPrivate;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGetHairColor() {
        return getHairColor;
    }

    public void setGetHairColor(String getHairColor) {
        this.getHairColor = getHairColor;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
