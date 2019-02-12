package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import powers.EmpowerPower;
import powers.KiPower;

public class BrassKnuckles extends CustomRelic {
    private static final String ID = "BrassKnuckles";
    private static final int HEAL = 2;

    public BrassKnuckles() {
        super(ID, ImageMaster.loadImage("img/relics/knuckles.png"), ImageMaster.loadImage("img/relics/outline/knuckles.png"), RelicTier.UNCOMMON, LandingSound.HEAVY);
    }

    public void atTurnStart() {
        this.counter = 0;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            ++this.counter;
            if (this.counter % 3 == 0) {
                this.counter = 0;
                this.flash();
                AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player, AbstractDungeon.player, HEAL));
            }
        }

    }

    public void onVictory() {
        this.counter = -1;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new BrassKnuckles();
    }
}