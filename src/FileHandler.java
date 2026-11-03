import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileHandler {
    private final CSVMapper csvMapper;
    private final Path bookPath;
    private final Path audioBookPath;
    private final Path moviePath;
    private final Path gamePath;
    private final Path memberPath;
    private final Path loanPath;

    public FileHandler(Path dataDir, CSVMapper csvMapper) {
        this.csvMapper = csvMapper;
        this.bookPath = dataDir.resolve("books.csv");
        this.audioBookPath = dataDir.resolve("audiobooks.csv");
        this.moviePath = dataDir.resolve("movies.csv");
        this.gamePath = dataDir.resolve("games.csv");
        this.memberPath = dataDir.resolve("members.csv");
        this.loanPath = dataDir.resolve("loans.csv");
    }
    // ___________Resources___________
    public void saveResources(List<Resource> resources) throws IOException {
        List<Book> books = new ArrayList<>();
        List<AudioBook> audioBooks = new ArrayList<>();
        List<Movie> movies = new ArrayList<>();
        List<Game> games = new ArrayList<>();

        for (Resource resource : resources) {
            if (resource instanceof Book book) {
                books.add(book);
            } else if (resource instanceof AudioBook audioBook) {
                audioBooks.add(audioBook);
            } else if (resource instanceof Movie movie) {
                movies.add(movie);
            } else if (resource instanceof Game game){
                games.add(game);
            }
        }
        saveBooks(books);
        saveAudioBooks(audioBooks);
        saveMovies(movies);
        saveGames(games);
    }

    // ___________Member___________
    public void saveMembers(List<Member> members) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(memberPath)) {
            for (Member member : members) {
                writer.write(csvMapper.memberToCSV(member));
                writer.newLine();
            }
        }
    }

    public List<Member> loadMembers() throws IOException {
        List<Member> members = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(memberPath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                members.add(csvMapper.memberFromCSV(line));
            }
        }
        return members;
    }

    // ___________Book___________
    public void saveBooks(List<Book> books) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(bookPath)) {
            for (Book book : books) {
                writer.write(csvMapper.bookToCSV(book));
                writer.newLine();
            }
        }
    }

    public List<Book> loadBooks() throws IOException {
        List<Book> books = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(bookPath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                books.add(csvMapper.bookFromCSV(line));
            }
        }
        return books;
    }

    // ___________AudioBook___________
    public void saveAudioBooks(List<AudioBook> audioBooks) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(audioBookPath)) {
            for (AudioBook audioBook : audioBooks) {
                writer.write(csvMapper.audioBookToCSV(audioBook));
                writer.newLine();
            }
        }
    }

    public List<AudioBook> loadAudioBooks() throws IOException {
        List<AudioBook> audioBooks = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(audioBookPath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                audioBooks.add(csvMapper.audioBookFromCSV(line));
            }
        }
        return audioBooks;
    }

    // ___________Movie___________
    public void saveMovies(List<Movie> movies) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(moviePath)) {
            for (Movie movie : movies) {
                writer.write(csvMapper.movieToCSV(movie));
                writer.newLine();
            }
        }
    }

    public List<Movie> loadMovies() throws IOException {
        List<Movie> movies = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(moviePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                movies.add(csvMapper.movieFromCSV(line));
            }
        }
        return movies;
    }

    // ___________Game___________
    public void saveGames(List<Game> games) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(gamePath)) {
            for (Game game : games) {
                writer.write(csvMapper.gameToCSV(game));
                writer.newLine();
            }
        }
    }

    public List<Game> loadGames() throws IOException {
        List<Game> games = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(gamePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                games.add(csvMapper.gameFromCSV(line));
            }
        }
        return games;
    }

    // ___________Loan___________
    public void saveLoans(List<Loan> loans) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(loanPath)) {
            for (Loan loan : loans) {
                writer.write(csvMapper.loanToCSV(loan));
                writer.newLine();
            }
        }
    }

    public List<Loan> loadLoans(List<Member> members, List<Resource> resources) throws IOException {
        List<Loan> loans = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(loanPath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                loans.add(csvMapper.loanFromCSV(line, members, resources));
            }
        }
        return loans;
    }

}