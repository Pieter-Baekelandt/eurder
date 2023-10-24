package be.switchfully.eurder.items;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.stream.Collectors;

@ApplicationScoped
public class ItemMapper {
    public ItemDTO mapToDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setAmountInStock(item.getAmountInStock());
        return itemDTO;
    }
    public Collection<ItemDTO> mapToDTO(Collection<Item> items) {
        return items.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

}
