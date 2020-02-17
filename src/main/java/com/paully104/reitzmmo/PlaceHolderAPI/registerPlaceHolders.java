/*    */ package com.paully104.reitzmmo.PlaceHolderAPI;
/*    */ 
/*    */ import com.paully104.reitzmmo.ConfigFiles.API;
/*    */ import me.clip.placeholderapi.PlaceholderAPI;
/*    */ import me.clip.placeholderapi.PlaceholderHook;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class registerPlaceHolders
/*    */ {
/*    */   public static void registerPlaceHoldersReitzMMO() {
/* 13 */     PlaceholderAPI.registerPlaceholderHook("ReitzMMO", new PlaceholderHook()
/*    */         {
/*    */           public String onRequest(OfflinePlayer p, String params) {
/* 16 */             if (p != null && p.isOnline())
/*    */             {
/* 18 */               return onPlaceholderRequest(p.getPlayer(), params);
/*    */             }
/* 20 */             return null;
/*    */           }
/*    */ 
/*    */           
/*    */           public String onPlaceholderRequest(Player p, String params) {
/* 25 */             if (p == null)
/*    */             {
/* 27 */               return null;
/*    */             }
/*    */             
/* 30 */             if (params.equalsIgnoreCase("Level"))
/*    */             {
/* 32 */               return API.getPlayerDataFromAPI(p, "Level") + "";
/*    */             }
/* 34 */             if (params.equalsIgnoreCase("Attack"))
/*    */             {
/* 36 */               return API.getPlayerDataFromAPI(p, "Attack") + "";
/*    */             }
/* 38 */             if (params.equalsIgnoreCase("Health"))
/*    */             {
/* 40 */               return API.getPlayerDataFromAPI(p, "Health") + "";
/*    */             }
/* 42 */             if (params.equalsIgnoreCase("Combat-EXP"))
/*    */             {
/* 44 */               return API.getPlayerDataFromAPI(p, "Combat-EXP") + "";
/*    */             }
/* 46 */             if (params.equalsIgnoreCase("DisplayName"))
/*    */             {
/* 48 */               return p.getDisplayName();
/*    */             }
/*    */             
/* 51 */             return null;
/*    */           }
/*    */         });
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\PlaceHolderAPI\registerPlaceHolders.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */