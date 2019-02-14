/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.controller.CardValid_Service;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author HUY
 */
@Named(value = "cardVaildMB")
@SessionScoped
public class cardVaildMB implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/TuanHuyCardServer_14/CardValid.wsdl")
    private CardValid_Service service;

    /**
     * Creates a new instance of cardVaildMB
     */
    private String card;
    private String message;

    public cardVaildMB(String card, String message) {
        this.card = card;
        this.message = message;
    }

    public cardVaildMB() {
    }

    private Boolean checkCardValidation(java.lang.String card) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        com.controller.CardValid port = service.getCardValidPort();
        return port.checkCardValidation(card);
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void validCard() {
        Boolean b = this.checkCardValidation(card);
        if (b) {
            message = "Valid Card";
        } else {
            message = "InVaild";
        }
    }
}
