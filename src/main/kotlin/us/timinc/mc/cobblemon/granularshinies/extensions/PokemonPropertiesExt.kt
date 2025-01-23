package us.timinc.mc.cobblemon.granularshinies.extensions

import com.cobblemon.mod.common.api.pokemon.PokemonProperties

fun PokemonProperties.isInvalid(): Boolean {
    return this.species === null
}