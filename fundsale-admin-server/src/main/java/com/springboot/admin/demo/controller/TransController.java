package com.springboot.admin.demo.controller;

import com.springboot.admin.demo.business.CustSignBusiness;
import com.springboot.admin.demo.business.OpenAccountBusiness;
import com.springboot.admin.demo.entity.OpenAccountRequest;
import com.springboot.admin.demo.entity.SignRequest;
import com.springboot.admin.demo.facade.TransService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trans")
public class TransController {

    @GetMapping("/doCustSign")
    public String doCustSign(SignRequest request) {
        TransService<SignRequest> transService = new CustSignBusiness();
        return transService.doProcess(request);
    }

    @GetMapping("/doOpenAccount")
    public String doOpenAccount(OpenAccountRequest request) {
        TransService<OpenAccountRequest> transService = new OpenAccountBusiness();
        return transService.doProcess(request);
    }

    public static void main(String[] args) {
        TransController transController = new TransController();

        SignRequest signRequest = new SignRequest();
        signRequest.setCertificateType("1");
        signRequest.setCertificateNo("430422...");
        signRequest.setAccountNo("19133...");
        transController.doCustSign(signRequest);

        OpenAccountRequest openAccountRequest = new OpenAccountRequest();
        openAccountRequest.setTano("70");
        openAccountRequest.setTransactionAccountNo("1234567890");
        transController.doOpenAccount(openAccountRequest);

    }
}
