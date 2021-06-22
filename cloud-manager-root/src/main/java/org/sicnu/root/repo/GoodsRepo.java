package org.sicnu.root.repo;

import org.sicnu.root.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface GoodsRepo extends JpaRepository<Goods,Integer> {
    @Query(value = "select * from goods ",nativeQuery = true)
    public List<Goods> getGoodsAll();

    @Query(value = "select * from goods ",nativeQuery = true)
    public ArrayList<Map<String,Object>> getGoodsAllObject();

    @Query(value = "select * from goods where typename=?1",nativeQuery = true)
    public List<Goods> getGoodsByTypename(String typename);

    @Query(value = "select * from goods where id=?1",nativeQuery = true)
    public Goods getById(Integer id);

    @Transactional()
    @Modifying
    @Query(value = "delete from goods where id = ?1",nativeQuery = true)
    public void deleteGoodsById(Integer id);
}
