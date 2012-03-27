package net.minecraft.src;

public class mod_Blockcheese 
{
public static final Block cheese = new Block(160, null).setBlockName("cheese").setHardness(1F).setResistance(2F).setLightValue(3F);

       public void load()
       {
    	       cheese.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/mods/imagename.png");
    	       ModLoader.registerBlock(cheese);
    	       ModLoader.addName(cheese, "cheeseblock");
    	       ModLoader.addRecipe(new ItemStack(cheese, 9), new Object [] {"###", 3, Character.valueOf('#'), Item.bucketMilk});
    	       }

       public String getVersion()
         {
                         return "1.2.4";
       }
}       
      
