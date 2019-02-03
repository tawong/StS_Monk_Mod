package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import patches.AbstractCardEnum;
import powers.KiPower;

public class ExtremeFocus extends CustomCard {
	public static final String ID = "ExtremeFocus";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int PREP = 3;
	private static final int PREP_UP = -1;


	public ExtremeFocus() {
		super(ID, NAME, "img/cards/focus.png", COST, DESCRIPTION, CardType.POWER,
				AbstractCardEnum.VIRIDIAN, CardRarity.RARE,
				CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = PREP;
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

		int KiAmt = 0;


			if(p.hasPower("KiPower")){
				KiAmt = p.getPower("KiPower").amount;
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KiPower(p, -KiAmt), -KiAmt));
			}
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, this.magicNumber), this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "Regeneration"));


	}

	public AbstractCard makeCopy() {
		return new ExtremeFocus();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(PREP_UP);
		}
	}
}