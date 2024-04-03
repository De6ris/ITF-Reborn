//package net.oilcake.mitelros.enchantment;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//
//import net.minecraft.EnumRarity;
//import sun.reflect.ConstructorAccessor;
//
//public class EnchantmentLevel {
//    private static ConstructorAccessor constructorAccessor;
//
//    private static int ordinary = (EnumRarity.values()).length;
//
//    static {
//        try {
//            Constructor<?> CTOR = EnumRarity.uncommon.getClass().getDeclaredConstructors()[0];
//            Method acquireConstructorAccessor = Constructor.class.getDeclaredMethod("acquireConstructorAccessor", new Class[0]);
//            acquireConstructorAccessor.setAccessible(true);
//            acquireConstructorAccessor.invoke(CTOR, new Object[0]);
//            Field field = Constructor.class.getDeclaredField("constructorAccessor");
//            field.setAccessible(true);
//            constructorAccessor = (ConstructorAccessor) field.get(CTOR);
//        } catch (NoSuchMethodException | IllegalAccessException | java.lang.reflect.InvocationTargetException |
//                 NoSuchFieldException e) {
//            throw new NumberFormatException();
//        }
//    }
//
//    public static final EnumRarity Ultimate = newEnumEnchantmentLevel(13, "Ultimate", 0);
//
//    private static final EnumRarity newEnumEnchantmentLevel(int color_index, String name, int standard_weight) {
//        try {
//            return (EnumRarity) constructorAccessor.newInstance(new Object[]{name, Integer.valueOf(ordinary++), Integer.valueOf(color_index), name, Integer.valueOf(standard_weight)});
//        } catch (InstantiationException | java.lang.reflect.InvocationTargetException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
