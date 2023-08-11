package top.maplex.rayskilljob.database

import com.alibaba.fastjson2.JSONObject
import taboolib.expansion.CustomType
import taboolib.expansion.CustomTypeData
import taboolib.module.database.ColumnOptionSQLite
import taboolib.module.database.ColumnTypeSQL

@CustomType
object FastJsonByTabooLib : CustomTypeData {

    override val sqlLiteType: ColumnOptionSQLite? = null

    override val sqlType: ColumnTypeSQL = ColumnTypeSQL.JSON

    override fun deserialize(obj: Any): Any {
        return JSONObject.parseObject(obj.toString())
    }

    override fun serialize(obj: Any): Any {
        return JSONObject.toJSONString(obj)
    }

    override fun isThis(obj: Any): Boolean {
        return obj is JSONObject
    }

    override fun isThisByClass(clazz: Class<*>): Boolean {
        return clazz == JSONObject::class.java
    }

}
