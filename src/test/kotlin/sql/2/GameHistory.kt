package me.skymc.spiralabyss.game

import me.skymc.spiralabyss.SpiralAbyss
import taboolib.expansion.BundleMap
import taboolib.expansion.Id
import taboolib.expansion.Key
import taboolib.expansion.Length
import java.util.*

/**
 * SpiralAbyss
 * me.skymc.spiralabyss.GameHistory
 *
 * @author 坏黑
 * @since 2023/3/13 14:32
 */
data class GameHistory(
    @Id
    val username: UUID,
    @Key
    @Length(32)
    val game: String,
    var playTimes: Int,
    var deepLevel: Int,
    var useTime: Long,
) {

    /** 更新数据 */
    fun update() {
        SpiralAbyss.storages.update(this)
    }

    companion object {

        @JvmStatic
        fun wrap(map: BundleMap): GameHistory {
            return GameHistory(map["username"], map["game"], map["play_times"], map["deep_level"], map["use_time"])
        }
    }
}