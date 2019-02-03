//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cards;

import actions.AlignSelfAction;
import actions.PlannedAttackAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches.AbstractCardEnum;


public class AlignSelf extends CustomCard {
    public static final String ID = "AlignSelf";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = -1;

    public AlignSelf() {
        super(ID, NAME, "img/cards/alignself.png", COST, DESCRIPTION, CardType.SKILL,
                AbstractCardEnum.VIRIDIAN, CardRarity.RARE,
                CardTarget.SELF);
        this.baseDamage = 0;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new AlignSelfAction(p, this.upgraded, this.freeToPlayOnce, this.energyOnUse));
    }

    public AbstractCard makeCopy() {
        return new AlignSelf();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }

    }


}
