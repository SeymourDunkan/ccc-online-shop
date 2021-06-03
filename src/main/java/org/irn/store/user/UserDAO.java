package org.irn.store.user;

import java.util.List;

import org.irn.store.admin.OrderListFilterParams;
import org.irn.store.admin.UserListParams;

public interface UserDAO {
    User create(User user);
    User update(User user);
    void delete(User user);
    User getByEmail(String email);
    User getById(Integer id);
    List<User> getAll();
	User checkRegisteredUser(String email, String password);
	void updateBlocked(Integer userId, String blocked);
	List<User> getUsers(String paginationSql, UserListParams params);
	Integer getTotalRecords();
}
