package moldas.professions;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class GlobalListeners implements Listener {

    PlayerDataHandler playersData;

    public GlobalListeners(PlayerDataHandler _playerData) {
        playersData = _playerData;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        //TODO Read logged in player data
    }

    @EventHandler
    public void onEnter(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        if(playersData.addPlayer(playerUUID, player.getName())) {
            System.out.println(ChatColor.YELLOW + "" + ChatColor.BOLD + "(*)" +
                    ChatColor.RESET + player.getName() + " entered to your server, a newbie here!");
            player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "(!)" +
                    ChatColor.RESET + " Welcome, " + player.getName() + ", please choose your professions using command...");
        }

        //TODO Changing players stats

        //example of set logged in player stat from hash table
        //setting only stats that can be set by existing methods for object Player
        PlayerData currentPlayerStats = playersData.getPlayer(playerUUID);

        playersData.playerUpdate(playerUUID, currentPlayerStats);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        //TODO Save quiting player data to database and deleting from hashmap

        playersData.deletePlayer(playerUUID);
    }
}
