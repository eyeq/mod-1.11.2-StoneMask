package eyeq.stonemask.event;

import eyeq.stonemask.StoneMask;
import eyeq.util.world.storage.loot.LootTableUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashSet;
import java.util.Set;

public class StoneMaskEventHandler {
    private static final Set<ResourceLocation> lootTableList = new HashSet<>();

    static {
        lootTableList.add(LootTableList.CHESTS_ABANDONED_MINESHAFT);
        lootTableList.add(LootTableList.CHESTS_DESERT_PYRAMID);
        lootTableList.add(LootTableList.CHESTS_JUNGLE_TEMPLE);
        lootTableList.add(LootTableList.CHESTS_STRONGHOLD_CORRIDOR);
        lootTableList.add(LootTableList.CHESTS_STRONGHOLD_CROSSING);
        lootTableList.add(LootTableList.CHESTS_SIMPLE_DUNGEON);
    }

    @SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event) {
        ResourceLocation name = event.getName();
        if(lootTableList.contains(name)) {
            LootPool pool = event.getTable().getPool("main");
            if(pool != null) {
                pool.addEntry(LootTableUtils.getLootEntryItem(StoneMask.stoneMask, 2));
            }
        }
    }
}
