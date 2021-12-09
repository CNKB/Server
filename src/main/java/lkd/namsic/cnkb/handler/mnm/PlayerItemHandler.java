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

import java.util.Objects;
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
        Item item = itemRepository.findById(itemId).orElseThrow(
            () -> new ObjectNotFoundException(player, Item.class, itemId)
        );
        Optional<LivingItemUnique> optionalUnique = livingItemUniqueRepository.findByPlayerAndItem(
            player, item
        );

        if(optionalUnique.isEmpty()) {
            return defaultCount;
        } else {
            Optional<LivingItem> optionalVariable = livingItemRepository.findByLiving(
                optionalUnique.get()
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
        Item item = itemRepository.findById(itemId).orElseThrow(
            () -> new ObjectNotFoundException(player, Item.class, itemId)
        );
        Optional<LivingItemUnique> optionalUnique = livingItemUniqueRepository.findByPlayerAndItem(
            player, item
        );
    
        LivingItemUnique unique;
        if(optionalUnique.isEmpty()) {
            unique = livingItemUniqueRepository.save(
                LivingItemUnique.builder()
                    .player(player)
                    .item(item)
                    .build()
            );
            
            livingItemRepository.save(
                LivingItem.builder()
                    .living(unique)
                    .itemCount(count)
                    .build()
            );
            
            return null;
        } else {
            unique = optionalUnique.get();
            Optional<LivingItem> prevOptional = livingItemRepository.findByLiving(
                unique
            );
            
            livingItemRepository.save(
                LivingItem.builder()
                    .living(unique)
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
    
    public int addCount(@NonNull Player player, long itemId, int count) {
        return Objects.requireNonNull(this.addCount(player, itemId, count, 0));
    }
    
    @Nullable
    public Integer addCount(@NonNull Player player, long itemId, int count, @Nullable Integer defaultValue) {
        Item item = itemRepository.findById(itemId).orElseThrow(
            () -> new ObjectNotFoundException(player, Item.class, itemId)
        );
        Optional<LivingItemUnique> optionalUnique = livingItemUniqueRepository.findByPlayerAndItem(
            player, item
        );
    
        LivingItemUnique living;
        if(optionalUnique.isEmpty()) {
            living = livingItemUniqueRepository.save(
                LivingItemUnique
                    .builder()
                    .player(player)
                    .item(item)
                    .build()
            );
            
            livingItemRepository.save(
                LivingItem.builder()
                    .living(living)
                    .itemCount(count)
                    .build()
            );
        
            return defaultValue;
        } else {
            living = optionalUnique.get();
            Optional<LivingItem> optionalLivingItem = livingItemRepository.findByLiving(
                living
            );
        
            if(optionalLivingItem.isEmpty()) {
                livingItemRepository.save(
                    LivingItem.builder()
                        .living(living)
                        .itemCount(count)
                        .build()
                );
                
                return defaultValue;
            } else {
                LivingItem livingItem = optionalLivingItem.get();
                int prevCount = livingItem.getItemCount();
                
                livingItem.setItemCount(prevCount + count);
                livingItemRepository.save(livingItem);
                
                return prevCount;
            }
        }
    }

}