package be.switchfully.eurder.security;

import be.switchfully.eurder.security.exceptions.UnauthorizedException;
import be.switchfully.eurder.security.exceptions.UnknownUserException;
import be.switchfully.eurder.security.exceptions.WrongPasswordException;
import be.switchfully.eurder.security.users.User;
import be.switchfully.eurder.security.users.UserRepository;
import jakarta.annotation.Nullable;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;
import java.util.Base64;
import java.util.Optional;
import static java.lang.String.format;

@ApplicationScoped
public class SecurityService {


    private final Logger logger = Logger.getLogger(SecurityService.class);

    private final UserRepository userRepository;

    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateAuthorization(@Nullable String authorization, Feature feature) {
        DecodedCredentials credentials = getUsernamePassword(Optional.ofNullable(authorization)
                .orElseThrow(UnauthorizedException::new));
        User user = userRepository.getUser(credentials.getUsername())
                .orElseThrow(() -> throwUserUnknownException(credentials.getUsername()));

        if (!user.doesPasswordMatch(credentials.getPassword())) {
            logger.errorf("Password does not match for user %s", credentials.getUsername());
            throw new WrongPasswordException();
        }

        if (!user.canHaveAccessTo(feature)) {
            logger.error(format("User %s does not have access to %s", credentials.getUsername(), feature));
            throw new UnauthorizedException();
        }
    }

    private UnknownUserException throwUserUnknownException(String username) {
        logger.errorf("Unknown user %s", username);
        return new UnknownUserException();
    }

    public DecodedCredentials getUsernamePassword(String authorization) {
        String decodedUsernameAndPassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String username = decodedUsernameAndPassword.substring(0, decodedUsernameAndPassword.indexOf(":")).toLowerCase();
        String password = decodedUsernameAndPassword.substring(decodedUsernameAndPassword.indexOf(":") + 1);
        return new DecodedCredentials(username, password);
    }
}
