package org.sicnu.root.controller;

import org.sicnu.root.model.Goods;
import org.sicnu.root.model.Goodstore;
import org.sicnu.root.repo.GoodsRepo;
import org.sicnu.root.repo.GoodstoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
public class GoodstoreController {
    @Autowired
    private GoodstoreRepo goodstoreRepo;
    @Autowired
    private GoodsRepo goodsRepo;

    @CrossOrigin
    @GetMapping(value = "/goodstore",produces = "application/json; charset=utf-8")
    public List<Goodstore> getGoodstores(){
        List<Goodstore> result = goodstoreRepo.getGoodstore();
        return result;
    }

    @CrossOrigin
    @GetMapping(value = "/show_goodstorecategory",produces = "application/json; charset=utf-8")
    public List<Goodstore> getGoodstorecategory(@RequestParam(value = "name",required = false) String name){
        List<Goodstore> result = goodstoreRepo.getGoodsByTypename(name);
        return result;
    }

    @CrossOrigin
    @PostMapping(value = "/add_goodstore",produces = "application/json; charset=utf-8")
    public Map<String,Object> addGoodstore(@RequestParam(value = "beginDate",required = true) String begin_date, @RequestParam(value = "checked",required = true) Integer checked, @RequestParam(value = "imgUrl",required = true) String img_url, @RequestParam(value = "name",required = true) String name, @RequestParam(value = "storecategory",required = true) String storecategory) {
        Goodstore goodstore = new Goodstore();
        goodstore.setBeginDate(begin_date);
        goodstore.setChecked(checked);
        goodstore.setImgUrl(img_url);
        goodstore.setName(name);
        goodstore.setStorecategory(storecategory);
        goodstoreRepo.save(goodstore);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", "ok");
        result.put("goodsId",goodstore.getId());
        return result;
    }


    @CrossOrigin
    @GetMapping(value = "/show_goodstore",produces = "application/json; charset=utf-8")
    public List<Map<String ,Object>> getGoodstoreAndGoods(){
        List<Map<String ,Object>> res = new ArrayList<Map<String, Object>>();
        List<Map<String ,Object>> restemp = new ArrayList<Map<String, Object>>();
        ArrayList<Map<String,Object>> goodstoreList =goodstoreRepo.getGoodstoreAllObject();
        ArrayList<Map<String,Object>> goodsList =goodsRepo.getGoodsAllObject();
        Map<String, Object> goodstoretemp = new HashMap<String, Object>();
        Map<String, Object> goodstemp = new HashMap<String, Object>();
        for(int i=0;i<goodstoreList.size();i++){
            goodstoretemp=new HashMap<String, Object>();
            Map<String,Object> gs = goodstoreList.get(i);
            Object storeName = gs.get("name");
            Object storeImg = gs.get("img_url");
            Object storeDate = gs.get("begin_date");
            Object storeChecked = gs.get("checked");
            Object storeCategory = gs.get("storecategory");
            for(int j=0;j<goodsList.size();j++){
                goodstemp= new HashMap<String, Object>();
                Map<String,Object> g = goodsList.get(j);
                Object title = g.get("title");
                Object img = g.get("img");
                Object typename = g.get("typename");
                Object checked = g.get("checked");
                Object price = g.get("price");
                Object remark = g.get("remark");
                Object goodsid = g.get("goodsid");
                Object number = g.get("number");
                if(storeName.equals(typename)){
                    goodstemp.put("title",title);
                    goodstemp.put("img",img);
                    goodstemp.put("typename",typename);
                    goodstemp.put("checked",checked);
                    goodstemp.put("price",price);
                    goodstemp.put("remark",remark);
                    goodstemp.put("goodsid",goodsid);
                    goodstemp.put("number",number);

                    restemp.add(goodstemp);
                }
            }
            goodstoretemp.put("name",storeName);
            goodstoretemp.put("beginDate",storeDate);
            goodstoretemp.put("imgUrl",storeImg);
            goodstoretemp.put("checked",storeChecked);
            goodstoretemp.put("storecategory",storeCategory);
            goodstoretemp.put("goods",restemp);

            res.add(goodstoretemp);
            restemp = new ArrayList<Map<String, Object>>();
        }

        return res;
    }






}
