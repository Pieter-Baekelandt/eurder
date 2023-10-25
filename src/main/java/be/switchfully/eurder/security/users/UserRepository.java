package be.switchfully.eurder.security.users;


import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class UserRepository {
    private final ConcurrentHashMap<String, User> users;
    private final Logger logger = Logger.getLogger(UserRepository.class);

    public UserRepository() {
        users = new ConcurrentHashMap<>();
        addAdmin();
    }

    private void addAdmin(){
        users.put("admin", new User("admin", "admin", Role.ADMIN, null));
    }

    public User addUser(User user) {
        users.put(user.getUsername(), user);
        return user;
    }
    public Optional<User> getUser(String username) {
        return Optional.ofNullable(users.get(username));
    }
    public String validateUser(String user) {
        if (user == null || user.isEmpty()) {
            logger.errorf("User not filled in");
            throw new IllegalArgumentException("User not filled in");
        }
        Optional<Map.Entry<String, User>> result =
                users.entrySet()
                        .stream()
                        .filter(e -> e.getKey().equals(user.toLowerCase()))
                        .findFirst();
        if (result.isPresent()) {
            logger.errorf("User " + user + " not unique");
            throw new IllegalArgumentException("User " + user + " not unique");
        }
        return user;
    }
}
