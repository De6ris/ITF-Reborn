package net.oilcake.mitelros.mixins.fml;

import com.chocohead.mm.api.ClassTinkerers;
import com.chocohead.mm.api.EnumAdder;
import net.oilcake.mitelros.api.ITFEnumExtend;
import net.xiaoyu233.fml.util.EnumExtends;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnumExtends.class)
public abstract class EnumExtendsMixin implements ITFEnumExtend {// TODO does not work now

    private static final EnumAdder ENCHANTMENT_RARITY = ClassTinkerers.enumBuilder
                    ("net.minecraft.EnumRarity", new Object[]{Integer.TYPE, String.class, Integer.TYPE})
            .addEnum("ULTIMATE", () -> new Object[]{13, "Ultimate", 0});
    ;

    @Inject(method = "buildEnumExtending", at = @At("TAIL"))
    private static void inject(CallbackInfo ci) {
        ENCHANTMENT_RARITY.build();
    }

    @Override
    public EnumAdder getRarity() {
        return ENCHANTMENT_RARITY;
    }
}
