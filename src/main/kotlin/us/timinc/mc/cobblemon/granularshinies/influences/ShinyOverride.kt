package us.timinc.mc.cobblemon.granularshinies.influences

import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.api.spawning.detail.PokemonSpawnAction
import com.cobblemon.mod.common.api.spawning.detail.SpawnAction
import com.cobblemon.mod.common.api.spawning.fishing.FishingSpawnCause
import com.cobblemon.mod.common.api.spawning.influence.SpawningInfluence
import net.minecraft.server.network.ServerPlayerEntity
import us.timinc.mc.cobblemon.granularshinies.GranularShinies.config
import us.timinc.mc.cobblemon.granularshinies.GranularShinies.debug
import us.timinc.mc.cobblemon.granularshinies.extensions.isInvalid
import java.util.*
import kotlin.random.Random.Default.nextInt

class ShinyOverride() : SpawningInfluence {
    constructor(serverPlayer: ServerPlayerEntity) : this()

    override fun affectAction(action: SpawnAction<*>) {
        if (action !is PokemonSpawnAction) return
        val uuid = UUID.randomUUID()
        debug("A ${action.props.species} is spawning", uuid)
        if (action.props.species == "magikarp" && action.ctx.cause is FishingSpawnCause) {
            println("Karp's on!")
        }

        val dummyMon = action.props.create()
        val found = config.overrides.entries.find { PokemonProperties.parse(it.key).matches(dummyMon) } ?: return
        debug("Found matching shiny override of ${found.value}", uuid)

        val rate = found.value
        val roll = nextInt(rate.toInt())
        debug("Rolled a $roll out of $rate (need to roll lower than 1)", uuid)
        if (roll < 1) {
            debug("Setting to shiny", uuid)
            action.props.shiny = true
        }
    }
}