// File: Registration.java
class ContactInfo implements Cloneable {
    String email, phone;
    public ContactInfo(String email, String phone) {
        this.email = email; this.phone = phone;
    }
    public ContactInfo clone() throws CloneNotSupportedException {
        return (ContactInfo) super.clone(); // shallow clone
    }
}

class Student implements Cloneable {
    String id, name;
    ContactInfo contact;

    public Student(String id, String name, ContactInfo contact) {
        this.id = id; this.name = name; this.contact = contact;
    }

    public Student shallowClone() throws CloneNotSupportedException {
        return (Student) super.clone();
    }

    public Student deepClone() throws CloneNotSupportedException {
        Student copy = (Student) super.clone();
        copy.contact = contact.clone(); // deep copy
        return copy;
    }

    public String toString() {
        return id + " - " + name + " [" + contact.email + ", " + contact.phone + "]";
    }
}

public class p4 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Student s1 = new Student("S01", "Anita", new ContactInfo("a@gmail.com", "99999"));
        Student s2 = s1.shallowClone();
        Student s3 = s1.deepClone();

        s1.contact.email = "changed@gmail.com";
        System.out.println("Original: " + s1);
        System.out.println("Shallow: " + s2);
        System.out.println("Deep: " + s3);
    }
}
