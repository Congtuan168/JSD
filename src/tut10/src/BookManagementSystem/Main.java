package tut10.src.BookManagementSystem;

public class Main {
    public static void main(String[] args) {
        BookManagementSystem view = new BookManagementSystem();
        BookDAO model = new BookDAO();
        new BookController(view, model);
        view.setVisible(true);
    }
}