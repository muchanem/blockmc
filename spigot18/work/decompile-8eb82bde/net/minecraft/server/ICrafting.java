package net.minecraft.server;

import java.util.List;

public interface ICrafting {

    void a(Container container, List list);

    void a(Container container, int i, ItemStack itemstack);

    void setContainerData(Container container, int i, int j);

    void setContainerData(Container container, IInventory iinventory);
}
