package net.oilcake.mitelros.mixins.render;
//

import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin({RenderPlayer.class})
public class RenderPlayerMixin extends RendererLivingEntity {

    @Shadow
    private ModelBiped modelBipedMain;
    @Shadow
    private ModelBiped modelArmor;
    @Shadow
    private ModelBiped modelArmorChestplate;
    private ModelBiped SpecializedRenderBody;

    private ModelBiped SpecializedRenderLegs;

    private ModelBiped SpecializedRenderHead;

    private ModelBiped newRenderBody;

    private ModelBiped newRenderLegs;

    public RenderPlayerMixin(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    @Inject(method = {"<init>()V"}, at = {@At("RETURN")})
    public void injectCtor(CallbackInfo callbackInfo) {
        this.SpecializedRenderBody = new ModelBiped(0.25F);
        this.SpecializedRenderLegs = new ModelBiped(0.0125F);
        this.SpecializedRenderHead = new ModelBiped(0.5F);
        this.newRenderBody = new ModelBiped(0.75F);
        this.newRenderLegs = new ModelBiped(0.375F);
    }

    @Inject(method = "setArmorModel", at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemArmor;getArmorMaterial()Lnet/minecraft/Material;"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void reSet(AbstractClientPlayer par1AbstractClientPlayer, int par2, float par3, CallbackInfoReturnable<Integer> cir, ItemStack var4, Item var5, ItemArmor var6, ModelBiped var7, float var8) {
        if (var6.getArmorMaterial() == Materials.custom_b || var6.getArmorMaterial() == Materials.custom_a) {
            var7 = (par2 == 2) ? this.SpecializedRenderLegs : ((par2 == 0) ? this.SpecializedRenderHead : this.SpecializedRenderBody);
            var7.bipedHead.showModel = (par2 == 0);
            var7.bipedHeadwear.showModel = (par2 == 0);
            var7.bipedBody.showModel = (par2 == 1 || par2 == 2);
            var7.bipedRightArm.showModel = (par2 == 1);
            var7.bipedLeftArm.showModel = (par2 == 1);
            var7.bipedRightLeg.showModel = (par2 == 2 || par2 == 3);
            var7.bipedLeftLeg.showModel = (par2 == 2 || par2 == 3);
        } else {
            var7 = (par2 == 2) ? this.newRenderLegs : this.newRenderBody;
            var7.bipedHead.showModel = (par2 == 0);
            var7.bipedHeadwear.showModel = (par2 == 0);
            var7.bipedBody.showModel = (par2 == 1 || par2 == 2);
            var7.bipedRightArm.showModel = (par2 == 1 || par2 == 2);
            var7.bipedLeftArm.showModel = (par2 == 1 || par2 == 2);
            var7.bipedRightLeg.showModel = (par2 == 2 || par2 == 3);
            var7.bipedLeftLeg.showModel = (par2 == 2 || par2 == 3);
        }
        var7.onGround = this.mainModel.onGround;
        var7.isRiding = this.mainModel.isRiding;
        var7.isChild = this.mainModel.isChild;
        this.setRenderPassModel(var7);
    }

    /**
     * @author
     * @reason TODO hard to inject maybe
     */
    @Overwrite
    public void func_130009_a(AbstractClientPlayer par1AbstractClientPlayer, double par2, double par4, double par6, float par8, float par9) {
        float var10 = 1.0F;
        GL11.glColor3f(var10, var10, var10);
        ItemStack var11 = par1AbstractClientPlayer.inventory.getCurrentItemStack();
        this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = this.SpecializedRenderBody.heldItemRight = this.SpecializedRenderLegs.heldItemRight = this.newRenderBody.heldItemRight = this.newRenderLegs.heldItemRight = this.SpecializedRenderHead.heldItemRight = var11 != null ? 1 : 0;
        if (var11 != null && par1AbstractClientPlayer.getItemInUseCount() > 0) {
            EnumItemInUseAction var12 = var11.getItemInUseAction(par1AbstractClientPlayer);
            if (var12 == EnumItemInUseAction.BLOCK) {
                this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = this.SpecializedRenderBody.heldItemRight = this.SpecializedRenderLegs.heldItemRight = this.newRenderBody.heldItemRight = this.newRenderLegs.heldItemRight = this.SpecializedRenderHead.heldItemRight = 3;
            } else if (var12 == EnumItemInUseAction.BOW) {
                this.modelArmorChestplate.aimedBow = this.modelArmor.aimedBow = this.modelBipedMain.aimedBow = this.SpecializedRenderBody.aimedBow = this.SpecializedRenderLegs.aimedBow = this.newRenderBody.aimedBow = this.newRenderLegs.aimedBow = this.SpecializedRenderHead.aimedBow = true;
            }
        }

        this.modelArmorChestplate.isSneak = this.modelArmor.isSneak = this.modelBipedMain.isSneak = this.SpecializedRenderBody.isSneak = this.SpecializedRenderLegs.isSneak = this.newRenderBody.isSneak = this.newRenderLegs.isSneak = this.SpecializedRenderHead.isSneak = par1AbstractClientPlayer.isSneaking();
        double var14 = par4 - (double) par1AbstractClientPlayer.yOffset;
        if (par1AbstractClientPlayer.isSneaking() && !(par1AbstractClientPlayer instanceof ClientPlayer)) {
            var14 -= 0.125;
        }

        super.doRenderLiving(par1AbstractClientPlayer, par2, var14, par6, par8, par9);
        this.modelArmorChestplate.aimedBow = this.modelArmor.aimedBow = this.modelBipedMain.aimedBow = this.SpecializedRenderBody.aimedBow = this.SpecializedRenderLegs.aimedBow = this.newRenderBody.aimedBow = this.newRenderLegs.aimedBow = this.SpecializedRenderHead.aimedBow = false;
        this.modelArmorChestplate.isSneak = this.modelArmor.isSneak = this.modelBipedMain.isSneak = this.SpecializedRenderBody.isSneak = this.SpecializedRenderLegs.isSneak = this.newRenderBody.isSneak = this.newRenderLegs.isSneak = this.SpecializedRenderHead.isSneak = false;
        this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = this.SpecializedRenderBody.heldItemRight = this.SpecializedRenderLegs.heldItemRight = this.newRenderBody.heldItemRight = this.newRenderLegs.heldItemRight = this.SpecializedRenderHead.heldItemRight = 0;
    }

    @Shadow
    protected ResourceLocation getEntityTexture(Entity entity) {
        return null;
    }
}
