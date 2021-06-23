package org.sicnu.root.controller;

import org.sicnu.root.model.Goods;
import org.sicnu.root.model.Orderbuy;
import org.sicnu.root.repo.OrderbuyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
public class OrderbuyController {
    @Autowired
    private OrderbuyRepo orderbuyRepo;

    @CrossOrigin
    @PostMapping(value = "/add_order",produces = "application/json; charset=utf-8")
    public Map<String,Object> addOrder(@RequestParam(value = "userid",required = true) String userid, @RequestParam(value = "price",required = true) String price, @RequestParam(value = "number",required = true) Integer number, @RequestParam(value ="goodsid",required = true) String goodsid,@RequestParam(value ="title",required = true) String title,@RequestParam(value ="img",required = true) String img) {
        Orderbuy order = new Orderbuy();
        order.setNumber(number);
        order.setGoodsid(goodsid);
        order.setPrice(price);
        order.setTitle(title);
        order.setUserid(userid);
        order.setImg(img);
        orderbuyRepo.save(order);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", "ok");
        return result;
    }



    @CrossOrigin
    @PostMapping(value = "/find_order",produces = "application/json; charset=utf-8")
    public List<Orderbuy> findOrder(@RequestParam(value = "userid",required = true) String userid) {
        List<Orderbuy> result = orderbuyRepo.getOrderbuyByuserid(userid);
        return result;
    }




}
