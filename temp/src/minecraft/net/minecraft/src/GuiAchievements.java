// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

// Referenced classes of package net.minecraft.src:
//            GuiScreen, AchievementList, Achievement, GuiSmallButton, 
//            StatCollector, GuiButton, GameSettings, KeyBinding, 
//            FontRenderer, MathHelper, RenderEngine, Block, 
//            StatFileWriter, RenderItem, RenderHelper

public class GuiAchievements extends GuiScreen
{

    private static final int field_27126_s;
    private static final int field_27125_t;
    private static final int field_27124_u;
    private static final int field_27123_v;
    protected int field_27121_a;
    protected int field_27119_i;
    protected int field_27118_j;
    protected int field_27117_l;
    protected double field_27116_m;
    protected double field_27115_n;
    protected double field_27114_o;
    protected double field_27113_p;
    protected double field_27112_q;
    protected double field_27111_r;
    private int field_27122_w;
    private StatFileWriter field_27120_x;

    public GuiAchievements(StatFileWriter p_i575_1_)
    {
        field_27121_a = 256;
        field_27119_i = 202;
        field_27118_j = 0;
        field_27117_l = 0;
        field_27122_w = 0;
        field_27120_x = p_i575_1_;
        char c = '\215';
        char c1 = '\215';
        field_27116_m = field_27114_o = field_27112_q = AchievementList.field_25195_b.field_25075_a * 24 - c / 2 - 12;
        field_27115_n = field_27113_p = field_27111_r = AchievementList.field_25195_b.field_25074_b * 24 - c1 / 2;
    }

    public void func_6448_a()
    {
        field_949_e.clear();
        field_949_e.add(new GuiSmallButton(1, field_951_c / 2 + 24, field_950_d / 2 + 74, 80, 20, StatCollector.func_25200_a("gui.done")));
    }

    protected void func_572_a(GuiButton p_572_1_)
    {
        if(p_572_1_.field_938_f == 1)
        {
            field_945_b.func_6272_a(null);
            field_945_b.func_6259_e();
        }
        super.func_572_a(p_572_1_);
    }

    protected void func_580_a(char p_580_1_, int p_580_2_)
    {
        if(p_580_2_ == field_945_b.field_6304_y.field_1570_o.field_1370_b)
        {
            field_945_b.func_6272_a(null);
            field_945_b.func_6259_e();
        } else
        {
            super.func_580_a(p_580_1_, p_580_2_);
        }
    }

    public void func_571_a(int p_571_1_, int p_571_2_, float p_571_3_)
    {
        if(Mouse.isButtonDown(0))
        {
            int i = (field_951_c - field_27121_a) / 2;
            int j = (field_950_d - field_27119_i) / 2;
            int k = i + 8;
            int l = j + 17;
            if((field_27122_w == 0 || field_27122_w == 1) && p_571_1_ >= k && p_571_1_ < k + 224 && p_571_2_ >= l && p_571_2_ < l + 155)
            {
                if(field_27122_w == 0)
                {
                    field_27122_w = 1;
                } else
                {
                    field_27114_o -= p_571_1_ - field_27118_j;
                    field_27113_p -= p_571_2_ - field_27117_l;
                    field_27112_q = field_27116_m = field_27114_o;
                    field_27111_r = field_27115_n = field_27113_p;
                }
                field_27118_j = p_571_1_;
                field_27117_l = p_571_2_;
            }
            if(field_27112_q < (double)field_27126_s)
            {
                field_27112_q = field_27126_s;
            }
            if(field_27111_r < (double)field_27125_t)
            {
                field_27111_r = field_27125_t;
            }
            if(field_27112_q >= (double)field_27124_u)
            {
                field_27112_q = field_27124_u - 1;
            }
            if(field_27111_r >= (double)field_27123_v)
            {
                field_27111_r = field_27123_v - 1;
            }
        } else
        {
            field_27122_w = 0;
        }
        func_578_i();
        func_27109_b(p_571_1_, p_571_2_, p_571_3_);
        GL11.glDisable(2896);
        GL11.glDisable(2929);
        func_27110_k();
        GL11.glEnable(2896);
        GL11.glEnable(2929);
    }

    public void func_570_g()
    {
        field_27116_m = field_27114_o;
        field_27115_n = field_27113_p;
        double d = field_27112_q - field_27114_o;
        double d1 = field_27111_r - field_27113_p;
        if(d * d + d1 * d1 < 4D)
        {
            field_27114_o += d;
            field_27113_p += d1;
        } else
        {
            field_27114_o += d * 0.84999999999999998D;
            field_27113_p += d1 * 0.84999999999999998D;
        }
    }

    protected void func_27110_k()
    {
        int i = (field_951_c - field_27121_a) / 2;
        int j = (field_950_d - field_27119_i) / 2;
        field_6451_g.func_873_b("Achievements", i + 15, j + 5, 0x404040);
    }

    protected void func_27109_b(int p_27109_1_, int p_27109_2_, float p_27109_3_)
    {
        int i = MathHelper.func_1108_b(field_27116_m + (field_27114_o - field_27116_m) * (double)p_27109_3_);
        int j = MathHelper.func_1108_b(field_27115_n + (field_27113_p - field_27115_n) * (double)p_27109_3_);
        if(i < field_27126_s)
        {
            i = field_27126_s;
        }
        if(j < field_27125_t)
        {
            j = field_27125_t;
        }
        if(i >= field_27124_u)
        {
            i = field_27124_u - 1;
        }
        if(j >= field_27123_v)
        {
            j = field_27123_v - 1;
        }
        int k = field_945_b.field_6315_n.func_1070_a("/terrain.png");
        int l = field_945_b.field_6315_n.func_1070_a("/achievement/bg.png");
        int i1 = (field_951_c - field_27121_a) / 2;
        int j1 = (field_950_d - field_27119_i) / 2;
        int k1 = i1 + 16;
        int l1 = j1 + 17;
        field_923_k = 0.0F;
        GL11.glDepthFunc(518);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, 0.0F, -200F);
        GL11.glEnable(3553);
        GL11.glDisable(2896);
        GL11.glEnable(32826);
        GL11.glEnable(2903);
        field_945_b.field_6315_n.func_1076_b(k);
        int i2 = i + 288 >> 4;
        int k2 = j + 288 >> 4;
        int l2 = (i + 288) % 16;
        int k3 = (j + 288) % 16;
        Random random = new Random();
        for(int j7 = 0; j7 * 16 - k3 < 155; j7++)
        {
            float f4 = 0.6F - ((float)(k2 + j7) / 25F) * 0.3F;
            GL11.glColor4f(f4, f4, f4, 1.0F);
            for(int k7 = 0; k7 * 16 - l2 < 224; k7++)
            {
                random.setSeed(1234 + i2 + k7);
                random.nextInt();
                int l7 = random.nextInt(1 + k2 + j7) + (k2 + j7) / 2;
                int i8 = Block.field_393_F.field_378_bb;
                if(l7 > 37 || k2 + j7 == 35)
                {
                    i8 = Block.field_403_A.field_378_bb;
                } else
                if(l7 == 22)
                {
                    if(random.nextInt(2) == 0)
                    {
                        i8 = Block.field_391_ax.field_378_bb;
                    } else
                    {
                        i8 = Block.field_433_aO.field_378_bb;
                    }
                } else
                if(l7 == 10)
                {
                    i8 = Block.field_388_I.field_378_bb;
                } else
                if(l7 == 8)
                {
                    i8 = Block.field_386_J.field_378_bb;
                } else
                if(l7 > 4)
                {
                    i8 = Block.field_338_u.field_378_bb;
                } else
                if(l7 > 0)
                {
                    i8 = Block.field_336_w.field_378_bb;
                }
                func_550_b((k1 + k7 * 16) - l2, (l1 + j7 * 16) - k3, i8 % 16 << 4, (i8 >> 4) << 4, 16, 16);
            }

        }

        GL11.glEnable(2929);
        GL11.glDepthFunc(515);
        GL11.glDisable(3553);
        for(int j2 = 0; j2 < AchievementList.field_27388_e.size(); j2++)
        {
            Achievement achievement1 = (Achievement)AchievementList.field_27388_e.get(j2);
            if(achievement1.field_25076_c == null)
            {
                continue;
            }
            int i3 = (achievement1.field_25075_a * 24 - i) + 11 + k1;
            int l3 = (achievement1.field_25074_b * 24 - j) + 11 + l1;
            int i4 = (achievement1.field_25076_c.field_25075_a * 24 - i) + 11 + k1;
            int k4 = (achievement1.field_25076_c.field_25074_b * 24 - j) + 11 + l1;
            int j5 = 0;
            boolean flag = field_27120_x.func_27183_a(achievement1);
            boolean flag1 = field_27120_x.func_27181_b(achievement1);
            char c = Math.sin(((double)(System.currentTimeMillis() % 600L) / 600D) * 3.1415926535897931D * 2D) <= 0.59999999999999998D ? '\202' : '\377';
            if(flag)
            {
                j5 = 0xff707070;
            } else
            if(flag1)
            {
                j5 = 65280 + (c << 24);
            } else
            {
                j5 = 0xff000000;
            }
            func_27100_a(i3, i4, l3, j5);
            func_27099_b(i4, l3, k4, j5);
        }

        Achievement achievement = null;
        RenderItem renderitem = new RenderItem();
        RenderHelper.func_41089_c();
        GL11.glDisable(2896);
        GL11.glEnable(32826);
        GL11.glEnable(2903);
        for(int j3 = 0; j3 < AchievementList.field_27388_e.size(); j3++)
        {
            Achievement achievement3 = (Achievement)AchievementList.field_27388_e.get(j3);
            int j4 = achievement3.field_25075_a * 24 - i;
            int l4 = achievement3.field_25074_b * 24 - j;
            if(j4 < -24 || l4 < -24 || j4 > 224 || l4 > 155)
            {
                continue;
            }
            if(field_27120_x.func_27183_a(achievement3))
            {
                float f = 1.0F;
                GL11.glColor4f(f, f, f, 1.0F);
            } else
            if(field_27120_x.func_27181_b(achievement3))
            {
                float f1 = Math.sin(((double)(System.currentTimeMillis() % 600L) / 600D) * 3.1415926535897931D * 2D) >= 0.59999999999999998D ? 0.8F : 0.6F;
                GL11.glColor4f(f1, f1, f1, 1.0F);
            } else
            {
                float f2 = 0.3F;
                GL11.glColor4f(f2, f2, f2, 1.0F);
            }
            field_945_b.field_6315_n.func_1076_b(l);
            int k5 = k1 + j4;
            int i6 = l1 + l4;
            if(achievement3.func_27093_f())
            {
                func_550_b(k5 - 2, i6 - 2, 26, 202, 26, 26);
            } else
            {
                func_550_b(k5 - 2, i6 - 2, 0, 202, 26, 26);
            }
            if(!field_27120_x.func_27181_b(achievement3))
            {
                float f3 = 0.1F;
                GL11.glColor4f(f3, f3, f3, 1.0F);
                renderitem.field_27004_a = false;
            }
            GL11.glEnable(2896);
            GL11.glEnable(2884);
            renderitem.func_161_a(field_945_b.field_6314_o, field_945_b.field_6315_n, achievement3.field_27097_d, k5 + 3, i6 + 3);
            GL11.glDisable(2896);
            if(!field_27120_x.func_27181_b(achievement3))
            {
                renderitem.field_27004_a = true;
            }
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            if(p_27109_1_ >= k1 && p_27109_2_ >= l1 && p_27109_1_ < k1 + 224 && p_27109_2_ < l1 + 155 && p_27109_1_ >= k5 && p_27109_1_ <= k5 + 22 && p_27109_2_ >= i6 && p_27109_2_ <= i6 + 22)
            {
                achievement = achievement3;
            }
        }

        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        field_945_b.field_6315_n.func_1076_b(l);
        func_550_b(i1, j1, 0, 0, field_27121_a, field_27119_i);
        GL11.glPopMatrix();
        field_923_k = 0.0F;
        GL11.glDepthFunc(515);
        GL11.glDisable(2929);
        GL11.glEnable(3553);
        super.func_571_a(p_27109_1_, p_27109_2_, p_27109_3_);
        if(achievement != null)
        {
            Achievement achievement2 = achievement;
            String s = StatCollector.func_25200_a(achievement2.func_44020_i());
            String s1 = achievement2.func_27090_e();
            int i5 = p_27109_1_ + 12;
            int l5 = p_27109_2_ - 4;
            if(field_27120_x.func_27181_b(achievement2))
            {
                int j6 = Math.max(field_6451_g.func_871_a(s), 120);
                int l6 = field_6451_g.func_27277_a(s1, j6);
                if(field_27120_x.func_27183_a(achievement2))
                {
                    l6 += 12;
                }
                func_549_a(i5 - 3, l5 - 3, i5 + j6 + 3, l5 + l6 + 3 + 12, 0xc0000000, 0xc0000000);
                field_6451_g.func_27278_a(s1, i5, l5 + 12, j6, 0xffa0a0a0);
                if(field_27120_x.func_27183_a(achievement2))
                {
                    field_6451_g.func_50103_a(StatCollector.func_25200_a("achievement.taken"), i5, l5 + l6 + 4, 0xff9090ff);
                }
            } else
            {
                int k6 = Math.max(field_6451_g.func_871_a(s), 120);
                String s2 = StatCollector.func_25199_a("achievement.requires", new Object[] {
                    StatCollector.func_25200_a(achievement2.field_25076_c.func_44020_i())
                });
                int i7 = field_6451_g.func_27277_a(s2, k6);
                func_549_a(i5 - 3, l5 - 3, i5 + k6 + 3, l5 + i7 + 12 + 3, 0xc0000000, 0xc0000000);
                field_6451_g.func_27278_a(s2, i5, l5 + 12, k6, 0xff705050);
            }
            field_6451_g.func_50103_a(s, i5, l5, field_27120_x.func_27181_b(achievement2) ? achievement2.func_27093_f() ? -128 : -1 : achievement2.func_27093_f() ? 0xff808040 : 0xff808080);
        }
        GL11.glEnable(2929);
        GL11.glEnable(2896);
        RenderHelper.func_1159_a();
    }

    public boolean func_6450_b()
    {
        return true;
    }

    static 
    {
        field_27126_s = AchievementList.field_27392_a * 24 - 112;
        field_27125_t = AchievementList.field_27391_b * 24 - 112;
        field_27124_u = AchievementList.field_27390_c * 24 - 77;
        field_27123_v = AchievementList.field_27389_d * 24 - 77;
    }
}
