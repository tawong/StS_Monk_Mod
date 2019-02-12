package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Wallet extends CustomRelic {
    private static final String ID = "Wallet";


    public Wallet() {
        super(ID, ImageMaster.loadImage("img/relics/wallet.png"), ImageMaster.loadImage("img/relics/outline/wallet.png"), RelicTier.UNCOMMON, LandingSound.FLAT);
        this.pulse = false;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            AbstractDungeon.player.gold += 1;
        }

    }



    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Wallet();
    }
}