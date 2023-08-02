package top.maplex.rayskilljob.data

import org.bukkit.entity.Player
import taboolib.common.platform.function.submitAsync
import top.maplex.rayskilljob.database.RaySkillJobTable

data class PlayerBaseData(val player: Player) {

    var skillDataTable: PlayerSkillData? = null

    /** 数据是否加载 */
    var isLoaded = false
        private set

    init {
        submitAsync {
            skillDataTable = RaySkillJobTable.skillData.get(player.uniqueId)
            init()
            isLoaded = true
        }
    }

    fun init() {

    }
}
