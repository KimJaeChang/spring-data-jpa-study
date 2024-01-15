package kr.co.spring_data_jpa_study.study.service;

import jakarta.persistence.EntityManager;
import kr.co.spring_data_jpa_study.study.entity.Address;
import kr.co.spring_data_jpa_study.study.entity.Member;
import kr.co.spring_data_jpa_study.study.entity.Order;
import kr.co.spring_data_jpa_study.study.entity.item.Book;
import kr.co.spring_data_jpa_study.study.entity.item.Item;
import kr.co.spring_data_jpa_study.study.enums.OrderStatus;
import kr.co.spring_data_jpa_study.study.exception.NotEnoughStockException;
import kr.co.spring_data_jpa_study.study.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("상품 주문")
    @Transactional
    // @Rollback(value = false)
    void 상품주문() throws Exception {

        // given
        Member member = createMember();
        em.persist(member);

        Book book = createBook("시골 JPA", 10000, 10);
        em.persist(book);

        int orderCount = 2;

        /// when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        // then
        Order findOrder = orderRepository.findOneById(orderId);

        em.flush();

        // 상품 주문시 상태는 ORDER
        Assertions.assertThat(OrderStatus.ORDER).isEqualTo(findOrder.getStatus());

        // 주문한 상품 종류 수가 정확해야한다.
        Assertions.assertThat(1).isEqualTo(findOrder.getOrderItems().size());

        // 주문 가격은 가격 * 수량이다.
        Assertions.assertThat(10000 * orderCount).isEqualTo(findOrder.getTotalPrice());

        // 주문 수량만큼 재고가 줄어야 한다.
        Assertions.assertThat(8).isEqualTo(book.getStockQuantity());
    }

    @Test
    @DisplayName("주문취소")
    @Transactional
        // @Rollback(value = false)
    void 주문취소() throws Exception {

        //given
        Member member = createMember();
        em.persist(member);

        Book item = createBook("시골 JPA", 10000, 10);
        em.persist(item);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order findOrder = orderRepository.findOneById(orderId);

        em.flush();

        // 주문 취소시 상태는 CANCEL 이다.
        Assertions.assertThat(OrderStatus.CANCEL).isEqualTo(findOrder.getStatus());

        // 주문이 취소된 상품은 그만큼 재고가 증가해야 한다.
        Assertions.assertThat(10).isEqualTo(item.getStockQuantity());
    }

    @Test
    @DisplayName("상품주문_재고사수초과")
    @Transactional
        // @Rollback(value = false)
    void 상품주문_재고수량초과() throws Exception {

        // given
        Member member = createMember();
        em.persist(member);

        Item item = createBook("시골 JPA", 10000, 10);
        em.persist(item);

        int orderCount = 11;

        /// when

        // then
        assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(member.getId(), item.getId(), orderCount);
            System.out.println("재고 수량 부족 예외가 발생해야 한다."); // 예외가 발생해야 한다.
        });
    }

    private Book createBook(String name, int orderPrice, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(orderPrice);
        book.setStockQuantity(stockQuantity);
        return book;
    }

    private Member createMember() {
        Member member = new Member("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        return member;
    }
}