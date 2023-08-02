package me.skymc.furniture

import taboolib.expansion.persistentContainer
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * SpiralAbyss
 * me.skymc.furniture.Storages
 *
 * @author 坏黑
 * @since 2023/3/13 14:32
 */
class Storages {

    /** 数据库容器 */
    val container = persistentContainer { new<PlayerFurniture>("player_furniture") }

    /** 线程池 */
    val pool: ExecutorService = Executors.newFixedThreadPool(16)

    /** 获取数据 */
    fun get(username: UUID): List<PlayerFurniture> {
        return container["player_furniture"].find(username)
    }

    /** 写入数据 */
    fun update(furniture: PlayerFurniture) {
        pool.submit { container["player_furniture"].updateByKey(furniture) }
    }

    fun close() {
        container.close()
    }
}