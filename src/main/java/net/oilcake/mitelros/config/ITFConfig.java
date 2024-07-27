package net.oilcake.mitelros.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.dy.masa.malilib.config.ConfigTab;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.SimpleConfigs;
import fi.dy.masa.malilib.config.options.ConfigBase;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import fi.dy.masa.malilib.util.JsonUtils;
import net.oilcake.mitelros.ITFStart;
import net.oilcake.mitelros.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class ITFConfig extends SimpleConfigs {

    /* stuckTags */
    public static final ConfigBooleanChallenge TagHeatStroke = new ConfigBooleanChallenge("酷暑代价", "水分自然消耗的速度提升100%", 1);
    public static final ConfigBooleanChallenge TagDryDilemma = new ConfigBooleanChallenge("旱地", "降低非碗类食物回复含水量的能力，高于1的减1，等于1的降低概率", 1);
    public static final ConfigBooleanChallenge TagInstinctSurvival = new ConfigBooleanChallenge("防御本能", "怪物享受护甲防御的比率提升25%，同时取消保底1伤害的设定", 1);
    public static final ConfigBooleanChallenge TagRejection = new ConfigBooleanChallenge("世界排异", "玩家始终获得一种女巫诅咒，尝试消除诅咒将随机改变诅咒类型", 2);
    public static final ConfigInteger TagFallenInMine = new ConfigInteger("矿难群体", 0, 0, 2, "LVL1:主世界矿洞生成僵尸扈从的概率提升, LVL2:亡魂的生命值提升50%, 攻击力提升25%, 且召唤僵尸支援, 重启游戏生效");
    public static final ConfigInteger TagBattleSuffer = new ConfigInteger("久经沙场", 0, 0, 2, "LVL1:主世界矿洞生成骷髅侍卫的概率提升, LVL2:骷髅领主的生命值提升50%, 攻击力提升40%, 召唤的支援获得强化, 重启游戏生效");
    public static final ConfigBooleanChallenge TagInvisibleFollower = new ConfigBooleanChallenge("无形跟随", "更低层数的爬行者将被替换为潜伏爬行者", 1);
    public static final ConfigBooleanChallenge TagUnstableConvection = new ConfigBooleanChallenge("不稳定对流", "闪电的触发频率提升300%", 1);
    public static final ConfigBooleanChallenge TagEternalRaining = new ConfigBooleanChallenge("阴雨连绵", "雨的最长持续时间提升300%，最短持续时间提升700%", 2);
    public static final ConfigBooleanChallenge TagDeadGeothermy = new ConfigBooleanChallenge("地热失效", "地下世界成为寒冷生物群系, 更改地下世界基岩生成, 同时生成绿宝石, 重启游戏生效", 2);
    public static final ConfigBooleanChallenge TagApocalypse = new ConfigBooleanChallenge("灾厄余生", "不再自然生成可提供肉类的动物", 3);
    public static final ConfigBooleanChallenge TagWorshipDark = new ConfigBooleanChallenge("崇尚黑暗", "僵尸将尝试摧毁其沿途可见的火把", 2);
    public static final ConfigBooleanChallenge TagMiracleDisaster = new ConfigBooleanChallenge("迷幻危机", "主世界出现更多种类怪物的刷怪笼", 1);
    public static final ConfigBooleanChallenge TagPseudoVision = new ConfigBooleanChallenge("幻视暗示", "黑色食尸鬼在成功索敌玩家后会给予玩家一次视觉黑暗效果", 1);
    public static final ConfigBooleanChallenge TagUnderAlliance = new ConfigBooleanChallenge("蛰骨联盟", "出现更多种类的骷髅骑士", 1);
    public static final ConfigBooleanChallenge TagDemonDescend = new ConfigBooleanChallenge("恶魔降临", "僵尸猪人领主血量提升50%，攻击力提升25%，概率手持钨钉头锤，且会召唤猪人守卫支援", 2);
    public static final ConfigBooleanChallenge TagDimensionInvade = new ConfigBooleanChallenge("维度入侵", "除了末地之外的任何维度都会生成其他维度的敌对生物", 4);
    public static final ConfigInteger TagCorrosion = new ConfigInteger("瘴气", 0, 0, 3, "每级使玩家的物品耐久度消耗提升30%");
    public static final ConfigBooleanChallenge TagPillager = new ConfigBooleanChallenge("掠夺者", "允许部分怪物捡起物品", 1);
    public static final ConfigBooleanChallenge TagBurnOut = new ConfigBooleanChallenge("油尽灯枯", "火把会变成阴燃, 最后熄灭", 2);


    // luck
    public static final ConfigBooleanChallenge TagDigest = new ConfigBooleanChallenge("原生代谢", "玩家食用生肉/饮用水获得概率性debuff的概率降低100%", -2);
    public static final ConfigBooleanChallenge TagArmament = new ConfigBooleanChallenge("战备军械", "玩家的护甲值在耐久低于25%时才会减少，且不再受到低于自身护甲值的伤害", -2);
    public static final ConfigBooleanChallenge TagWorkOfHeaven = new ConfigBooleanChallenge("巧夺天工", "品质装备的效果增强一倍", -2);
    public static final ConfigBooleanChallenge TagTotemBlessing = new ConfigBooleanChallenge("图腾祝福", "加强图腾的正面效果", -2);


    /* experimentalConfig */
    public static final ConfigBoolean TagCreaturesV2 = new ConfigBoolean("新动物生成机制", "重启游戏生效");
    public static final ConfigBoolean TagBenchingV2 = new ConfigBoolean("工作站废料回收");
    public static final ConfigBoolean FinalChallenge = new ConfigBoolean("终极挑战模式");


    //misc
    public static final ConfigBoolean FixedID = new ConfigBoolean("固定ID", false, "(整合包作者需考虑)固定物品ID以及方块ID");
    public static final ConfigInteger ItemIDStart = new ConfigInteger("物品ID起始点", 4000, 4000, 10000, false, "明显需要重启, 且有崩档风险, 操作前请备份!");
    public static final ConfigInteger BlockIDStart = new ConfigInteger("方块ID起始点", 4095, 255, 4095, false, "明显需要重启, 且有崩档风险, 操作前请备份!");

    public static final List<ConfigBase<?>> challenge;
    public static final List<ConfigBase<?>> spite;
    public static final List<ConfigBase<?>> enemy;
    public static final List<ConfigBase<?>> luck;
    public static final List<ConfigBase<?>> misc;
    public static final List<ConfigBase<?>> experimental;
    public static final List<ConfigBase<?>> values;

    public ITFConfig(String name, List<ConfigHotkey> hotkeys, List<ConfigBase<?>> values) {
        super(name, hotkeys, values);
    }

    public static final List<ConfigTab> configTabs = new ArrayList<>();
    public static final ITFConfig Instance;

    static {
        spite = List.of(TagUnstableConvection, TagDryDilemma, TagHeatStroke, TagDeadGeothermy, TagRejection, TagEternalRaining, TagApocalypse, TagDimensionInvade, TagCorrosion, TagBurnOut);
        enemy = List.of(TagMiracleDisaster, TagInvisibleFollower, TagUnderAlliance, TagPseudoVision, TagInstinctSurvival, TagFallenInMine, TagBattleSuffer, TagWorshipDark, TagDemonDescend, TagPillager);
        luck = List.of(TagDigest, TagArmament, TagWorkOfHeaven, TagTotemBlessing);
        challenge = new ArrayList<>();
        challenge.addAll(spite);
        challenge.addAll(enemy);
        challenge.addAll(luck);

        experimental = List.of(TagCreaturesV2, TagBenchingV2, FinalChallenge);
        misc = List.of(FixedID, ItemIDStart, BlockIDStart);

        values = new ArrayList<>();
        values.addAll(challenge);
        values.addAll(experimental);
        values.addAll(misc);

        Instance = new ITFConfig(ITFStart.MOD_ID, null, values);

        Constant.ultimateDifficulty = calculateUltimateDifficulty();


        configTabs.add(new ConfigTab("自然恶意", spite));
        configTabs.add(new ConfigTab("疯狂劲敌", enemy));
        configTabs.add(new ConfigTab("天赐福星", luck));
        configTabs.add(new ConfigTab("实验性玩法", experimental));
        configTabs.add(new ConfigTab("杂项", misc));
    }

    private static int calculateUltimateDifficulty() {
        int difficulty = 0;
        for (ConfigBase<?> configBase : spite) {
            if (configBase instanceof ConfigBooleanChallenge configBooleanChallenge) {
                difficulty += configBooleanChallenge.getLevel();
            }
            if (configBase instanceof ConfigInteger configInteger) {
                difficulty += configInteger.getMaxIntegerValue();
            }
        }
        for (ConfigBase<?> configBase : enemy) {
            if (configBase instanceof ConfigBooleanChallenge configBooleanChallenge) {
                difficulty += configBooleanChallenge.getLevel();
            }
            if (configBase instanceof ConfigInteger configInteger) {
                difficulty += configInteger.getMaxIntegerValue();
            }
        }
        return difficulty;
    }

    public static ITFConfig getInstance() {
        return Instance;
    }

    @Override
    public List<ConfigTab> getConfigTabs() {
        return configTabs;
    }

    @Override
    public void save() {
        JsonObject root = new JsonObject();
        JsonObject challenge = JsonUtils.getNestedObject(root, "挑战", true);
        ConfigUtils.writeConfigBase(challenge, "自然恶意", spite);
        ConfigUtils.writeConfigBase(challenge, "疯狂劲敌", enemy);
        ConfigUtils.writeConfigBase(challenge, "天赐福星", luck);
        ConfigUtils.writeConfigBase(root, "实验性玩法", experimental);
        ConfigUtils.writeConfigBase(root, "杂项", misc);
        JsonUtils.writeJsonToFile(root, this.optionsFile);
    }

    @Override
    public void load() {
        if (!this.optionsFile.exists()) {
            this.save();
        } else {
            JsonElement jsonElement = JsonUtils.parseJsonFile(this.optionsFile);
            if (jsonElement != null && jsonElement.isJsonObject()) {
                JsonObject root = jsonElement.getAsJsonObject();
                JsonObject challenge = JsonUtils.getNestedObject(root, "挑战", true);
                ConfigUtils.readConfigBase(challenge, "自然恶意", spite);
                ConfigUtils.readConfigBase(challenge, "疯狂劲敌", enemy);
                ConfigUtils.readConfigBase(challenge, "天赐福星", luck);
                ConfigUtils.readConfigBase(root, "实验性玩法", experimental);
                ConfigUtils.readConfigBase(root, "杂项", misc);
            }
            if (FixedID.getBooleanValue()) {
                Constant.nextItemID = ItemIDStart.getIntegerValue();
                Constant.nextBlockID = BlockIDStart.getIntegerValue();
            }
        }
    }

}