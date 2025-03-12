package com.thc.sprboot.util;

import com.thc.sprboot.domain.RefreshToken;
import com.thc.sprboot.mapper.NoticeMapper;
import com.thc.sprboot.repository.NoticeRepository;
import com.thc.sprboot.repository.RefreshTokenRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TokenFactory {

    String prefix = "Bearer/u0020";
    int refreshTokenExpiredTime = 3000;
    int accessTokenExpiredTime = 30;

    private final RefreshTokenRepository refreshTokenRepository;
    public TokenFactory(RefreshTokenRepository refreshTokenRepository){
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String generateRefreshToken(Long userId){
        String tempToken = generateToken(userId, refreshTokenExpiredTime);
        RefreshToken refreshToken = RefreshToken.of(userId, tempToken);
        refreshTokenRepository.save(refreshToken);

        return tempToken;
    }
    public Long verifyRefreshToken(String token){
        RefreshToken refreshToken = refreshTokenRepository.findByContent(token);
        if(refreshToken == null){
            throw new RuntimeException("no auth");
        }
        String result = verifyToken(token);
        Long userId = refreshToken.getUserId();

        if(!result.equals(userId.toString())){
            throw new RuntimeException("no auth");
        }
        return userId;
    }
    public String revokeRefreshToken(Long userId){
        return null;
    }

    public Long verifyAccessToken(String token){
        String result = verifyToken(token);
        if(result == null){
            throw new RuntimeException("no auth");
        }
        Long userId = Long.parseLong(result);
        return userId;
    }

    public String generateAccessToken(Long userId){
        return generateToken(userId, accessTokenExpiredTime);
    }

    public String generateToken(Long userId, int minute){
        String token = null;
        //토큰에 넣어야 하는 정보!!
        // userId랑 만료일시!!
        try{
            String dueDate = new NowDate().due(minute);
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
