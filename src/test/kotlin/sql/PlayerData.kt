package me.skymc.furniture

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import taboolib.common.platform.function.submitAsync
import taboolib.common.platform.function.warning
import java.util.*

/**
 * Furnitures
 * me.skymc.furniture.PlayerData
 *
 * @author 坏黑
 * @since 2023/5/29 00:42
 */
class PlayerData(val player: Player) {

    private val furnitures = arrayListOf<PlayerFurniture>()

    /** 数据是否加载 */
    var isLoaded = false
        private set

    init {
        submitAsync {
            furnitures += Furnitures.storages.get(player.uniqueId)
            init()
            isLoaded = true
        }
    }

    /** 初始化家具 */
    fun init() {
        furnitures.forEach { furniture ->
            if (furniture.active) {
                val world = Bukkit.getWorld(furniture.world)
                if (world == null) {
                    warning("Cannot find world ${furniture.world}.")
                    return@forEach
                }
                val loc = Location(world, furniture.x, furniture.y, furniture.z, furniture.yaw, furniture.pitch)
                val item = Furnitures.furnitureItems[furniture.furnitureItem]
                if (item == null) {
                    warning("Cannot find furniture ${furniture.furnitureItem}.")
                    return@forEach
                }
                item.spawn(player, loc, furniture.furnitureUid, furniture.furnitureItem)
            }
        }
    }

    fun addFurniture(location: Location, uid: UUID, zapItemId: String) {
        // 如果家具已经存在，则更新信息
        val furniture = furnitures.find { it.furnitureUid == uid }
        if (furniture != null) {
            furniture.world = location.world!!.name
            furniture.x = location.x
            furniture.y = location.y
            furniture.z = location.z
            furniture.yaw = location.yaw
            furniture.pitch = location.pitch
            furniture.active = true
            furniture.update()
        } else {
            // 否则创建新的家具
            furnitures += PlayerFurniture(
                player.uniqueId,
                uid,
                zapItemId,
                location.world!!.name,
                location.x,
                location.y,
                location.z,
                location.yaw,
                location.pitch,
                true,
            ).update()
        }
    }

    fun removeFurniture(uid: UUID) {
        val furniture = furnitures.find { it.furnitureUid == uid }
        if (furniture != null) {
            furniture.active = false
            furniture.update()
        }
    }

    fun count(): Int {
        return furnitures.count { it.active }
    }
}