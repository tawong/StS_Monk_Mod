
package actions;

import cards.AllOrNothing;
import cards.SpiritBarrier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.AllForOne;
import com.megacrit.cardcrawl.cards.blue.Claw;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.Iterator;

public class ReduceOnKiGainAction extends AbstractGameAction {
    private AbstractCard card;

    public ReduceOnKiGainAction(int amount) {
        this.duration = Settings.ACTION_DUR_FAST;
//        this.card = card;
        this.amount = amount;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {

            Iterator var1 = AbstractDungeon.player.discardPile.group.iterator();

            AbstractCard c;
            while(var1.hasNext()) {
                c = (AbstractCard)var1.next();
                if (c instanceof AllOrNothing || c instanceof SpiritBarrier) {
                    c.updateCost(-this.amount);
                    if(c.cost < 0){
                        c.cost = 0;
                    }
                    c.applyPowers();
                }
            }

            var1 = AbstractDungeon.player.drawPile.group.iterator();

            while(var1.hasNext()) {
                c = (AbstractCard)var1.next();
                if (c instanceof AllOrNothing || c instanceof SpiritBarrier) {
                    c.updateCost(-this.amount);
                    if(c.cost < 0){
                        c.cost = 0;
                    }
                    c.applyPowers();
                }
            }

            var1 = AbstractDungeon.player.hand.group.iterator();

            while(var1.hasNext()) {
                c = (AbstractCard)var1.next();
                if (c instanceof AllOrNothing || c instanceof SpiritBarrier) {
                    c.updateCost(-this.amount);
                    if(c.cost < 0){
                        c.cost = 0;
                    }
                    c.applyPowers();
                }
            }
        }

        this.tickDuration();
    }
}
