package us.timinc.mc.cobblemon.granularshinies.influences

import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.api.spawning.detail.PokemonSpawnAction
import com.cobblemon.mod.common.api.spawning.detail.SpawnAction
import com.cobblemon.mod.common.api.spawning.influence.SpawningInfluence
import net.minecraft.server.network.ServerPlayerEntity
import us.timinc.mc.cobblemon.granularshinies.GranularShinies.config
import kotlin.random.Random.Default.nextInt

class ShinyOverride(@Suppress("unused") private val player: ServerPlayerEntity) : SpawningInfluence {
    override fun affectAction(action: SpawnAction<*>) {
        if (action !is PokemonSpawnAction) return

        val dummyMon = action.props.create()
        val found = config.overrides.entries.find { PokemonProperties.parse(it.key).matches(dummyMon) } ?: return

        val rate = found.value
        if (nextInt(rate.toInt()) < 1) {
            action.props.shiny = true
        }
    }
}