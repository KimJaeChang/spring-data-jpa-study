package kr.co.spring_data_jpa_study.study.repository;

import kr.co.spring_data_jpa_study.study.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;

}
