package com.github.tacowasa059.branchmining.events;

import com.github.tacowasa059.branchmining.BranchMining;
import com.github.tacowasa059.branchmining.utils.TimeCount;
import com.github.tacowasa059.branchmining.utils.UpdateValue;
import com.github.tacowasa059.branchmining.utils.BlockCountList;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.UUID;

import static com.github.tacowasa059.branchmining.utils.DataList.*;

public class BlockBreak implements Listener {
    private final BranchMining branchMining;
    public BlockBreak(BranchMining branchMining){
        this.branchMining=branchMining;
    }
    @EventHandler
    void onBlockBreak(BlockBreakEvent e){
        Player player=e.getPlayer();
        if(player==null){return;}
        UUID uuid=player.getUniqueId();
        Block block=e.getBlock();
        //System.out.println("aa");
        if(BlockCountList.materials.contains(block.getType())){
            //System.out.println("ab");
            UpdateValue.update(sum,uuid);
            TimeCount.ResetValue(uuid);
        }

        switch(block.getType()){
            case COAL_ORE:
                UpdateValue.update(coal,uuid);
                break;
            case IRON_ORE:
                UpdateValue.update(iron,uuid);
                break;
            case LAPIS_ORE:
                UpdateValue.update(laps,uuid);
                break;
            case DIAMOND_ORE:
                UpdateValue.update(diamond,uuid);
                break;
            case GOLD_ORE:
                UpdateValue.update(gold,uuid);
                break;
            case EMERALD_ORE:
                UpdateValue.update(emerald,uuid);
                break;
            case REDSTONE_ORE:
                UpdateValue.update(red_stone,uuid);
                break;
            case ANCIENT_DEBRIS:
                UpdateValue.update(ancient_debris,uuid);
                break;
        }
        branchMining.schedule.updateScoreboard();
    }
}
