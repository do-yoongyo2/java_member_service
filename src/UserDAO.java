import java.util.HashMap;
import java.util.Map;

public class UserDAO { // => Repository
    Map<String, User> db = new HashMap<>();

    public User find(String id) {
        return db.get(id);
    }

    public boolean save(User user){
        if (db.containsKey(user.getId())) {
            return false;
        }

        db.put(user.getId(), user);
        return true;
    }

    public void update(User user) {
        db.replace(user.getId(), user);
    }

    public void delete(String id) {
        db.remove(id);
    }
}
