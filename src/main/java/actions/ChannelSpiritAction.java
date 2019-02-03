package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import java.util.Iterator;

import javax.swing.*;
import java.util.Iterator;

public class ChannelSpiritAction extends AbstractGameAction {

//    private static final UIStrings uiStrings;
//    public static final String[] TEXT;
    public static final String[] Custom = new String[1];
    private AbstractPlayer p;
    private boolean isRandom;
    private boolean anyNumber;
    private boolean canPickZero;
    public static int cardsReduced;

    public ChannelSpiritAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom) {
        this(target, source, amount, isRandom, false, false);
    }

    public ChannelSpiritAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean anyNumber, boolean canPickZero) {
        this.canPickZero = false;
        this.anyNumber = anyNumber;
        this.canPickZero = canPickZero;
        this.p = (AbstractPlayer)target;
        this.isRandom = isRandom;
        this.setValues(target, source, amount);
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public ChannelSpiritAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean anyNumber) {
        this(target, source, amount, isRandom, anyNumber, false);
    }

    public void update() {
        this.Custom[0] = "card to reduce";
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.size() == 0) {
                this.isDone = true;
                return;
            }

            int i;
            if (!this.anyNumber && this.p.hand.size() <= this.amount) {
                this.amount = this.p.hand.size();
                cardsReduced = this.amount;
                i = this.p.hand.size();

                for(int j = 0; j < i; ++j) {
                    AbstractCard c = this.p.hand.getTopCard();
                    c.costForTurn = (c.costForTurn-5);
                    if(c.costForTurn < 0) c.costForTurn = 0;
                    this.p.hand.addToTop(c);
                }

                this.isDone = true;
                return;
            }

            if (!this.isRandom) {
                cardsReduced = this.amount;
                AbstractDungeon.handCardSelectScreen.open(Custom[0], this.amount, this.anyNumber, this.canPickZero);
                this.tickDuration();
                return;
            }

//            for(i = 0; i < this.amount; ++i) {
//                this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng).modifyCostForTurn(0);
//            }

        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            Iterator var4 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();

            while(var4.hasNext()) {
                AbstractCard c = (AbstractCard)var4.next();
                c.costForTurn = c.costForTurn-5;
                if(c.costForTurn < 0) c.costForTurn = 0;
                this.p.hand.addToTop(c);
            }

            this.p.hand.refreshHandLayout();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }

        this.tickDuration();
    }

    static {

    }
}
