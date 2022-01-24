package lkd.namsic.cnkb.config.init;

import lkd.namsic.cnkb.domain.game.item.Item;
import lkd.namsic.cnkb.enums.object.ItemEnum;
import lkd.namsic.cnkb.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemInitializer extends Initializer {
    
    private final ItemRepository itemRepository;
    
    @Override
    protected String getName() {
        return "Item";
    }
    
    @Override
    protected void init() {
        for(ItemEnum itemEnum : ItemEnum.values()) {
            itemRepository.save(
                Item.builder()
                    .id(itemEnum.value)
                    .des(itemEnum.des)
                    .gainDes(itemEnum.gainDes)
                    .useDes(itemEnum.useDes)
                    .eatDes(itemEnum.eatDes)
                    .build()
            );
        }
    }
    
}
