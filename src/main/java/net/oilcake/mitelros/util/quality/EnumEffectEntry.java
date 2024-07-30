package net.oilcake.mitelros.util.quality;

public enum EnumEffectEntry {
    Digging("挖掘速度"),
    Protection("护甲值"),
    Blocking("格挡效果"),
    ArrowSpeed("箭矢速度"),
    PullSpeed("拉弓速度"),
    Attack("攻击伤害"),
    ArrowDamage("箭矢伤害"),
    ;
    final String name;

    EnumEffectEntry(String name) {
        this.name = name;
    }

    public String getInfo(float multiplier) {
        return this.name + ": " + (multiplier > 1.0F ? "+" : "") + ((int) (multiplier * 100) - 100) + "%";
    }
}
