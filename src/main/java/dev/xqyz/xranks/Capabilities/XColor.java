package dev.xqyz.xranks.Capabilities;

import net.minecraft.nbt.CompoundTag;

public class XColor {
    private String color;

    public String getColor() {
        return color;
    }

    public void changeColor(String color) {
        this.color = color;
    }

    public void copyFrom(XColor source) {
        this.color = source.color;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putString("color", color);
    }

    public void loadNBTData(CompoundTag nbt) {
        color = nbt.getString("color");
    }
}
