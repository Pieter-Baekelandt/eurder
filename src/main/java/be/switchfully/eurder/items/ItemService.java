package be.switchfully.eurder.items;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public void addItem(Item item) throws IllegalArgumentException {
        if (itemRepository.containsId(item.getId())) {
            throw new IllegalArgumentException("Item with ID " + item.getId() + " already exists.");
        }
        else {
            itemRepository.save(item);
        }
    }

    public Collection<ItemDTO> getAllItems() {
        return itemMapper.mapToDTO(itemRepository.getAllItems());
    }
    public void updateItem(String id, Item updatedItem) {
        Optional<Item> optionalItem = Optional.ofNullable(itemRepository.getItemById(id));
    if (optionalItem.isEmpty()) {
        throw new NotFoundException(String.format("No item with id %s found", id));
            }
        itemRepository.updateItem(id, updatedItem);
    }
}
