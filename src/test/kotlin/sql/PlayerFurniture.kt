package me.skymc.furniture

import taboolib.expansion.BundleMap
import taboolib.expansion.Id
import taboolib.expansion.Key
import taboolib.expansion.Length
import java.util.*

/**
 * SpiralAbyss
 * me.skymc.spiralabyss.PlayerFurniture
 *
 * @author 坏黑
 * @since 2023/3/13 14:32
 */
data class PlayerFurniture(
    @Id
    val username: UUID,
    @Key
    val furnitureUid: UUID,
    @Length(32)
    val furnitureItem: String,
    @Length(32)
    var world: String,
    var x: Double,
    var y: Double,
    var z: Double,
    var yaw: Float,
    var pitch: Float,
    var active: Boolean,
) {

    /** 更新数据 */
    fun update(): PlayerFurniture {
        Furnitures.storages.update(this)
        return this
    }

    companion object {

        @JvmStatic
        fun wrap(map: BundleMap): PlayerFurniture {
            return PlayerFurniture(
                map["username"],
                map["furniture_uid"],
                map["furniture_item"],
                map["world"],
                map["x"],
                map["y"],
                map["z"],
                map["yaw"],
                map["pitch"],
                map["active"],
            )
        }
    }
}