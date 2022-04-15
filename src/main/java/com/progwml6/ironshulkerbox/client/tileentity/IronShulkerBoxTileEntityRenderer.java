package com.progwml6.ironshulkerbox.client.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.progwml6.ironshulkerbox.common.block.GenericIronShulkerBlock;
import com.progwml6.ironshulkerbox.common.IronShulkerBoxesTypes;
import com.progwml6.ironshulkerbox.common.block.tileentity.GenericIronShulkerBoxTileEntity;
import net.minecraft.client.model.geom.ModelLayers;
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
import com.mojang.math.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IronShulkerBoxTileEntityRenderer implements BlockEntityRenderer<GenericIronShulkerBoxTileEntity> {

  private final ShulkerModel<?> model;

  public IronShulkerBoxTileEntityRenderer(BlockEntityRendererProvider.Context p_173626_) {
    this.model = new ShulkerModel(p_173626_.bakeLayer(ModelLayers.SHULKER));
  }

  @Override
  public void render(GenericIronShulkerBoxTileEntity tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
    Direction direction = Direction.UP;

    if (tileEntityIn.hasLevel()) {
      BlockState blockstate = tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos());
      if (blockstate.getBlock() instanceof GenericIronShulkerBlock) {
        direction = blockstate.getValue(GenericIronShulkerBlock.FACING);
      }
    }

    BlockState blockState = tileEntityIn.hasLevel() ? tileEntityIn.getBlockState() : (BlockState) tileEntityIn.getBlockToUse().defaultBlockState().setValue(GenericIronShulkerBlock.FACING, Direction.NORTH);
    IronShulkerBoxesTypes boxType = IronShulkerBoxesTypes.IRON;
    IronShulkerBoxesTypes typeFromTileEntity = tileEntityIn.getShulkerBoxType();
    IronShulkerBoxesTypes typeFromBlock = GenericIronShulkerBlock.getTypeFromBlock(blockState.getBlock());

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
    float f = 0.9995F;
    matrixStackIn.scale(0.9995F, 0.9995F, 0.9995F);
    matrixStackIn.mulPose(direction.getRotation());
    matrixStackIn.scale(1.0F, -1.0F, -1.0F);
    matrixStackIn.translate(0.0D, -1.0D, 0.0D);
    VertexConsumer ivertexbuilder = material.buffer(bufferIn, RenderType::entityCutoutNoCull);
    this.model.getBase().render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
    matrixStackIn.translate(0.0D, (double) (-tileEntityIn.getProgress(partialTicks) * 0.5F), 0.0D);
    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(270.0F * tileEntityIn.getProgress(partialTicks)));
    this.model.getLid().render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
    matrixStackIn.popPose();
  }
}
