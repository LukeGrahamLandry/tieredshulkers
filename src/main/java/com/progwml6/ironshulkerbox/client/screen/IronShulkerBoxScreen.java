package com.progwml6.ironshulkerbox.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.progwml6.ironshulkerbox.common.block.IronShulkerBoxesTypes;
import com.progwml6.ironshulkerbox.common.inventory.IronShulkerBoxContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class IronShulkerBoxScreen extends ContainerScreen<IronShulkerBoxContainer> {

  private final IronShulkerBoxesTypes shulkerBoxType;

  private final int textureXSize;

  private final int textureYSize;

  public IronShulkerBoxScreen(IronShulkerBoxContainer container, PlayerInventory playerInventory, ITextComponent title) {
    super(container, playerInventory, title);

    this.shulkerBoxType = container.getShulkerBoxType();
    this.imageWidth = container.getShulkerBoxType().xSize;
    this.imageHeight = container.getShulkerBoxType().ySize;
    this.textureXSize = container.getShulkerBoxType().textureXSize;
    this.textureYSize = container.getShulkerBoxType().textureYSize;

    this.passEvents = false;
  }

  @Override
  public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
    this.renderBackground(matrixStack);
    super.render(matrixStack, mouseX, mouseY, partialTicks);
    this.renderTooltip(matrixStack, mouseX, mouseY);
  }

  @Override
  protected void renderLabels(MatrixStack matrixStack, int mouseX, int mouseY) {
    this.font.draw(matrixStack, this.title, 8.0F, 6.0F, 4210752);
    this.font.draw(matrixStack, this.inventory.getDisplayName(), 8.0F, (float) (this.imageHeight - 96 + 2), 4210752);
  }

  @Override
  protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

    this.minecraft.getTextureManager().bind(this.shulkerBoxType.guiTexture);

    int x = (this.width - this.imageWidth) / 2;
    int y = (this.height - this.imageHeight) / 2;

    blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight, this.textureXSize, this.textureYSize);
  }
}
