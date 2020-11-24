package lab_4;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    private final String DATABASE_USER = System.getenv("MYSQL_USER");
    private final String DATABASE_PASSWORD = System.getenv("MYSQL_PASSWORD");

    private final String CONNECTION_STRING = System.getenv("DATABASE_URL");

    private Connection connection;

    private final String SELECT_ALL = "SELECT * FROM users_words;";
    private final String GET_BY_ID = "SELECT * FROM users_words WHERE id = ?;";
    private final String SAVE_ENTITY =
            "INSERT INTO users_words (username, email, favourite_word) VALUES (?, ?, ?);";
    private final String UPDATE_ENTITY =
            "UPDATE users_words SET username = ?, email = ?, favourite_word = ? WHERE id = ?;";
    private final String DELETE_ENTITY =
            "DELETE FROM users_words WHERE id = ?;";

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println(CONNECTION_STRING);
            System.out.println(DATABASE_USER);
            connection = DriverManager.getConnection(CONNECTION_STRING, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public ArrayList<DBEntity> getAllEntities() {
        ArrayList<DBEntity> entities = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
        
            ResultSet rs = st.executeQuery(SELECT_ALL);
            while (rs.next()) {
                entities.add(new DBEntity(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("favourite_word")
                ));
            }
            st.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return entities;
    }

    public DBEntity getEntityById(Integer id) {
        DBEntity entity = null;
        try {
            PreparedStatement st = connection.prepareStatement(GET_BY_ID);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                entity = new DBEntity(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("favourite_word")
                );
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return entity;
    }

    public DBEntity saveEntity(DBEntity entity) {
        DBEntity newEntity = null;
        try {
            PreparedStatement st = connection.prepareStatement(SAVE_ENTITY, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, entity.getUsername());
            st.setString(2, entity.getEmail());
            st.setString(3, entity.getFavouriteWord());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                newEntity = new DBEntity(rs.getInt(1), entity);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return newEntity;
    }

    public DBEntity updateEntity(DBEntity entity) {
        try {
            PreparedStatement st = connection.prepareStatement(UPDATE_ENTITY);

            st.setString(1, entity.getUsername());
            st.setString(2, entity.getEmail());
            st.setString(3, entity.getFavouriteWord());
            st.setInt(4, entity.getId());

            st.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return entity;
    }

    public void deleteEntity(DBEntity entity) {
        try {
            PreparedStatement st = connection.prepareStatement(DELETE_ENTITY);
            st.setInt(1, entity.getId());
            st.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
