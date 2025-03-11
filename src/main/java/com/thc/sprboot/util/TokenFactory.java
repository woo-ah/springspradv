package com.thc.sprboot.util;

import java.util.Arrays;

public class TokenFactory {

    String prefix = "Bearer/u0020";

    public String generateToken(Long userId){
        String token = null;
        //토큰에 넣어야 하는 정보!!
        // userId랑 만료일시!!
        try{
            String dueDate = new NowDate().due(60);
            System.out.println(dueDate);

            String data = userId + "_" + dueDate;
            System.out.println("data : " + data);
            token = prefix + AES256Cipher.AES_Encode(null, data);
        } catch (Exception e){
        }
        return token;
    }
    public String verifyToken(String token){
        String returnData = null;

        try{
            //System.out.println("1 token : " + token);
            token = token.substring(prefix.length());
            //System.out.println("2 token : " + token);
            String data = AES256Cipher.AES_Decode(null, token);

            String[] arrayData = data.split("_");
            if(arrayData.length == 2){
                Long userId = Long.parseLong(arrayData[0]);

                String dueDate = arrayData[1];
                String nowDate = new NowDate().getNow();

                String[] dates = {dueDate, nowDate};
                Arrays.sort(dates);

                if(nowDate.equals(dates[0])){
                    //ok!!
                    returnData = userId + "";
                }
            }
        } catch (Exception e){
        }
        return returnData;
    }

}
