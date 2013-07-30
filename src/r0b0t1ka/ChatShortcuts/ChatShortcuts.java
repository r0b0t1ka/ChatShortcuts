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
            PrintWriter writing = new PrintWriter(new File (filepath + "\\plugins\\ChatShortcuts\\commands.yml"));
            writing.println("eg|ChatShortcuts command example!|eg|orange||");            
            writing.close();
        }
        PrintWriter writer = new PrintWriter(new File(filepath + "\\plugins\\ChatShortcuts\\plugin.yml"));
        writer.println("name: ChatShortcuts");
        writer.println("main: r0b0t1ka.ChatShortcuts.ChatShortcuts");
        writer.println("version: 0.1");
        writer.println("author: r0b0t1ka");
        writer.println("commands:");
        ArrayList<String> commands = new ArrayList<>();  
        Scanner in = new Scanner(new File(filepath + "\\plugins\\ChatShortcuts\\commands.yml"));
       // if (checkd.equalsIgnoreCase(""))
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
        writer.close();
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
            if (colour.equals("darkblue"))
                    colour = "§1";
            else if (colour.equals("black"))
                    colour = "§0";
            else if (colour.equals("darkgreen"))            
                    colour = "§2";
            else if (colour.equals("darkaqua"))
                    colour = "§3";
            else if (colour.equals("darkred"))
                    colour = "§4";
            else if (colour.equals("purple"))
                    colour = "§5";
            else if (colour.equals("orange"))
                    colour = "§6";
            else if (colour.equals("grey"))
                    colour = "§7";
            else if (colour.equals("darkgrey"))
                    colour = "§8";
            else if (colour.equals("indigo"))
                    colour = "§9";
            else if (colour.equals("brightgreen"))
                    colour = "§A";
            else if (colour.equals("aqua"))
                    colour = "§B";
            else if (colour.equals("red"))
                    colour = "§C";
            else if (colour.equals("pink"))
                    colour = "§D";
            else if (colour.equals("yellow"))
                    colour = "§E";
            else if (colour.equals("white"))
                    colour = "§F";
            else
                    colour = "§F";
            color.add(colour);
            cmds = cmds.substring(index + 1);
            index = cmds.indexOf("|");
            String txtstyle = cmds.substring(0, index);
            if (txtstyle.equals("random")) 
                    txtstyle = "§K";
            else if (txtstyle.equals("bold"))
                    txtstyle = "§L";
            else if (txtstyle.equals("strike"))
                    txtstyle = "§M";
            else if (txtstyle.equals("underline"))
                    txtstyle = "§N";
            else if (txtstyle.equals("italics"))
                    txtstyle = "§O";
            else
                    txtstyle = "";
            style.add(txtstyle);
        }
        commands.trimToSize();
        say.trimToSize();
        perm.trimToSize();
        color.trimToSize();
        style.trimToSize();
        for (int counter = 0; counter < commands.size(); counter++)
        {
            if (cmd.getName().equalsIgnoreCase(commands.get(counter)))
            {
                if (!(sender instanceof Player))
                {
                    sender.sendMessage("This command is for players only!");
                    sender.sendMessage("Use the say command, lazy!");
                    command = false;
                }
                else
                {
                    Player player = (Player) sender;
                    if (player.hasPermission(perm.get(counter)))
                    {
                        player.chat("§R" + color.get(counter) + style.get(counter) + say.get(counter));
                        command = true;
                    }
                    else
                    {
                        sender.sendMessage("You don't have permission to use this.");
                        command = false;
                    }
                }
            }
        }
        in.close();
        return command;
    }    
}
