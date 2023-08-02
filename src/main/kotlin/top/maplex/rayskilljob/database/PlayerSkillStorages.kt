package top.maplex.rayskilljob.database

import taboolib.expansion.db
import taboolib.expansion.persistentContainer
import top.maplex.rayskilljob.data.PlayerSkillData
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PlayerSkillStorages {

    val container by lazy {
        persistentContainer(db()) { new<PlayerSkillData>("rss_player_skill") }
    }

    /** 线程池 */
    val pool: ExecutorService = Executors.newFixedThreadPool(16)

    /** 获取数据 */
    fun get(uuid: UUID): PlayerSkillData? {
        return container["rss_player_skill"].findOne(uuid) { "uuid" eq uuid }
    }

    /** 写入数据 */
    fun update(furniture: PlayerSkillData) {
        pool.submit {
            container["rss_player_skill"].update(furniture)
        }
    }

    fun close() {
        container.close()
    }
}
