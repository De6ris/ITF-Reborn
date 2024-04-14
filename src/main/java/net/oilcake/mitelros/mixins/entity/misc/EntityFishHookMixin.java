//package net.oilcake.mitelros.mixins.entity.misc;
//
//import net.minecraft.*;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//@Mixin(EntityFishHook.class)
//public class EntityFishHookMixin extends Entity {
//    public EntityFishHookMixin(World par1World) {
//        super(par1World);
//    }
//
//    @Inject(method = "getFishType", at = @At("HEAD"))
//    private void moreFishType(CallbackInfoReturnable<Item> cir) {
//        if(this.rand.nextInt(2) == 0) {
//            cir.setReturnValue(Item.leather);
//        }
//    }
//
//    @Override
//    protected void entityInit() {
//
//    }
//
//    @Override
//    protected void readEntityFromNBT(NBTTagCompound nBTTagCompound) {
//
//    }
//
//    @Override
//    protected void writeEntityToNBT(NBTTagCompound nBTTagCompound) {
//
//    }
//}
