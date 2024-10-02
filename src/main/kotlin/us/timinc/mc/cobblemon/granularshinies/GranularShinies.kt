package us.timinc.mc.cobblemon.granularshinies

import com.cobblemon.mod.common.api.spawning.spawner.PlayerSpawnerFactory
import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import us.timinc.mc.cobblemon.granularshinies.config.ConfigBuilder
import us.timinc.mc.cobblemon.granularshinies.config.MainConfig
import us.timinc.mc.cobblemon.granularshinies.influences.ShinyOverride
import java.util.*

object GranularShinies : ModInitializer {
    @Suppress("MemberVisibilityCanBePrivate")
    const val MOD_ID = "granularshinies"
    lateinit var config: MainConfig
    val logger = LogManager.getLogger(MOD_ID)

    override fun onInitialize() {
        config = ConfigBuilder.load(MainConfig::class.java, MOD_ID)
        PlayerSpawnerFactory.influenceBuilders.add(::ShinyOverride)
    }

    fun debug(msg: String, uuid: UUID? = null) {
        if (!config.debug) return
        logger.log(Level.INFO, if (uuid == null) msg else "$msg ($uuid)")
    }
}