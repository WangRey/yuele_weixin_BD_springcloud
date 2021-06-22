package org.sicnu.shop.dao;


import org.sicnu.shop.model.CommunitySquare;
import org.sicnu.shop.model.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommunityRepository extends JpaRepository<CommunitySquare, Integer> {

}
