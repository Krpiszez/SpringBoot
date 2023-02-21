package com.tpe.controller;

import com.tpe.dto.request.ShopOwnerRequest;
import com.tpe.service.ShopOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/shop-owner")
public class ShopOwnerController {

    @Autowired
    private ShopOwnerService shopOwnerService;

    @PostMapping
    public ResponseEntity<String> saveShopOwner(@Valid @RequestBody ShopOwnerRequest shopOwnerRequest){
        shopOwnerService.saveShopOwner(shopOwnerRequest);
        String message = "Shop owner: " + shopOwnerRequest.getUserName() + " has been registered!";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}
