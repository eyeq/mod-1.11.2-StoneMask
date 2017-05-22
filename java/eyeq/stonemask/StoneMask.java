package eyeq.stonemask;

import eyeq.stonemask.item.ItemArmorStoneMask;
import eyeq.util.client.model.UModelCreator;
import eyeq.util.client.model.UModelLoader;
import eyeq.util.client.model.gson.ItemmodelJsonFactory;
import eyeq.util.client.renderer.ResourceLocationFactory;
import eyeq.util.client.resource.ULanguageCreator;
import eyeq.util.client.resource.lang.LanguageResourceManager;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;

import static eyeq.stonemask.StoneMask.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
@Mod.EventBusSubscriber
public class StoneMask {
    public static final String MOD_ID = "eyeq_stonemask";

    @Mod.Instance(MOD_ID)
    public static StoneMask instance;

    private static final ResourceLocationFactory resource = new ResourceLocationFactory(MOD_ID);

    public static Item stoneMask;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if(event.getSide().isServer()) {
            return;
        }
        renderItemModels();
        createFiles();
    }

    @SubscribeEvent
    protected static void registerItems(RegistryEvent.Register<Item> event) {
        stoneMask = new ItemArmorStoneMask(0, EntityEquipmentSlot.HEAD).setUnlocalizedName("stoneMask");

        GameRegistry.register(stoneMask, resource.createResourceLocation("stone_mask"));
    }

	@SideOnly(Side.CLIENT)
    public static void renderItemModels() {
        UModelLoader.setCustomModelResourceLocation(stoneMask);
    }
	
    public static void createFiles() {
    	File project = new File("../1.11.2-StoneMask");
    	
        LanguageResourceManager language = new LanguageResourceManager();

        language.register(LanguageResourceManager.EN_US, stoneMask, "Stone Mask");
        language.register(LanguageResourceManager.JA_JP, stoneMask, "石仮面");

        ULanguageCreator.createLanguage(project, MOD_ID, language);

        UModelCreator.createItemJson(project, stoneMask, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
    }
}
