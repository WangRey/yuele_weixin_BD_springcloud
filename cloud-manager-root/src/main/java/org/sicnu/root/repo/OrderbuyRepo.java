package org.sicnu.root.repo;

import org.sicnu.root.model.Goods;
import org.sicnu.root.model.Orderbuy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderbuyRepo extends JpaRepository<Orderbuy,Integer> {
    @Query(value = "select * from orderbuy where userid=?1",nativeQuery = true)
    public List<Orderbuy> getOrderbuyByuserid(String userid);
}
