package org.max6468.climateevents.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.max6468.climateevents.ClimateEvents;

public class ClimateUtils {
    private final ClimateEvents plugin;
    private CommandSender commandSender;
    String[] args;
    Command command;

    public ClimateUtils(ClimateEvents plugin) {
        this.plugin = plugin;
    }

    int min = 0, sec = 0, currentMenu = 1;
    boolean firstTime = true;


    TextComponent message1 = new TextComponent("+ minutos   ");
    TextComponent message2 = new TextComponent("- minutos");
    TextComponent message3 = new TextComponent("+ segundos   ");
    TextComponent message4 = new TextComponent("- segundos");
    TextComponent messageOK = new TextComponent("OK");

    //Effects

    TextComponent poison = new TextComponent("Poison ");
    TextComponent regeneration = new TextComponent("Regeneration ");
    TextComponent saturation = new TextComponent("Saturation");
    TextComponent levitation = new TextComponent("Levitation");
    TextComponent darkness = new TextComponent("Darkness");


    public void climateCreate(CommandSender commandSender, Command command, String s, String[] args) {
        FileConfiguration config = plugin.getConfig();
        config.set("climates." + args[1] + ".frequency", 0);
        config.set("climates." + args[1] + ".duration", 0);
        config.set("climates." + args[1] + ".effects", 0);
        this.plugin.saveConfig();

        this.commandSender = commandSender;
        this.command = command;
        this.args = args;


        climateCreateMenuFrequency(commandSender, args[1], args);


    }

    public void climateEditFrequency(String[] args) {
        int toutal = 0;
        FileConfiguration config = plugin.getConfig();
        switch (args[3]) {
            case "min+":
                min++;
                toutal = min * 60 + sec;
                break;
            case "min-":
                if (!(min == 0)) {
                    min--;
                }
                toutal = min * 60 + sec;
                break;
            case "sec+":
                sec++;
                toutal = min * 60 + sec;
                break;
            case "sec-":
                if (!(sec == 0)) {
                    sec--;
                }
                toutal = min * 60 + sec;
                break;
        }
        config.set("climates." + args[1] + ".frequency", toutal);
        this.plugin.saveConfig();
        mostramenu("Frequency");


    }

    public void climateEditDuration(String[] args) {
        int toutal = 0;
        FileConfiguration config = plugin.getConfig();
        switch (args[3]) {
            case "min+":
                min++;
                toutal = min * 60 + sec;
                break;
            case "min-":
                if (!(min == 0)) {
                    min--;
                }
                toutal = min * 60 + sec;
                break;
            case "sec+":
                sec++;
                toutal = min * 60 + sec;
                break;
            case "sec-":
                if (!(sec == 0)) {
                    sec--;
                }
                toutal = min * 60 + sec;
                break;
        }
        config.set("climates." + args[1] + ".duration", toutal);
        this.plugin.saveConfig();
        mostramenu("Duration");

    }

    public void climateEditEffects(String[] args) {
        FileConfiguration config = plugin.getConfig();
        switch (args[3]) {
            case "poison":
                if (poison.getColor().equals(ChatColor.RED)) {
                    poison.setColor(ChatColor.GREEN);
                    config.set("climates." + args[1] + ".effects.poison", true);
                    this.plugin.saveConfig();

                } else {
                    poison.setColor(ChatColor.RED);
                    config.set("climates." + args[1] + ".effects.poison", false);
                    this.plugin.saveConfig();
                }

                break;
            case "darkness":
                if (darkness.getColor().equals(ChatColor.RED)) {
                    darkness.setColor(ChatColor.GREEN);
                    config.set("climates." + args[1] + ".effects.darkness", true);
                    this.plugin.saveConfig();

                } else {
                    darkness.setColor(ChatColor.RED);
                    config.set("climates." + args[1] + ".effects.darkness", false);
                    this.plugin.saveConfig();
                }
                break;
            case "levitation":
                if (levitation.getColor().equals(ChatColor.RED)) {
                    levitation.setColor(ChatColor.GREEN);
                    config.set("climates." + args[1] + ".effects.levitation", true);
                    this.plugin.saveConfig();

                } else {
                    levitation.setColor(ChatColor.RED);
                    config.set("climates." + args[1] + ".effects.levitation", false);
                    this.plugin.saveConfig();
                }
                break;
            case "regeneration":
                if (regeneration.getColor().equals(ChatColor.RED)) {
                    regeneration.setColor(ChatColor.GREEN);
                    config.set("climates." + args[1] + ".effects.regeneration", true);
                    this.plugin.saveConfig();

                } else {
                    regeneration.setColor(ChatColor.RED);
                    config.set("climates." + args[1] + ".effects.regeneration", false);
                    this.plugin.saveConfig();
                }
                break;
            case "saturation":
                if (saturation.getColor().equals(ChatColor.RED)) {
                    saturation.setColor(ChatColor.GREEN);
                    config.set("climates." + args[1] + ".effects.saturation", true);
                    this.plugin.saveConfig();

                } else {
                    saturation.setColor(ChatColor.RED);
                    config.set("climates." + args[1] + ".effects.saturation", false);
                    this.plugin.saveConfig();
                }
                break;
        }
        mostramenu("Effects");

    }

    public void climateSave() {
        switch (currentMenu) {
            case 1:
                min = 0;
                sec = 0;
                climateCreateMenuDuration(commandSender, args[1], args);
                currentMenu++;
                break;
            case 2:
                min = 0;
                sec = 0;
                climateCreateMenuEffects(commandSender, args[1], args);
                currentMenu++;
                break;

        }
    }


    public void climateCreateMenuFrequency(CommandSender commandSender, String campo, String[] args) {
        this.commandSender = commandSender;


        message1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " frequency min+"));
        message3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " frequency sec+"));
        message2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " frequency min-"));
        message4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " frequency sec-"));
        messageOK.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " save"));
        mostramenu("Frequency");

    }

    public void climateCreateMenuDuration(CommandSender commandSender, String campo, String[] args) {
        this.commandSender = commandSender;


        message1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " duration min+"));
        message3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " duration sec+"));
        message2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " duration min-"));
        message4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " duration sec-"));
        messageOK.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " save"));
        mostramenu("Duration");

    }

    public void climateCreateMenuEffects(CommandSender commandSender, String campo, String[] args) {

        this.commandSender = commandSender;


        darkness.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " effects darkness"));
        levitation.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " effects levitation"));
        regeneration.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " effects regeneration"));
        saturation.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " effects saturation"));
        poison.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " effects poison"));
        messageOK.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " save"));
        mostramenu("Effects");

    }

    private void saltoLinea(int cuanto) {
        for (int i = cuanto; i >= 0; i--) {
            commandSender.sendMessage("");
        }

    }

    public void mostramenu(String que) {
        if (firstTime) {
            message1.addExtra(message2);
            message3.addExtra(message4);

            poison.setColor(ChatColor.RED);
            darkness.setColor(ChatColor.RED);
            levitation.setColor(ChatColor.RED);
            regeneration.setColor(ChatColor.RED);
            saturation.setColor(ChatColor.RED);

            poison.addExtra(darkness);
            darkness.addExtra(levitation);
            levitation.addExtra(regeneration);
            regeneration.addExtra(saturation);

            firstTime = false;
        }
        switch (que) {
            case "Frequency":
                saltoLinea(20);
                commandSender.sendMessage("Selecciona la frecuencia:");
                commandSender.sendMessage("Frecuencia: " + min + " min " + sec + " sec");
                commandSender.sendMessage(" ");
                message1.setBold(true);
                message2.setBold(true);
                message1.setColor(ChatColor.GREEN);
                message2.setColor(ChatColor.RED);

                commandSender.spigot().sendMessage(message1);
                message3.setBold(true);
                message4.setBold(true);
                message3.setColor(ChatColor.GREEN);
                message4.setColor(ChatColor.RED);
                commandSender.spigot().sendMessage(message3);
                commandSender.sendMessage(" ");
                commandSender.spigot().sendMessage(messageOK);
                break;
            case "Duration":
                saltoLinea(20);
                commandSender.sendMessage("Selecciona la Duración:");
                commandSender.sendMessage("Duration: " + min + " min " + sec + " sec");
                commandSender.sendMessage(" ");
                message1.setBold(true);
                message2.setBold(true);
                message1.setColor(ChatColor.GREEN);
                message2.setColor(ChatColor.RED);
                commandSender.spigot().sendMessage(message1);
                message3.setBold(true);
                message4.setBold(true);
                message3.setColor(ChatColor.GREEN);
                message4.setColor(ChatColor.RED);
                commandSender.spigot().sendMessage(message3);
                commandSender.sendMessage(" ");
                commandSender.spigot().sendMessage(messageOK);

                break;
            case "Effects":
                saltoLinea(20);
                commandSender.sendMessage("Para seleccionar uno, dale click:");
                commandSender.sendMessage(" ");
                commandSender.spigot().sendMessage(poison);

                break;
        }
    }
}

//Concepto: 3 menús, climateCreateMenuDuration, climateCreateMenuFrequency, climateCreateMenuEffects. Al abrir un
//menú, que se repita cada ve que se haga un evento de onclick, cada menu tiene botones de sumar y resa minutos y
// egundos. Una vez dado a save, que pase al siguiente menu



