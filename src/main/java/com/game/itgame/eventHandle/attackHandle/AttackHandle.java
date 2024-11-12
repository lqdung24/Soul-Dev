package com.game.itgame.eventHandle.attackHandle;

import com.game.itgame.entity.player.Player;

public class AttackHandle {
    Player player;
    SwordAttack sword;

    public AttackHandle(Player p) {
        player = p;
        sword = new SwordAttack(10, 45, 1);
    }

}
