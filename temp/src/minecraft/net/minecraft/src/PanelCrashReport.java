// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.TextArea;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

// Referenced classes of package net.minecraft.src:
//            UnexpectedThrowable, ModLoader, BaseMod, CanvasMojangLogo, 
//            CanvasCrashReport

public class PanelCrashReport extends Panel
{

    public PanelCrashReport(UnexpectedThrowable p_i634_1_)
    {
        setBackground(new Color(0x2e3444));
        setLayout(new BorderLayout());
        StringWriter stringwriter = new StringWriter();
        p_i634_1_.field_6514_b.printStackTrace(new PrintWriter(stringwriter));
        String s = stringwriter.toString();
        String s1 = "";
        String s2 = "";
        try
        {
            s2 = (new StringBuilder(String.valueOf(s2))).append("Generated ").append((new SimpleDateFormat()).format(new Date())).append("\n").toString();
            s2 = (new StringBuilder(String.valueOf(s2))).append("\n").toString();
            s2 = (new StringBuilder(String.valueOf(s2))).append("Minecraft: Minecraft 1.2.4\n").toString();
            s2 = (new StringBuilder(String.valueOf(s2))).append("OS: ").append(System.getProperty("os.name")).append(" (").append(System.getProperty("os.arch")).append(") version ").append(System.getProperty("os.version")).append("\n").toString();
            s2 = (new StringBuilder(String.valueOf(s2))).append("Java: ").append(System.getProperty("java.version")).append(", ").append(System.getProperty("java.vendor")).append("\n").toString();
            s2 = (new StringBuilder(String.valueOf(s2))).append("VM: ").append(System.getProperty("java.vm.name")).append(" (").append(System.getProperty("java.vm.info")).append("), ").append(System.getProperty("java.vm.vendor")).append("\n").toString();
            s2 = (new StringBuilder(String.valueOf(s2))).append("LWJGL: ").append(Sys.getVersion()).append("\n").toString();
            s1 = GL11.glGetString(7936);
            s2 = (new StringBuilder(String.valueOf(s2))).append("OpenGL: ").append(GL11.glGetString(7937)).append(" version ").append(GL11.glGetString(7938)).append(", ").append(GL11.glGetString(7936)).append("\n").toString();
        }
        catch(Throwable throwable)
        {
            s2 = (new StringBuilder(String.valueOf(s2))).append("[failed to get system properties (").append(throwable).append(")]\n").toString();
        }
        s2 = (new StringBuilder(String.valueOf(s2))).append("\n").toString();
        s2 = (new StringBuilder(String.valueOf(s2))).append(s).toString();
        String s3 = "";
        s3 = (new StringBuilder(String.valueOf(s3))).append("Mods loaded: ").append(ModLoader.getLoadedMods().size() + 1).append("\n").toString();
        s3 = (new StringBuilder(String.valueOf(s3))).append("ModLoader 1.2.4").append("\n").toString();
        for(Iterator iterator = ModLoader.getLoadedMods().iterator(); iterator.hasNext();)
        {
            BaseMod basemod = (BaseMod)iterator.next();
            s3 = (new StringBuilder(String.valueOf(s3))).append(basemod.getName()).append(" ").append(basemod.getVersion()).append("\n").toString();
        }

        s3 = (new StringBuilder(String.valueOf(s3))).append("\n").toString();
        if(s.contains("Pixel format not accelerated"))
        {
            s3 = (new StringBuilder(String.valueOf(s3))).append("      Bad video card drivers!      \n").toString();
            s3 = (new StringBuilder(String.valueOf(s3))).append("      -----------------------      \n").toString();
            s3 = (new StringBuilder(String.valueOf(s3))).append("\n").toString();
            s3 = (new StringBuilder(String.valueOf(s3))).append("Minecraft was unable to start because it failed to find an accelerated OpenGL mode.\n").toString();
            s3 = (new StringBuilder(String.valueOf(s3))).append("This can usually be fixed by updating the video card drivers.\n").toString();
            if(s1.toLowerCase().contains("nvidia"))
            {
                s3 = (new StringBuilder(String.valueOf(s3))).append("\n").toString();
                s3 = (new StringBuilder(String.valueOf(s3))).append("You might be able to find drivers for your video card here:\n").toString();
                s3 = (new StringBuilder(String.valueOf(s3))).append("  http://www.nvidia.com/\n").toString();
            } else
            if(s1.toLowerCase().contains("ati"))
            {
                s3 = (new StringBuilder(String.valueOf(s3))).append("\n").toString();
                s3 = (new StringBuilder(String.valueOf(s3))).append("You might be able to find drivers for your video card here:\n").toString();
                s3 = (new StringBuilder(String.valueOf(s3))).append("  http://www.amd.com/\n").toString();
            }
        } else
        {
            s3 = (new StringBuilder(String.valueOf(s3))).append("      Minecraft has crashed!      \n").toString();
            s3 = (new StringBuilder(String.valueOf(s3))).append("      ----------------------      \n").toString();
            s3 = (new StringBuilder(String.valueOf(s3))).append("\n").toString();
            s3 = (new StringBuilder(String.valueOf(s3))).append("Minecraft has stopped running because it encountered a problem.\n").toString();
            s3 = (new StringBuilder(String.valueOf(s3))).append("\n").toString();
        }
        s3 = (new StringBuilder(String.valueOf(s3))).append("\n").toString();
        s3 = (new StringBuilder(String.valueOf(s3))).append("\n").toString();
        s3 = (new StringBuilder(String.valueOf(s3))).append("\n").toString();
        s3 = (new StringBuilder(String.valueOf(s3))).append("--- BEGIN ERROR REPORT ").append(Integer.toHexString(s3.hashCode())).append(" --------\n").toString();
        s3 = (new StringBuilder(String.valueOf(s3))).append(s2).toString();
        s3 = (new StringBuilder(String.valueOf(s3))).append("--- END ERROR REPORT ").append(Integer.toHexString(s3.hashCode())).append(" ----------\n").toString();
        s3 = (new StringBuilder(String.valueOf(s3))).append("\n").toString();
        s3 = (new StringBuilder(String.valueOf(s3))).append("\n").toString();
        TextArea textarea = new TextArea(s3, 0, 0, 1);
        textarea.setFont(new Font("Monospaced", 0, 12));
        add(new CanvasMojangLogo(), "North");
        add(new CanvasCrashReport(80), "East");
        add(new CanvasCrashReport(80), "West");
        add(new CanvasCrashReport(100), "South");
        add(textarea, "Center");
    }
}
