package org.sicnu.root.repo;

import org.sicnu.root.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo  extends JpaRepository<User,Integer> {
    @Query(value = "select * from user where user_name=?1",nativeQuery = true)
    public List<User> findByUserName(String userName);
}
