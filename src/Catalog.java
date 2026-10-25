import java.util.ArrayList;
import java.util.Arrays;

public class Catalog {
    public ArrayList<Resource> resources = new ArrayList<>();
    public ArrayList<Member> members = new ArrayList<>();
    public ArrayList<Loan> loans = new ArrayList<>();

    public Catalog() {

        //<editor-fold desc="Book Creation">
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
        //</editor-fold>

        //<editor-fold desc="Audiobook Creation">
        AudioBook harryPotterSeven = new AudioBook("Harry Potter and the Deathly Hallows, book 7",
                "JK. Rowling",
                "23 hrs and 59 mins",
                "978-0747591108");

        //</editor-fold>

        //<editor-fold desc="Game Creation">
        Game wowForever = new Game("World of Warcraft: Forever",
                "PC",
                "Blizzard Entertainment",
                "5030917289736");
        //</editor-fold>





        Member thorkild = new Member("Thorkild Hansen", 111);
        Member lise = new Member("Lise Andersen", 112);
        Member peter = new Member("Peter Larsen", 113);
        Member hans = new Member("Hans Christian", 114);
        Member sophie = new Member("Sophie Ofelia", 115);
        Member valdemar = new Member("Valdemar Jørgensen", 116);
        Member mathilde = new Member("Mathilde Asmussen", 117);
        Member ida = new Member("Ida Poppel", 118);

        resources.addAll(Arrays.asList(thinkJava, omrids, sapiens, atomicHabits, theIntelligentInvestor, open, theIliad, theOdyssey));
        members.addAll(Arrays.asList(thorkild, lise, peter, hans, sophie, valdemar, mathilde, ida));
        loans.addAll(Arrays.asList());  // Empty
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }
    public ArrayList<Member> getMembers() {
        return members;
    }
    public ArrayList<Loan> getLoans() {
        return loans;
    }
}
