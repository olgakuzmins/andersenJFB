package model;

public class User extends BasicEntity {

    public void printRole() {
        System.out.println("This User is " + this.getClass().getSimpleName() + ". ");
    }
}
