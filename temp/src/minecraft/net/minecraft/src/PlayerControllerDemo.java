// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

// Referenced classes of package net.minecraft.src:
//            PlayerControllerSP, World, GuiIngame, StringTranslate, 
//            GameSettings, KeyBinding, EntityPlayer, ItemStack, 
//            Entity

public class PlayerControllerDemo extends PlayerControllerSP
{

    private boolean field_48518_c;
    private int field_48517_d;

    public PlayerControllerDemo(Minecraft p_i1078_1_)
    {
        super(p_i1078_1_);
        field_48518_c = false;
        field_48517_d = 0;
    }

    public void func_6474_c()
    {
        super.func_6474_c();
        long l = field_1065_a.field_6324_e.func_22139_r();
        long l1 = l / 24000L + 1L;
        field_48518_c = l > 0x1d6b4L;
        if(field_48518_c)
        {
            field_48517_d++;
        }
        if(l % 24000L == 500L)
        {
            if(l1 <= 6L)
            {
                field_1065_a.field_6308_u.func_22064_c((new StringBuilder()).append("demo.day.").append(l1).toString());
            }
        } else
        if(l1 == 1L)
        {
            GameSettings gamesettings = field_1065_a.field_6304_y;
            StringTranslate stringtranslate = StringTranslate.func_20162_a();
            String s = null;
            if(l == 100L)
            {
                s = stringtranslate.func_20163_a("demo.help.movement");
                s = String.format(s, new Object[] {
                    Keyboard.getKeyName(gamesettings.field_1575_j.field_1370_b), Keyboard.getKeyName(gamesettings.field_1574_k.field_1370_b), Keyboard.getKeyName(gamesettings.field_1573_l.field_1370_b), Keyboard.getKeyName(gamesettings.field_1572_m.field_1370_b)
                });
            } else
            if(l == 175L)
            {
                s = stringtranslate.func_20163_a("demo.help.jump");
                s = String.format(s, new Object[] {
                    Keyboard.getKeyName(gamesettings.field_1571_n.field_1370_b)
                });
            } else
            if(l == 250L)
            {
                s = stringtranslate.func_20163_a("demo.help.inventory");
                s = String.format(s, new Object[] {
                    Keyboard.getKeyName(gamesettings.field_1570_o.field_1370_b)
                });
            }
            if(s != null)
            {
                field_1065_a.field_6308_u.func_552_a(s);
            }
        } else
        if(l1 == 5L && l % 24000L == 22000L)
        {
            field_1065_a.field_6308_u.func_22064_c("demo.day.warning");
        }
    }

    private void func_48516_j()
    {
        if(field_48517_d > 100)
        {
            field_1065_a.field_6308_u.func_22064_c("demo.reminder");
            field_48517_d = 0;
        }
    }

    public void func_719_a(int p_719_1_, int p_719_2_, int p_719_3_, int p_719_4_)
    {
        if(field_48518_c)
        {
            func_48516_j();
            return;
        } else
        {
            super.func_719_a(p_719_1_, p_719_2_, p_719_3_, p_719_4_);
            return;
        }
    }

    public void func_6470_c(int p_6470_1_, int p_6470_2_, int p_6470_3_, int p_6470_4_)
    {
        if(field_48518_c)
        {
            return;
        } else
        {
            super.func_6470_c(p_6470_1_, p_6470_2_, p_6470_3_, p_6470_4_);
            return;
        }
    }

    public boolean func_729_b(int p_729_1_, int p_729_2_, int p_729_3_, int p_729_4_)
    {
        if(field_48518_c)
        {
            return false;
        } else
        {
            return super.func_729_b(p_729_1_, p_729_2_, p_729_3_, p_729_4_);
        }
    }

    public boolean func_6471_a(EntityPlayer p_6471_1_, World p_6471_2_, ItemStack p_6471_3_)
    {
        if(field_48518_c)
        {
            func_48516_j();
            return false;
        } else
        {
            return super.func_6471_a(p_6471_1_, p_6471_2_, p_6471_3_);
        }
    }

    public boolean func_722_a(EntityPlayer p_722_1_, World p_722_2_, ItemStack p_722_3_, int p_722_4_, int p_722_5_, int p_722_6_, int p_722_7_)
    {
        if(field_48518_c)
        {
            func_48516_j();
            return false;
        } else
        {
            return super.func_722_a(p_722_1_, p_722_2_, p_722_3_, p_722_4_, p_722_5_, p_722_6_, p_722_7_);
        }
    }

    public void func_6472_b(EntityPlayer p_6472_1_, Entity p_6472_2_)
    {
        if(field_48518_c)
        {
            func_48516_j();
            return;
        } else
        {
            super.func_6472_b(p_6472_1_, p_6472_2_);
            return;
        }
    }
}
