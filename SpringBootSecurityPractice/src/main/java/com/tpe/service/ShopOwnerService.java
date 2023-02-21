package com.tpe.service;

import com.tpe.domain.ShopOwner;
import com.tpe.dto.request.ShopOwnerRequest;
import com.tpe.repository.ShopOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopOwnerService {

    @Autowired
    private ShopOwnerRepository shopOwnerRepository;

    @Autowired
    private UserService userService;
    public void saveShopOwner(ShopOwnerRequest shopOwnerRequest) {
        ShopOwner shopOwner =
                new ShopOwner(
                        shopOwnerRequest.getShopName()
                        , shopOwnerRequest.getLastName()
                        , shopOwnerRequest.getName()
                        , shopOwnerRequest.getPhone()
                        , shopOwnerRequest.getEmail());
        shopOwner.setUser(userService.findUserByName(shopOwnerRequest.getUserName()));
        shopOwnerRepository.save(shopOwner);
    }
}
