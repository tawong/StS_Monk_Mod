package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import patches.AbstractCardEnum;
import powers.PrimalPower;

public class PrimalForm extends CustomCard {
	public static final String ID = "PrimalForm";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 3;
	private static final int APPLY_POWER = 1;

	public PrimalForm() {
		super(ID, NAME, "img/cards/primalform.png", COST, DESCRIPTION, CardType.POWER,
				AbstractCardEnum.VIRIDIAN, CardRarity.RARE,
				CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = APPLY_POWER;
		this.isEthereal = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PrimalPower(p, this.magicNumber), this.magicNumber));

	}

	public AbstractCard makeCopy() {
		return new PrimalForm();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = UPGRADE_DESCRIPTION;
			this.isEthereal = false;
//			upgradeMagicNumber(UPGRADE_COST);
			this.initializeDescription();
		}
	}
}