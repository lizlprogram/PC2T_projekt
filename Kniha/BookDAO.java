package Kniha;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookDAO {
    private static final String INSERT_QUERY = "INSERT INTO books (titul, autor, datum_vydani, zanr, vypujcena) VALUES (?, ?, ?, ?, ?)";

    public void insertBook(Knihy book) throws SQLException {
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, book.getTitul());
            preparedStatement.setString(2, String.join(", ", book.getAutor()));
            preparedStatement.setInt(3, book.getdatumVydani());
            preparedStatement.setString(4, book.getZanr());
            preparedStatement.setInt(5, book.isVypujcena() ? 1 : 0);
            preparedStatement.executeUpdate();
        }
    }
}
