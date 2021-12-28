package zoony.chonker.cat.common.init;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.world.Difficulty;
import zoony.chonker.cat.common.world.ChonkerSpawner;

public class ChonkerModSpawner {
    static Difficulty PEACEFUL = Difficulty.PEACEFUL;
    
    public static void init() {
        ChonkerSpawner chonkerSpawner = new ChonkerSpawner();
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            server.getWorlds().forEach(world -> {
                chonkerSpawner.spawn(world, server.getSaveProperties().getDifficulty() != PEACEFUL, server.shouldSpawnAnimals());
            });
        });
    }

}
