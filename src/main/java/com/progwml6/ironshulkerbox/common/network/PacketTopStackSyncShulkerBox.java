package com.progwml6.ironshulkerbox.common.network;

import com.progwml6.ironshulkerbox.common.boxes.tile.CrystalShulkerBoxTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketTopStackSyncShulkerBox {

  private final BlockPos pos;

  private final NonNullList<ItemStack> topStacks;

  public PacketTopStackSyncShulkerBox(BlockPos pos, NonNullList<ItemStack> topStacks) {
    this.pos = pos;
    this.topStacks = topStacks;
  }

  public static void encode(PacketTopStackSyncShulkerBox msg, FriendlyByteBuf buf) {
    buf.writeInt(msg.pos.getX());
    buf.writeInt(msg.pos.getY());
    buf.writeInt(msg.pos.getZ());
    buf.writeInt(msg.topStacks.size());

    for (ItemStack stack : msg.topStacks) {
      buf.writeItem(stack);
    }
  }

  public static PacketTopStackSyncShulkerBox decode(FriendlyByteBuf buf) {
    BlockPos pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());

    int size = buf.readInt();
    NonNullList<ItemStack> topStacks = NonNullList.withSize(size, ItemStack.EMPTY);

    for (int item = 0; item < size; item++) {
      ItemStack itemStack = buf.readItem();

      topStacks.set(item, itemStack);
    }

    return new PacketTopStackSyncShulkerBox(pos, topStacks);
  }

  public static class Handler {

    public static void handle(final PacketTopStackSyncShulkerBox message, Supplier<NetworkEvent.Context> ctx) {
      ctx.get().enqueueWork(() -> {
        Level world = DistExecutor.callWhenOn(Dist.CLIENT, () -> () -> Minecraft.getInstance().level);

        if (world != null) {
          BlockEntity tile = world.getBlockEntity(message.pos);

          if (tile instanceof CrystalShulkerBoxTileEntity) {
            ((CrystalShulkerBoxTileEntity) tile).receiveMessageFromServer(message.topStacks);
          }
        }
      });

      ctx.get().setPacketHandled(true);
    }
  }

}
