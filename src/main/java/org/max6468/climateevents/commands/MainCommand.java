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
                    break;
                case 2:
                    if (args[0].equals("create")) {
                        climateUtils.climateCreate(commandSender, command, s, args);
                    } else if (args[0].equals("delete")) {
                        // ...
                    } else if (args[0].equals("help")) {
                        // ...
                    }
                    break;
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    if (args[0].equals("edit")) {

                        if (args[2].equals("frequency")) {
                            climateUtils.climateEditFrequency(args);
                        } else if (args[2].equals("duration")) {
                            climateUtils.climateEditDuration(args);
                        } else if (args[2].equals("effects")) {
                            climateUtils.climateEditEffects(args);
                        } else if (args[2].equals("save")) {
                            climateUtils.climateSave();
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