package lab_4;

public class DBEntity {
    private Integer id;
    private String username;
    private String email;
    private String favouriteWord;

    public DBEntity(Integer id, String username, String email, String favouriteWord) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.favouriteWord = favouriteWord;
    }

    public DBEntity(String username, String email, String favouriteWord) {
        this.username = username;
        this.email = email;
        this.favouriteWord = favouriteWord;
    }

    public DBEntity(Integer id, DBEntity entity) {
        this.id = id;
        this.username = entity.username;
        this.email = entity.email;
        this.favouriteWord = entity.favouriteWord;
    }

    public void print() {
        System.out.println(
                id + ". " +
                "Name = " + username + "; " +
                "Email = " + email + "; " +
                "Favourite word = " + favouriteWord + "; "
        );
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFavouriteWord() {
        return favouriteWord;
    }

    public void setFavouriteWord(String favouriteWord) {
        this.favouriteWord = favouriteWord;
    }
}
