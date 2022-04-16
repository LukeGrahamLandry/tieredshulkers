package ca.lukegrahamlandry.tieredshulkers.common.network;

import ca.lukegrahamlandry.tieredshulkers.TieredShulkersMain;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {

  private static final String PROTOCOL_VERSION = "1.0";

  private static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(TieredShulkersMain.MOD_ID, "main_channel"))
    .clientAcceptedVersions(PROTOCOL_VERSION::equals)
    .serverAcceptedVersions(PROTOCOL_VERSION::equals)
    .networkProtocolVersion(() -> PROTOCOL_VERSION)
    .simpleChannel();

  public static void register() {
    HANDLER.registerMessage(0, PacketTopStackSyncShulkerBox.class, PacketTopStackSyncShulkerBox::encode, PacketTopStackSyncShulkerBox::decode, PacketTopStackSyncShulkerBox.Handler::handle);
  }

  public static <MSG> void send(PacketDistributor.PacketTarget target, MSG message) {
    HANDLER.send(target, message);
  }
}
