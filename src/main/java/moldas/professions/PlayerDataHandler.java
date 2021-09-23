package moldas.professions;

import org.bukkit.Bukkit;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class PlayerDataHandler {
    private HashMap <UUID, PlayerData> players = new HashMap<>();

    //Return Hashmap of all Players
    public HashMap <UUID, PlayerData> getAllPlayers() { return players; }

    //Return Player by its UUID
    public PlayerData getPlayer(UUID playerUUID) { return players.get(playerUUID); }

    public boolean playerExist(UUID playerUUID) { return players.containsKey(playerUUID); }

    //Add new unique Player to Hashmap, if player added return 'true'
    public boolean addPlayer(UUID playerUUID, String playerName) {
        if(!players.containsKey(playerUUID)) {
            PlayerData newPlayer = new PlayerData(playerName);

            players.put(playerUUID, newPlayer);
            playerUpdate(playerUUID, newPlayer);
            return true;
        }

        return false;
    }

    public void playerUpdate(UUID playerUUID, PlayerData playerData) {
        //TODO All stats needed to be updated here whenever player gets upgraded his stats
        players.replace(playerUUID, playerData);
        Player player = Bukkit.getPlayer(playerData.playerName);

        player.setHealthScale(playerData.health);
        player.setWalkSpeed(playerData.speed);
    }
}
