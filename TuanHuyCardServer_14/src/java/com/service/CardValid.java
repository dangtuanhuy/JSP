/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author HUY
 */
@WebService(serviceName = "CardValid")
public class CardValid {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "checkCardValidation")
    public Boolean checkCardValidation(@WebParam(name = "card") String card) {
     //TODO write your implementation code here:
        Boolean result = false;
        //Using regular expression card
        String regularEX
                = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|"
                + "(?<mastercard>5[1-5][0-9]{14})|"
                + "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|"
                + "(?<americanexpress>3[47][0-9]{13}))$";
        
        Pattern pa = Pattern.compile(regularEX);

        Matcher m = pa.matcher(card);

        System.out.println(m.matches());

        if (m.matches()) {
//If card is valid then verify which group it belong to
            System.out.println(m.group("mastercard"));
// Step 1: Starting from the second digit from the right and moving towards the left, multiply every digit by 2.
            String cardInfo = "";
            for (int i = card.length() - 1; i >= 0; i--) {
                if (i % 2 == 0) {
                    String str = String.valueOf((card.charAt(i) - '0') * 2);
                    cardInfo += str;
                }
            }
            
//Step 2: Sum the digits from Step 1.
            int sum2 = 0;
            for (int i = 0; i < cardInfo.length(); i++) {
                sum2 += (cardInfo.charAt(i) - '0');
            }
            System.out.println(sum2);
            
//Step 3: Sum all of the digits not originally mutliplied by 2.
            int sum3 = 0;
            for (int i = 0; i < card.length() - 1; i++) {
                if (i % 2 != 0) {
                    sum3 += card.charAt(i) - '0';
                }
            }
            System.out.println(sum3);
            
//Step 4: Sum together the results from Step 2 and Step 3.
            int sum4 = sum2 + sum3;
            System.out.println(sum4);
            if (sum4 % 10 == 0) {
                result = true;
            } else {
                int temp = sum4 - sum4 % 10 + 10;
                int totalValue = temp - sum4;
                System.out.println(totalValue);
                
//Step 5: Subtract the sum from the next highest multiple of 10.
                int lastCardInfo = (card.charAt(card.length() - 1) - '0');
                if (lastCardInfo == totalValue) {
                    result = true;
                } else {
                    result = false;
                }
            }
        }
        return result;
    }

}
