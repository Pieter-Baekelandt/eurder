package be.switchfully.eurder.security.users;

import be.switchfully.eurder.security.Feature;

import java.util.Optional;

public class User {
    private final String username;
    private final String password;
    private final Role role;
    private final String id;

    public User(String username, String password, Role role, String id) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Optional<String> getId() {
        return Optional.ofNullable(id);
    }

    public boolean canHaveAccessTo(Feature feature) {
        return role.containsFeature(feature);
    }

    public boolean doesPasswordMatch(String password) {
        return this.password.equals(password);
    }
}
