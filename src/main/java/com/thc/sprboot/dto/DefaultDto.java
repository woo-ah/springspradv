package com.thc.sprboot.dto;

import com.thc.sprboot.domain.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class DefaultDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder
    @Getter
    @Setter
    public static class UpdateReqDto{
        private Long id;
        private Boolean deleted;
        private String process;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class DetailReqDto{
        private Long id;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder
    @Getter
    @Setter
    public static class DetailResDto{
        private Long id;
        private String createdAt;
        private String modifiedAt;
        private Boolean deleted;
        private String process;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder
    @Getter
    @Setter
    public static class ListReqDto{
        private String orderby;
        private String orderway;

        private Boolean deleted;
        private String process;

        public void init(){
            //정렬 기준 입력 안했을때, 보완 코드
            if(getOrderby() == null || getOrderby().isEmpty()){
                setOrderby("id");
            }
            //정렬 기준 입력 안했을때, 보완 코드
            if(getOrderway() == null || getOrderway().isEmpty()){
                setOrderway("desc");
            }
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder
    @Getter
    @Setter
    public static class PagedListReqDto{
        private Integer offset;
        private Integer callpage;
        private Integer perpage;
        private String orderby;
        private String orderway;

        private Boolean deleted;
        private String process;

        public PagedListResDto init(int totalList){
            //정렬 기준 입력 안했을때, 보완 코드
            if(getOrderby() == null || getOrderby().isEmpty()){
                setOrderby("id");
            }
            //정렬 기준 입력 안했을때, 보완 코드
            if(getOrderway() == null || getOrderway().isEmpty()){
                setOrderway("desc");
            }

            //한 페이지에 몇개씩 볼지 확인할 것!!
            Integer perpage = getPerpage();
            if(perpage == null || perpage <= 0){
                perpage = 10;
            }
            setPerpage(perpage);

            //전체 페이지 갯수
            int totalPage = totalList / perpage;
            if(totalList % perpage > 0){
                totalPage++;
            }

            //몇번째 페이지 보고 싶은지
            Integer callpage = getCallpage();
            if(callpage == null || callpage <= 0){
                callpage = 1;
            } else if(callpage > totalPage){
                callpage = totalPage;
            }
            setCallpage(callpage);

            //몇번째 글부터 보여줄지
            int offset = (callpage - 1) * perpage;
            setOffset(offset);

            return DefaultDto.PagedListResDto.builder()
                    .totalList(totalList)
                    .totalPage(totalPage)
                    .callpage(callpage)
                    .perpage(perpage)
                    .build();
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder
    @Getter
    @Setter
    public static class PagedListResDto{
        private Object list;

        private Integer totalList;
        private Integer totalPage;
        private Integer callpage;
        private Integer perpage;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder
    @Getter
    @Setter
    public static class ScrollListReqDto{
        private Long cursor;

        private String orderway;
        private Integer perpage;

        private Boolean deleted;
        private String process;

        public void init(){
            //정렬 기준 입력 안했을때, 보완 코드
            if(getOrderway() == null || getOrderway().isEmpty()){
                setOrderway("desc");
            }
            //한 페이지에 몇개씩 볼지 확인할 것!!
            Integer perpage = getPerpage();
            if(perpage == null || perpage <= 0){
                perpage = 10;
            }
            setPerpage(perpage);
        }
    }

}
