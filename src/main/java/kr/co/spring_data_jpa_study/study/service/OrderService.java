package kr.co.spring_data_jpa_study.study.service;

import kr.co.spring_data_jpa_study.study.entity.Delivery;
import kr.co.spring_data_jpa_study.study.entity.Member;
import kr.co.spring_data_jpa_study.study.entity.Order;
import kr.co.spring_data_jpa_study.study.entity.OrderItem;
import kr.co.spring_data_jpa_study.study.entity.item.Item;
import kr.co.spring_data_jpa_study.study.repository.ItemRepository;
import kr.co.spring_data_jpa_study.study.repository.MemberRepository;
import kr.co.spring_data_jpa_study.study.repository.OrderRepository;
import kr.co.spring_data_jpa_study.study.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        
        // 엔티티 조회
        Member member = memberRepository.findOneById(memberId);
        Item item = itemRepository.findOneById(itemId);
        
        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        
        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        
        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);
        
        // 주문 저장
        orderRepository.save(order); // Order만 persist해줘도 Order 엔티티에 "orderItem`와 Delivery가 CascadeType.ALL 으로 설정되어있기 때문에 같이 persist 된다".

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findOneById(orderId);
        
        // 주문 취소
        order.cancel();
    }

    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByString(orderSearch);
    }
}
