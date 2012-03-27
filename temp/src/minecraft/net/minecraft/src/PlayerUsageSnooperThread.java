// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;
import java.net.*;

// Referenced classes of package net.minecraft.src:
//            PlayerUsageSnooper

class PlayerUsageSnooperThread extends Thread
{

    final PlayerUsageSnooper field_48553_a; /* synthetic field */

    PlayerUsageSnooperThread(PlayerUsageSnooper p_i1050_1_, String p_i1050_2_)
    {
        field_48553_a = p_i1050_1_;
        super(p_i1050_2_);
    }

    public void run()
    {
        try
        {
            String s = (new StringBuilder()).append("fName=").append(URLEncoder.encode("???", "UTF-8")).append("&lName=").append(URLEncoder.encode("???", "UTF-8")).toString();
            URL url = new URL("http://snoop.minecraft.net/");
            HttpURLConnection httpurlconnection = (HttpURLConnection)url.openConnection();
            httpurlconnection.setRequestMethod("POST");
            httpurlconnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpurlconnection.setRequestProperty("Content-Length", (new StringBuilder()).append("").append(Integer.toString(s.getBytes().length)).toString());
            httpurlconnection.setRequestProperty("Content-Language", "en-US");
            httpurlconnection.setUseCaches(false);
            httpurlconnection.setDoInput(true);
            httpurlconnection.setDoOutput(true);
            DataOutputStream dataoutputstream = new DataOutputStream(httpurlconnection.getOutputStream());
            dataoutputstream.writeBytes(s);
            dataoutputstream.flush();
            dataoutputstream.close();
            java.io.InputStream inputstream = httpurlconnection.getInputStream();
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
            StringBuffer stringbuffer = new StringBuffer();
            String s1;
            while((s1 = bufferedreader.readLine()) != null) 
            {
                stringbuffer.append(s1);
                stringbuffer.append('\r');
            }
            bufferedreader.close();
        }
        catch(Exception exception) { }
    }
}
