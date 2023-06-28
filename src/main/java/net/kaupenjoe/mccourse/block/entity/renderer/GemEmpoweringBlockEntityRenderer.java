package net.kaupenjoe.mccourse.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.kaupenjoe.mccourse.block.custom.GemEmpoweringStationBlock;
import net.kaupenjoe.mccourse.block.entity.GemEmpoweringStationBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

public class GemEmpoweringBlockEntityRenderer implements BlockEntityRenderer<GemEmpoweringStationBlockEntity> {
    public GemEmpoweringBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(GemEmpoweringStationBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource,
                       int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = pBlockEntity.getRenderStack();

        pPoseStack.pushPose();
        pPoseStack.translate(0.5f, 0.75f, 0.5f);
        pPoseStack.scale(0.35f, 0.35f, 0.35f);
        pPoseStack.mulPose(Axis.YN.rotationDegrees(pBlockEntity.getBlockState().getValue(GemEmpoweringStationBlock.FACING).toYRot()));
        pPoseStack.mulPose(Axis.XP.rotationDegrees(270));

        itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, getLightLevel(pBlockEntity.getLevel(),
                pBlockEntity.getBlockPos()), OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource, pBlockEntity.getLevel(), 1);
        pPoseStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
