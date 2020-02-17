/*     */ package com.paully104.reitzmmo.Metrics;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.lang.reflect.Method;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.logging.Level;
/*     */ import java.util.zip.GZIPOutputStream;
/*     */ import javax.net.ssl.HttpsURLConnection;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.RegisteredServiceProvider;
/*     */ import org.bukkit.plugin.ServicePriority;
/*     */ 
/*     */ public class Metrics {
/*     */   static {
/*  33 */     if (System.getProperty("bstats.relocatecheck") == null || !System.getProperty("bstats.relocatecheck").equals("false")) {
/*     */       
/*  35 */       String defaultPackage = new String(new byte[] { 111, 114, 103, 46, 98, 115, 116, 97, 116, 115, 46, 98, 117, 107, 107, 105, 116 });
/*     */       
/*  37 */       String examplePackage = new String(new byte[] { 121, 111, 117, 114, 46, 112, 97, 99, 107, 97, 103, 101 });
/*     */       
/*  39 */       if (Metrics.class.getPackage().getName().equals(defaultPackage) || Metrics.class.getPackage().getName().equals(examplePackage)) {
/*  40 */         throw new IllegalStateException("bStats Metrics class has not been relocated correctly!");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int B_STATS_VERSION = 1;
/*     */ 
/*     */   
/*     */   private static final String URL = "https://bStats.org/submitData/bukkit";
/*     */ 
/*     */   
/*     */   private boolean enabled;
/*     */ 
/*     */   
/*     */   private static boolean logFailedRequests;
/*     */ 
/*     */   
/*     */   private static boolean logSentData;
/*     */ 
/*     */   
/*     */   private static boolean logResponseStatusText;
/*     */ 
/*     */   
/*     */   private static String serverUUID;
/*     */ 
/*     */   
/*     */   private final Plugin plugin;
/*     */   
/*  70 */   private final List<CustomChart> charts = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Metrics(Plugin plugin) {
/*  78 */     if (plugin == null) {
/*  79 */       throw new IllegalArgumentException("Plugin cannot be null!");
/*     */     }
/*  81 */     this.plugin = plugin;
/*     */ 
/*     */     
/*  84 */     File bStatsFolder = new File(plugin.getDataFolder().getParentFile(), "bStats");
/*  85 */     File configFile = new File(bStatsFolder, "config.yml");
/*  86 */     YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
/*     */ 
/*     */     
/*  89 */     if (!config.isSet("serverUuid")) {
/*     */ 
/*     */       
/*  92 */       config.addDefault("enabled", Boolean.valueOf(true));
/*     */       
/*  94 */       config.addDefault("serverUuid", UUID.randomUUID().toString());
/*     */       
/*  96 */       config.addDefault("logFailedRequests", Boolean.valueOf(false));
/*     */       
/*  98 */       config.addDefault("logSentData", Boolean.valueOf(false));
/*     */       
/* 100 */       config.addDefault("logResponseStatusText", Boolean.valueOf(false));
/*     */ 
/*     */       
/* 103 */       config.options().header("bStats collects some data for plugin authors like how many servers are using their plugins.\nTo honor their work, you should not disable it.\nThis has nearly no effect on the server performance!\nCheck out https://bStats.org/ to learn more :)")
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 108 */         .copyDefaults(true);
/*     */       try {
/* 110 */         config.save(configFile);
/* 111 */       } catch (IOException iOException) {}
/*     */     } 
/*     */ 
/*     */     
/* 115 */     this.enabled = config.getBoolean("enabled", true);
/* 116 */     serverUUID = config.getString("serverUuid");
/* 117 */     logFailedRequests = config.getBoolean("logFailedRequests", false);
/* 118 */     logSentData = config.getBoolean("logSentData", false);
/* 119 */     logResponseStatusText = config.getBoolean("logResponseStatusText", false);
/*     */     
/* 121 */     if (this.enabled) {
/* 122 */       boolean found = false;
/*     */       
/* 124 */       for (Class<?> service : (Iterable<Class<?>>)Bukkit.getServicesManager().getKnownServices()) {
/*     */         try {
/* 126 */           service.getField("B_STATS_VERSION");
/* 127 */           found = true;
/*     */           break;
/* 129 */         } catch (NoSuchFieldException noSuchFieldException) {}
/*     */       } 
/*     */       
/* 132 */       Bukkit.getServicesManager().register(Metrics.class, this, plugin, ServicePriority.Normal);
/* 133 */       if (!found)
/*     */       {
/* 135 */         startSubmitting();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/* 146 */     return this.enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCustomChart(CustomChart chart) {
/* 155 */     if (chart == null) {
/* 156 */       throw new IllegalArgumentException("Chart cannot be null!");
/*     */     }
/* 158 */     this.charts.add(chart);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void startSubmitting() {
/* 165 */     final Timer timer = new Timer(true);
/* 166 */     timer.scheduleAtFixedRate(new TimerTask()
/*     */         {
/*     */           public void run() {
/* 169 */             if (!Metrics.this.plugin.isEnabled()) {
/* 170 */               timer.cancel();
/*     */               
/*     */               return;
/*     */             } 
/*     */             
/* 175 */             Bukkit.getScheduler().runTask(Metrics.this.plugin, () -> Metrics.this.submitData());
/*     */           }
/*     */         }300000L, 1800000L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonObject getPluginData() {
/* 190 */     JsonObject data = new JsonObject();
/*     */     
/* 192 */     String pluginName = this.plugin.getDescription().getName();
/* 193 */     String pluginVersion = this.plugin.getDescription().getVersion();
/*     */     
/* 195 */     data.addProperty("pluginName", pluginName);
/* 196 */     data.addProperty("pluginVersion", pluginVersion);
/* 197 */     JsonArray customCharts = new JsonArray();
/* 198 */     for (CustomChart customChart : this.charts) {
/*     */       
/* 200 */       JsonObject chart = customChart.getRequestJsonObject();
/* 201 */       if (chart == null) {
/*     */         continue;
/*     */       }
/* 204 */       customCharts.add((JsonElement)chart);
/*     */     } 
/* 206 */     data.add("customCharts", (JsonElement)customCharts);
/*     */     
/* 208 */     return data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JsonObject getServerData() {
/*     */     int playerAmount;
/*     */     try {
/* 222 */       Method onlinePlayersMethod = Class.forName("org.bukkit.Server").getMethod("getOnlinePlayers", new Class[0]);
/*     */ 
/*     */       
/* 225 */       playerAmount = onlinePlayersMethod.getReturnType().equals(Collection.class) ? ((Collection)onlinePlayersMethod.invoke(Bukkit.getServer(), new Object[0])).size() : ((Player[])onlinePlayersMethod.invoke(Bukkit.getServer(), new Object[0])).length;
/* 226 */     } catch (Exception e) {
/* 227 */       playerAmount = Bukkit.getOnlinePlayers().size();
/*     */     } 
/* 229 */     int onlineMode = Bukkit.getOnlineMode() ? 1 : 0;
/* 230 */     String bukkitVersion = Bukkit.getVersion();
/* 231 */     String bukkitName = Bukkit.getName();
/*     */ 
/*     */     
/* 234 */     String javaVersion = System.getProperty("java.version");
/* 235 */     String osName = System.getProperty("os.name");
/* 236 */     String osArch = System.getProperty("os.arch");
/* 237 */     String osVersion = System.getProperty("os.version");
/* 238 */     int coreCount = Runtime.getRuntime().availableProcessors();
/*     */     
/* 240 */     JsonObject data = new JsonObject();
/*     */     
/* 242 */     data.addProperty("serverUUID", serverUUID);
/*     */     
/* 244 */     data.addProperty("playerAmount", Integer.valueOf(playerAmount));
/* 245 */     data.addProperty("onlineMode", Integer.valueOf(onlineMode));
/* 246 */     data.addProperty("bukkitVersion", bukkitVersion);
/* 247 */     data.addProperty("bukkitName", bukkitName);
/*     */     
/* 249 */     data.addProperty("javaVersion", javaVersion);
/* 250 */     data.addProperty("osName", osName);
/* 251 */     data.addProperty("osArch", osArch);
/* 252 */     data.addProperty("osVersion", osVersion);
/* 253 */     data.addProperty("coreCount", Integer.valueOf(coreCount));
/*     */     
/* 255 */     return data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void submitData() {
/* 262 */     JsonObject data = getServerData();
/*     */     
/* 264 */     JsonArray pluginData = new JsonArray();
/*     */     
/* 266 */     for (Class<?> service : (Iterable<Class<?>>)Bukkit.getServicesManager().getKnownServices()) {
/*     */       try {
/* 268 */         service.getField("B_STATS_VERSION");
/*     */         
/* 270 */         for (RegisteredServiceProvider<?> provider : (Iterable<RegisteredServiceProvider<?>>)Bukkit.getServicesManager().getRegistrations(service)) {
/*     */           try {
/* 272 */             Object plugin = provider.getService().getMethod("getPluginData", new Class[0]).invoke(provider.getProvider(), new Object[0]);
/* 273 */             if (plugin instanceof JsonObject) {
/* 274 */               pluginData.add((JsonElement)plugin); continue;
/*     */             } 
/*     */             try {
/* 277 */               Class<?> jsonObjectJsonSimple = Class.forName("org.json.simple.JSONObject");
/* 278 */               if (plugin.getClass().isAssignableFrom(jsonObjectJsonSimple)) {
/* 279 */                 Method jsonStringGetter = jsonObjectJsonSimple.getDeclaredMethod("toJSONString", new Class[0]);
/* 280 */                 jsonStringGetter.setAccessible(true);
/* 281 */                 String jsonString = (String)jsonStringGetter.invoke(plugin, new Object[0]);
/* 282 */                 JsonObject object = (new JsonParser()).parse(jsonString).getAsJsonObject();
/* 283 */                 pluginData.add((JsonElement)object);
/*     */               } 
/* 285 */             } catch (ClassNotFoundException e) {
/*     */               
/* 287 */               if (logFailedRequests) {
/* 288 */                 this.plugin.getLogger().log(Level.SEVERE, "Encountered unexpected exception", e);
/*     */               }
/*     */             }
/*     */           
/*     */           }
/* 293 */           catch (NullPointerException|NoSuchMethodException|IllegalAccessException|java.lang.reflect.InvocationTargetException nullPointerException) {}
/*     */         } 
/* 295 */       } catch (NoSuchFieldException noSuchFieldException) {}
/*     */     } 
/*     */     
/* 298 */     data.add("plugins", (JsonElement)pluginData);
/*     */ 
/*     */     
/* 301 */     (new Thread(() -> {
/*     */           
/*     */           try {
/*     */             sendData(this.plugin, data);
/* 305 */           } catch (Exception e) {
/*     */             
/*     */             if (logFailedRequests) {
/*     */               this.plugin.getLogger().log(Level.WARNING, "Could not submit plugin stats of " + this.plugin.getName(), e);
/*     */             }
/*     */           } 
/* 311 */         })).start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void sendData(Plugin plugin, JsonObject data) throws Exception {
/* 322 */     if (data == null) {
/* 323 */       throw new IllegalArgumentException("Data cannot be null!");
/*     */     }
/* 325 */     if (Bukkit.isPrimaryThread()) {
/* 326 */       throw new IllegalAccessException("This method must not be called from the main thread!");
/*     */     }
/* 328 */     if (logSentData) {
/* 329 */       plugin.getLogger().info("Sending data to bStats: " + data.toString());
/*     */     }
/* 331 */     HttpsURLConnection connection = (HttpsURLConnection)(new URL("https://bStats.org/submitData/bukkit")).openConnection();
/*     */ 
/*     */     
/* 334 */     byte[] compressedData = compress(data.toString());
/*     */ 
/*     */     
/* 337 */     connection.setRequestMethod("POST");
/* 338 */     connection.addRequestProperty("Accept", "application/json");
/* 339 */     connection.addRequestProperty("Connection", "close");
/* 340 */     connection.addRequestProperty("Content-Encoding", "gzip");
/* 341 */     connection.addRequestProperty("Content-Length", String.valueOf(compressedData.length));
/* 342 */     connection.setRequestProperty("Content-Type", "application/json");
/* 343 */     connection.setRequestProperty("User-Agent", "MC-Server/1");
/*     */ 
/*     */     
/* 346 */     connection.setDoOutput(true);
/* 347 */     DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
/* 348 */     outputStream.write(compressedData);
/* 349 */     outputStream.flush();
/* 350 */     outputStream.close();
/*     */     
/* 352 */     InputStream inputStream = connection.getInputStream();
/* 353 */     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
/*     */     
/* 355 */     StringBuilder builder = new StringBuilder();
/*     */     String line;
/* 357 */     while ((line = bufferedReader.readLine()) != null) {
/* 358 */       builder.append(line);
/*     */     }
/* 360 */     bufferedReader.close();
/* 361 */     if (logResponseStatusText) {
/* 362 */       plugin.getLogger().info("Sent data to bStats and received response: " + builder.toString());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static byte[] compress(String str) throws IOException {
/* 374 */     if (str == null) {
/* 375 */       return null;
/*     */     }
/* 377 */     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
/* 378 */     GZIPOutputStream gzip = new GZIPOutputStream(outputStream);
/* 379 */     gzip.write(str.getBytes(StandardCharsets.UTF_8));
/* 380 */     gzip.close();
/* 381 */     return outputStream.toByteArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static abstract class CustomChart
/*     */   {
/*     */     final String chartId;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     CustomChart(String chartId) {
/* 398 */       if (chartId == null || chartId.isEmpty()) {
/* 399 */         throw new IllegalArgumentException("ChartId cannot be null or empty!");
/*     */       }
/* 401 */       this.chartId = chartId;
/*     */     }
/*     */     
/*     */     private JsonObject getRequestJsonObject() {
/* 405 */       JsonObject chart = new JsonObject();
/* 406 */       chart.addProperty("chartId", this.chartId);
/*     */       try {
/* 408 */         JsonObject data = getChartData();
/* 409 */         if (data == null)
/*     */         {
/* 411 */           return null;
/*     */         }
/* 413 */         chart.add("data", (JsonElement)data);
/* 414 */       } catch (Throwable t) {
/* 415 */         if (Metrics.logFailedRequests) {
/* 416 */           Bukkit.getLogger().log(Level.WARNING, "Failed to get data for custom chart with id " + this.chartId, t);
/*     */         }
/* 418 */         return null;
/*     */       } 
/* 420 */       return chart;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected abstract JsonObject getChartData() throws Exception;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class SimplePie
/*     */     extends CustomChart
/*     */   {
/*     */     private final Callable<String> callable;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SimplePie(String chartId, Callable<String> callable) {
/* 441 */       super(chartId);
/* 442 */       this.callable = callable;
/*     */     }
/*     */ 
/*     */     
/*     */     protected JsonObject getChartData() throws Exception {
/* 447 */       JsonObject data = new JsonObject();
/* 448 */       String value = this.callable.call();
/* 449 */       if (value == null || value.isEmpty())
/*     */       {
/* 451 */         return null;
/*     */       }
/* 453 */       data.addProperty("value", value);
/* 454 */       return data;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class AdvancedPie
/*     */     extends CustomChart
/*     */   {
/*     */     private final Callable<Map<String, Integer>> callable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public AdvancedPie(String chartId, Callable<Map<String, Integer>> callable) {
/* 472 */       super(chartId);
/* 473 */       this.callable = callable;
/*     */     }
/*     */ 
/*     */     
/*     */     protected JsonObject getChartData() throws Exception {
/* 478 */       JsonObject data = new JsonObject();
/* 479 */       JsonObject values = new JsonObject();
/* 480 */       Map<String, Integer> map = this.callable.call();
/* 481 */       if (map == null || map.isEmpty())
/*     */       {
/* 483 */         return null;
/*     */       }
/* 485 */       boolean allSkipped = true;
/* 486 */       for (Map.Entry<String, Integer> entry : map.entrySet()) {
/* 487 */         if (((Integer)entry.getValue()).intValue() == 0) {
/*     */           continue;
/*     */         }
/* 490 */         allSkipped = false;
/* 491 */         values.addProperty(entry.getKey(), entry.getValue());
/*     */       } 
/* 493 */       if (allSkipped)
/*     */       {
/* 495 */         return null;
/*     */       }
/* 497 */       data.add("values", (JsonElement)values);
/* 498 */       return data;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class DrilldownPie
/*     */     extends CustomChart
/*     */   {
/*     */     private final Callable<Map<String, Map<String, Integer>>> callable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DrilldownPie(String chartId, Callable<Map<String, Map<String, Integer>>> callable) {
/* 516 */       super(chartId);
/* 517 */       this.callable = callable;
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonObject getChartData() throws Exception {
/* 522 */       JsonObject data = new JsonObject();
/* 523 */       JsonObject values = new JsonObject();
/* 524 */       Map<String, Map<String, Integer>> map = this.callable.call();
/* 525 */       if (map == null || map.isEmpty())
/*     */       {
/* 527 */         return null;
/*     */       }
/* 529 */       boolean reallyAllSkipped = true;
/* 530 */       for (Map.Entry<String, Map<String, Integer>> entryValues : map.entrySet()) {
/* 531 */         JsonObject value = new JsonObject();
/* 532 */         boolean allSkipped = true;
/* 533 */         for (Map.Entry<String, Integer> valueEntry : (Iterable<Map.Entry<String, Integer>>)((Map)map.get(entryValues.getKey())).entrySet()) {
/* 534 */           value.addProperty(valueEntry.getKey(), valueEntry.getValue());
/* 535 */           allSkipped = false;
/*     */         } 
/* 537 */         if (!allSkipped) {
/* 538 */           reallyAllSkipped = false;
/* 539 */           values.add(entryValues.getKey(), (JsonElement)value);
/*     */         } 
/*     */       } 
/* 542 */       if (reallyAllSkipped)
/*     */       {
/* 544 */         return null;
/*     */       }
/* 546 */       data.add("values", (JsonElement)values);
/* 547 */       return data;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class SingleLineChart
/*     */     extends CustomChart
/*     */   {
/*     */     private final Callable<Integer> callable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SingleLineChart(String chartId, Callable<Integer> callable) {
/* 565 */       super(chartId);
/* 566 */       this.callable = callable;
/*     */     }
/*     */ 
/*     */     
/*     */     protected JsonObject getChartData() throws Exception {
/* 571 */       JsonObject data = new JsonObject();
/* 572 */       int value = ((Integer)this.callable.call()).intValue();
/* 573 */       if (value == 0)
/*     */       {
/* 575 */         return null;
/*     */       }
/* 577 */       data.addProperty("value", Integer.valueOf(value));
/* 578 */       return data;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class MultiLineChart
/*     */     extends CustomChart
/*     */   {
/*     */     private final Callable<Map<String, Integer>> callable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public MultiLineChart(String chartId, Callable<Map<String, Integer>> callable) {
/* 597 */       super(chartId);
/* 598 */       this.callable = callable;
/*     */     }
/*     */ 
/*     */     
/*     */     protected JsonObject getChartData() throws Exception {
/* 603 */       JsonObject data = new JsonObject();
/* 604 */       JsonObject values = new JsonObject();
/* 605 */       Map<String, Integer> map = this.callable.call();
/* 606 */       if (map == null || map.isEmpty())
/*     */       {
/* 608 */         return null;
/*     */       }
/* 610 */       boolean allSkipped = true;
/* 611 */       for (Map.Entry<String, Integer> entry : map.entrySet()) {
/* 612 */         if (((Integer)entry.getValue()).intValue() == 0) {
/*     */           continue;
/*     */         }
/* 615 */         allSkipped = false;
/* 616 */         values.addProperty(entry.getKey(), entry.getValue());
/*     */       } 
/* 618 */       if (allSkipped)
/*     */       {
/* 620 */         return null;
/*     */       }
/* 622 */       data.add("values", (JsonElement)values);
/* 623 */       return data;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class SimpleBarChart
/*     */     extends CustomChart
/*     */   {
/*     */     private final Callable<Map<String, Integer>> callable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SimpleBarChart(String chartId, Callable<Map<String, Integer>> callable) {
/* 642 */       super(chartId);
/* 643 */       this.callable = callable;
/*     */     }
/*     */ 
/*     */     
/*     */     protected JsonObject getChartData() throws Exception {
/* 648 */       JsonObject data = new JsonObject();
/* 649 */       JsonObject values = new JsonObject();
/* 650 */       Map<String, Integer> map = this.callable.call();
/* 651 */       if (map == null || map.isEmpty())
/*     */       {
/* 653 */         return null;
/*     */       }
/* 655 */       for (Map.Entry<String, Integer> entry : map.entrySet()) {
/* 656 */         JsonArray categoryValues = new JsonArray();
/* 657 */         categoryValues.add(entry.getValue());
/* 658 */         values.add(entry.getKey(), (JsonElement)categoryValues);
/*     */       } 
/* 660 */       data.add("values", (JsonElement)values);
/* 661 */       return data;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class AdvancedBarChart
/*     */     extends CustomChart
/*     */   {
/*     */     private final Callable<Map<String, int[]>> callable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public AdvancedBarChart(String chartId, Callable<Map<String, int[]>> callable) {
/* 680 */       super(chartId);
/* 681 */       this.callable = callable;
/*     */     }
/*     */ 
/*     */     
/*     */     protected JsonObject getChartData() throws Exception {
/* 686 */       JsonObject data = new JsonObject();
/* 687 */       JsonObject values = new JsonObject();
/* 688 */       Map<String, int[]> map = this.callable.call();
/* 689 */       if (map == null || map.isEmpty())
/*     */       {
/* 691 */         return null;
/*     */       }
/* 693 */       boolean allSkipped = true;
/* 694 */       for (Map.Entry<String, int[]> entry : map.entrySet()) {
/* 695 */         if (((int[])entry.getValue()).length == 0) {
/*     */           continue;
/*     */         }
/* 698 */         allSkipped = false;
/* 699 */         JsonArray categoryValues = new JsonArray();
/* 700 */         for (int categoryValue : (int[])entry.getValue()) {
/* 701 */           categoryValues.add(Integer.valueOf(categoryValue));
/*     */         }
/* 703 */         values.add(entry.getKey(), (JsonElement)categoryValues);
/*     */       } 
/* 705 */       if (allSkipped)
/*     */       {
/* 707 */         return null;
/*     */       }
/* 709 */       data.add("values", (JsonElement)values);
/* 710 */       return data;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\Metrics\Metrics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */