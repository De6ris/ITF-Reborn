package net.oilcake.mitelros.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.SimpleConfigs;
import fi.dy.masa.malilib.config.options.ConfigBase;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.util.JsonUtils;
import net.oilcake.mitelros.ITFStart;
import net.xiaoyu233.fml.config.ConfigCategory;
import net.xiaoyu233.fml.config.ConfigEntry;
import net.xiaoyu233.fml.config.ConfigRoot;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ITFConfig extends SimpleConfigs {

    /* stuckTags */
    public static final ConfigBoolean TagHeatStroke = new ConfigBoolean("(LVL1)酷暑代价", "水分自然消耗的速度提升100%");
    public static final ConfigBoolean TagDryDilemma = new ConfigBoolean("(LVL1)旱地", "降低非碗类食物回复含水量的能力（奇数去尾，等于1更改概率）");
    public static final ConfigBoolean TagInstinctSurvival = new ConfigBoolean("(LVL1)防御本能", "怪物享受护甲防御的比率提升25%，同时取消保底1伤害的设定");
    public static final ConfigBoolean TagLegendFreeze = new ConfigBoolean("(LVL1)刺骨寒风", "寒冷惩罚的积累速度提升200%");
    public static final ConfigBoolean TagHeatStorm = new ConfigBoolean("(LVL1)灼地烈阳", "玩家额外拥有炎热惩罚");
    public static final ConfigBoolean TagRejection = new ConfigBoolean("(LVL2)世界排异", "玩家始终获得一种女巫诅咒，尝试消除诅咒将随机改变诅咒类型");
    public static final ConfigBoolean TagFallenInMineLVL1 = new ConfigBoolean("(LVL1)矿难群体", "主世界矿洞生成僵尸扈从的概率提升");
    public static final ConfigBoolean TagBattleSufferLVL1 = new ConfigBoolean("(LVL1)久经沙场", "主世界矿洞生成骷髅侍卫的概率提升");
    public static final ConfigBoolean TagFallenInMineLVL2 = new ConfigBoolean("(LVL2)矿难群体", "主世界矿洞生成僵尸扈从的概率提升，亡魂的生命值提升50%，攻击力提升25%，且召唤僵尸支援");
    public static final ConfigBoolean TagBattleSufferLVL2 = new ConfigBoolean("(LVL2)久经沙场", "主世界矿洞生成骷髅侍卫的概率提升，骷髅领主的生命值提升50%，攻击力提升40%，召唤的支援获得强化");
    public static final ConfigBoolean TagInvisibleFollower = new ConfigBoolean("(LVL1)无形跟随", "更低层数的爬行者将被替换为潜伏爬行者");
    public static final ConfigBoolean TagUnstableConvection = new ConfigBoolean("(LVL1)不稳定对流", "闪电的触发频率提升300%");
    public static final ConfigBoolean TagEternalRaining = new ConfigBoolean("(LVL2)阴雨连绵", "雨的最长持续时间提升300%，最短持续时间提升700%");
    public static final ConfigBoolean TagDeadGeothermy = new ConfigBoolean("(LVL2)地热失效", "地下世界成为寒冷生物群系，更改地下世界基岩生成，同时生成绿宝石");
    public static final ConfigBoolean TagApocalypse = new ConfigBoolean("(LVL3)灾厄余生", "不再自然生成可提供肉类的动物");
    public static final ConfigBoolean TagArmament = new ConfigBoolean("(LVL-2)战备军械", "玩家的护甲值在耐久低于25%时才会减少，且不再受到低于自身护甲值的伤害");
    public static final ConfigBoolean TagDistortion = new ConfigBoolean("(LVL-2)血肉畸变", "玩家可获得最高40的生命值");
    public static final ConfigBoolean TagWorshipDark = new ConfigBoolean("(LVL2)崇尚黑暗", "僵尸将尝试摧毁其沿途可见的火把");
    public static final ConfigBoolean TagMiracleDisaster = new ConfigBoolean("(LVL1)迷幻危机", "出现更多种类怪物的刷怪笼");
    public static final ConfigBoolean TagPseudoVision = new ConfigBoolean("(LVL1)幻视暗示", "黑色食尸鬼在成功索敌玩家后会给予玩家一次视觉黑暗效果");
    public static final ConfigBoolean TagUnderAlliance = new ConfigBoolean("(LVL1)蛰骨联盟", "出现更多种类的骷髅骑士");
    public static final ConfigBoolean TagDigest = new ConfigBoolean("(LVL-2)原生代谢", "玩家食用生肉/饮用水获得概率性debuff的概率降低100%");
    public static final ConfigBoolean TagDemonDescend = new ConfigBoolean("(LVL2)恶魔降临", "僵尸猪人领主血量提升50%，攻击力提升25%，概率手持钨钉头锤，且会召唤猪人守卫支援");
    public static final ConfigBoolean TagDimensionInvade = new ConfigBoolean("(LVL4)维度入侵", "除了末地之外的任何维度都会生成其他维度的敌对生物");


    /* experimentalConfig */
    public static final ConfigBoolean TagCreaturesV2 = new ConfigBoolean("新动物生成机制");
    public static final ConfigBoolean TagSpawningV2 = new ConfigBoolean("新动物生成频率");
    public static final ConfigBoolean TagBenchingV2 = new ConfigBoolean("工作站废料回收");
    public static final ConfigBoolean FinalChallenge = new ConfigBoolean("终极挑战模式");
    public static final ConfigBoolean Realistic = new ConfigBoolean("真实状态模拟");
    public static final ConfigBoolean TagMovingV2 = new ConfigBoolean("新移动模式");


    /* other */
    public static final ConfigBoolean SeasonColor = new ConfigBoolean("季节植被颜色", true);
    public static final ConfigBoolean DisplayHud = new ConfigBoolean("信息显示", true);

    public static List<ConfigBase> spite;
    public static List<ConfigBase> enemy;
    public static List<ConfigBase> luck;
    public static List<ConfigBase> others;
    public static List<ConfigBase> experimental;
    public static List<ConfigBase> values;

    public ITFConfig(String name, List<ConfigHotkey> hotkeys, List<ConfigBase> values) {
        super(name, hotkeys, values);
    }

    public static ITFConfig Instance;

    public static void init() {
        spite = List.of(TagUnstableConvection, TagHeatStorm, TagLegendFreeze, TagDryDilemma, TagHeatStroke, TagDeadGeothermy, TagRejection, TagEternalRaining, TagApocalypse, TagDimensionInvade);
        enemy = List.of(TagMiracleDisaster, TagInvisibleFollower, TagUnderAlliance, TagPseudoVision, TagInstinctSurvival, TagFallenInMineLVL1, TagBattleSufferLVL1, TagFallenInMineLVL2, TagBattleSufferLVL2, TagWorshipDark, TagDemonDescend);
        luck = List.of(TagDistortion, TagDigest, TagArmament);

        others = List.of(SeasonColor, DisplayHud);

        experimental = List.of(TagCreaturesV2, TagSpawningV2, TagBenchingV2, FinalChallenge, Realistic, TagMovingV2);

        values = new ArrayList<>();
        values.addAll(spite);
        values.addAll(enemy);
        values.addAll(luck);
        values.addAll(experimental);
        values.addAll(others);
        Instance = new ITFConfig(ITFStart.MOD_ID, null, values);
    }

    public static ITFConfig getInstance() {
        return Instance;
    }

    @Override
    public void save() {
        JsonObject root = new JsonObject();
        JsonObject challenge = JsonUtils.getNestedObject(root, "挑战", true);
        ConfigUtils.writeConfigBase(challenge, "自然恶意", spite);
        ConfigUtils.writeConfigBase(challenge, "疯狂劲敌", enemy);
        ConfigUtils.writeConfigBase(challenge, "天赐福星", luck);
        ConfigUtils.writeConfigBase(root, "实验性玩法", experimental);
        ConfigUtils.writeConfigBase(root, "其他配置项", others);
        JsonUtils.writeJsonToFile(root, this.getOptionsFile());
    }

    @Override
    public void load() {
        if (!this.getOptionsFile().exists()) {
            this.save();
        } else {
            JsonElement jsonElement = JsonUtils.parseJsonFile(this.getOptionsFile());
            if (jsonElement != null && jsonElement.isJsonObject()) {
                JsonObject root = jsonElement.getAsJsonObject();
                JsonObject challenge = JsonUtils.getNestedObject(root, "挑战", true);
                ConfigUtils.readConfigBase(challenge, "自然恶意", spite);
                ConfigUtils.readConfigBase(challenge, "疯狂劲敌", enemy);
                ConfigUtils.readConfigBase(challenge, "天赐福星", luck);
                ConfigUtils.readConfigBase(root, "实验性玩法", experimental);
                ConfigUtils.readConfigBase(root, "其他配置项", others);
            }
        }
    }

}