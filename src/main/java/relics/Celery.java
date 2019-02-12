package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Celery extends CustomRelic {
    private static final String ID = "Celery";
    private static final int ENERGY = 1;
    private boolean gainEnergyNext = false;
    private boolean firstTurn = false;

    public Celery() {
        super(ID, ImageMaster.loadImage("img/relics/celery.png"), ImageMaster.loadImage("img/relics/outline/celery.png"), RelicTier.COMMON, LandingSound.FLAT);
        this.pulse = false;
    }


    public void atPreBattle() {
        this.flash();
        this.firstTurn = true;
        this.gainEnergyNext = true;
        if (!this.pulse) {
            this.beginPulse();
            this.pulse = true;
        }

    }

    public void atTurnStart() {
        this.beginPulse();
        this.pulse = true;
        if (this.gainEnergyNext && !this.firstTurn) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ArtifactPower(AbstractDungeon.player, 1), 1));
        }

        this.firstTurn = false;
        this.gainEnergyNext = true;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
            this.gainEnergyNext = false;
            this.pulse = false;

    }



    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Celery();
    }
}