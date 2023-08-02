package me.skymc.spiralabyss

import me.skymc.spiralabyss.game.GameHistory
import taboolib.expansion.*
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * SpiralAbyss
 * me.skymc.spiralabyss.Storages
 *
 * @author 坏黑
 * @since 2023/3/13 14:32
 */
class Storages {

    /** 数据库容器 */
    val container = persistentContainer { new<GameHistory>("history") }

    /** 线程池 */
    val pool: ExecutorService = Executors.newFixedThreadPool(16)

    /** 获取数据 */
    fun get(username: UUID): List<GameHistory> {
        return container["history"].find(username)
    }

    /** 获取数据 */
    fun getByGame(username: UUID, game: String): GameHistory? {
        return container["history"].findOne(username) { "game" eq game }
    }

    /** 写入数据 */
    fun update(history: GameHistory) {
        pool.submit { container["history"].updateByKey(history) }
    }

    /** 获取排行 */
    fun sortByDeepLevel(game: String, limit: Int = 10): List<GameHistory> {
        return container["history"].sortDescending("deep_level", limit) { "game" to game }
    }

    /** 获取排行 */
    fun sortByUseTime(game: String, limit: Int = 10): List<GameHistory> {
        return container["history"].sort("use_time", limit) { "game" to game }
    }

    fun close() {
        container.close()
    }
}