package net.minecraft.src;

public class PathNavigate
{
    private EntityLiving theEntity;
    private World worldObj;
    private PathEntity field_46075_c;
    private float field_46073_d;
    private float field_48683_e;
    private boolean noSunPathfind;
    private int field_48682_g;
    private int field_48688_h;
    private Vec3D field_48689_i;
    private boolean field_48686_j;
    private boolean field_48687_k;
    private boolean field_48684_l;
    private boolean field_48685_m;

    public PathNavigate(EntityLiving par1EntityLiving, World par2World, float par3)
    {
        noSunPathfind = false;
        field_48689_i = Vec3D.createVectorHelper(0.0D, 0.0D, 0.0D);
        field_48686_j = true;
        field_48687_k = false;
        field_48684_l = false;
        field_48685_m = false;
        theEntity = par1EntityLiving;
        worldObj = par2World;
        field_48683_e = par3;
    }

    public void func_48664_a(boolean par1)
    {
        field_48684_l = par1;
    }

    public boolean func_48658_a()
    {
        return field_48684_l;
    }

    public void setBreakDoors(boolean par1)
    {
        field_48687_k = par1;
    }

    public void func_48663_c(boolean par1)
    {
        field_48686_j = par1;
    }

    public boolean func_48665_b()
    {
        return field_48687_k;
    }

    public void func_48680_d(boolean par1)
    {
        noSunPathfind = par1;
    }

    public void func_48660_a(float par1)
    {
        field_46073_d = par1;
    }

    public void func_48669_e(boolean par1)
    {
        field_48685_m = par1;
    }

    public PathEntity func_48671_a(double par1, double par3, double par5)
    {
        if (!canNavigate())
        {
            return null;
        }
        else
        {
            return worldObj.getEntityPathToXYZ(theEntity, MathHelper.floor_double(par1), (int)par3, MathHelper.floor_double(par5), field_48683_e, field_48686_j, field_48687_k, field_48684_l, field_48685_m);
        }
    }

    public boolean func_48666_a(double par1, double par3, double par5, float par7)
    {
        PathEntity pathentity = func_48671_a(MathHelper.floor_double(par1), (int)par3, MathHelper.floor_double(par5));
        return setPath(pathentity, par7);
    }

    public PathEntity func_48679_a(EntityLiving par1EntityLiving)
    {
        if (!canNavigate())
        {
            return null;
        }
        else
        {
            return worldObj.getPathEntityToEntity(theEntity, par1EntityLiving, field_48683_e, field_48686_j, field_48687_k, field_48684_l, field_48685_m);
        }
    }

    public boolean func_48667_a(EntityLiving par1EntityLiving, float par2)
    {
        PathEntity pathentity = func_48679_a(par1EntityLiving);

        if (pathentity != null)
        {
            return setPath(pathentity, par2);
        }
        else
        {
            return false;
        }
    }

    /**
     * sets the active path data if path is 100% unique compared to old path, checks to adjust path for sun avoiding
     * ents and stores end coords
     */
    public boolean setPath(PathEntity par1PathEntity, float par2)
    {
        if (par1PathEntity == null)
        {
            field_46075_c = null;
            return false;
        }

        if (!par1PathEntity.func_48647_a(field_46075_c))
        {
            field_46075_c = par1PathEntity;
        }

        if (noSunPathfind)
        {
            removeSunnyPath();
        }

        if (field_46075_c.getCurrentPathLength() == 0)
        {
            return false;
        }
        else
        {
            field_46073_d = par2;
            Vec3D vec3d = getEntityPosition();
            field_48688_h = field_48682_g;
            field_48689_i.xCoord = vec3d.xCoord;
            field_48689_i.yCoord = vec3d.yCoord;
            field_48689_i.zCoord = vec3d.zCoord;
            return true;
        }
    }

    /**
     * gets the actively used PathEntity
     */
    public PathEntity getPath()
    {
        return field_46075_c;
    }

    public void onUpdateNavigation()
    {
        field_48682_g++;

        if (noPath())
        {
            return;
        }

        if (canNavigate())
        {
            pathFollow();
        }

        if (noPath())
        {
            return;
        }

        Vec3D vec3d = field_46075_c.getCurrentNodeVec3d(theEntity);

        if (vec3d == null)
        {
            return;
        }
        else
        {
            theEntity.getMoveHelper().setMoveTo(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord, field_46073_d);
            return;
        }
    }

    private void pathFollow()
    {
        Vec3D vec3d = getEntityPosition();
        int i = field_46075_c.getCurrentPathLength();
        int i2 = field_46075_c.getCurrentPathIndex();

        do
        {
            if (i2 >= field_46075_c.getCurrentPathLength())
            {
                break;
            }

            if (field_46075_c.getPathPointFromIndex(i2).yCoord != (int)vec3d.yCoord)
            {
                i = i2;
                break;
            }

            i2++;
        }
        while (true);

        float f = theEntity.width * theEntity.width;

        for (int j = field_46075_c.getCurrentPathIndex(); j < i; j++)
        {
            if (vec3d.squareDistanceTo(field_46075_c.func_48646_a(theEntity, j)) < (double)f)
            {
                field_46075_c.setCurrentPathIndex(j + 1);
            }
        }

        int k = (int)Math.ceil(theEntity.width);
        int l = (int)theEntity.height + 1;
        int i1 = k;
        int j1 = i - 1;

        do
        {
            if (j1 < field_46075_c.getCurrentPathIndex())
            {
                break;
            }

            if (func_48662_a(vec3d, field_46075_c.func_48646_a(theEntity, j1), k, l, i1))
            {
                field_46075_c.setCurrentPathIndex(j1);
                break;
            }

            j1--;
        }
        while (true);

        if (field_48682_g - field_48688_h > 100)
        {
            if (vec3d.squareDistanceTo(field_48689_i) < 2.25D)
            {
                clearPathEntity();
            }

            field_48688_h = field_48682_g;
            field_48689_i.xCoord = vec3d.xCoord;
            field_48689_i.yCoord = vec3d.yCoord;
            field_48689_i.zCoord = vec3d.zCoord;
        }
    }

    /**
     * If null path or reached the end
     */
    public boolean noPath()
    {
        return field_46075_c == null || field_46075_c.isFinished();
    }

    /**
     * sets active PathEntity to null
     */
    public void clearPathEntity()
    {
        field_46075_c = null;
    }

    private Vec3D getEntityPosition()
    {
        return Vec3D.createVector(theEntity.posX, getPathableYPos(), theEntity.posZ);
    }

    /**
     * Gets the safe pathing Y position for the entity depending on if it can path swim or not
     */
    private int getPathableYPos()
    {
        if (!theEntity.isInWater() || !field_48685_m)
        {
            return (int)(theEntity.boundingBox.minY + 0.5D);
        }

        int i = (int)theEntity.boundingBox.minY;
        int j = worldObj.getBlockId(MathHelper.floor_double(theEntity.posX), i, MathHelper.floor_double(theEntity.posZ));
        int k = 0;

        while (j == Block.waterMoving.blockID || j == Block.waterStill.blockID)
        {
            i++;
            j = worldObj.getBlockId(MathHelper.floor_double(theEntity.posX), i, MathHelper.floor_double(theEntity.posZ));

            if (++k > 16)
            {
                return (int)theEntity.boundingBox.minY;
            }
        }

        return i;
    }

    /**
     * If on ground or swimming and can swim
     */
    private boolean canNavigate()
    {
        return theEntity.onGround || field_48685_m && func_48657_k();
    }

    private boolean func_48657_k()
    {
        return theEntity.isInWater() || theEntity.handleLavaMovement();
    }

    /**
     * Trims path data from the end to the first sun covered block
     */
    private void removeSunnyPath()
    {
        if (worldObj.canBlockSeeTheSky(MathHelper.floor_double(theEntity.posX), (int)(theEntity.boundingBox.minY + 0.5D), MathHelper.floor_double(theEntity.posZ)))
        {
            return;
        }

        for (int i = 0; i < field_46075_c.getCurrentPathLength(); i++)
        {
            PathPoint pathpoint = field_46075_c.getPathPointFromIndex(i);

            if (worldObj.canBlockSeeTheSky(pathpoint.xCoord, pathpoint.yCoord, pathpoint.zCoord))
            {
                field_46075_c.setCurrentPathLength(i - 1);
                return;
            }
        }
    }

    private boolean func_48662_a(Vec3D par1Vec3D, Vec3D par2Vec3D, int par3, int par4, int par5)
    {
        int i = MathHelper.floor_double(par1Vec3D.xCoord);
        int j = MathHelper.floor_double(par1Vec3D.zCoord);
        double d = par2Vec3D.xCoord - par1Vec3D.xCoord;
        double d1 = par2Vec3D.zCoord - par1Vec3D.zCoord;
        double d2 = d * d + d1 * d1;

        if (d2 < 1E-008D)
        {
            return false;
        }

        double d3 = 1.0D / Math.sqrt(d2);
        d *= d3;
        d1 *= d3;
        par3 += 2;
        par5 += 2;

        if (!func_48675_a(i, (int)par1Vec3D.yCoord, j, par3, par4, par5, par1Vec3D, d, d1))
        {
            return false;
        }

        par3 -= 2;
        par5 -= 2;
        double d4 = 1.0D / Math.abs(d);
        double d5 = 1.0D / Math.abs(d1);
        double d6 = (double)(i * 1) - par1Vec3D.xCoord;
        double d7 = (double)(j * 1) - par1Vec3D.zCoord;

        if (d >= 0.0D)
        {
            d6++;
        }

        if (d1 >= 0.0D)
        {
            d7++;
        }

        d6 /= d;
        d7 /= d1;
        byte byte0 = ((byte)(d >= 0.0D ? 1 : -1));
        byte byte1 = ((byte)(d1 >= 0.0D ? 1 : -1));
        int k = MathHelper.floor_double(par2Vec3D.xCoord);
        int l = MathHelper.floor_double(par2Vec3D.zCoord);
        int i1 = k - i;

        for (int j1 = l - j; i1 * byte0 > 0 || j1 * byte1 > 0;)
        {
            if (d6 < d7)
            {
                d6 += d4;
                i += byte0;
                i1 = k - i;
            }
            else
            {
                d7 += d5;
                j += byte1;
                j1 = l - j;
            }

            if (!func_48675_a(i, (int)par1Vec3D.yCoord, j, par3, par4, par5, par1Vec3D, d, d1))
            {
                return false;
            }
        }

        return true;
    }

    private boolean func_48675_a(int par1, int par2, int par3, int par4, int par5, int par6, Vec3D par7Vec3D, double par8, double par10)
    {
        int i = par1 - par4 / 2;
        int j = par3 - par6 / 2;

        if (!func_48676_b(i, par2, j, par4, par5, par6, par7Vec3D, par8, par10))
        {
            return false;
        }

        for (int k = i; k < i + par4; k++)
        {
            for (int l = j; l < j + par6; l++)
            {
                double d = ((double)k + 0.5D) - par7Vec3D.xCoord;
                double d1 = ((double)l + 0.5D) - par7Vec3D.zCoord;

                if (d * par8 + d1 * par10 < 0.0D)
                {
                    continue;
                }

                int i1 = worldObj.getBlockId(k, par2 - 1, l);

                if (i1 <= 0)
                {
                    return false;
                }

                Material material = Block.blocksList[i1].blockMaterial;

                if (material == Material.water && !theEntity.isInWater())
                {
                    return false;
                }

                if (material == Material.lava)
                {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean func_48676_b(int par1, int par2, int par3, int par4, int par5, int par6, Vec3D par7Vec3D, double par8, double par10)
    {
        for (int i = par1; i < par1 + par4; i++)
        {
            for (int j = par2; j < par2 + par5; j++)
            {
                for (int k = par3; k < par3 + par6; k++)
                {
                    double d = ((double)i + 0.5D) - par7Vec3D.xCoord;
                    double d1 = ((double)k + 0.5D) - par7Vec3D.zCoord;

                    if (d * par8 + d1 * par10 < 0.0D)
                    {
                        continue;
                    }

                    int l = worldObj.getBlockId(i, j, k);

                    if (l > 0 && !Block.blocksList[l].getBlocksMovement(worldObj, i, j, k))
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
