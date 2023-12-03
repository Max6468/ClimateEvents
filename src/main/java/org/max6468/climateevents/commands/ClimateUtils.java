package org.max6468.climateevents.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
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
    boolean firstTimeCreate = true;
    boolean firstTimeDelete = true;


    TextComponent message1 = new TextComponent("+ minutes   ");
    TextComponent message2 = new TextComponent("- minutes");
    TextComponent message3 = new TextComponent("+ seconds   ");
    TextComponent message4 = new TextComponent("- seconds");
    TextComponent messageOK = new TextComponent("OK");

    //Effects

    TextComponent poison = new TextComponent("Poison  ");
    TextComponent regeneration = new TextComponent("Regeneration  ");
    TextComponent saturation = new TextComponent("Saturation  ");
    TextComponent levitation = new TextComponent("Levitation  ");
    TextComponent blindness = new TextComponent("Blindness  ");

    //Aceptar / Denegar

    TextComponent accept = new TextComponent("Accept   ");
    TextComponent decline = new TextComponent("Decline");
    //HELP
    TextComponent help = new TextComponent("Help");


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
            case "blindness":
                if (blindness.getColor().equals(ChatColor.RED)) {
                    blindness.setColor(ChatColor.GREEN);
                    config.set("climates." + args[1] + ".effects.blindness", true);
                    this.plugin.saveConfig();

                } else {
                    blindness.setColor(ChatColor.RED);
                    config.set("climates." + args[1] + ".effects.blindness", false);
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
            case 3:
                min = 0;
                sec = 0;
                commandSender.sendMessage("Successfully created climate!");
                plugin.eventsCreator();

        }
    }


    public void climateCreateMenuFrequency(CommandSender commandSender, String campo, String[] args) {
        this.commandSender = commandSender;

        this.currentMenu = 1;
        poison.setColor(ChatColor.RED);
        blindness.setColor(ChatColor.RED);
        levitation.setColor(ChatColor.RED);
        regeneration.setColor(ChatColor.RED);
        saturation.setColor(ChatColor.RED);


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


        blindness.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " effects blindness"));
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
        if (firstTimeCreate) {
            message1.addExtra(message2);
            message3.addExtra(message4);

            poison.setColor(ChatColor.RED);
            blindness.setColor(ChatColor.RED);
            levitation.setColor(ChatColor.RED);
            regeneration.setColor(ChatColor.RED);
            saturation.setColor(ChatColor.RED);

            poison.addExtra(blindness);
            blindness.addExtra(levitation);
            levitation.addExtra(regeneration);
            regeneration.addExtra(saturation);

            firstTimeCreate = false;
        }
        switch (que) {
            case "Frequency":
                saltoLinea(20);
                commandSender.sendMessage("Select frequency:");
                commandSender.sendMessage("Frequency: " + min + " min " + sec + " sec");
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
                commandSender.sendMessage("Select duration:");
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
                commandSender.sendMessage("Click on the effects that you want to use:");
                commandSender.sendMessage(" ");
                commandSender.spigot().sendMessage(poison);
                commandSender.sendMessage(" ");
                commandSender.spigot().sendMessage(messageOK);

                break;
        }
    }

    public void borrarClimaPreguntar(CommandSender commandSender, Command command, String s, String[] args) {
        if (firstTimeDelete) {
            accept.addExtra(decline);
            accept.setColor(ChatColor.GREEN);
            accept.setBold(true);
            decline.setColor(ChatColor.RED);
            decline.setBold(false);
            firstTimeDelete = false;
        }
        accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents accept " + args[1]));
        decline.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents decline " + args[1]));


        commandSender.sendMessage("");
        commandSender.sendMessage("Do you want to delete  " + args[1] + "?");
        commandSender.sendMessage("");
        commandSender.spigot().sendMessage(accept);


    }

    public void acceptBorrarClima(CommandSender commandSender, Command command, String s, String[] args) {
        FileConfiguration config = plugin.getConfig();
        commandSender.sendMessage("");
        commandSender.sendMessage("Deleting  " + args[1]);
        commandSender.sendMessage("");
        config.set("climates." + args[1], null);
        this.plugin.saveConfig();
        if (config.get("climates." + args[1]) == null) {
            commandSender.sendMessage("Correctly deleted");
        } else {
            commandSender.sendMessage("There was a deletion problem");
            commandSender.sendMessage((String) config.get("climates." + args[1]));
        }


    }

    public void declineBorrarClima(CommandSender commandSender, Command command, String s, String[] args) {
        commandSender.sendMessage("");
        commandSender.sendMessage("Coming out of deletion...");
        commandSender.sendMessage("");

    }

    public void help(CommandSender commandSender) {
        help.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/Max6468/ClimateEvents/blob/main/README.md"));
        help.setBold(true);
        help.setColor(ChatColor.GOLD);

    }

    public void climateList(CommandSender commandSender) {
        FileConfiguration config = plugin.getConfig();
        ConfigurationSection climates = config.getConfigurationSection("climates");
        for (String climateName : climates.getKeys(false)) {
            commandSender.sendMessage(climateName);
        }


    }
}

//Concepto: 3 menús, climateCreateMenuDuration, climateCreateMenuFrequency, climateCreateMenuEffects. Al abrir un
//menú, que se repita cada ve que se haga un evento de onclick, cada menu tiene botones de sumar y resa minutos y
// egundos. Una vez dado a save, que pase al siguiente menu



