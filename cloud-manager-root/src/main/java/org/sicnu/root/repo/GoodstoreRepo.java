package org.sicnu.root.repo;

import org.sicnu.root.model.Goods;
import org.sicnu.root.model.Goodstore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface GoodstoreRepo extends JpaRepository<Goodstore,Integer> {
    @Query(value = "select * from goodstore ",nativeQuery = true)
    public ArrayList<Map<String,Object>>  getGoodstoreAllObject();

    @Query(value = "select * from goodstore ",nativeQuery = true)
    public List<Goodstore> getGoodstore();

    @Query(value = "select * from goodstore where name=?1",nativeQuery = true)
    public List<Goodstore> getGoodsByTypename(String name);
}
