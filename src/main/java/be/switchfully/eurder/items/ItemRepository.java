package be.switchfully.eurder.items;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class ItemRepository {
    private final ConcurrentHashMap<String, Item> items;

    public ItemRepository() {
        this.items = new ConcurrentHashMap<>();
    }

    public void save(Item savedItem) {
        items.put(savedItem.getId(), savedItem);
    }

    public boolean containsId(String id) {
        return items.containsKey(id);
    }

    public Collection<Item> getAllItems() {
        return items.values();
    }

    public Item getItemById(String id) {
        return items.get(id);
    }
    public void updateItem(String id, Item updatedItem) {
        items.replace(id, updatedItem);
    }
}
