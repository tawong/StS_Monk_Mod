//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cards;

import actions.ChannelSpiritAction;
import actions.PlannedAttackAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches.AbstractCardEnum;
import powers.PreparationPower;


public class ChannelSpirit extends CustomCard {
    public static final String ID = "ChannelSpirit";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 0;
    private static final int PREP = 2;
    private static final int PREP_UP = -1;

    public ChannelSpirit() {
        super(ID, NAME, "img/cards/channelspirit.png", COST, DESCRIPTION, CardType.SKILL,
                AbstractCardEnum.VIRIDIAN, CardRarity.UNCOMMON,
                CardTarget.SELF);
        this.baseDamage = 0;
        this.baseMagicNumber = this.magicNumber = PREP;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PreparationPower(p, this.magicNumber), this.magicNumber));

        AbstractDungeon.actionManager.addToBottom(new ChannelSpiritAction(p, p, 1, false));
    }

    public AbstractCard makeCopy() {
        return new ChannelSpirit();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(PREP_UP);
        }

    }


}
