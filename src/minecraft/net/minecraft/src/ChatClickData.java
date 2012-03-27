package net.minecraft.src;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatClickData
{
    public static final Pattern field_50097_a = Pattern.compile("^(?:(https?)://)?([\\w-_\\.]{2,}\\.[a-z]{2,3})(/\\S+)?$");
    private final FontRenderer field_50095_b;
    private final ChatLine field_50096_c;
    private final int field_50093_d;
    private final int field_50094_e;
    private final String field_50091_f;
    private final String field_50092_g = func_50090_c();

    public ChatClickData(FontRenderer par1FontRenderer, ChatLine par2ChatLine, int par3, int par4)
    {
        field_50095_b = par1FontRenderer;
        field_50096_c = par2ChatLine;
        field_50093_d = par3;
        field_50094_e = par4;
        field_50091_f = par1FontRenderer.func_50107_a(par2ChatLine.message, par3);
    }

    public String func_50088_a()
    {
        return field_50092_g;
    }

    public URI func_50089_b()
    {
        URI uri = null;
        String s = func_50088_a();

        if (s == null)
        {
            return null;
        }

        Matcher matcher = field_50097_a.matcher(s);

        if (matcher.matches())
        {
            try
            {
                uri = new URI(matcher.group(0));
            }
            catch (URISyntaxException urisyntaxexception)
            {
                Logger.getLogger("Minecraft").log(Level.SEVERE, "Couldn't create URI from chat", urisyntaxexception);
            }
        }

        return uri;
    }

    private String func_50090_c()
    {
        int i = field_50091_f.lastIndexOf(" ", field_50091_f.length()) + 1;

        if (i < 0)
        {
            i = 0;
        }

        int j = field_50096_c.message.indexOf(" ", i);

        if (j < 0)
        {
            j = field_50096_c.message.length();
        }

        return field_50096_c.message.substring(i, j);
    }
}
