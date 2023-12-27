// Durability - Check tool durability
// Copyright © 2023 - Obsidianlad
//
// This file is part of Durability.
//
// Durability is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// Durability is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with Durability. If not, see <https://www.gnu.org/licenses/>.

package lad.obsidian.durability;

import java.util.List;
import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.inventory.ItemStack;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.CommandExecutor;

/**
 * Durability Plugin
 *
 * Registers a /durability command, allowing the player to check tool durability.
 */
public class Durability extends JavaPlugin implements CommandExecutor
{
    private String name = "[Durability] ";

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnable()
    {
        getCommand("durability").setExecutor(this);
        getCommand("uses").setExecutor(this);

        getServer().getLogger().info(this.name + "Plugin enabled");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDisable()
    {
        getServer().getLogger().info(this.name + "Plugin disabled");
    }

    /**
     * Function called when something calls /durability
     * If it's a player, check if their hand has something with
     * a max durability in it, and calculate how many uses are
     * left. Then, calculate how many of the total percentage
     * those uses are and select a color. Green means there are
     * many uses left, while red means there are fewer uses left.
     * Finally, send a message with the info back to the player.
     *
     * @param sender CommandSender that called the command
     * @param command Command that got called
     * @param label Name or alias of the command
     * @param args Arguments to the command
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            ItemStack tool = player.getItemInHand();

            if(tool.getType().getMaxDurability() <= 0)
            {
                return true;
            }

            String remaining = Integer.toString(tool.getType().getMaxDurability() - tool.getDurability() + 1);

            ChatColor[] colors = {
                ChatColor.GREEN,
                ChatColor.DARK_GREEN,
                ChatColor.YELLOW,
                ChatColor.GOLD,
                ChatColor.RED,
                ChatColor.DARK_RED,
                ChatColor.BLACK,
            };

            double ratio = ((double) tool.getDurability() / (double) tool.getType().getMaxDurability());
            int percent = Math.toIntExact(Math.round(ratio * 6.0f));

            sender.sendMessage("Your " + colors[percent] + tool.getType().name() + ChatColor.WHITE + " has " + remaining + " uses left!");

            return true;
        }

        sender.sendMessage("Player command to check tool durability");

        return true;
    }
}
