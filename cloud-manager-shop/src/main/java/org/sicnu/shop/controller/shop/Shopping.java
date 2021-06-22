package org.sicnu.shop.controller.shop;


import com.auth0.jwt.JWT;
import org.sicnu.shop.model.AjaxResponse;
import org.sicnu.shop.model.CommunitySquare;
import org.sicnu.shop.service.ShoppingService;
import org.sicnu.shop.service.imp.ShoppingServiceImp;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/shopping")
public class Shopping {

    @Resource
    ShoppingServiceImp shoppingServiceImp;

    @PostMapping("/buging")
    @ResponseBody
    public AjaxResponse buging(@RequestBody  Map<String ,String > mp ,HttpServletRequest request ) {
        String token = request.getHeader("token");
        String  phone = JWT.decode(token).getAudience().get(0);
        String amount = mp.get("amount");
        shoppingServiceImp.buy(amount,phone);
        return AjaxResponse.success();
    }
}
