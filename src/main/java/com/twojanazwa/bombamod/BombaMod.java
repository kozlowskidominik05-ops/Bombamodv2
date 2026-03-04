package com.twojanazwa.bombamod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.text.Text;

public class BombaMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Rejestracja komendy /bomba <nick>
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("bomba")
                .then(ClientCommandManager.argument("nick", StringArgumentType.string())
                .executes(context -> {
                    String victim = StringArgumentType.getString(context, "nick");
                    
                    // Wiadomość na czacie (widoczna tylko dla Ciebie)
                    context.getSource().sendFeedback(Text.literal("§c[!] Inicjacja protokołu na: §f" + victim));
                    context.getSource().sendFeedback(Text.literal("§7Wysyłanie pakietów ruchu..."));
                    
                    // Odpalenie silnika lagów z drugiego pliku
                    ExploitLogic.startSpam();
                    
                    return 1;
                })));
        });
    }
}

