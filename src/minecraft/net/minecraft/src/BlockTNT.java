package net.minecraft.src;

import java.util.Random;

public class BlockTNT extends Block
{
    public BlockTNT(int par1, int par2)
    {
        super(par1, par2, Material.tnt);
    }

    /**
     * Returns the block texture based on the side being looked at.  Args: side
     */
    public int getBlockTextureFromSide(int par1)
    {
        if (par1 == 0)
        {
            return blockIndexInTexture + 2;
        }

        if (par1 == 1)
        {
            return blockIndexInTexture + 1;
        }
        else
        {
            return blockIndexInTexture;
        }
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);

        if (par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
        {
            onBlockDestroyedByPlayer(par1World, par2, par3, par4, 1);
            par1World.setBlockWithNotify(par2, par3, par4, 0);
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (par5 > 0 && Block.blocksList[par5].canProvidePower() && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
        {
            onBlockDestroyedByPlayer(par1World, par2, par3, par4, 1);
            par1World.setBlockWithNotify(par2, par3, par4, 0);
        }
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World par1World, int par2, int par3, int par4)
    {
        if (par1World.isRemote)
        {
            return;
        }
        else
        {
            EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(par1World, (float)par2 + 0.5F, (float)par3 + 0.5F, (float)par4 + 0.5F);
            entitytntprimed.fuse = par1World.rand.nextInt(entitytntprimed.fuse / 4) + entitytntprimed.fuse / 8;
            par1World.spawnEntityInWorld(entitytntprimed);
            return;
        }
    }

    /**
     * Called right before the block is destroyed by a player.  Args: world, x, y, z, metaData
     */
    public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5)
    {
        if (par1World.isRemote)
        {
            return;
        }

        if ((par5 & 1) == 0)
        {
            dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(Block.tnt.blockID, 1, 0));
        }
        else
        {
            EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(par1World, (float)par2 + 0.5F, (float)par3 + 0.5F, (float)par4 + 0.5F);
            par1World.spawnEntityInWorld(entitytntprimed);
            par1World.playSoundAtEntity(entitytntprimed, "random.fuse", 1.0F, 1.0F);
        }
    }

    /**
     * Called when the block is clicked by a player. Args: x, y, z, entityPlayer
     */
    public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
    {
        if (par5EntityPlayer.getCurrentEquippedItem() != null && par5EntityPlayer.getCurrentEquippedItem().itemID == Item.flintAndSteel.shiftedIndex)
        {
            par1World.setBlockMetadata(par2, par3, par4, 1);
        }

        super.onBlockClicked(par1World, par2, par3, par4, par5EntityPlayer);
    }

    /**
     * Called upon block activation (left or right click on the block.). The three integers represent x,y,z of the
     * block.
     */
    public boolean blockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
    {
        return super.blockActivated(par1World, par2, par3, par4, par5EntityPlayer);
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int par1)
    {
        return null;
    }
}
