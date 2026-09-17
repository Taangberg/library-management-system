void main() {

    Library library = new Library();
    ConsoleUI consoleUI = new ConsoleUI(library);


    Book thinkJava = new Book("Allan B. Downey",
            "Think Java",
            "9781492072508",
            1,
            123);
    Book omrids = new Book("Rachel Cusk",
            "Omrids",
            "9781492072508",
            2,
            1234);
    Book sapiens = new Book("Yuval Noah Harari",
            "Sapiens",
            "9780062316097",
            3,
            12345);

    Member thorkild = new Member("Thorkild Hansen",111);
    Member lise = new Member("Lise Andersen", 112);
    Member peter = new Member("Peter Larsen", 113);

    library.addBook(thinkJava);
    library.addBook(omrids);
    library.addBook(sapiens);

    library.addMember(thorkild);
    library.addMember(lise);
    library.addMember(peter);

    consoleUI.run();

/*
    ArrayList<Book>books = new ArrayList<>();
    books.add(new Book("Hp", "Dp", "12352315", 1));

    Book dP = new Book("Hp", "Dp", "12352315", 1);

    IO.println("ArrayList contains: " + books.contains(dP));
*/
}