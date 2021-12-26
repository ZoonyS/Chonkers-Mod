package zoony.chonker.cat.common;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChonkerMod implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger("chonkers");

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
	}
	
}
