package net.minecraft.src;

import java.util.Map;

public class mod_sontaran extends BaseMod 
{
	
	
	   public void load()
	   {
		       ModLoader.registerEntityID(sontaran.class, "sontaran.png", ModLoader.getUniqueEntityId());
		       ModLoader.addSpawn(sontaran.class, 12, 4, 10, EnumCreatureType.monster);
	   }
	   
	   public void addRenderer(Map map)
	   {
		       map.put(sontaran.class, new RenderBiped(new ModelBiped(), 0.5F));
	   }
	   
	   public String getVersion()
	   {
		       return "1.2.4";
	   }
	   
}

