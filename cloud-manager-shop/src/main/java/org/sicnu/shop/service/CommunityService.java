package org.sicnu.shop.service;

import org.sicnu.shop.model.CommunitySquare;

import java.util.List;

public interface CommunityService {
    boolean saveCommunityService(String phone, CommunitySquare cs);
    List<CommunitySquare> getCommunityService();
}
