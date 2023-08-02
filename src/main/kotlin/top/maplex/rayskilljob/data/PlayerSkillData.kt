package top.maplex.rayskilljob.data

import com.alibaba.fastjson2.JSONObject
import taboolib.expansion.Id
import taboolib.expansion.Key
import taboolib.module.configuration.Configuration
import top.maplex.rayskilljob.database.RaySkillJobTable
import java.util.UUID

data class PlayerSkillData(
    @Id
    val name: String,
    @Key
    val uuid: UUID,
    val levelMap: String
) {

    fun update(): PlayerSkillData {
        RaySkillJobTable.skillData.update(this)
        return this
    }

}
