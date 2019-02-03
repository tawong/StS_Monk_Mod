package relics;

import basemod.abstracts.CustomCard;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import powers.KiPower;

public class SatinRobe extends CustomRelic {
    private static final String ID = "SatinRobe";
    private static final int KI = 5;

    public SatinRobe() {
        super(ID, ImageMaster.loadImage("img/relics/robe.png"), ImageMaster.loadImage("img/relics/outline/robe.png"), RelicTier.BOSS, LandingSound.MAGICAL);
    }

    public void onEquip(){
        this.flash();
        AbstractDungeon.player.gold = 0;
    }

    public void atBattleStart() {
        this.flash();
    }

    public void atTurnStart(){
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new KiPower(AbstractDungeon.player, KI), KI));

    }

    public void onGainGold() {
        this.flash();
        AbstractDungeon.player.gold = 0;

    }


    public void onVictory() {

    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new SatinRobe();
    }
}