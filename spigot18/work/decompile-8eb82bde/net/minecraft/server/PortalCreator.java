package net.minecraft.server;

public class PortalCreator {

    private final World a;
    private final EnumAxis b;
    private final EnumDirection c;
    private final EnumDirection d;
    private int e = 0;
    private BlockPosition f;
    private int g;
    private int h;

    public PortalCreator(World world, BlockPosition blockposition, EnumAxis enumaxis) {
        this.a = world;
        this.b = enumaxis;
        if (enumaxis == EnumAxis.X) {
            this.d = EnumDirection.EAST;
            this.c = EnumDirection.WEST;
        } else {
            this.d = EnumDirection.NORTH;
            this.c = EnumDirection.SOUTH;
        }

        for (BlockPosition blockposition1 = blockposition; blockposition.getY() > blockposition1.getY() - 21 && blockposition.getY() > 0 && this.a(world.getType(blockposition.down()).getBlock()); blockposition = blockposition.down()) {
            ;
        }

        int i = this.a(blockposition, this.d) - 1;

        if (i >= 0) {
            this.f = blockposition.shift(this.d, i);
            this.h = this.a(this.f, this.c);
            if (this.h < 2 || this.h > 21) {
                this.f = null;
                this.h = 0;
            }
        }

        if (this.f != null) {
            this.g = this.a();
        }

    }

    protected int a(BlockPosition blockposition, EnumDirection enumdirection) {
        int i;

        for (i = 0; i < 22; ++i) {
            BlockPosition blockposition1 = blockposition.shift(enumdirection, i);

            if (!this.a(this.a.getType(blockposition1).getBlock()) || this.a.getType(blockposition1.down()).getBlock() != Blocks.OBSIDIAN) {
                break;
            }
        }

        Block block = this.a.getType(blockposition.shift(enumdirection, i)).getBlock();

        return block == Blocks.OBSIDIAN ? i : 0;
    }

    protected int a() {
        int i;

        label56:
        for (this.g = 0; this.g < 21; ++this.g) {
            for (i = 0; i < this.h; ++i) {
                BlockPosition blockposition = this.f.shift(this.c, i).up(this.g);
                Block block = this.a.getType(blockposition).getBlock();

                if (!this.a(block)) {
                    break label56;
                }

                if (block == Blocks.PORTAL) {
                    ++this.e;
                }

                if (i == 0) {
                    block = this.a.getType(blockposition.shift(this.d)).getBlock();
                    if (block != Blocks.OBSIDIAN) {
                        break label56;
                    }
                } else if (i == this.h - 1) {
                    block = this.a.getType(blockposition.shift(this.c)).getBlock();
                    if (block != Blocks.OBSIDIAN) {
                        break label56;
                    }
                }
            }
        }

        for (i = 0; i < this.h; ++i) {
            if (this.a.getType(this.f.shift(this.c, i).up(this.g)).getBlock() != Blocks.OBSIDIAN) {
                this.g = 0;
                break;
            }
        }

        if (this.g <= 21 && this.g >= 3) {
            return this.g;
        } else {
            this.f = null;
            this.h = 0;
            this.g = 0;
            return 0;
        }
    }

    protected boolean a(Block block) {
        return block.material == Material.AIR || block == Blocks.FIRE || block == Blocks.PORTAL;
    }

    public boolean b() {
        return this.f != null && this.h >= 2 && this.h <= 21 && this.g >= 3 && this.g <= 21;
    }

    public void c() {
        for (int i = 0; i < this.h; ++i) {
            BlockPosition blockposition = this.f.shift(this.c, i);

            for (int j = 0; j < this.g; ++j) {
                this.a.setTypeAndData(blockposition.up(j), Blocks.PORTAL.getBlockData().set(BlockPortal.AXIS, this.b), 2);
            }
        }

    }

    public static int a(PortalCreator portalcreator) {
        return portalcreator.e;
    }

    public static int b(PortalCreator portalcreator) {
        return portalcreator.h;
    }

    public static int c(PortalCreator portalcreator) {
        return portalcreator.g;
    }
}
