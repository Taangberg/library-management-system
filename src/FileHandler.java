import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileHandler {
    private final CSVMapper csvMapper;
    private Path path;      // Unsure about path

    public FileHandler(Path path, CSVMapper csvMapper) {
        this.path = path;
        this.csvMapper = csvMapper;
    }

    // ___________Member___________
    public void saveMembers(List<Member> members) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Member member : members) {
                writer.write(csvMapper.memberToCSV(member));
                writer.newLine();
            }
        }
    }

    public List<Member> loadMembers() throws IOException {
        List<Member> members = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                members.add(csvMapper.memberFromCSV(line));
            }
        }
        return members;
    }

    // ___________Book___________
    public void saveBooks(List<Book> books) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Book book : books) {
                writer.write(csvMapper.bookToCSV(book));
                writer.newLine();
            }
        }
    }

    public List<Book> loadBooks() throws IOException {
        List<Book> books = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                books.add(csvMapper.bookFromCSV(line));
            }
        }
        return books;
    }

    // ___________AudioBook___________
    public void saveAudioBooks(List<AudioBook> audioBooks) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AudioBook audioBook : audioBooks) {
                writer.write(csvMapper.audioBookToCSV(audioBook));
                writer.newLine();
            }
        }
    }

    public List<AudioBook> loadAudioBooks() throws IOException {
        List<AudioBook> audioBooks = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                audioBooks.add(csvMapper.audioBookFromCSV(line));
            }
        }
        return audioBooks;
    }

    // ___________Movie___________
    public void saveMovies(List<Movie> movies) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Movie movie : movies) {
                writer.write(csvMapper.movieToCSV(movie));
                writer.newLine();
            }
        }
    }

    public List<Movie> loadMovies() throws IOException {
        List<Movie> movies = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                movies.add(csvMapper.movieFromCSV(line));
            }
        }
        return movies;
    }

    // ___________Game___________
    public void saveGames(List<Game> games) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Game game : games) {
                writer.write(csvMapper.gameToCSV(game));
                writer.newLine();
            }
        }
    }

    public List<Game> loadGames() throws IOException {
        List<Game> games = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                games.add(csvMapper.gameFromCSV(line));
            }
        }
        return games;
    }

    // ___________Loan___________
    public void saveLoans(List<Loan> loans) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Loan loan : loans) {
                writer.write(csvMapper.loanToCSV(loan));
                writer.newLine();
            }
        }
    }

    public List<Loan> loadLoans() throws IOException {
        List<Loan> loans = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                loans.add(csvMapper.loanFromCSV(line));
            }
        }
        return loans;
    }

}