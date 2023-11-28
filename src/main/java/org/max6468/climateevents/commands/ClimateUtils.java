package org.max6468.climateevents.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.max6468.climateevents.ClimateEvents;

import java.util.concurrent.TimeUnit;

public class ClimateUtils {
    private final ClimateEvents plugin;
    private CommandSender commandSender;

    public ClimateUtils(ClimateEvents plugin) {
        this.plugin = plugin;
    }
    int min = 0, sec = 0;




    TextComponent message1 = new TextComponent("+ minutos");
    TextComponent message2 = new TextComponent("+ segundos");
    TextComponent message3 = new TextComponent("- minutos");
    TextComponent message4 = new TextComponent("- minutos");
    TextComponent messageOK = new TextComponent("OK");




    public void climateCreate(CommandSender commandSender, Command command, String s, String[] args){
        FileConfiguration config = plugin.getConfig();
        config.set("climates." + args[1] + ".frequency", 0);
        config.set("climates." + args[1] + ".duration", 0);
        config.set("climates." + args[1] + ".effects", 0);
        this.plugin.saveConfig();

        this.commandSender = commandSender;

        climateCreateMenu(commandSender, args[1], args);

    }

    public void climateEditFrequency(String[] args){
        FileConfiguration config = plugin.getConfig();
        config.set("climates." + args[1] + ".frequency", args[3]);
        this.plugin.saveConfig();
        //TODO: Poner en ifs separados, no en 1
        if (args[4] != null && args[4].equals("continue")){
            mostramenu();
        }


    }

    public void climateEditDuration(String[] args){
        FileConfiguration config = plugin.getConfig();
        config.set("climates." + args[1] + ".duration", args[3]);
        this.plugin.saveConfig();
        if (args[4] != null && args[4].equals("continue")){
            mostramenu();
        }

    }

    public void climateEditEffects(String[] args){
        FileConfiguration config = plugin.getConfig();
        config.set("climates." + args[1] + ".effects", args[3]);
        this.plugin.saveConfig();
        if (args[4] != null && args[4].equals("continue")){
            mostramenu();
        }

    }

    public void climateSave(){

    }


    public void climateCreateMenu(CommandSender commandSender, String campo, String[]args){
        Bukkit.getConsoleSender().sendMessage(args);
        this.commandSender = commandSender;

            mostramenu();


        message1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " frequency " + (min + 1) * 60 + sec));
        message2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " frequency " + min * 60 + sec + 1));
        message3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " frequency " + (min - 1) * 60 + sec));
        message4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " frequency " + min * 60 + (sec - 1)));
        messageOK.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " save"));
        }


    public void mostramenu(){

        commandSender.sendMessage("Selecciona la frecuencia en segundos:");
        commandSender.sendMessage("Frecuencia: " + min + " min " + sec + " sec");
        commandSender.spigot().sendMessage(message1);
        commandSender.spigot().sendMessage(message2);
        commandSender.spigot().sendMessage(message3);
        commandSender.spigot().sendMessage(message4);
        commandSender.spigot().sendMessage(messageOK);
    }
    }



