package org.sicnu.shop.service.imp;

import org.sicnu.shop.dao.CommunityRepository;
import org.sicnu.shop.dao.UserRepository;
import org.sicnu.shop.model.CommunitySquare;
import org.sicnu.shop.model.Userinfo;
import org.sicnu.shop.service.CommunityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service

public class CommunityServiceImp implements CommunityService {

    @Resource
    CommunityRepository communityRepository;

    @Resource
    UserRepository userRepository;

    @Override
    public boolean saveCommunityService(String phone, CommunitySquare cs) {
        Userinfo u = userRepository.findUserByPhone(phone);
        cs.setUserId(u.getId());
        cs.setHeadImage(u.getHeadImage());
        cs.setNickName(u.getNickName());
        communityRepository.save(cs);
        return false;
    }

    @Override
    public List<CommunitySquare> getCommunityService() {
        return communityRepository.findAll();
    }
}
