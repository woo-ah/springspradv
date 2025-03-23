package com.thc.sprboot.util;

import com.thc.sprboot.domain.RefreshToken;
import com.thc.sprboot.mapper.NoticeMapper;
import com.thc.sprboot.repository.NoticeRepository;
import com.thc.sprboot.repository.RefreshTokenRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TokenFactory {

    String prefix = "Bearer/u0020"; //토큰의 접두사
    int refreshTokenExpiredTime = 3000; // RefreshToken 유효 시간
    int accessTokenExpiredTime = 30; //AccessToken 유효 시간

    private final RefreshTokenRepository refreshTokenRepository;
    public TokenFactory(RefreshTokenRepository refreshTokenRepository){
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String generateRefreshToken(Long userId){
        String tempToken = generateToken(userId, refreshTokenExpiredTime); //토큰 생성
        RefreshToken refreshToken = RefreshToken.of(userId, tempToken); //객체 만들고
        refreshTokenRepository.save(refreshToken); // DB에 저장

        return tempToken;
    }
    public Long verifyRefreshToken(String token){
        RefreshToken refreshToken = refreshTokenRepository.findByContent(token); // DB에서 조회
        if(refreshToken == null){
            throw new RuntimeException("no auth"); // 없으면 불법 토큰
        }
        String result = verifyToken(token); // 토큰 복호화해서 userId 꺼냄
        Long userId = refreshToken.getUserId();

        if(!result.equals(userId.toString())){ // 정보가 일치하지 않으면 불법 토큰
            throw new RuntimeException("no auth");
        }
        return userId;
    }
    public String revokeRefreshToken(Long userId){
        return null;
    }

    public Long verifyAccessToken(String token){
        String result = verifyToken(token); // 토큰 복호화
        if(result == null){
            throw new RuntimeException("no auth");
        }
        Long userId = Long.parseLong(result); // 사용자 ID 꺼냄
        return userId;
    }

    public String generateAccessToken(Long userId){
        return generateToken(userId, accessTokenExpiredTime);
    }

    //*-------------------------------*

    //토큰 생성
    public String generateToken(Long userId, int minute){
        String token = null;
        //토큰에 넣어야 하는 정보: userId랑 만료일시!!

        try{
            String dueDate = new NowDate().due(minute); // minute분 후 만료 시간 설정
            System.out.println(dueDate);

            String data = userId + "_" + dueDate; // "사용자ID_만료시간" 형태로 데이터 구성
            System.out.println("data : " + data);
            token = prefix + AES256Cipher.AES_Encode(null, data); // AES-256으로 암호화 후 prefix 추가
        } catch (Exception e){
        }
        return token;
    }

    //토큰 검증
    public String verifyToken(String token){
        String returnData = null;

        try{
            //System.out.println("1 token : " + token);
            token = token.substring(prefix.length()); // "Bearer/u0020" 제거
            //System.out.println("2 token : " + token);
            String data = AES256Cipher.AES_Decode(null, token); // 토큰 복호화

            String[] arrayData = data.split("_"); //"userId_만료시간" 형태로 저장되어 있기 때문에 언더스코어(_) 기준으로 데이터를 나눔
            if(arrayData.length == 2){
                Long userId = Long.parseLong(arrayData[0]);

                String dueDate = arrayData[1];
                String nowDate = new NowDate().getNow(); //현재시간

                String[] dates = {dueDate, nowDate};
                Arrays.sort(dates); //dueDate(토큰 만료 시간)과 nowDate(현재 시간)을 배열에 넣고 정렬 | 작은 값이 앞에 오게 됨

                if(nowDate.equals(dates[0])){ // 만료되지 않았다면
                    //ok!!
                    returnData = userId + ""; //아이디 반환
                }
            }
        } catch (Exception e){
        }
        return returnData;
    }

}
