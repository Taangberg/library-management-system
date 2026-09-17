void main() {

    Library library = new Library();
    ConsoleUI consoleUI = new ConsoleUI(library);


    Book thinkJava = new Book("Allan B. Downey",
            "Think Java",
            "9781492072508",
            1,
            280);
    Book omrids = new Book("Rachel Cusk",
            "Omrids",
            "9788763851664",
            2,
            256);
    Book sapiens = new Book("Yuval Noah Harari",
            "Sapiens",
            "9780062316097",
            3,
            464);
    Book atomicHabits = new Book("James Clear",
            "Atomic Habits",
            "9781847941831",
            4,
            320);
    Book theIntelligentInvestor = new Book("Benjamin Graham",
            "The Intelligent Investor",
            "9780060555665",
            5,
            640);
    Book open = new Book("Andre Agassi",
            "Open",
            "9780007281435",
            6,
            400);
    Book theIliad = new Book("Homer",
            "The Iliad",
            "9780141394657",
            7,
            560);
    Book theOdyssey =new Book("Homer",
            "The Odyssey",
            "9780141192444",
            8,
            416);


    Member thorkild = new Member("Thorkild Hansen",111);
    Member lise = new Member("Lise Andersen", 112);
    Member peter = new Member("Peter Larsen", 113);
    Member hans = new Member("Hans Christian", 114);
    Member sophie = new Member("Sophie Ofelia", 115);
    Member valdemar = new Member("Valdemar Jørgensen", 116);
    Member mathilde = new Member("Mathilde Asmussen", 117);
    Member ida = new Member("Ida Poppel", 118);

    library.addBook(thinkJava);
    library.addBook(omrids);
    library.addBook(sapiens);
    library.addBook(atomicHabits);
    library.addBook(theIntelligentInvestor);
    library.addBook(open);
    library.addBook(theIliad);
    library.addBook(theOdyssey);

    library.addMember(thorkild);
    library.addMember(lise);
    library.addMember(peter);
    library.addMember(hans);
    library.addMember(sophie);
    library.addMember(valdemar);
    library.addMember(mathilde);
    library.addMember(ida);

    consoleUI.run();

/*
    ArrayList<Book>books = new ArrayList<>();
    books.add(new Book("Hp", "Dp", "12352315", 1));

    Book dP = new Book("Hp", "Dp", "12352315", 1);

    IO.println("ArrayList contains: " + books.contains(dP));
*/
}