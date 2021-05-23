package org.irn.store.user;

import java.util.List;

public interface UserDAO {
    User create(User user);
    User update(User user);
    void delete(User user);
    User getByEmail(String email);
    User getById(Integer id);
    void toggleStatus(Integer id, String action);
    List<User> getAll();
	User checkRegisteredUser(String email, String password);
}
