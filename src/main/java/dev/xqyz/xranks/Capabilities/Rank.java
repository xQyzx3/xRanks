package dev.xqyz.xranks.Capabilities;

import net.minecraft.nbt.CompoundTag;

public class Rank {
    private String rank;

    public String getRank() {
        return rank;
    }

    public void changeRank(String rank) {
        this.rank = rank;
    }

    public void copyFrom(Rank source) {
        this.rank = source.rank;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putString("rank", rank);
    }

    public void loadNBTData(CompoundTag nbt) {
        rank = nbt.getString("rank");
    }
}
