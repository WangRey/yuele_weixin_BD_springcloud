package org.sicnu.shop.controller.index;

import com.auth0.jwt.JWT;
import org.sicnu.shop.model.AjaxResponse;
import org.sicnu.shop.model.CommunitySquare;
import org.sicnu.shop.service.CommunityService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


@RestController
@RequestMapping("/community")
public class Community {

    @Resource
    CommunityService communityService;

    @PostMapping("/Public")
    @ResponseBody
    public AjaxResponse CommunityPublic(@RequestBody CommunitySquare cs, HttpServletRequest request) {
        String token = request.getHeader("token");
        String  phone = JWT.decode(token).getAudience().get(0);
        communityService.saveCommunityService(phone,cs);
        return AjaxResponse.success();
    }

    @GetMapping("/Show")
    @ResponseBody
    public AjaxResponse CommunityShow(HttpServletRequest request) {
        ArrayList <CommunitySquare> hl = (ArrayList<CommunitySquare>) communityService.getCommunityService();
        return AjaxResponse.success(hl);
    }

}
