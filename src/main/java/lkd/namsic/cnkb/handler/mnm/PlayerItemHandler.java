package lkd.namsic.cnkb.handler.mnm;

import lkd.namsic.cnkb.domain.game.item.Item;
import lkd.namsic.cnkb.domain.game.living.LivingItem;
import lkd.namsic.cnkb.domain.game.living.LivingItemUnique;
import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.exception.ObjectNotFoundException;
import lkd.namsic.cnkb.repository.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class PlayerItemHandler {

    private final ItemRepository itemRepository;
    private final LivingItemRepository livingItemRepository;
    private final LivingItemUniqueRepository livingItemUniqueRepository;
    
    public int getCount(@NonNull Player player, long itemId) {
        return this.getCount(player, itemId, 0);
    }
    
    public int getCount(@NonNull Player player, long itemId, int defaultCount) {
        Optional<LivingItemUnique> optionalUnique = livingItemUniqueRepository.findByPlayer(player);
        Item item = itemRepository.findById(itemId).orElseThrow(
            () -> new ObjectNotFoundException(player, Item.class, itemId)
        );

        if(optionalUnique.isEmpty()) {
            return defaultCount;
        } else {
            Optional<LivingItem> optionalVariable = livingItemRepository.findByLivingAndItem(
                optionalUnique.get(), item
            );

            if(optionalVariable.isEmpty()) {
                return defaultCount;
            } else {
                return optionalVariable.get().getItemCount();
            }
        }
    }
    
    @Nullable
    public Integer setCount(@NonNull Player player, long itemId, int count) {
        LivingItemUnique unique;
        Optional<LivingItemUnique> optionalUnique = livingItemUniqueRepository.findByPlayer(player);
        Item item = itemRepository.findById(itemId).orElseThrow(
            () -> new ObjectNotFoundException(player, Item.class, itemId)
        );
        
        if(optionalUnique.isEmpty()) {
            unique = livingItemUniqueRepository.save(
                LivingItemUnique.builder().player(player).build()
            );
            
            livingItemRepository.save(
                LivingItem.builder()
                    .living(unique)
                    .item(item)
                    .itemCount(count)
                    .build()
            );
            
            return null;
        } else {
            unique = optionalUnique.get();
            Optional<LivingItem> prevOptional = livingItemRepository.findByLivingAndItem(
                unique, item
            );
            
            livingItemRepository.save(
                LivingItem.builder()
                    .living(unique)
                    .item(item)
                    .itemCount(count)
                    .build()
            );
            
            if(prevOptional.isEmpty()) {
                return null;
            } else {
                return prevOptional.get().getItemCount();
            }
        }
    }
    
    @Nullable
    public Integer addCount(@NonNull Player player, long itemId, int count) {
        LivingItemUnique living;
        Optional<LivingItemUnique> optionalUnique = livingItemUniqueRepository.findByPlayer(player);
        Item item = itemRepository.findById(itemId).orElseThrow(
            () -> new ObjectNotFoundException(player, Item.class, itemId)
        );
    
        if(optionalUnique.isEmpty()) {
            living = livingItemUniqueRepository.save(
                LivingItemUnique.builder().player(player).build()
            );
            
            livingItemRepository.save(
                LivingItem.builder()
                    .living(living)
                    .item(item)
                    .itemCount(count)
                    .build()
            );
        
            return null;
        } else {
            living = optionalUnique.get();
            Optional<LivingItem> optionalLivingItem = livingItemRepository.findByLivingAndItem(
                living, item
            );
        
            if(optionalLivingItem.isEmpty()) {
                livingItemRepository.findByLivingAndItem(
                    living, item
                ).ifPresent(livingItem -> System.out.println(livingItem.getId()));
                livingItemRepository.save(
                    LivingItem.builder()
                        .living(living)
                        .item(item)
                        .itemCount(count)
                        .build()
                );
                
                return null;
            } else {
                LivingItem livingItem = optionalLivingItem.get();
                int prevCount = livingItem.getItemCount();
                
                livingItem.setItemCount(prevCount + count);
                return prevCount;
            }
        }
    }

}