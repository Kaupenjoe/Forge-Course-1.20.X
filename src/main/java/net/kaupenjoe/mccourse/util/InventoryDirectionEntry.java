package net.kaupenjoe.mccourse.util;

import net.minecraft.core.Direction;

public class InventoryDirectionEntry {
    public Direction direction;
    public int slotIndex;
    public boolean canInsert;

    public InventoryDirectionEntry(Direction direction, int slotIndex, boolean canInsert) {
        this.direction = direction;
        this.slotIndex = slotIndex;
        this.canInsert = canInsert;
    }
}
