package org.sicnu.root.controller;

import org.sicnu.root.model.Goods;
import org.sicnu.root.repo.GoodsRepo;
import org.sicnu.root.util.AliyunOssHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
public class GoodsController {
    @Autowired
    private GoodsRepo goodsRepo;

    @CrossOrigin
    @PostMapping(value = "/upload_image",produces = "application/json; charset=utf-8")
    public Map<String ,Object> uploadImage(@RequestParam(value = "imageFile",required = true) MultipartFile imageFile){
        String imageUrl = AliyunOssHelper.uploadImage(imageFile);

        Map<String ,Object> result = new HashMap<String,Object>();
        result.put("status", "ok");
        result.put("imageUrl",imageUrl);
        return result;
    }

    @CrossOrigin
    @GetMapping(value = "/goods",produces = "application/json; charset=utf-8")
    public List<Goods> getAllGoods(){
        List<Goods> result = goodsRepo.getGoodsAll();
        return result;
    }

    @CrossOrigin
    @GetMapping(value = "/show_goods",produces = "application/json; charset=utf-8")
    public List<Goods> getProducts(@RequestParam(value = "typename",required = false) String typename){
        List<Goods> result = goodsRepo.getGoodsByTypename(typename);
        return result;
    }

    @CrossOrigin
    @PostMapping(value = "/select_goods_by_id",produces = "application/json; charset=utf-8")
    public Map<String,Object> selectGoodsId(@RequestParam(value ="id",required = true) Integer id) {
        Goods goods = goodsRepo.getById(id);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("title",goods.getTitle());
        result.put("typename",goods.getTypename());
        result.put("img",goods.getImg());
        result.put("remark",goods.getRemark());
        result.put("price",goods.getPrice());
        result.put("number",goods.getNumber());
        result.put("checked",goods.getChecked());
        result.put("goodsid",goods.getGoodsid());
        return result;
    }

    @CrossOrigin
    @PostMapping(value = "/add_goods",produces = "application/json; charset=utf-8")
    public Map<String,Object> addGoods(@RequestParam(value = "title",required = true) String title, @RequestParam(value = "typename",required = true) String typename,@RequestParam(value = "img",required = true) String img,@RequestParam(value = "remark",required = true) String remark,@RequestParam(value = "price",required = true) String price,@RequestParam(value = "number",required = true) Integer number,@RequestParam(value ="checked",required = true) Integer checked,@RequestParam(value ="goodsid",required = true) String goodsid) {
        Goods goods = new Goods();
        goods.setTitle(title);
        goods.setTypename(typename);
        goods.setImg(img);
        goods.setRemark(remark);
        goods.setPrice(price);
        goods.setNumber(number);
        goods.setChecked(checked);
        goods.setGoodsid(goodsid);
        goodsRepo.save(goods);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", "ok");
        result.put("goodsId",goods.getGoodsid());
        return result;
    }

    @CrossOrigin
    @PostMapping(value = "/edit_goods",produces = "application/json; charset=utf-8")
    public Map<String,Object> editGoods(@RequestParam(value = "title",required = true) String title, @RequestParam(value = "typename",required = true) String typename,@RequestParam(value = "img",required = true) String img,@RequestParam(value = "remark",required = true) String remark,@RequestParam(value = "price",required = true) String price,@RequestParam(value = "number",required = true) Integer number,@RequestParam(value ="checked",required = true) Integer checked,@RequestParam(value ="goodsid",required = true) String goodsid,@RequestParam(value ="id",required = true) Integer id) {
        Goods goods = goodsRepo.getById(id);
        goods.setTitle(title);
        goods.setTypename(typename);
        goods.setImg(img);
        goods.setRemark(remark);
        goods.setPrice(price);
        goods.setNumber(number);
        goods.setChecked(checked);
        goods.setGoodsid(goodsid);
        goodsRepo.save(goods);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", "ok");
        result.put("goodsId",goods.getGoodsid());
        return result;
    }

    @CrossOrigin
    @PostMapping(value = "/delete_goods")
    public Map<String,Object> deleteGoods(@RequestParam(value = "id",required = true) Integer id) {
        goodsRepo.deleteGoodsById(id);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", "ok");
        return result;
    }
}
