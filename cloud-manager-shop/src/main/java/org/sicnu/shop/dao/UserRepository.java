package org.sicnu.shop.dao;


import org.sicnu.shop.model.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Userinfo, Integer> {

    @Query("from Userinfo u where u.phone = ?1")
    Userinfo findUserByPhone(String phone);

}
