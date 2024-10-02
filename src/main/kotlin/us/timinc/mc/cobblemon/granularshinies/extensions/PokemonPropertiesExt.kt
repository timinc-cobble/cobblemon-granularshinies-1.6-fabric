package us.timinc.mc.cobblemon.granularshinies.extensions

import com.cobblemon.mod.common.api.pokemon.PokemonProperties

fun PokemonProperties.isInvalid(): Boolean {
    val json = this.saveToJSON()
    return json.keySet().size == 4
            && json.get("IVs").asJsonObject.keySet().size == 0
            && json.get("EVs").asJsonObject.keySet().size == 0
            && json.get("CustomProperties").asJsonArray.size() == 0
}