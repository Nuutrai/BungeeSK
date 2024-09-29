package fr.zorg.bungeesk.velocity.utils;

import fr.zorg.bungeesk.velocity.BungeeSK;

public class Debug {

    public static void log(String... text) {
        if ((boolean) BungeeConfig.DEBUG.get())
            for (String line : text)
                BungeeSK.getLogger().atInfo().log(line);
    }

    public static void throwEx(Exception ex) {
        if ((boolean) BungeeConfig.DEBUG.get())
            ex.printStackTrace();
    }

}