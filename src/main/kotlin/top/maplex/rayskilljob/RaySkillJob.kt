package top.maplex.rayskilljob

import com.alibaba.fastjson2.JSONObject
import org.bukkit.event.player.PlayerJoinEvent
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.Plugin
import taboolib.common.platform.Schedule
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.info
import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigFile
import taboolib.module.configuration.Configuration
import taboolib.module.configuration.Type
import top.maplex.rayskilljob.data.PlayerBaseData
import top.maplex.rayskilljob.data.PlayerSkillData
import top.maplex.rayskilljob.database.RaySkillJobTable
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap


object RaySkillJob : Plugin() {

    val players = ConcurrentHashMap<UUID, PlayerBaseData>()

    @Config
    lateinit var config: ConfigFile

    override fun onEnable() {
        info("Hook by RaySkillSystem - Job")
    }

    @SubscribeEvent
    fun join(event: PlayerJoinEvent) {
        val newConfig = JSONObject.of()
        newConfig["测试技能"] = 1
        newConfig["猛虎下山"] = 10
        newConfig["AAAZZZ"] = 7
        RaySkillJobTable.skillData.update(PlayerSkillData(event.player.name, event.player.uniqueId, newConfig.toJSONString()))
        players[event.player.uniqueId] = PlayerBaseData(event.player)
    }

    @Schedule(period = 20)
    fun test() {
    }

    @Awake(LifeCycle.DISABLE)
    fun close() {
        RaySkillJobTable.skillData.close()
    }

}
