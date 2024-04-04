package net.oilcake.mitelros.util;

import net.xiaoyu233.fml.config.ConfigCategory;
import net.xiaoyu233.fml.config.ConfigEntry;
import net.xiaoyu233.fml.config.ConfigRoot;
import net.xiaoyu233.fml.util.FieldReference;

import java.io.File;

public class Config {
    public static final File CONFIG_FILE = new File("MITE-Is-Too-False-CFG.json");

    /* stuckTags */
    public static final FieldReference<Boolean> TagHeatStroke = new FieldReference(false);
    public static final FieldReference<Boolean> TagDryDilemma = new FieldReference(false);
    public static final FieldReference<Boolean> TagInstinctSurvival = new FieldReference(false);
    public static final FieldReference<Boolean> TagLegendFreeze = new FieldReference(false);
    public static final FieldReference<Boolean> TagHeatStorm = new FieldReference(false);
    public static final FieldReference<Boolean> TagRejection = new FieldReference(false);
    public static final FieldReference<Boolean> TagFallenInMineLVL1 = new FieldReference(false);
    public static final FieldReference<Boolean> TagBattleSufferLVL1 = new FieldReference(false);
    public static final FieldReference<Boolean> TagFallenInMineLVL2 = new FieldReference(false);
    public static final FieldReference<Boolean> TagBattleSufferLVL2 = new FieldReference(false);
    public static final FieldReference<Boolean> TagInvisibleFollower = new FieldReference(false);
    public static final FieldReference<Boolean> TagUnstableConvection = new FieldReference(false);
    public static final FieldReference<Boolean> TagEternalRaining = new FieldReference(false);
    public static final FieldReference<Boolean> TagDeadGeothermy = new FieldReference(false);
    public static final FieldReference<Boolean> TagApocalypse = new FieldReference(false);
    public static final FieldReference<Boolean> TagArmament = new FieldReference(false);
    public static final FieldReference<Boolean> TagDistortion = new FieldReference(false);
    public static final FieldReference<Boolean> TagWorshipDark = new FieldReference(false);
    public static final FieldReference<Boolean> TagMiracleDisaster = new FieldReference(false);
    public static final FieldReference<Boolean> TagPseudoVision = new FieldReference(false);
    public static final FieldReference<Boolean> TagUnderAlliance = new FieldReference(false);
    public static final FieldReference<Boolean> TagDigest = new FieldReference(false);

    /* del */
    public static final FieldReference<Boolean> TagAcousma = new FieldReference(false);
    public static final FieldReference<Boolean> TagNoWeatherPredict = new FieldReference(false);


    /* experimentalConfig */
    public static final FieldReference<Boolean> TagCreaturesV2 = new FieldReference(true);
    public static final FieldReference<Boolean> TagSpawningV2 = new FieldReference(true);
    public static final FieldReference<Boolean> TagBenchingV2 = new FieldReference(false);
    public static final FieldReference<Boolean> FinalChallenge = new FieldReference(false);
    public static final FieldReference<Boolean> Realistic = new FieldReference(false);
    public static final FieldReference<Boolean> TagMovingV2 = new FieldReference(false);
    /* del */
    public static final FieldReference<Boolean> NewVersion = new FieldReference(false);


    /* other */
    public static final FieldReference<Boolean> SeasonColor = new FieldReference(true);
    public static final FieldReference<Boolean> DisplayHud = new FieldReference(true);


    public static final ConfigRoot StuckTags = ConfigRoot.create(Constant.CONFIG_VERSION)
            .addEntry(ConfigCategory.of("挑战")
                    .addEntry(ConfigCategory.of("自然恶意")
                            .addEntry(ConfigEntry.of("heat_stroke", TagHeatStroke).withComment("(LVL1)酷暑代价：水分自然消耗的速度提升100%"))
                            .addEntry(ConfigEntry.of("dry_dilemma", TagDryDilemma).withComment("(LVL1)旱地：降低非碗类食物回复含水量的能力（奇数去尾，等于1更改概率）"))
                            .addEntry(ConfigEntry.of("legend_freeze", TagLegendFreeze).withComment("(LVL1)刺骨寒风：寒冷惩罚的积累速度提升200%"))
                            .addEntry(ConfigEntry.of("heat_storm", TagHeatStorm).withComment("(LVL1)灼地烈阳：玩家额外拥有炎热惩罚"))
                            .addEntry(ConfigEntry.of("rejection", TagRejection).withComment("(LVL2)世界排异：玩家始终获得一种女巫诅咒，尝试消除诅咒将随机改变诅咒类型"))
                            .addEntry(ConfigEntry.of("unstable_convection", TagUnstableConvection).withComment("(LVL1)不稳定对流：闪电的触发频率提升300%"))
                            .addEntry(ConfigEntry.of("eternal_raining", TagEternalRaining).withComment("(LVL2)阴雨连绵：雨的最长持续时间提升300%，最短持续时间提升700%"))
                            .addEntry(ConfigEntry.of("dead_geothermy", TagDeadGeothermy).withComment("(LVL2)地热失效：地下世界成为寒冷生物群系，更改地下世界基岩生成，同时生成绿宝石"))
                            .addEntry(ConfigEntry.of("apocalypse", TagApocalypse).withComment("(LVL3)灾厄余生：不再自然生成可提供肉类的动物"))
                    )
                    .addEntry(ConfigCategory.of("疯狂劲敌")
                            .addEntry(ConfigEntry.of("instinct_survival", TagInstinctSurvival).withComment("(LVL1)防御本能：怪物享受护甲防御的比率提升25%，同时取消保底1伤害的设定"))
                            .addEntry(ConfigEntry.of("fallen_in_mine_lvl1", TagFallenInMineLVL1).withComment("(LVL1)矿难群体：主世界矿洞生成僵尸扈从的概率提升"))
                            .addEntry(ConfigEntry.of("battle_suffer_lvl1", TagBattleSufferLVL1).withComment("(LVL1)久经沙场：主世界矿洞生成骷髅侍卫的概率提升"))
                            .addEntry(ConfigEntry.of("fallen_in_mine_lvl2", TagFallenInMineLVL2).withComment("(LVL2)矿难群体：主世界矿洞生成僵尸扈从的概率提升，亡魂的生命值提升50%，攻击力提升25%，且召唤僵尸支援"))
                            .addEntry(ConfigEntry.of("battle_suffer_lvl2", TagBattleSufferLVL2).withComment("(LVL2)久经沙场：主世界矿洞生成骷髅侍卫的概率提升，骷髅领主的生命值提升50%，攻击力提升40%，召唤的支援获得强化"))
                            .addEntry(ConfigEntry.of("invisible_follower", TagInvisibleFollower).withComment("(LVL1)无形跟随：更低层数的爬行者将被替换为潜伏爬行者"))
                            .addEntry(ConfigEntry.of("worship_dark", TagWorshipDark).withComment("(LVL2)崇尚黑暗：僵尸将尝试摧毁其沿途可见的火把"))
                            .addEntry(ConfigEntry.of("miracle_disaster", TagMiracleDisaster).withComment("(LVL1)迷幻危机：出现更多种类怪物的刷怪笼"))
                            .addEntry(ConfigEntry.of("pseudo_vision", TagPseudoVision).withComment("(LVL1)幻视暗示：黑色食尸鬼在成功索敌玩家后会给予玩家一次视觉黑暗效果"))
                            .addEntry(ConfigEntry.of("under_alliance", TagUnderAlliance).withComment("(LVL1)蛰骨联盟：出现更多种类的骷髅骑士"))
                    )
                    .addEntry(ConfigCategory.of("天赐福星")
                            .addEntry(ConfigEntry.of("armament", TagArmament).withComment("(LVL-2)战备军械：玩家的护甲值在耐久低于25%时才会减少，且不再受到低于自身护甲值的伤害"))
                            .addEntry(ConfigEntry.of("distortion", TagDistortion).withComment("(LVL-2)血肉畸变：玩家可获得最高40的生命值"))
                            .addEntry(ConfigEntry.of("digest", TagDigest).withComment("(LVL-2)原生代谢：玩家食用生肉/饮用水获得概率性debuff的概率降低100%"))
                    )
            )
            .addEntry(ConfigCategory.of("实验性玩法")
                    .addEntry(ConfigEntry.of("creatures", TagCreaturesV2).withComment("新动物生成机制"))
                    .addEntry(ConfigEntry.of("spawning", TagSpawningV2).withComment("新怪物生成频率"))
                    .addEntry(ConfigEntry.of("Benching", TagBenchingV2).withComment("工作站废料回收"))
                    .addEntry(ConfigEntry.of("final_challenge", FinalChallenge).withComment("终极挑战模式"))
                    .addEntry(ConfigEntry.of("realistic", Realistic).withComment("真实状态模拟"))
                    .addEntry(ConfigEntry.of("moving", TagMovingV2).withComment("新移动模式"))
            )
            .addEntry(ConfigCategory.of("其他配置项")
                    .addEntry(ConfigEntry.of("season_color", SeasonColor).withComment("季节植被颜色"))
                    .addEntry(ConfigEntry.of("display_hud", DisplayHud).withComment("信息显示"))
            );

}