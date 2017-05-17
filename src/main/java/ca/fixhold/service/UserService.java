package ca.fixhold.service;

import ca.fixhold.model.User;

public interface UserService {
    void save(User user);

    User findByEmail(String email);
}
