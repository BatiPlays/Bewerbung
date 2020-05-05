package com.homestream.HomeStream.main.console;

import com.homestream.HomeStream.main.assets.Assets;
import com.homestream.HomeStream.main.assets.MessageBuilder;
import com.homestream.HomeStream.main.assets.property.Properties;
import com.homestream.HomeStream.main.exception.CommandException;
import com.homestream.HomeStream.main.exception.CommandItemException;
import com.homestream.HomeStream.main.exception.CommandSyntaxException;

public class ConsoleDecrypts
{
    String command;

    /**
     * Console Decrypts
     *
     * Value Shortcuts:
     * -- = no change
     * -<value> = remove this from list without override
     * +<value> = add this to list without override
     *
     * Commands:
     * help = print all Commands
     *
     * info = print General Server Information's
     * info user = print User Information's
     * info media = print Media Information's
     * info music = print Music Media Information's
     * info image = print Image Media Information's
     * info video = print Video Media Information's
     *
     * create user <Name> <Password> = create new User
     * create roll <Name> <permissions>[array] = create new Roll
     * edit user <userID> <Name> <Password> = edit User y ID
     * edit roll <rollID> <Name> <permissions>[array] = edit Roll by ID
     * delete user <userID> = delete User
     * delete roll <rollID> = delete Roll
     *
     * add roll <userID> <rollID> = add Roll to user else add Default Roll to user
     * remove roll <userID> <rollID> = remove Roll to user
     *
     * edit media <mediaID> <name> <mediaFile> <mediaThumbnail> <mediaDescription> <mediaOwner> <mediaAuthor>[array] <mediaTags>[array] <mediaRolls>[array] = edit Media Information
     * remove media <mediaID> = remove Media
     *
     * config = print all Config Commands;
     * config lang = print all available Languages
     * config lang <lang> = set Language
     *
     */

    public void decrypt(String command) throws CommandException {
        command = command.replaceAll("  "," ");

        this.command = command;

        String[] commandElements = command.split(" ");
        if(commandElements.length == 0) throw new CommandException("Unknown Command: " + command);

        if(commandElements[0].equalsIgnoreCase("help")) helpCommands(commandElements);
        else if(commandElements[0].equalsIgnoreCase("info")) infoCommands(commandElements);
        else if(commandElements[0].equalsIgnoreCase("config")) configCommands(commandElements);
        else throw new CommandItemException(command, commandElements[0]);
    }

    private void infoCommands(String[] items) throws CommandItemException {
        if(items.length == 1) MessageBuilder.printSeverMessages("General Information's","Version = " + Properties.VERSION,
                "Current Language = " + Properties.LANG);
        else if(items[1].equalsIgnoreCase("user")) System.out.println("User Information's");
        else if(items[1].equalsIgnoreCase("media")) System.out.println("Media Information's");
        else if(items[1].equalsIgnoreCase("music")) System.out.println("Music Information's");
        else if(items[1].equalsIgnoreCase("image")) System.out.println("Image Information's");
        else if(items[1].equalsIgnoreCase("video")) System.out.println("Video Information's");
        else throw new CommandItemException(command, items[1]);
    }

    private void configCommands(String[] items) throws CommandItemException {
        if(items.length == 1) System.out.println("Config Information's");
        else if(items[1].equalsIgnoreCase("lang") && items.length == 2) MessageBuilder.printArraysWithTitle_Parallel("Available Languages", Properties.LANGUAGES, Properties.LANGUAGE_TAGS);
        else if(items[1].equalsIgnoreCase("lang") && items.length == 3)
        {
            if (Assets.compareWithArray(items[2],Properties.LANGUAGE_TAGS))
            {
                Properties.LANG = items[2];
                MessageBuilder.printSeverMessages("Change Language to " + items[2]);
            }
            else MessageBuilder.hint("<lang> neat to be an Language Tag. For example en_US for US-English");
        }
        else throw new CommandItemException(command, items[1]);
    }

    private void helpCommands(String[] items) throws CommandException {
        if(items.length == 1)
        {
            String out = "";
            out += "*Value Shortcuts:\n";
            out += "* -- = no change\n";
            out += "* -<value> = remove this from list without override\n";
            out += "* +<value> = add this to list without override\n";
            out += "*\n";
            out += "* Commands:\n";
            out += "* help = print all Commands\n";
            out += "*\n";
            out += "* info = print General Server Information's\n";
            out += "* info user = print User Information's\n";
            out += "* info media = print Media Information's\n";
            out += "* info music = print Music Media Information's\n";
            out += "* info image = print Image Media Information's\n";
            out += "* info video = print Video Media Information's\n";
            out += "*\n";
            out += "* create user <Name> <Password> = create new User\n";
            out += "* create roll <Name> <permissions>[array] = create new Roll\n";
            out += "* edit user <userID> <Name> <Password> = edit User\n";
            out += "* edit roll <rollID> <Name> <permissions>[array] = edit Roll\n";
            out += "* delete user <userID> = delete User\n";
            out += "* delete roll <rollID> = delete Roll\n";
            out += "*\n";
            out += "* add roll <userID> <rollID> = add Roll to user else add Default Roll to user\n";
            out += "* remove roll <userID> <rollID> = remove Roll to user\n";
            out += "*\n";
            out += "* edit media <mediaID> <name> <mediaFile> <mediaThumbnail> <mediaDescription> <mediaOwner> <mediaAuthor>[array] <mediaTags>[array] <mediaRolls>[array] = edit Media Information\n";
            out += "* remove media <mediaID> = remove Media\n";
            out += "*\n";
            out += "* config = print all Config Commands\n";
            out += "* config lang = print all available Languages\n";
            out += "* config lang <lang> = set Language\n";

            System.out.println(out);
        }
        else throw new CommandSyntaxException(command, "help");
    }
}
