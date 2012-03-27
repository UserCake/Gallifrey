package net.minecraft.src;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class PlayerControllerDemo extends PlayerControllerSP
{
    /** Whether the demo has expired. */
    private boolean demoExpired;

    /**
     * Counter used to show the demo expired message. Increases every tick, when a player action is performed and the
     * counter is over 100, displays the demo expired message and resets to 0.
     */
    private int demoNotificationTime;

    public PlayerControllerDemo(Minecraft par1Minecraft)
    {
        super(par1Minecraft);
        demoExpired = false;
        demoNotificationTime = 0;
    }

    public void updateController()
    {
        super.updateController();
        long l = mc.theWorld.getWorldTime();
        long l1 = l / 24000L + 1L;
        demoExpired = l > 0x1d6b4L;

        if (demoExpired)
        {
            demoNotificationTime++;
        }

        if (l % 24000L == 500L)
        {
            if (l1 <= 6L)
            {
                mc.ingameGUI.addChatMessageTranslate((new StringBuilder()).append("demo.day.").append(l1).toString());
            }
        }
        else if (l1 == 1L)
        {
            GameSettings gamesettings = mc.gameSettings;
            StringTranslate stringtranslate = StringTranslate.getInstance();
            String s = null;

            if (l == 100L)
            {
                s = stringtranslate.translateKey("demo.help.movement");
                s = String.format(s, new Object[]
                        {
                            Keyboard.getKeyName(gamesettings.keyBindForward.keyCode), Keyboard.getKeyName(gamesettings.keyBindLeft.keyCode), Keyboard.getKeyName(gamesettings.keyBindBack.keyCode), Keyboard.getKeyName(gamesettings.keyBindRight.keyCode)
                        });
            }
            else if (l == 175L)
            {
                s = stringtranslate.translateKey("demo.help.jump");
                s = String.format(s, new Object[]
                        {
                            Keyboard.getKeyName(gamesettings.keyBindJump.keyCode)
                        });
            }
            else if (l == 250L)
            {
                s = stringtranslate.translateKey("demo.help.inventory");
                s = String.format(s, new Object[]
                        {
                            Keyboard.getKeyName(gamesettings.keyBindInventory.keyCode)
                        });
            }

            if (s != null)
            {
                mc.ingameGUI.addChatMessage(s);
            }
        }
        else if (l1 == 5L && l % 24000L == 22000L)
        {
            mc.ingameGUI.addChatMessageTranslate("demo.day.warning");
        }
    }

    private void func_48516_j()
    {
        if (demoNotificationTime > 100)
        {
            mc.ingameGUI.addChatMessageTranslate("demo.reminder");
            demoNotificationTime = 0;
        }
    }

    /**
     * Called by Minecraft class when the player is hitting a block with an item. Args: x, y, z, side
     */
    public void clickBlock(int par1, int par2, int par3, int par4)
    {
        if (demoExpired)
        {
            func_48516_j();
            return;
        }
        else
        {
            super.clickBlock(par1, par2, par3, par4);
            return;
        }
    }

    /**
     * Called when a player damages a block and updates damage counters
     */
    public void onPlayerDamageBlock(int par1, int par2, int par3, int par4)
    {
        if (demoExpired)
        {
            return;
        }
        else
        {
            super.onPlayerDamageBlock(par1, par2, par3, par4);
            return;
        }
    }

    /**
     * Called when a player completes the destruction of a block
     */
    public boolean onPlayerDestroyBlock(int par1, int par2, int par3, int par4)
    {
        if (demoExpired)
        {
            return false;
        }
        else
        {
            return super.onPlayerDestroyBlock(par1, par2, par3, par4);
        }
    }

    /**
     * Notifies the server of things like consuming food, etc...
     */
    public boolean sendUseItem(EntityPlayer par1EntityPlayer, World par2World, ItemStack par3ItemStack)
    {
        if (demoExpired)
        {
            func_48516_j();
            return false;
        }
        else
        {
            return super.sendUseItem(par1EntityPlayer, par2World, par3ItemStack);
        }
    }

    /**
     * Handles a players right click
     */
    public boolean onPlayerRightClick(EntityPlayer par1EntityPlayer, World par2World, ItemStack par3ItemStack, int par4, int par5, int par6, int par7)
    {
        if (demoExpired)
        {
            func_48516_j();
            return false;
        }
        else
        {
            return super.onPlayerRightClick(par1EntityPlayer, par2World, par3ItemStack, par4, par5, par6, par7);
        }
    }

    /**
     * Attacks an entity
     */
    public void attackEntity(EntityPlayer par1EntityPlayer, Entity par2Entity)
    {
        if (demoExpired)
        {
            func_48516_j();
            return;
        }
        else
        {
            super.attackEntity(par1EntityPlayer, par2Entity);
            return;
        }
    }
}
