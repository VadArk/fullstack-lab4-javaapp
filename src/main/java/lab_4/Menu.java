package lab_4;

import java.util.ArrayList;
import java.util.Scanner;

enum MENU_ACTION {
    GET_ENTITIES,
    SAVE_ENTITY,
    UPDATE_ENTITY,
    DELETE_ENTITY,
    EXIT
}

public class Menu {

    private final Database db;
    private final Scanner input;

    public Menu(Database db) {
        this.db = db;
        input = new Scanner(System.in);
    }

    public void start() {
        String id;
        DBEntity entity;
        boolean toExit = false;
        while (!toExit) {
            MENU_ACTION action = menu();
            ArrayList<DBEntity> list = db.getAllEntities();
            switch (action) {
                case GET_ENTITIES:
                    System.out.println("\nAll entities:");
                    printAllEntities(list);
                    break;
                case SAVE_ENTITY:
                    System.out.println("\nSave entity activity:");
                    entity = saveEntity();
                    System.out.println("Saved!\nEntity:");
                    entity.print();
                    break;
                case UPDATE_ENTITY:
                    System.out.println("\nUpdate entity activity:");
                    System.out.print("Entity id: ");
                    id = input.nextLine();
                    entity = db.getEntityById(Integer.parseInt(id));
                    entity = updateEntity(entity);
                    System.out.println("Updated!\nEntity:");
                    entity.print();
                    break;
                case DELETE_ENTITY:
                    System.out.println("\nDelete entity activity:");
                    System.out.print("Entity id: ");
                    id = input.nextLine();
                    entity = db.getEntityById(Integer.parseInt(id));
                    deleteEntity(entity);
                    System.out.println("Deleted!");
                    break;
                case EXIT:
                    toExit = true;
                    break;
                default:
                    System.out.println("\nWrong choice, buddy.");
            }
        }
        input.close();
    }

    private MENU_ACTION menu() {
        System.out.println("\nChoose menu item:");
        System.out.println("1. Get all entities");
        System.out.println("2. Save entity");
        System.out.println("3. Update entity");
        System.out.println("4. Delete entity");
        System.out.println("5. Exit");
        System.out.print(">>>  ");
        String item = input.nextLine();
        return MENU_ACTION.values()[Integer.parseInt(item) - 1];
    }

    private void printAllEntities(ArrayList<DBEntity> list) {
        for (DBEntity item: list) {
            item.print();
        }
    }

    private DBEntity saveEntity() {
        System.out.print("Username: ");
        String username = input.nextLine();
        System.out.print("Email: ");
        String email = input.nextLine();
        System.out.print("Favourite word: ");
        String favouriteWord = input.nextLine();
        DBEntity entity = new DBEntity(username, email, favouriteWord);
        return db.saveEntity(entity);
    }

    private DBEntity updateEntity(DBEntity entity) {
        System.out.print("Username ("+entity.getUsername()+"): ");
        String username = input.nextLine();
        System.out.print("Email ("+entity.getEmail()+"): ");
        String email = input.nextLine();
        System.out.print("Favourite word ("+entity.getFavouriteWord()+"): ");
        String favouriteWord = input.nextLine();
        username = username.trim().isEmpty() ? entity.getUsername() : username.trim();
        email = email.trim().isEmpty() ? entity.getEmail() : email.trim();
        favouriteWord = favouriteWord.trim().isEmpty() ? entity.getFavouriteWord() : favouriteWord.trim();
        entity.setUsername(username);
        entity.setEmail(email);
        entity.setFavouriteWord(favouriteWord);
        return db.updateEntity(entity);
    }

    private void deleteEntity(DBEntity entity) {
        db.deleteEntity(entity);
    }

}
