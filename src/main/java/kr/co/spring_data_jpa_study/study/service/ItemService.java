package kr.co.spring_data_jpa_study.study.service;

import kr.co.spring_data_jpa_study.study.entity.item.Item;
import kr.co.spring_data_jpa_study.study.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    public final ItemRepository itemRepository;

    @Transactional  // 상단에 @Transactional(readOnly = true) 을 선언하였으나 메소드에 직접 선언한 @Transactional를 우선시 하기 때문에 readonly가 적용되지 않는다.
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public Item findOneById(Long itemId) {
        return itemRepository.findOneById(itemId);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }
}
