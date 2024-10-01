package us.timinc.mc.cobblemon.granularshinies

import com.cobblemon.mod.common.api.spawning.spawner.PlayerSpawnerFactory
import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier
import us.timinc.mc.cobblemon.granularshinies.config.ConfigBuilder
import us.timinc.mc.cobblemon.granularshinies.config.MainConfig
import us.timinc.mc.cobblemon.granularshinies.influences.ShinyOverride

object GranularShinies : ModInitializer {
    @Suppress("MemberVisibilityCanBePrivate")
    const val MOD_ID = "granularshinies"
    lateinit var config: MainConfig

    override fun onInitialize() {
        config = ConfigBuilder.load(MainConfig::class.java, MOD_ID)
        PlayerSpawnerFactory.influenceBuilders.add(::ShinyOverride)
    }
}