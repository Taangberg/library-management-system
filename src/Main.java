void main(String[] args) {

    Library library = new Library();
    ConsoleUI consoleUI = new ConsoleUI(library);

    Book thinkJava = new Book("Think Java",
            "Allan B. Downey",
            280,
            "9781492072508");
    Book omrids = new Book("Omrids",
            "Rachel Cusk",
            256,
            "9788763851664");
    Book sapiens = new Book("Sapiens",
            "Yuval Noah Harari",
            464,
            "9780062316097");
    Book atomicHabits = new Book("Atomic Habits",
            "James Clear",
            320,
            "9781847941831");
    Book theIntelligentInvestor = new Book("The Intelligent Investor",
            "Benjamin Graham",
            640,
            "9780060555665");
    Book open = new Book("Open",
            "Andre Agassi",
            400,
            "9780007281435");
    Book theIliad = new Book("The Iliad",
            "Homer",
            560,
            "9780141394657");
    Book theOdyssey = new Book("The Odyssey",
            "Homer",
            416,
            "9780141192444");


    Member thorkild = new Member("Thorkild Hansen", 111);
    Member lise = new Member("Lise Andersen", 112);
    Member peter = new Member("Peter Larsen", 113);
    Member hans = new Member("Hans Christian", 114);
    Member sophie = new Member("Sophie Ofelia", 115);
    Member valdemar = new Member("Valdemar Jørgensen", 116);
    Member mathilde = new Member("Mathilde Asmussen", 117);
    Member ida = new Member("Ida Poppel", 118);

    library.addResources(thinkJava, omrids, sapiens, atomicHabits, theIntelligentInvestor, open, theIliad, theOdyssey);
    library.addMembers(thorkild, lise, peter, hans, sophie, valdemar, mathilde, ida);


    consoleUI.run();
}