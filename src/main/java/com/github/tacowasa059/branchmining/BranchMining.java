package com.github.tacowasa059.branchmining;

import com.github.tacowasa059.branchmining.commands.*;
import com.github.tacowasa059.branchmining.events.BlockBreak;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class BranchMining extends JavaPlugin {
    private boolean execute=false;
    private double passed_time=0.0;

    private int hour;
    private int minute;
    private int second;

    public Schedule schedule;
    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("BM-on").setExecutor(new PluginOn(this));
        getCommand("BM-off").setExecutor(new PluginOff(this));
        getCommand("BM-reset").setExecutor(new Reset(this));
        getCommand("BM-Count").setExecutor(new ShowCount());
        getCommand("BM-Time").setExecutor(new ShowTime(this));
        getCommand("BM-getTools").setExecutor(new GetTools());
        getCommand("BM-setTimeLimit").setExecutor(new SetTimeLimit(this));
        this.schedule=new Schedule(this);
        getCommand("BM-ClearScoreBoard").setExecutor(new ClearScoreBoard(this));
        getCommand("BM-SetLoc").setExecutor(new SetLoc());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public void start(){
        if(execute==false){
            execute=true;
        }
    }
    public void stop(){
        if(execute==true){
            execute=false;
        }
    }
    public boolean getExecute(){
        return this.execute;
    }
    public void set_time(double t){
        this.passed_time=t;
        this.hour=(int)(this.get_time()/3600.0);
        this.minute=(int)((this.get_time()-hour*3600)/60.0);
        this.second=(int)((this.get_time()-hour*3600-60*minute));
    }
    public double get_time(){
        return this.passed_time;
    }
    public void show_time(){
        this.hour=(int)(this.get_time()/3600.0);
        this.minute=(int)((this.get_time()-hour*3600)/60.0);
        this.second=(int)((this.get_time()-hour*3600-60*minute));
        Bukkit.broadcastMessage(ChatColor.RED+String.valueOf(this.hour)+"時間"+String.valueOf(minute)+"分"+String.valueOf(second)+"秒経過");
    }
    public void show_time(Player player){
        this.hour=(int)(this.get_time()/3600.0);
        this.minute=(int)((this.get_time()-hour*3600)/60.0);
        this.second=(int)((this.get_time()-hour*3600-60*minute));
        player.sendMessage(ChatColor.GREEN+String.valueOf(hour)+"時間"+String.valueOf(minute)+"分"+String.valueOf(second)+"秒経過");
    }
    public void calc_time(){
        this.hour=(int)(this.get_time()/3600.0);
        this.minute=(int)((this.get_time()-hour*3600)/60.0);
        this.second=(int)((this.get_time()-hour*3600-60*minute));
    }
    public int getHour(){
        return this.hour;
    }
    public int getMinute(){
        return this.minute;
    }
    public int getSecond(){
        return this.second;
    }
    public void resetTime(){
        this.passed_time=0.0;
        this.hour=0;
        this.minute=0;
        this.second=0;
    }
}
