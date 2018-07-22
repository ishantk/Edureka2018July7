package co.edureka.edurekajuly7;

public class Book {

    String price;
    String name;
    String author;

    public Book(){

    }

    public Book(String price, String name, String author) {
        this.price = price;
        this.name = name;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Price: "+price+"\nName: "+name+"\nAuthor: "+author;
    }
}
