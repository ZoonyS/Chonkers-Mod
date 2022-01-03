package zoony.chonker.cat.common;

import net.fabricmc.api.ModInitializer;
import software.bernie.geckolib3.GeckoLib;
import zoony.chonker.cat.common.init.ChonkerModBlock;
import zoony.chonker.cat.common.init.ChonkerModEntity;
import zoony.chonker.cat.common.init.ChonkerModItem;
import zoony.chonker.cat.common.init.ChonkerModSpawner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChonkerMod implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger("chonkers");

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		GeckoLib.initialize();
		ChonkerModEntity.init();
		ChonkerModItem.init();
		ChonkerModBlock.init();
		ChonkerModSpawner.init();
	}

}
