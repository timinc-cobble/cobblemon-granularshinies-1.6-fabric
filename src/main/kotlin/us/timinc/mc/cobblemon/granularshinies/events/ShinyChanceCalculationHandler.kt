package us.timinc.mc.cobblemon.granularshinies.events

import com.cobblemon.mod.common.api.events.pokemon.ShinyChanceCalculationEvent
import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import us.timinc.mc.cobblemon.granularshinies.GranularShinies.config
import us.timinc.mc.cobblemon.granularshinies.GranularShinies.debug

object ShinyChanceCalculationHandler {
    fun handle(event: ShinyChanceCalculationEvent) {
        event.addModificationFunction { chance, player, pokemon ->
            val found =
                config.overrides.entries.find { PokemonProperties.parse(it.key).matches(pokemon) }
                    ?: return@addModificationFunction chance
            debug("Found matching shiny override of \"${found.key}\":${found.value} for ${pokemon.form.name} ${pokemon.species.name}")
            return@addModificationFunction found.value
        }
    }
}