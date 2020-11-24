package lab_4;

// java -cp target/fullstack_4_mvn-1.0.jar:target/classes/dotenv-java-2.2.0.jar:target/classes/mysql-connector-java-8.0.22.jar lab_4.Main
public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        Menu menu = new Menu(db);
        menu.start();
        db.close();
    }
}
