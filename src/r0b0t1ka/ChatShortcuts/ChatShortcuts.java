package r0b0t1ka.ChatShortcuts;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
/**
 *
 * @author r0b0t1ka
 */
import org.bukkit.plugin.java.*;
public final class ChatShortcuts extends JavaPlugin
{
    /**
     *
     * @throws IOException
     */
    @Override
    public void onEnable()
    {
        getLogger().info("ChatShortcuts has been enabled!");
        try {
            writethem();
        } catch (IOException ex) {
            Logger.getLogger(ChatShortcuts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void onDisable()
    {
        getLogger().info("ChatShortcuts has been disabled!");
    }
    @SuppressWarnings("empty-statement")
    public void writethem() throws IOException
    {
        File file = new File("wtf.txt");
        String filepath = file.getCanonicalPath();
        filepath = filepath.replace("\\wtf.txt", "");
        boolean dirMake = new File(filepath + "\\plugins\\ChatShortcuts").mkdir();
        if (!dirMake)
        {
            getLogger().info("Plugin folder already exits or couldn't be created");
        }
        if (dirMake)
        {
            getLogger().info("Plugin folder successfully created!");
            dirMake = new File(filepath + "\\plugins\\ChatShortcuts\\commands.yml").createNewFile();
            if (!dirMake)
                getLogger().info("OK, something is wrong, plugin couldn't create commands.yml");
            dirMake = new File(filepath + "\\plugins\\ChatShortcuts\\plugin.yml").createNewFile();
            if (!dirMake)
                getLogger().info("OK, something is wrong, plugin couldn't create plugin.yml");
            try (PrintWriter writing = new PrintWriter(new File (filepath + "\\plugins\\ChatShortcuts\\commands.yml"))) {
                writing.println("eg|ChatShortcuts command example!|eg|orange||console|player||");
            }
        }
        Scanner in;
        try (PrintWriter writer = new PrintWriter(new File(filepath + "\\plugins\\ChatShortcuts\\plugin.yml"))) {
            writer.println("name: ChatShortcuts");
            writer.println("main: r0b0t1ka.ChatShortcuts.ChatShortcuts");
            writer.println("version: 1.3");
            writer.println("author: r0b0t1ka");
            writer.println("commands:");
            ArrayList<String> commands = new ArrayList<>();
            in = new Scanner(new File(filepath + "\\plugins\\ChatShortcuts\\commands.yml"));
            while(in.hasNextLine())
            {
                String fileIn = in.nextLine();
                commands.add(fileIn);
            }
            commands.trimToSize();
            String[] cmds = new String[commands.size()];
            String[] cmdName = new String[commands.size()];
            String[] cmdDesc = new String[commands.size()];
            String[] cmdUsag = new String[commands.size()];
            String[] cmdPerm = new String[commands.size()];
            String[] cmdPerM = new String[commands.size()];
            for (int counter = 0; counter < cmds.length; counter++)
            {
                cmds[counter] = commands.get(counter);
            }
            for (int counter = 0; counter < cmds.length; counter++)
            {
                int index = cmds[counter].indexOf("|");
                cmdName[counter] = cmds[counter].substring(0, index);
                cmds[counter] = cmds[counter].substring((index) + 1);
                index = cmds[counter].indexOf("|");
                cmdDesc[counter] = cmds[counter].substring(0, index);
                cmds[counter] = cmds[counter].substring((index) + 1);
                cmdUsag[counter] = ("/" + cmdName[counter]);
                cmdPerm[counter] = ("ChatShortcuts." + cmdName[counter]);
                cmdPerM[counter] = ("You don't have <permission>!");
            }
            for (int counter = 0; counter < cmdName.length; counter++)
            {
                writer.println("   " + cmdName[counter] + ":");
                writer.println("      description: " + cmdDesc[counter]);
                writer.println("      usage: " + cmdUsag[counter]);
                writer.println("      permission: " + cmdPerm[counter]);
                writer.println("      permission-message: " + cmdPerM[counter]);
            }
            getLogger().info("ChatShortcuts has successfully written the plugin.yml file!");
            getLogger().info("Plugin.yml must be placed into the root folder of the plugin jar");
        }
        in.close();
    }    
    /**
     *
     * @param sender
     * @param cmd
     * @param label
     * @param args
     * @return
     * @throws FileNotFoundException
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        ArrayList<String> commands = new ArrayList<>();
        ArrayList<String> say = new ArrayList<>();
        ArrayList<String> perm = new ArrayList<>();
        ArrayList<String> color = new ArrayList<>();
        ArrayList<String> style = new ArrayList<>();
        ArrayList<Boolean> con = new ArrayList<>();
        ArrayList<Boolean> play = new ArrayList<>();
        ArrayList<Boolean> anon = new ArrayList<>();
        File file = new File("wtf.txt");
        String filepath = null;
        try {
            filepath = file.getCanonicalPath();
        } catch (IOException ex) {
            Logger.getLogger(ChatShortcuts.class.getName()).log(Level.SEVERE, null, ex);
        }
        filepath = filepath.replace("\\wtf.txt", "");        
        Scanner in = null;
        try {
            in = new Scanner(new File(filepath + "\\plugins\\ChatShortcuts\\commands.yml"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChatShortcuts.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean command = false;
        while(in.hasNextLine())
        {
            String cmds = in.nextLine();
            int index = cmds.indexOf("|");
            String cmdN = cmds.substring(0, index);
            commands.add(cmdN);
            cmds = cmds.substring(index + 1);
            index = cmds.indexOf("|");
            String cmdSay = cmds.substring(0, index);
            say.add(cmdSay);
            cmds = cmds.substring(index + 1);
            perm.add("ChatShortcuts." + cmdN);
            index = cmds.indexOf("|");
            cmds = cmds.substring(index + 1);
            index = cmds.indexOf("|");
            String colour = cmds.substring(0, index);
            switch (colour) {
                case "darkblue":
                    colour = "§1";
                    break;
                case "black":
                    colour = "§0";
                    break;            
                case "darkgreen":
                    colour = "§2";
                    break;
                case "darkaqua":
                    colour = "§3";
                    break;
                case "darkred":
                    colour = "§4";
                    break;
                case "purple":
                    colour = "§5";
                    break;
                case "orange":
                    colour = "§6";
                    break;
                case "grey":
                    colour = "§7";
                    break;
                case "darkgrey":
                    colour = "§8";
                    break;
                case "indigo":
                    colour = "§9";
                    break;
                case "brightgreen":
                    colour = "§A";
                    break;
                case "aqua":
                    colour = "§B";
                    break;
                case "red":
                    colour = "§C";
                    break;
                case "pink":
                    colour = "§D";
                    break;
                case "yellow":
                    colour = "§E";
                    break;
                case "white":
                    colour = "§F";
                    break;
                default:
                    colour = "§F";
                    break;
            }
            color.add(colour);
            cmds = cmds.substring(index + 1);
            index = cmds.indexOf("|");
            String txtstyle = cmds.substring(0, index);
            switch (txtstyle) { 
                case "random":
                    txtstyle = "§K";
                    break;
                case "bold":
                    txtstyle = "§L";
                    break;
                case "strike":
                    txtstyle = "§M";
                    break;
                case "underline":
                    txtstyle = "§N";
                    break;
                case "italics":
                    txtstyle = "§O";
                    break;
                default:
                    txtstyle = "";
                    break;
            }
            style.add(txtstyle);
            cmds = cmds.substring(index + 1);
            index = cmds.indexOf("|");
            String consoletruth = cmds.substring(0, index);
            if (consoletruth.equals("console"))
                con.add(true);
            else
                con.add(false);
            cmds = cmds.substring(index + 1);
            index = cmds.indexOf("|");
            String playertruth = cmds.substring(0, index);
            if (playertruth.equals("player"))
                play.add(true);
            else
                play.add(false);
            cmds = cmds.substring(index + 1);
            index = cmds.indexOf("|");
            String anony = cmds.substring(0, index);
            if (anony.equals("anon"))
                anon.add(true);
            else
                anon.add(false);
        }
        commands.trimToSize();
        say.trimToSize();
        perm.trimToSize();
        color.trimToSize();
        style.trimToSize();
        con.trimToSize();
        play.trimToSize();
        anon.trimToSize();
        for (int counter = 0; counter < commands.size(); counter++)
        {
            if (cmd.getName().equalsIgnoreCase(commands.get(counter)))
            {
                if (!(sender instanceof Player) && con.get(counter))
                {
                    if (anon.get(counter))
                    {
                        cmd.broadcastCommandMessage(sender, color.get(counter) + style.get(counter) + say.get(counter));
                    }
                    else
                        getServer().dispatchCommand(sender, "say §R" + color.get(counter) + style.get(counter) + say.get(counter));
                        
                    command = true;
                }
                else if (!(sender instanceof Player) && !(con.get(counter)))
                {
                    sender.sendMessage("This command is for players only -_-");
                    command = false;
                }
                else
                {
                    Player player = (Player) sender;
                    if (player.hasPermission(perm.get(counter)) && play.get(counter))
                    {
                        String playername = "";
                        String playerAnonymous = "§K";
                        if (anon.get(counter))
                        {
                            playername = player.getDisplayName();
                            player.setDisplayName(playerAnonymous + playername + "§R");
                            player.chat("§R" + color.get(counter) + style.get(counter) + say.get(counter));
                            player.setDisplayName(playername);
                        }
                        else                            
                        {
                            player.chat("§R" + color.get(counter) + style.get(counter) + say.get(counter));
                        }
                        command = true;
                    }
                    else if (player.hasPermission(perm.get(counter)) && !(play.get(counter)))
                    {
                        sender.sendMessage("This command is for the console only");
                        command = false;
                    }
                    else if (!player.hasPermission(perm.get(counter)) && !(play.get(counter)))
                    {
                        sender.sendMessage("You don't have permission to use this and it's for the console only.");
                        command = false;
                    }
                    else if (!player.hasPermission(perm.get(counter)) && !(play.get(counter)))
                    {
                        sender.sendMessage("You don't have permission to use this command.");
                        command = false;
                    }
                }
            }
        }
        in.close();
        return command;
    }    
}
