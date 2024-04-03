//package net.oilcake.mitelros.util;
//
//import net.xiaoyu233.fml.config.ConfigEntry;
//import net.xiaoyu233.fml.config.ConfigRoot;
//import net.xiaoyu233.fml.util.FieldReference;
//
//import java.io.*;
//
//public class ExperimentalConfig {
//    public static Map<String, ConfigItem> Tags = new HashMap<>();
//
//    public static class ConfigItem<T> {
//        public String ConfigKey;
//        public T ConfigValue;
//        public T min;
//        public T max;
//        public boolean isNeedCompare = false;
//        public String ConfigComment;
//
//        ConfigItem(String key, T value, String comment) {
//            this.ConfigKey = key;
//            this.ConfigValue = value;
//            this.ConfigComment = comment;
//        }
//
//        ConfigItem(String key, T value, String comment, T min, T max) {
//            this.ConfigKey = key;
//            this.ConfigValue = value;
//            this.isNeedCompare = true;
//            this.min = min;
//            this.max = max;
//            this.ConfigComment = comment + " [范围：" + min + "-" + max + "]";
//        }
//
//        public void setConfigValue(T configValue) {
//            ConfigValue = configValue;
//        }
//
//        public T getConfigValue() {
//            return this.ConfigValue;
//        }
//    }
//
//    public static class TagConfig {
//        public static ConfigItem<Boolean> TagCreaturesV2 = new ConfigItem<>("CreaturesV2", true, "新动物生成机制");
//        public static ConfigItem<Boolean> TagSpawningV2 = new ConfigItem<>("SpawningV2", true, "新怪物生成频率");
//        public static ConfigItem<Boolean> TagBenchingV2 = new ConfigItem<>("BenchingV2", false, "工作站废料回收");
//        public static ConfigItem<Boolean> FinalChallenge = new ConfigItem<>("FinalChallenge", false, "终极挑战模式");
//        public static ConfigItem<Boolean> Realistic = new ConfigItem<>("Realistic", false, "真实状态模拟");
//        public static ConfigItem<Boolean> TagMovingV2 = new ConfigItem<>("MovingV2", false, "新移动模式");
////        public static ConfigItem <Boolean> NewVersion = new ConfigItem<>("NewVersion", false, "启用未完成的版本内容");
//        //*这个有问题*//
//
//        //public static ConfigItem <Boolean> = new ConfigItem("Tag",false,"(LVL)");
//    }
//
//    public static void loadConfigs(String filePth) {
//        System.out.println("Experimental settings were put in HASHMAP");
//        //常驻
//        Tags.put("CreaturesV2", TagConfig.TagCreaturesV2);
//        Tags.put("SpawningV2", TagConfig.TagSpawningV2);
//        Tags.put("BenchingV2", TagConfig.TagBenchingV2);
//        Tags.put("FinalChallenge", TagConfig.FinalChallenge);
//        Tags.put("Realistic", TagConfig.Realistic);
//        Tags.put("MovingV2", TagConfig.TagMovingV2);
////        Tags.put("NewVersion", TagConfig.NewVersion);
////      Tags.put("NoWeatherPredict",TagConfig.TagNoWeatherPredict);
//
//        File file_mite = new File(filePth);
//        if (file_mite.exists()) {
//            System.out.println("READING SETTINGS FILE");
//            Properties properties = new Properties();
//            FileReader fr = null;
//            try {
//                fr = new FileReader(file_mite.getName());
//                properties.load(fr);
//                fr.close();
//                readConfigFromFile(file_mite, properties);
//                packConfigFile(file_mite, properties);
//            } catch (FileNotFoundException var6) {
//                System.out.println("READING FAILED TP1");
//                var6.printStackTrace();
//            } catch (IOException var7) {
//                System.out.println("READING FAILED TP2");
//                var7.printStackTrace();
//            }
//        } else {
//            System.out.println("GENERATING SETTINGS FILE");
//            try {
//                if (file_mite.createNewFile()) {
//                    file_mite.setExecutable(true);//设置可执行权限
//                    file_mite.setReadable(true);//设置可读权限
//                    file_mite.setWritable(true);//设置可写权限
//                    generateConfigFile(file_mite);
//                }
//            } catch (IOException e) {
//                System.out.println("GENERATING FAILED");
//                e.printStackTrace();
//                JFrame jFrame = new JFrame();
//                jFrame.setAlwaysOnTop(true);
//                JOptionPane.showMessageDialog(jFrame, "实验性玩法读取失败，尝试删除配置文件……", "错误", 0);
//                System.exit(0);
//            }
//        }
//    }
//
//    public static void readConfigFromFile(File file_mite, Properties properties) {
//        for (String key : properties.stringPropertyNames()) {
//            ConfigItem configItem = Tags.get(key);
//            if (configItem != null) {
//                if (configItem.ConfigValue instanceof Boolean) {
//                    configItem.setConfigValue(Boolean.parseBoolean(properties.getProperty(key)));
//                } else if (configItem.ConfigValue instanceof Float) {
//                    float value = Float.parseFloat(properties.getProperty(key));
//                    if (configItem.isNeedCompare) {
//                        value = value > (float) configItem.max ? (float) configItem.max : Math.max(value, (float) configItem.min);
//                    }
//                    configItem.setConfigValue(value);
//                } else if (configItem.ConfigValue instanceof Double) {
//                    double value = Double.parseDouble(properties.getProperty(key));
//                    if (configItem.isNeedCompare) {
//                        value = value > (double) configItem.max ? (double) configItem.max : Math.max(value, (double) configItem.min);
//                    }
//                    configItem.setConfigValue(value);
//                } else if (configItem.ConfigValue instanceof Integer) {
//                    int value = Integer.parseInt(properties.getProperty(key));
//                    if (configItem.isNeedCompare) {
//                        value = value > (int) configItem.max ? (int) configItem.max : Math.max(value, (int) configItem.min);
//                    }
//                    configItem.setConfigValue(value);
//                } else {
//                    configItem.setConfigValue(properties.getProperty(key));
//                }
//            }
//        }
//    }
//
//    public static void packConfigFile(File file, Properties properties) {
//        try {
//            FileWriter fileWritter = new FileWriter(file.getName(), true);
//            for (Map.Entry<String, ConfigItem> entry : Tags.entrySet()) {
//                ConfigItem value = entry.getValue();
//                String localValue = properties.getProperty(value.ConfigKey);
//                if (localValue == null) {
//                    fileWritter.write("// " + value.ConfigComment + "\n");
//                    fileWritter.write(value.ConfigKey + "=" + value.ConfigValue + "\n\n");
//                }
//            }
//            fileWritter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void generateConfigFile(File file) {
//        try {
//            FileWriter fileWritter = new FileWriter(file);
//            fileWritter.write("// 在每一项配置后填入true或者false来选择，不建议在游玩中途更改设置 \n");
//            for (Map.Entry<String, ConfigItem> entry : Tags.entrySet()) {
//                ConfigItem value = entry.getValue();
//                fileWritter.write("// " + value.ConfigComment + "\n");
//                fileWritter.write(value.ConfigKey + "=" + value.ConfigValue + "\n\n");
//            }
//            fileWritter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static final File CONFIG_FILE = new File("ITF-Experimental-CFG.json");
//
//    public static final FieldReference<Boolean> TagCreaturesV2 = new FieldReference(true);
//    public static final FieldReference<Boolean> TagSpawningV2 = new FieldReference(true);
//    public static final FieldReference<Boolean> TagBenchingV2 = new FieldReference(false);
//    public static final FieldReference<Boolean> FinalChallenge = new FieldReference(false);
//    public static final FieldReference<Boolean> Realistic = new FieldReference(false);
//    public static final FieldReference<Boolean> TagMovingV2 = new FieldReference(false);
//    public static final FieldReference<Boolean> SeasonColor = new FieldReference(true);
//
//    /* del */
//    public static final FieldReference<Boolean> NewVersion = new FieldReference(false);
//
//    public static final ConfigRoot ExperimentalConfig = ConfigRoot.create(Constant.CONFIG_VERSION)
//            .addEntry(ConfigEntry.of("creatures", TagCreaturesV2).withComment("新动物生成机制"))
//            .addEntry(ConfigEntry.of("spawning", TagSpawningV2).withComment("新怪物生成频率"))
//            .addEntry(ConfigEntry.of("Benching", TagBenchingV2).withComment("工作站废料回收"))
//            .addEntry(ConfigEntry.of("final_challenge", FinalChallenge).withComment("终极挑战模式"))
//            .addEntry(ConfigEntry.of("realistic", Realistic).withComment("真实状态模拟"))
//            .addEntry(ConfigEntry.of("moving", TagMovingV2).withComment("新移动模式"))
//            .addEntry(ConfigEntry.of("season_color", SeasonColor).withComment("季节植被颜色"));
//
//}