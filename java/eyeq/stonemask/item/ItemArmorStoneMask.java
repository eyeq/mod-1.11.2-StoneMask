package eyeq.stonemask.item;

import eyeq.stonemask.StoneMask;
import eyeq.util.UItemArmor;
import eyeq.util.entity.EntityLivingBaseUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class ItemArmorStoneMask extends UItemArmor {
    public static final ArmorMaterial armorMaterialStoneMask = EnumHelper.addArmorMaterial("stone_mask", "", 12, new int[]{1, 1, 1, 1}, 0, null, 0.0F).setRepairItem(new ItemStack(Blocks.REDSTONE_BLOCK));

    private static final ResourceLocation armorName = new ResourceLocation(StoneMask.MOD_ID, "stone_mask");

    public ItemArmorStoneMask(int renderIndex, EntityEquipmentSlot equipmentSlot) {
        super(armorMaterialStoneMask, renderIndex, equipmentSlot, armorName);
    }

    @Override
    protected void onArmorTickHead(World world, EntityPlayer player, ItemStack itemStack) {
        if(world.isDaytime() && player.getBrightness(1.0F) > 0.5F && world.canSeeSky(new BlockPos(player.posX, player.posY + player.getEyeHeight(), player.posZ))) {
            player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 1, 1));
            player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 1, 1));
            player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 1, 0));
            player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 1, 1));
            player.setFire(1);
        } else {
            EntityLivingBaseUtils.clearActiveBadPotions(player);
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 1, 1));
            player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 1, 1));
            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 1, 1));
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 1, 1));
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1, 1));
            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 1, 2));
            player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 1, 0));
            player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 1, 9));
            player.extinguish();
        }
    }
}
