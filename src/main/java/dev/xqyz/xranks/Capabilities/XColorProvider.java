package dev.xqyz.xranks.Capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class XColorProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<XColor> COLOR =
            CapabilityManager.get(new CapabilityToken<XColor>() { });

    private XColor color = null;
    private final LazyOptional<XColor> optional = LazyOptional.of(this::createColor);

    private XColor createColor() {
        if (this.color == null) {
            this.color = new XColor();
        }
        return this.color;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == COLOR) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createColor().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createColor().loadNBTData(nbt);
    }
}
