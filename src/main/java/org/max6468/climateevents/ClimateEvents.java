package org.max6468.climateevents;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import org.max6468.climateevents.commands.MainCommand;
import org.max6468.climateevents.utils.MessageUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

public final class ClimateEvents extends JavaPlugin {


    public static String prefix = "&0&l[&dClimate&9Events&0&l]";
    private final String version = getDescription().getVersion();


    FileConfiguration config = this.getConfig();

    public void onEnable() {
        registerCommands();
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&l&6======================================"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&l&6|                                    &l&6|"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&l&6|      &l&dClimate Events Plugin         &l&6|"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&l&6|                                    &l&6|"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&l&6|              &l&aEabled                &l&6|"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&l&6|                                    &l&6|"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&l&6|       &l&9Version: " + espaciosAdd(20, version) + "&l&6|"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&l&6|                                    &l&6|"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&l&6======================================"));
        Bukkit.getConsoleSender().sendMessage("");
        insertConfig();
        eventsCreator();

    }


    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&l&6======================================"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&l&6|                                    &l&6|"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&l&6|      &l&dClimate Events Plugin         &l&6|"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&l&6|                                    &l&6|"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&l&6|              &l&cDisabled              &l&6|"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&l&6|                                    &l&6|"));
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&l&6======================================"));
        Bukkit.getConsoleSender().sendMessage("");
    }

    public void registerCommands() {
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

    public void insertConfig() {
        File config = new File(this.getDataFolder(), "config.yml");
        String ruta = config.getPath();

        if (!config.exists()) {

            this.getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
    }
    private void aplicarEfectoATodos(String tipoEfecto, int duracion, int amplificador) {
        for (Player jugador : Bukkit.getOnlinePlayers()) {
            try {
                jugador.addPotionEffect(new PotionEffect(PotionEffectType.getByName(tipoEfecto), duracion, amplificador));
            }catch (Exception e){
                Bukkit.getConsoleSender().sendMessage(String.valueOf(e));
            }

        }
    }
    public void eventsCreator(){
        FileConfiguration config = this.getConfig();

        if (config.contains("climates")) {
            ConfigurationSection climates = config.getConfigurationSection("climates");


            for (String climateName : climates.getKeys(false)) {
                ConfigurationSection climate = climates.getConfigurationSection(climateName);
                ConfigurationSection effecting = config.getConfigurationSection("climates." + climateName + ".effects");

                if (climate.contains("frequency")) {
                    int frequency = climate.getInt("frequency");
                    Runnable evento = () -> {
                    if (effecting != null) {
                        for (String effect : effecting.getKeys(false)) {
                            if (config.getBoolean("climates." + climateName + ".effects." + effect)) {
                                aplicarEfectoATodos(effect, config.getInt("climates." + climateName + ".duration") * 20, 1);

                            }
                        }
}

                    };

                    // Programar el evento según la frecuencia
                    Bukkit.getScheduler().runTaskTimer(this, evento, 0L, frequency * 20L); // Multiplicar por 20 para convertir a ticks
                } else {
                    Bukkit.getConsoleSender().sendMessage("Falta la frecuencia para el clima: " + climateName);
                }
            }
        } else {
            Bukkit.getConsoleSender().sendMessage("La sección 'climates' no está presente en el archivo de configuración.");
        }


    }



}
