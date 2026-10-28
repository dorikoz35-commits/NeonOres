package neonores;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(NeonOres.MODID)
public class NeonOres {
    public static final String MODID = "neonores";

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    // Гравитит: руда в камне и в глубинном сланце (твёрдость как у алмазной руды)
    public static final DeferredBlock<Block> GRAVITITE_ORE = BLOCKS.registerBlock(
            "gravitite_ore",
            props -> new DropExperienceBlock(UniformInt.of(3, 7), props),
            BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE));

    public static final DeferredBlock<Block> DEEPSLATE_GRAVITITE_ORE = BLOCKS.registerBlock(
            "deepslate_gravitite_ore",
            props -> new DropExperienceBlock(UniformInt.of(3, 7), props),
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE));

    public static final DeferredItem<Item> GRAVITITE = ITEMS.registerSimpleItem("gravitite", new Item.Properties());
    public static final DeferredItem<Item> GRAVITITE_INGOT = ITEMS.registerSimpleItem("gravitite_ingot", new Item.Properties());

    public static final DeferredItem<BlockItem> GRAVITITE_ORE_ITEM = ITEMS.registerSimpleBlockItem(GRAVITITE_ORE);
    public static final DeferredItem<BlockItem> DEEPSLATE_GRAVITITE_ORE_ITEM = ITEMS.registerSimpleBlockItem(DEEPSLATE_GRAVITITE_ORE);

    public NeonOres(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        modEventBus.addListener(NeonOres::addCreative);
    }

    private static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(GRAVITITE_ORE_ITEM);
            event.accept(DEEPSLATE_GRAVITITE_ORE_ITEM);
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(GRAVITITE);
            event.accept(GRAVITITE_INGOT);
        }
    }
}
