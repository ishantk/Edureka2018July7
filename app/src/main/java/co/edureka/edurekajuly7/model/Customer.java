package co.edureka.edurekajuly7.model;

public class Customer {

    // Attributes
    public int id;
    public String name;
    public String phone;
    public String email;


    // Constructors
    public Customer(){

    }

    public Customer(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
