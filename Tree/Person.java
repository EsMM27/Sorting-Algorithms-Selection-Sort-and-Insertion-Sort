//package Tree;

public class Person implements Comparable<Person> {
    
    private String firstName;
    private String lastName;
    private int age = 0;

    @Override
    public int compareTo(Person other) {
        return this.lastName.compareTo(other.lastName);
    }

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + age + ")";
    }



}
