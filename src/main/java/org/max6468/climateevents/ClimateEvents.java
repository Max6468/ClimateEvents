package org.max6468.climateevents;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.max6468.climateevents.commands.MainCommand;
import org.max6468.climateevents.utils.MessageUtils;

import java.io.File;

public final class ClimateEvents extends JavaPlugin {


    public static String prefix = "&0&l[&dClimate&9Events&0&l]";
    private String version = getDescription().getVersion();


    FileConfiguration config = this.getConfig();

    public void onEnable(){
        registerCommands();
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&l&6======================================"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage( "&l&6|                                    &l&6|"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage( "&l&6|      &l&dClimate Events Plugin         &l&6|"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage( "&l&6|                                    &l&6|"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage( "&l&6|              &l&aEabled               &l&6|"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage( "&l&6|                                    &l&6|"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage( "&l&6|       &l&9Version: " + espaciosAdd(20, version) + "&l&6|"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage( "&l&6|                                    &l&6|"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage( "&l&6======================================"));
        Bukkit.getConsoleSender().sendMessage("");
        insertConfig();

    }


    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage( "&l&6======================================"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage( "&l&6|                                    &l&6|"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage( "&l&6|      &l&dClimate Events Plugin         &l&6|"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage( "&l&6|                                    &l&6|"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage( "&l&6|              &l&cDisabled              &l&6|"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage( "&l&6|                                    &l&6|"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage( "&l&6======================================"));
        Bukkit.getConsoleSender().sendMessage("");
    }

    public void registerCommands(){
        this.getCommand("climateevents").setExecutor(new MainCommand(this));
    }





    private String espaciosAdd(int espacios, String texto) {
        int longitudTexto = texto.length();
        int espaciosQueFaltan = espacios - longitudTexto;
        String espaciosAñadir = "";
        for (int i = 0; i < espaciosQueFaltan; i++) {
            espaciosAñadir += " ";
        }
        return texto + espaciosAñadir;
    }

    public void insertConfig(){
        File config = new File(this.getDataFolder(), "config.yml");
        String ruta = config.getPath();

        if (!config.exists()) {

            this.getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
    }
}
