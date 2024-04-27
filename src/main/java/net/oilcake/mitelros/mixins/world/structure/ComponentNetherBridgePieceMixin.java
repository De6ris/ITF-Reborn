package net.oilcake.mitelros.mixins.world.structure;

import net.minecraft.ComponentNetherBridgePiece;
import net.minecraft.WeightedRandomChestContent;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ComponentNetherBridgePiece.class)
public class ComponentNetherBridgePieceMixin {
    @Mutable
    @Shadow
    @Final
    protected static WeightedRandomChestContent[] field_111019_a;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void addITFLoot(CallbackInfo ci) {
        field_111019_a = WeightedRandomChestContent.func_92080_a(field_111019_a,
                new WeightedRandomChestContent(Items.tungstenIngot.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Items.tungstenNugget.itemID, 0, 3, 7, 10),
                new WeightedRandomChestContent(Items.tungstenSword.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Items.tungstenWarHammer.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Items.tungstenHelmetChain.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Items.tungstenChestplateChain.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Items.tungstenLeggingsChain.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Items.tungstenBootsChain.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Items.tungstenHelmet.itemID, 0, 1, 1, 2),
                new WeightedRandomChestContent(Items.tungstenChestplate.itemID, 0, 1, 1, 2),
                new WeightedRandomChestContent(Items.tungstenLeggings.itemID, 0, 1, 1, 2),
                new WeightedRandomChestContent(Items.tungstenBoots.itemID, 0, 1, 1, 2),
                new WeightedRandomChestContent(Items.ignitionGold.itemID, 0, 1, 1, 18),
                new WeightedRandomChestContent(Items.ignitionTungsten.itemID, 0, 1, 1, 2),
                new WeightedRandomChestContent(Items.totemOfDestroy.itemID, 0, 1, 1, 5)
        );
    }
}
