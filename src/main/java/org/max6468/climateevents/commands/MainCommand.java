package org.max6468.climateevents.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.max6468.climateevents.ClimateEvents;

public class MainCommand implements CommandExecutor {

    private final ClimateUtils climateUtils;

    public MainCommand(ClimateEvents plugin) {
        this.climateUtils = new ClimateUtils(plugin);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        // ...
        if (args.length >= 1) {
            switch (args.length) {
                case 1:
                    switch (args[0]){
                        case "list":
                            climateUtils.climateList(commandSender);
                            break;
                    }
                    break;
                case 2:
                    switch (args[0]) {
                        case "create":
                            climateUtils.climateCreate(commandSender, command, s, args);
                            break;
                        case "delete":
                            climateUtils.borrarClimaPreguntar(commandSender, command, s, args);
                            break;
                        case "help":
                            climateUtils.help(commandSender);
                            break;
                        case "accept":
                            climateUtils.acceptBorrarClima(commandSender, command, s, args);
                            break;
                        case "decline":
                            climateUtils.declineBorrarClima(commandSender, command, s, args);
                            break;

                    }
                    break;
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    if (args[0].equals("edit")) {

                        switch (args[2]) {
                            case "frequency":
                                climateUtils.climateEditFrequency(args);
                                break;
                            case "duration":
                                climateUtils.climateEditDuration(args);
                                break;
                            case "effects":
                                climateUtils.climateEditEffects(args);
                                break;
                            case "save":
                                climateUtils.climateSave();
                                break;
                        }
                    }
                    break;

                default:
                    commandSender.sendMessage("Nada furula en el listener de comandos");
                    commandSender.sendMessage(args);
            }
        }
        return true;
    }
}