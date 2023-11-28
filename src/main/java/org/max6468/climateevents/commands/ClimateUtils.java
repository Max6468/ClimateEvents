package org.max6468.climateevents.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
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






    public void climateCreate(CommandSender commandSender, Command command, String s, String[] args){
        FileConfiguration config = plugin.getConfig();
        config.set("climates." + args[1] + ".frequency", 0);
        config.set("climates." + args[1] + ".duration", 0);
        config.set("climates." + args[1] + ".effects", 0);
        this.plugin.saveConfig();

        this.commandSender = commandSender;
        this.command = command;
        this.args = args;


        climateCreateMenuFrequency(commandSender, args[1], args);
        //climateCreateMenuDuration(commandSender, args[1], args);
        //climateCreateMenuEffects(commandSender, args[1], args);
        

    }

    public void climateEditFrequency(String[] args){
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

    public void climateEditDuration(String[] args){
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

    public void climateEditEffects(String[] args){
        FileConfiguration config = plugin.getConfig();
        config.set("climates." + args[1] + ".effects", args[3]);
        this.plugin.saveConfig();
                mostramenu("Effects");

    }

    public void climateSave(){
        switch (currentMenu){
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


    public void climateCreateMenuFrequency(CommandSender commandSender, String campo, String[]args){
        this.commandSender = commandSender;


        message1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " frequency min+"));
        message3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " frequency sec+"));
        message2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " frequency min-"));
        message4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " frequency sec-"));
        messageOK.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " save"));
        mostramenu("Frequency");

        }
    public void climateCreateMenuDuration(CommandSender commandSender, String campo, String[]args){
        this.commandSender = commandSender;


        message1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " duration min+"));
        message3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " duration sec+"));
        message2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " duration min-"));
        message4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " duration sec-"));
        messageOK.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " save"));
        mostramenu("Duration");

    }
    public void climateCreateMenuEffects(CommandSender commandSender, String campo, String[]args){
        this.commandSender = commandSender;


        message1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " frequency "));
        message3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " frequency "));
        message2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " frequency "));
        message4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " frequency "));
        messageOK.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/climateevents edit " + campo + " save"));
        mostramenu("Effects");

    }

    private void saltoLinea(int cuanto){
        for (int i = cuanto;i>=0;i--) {
            commandSender.sendMessage("");
        }

    }
    public void mostramenu(String que){
        if (firstTime){
            message1.addExtra(message2);
            message3.addExtra(message4);
            firstTime = false;
        }
    if (que.equals("Frequency")) {
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
    } else if (que.equals("Duration")) {
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

    }else if (que.equals("Effects")) {

    }
    }
    }

    //Concepto: 3 menús, climateCreateMenuDuration, climateCreateMenuFrequency, climateCreateMenuEffects. Al abrir un
    //menú, que se repita cada ve que se haga un evento de onclick, cada menu tiene botones de sumar y resa minutos y
    // egundos. Una vez dado a save, que pase al siguiente menu



