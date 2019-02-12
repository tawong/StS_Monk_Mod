package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import powers.EmpowerPower;
import powers.KiPower;

public class SpiritJar extends CustomRelic {
    private static final String ID = "SpiritJar";
    private static final int Ki = 5;

    public SpiritJar() {
        super(ID, ImageMaster.loadImage("img/relics/jar.png"), ImageMaster.loadImage("img/relics/outline/jar.png"), RelicTier.BOSS, LandingSound.MAGICAL);
    }


    public void onMonsterDeath(AbstractMonster m) {

        if (m.currentHealth == 0 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();

            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(m, this));
            AbstractDungeon.actionManager.addToTop(new HealAction(AbstractDungeon.player,AbstractDungeon.player, Ki));
        }


    }

    public void onVictory() {
        AbstractDungeon.actionManager.addToTop(new HealAction(AbstractDungeon.player,AbstractDungeon.player, Ki));
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new SpiritJar();
    }
}