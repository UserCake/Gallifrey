package net.minecraft.src;

import java.util.Random;

public class sontaran extends EntityMob
{
	    public sontaran(World world)
	    {
	    	    super(world);
	    	    texture = "sontaran.png";
	    	    moveSpeed = 0.2F;
	    attackStrength = 6;
	    }
	    
	    public int getMaxHealth()
	    {
	    	    return 30;
	    }

        protected String getHurtSound()
        {
                return "mob.villager.defaulthurt";
        }

        protected String getDeathSound()
        {
                return "mob.villager.defaultdeath";
        }

        protected int getDropItemId()
        {
                return Item.ingotIron.shiftedIndex;
        }
        
        protected boolean canDespawn()
        {
                return true;
        }

}


