package com.progwml6.ironshulkerbox.client.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.progwml6.ironshulkerbox.common.boxes.UpgradableBoxBlock;
import com.progwml6.ironshulkerbox.common.boxes.UpgradableBoxTier;
import com.progwml6.ironshulkerbox.common.boxes.tile.UpgradableBoxTile;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.model.ShulkerModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.world.item.DyeColor;
import net.minecraft.core.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class UpgradableBoxTileEntityRenderer implements BlockEntityRenderer<UpgradableBoxTile> {

  private final ShulkerModel<?> model;

  public UpgradableBoxTileEntityRenderer(BlockEntityRendererProvider.Context p_173626_) {
    this.model = new ShulkerModel(p_173626_.bakeLayer(ModelLayers.SHULKER));
  }

  @Override
  public void render(UpgradableBoxTile tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
    Direction direction = Direction.UP;

    if (tileEntityIn.hasLevel()) {
      BlockState blockstate = tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos());
      if (blockstate.getBlock() instanceof UpgradableBoxBlock) {
        direction = blockstate.getValue(UpgradableBoxBlock.FACING);
      }
    }

    BlockState blockState = tileEntityIn.hasLevel() ? tileEntityIn.getBlockState() : (BlockState) tileEntityIn.getBlockToUse().defaultBlockState().setValue(UpgradableBoxBlock.FACING, Direction.NORTH);
    UpgradableBoxTier boxType = UpgradableBoxTier.IRON;
    UpgradableBoxTier typeFromTileEntity = tileEntityIn.getShulkerBoxType();
    UpgradableBoxTier typeFromBlock = UpgradableBoxBlock.getTypeFromBlock(blockState.getBlock());

    if (typeFromTileEntity != null) {
      boxType = typeFromTileEntity;
    }

    if (boxType != typeFromBlock || typeFromTileEntity != typeFromBlock) {
      if (typeFromBlock != null) {
        boxType = typeFromBlock;
      }
    }

    DyeColor dyecolor = tileEntityIn.getColor();

    Material material = new Material(Sheets.SHULKER_SHEET, IronShulkerBoxesModels.chooseShulkerBoxModel(boxType, dyecolor.getId()));

    matrixStackIn.pushPose();
    matrixStackIn.translate(0.5D, 0.5D, 0.5D);
    matrixStackIn.scale(0.9995F, 0.9995F, 0.9995F);
    matrixStackIn.mulPose(direction.getRotation());
    matrixStackIn.scale(1.0F, -1.0F, -1.0F);
    matrixStackIn.translate(0.0D, -1.0D, 0.0D);
    ModelPart modelpart = this.model.getLid();
    modelpart.setPos(0.0F, 24.0F - tileEntityIn.getProgress(partialTicks) * 0.5F * 16.0F, 0.0F);
    modelpart.yRot = 270.0F * tileEntityIn.getProgress(partialTicks) * ((float)Math.PI / 180F);
    VertexConsumer vertexconsumer = material.buffer(bufferIn, RenderType::entityCutoutNoCull);
    this.model.renderToBuffer(matrixStackIn, vertexconsumer, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
    matrixStackIn.popPose();
  }
}
