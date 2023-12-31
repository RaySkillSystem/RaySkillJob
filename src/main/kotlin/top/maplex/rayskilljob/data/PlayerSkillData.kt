package top.maplex.rayskilljob.data

import com.alibaba.fastjson2.JSONObject
import taboolib.expansion.Id
import taboolib.expansion.Key
import top.maplex.rayskilljob.database.RaySkillJobTable
import java.util.*

data class PlayerSkillData(
    @Id
    val name: String,
    @Key
    val uuid: UUID,
    var levelMap: JSONObject
) {

    fun update(): PlayerSkillData {
        RaySkillJobTable.skillData.update(this)
        return this
    }

}
