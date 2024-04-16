package jpashop.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jpashop.domain.Item;
import jpashop.repository.ItemRepository;

@Service
@Transactional
public class ItemService {

	@Autowired
	ItemRepository itemRepository;

	public void saveItem(Item item){
		itemRepository.save(item);
	}
	public List<Item> findItems(){
		return itemRepository.findAll();
	}

	public Optional<Item> findOne(Long itemId){
		return itemRepository.findById(itemId);
	}

}
