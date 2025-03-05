package us.timinc.mc.cobblemon.granularshinies

import com.cobblemon.mod.common.api.Priority
import com.cobblemon.mod.common.api.events.CobblemonEvents
import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import us.timinc.mc.cobblemon.granularshinies.config.ConfigBuilder
import us.timinc.mc.cobblemon.granularshinies.config.MainConfig
import us.timinc.mc.cobblemon.granularshinies.events.ShinyChanceCalculationHandler
import us.timinc.mc.cobblemon.granularshinies.extensions.isInvalid
import java.util.*

object GranularShinies : ModInitializer {
    @Suppress("MemberVisibilityCanBePrivate")
    const val MOD_ID = "granularshinies"
    lateinit var config: MainConfig
    val logger = LogManager.getLogger(MOD_ID)

    override fun onInitialize() {
        config = ConfigBuilder.load(MainConfig::class.java, MOD_ID)
        validateConfig()
        CobblemonEvents.SHINY_CHANCE_CALCULATION.subscribe(Priority.HIGHEST, ShinyChanceCalculationHandler::handle)
    }

    private fun validateConfig() {
        CobblemonEvents.DATA_SYNCHRONIZED.subscribe {
            config.overrides.forEach { (properties) ->
                if (PokemonProperties.parse(properties).isInvalid()) {
                    debug("Your override of $properties is invalid and will match all Pokemon", bypassConfig = true)
                }
            }
        }
    }

    fun debug(msg: String, uuid: UUID? = null, bypassConfig: Boolean = false) {
        if (!config.debug && !bypassConfig) return
        logger.log(Level.INFO, if (uuid == null) msg else "$msg ($uuid)")
    }
}