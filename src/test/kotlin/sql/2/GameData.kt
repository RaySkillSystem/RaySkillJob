package me.skymc.spiralabyss.game

import me.skymc.spiralabyss.SpiralAbyss
import org.bukkit.entity.Player
import java.util.concurrent.ConcurrentHashMap

/**
 * SpiralAbyss
 * me.skymc.spiralabyss.game.GameData
 *
 * @author 坏黑
 * @since 2023/3/29 19:18
 */
class GameData(val player: Player) {

    val games = ConcurrentHashMap<String, GameHistory>()

    init {
        games += SpiralAbyss.storages.get(player.uniqueId).associateBy { it.game }
    }

    fun getGameHistory(game: String): GameHistory {
        return games.getOrPut(game) { GameHistory(player.uniqueId, game, 0, 0, 0) }
    }
}