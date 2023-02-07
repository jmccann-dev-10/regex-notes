package learn.regex.model;

public class Person {
    
    public String firstName;
    public String lastName;
    
    protected int age;
    
    public String getHairColor;
    protected boolean isPrivate;

    private String getFirstName() {
        return firstName;
    }

    protected void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    protected String getLastName() {
        return lastName;
    }

    protected void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    protected String getGetHairColor() {
        return getHairColor;
    }

    protected void setGetHairColor(String getHairColor) {
        this.getHairColor = getHairColor;
    }

    protected boolean isPrivate() {
        return isPrivate;
    }

    private void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
