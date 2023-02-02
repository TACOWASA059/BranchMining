package com.github.tacowasa059.branchmining;

import com.github.tacowasa059.branchmining.utils.DataList;
import com.github.tacowasa059.branchmining.utils.DisplayNameList;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.UUID;

import static com.github.tacowasa059.branchmining.utils.DataList.time_count;
import static java.lang.Math.max;

public class Schedule {
    private final BranchMining branchMining;
    private HashMap<UUID,BossBar> hashMap;
    private int taskId;
    private HashMap<UUID,Scoreboard> scoreboard_map;
    private HashMap<UUID,Objective> objective_map;
    Schedule(BranchMining branchMining){
        this.branchMining=branchMining;
    }
    //タスク生成用
    public void enable(){
        this.hashMap=new HashMap<>();
        this.objective_map=new HashMap<>();
        this.scoreboard_map=new HashMap<>();
        //scoreboardの初期化
        InitializeScoreboard();
        taskId = branchMining.getServer().getScheduler().scheduleSyncRepeatingTask(branchMining, new Runnable(){
            @Override
            public void run() {
                //カウントを進める
                for (UUID uuid:time_count.keySet()){
                    Player onlinePlayer=Bukkit.getPlayer(uuid);
                    time_count.put(uuid,time_count.get(uuid)+1.0);
                    //そもそもIDが見つからないとき(多分オフラインのとき)
                    if (onlinePlayer==null){
                        if(time_count.get(uuid)>branchMining.getConfig().getDouble("Limit_Time")){
                            displayDropOut(uuid);
                        }
                        continue;
                    }
                    //以下boss_barの表示
                    if(onlinePlayer.isOnline()){
                        BossBar bar;
                        double time=max((branchMining.getConfig().getDouble("Limit_Time")-time_count.get(uuid))/branchMining.getConfig().getDouble("Limit_Time"),0.0);
                        if(hashMap.containsKey(uuid)){
                            bar = hashMap.get(uuid);
                            bar.setTitle(ChatColor.YELLOW + "残り時間:"+(int)(time* branchMining.getConfig().getDouble("Limit_Time")+1.0)+"秒");
                            bar.setProgress(time);
                            bar.setVisible(true);
                        }
                        else{
                            bar = Bukkit.createBossBar(ChatColor.YELLOW + "残り時間:"+(int)(time* branchMining.getConfig().getDouble("Limit_Time")+1.0)+"秒", BarColor.GREEN, BarStyle.SEGMENTED_20);
                            bar.setProgress(time);
                            bar.addPlayer(onlinePlayer);
                            bar.setVisible(true);
                            hashMap.put(uuid,bar);
                        }
                        if(time_count.get(uuid)>branchMining.getConfig().getDouble("Limit_Time")){
                            if(onlinePlayer.getGameMode()== GameMode.SURVIVAL){
                                onlinePlayer.setHealth(0.0);
                                onlinePlayer.setGameMode(GameMode.SPECTATOR);
                            }
                            displayDropOut(uuid);
                        }
                    }
                    else{
                        if(time_count.get(uuid)>branchMining.getConfig().getDouble("Limit_Time")){
                            displayDropOut(uuid);
                        }
                    }
                }
                updateScoreboard();
                branchMining.set_time(branchMining.get_time()+1.0);//全体の時間カウント
                //for(Player player:branchMining.getServer().getOnlinePlayers()){
                //    player.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent(ChatColor.GREEN+Integer.valueOf(branchMining.getHour()).toString()+" 時間 "+Integer.valueOf(branchMining.getMinute()).toString()+" 分 "+Integer.valueOf(branchMining.getSecond()).toString()+" 秒 経過"));
                //}
            }
        }, 0L, 20L);
    }
    //タスクキャンセル用
    public void disable(){
        branchMining.getServer().getScheduler().cancelTask(taskId);
        //removeしてclearする
        for(BossBar bar:this.hashMap.values()){
            bar.removeAll();
        }
        this.hashMap.clear();
    }
    //脱落者を表示する
    public void displayDropOut(UUID uuid){
        String display_name= DisplayNameList.name_list.get(uuid);
        Bukkit.broadcastMessage(ChatColor.RED+display_name+":脱落");
        if(time_count.containsKey(uuid)){
            time_count.remove(uuid);
        }
        if(hashMap.containsKey(uuid)){
            BossBar bar1=hashMap.get(uuid);
            bar1.removeAll();
            hashMap.remove(uuid);
        }
        branchMining.show_time();//全体時間を表示
    }
    //barの初期化
    public void InitializeBar(UUID uuid){
        BossBar bar;
        double time=max((branchMining.getConfig().getDouble("Limit_Time")-time_count.get(uuid))/branchMining.getConfig().getDouble("Limit_Time"),0.0);
        bar = Bukkit.createBossBar(ChatColor.YELLOW + "残り時間:"+(int)(time* branchMining.getConfig().getDouble("Limit_Time")+1.0)+"秒", BarColor.GREEN, BarStyle.SEGMENTED_20);
        bar.setProgress(time);
        Player onlinePlayer=Bukkit.getPlayer(uuid);
        bar.addPlayer(onlinePlayer);
        bar.setVisible(true);
        hashMap.put(uuid,bar);
    }
    //スコアボードの初期化
    public void InitializeScoreboard(){

        for(Player player:branchMining.getServer().getOnlinePlayers()){
            UUID uuid=player.getUniqueId();
            Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
            player.setScoreboard(scoreboard);
            Objective objective = scoreboard.registerNewObjective(player.getDisplayName(), "dummy", player.getDisplayName());
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            writeobject(objective,uuid);
            scoreboard_map.put(uuid,scoreboard);
            objective_map.put(uuid,objective);

        }

    }
    public void updateScoreboard(){
        for(Player player:branchMining.getServer().getOnlinePlayers()){
            UUID uuid=player.getUniqueId();
            if(!scoreboard_map.containsKey(uuid)||!objective_map.containsKey(uuid)){
                continue;
            }
            objective_map.get(uuid).unregister();
            Scoreboard scoreboard = scoreboard_map.get(uuid);
            Objective objective = scoreboard.registerNewObjective(player.getDisplayName(), "dummy", player.getDisplayName());
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            writeobject(objective,uuid);

            objective_map.put(uuid,objective);
        }
    }
    //表示する
    private void writeobject(Objective objective,UUID uuid){
        int i=0;
        String str=ChatColor.RED+Integer.valueOf(branchMining.getHour()).toString()+"時間"+Integer.valueOf(branchMining.getMinute()).toString()+"分"+Integer.valueOf(branchMining.getSecond()).toString()+"秒";
        objective.getScore(str).setScore(0);
        if(DataList.laps.containsKey(uuid)){
            objective.getScore(ChatColor.AQUA+"ラピスラズリ鉱石数 : "+DataList.laps.get(uuid)).setScore(i);
        }
        if(DataList.diamond.containsKey(uuid)){
            objective.getScore(ChatColor.AQUA+"ダイヤモンド鉱石数 : "+DataList.diamond.get(uuid)).setScore(i);
        }
        if(DataList.red_stone.containsKey(uuid)){
            objective.getScore(ChatColor.AQUA+"レッドストーン鉱石数 : "+DataList.red_stone.get(uuid)).setScore(i);
        }
        if(DataList.emerald.containsKey(uuid)){
            objective.getScore(ChatColor.AQUA+"エメラルド鉱石数 : "+DataList.emerald.get(uuid)).setScore(i);
        }if(DataList.gold.containsKey(uuid)){
            objective.getScore(ChatColor.AQUA+"金鉱石数 : "+DataList.gold.get(uuid)).setScore(i);
        }
        if(DataList.ancient_debris.containsKey(uuid)){
            objective.getScore(ChatColor.AQUA+"古代の残骸数 : "+DataList.ancient_debris.get(uuid)).setScore(i);
        }
        if(DataList.sum.containsKey(uuid)){
            objective.getScore(ChatColor.AQUA+"総ブロック数 : "+DataList.sum.get(uuid)).setScore(i);
        }
        if(DataList.coal.containsKey(uuid)){
            objective.getScore(ChatColor.AQUA+"石炭鉱石数 : "+DataList.coal.get(uuid)).setScore(i);
        }
        if(DataList.iron.containsKey(uuid)){
            objective.getScore(ChatColor.AQUA+"鉄鉱石数 : "+DataList.iron.get(uuid)).setScore(i);
        }

    }
    public void clearScoreboard(){
        this.scoreboard_map.clear();
        this.objective_map.clear();
        //scoreboardを消す
        for(Player player:branchMining.getServer().getOnlinePlayers()){
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        }
    }
}
